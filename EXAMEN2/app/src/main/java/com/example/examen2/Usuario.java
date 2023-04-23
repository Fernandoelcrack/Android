package com.example.examen2;

import android.widget.EditText;

public class Usuario {

    public int tipo;
    public String correo;
    public String password;
    public String password2;

    public Usuario(int tipo, EditText correo, EditText password, EditText password2) {
    }

    public Usuario(int tipo, String correo, String password, String password2) {
        this.tipo = tipo;
        this.correo = correo;
        this.password = password;
        this.password2 = password2;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "tipo=" + tipo +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                '}';
    }
}
