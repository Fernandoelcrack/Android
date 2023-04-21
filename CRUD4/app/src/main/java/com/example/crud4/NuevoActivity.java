package com.example.crud4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud4.db.DbContactos;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre;
    EditText txtCinta;
    EditText txtCategoria;
    EditText txtEscuela;
    String txtCompite;
    Button btnGuarda;
    int condicion;
    String categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtCinta = findViewById(R.id.txtCinta);
        txtCategoria = findViewById(R.id.txtCategoria);
        txtEscuela = findViewById(R.id.txtEscuela);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                categoria = txtCategoria.getText().toString();
                condicion=Integer.parseInt(categoria);
                if (!txtNombre.getText().toString().equals("") && !txtCinta.getText().toString().equals("") && !txtCategoria.getText().toString().equals("") && !txtEscuela.getText().toString().equals("")) {
                    if (condicion >= 41 && condicion <= 45) {
                        DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                        txtCompite = "MINIMOSCA";
                        long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtCinta.getText().toString(), txtCategoria.getText().toString(), txtEscuela.getText().toString(), txtCompite);

                        if (id > 0) {
                            Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                            limpiar();
                        } else {
                            Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if (condicion >= 45 && condicion <= 49) {
                        DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                        txtCompite = "MOSCA";
                        long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtCinta.getText().toString(), txtCategoria.getText().toString(), txtEscuela.getText().toString(), txtCompite);

                        if (id > 0) {
                            Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                            limpiar();
                        } else {
                            Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                        }
                    } else if (condicion >= 49 && condicion <= 54) {
                        DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                        txtCompite = "GALLO";
                        long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtCinta.getText().toString(), txtCategoria.getText().toString(), txtEscuela.getText().toString(), txtCompite);

                        if (id > 0) {
                            Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                            limpiar();
                        } else {
                            Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                        }
                    } else if (condicion >= 54 && condicion <= 59) {
                        DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                        txtCompite = "PLUMA";
                        long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtCinta.getText().toString(), txtCategoria.getText().toString(), txtEscuela.getText().toString(), txtCompite);

                        if (id > 0) {
                            Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                            limpiar();
                        } else {
                            Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                        }
                    }else if (condicion >= 59 && condicion <= 65){
                        DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                        txtCompite = "LIGERO";
                        long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtCinta.getText().toString(), txtCategoria.getText().toString(), txtEscuela.getText().toString(), txtCompite);

                        if (id > 0) {
                            Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                            limpiar();
                        } else {
                            Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                        }
                    }else if (condicion >= 65 && condicion <= 71){
                        DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                        txtCompite = "SUPER LIGERO";
                        long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtCinta.getText().toString(), txtCategoria.getText().toString(), txtEscuela.getText().toString(), txtCompite);

                        if (id > 0) {
                            Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                            limpiar();
                        } else {
                            Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                        }
                    }else if (condicion >= 71 && condicion <= 78){
                        DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                        txtCompite = "MEDIO";
                        long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtCinta.getText().toString(), txtCategoria.getText().toString(), txtEscuela.getText().toString(), txtCompite);

                        if (id > 0) {
                            Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                            limpiar();
                        } else {
                            Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                        }
                    }else if (condicion >= 78 && condicion <= 200){
                        DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                        txtCompite = "PESADO";
                        long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtCinta.getText().toString(), txtCategoria.getText().toString(), txtEscuela.getText().toString(), txtCompite);

                        if (id > 0) {
                            Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                            limpiar();
                        } else {
                            Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                        }
                    }
                    }
                }
            });


    }



    private void limpiar(){
        txtNombre.setText("");
        txtCinta.setText("");
        txtCategoria.setText("");
        txtEscuela.setText("");
    }
}