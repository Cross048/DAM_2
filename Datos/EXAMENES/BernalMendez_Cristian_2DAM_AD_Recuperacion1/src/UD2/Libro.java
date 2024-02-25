package UD2;
import java.util.Objects;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Libro {
    private int IdLibro;
    private String Nombre;
    private String Autor;
    private String Editorial;
    private String ISBN;
    private float Precio;

    public Libro() {
    }

    public Libro(int IdLibro, String Nombre, String Autor, String Editorial, String ISBN, float Precio) {
        this.IdLibro = IdLibro;
        this.Nombre = Nombre;
        this.Autor = Autor;
        this.Editorial = Editorial;
        this.ISBN = ISBN;
        this.Precio = Precio;
    }

    public int getIdLibro() {
        return this.IdLibro;
    }

    public void setIdLibro(int IdLibro) {
        this.IdLibro = IdLibro;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getAutor() {
        return this.Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public String getEditorial() {
        return this.Editorial;
    }

    public void setEditorial(String Editorial) {
        this.Editorial = Editorial;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public float getPrecio() {
        return this.Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }

    public Libro IdLibro(int IdLibro) {
        setIdLibro(IdLibro);
        return this;
    }

    public Libro Nombre(String Nombre) {
        setNombre(Nombre);
        return this;
    }

    public Libro Autor(String Autor) {
        setAutor(Autor);
        return this;
    }

    public Libro Editorial(String Editorial) {
        setEditorial(Editorial);
        return this;
    }

    public Libro ISBN(String ISBN) {
        setISBN(ISBN);
        return this;
    }

    public Libro Precio(float Precio) {
        setPrecio(Precio);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Libro)) {
            return false;
        }
        Libro libro = (Libro) o;
        return IdLibro == libro.IdLibro && Objects.equals(Nombre, libro.Nombre) && Objects.equals(Autor, libro.Autor) && Objects.equals(Editorial, libro.Editorial) && Objects.equals(ISBN, libro.ISBN) && Precio == libro.Precio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(IdLibro, Nombre, Autor, Editorial, ISBN, Precio);
    }

    @Override
    public String toString() {
        return "{" +
            " IdLibro='" + getIdLibro() + "'" +
            ", Nombre='" + getNombre() + "'" +
            ", Autor='" + getAutor() + "'" +
            ", Editorial='" + getEditorial() + "'" +
            ", ISBN='" + getISBN() + "'" +
            ", Precio='" + getPrecio() + "'" +
            "}";
    }    
}
