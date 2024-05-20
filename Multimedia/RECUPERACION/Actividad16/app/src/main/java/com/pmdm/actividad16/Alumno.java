package com.pmdm.actividad16;

public class Alumno {
    private String nombre;
    private String curso;
    private String ciclo;

    public Alumno(String nombre, String curso, String ciclo) {
        this.nombre = nombre;
        this.curso = curso;
        this.ciclo = ciclo;
    }

    public Alumno(String nombre, String curso) {
        this.nombre = nombre;
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public boolean tieneCiclo() {
        return ciclo != null && !ciclo.isEmpty();
    }

    @Override
    public String toString() {
        if (tieneCiclo()) {
            return "Alumno: " + nombre + ", Curso: " + curso + ", Ciclo: " + ciclo;
        } else {
            return "Alumno: " + nombre + ", Curso: " + curso;
        }
    }
}
