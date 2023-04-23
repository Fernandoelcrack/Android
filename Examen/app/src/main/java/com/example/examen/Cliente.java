package com.example.examen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Cliente extends AppCompatActivity {
    private ListView lvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        lvDatos = (ListView) findViewById(R.id.lvDatos);
        listarProductoses();
    }

    private void listarProductoses(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbref = db.getReference(Productos.class.getSimpleName());
        ArrayList<Productos> lisadm = new ArrayList<Productos>();
        ArrayAdapter<Productos> ada = new ArrayAdapter<Productos>(Cliente.this, android.R.layout.simple_list_item_1, lisadm);
        lvDatos.setAdapter(ada);

        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Productos adm = snapshot.getValue(Productos.class);
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
                Productos adm = lisadm.get(position);
                AlertDialog.Builder a = new AlertDialog.Builder(Cliente.this);
                a.setCancelable(true);
                a.setTitle("Productos seleccionado");
                String msg = "ID : " + adm.getId() +"\n\n";
                msg += "NOMBRE : " +adm.getNombre();
                msg += "PRECIO : " +adm.getPrecio() +"\n\n";

                a.setMessage(msg);
                a.show();
            }
        });

    }//final metodo
}