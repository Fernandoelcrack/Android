package com.example.crudfb2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private EditText txtid, txtnom, txtapp, txtcorreo, txtedad, txtfecha, txtpass;
    private Button btnbus, btnmod, btnreg, btneli;
    private ListView lvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtid   = (EditText) findViewById(R.id.txtid);
        txtnom  = (EditText) findViewById(R.id.txtnom);
        txtapp = (EditText) findViewById(R.id.txtapp);
        txtcorreo = (EditText) findViewById(R.id.txtcorreo);
        txtedad = (EditText) findViewById(R.id.txtedad);
        txtfecha = (EditText) findViewById(R.id.txtfecha);
        txtpass = (EditText) findViewById(R.id.txtpass);
        btnbus  = (Button)   findViewById(R.id.btnbus);
        btnmod  = (Button)   findViewById(R.id.btnmod);
        btnreg  = (Button)   findViewById(R.id.btnreg);
        btneli  = (Button)   findViewById(R.id.btneli);
        lvDatos = (ListView) findViewById(R.id.lvDatos);

        botonBuscar();
        botonModificar();
        botonRegistrar();
        botonEliminar();
        listarUsuarioes();
    }

    private void botonBuscar(){
        btnbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtid.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainActivity.this, "Digite el ID del luchador a buscar", Toast.LENGTH_SHORT).show();

                }else{
                    int id = Integer.parseInt(txtid.getText().toString());

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Usuario.class.getSimpleName());
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
                                    txtapp.setText(x.child("apellido").getValue().toString());
                                    txtcorreo.setText(x.child("correo").getValue().toString());
                                    txtedad.setText(x.child("edad").getValue().toString());
                                    txtfecha.setText(x.child("fecha").getValue().toString());
                                    txtpass.setText(x.child("pass").getValue().toString());
                                    break;
                                }
                            }
                            if(res == false){
                                Toast.makeText(MainActivity.this, "ID ("+aux+") No Encontrado", Toast.LENGTH_SHORT).show();
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
                if(txtid.getText().toString().trim().isEmpty() || txtnom.getText().toString().trim().isEmpty() || txtapp.getText().toString().trim().isEmpty() || txtcorreo.getText().toString().trim().isEmpty() || txtedad.getText().toString().trim().isEmpty() || txtfecha.getText().toString().trim().isEmpty() || txtpass.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(MainActivity.this, "Complete los campos faltantes para actualizar", Toast.LENGTH_SHORT).show();
                }else{
                    int id = Integer.parseInt(txtid.getText().toString());
                    String nom = txtnom.getText().toString();
                    String app = txtapp.getText().toString();
                    String correo = txtcorreo.getText().toString();
                    int edad = Integer.parseInt(txtedad.getText().toString());
                    String fecha = txtfecha.getText().toString();
                    String pass = txtpass.getText().toString();

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Usuario.class.getSimpleName());

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
                                    x.getRef().child("apellido").setValue(app);
                                    x.getRef().child("correo").setValue(correo);
                                    x.getRef().child("edad").setValue(edad);
                                    x.getRef().child("fecha").setValue(fecha);
                                    x.getRef().child("pass").setValue(pass);
                                    txtid.setText("");
                                    txtnom.setText("");
                                    txtapp.setText("");
                                    txtcorreo.setText("");
                                    txtedad.setText("");
                                    txtfecha.setText("");
                                    txtpass.setText("");
                                    ocultarTeclado();
                                    listarUsuarioes();
                                    break;
                                }
                            }

                            if(res == false){

                                ocultarTeclado();
                                Toast.makeText(MainActivity.this, "ID ("+aux+") de Usuario Modificado", Toast.LENGTH_SHORT).show();

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
                if(txtid.getText().toString().trim().isEmpty() || txtnom.getText().toString().trim().isEmpty() || txtapp.getText().toString().trim().isEmpty() || txtcorreo.getText().toString().trim().isEmpty() || txtedad.getText().toString().trim().isEmpty() || txtfecha.getText().toString().trim().isEmpty() || txtpass.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(MainActivity.this, "Complete los campos faltantes", Toast.LENGTH_SHORT).show();
                }else{
                    int id = Integer.parseInt(txtid.getText().toString());
                    String nom = txtnom.getText().toString();
                    String app = txtapp.getText().toString();
                    String correo = txtcorreo.getText().toString();
                    int edad = Integer.parseInt(txtedad.getText().toString());
                    String fecha = txtfecha.getText().toString();
                    String pass = txtpass.getText().toString();

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Usuario.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String aux = Integer.toString(id);
                            boolean res = false;
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(x.child("id").getValue().toString().equalsIgnoreCase(aux)){
                                    res = true;
                                    ocultarTeclado();
                                    Toast.makeText(MainActivity.this, "ERROR, El ID ("+aux+") YA EXISTE", Toast.LENGTH_SHORT).show();
                                    ocultarTeclado();
                                    break;
                                }
                            }

                            if(res == false){

                                Usuario adm = new Usuario(id, nom, app, correo, edad, fecha, pass);
                                dbref.push().setValue(adm);
                                ocultarTeclado();
                                Toast.makeText(MainActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();

                                txtid.setText("");
                                txtnom.setText("");
                                txtapp.setText("");
                                txtcorreo.setText("");
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

    private void listarUsuarioes(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbref = db.getReference(Usuario.class.getSimpleName());
        ArrayList<Usuario> lisadm = new ArrayList<Usuario>();
        ArrayAdapter <Usuario> ada = new ArrayAdapter<Usuario>(MainActivity.this, android.R.layout.simple_list_item_1, lisadm);
        lvDatos.setAdapter(ada);

        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Usuario adm = snapshot.getValue(Usuario.class);
                lisadm.add(adm);
                ada.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                ada.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Usuario adm = lisadm.get(position);
                AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this);
                a.setCancelable(true);
                a.setTitle("Usuario seleccionado");
                String msg = "ID : " + adm.getId() +"\n\n";
                msg += "NOMBRE : " +adm.getNombre() +adm.getApellido() +"\n\n";
                msg += "CORREO : " +adm.getCorreo() +"\n\n";
                msg += "EDAD : " +adm.getEdad() +"\n\n";
                msg += "FECHA : " +adm.getFecha() +"\n\n";

                a.setMessage(msg);
                a.show();
            }
        });

    }//final metodo


   private void botonEliminar(){
        btneli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtid.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainActivity.this, "Digite el ID del luchador a buscar", Toast.LENGTH_SHORT).show();

                }else{
                    int id = Integer.parseInt(txtid.getText().toString());
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Usuario.class.getSimpleName());
                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String aux = Integer.toString(id);
                            final boolean[] res = {false};
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(aux.equalsIgnoreCase(x.child("id").getValue().toString())){
                                    AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this);
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
                                            listarUsuarioes();
                                            txtid.setText("");
                                            txtnom.setText("");
                                            txtapp.setText("");
                                            txtcorreo.setText("");
                                            txtedad.setText("");
                                            txtfecha.setText("");
                                            txtpass.setText("");
                                            Toast.makeText(MainActivity.this, "Registro eliminado", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                    a.show();
                                    break;

                                }
                            }
                            if(res[0] == true){
                                Toast.makeText(MainActivity.this, "ID ("+aux+") Imposible de Eliminar", Toast.LENGTH_SHORT).show();
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


}