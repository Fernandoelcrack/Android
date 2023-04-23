package com.example.examen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {

    private EditText txtid, txtnom, txtprecio;
    private Button btnbus, btnmod, btnreg, btneli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        txtid   = (EditText) findViewById(R.id.txtid);
        txtnom  = (EditText) findViewById(R.id.txtnom);
        txtprecio = (EditText) findViewById(R.id.txtprecio);
        btnbus  = (Button)   findViewById(R.id.btnbus);
        btnmod  = (Button)   findViewById(R.id.btnmod);
        btnreg  = (Button)   findViewById(R.id.btnreg);
        btneli  = (Button)   findViewById(R.id.btneli);

        botonBuscar();
        botonModificar();
        botonRegistrar();
        botonEliminar();
    }

    private void botonBuscar(){
        btnbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtid.getText().toString().trim().isEmpty()){
                    Toast.makeText(Admin.this, "Digite el ID del luchador a buscar", Toast.LENGTH_SHORT).show();

                }else{
                    int id = Integer.parseInt(txtid.getText().toString());

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
                                    txtnom.setText(x.child("nombre").getValue().toString());
                                    txtprecio.setText(x.child("precio").getValue().toString());
                                    
                                    break;
                                }
                            }
                            if(res == false){
                                Toast.makeText(Admin.this, "ID ("+aux+") No Encontrado", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }//fin if
            }
        });

    }//botonbuscar */

    private void botonModificar(){
        btnmod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtid.getText().toString().trim().isEmpty() || txtnom.getText().toString().trim().isEmpty() || txtprecio.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(Admin.this, "Complete los campos faltantes para actualizar", Toast.LENGTH_SHORT).show();
                }else{
                    int id = Integer.parseInt(txtid.getText().toString());
                    String nom = txtnom.getText().toString();
                    int precio = Integer.parseInt(txtprecio.getText().toString());


                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Admin.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String aux = Integer.toString(id);
                            boolean res = false;
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(x.child("id").getValue().toString().equalsIgnoreCase(aux)){
                                    res = true;
                                    ocultarTeclado();
                                    x.getRef().child("nombre").setValue(nom);
                                    x.getRef().child("precio").setValue(precio);
                                    txtid.setText("");
                                    txtnom.setText("");
                                    txtprecio.setText("");
                                    ocultarTeclado();
                                    break;
                                }
                            }

                            if(res == false){

                                ocultarTeclado();
                                Toast.makeText(Admin.this, "ID ("+aux+") de Admin Modificado", Toast.LENGTH_SHORT).show();

                                txtid.setText("");
                                txtnom.setText("");
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });

    }//finalmodificar */

    private void botonRegistrar(){
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtid.getText().toString().trim().isEmpty() || txtnom.getText().toString().trim().isEmpty() || txtprecio.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(Admin.this, "Complete los campos faltantes", Toast.LENGTH_SHORT).show();
                }else{
                    int id = Integer.parseInt(txtid.getText().toString());
                    String nom = txtnom.getText().toString();
                    int precio = Integer.parseInt(txtprecio.getText().toString());

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Admin.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String aux = Integer.toString(id);
                            boolean res = false;
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(x.child("id").getValue().toString().equalsIgnoreCase(aux)){
                                    res = true;
                                    ocultarTeclado();
                                    Toast.makeText(Admin.this, "ERROR, El ID ("+aux+") YA EXISTE", Toast.LENGTH_SHORT).show();
                                    ocultarTeclado();
                                    break;
                                }
                            }

                            if(res == false){
                                    Productos adm = new Productos(id, nom, precio);
                                    dbref.push().setValue(adm);
                                    ocultarTeclado();
                                    Toast.makeText(Admin.this, "Producto Registrado", Toast.LENGTH_SHORT).show();

                                txtid.setText("");
                                txtnom.setText("");
                                txtprecio.setText("");
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


    private void botonEliminar(){
        btneli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtid.getText().toString().trim().isEmpty()){
                    Toast.makeText(Admin.this, "Digite el ID del luchador a buscar", Toast.LENGTH_SHORT).show();

                }else{
                    int id = Integer.parseInt(txtid.getText().toString());
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Admin.class.getSimpleName());
                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String aux = Integer.toString(id);
                            final boolean[] res = {false};
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(aux.equalsIgnoreCase(x.child("id").getValue().toString())){
                                    AlertDialog.Builder a = new AlertDialog.Builder(Admin.this);
                                    a.setTitle("Pregunta");
                                    a.setMessage("Está seguro de querer borrar el registro?");
                                    a.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ocultarTeclado();
                                        }
                                    });
                                    a.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            res[0] = true;
                                            ocultarTeclado();
                                            x.getRef().removeValue();
                                            txtid.setText("");
                                            txtnom.setText("");
                                            txtprecio.setText("");

                                            Toast.makeText(Admin.this, "Registro eliminado", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                    a.show();
                                    break;

                                }
                            }
                            if(res[0] == true){
                                Toast.makeText(Admin.this, "ID ("+aux+") Imposible de Eliminar", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }//fin if
            }
        });
    }//fineliminar */

    private void ocultarTeclado(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    } // Cierra el método ocultarTeclado.

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuCe:
                CerrarSesion();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, Productos.class);
        startActivity(intent);
    }


    private void CerrarSesion(){
        Intent intent = new Intent(this, Principal.class);
        startActivity(intent);
        Toast.makeText(this, "Cerraste Sesion", Toast.LENGTH_SHORT).show();
    }


}