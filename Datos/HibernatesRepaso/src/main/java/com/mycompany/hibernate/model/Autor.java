package com.mycompany.hibernate.model;

import com.mycompany.hibernate.model.Libro;
import com.mycompany.hibernate.model.Telefono;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Table(name = "autores",
        uniqueConstraints={@UniqueConstraint(columnNames={"DniAutor"})})
public class Autor implements Serializable {
    @Id
    @Column(name = "DniAutor", nullable = false, unique = true) 
    private String dni;
    
    @Column(name = "Nombre", nullable = true) 
    private String nombre;
    
    @Column(name = "Nacionalidad", nullable = true) 
    private String nacionalidad;

    @OneToMany(mappedBy = "autor")
    private List<Libro> libros = new ArrayList<>();
    
    @OneToOne(mappedBy = "autor", cascade = CascadeType.ALL)
    private Telefono telefono;
    
    public Autor() {
    }

    public Autor(String dni, String nombre, String nacionalidad) {
        this.dni = dni;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Autores{" + "dni=" + dni + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + '}';
    }
}