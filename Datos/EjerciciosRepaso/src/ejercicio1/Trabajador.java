package ejercicio1;

import java.lang.IllegalArgumentException;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Trabajador {
    private String nombre;
    private int edad;
    private int categoria;
    private int antiguedad;

    public static final int CAT_EMPLEADO = 0;
    public static final int CAT_ENCARGADO = 1;
    public static final int CAT_DIRECTIVO = 2;
    public static final int ANT_NOVATO = 0;
    public static final int ANT_MADURO = 1;
    public static final int ANT_EXPERTO = 2;

    public Trabajador(String nombre, int edad, int categoria, int antiguedad) {
        try {
            this.nombre = nombre;
            this.edad = edad;
            if (categoria != CAT_EMPLEADO && categoria != CAT_ENCARGADO && categoria != CAT_DIRECTIVO) {
                throw new IllegalArgumentException();
            } else {
                this.categoria = categoria;
            }
            if (antiguedad != ANT_NOVATO && antiguedad != ANT_MADURO && antiguedad != ANT_EXPERTO) {
                throw new IllegalArgumentException();
            } else {
                this.antiguedad = antiguedad;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("El valor no esta permitida.");
        }
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public int getCategoria() {
        return this.categoria;
    }

    public int getAntiguedad() {
        return this.antiguedad;
    }

    public void setNombre(String nombre) {
        try {
            this.nombre = nombre;
        } catch (IllegalArgumentException e) {
            System.out.println("El valor no esta permitida.");
        }
    }

    public void setEdad(int edad) {
        try {
            this.edad = edad;
        } catch (IllegalArgumentException e) {
            System.out.println("El valor no esta permitida.");
        }
    }

    public void setCategoria(int categoria) {
        try {
            this.categoria = categoria;
        } catch (IllegalArgumentException e) {
            System.out.println("El valor no esta permitida.");
        }
    }

    public void setAntiguedad(int antiguedad) {
        try {
            this.antiguedad = antiguedad;
        } catch (IllegalArgumentException e) {
            System.out.println("El valor no esta permitida.");
        }
    }

    public double calcularSueldo() {
        double sueldo = 607;
        // Terminar código V V V
        switch (nombre) {
            case value:
                
                break;
        
            default:
                break;
        }
        switch (nombre) {
            case value:
                
                break;
        
            default:
                break;
        }
        // Terminar código en casa ^ ^ ^ 
        return sueldo;
    }
}
