package com.example.examen;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Registro extends AppCompatActivity {

    private EditText txttipo, txtusuario, txtpassword, txtpassword2;
    private Button btnreg, btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txttipo   = (EditText) findViewById(R.id.txttipo);
        txtusuario  = (EditText) findViewById(R.id.txtusuario);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        txtpassword2 = (EditText) findViewById(R.id.txtpassword2);
        btnreg  = (Button)   findViewById(R.id.btnreg);
        btnIniciar  = (Button)   findViewById(R.id.btnIniciar);

        botonRegistrar();

    }


    private void botonRegistrar(){
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txttipo.getText().toString().trim().isEmpty() || txtusuario.getText().toString().trim().isEmpty() || txtpassword.getText().toString().trim().isEmpty() || txtpassword2.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(Registro.this, "Complete los campos faltantes", Toast.LENGTH_SHORT).show();
                }else{
                    int tipo = Integer.parseInt(txttipo.getText().toString());
                    String user = txtusuario.getText().toString();
                    String password = txtpassword.getText().toString();
                    String password2 = txtpassword2.getText().toString();
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Usuarios.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            boolean res = false;
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(x.child("user").getValue().toString().equalsIgnoreCase(user)){
                                    res = true;
                                    ocultarTeclado();
                                    Toast.makeText(Registro.this, "ERROR, El ID ("+user+") YA EXISTE", Toast.LENGTH_SHORT).show();
                                    ocultarTeclado();
                                    break;
                                }
                            }

                            if(res == false){
                                if(txttipo.getText().toString().equals("1")){
                                    if(txtpassword.getText().toString().equals(txtpassword2.getText().toString())){
                                        Usuarios adm = new Usuarios(tipo, user, password, password2);
                                        dbref.push().setValue(adm);
                                        ocultarTeclado();
                                        Toast.makeText(Registro.this, "Administrador Registrado", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Registro.this, Admin.class);
                                        startActivity(intent);
                                    }else {
                                        Toast.makeText(Registro.this, "Las contrasenas son incoreectas", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Usuarios adm = new Usuarios(tipo, user, password, password2);
                                    dbref.push().setValue(adm);
                                    ocultarTeclado();
                                    Toast.makeText(Registro.this, "Cliente Registrado", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Registro.this, Cliente.class);
                                    startActivity(intent);
                                }

                                txttipo.setText("");
                                txtusuario.setText("");
                                txtpassword.setText("");
                                txtpassword2.setText("");
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });
    }//findelbotonregistrar


    private void ocultarTeclado(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    } // Cierra el método ocultarTeclado.


    private void botonBuscar(){
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtusuario.getText().toString().trim().isEmpty()){
                    Toast.makeText(Registro.this, "Digite el ID del usuario a buscar", Toast.LENGTH_SHORT).show();

                }else{
                    int id = Integer.parseInt(txtusuario.getText().toString());

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Admin.class.getSimpleName());
                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String aux = Integer.toString(id);

                            boolean res = false;
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(aux.equalsIgnoreCase(x.child("id").getValue().toString())){
                                    res = true;
                                    ocultarTeclado();
                                    Intent intent = new Intent(Registro.this, Admin.class);
                                    startActivity(intent);

                                    break;
                                }
                            }
                            if(res == false){
                                Toast.makeText(Registro.this, "ID ("+aux+") No Encontrado", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }//fin if
            }
        });

    }//botonbuscar
}