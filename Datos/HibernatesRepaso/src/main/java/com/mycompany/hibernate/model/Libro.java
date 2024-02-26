package com.mycompany.hibernate.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "libros",
        uniqueConstraints={@UniqueConstraint(columnNames={"IdLibro"})})
public class Libro implements Serializable {
    @Id
    @Column(name = "IdLibro", nullable = false, unique = true)
    private int id;
    
    @Column(name = "Titulo", nullable = true)
    private String titulo;
    
    @Column(name = "Precio", nullable = true)
    private double precio;
    
    @ManyToOne
    @JoinColumn(name = "DniAutor")
    private Autor autor;

    public Libro() {
    }

    public Libro(int id, String titulo, double precio) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", titulo=" + titulo + ", precio=" + precio + '}';
    }
}
