package UD1;

import java.util.Objects;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Medicamento {
    private String Codigo;
    private String Nombre;
    private String Lote;
    private String Caducidad; // No me sale con Date :(
    private float Precio;
    private float IVA;
    private String Laboratorio;

    public Medicamento() {
    }

    public Medicamento(String Codigo, String Nombre, String Lote, String Caducidad, float Precio, float IVA, String Laboratorio) {
        this.Codigo = Codigo;
        this.Nombre = Nombre;
        this.Lote = Lote;
        this.Caducidad = Caducidad;
        this.Precio = Precio;
        this.IVA = IVA;
        this.Laboratorio = Laboratorio;
    }

    public String getCodigo() {
        return this.Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getLote() {
        return this.Lote;
    }

    public void setLote(String Lote) {
        this.Lote = Lote;
    }

    public String getCaducidad() {
        return this.Caducidad;
    }

    public void setCaducidad(String Caducidad) {
        this.Caducidad = Caducidad;
    }

    public float getPrecio() {
        return this.Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }

    public float getIVA() {
        return this.IVA;
    }

    public void setIVA(float IVA) {
        this.IVA = IVA;
    }

    public String getLaboratorio() {
        return this.Laboratorio;
    }

    public void setLaboratorio(String Laboratorio) {
        this.Laboratorio = Laboratorio;
    }

    public Medicamento Codigo(String Codigo) {
        setCodigo(Codigo);
        return this;
    }

    public Medicamento Nombre(String Nombre) {
        setNombre(Nombre);
        return this;
    }

    public Medicamento Lote(String Lote) {
        setLote(Lote);
        return this;
    }

    public Medicamento Caducidad(String Caducidad) {
        setCaducidad(Caducidad);
        return this;
    }

    public Medicamento Precio(float Precio) {
        setPrecio(Precio);
        return this;
    }

    public Medicamento IVA(float IVA) {
        setIVA(IVA);
        return this;
    }

    public Medicamento Laboratorio(String Laboratorio) {
        setLaboratorio(Laboratorio);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Medicamento)) {
            return false;
        }
        Medicamento medicamento = (Medicamento) o;
        return Objects.equals(Codigo, medicamento.Codigo) && Objects.equals(Nombre, medicamento.Nombre) && Objects.equals(Lote, medicamento.Lote) && Objects.equals(Caducidad, medicamento.Caducidad) && Precio == medicamento.Precio && IVA == medicamento.IVA && Objects.equals(Laboratorio, medicamento.Laboratorio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Codigo, Nombre, Lote, Caducidad, Precio, IVA, Laboratorio);
    }

    @Override
    public String toString() {
        float Importe = this.Precio + (this.Precio * this.IVA * (float) 0.01);
        return "{" +
            " Codigo='" + getCodigo() + "'" +
            ", Nombre='" + getNombre() + "'" +
            ", Lote='" + getLote() + "'" +
            ", Caducidad='" + getCaducidad() + "'" +
            ", Precio='" + getPrecio() + "'" +
            ", IVA='" + getIVA() + "'" +
            ", Laboratorio='" + getLaboratorio() + "'" +
            ", Importe='" + Importe + "'" +
            "}";
    }    
}
