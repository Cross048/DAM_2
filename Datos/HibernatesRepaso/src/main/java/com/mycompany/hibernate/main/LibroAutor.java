package com.mycompany.hibernate.main;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "libros_autores")
public class LibroAutor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLibroAutor", nullable = false, unique = true)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "IdLibro")
    private Libro libro;
    
    @ManyToOne
    @JoinColumn(name = "DniAutor")
    private Autor autor;

    public LibroAutor() {
    }

    public LibroAutor(int id, Libro libro, Autor autor) {
        this.id = id;
        this.libro = libro;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "LibroAutor{" + "id=" + id + ", libro=" + libro + ", autor=" + autor + '}';
    }
}
