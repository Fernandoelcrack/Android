package com.example.crud4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.crud4.adaptadores.ListaContactosAdapter;
import com.example.crud4.db.DbContactos;
import com.example.crud4.db.DbHelper;
import com.example.crud4.entidades.Contactos;

import org.intellij.lang.annotations.JdkConstants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaContactos;
    ArrayList <Contactos> listaArrayContactos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaContactos = findViewById(R.id.listaContactos);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));

        DbContactos dbContactos = new DbContactos(MainActivity.this);
        listaArrayContactos = new ArrayList<>();

        ListaContactosAdapter adapter = new ListaContactosAdapter(dbContactos.mostrarContactos());
        listaContactos.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        listaContactos.addItemDecoration(dividerItemDecoration);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }
}