package com.example.crudfb2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Administradores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administradores);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_administradores, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuP:
                nuevoRegistro();
                return true;
            case R.id.menuPe:
                Verperfil();
                return true;
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

    private void Verperfil(){
        Intent intent = new Intent(this, Perfil.class);
        startActivity(intent);
    }

    private void CerrarSesion(){
        Intent intent = new Intent(this, Principal.class);
        startActivity(intent);
        Toast.makeText(this, "Cerraste Sesion", Toast.LENGTH_SHORT).show();
    }
}