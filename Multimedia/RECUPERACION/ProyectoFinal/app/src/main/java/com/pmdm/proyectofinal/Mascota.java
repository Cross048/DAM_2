package com.pmdm.proyectofinal;

public class Mascota {
    private String nombre;
    private String raza;
    private String nombreDueno;

    public Mascota(String nombre, String raza, String nombreDueno) {
        this.nombre = nombre;
        this.raza = raza;
        this.nombreDueno = nombreDueno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }
}
