package com.example.examen2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Administradores extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administradores);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin, menu);
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
        Intent intent = new Intent(this, Registrar_Productos.class);
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