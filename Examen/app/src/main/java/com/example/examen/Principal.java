package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.examen.R;

public class Principal extends AppCompatActivity {
    Button btnadmin, btncliente, btnver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnadmin = findViewById(R.id.btnadmin);
        btncliente = findViewById(R.id.btncliente);
        btnver = findViewById(R.id.btnver);

        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, Registro.class);
                startActivity(intent);

            }
        });

        btncliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, Registro.class);
                startActivity(intent);
            }
        });

        btnver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, Empresa.class);
                startActivity(intent);
            }
        });
    }


}