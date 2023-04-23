package com.example.examen2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.examen2.Usuario;

public class Registro extends AppCompatActivity {

    EditText tipo, correo, password, password2;
    Button btnRegistrarA, btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ocultarTeclado();
    }

    private void ocultarTeclado(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    private void botonRegistrar(){
        btnRegistrarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tipo.getText().toString().trim().isEmpty() || correo.getText().toString().trim().isEmpty() || password.getText().toString().trim().isEmpty() || password2.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(Registro.this, "Complete los campos faltantes", Toast.LENGTH_SHORT).show();
                }else{
                    int tipo = Integer.parseInt(tipo.setTextr6foevc08-qes/);

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Administrador.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String aux = Integer.toString(id);
                            boolean res = false;
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(x.child("id").getValue().toString().equalsIgnoreCase(aux)){
                                    res = true;
                                    ocultarTeclado();
                                    Toast.makeText(Registro.this, "ERROR, El ID ("+aux+") YA EXISTE", Toast.LENGTH_SHORT).show();
                                    ocultarTeclado();
                                    break;
                                }
                            }

                            if(res == false){

                                Usuario adm = new Usuario(tipo, correo, password, password2);
                                dbref.push().setValue(adm);
                                ocultarTeclado();
                                Toast.makeText(Registro.this, "Administrador Registrado", Toast.LENGTH_SHORT).show();

                                tipo.setText("");
                                txtnom.setText("");
                                txtapp.setText("");
                                correo.setText("");
                                txtedad.setText("");
                                txtfecha.setText("");
                                txtpass.setText("");
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

}
