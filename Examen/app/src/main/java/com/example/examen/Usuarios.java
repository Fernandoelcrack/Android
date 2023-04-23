package com.example.examen;

public class Usuarios {

    private int tipo;
    private String username;
    private String password;
    private String password2;

    public Usuarios() {
    }

    public Usuarios(int tipo, String username, String password, String password2) {
        this.tipo = tipo;
        this.username = username;
        this.password = password;
        this.password2 = password2;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
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
        return "Usuarios{" +
                "tipo=" + tipo +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                '}';
    }
}
