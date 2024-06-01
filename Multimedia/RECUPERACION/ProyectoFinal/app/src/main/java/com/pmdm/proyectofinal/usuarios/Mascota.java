package com.pmdm.proyectofinal.usuarios;

public class Mascota {
    private String nombre;
    private int raza; // Cambiado a int para que coincida con el esquema de tu tabla
    private String propietario; // Cambiado a coincidir con el esquema de tu tabla

    public Mascota(String nombre, int raza, String propietario) {
        this.nombre = nombre;
        this.raza = raza;
        this.propietario = propietario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRaza() {
        return raza;
    }

    public void setRaza(int raza) {
        this.raza = raza;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
}
