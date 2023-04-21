package com.example.crud4.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import com.example.crud4.entidades.Contactos;

import java.util.ArrayList;

public class DbContactos extends DbHelper {
    Context context;

    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarContacto(String nombre, String cinta, String categoria, String escuela, String compite){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("cinta", cinta);
            values.put("categoria", categoria);
            values.put("escuela", escuela);
            values.put("compite", compite);

            id = db.insert(TABLE_CONTACTOS, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList <Contactos> mostrarContactos(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList <Contactos> listaContactos = new ArrayList<>();
        Contactos contacto = null;
        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS, null);
        if(cursorContactos.moveToFirst()){
            do{
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setCinta(cursorContactos.getString(2));
                contacto.setCategoria(cursorContactos.getString(3));
                contacto.setEscuela(cursorContactos.getString(4));
                contacto.setCompite(cursorContactos.getString(5));

                listaContactos.add(contacto);
            }while(cursorContactos.moveToNext());
        }
        cursorContactos.close();

        return listaContactos;
    }
}
