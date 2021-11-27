package com.LUIS.ProyectoAndroid;

public class firebaspet {
    private String nombre;
    private String raza;
    private int edad;

    public firebaspet() {
        this.nombre = "";
        this.raza = "";
        this.edad = 0;
    }

    public firebaspet(String nombre, String raza, int edad) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
