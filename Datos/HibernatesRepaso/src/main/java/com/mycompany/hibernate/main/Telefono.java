package com.mycompany.hibernate.main;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "telefonos",
        uniqueConstraints={@UniqueConstraint(columnNames={"DniAutor"})})
public class Telefono implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DniAutor", nullable = false, unique = true)
    private String dni;
    
    @Column(name = "NumeroTf", nullable = false, unique = true)
    private String numero;

    @OneToOne
    @JoinColumn(name = "DniAutor")
    private Autor autor;
    
    public Telefono() {
    }

    public Telefono(String dni, String numero) {
        this.dni = dni;
        this.numero = numero;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Telefono{" + "dni=" + dni + ", numero=" + numero + ", autor=" + autor + '}';
    }
}
