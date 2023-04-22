package com.example.examen2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Principal extends AppCompatActivity {

    Button btnadmin, btncliente, btnver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnadmin = findViewById(R.id.btnadmin);
        btncliente = findViewById(R.id.btncliente);
        btnver = findViewById(R.id.btnver);

    }
}