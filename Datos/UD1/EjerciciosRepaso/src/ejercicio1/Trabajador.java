package ejercicio1;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Trabajador {
    private String nombre;
    private int edad;
    private int categoria = -1;
    private int antiguedad;

    public static final int CAT_EMPLEADO = 0;
    public static final int CAT_ENCARGADO = 1;
    public static final int CAT_DIRECTIVO = 2;
    public static final int ANT_NOVATO = 0;
    public static final int ANT_MADURO = 1;
    public static final int ANT_EXPERTO = 2;

    public Trabajador(String nombre, int edad, int categoria, int antiguedad) {
        this.nombre = nombre;
        this.edad = edad;
        if (categoria != CAT_EMPLEADO && categoria != CAT_ENCARGADO && categoria != CAT_DIRECTIVO) {
            throw new IllegalArgumentException("Categoria no valida.");
        } else {
            this.categoria = categoria;
        }
        if (antiguedad != ANT_NOVATO && antiguedad != ANT_MADURO && antiguedad != ANT_EXPERTO) {
            throw new IllegalArgumentException("Antiguedad no valida.");
        } else {
            this.antiguedad = antiguedad;
        }
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public String getCategoria() {
        String returnCategoria = null;

        switch (this.categoria) {
            case CAT_EMPLEADO:
                returnCategoria = "Empleado";
                break;
            case CAT_ENCARGADO:
                returnCategoria = "Encargado";
                break;
            case CAT_DIRECTIVO:
                returnCategoria = "Directivo";
                break;
            default:
                System.out.println("Ha habido un problema.");
                break;
        }

        return returnCategoria;
    }

    public String getAntiguedad() {
        String returnAntiguedad = null;

        switch (this.antiguedad) {
            case ANT_NOVATO:
                returnAntiguedad = "Novato";
                break;
            case ANT_MADURO:
                returnAntiguedad = "Maduro";
                break;
            case ANT_EXPERTO:
                returnAntiguedad = "Experto";
                break;
            default:
                System.out.println("Ha habido un problema.");
                break;
        }

        return returnAntiguedad;
    }

    public void setNombre(String nombre) {
        try {
            this.nombre = nombre;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El nombre no esta permitido.");
        }
    }

    public void setEdad(int edad) {
        try {
            this.edad = edad;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("La edad no esta permitida.");
        }
    }

    public void setCategoria(int categoria) {
        if (categoria != CAT_EMPLEADO && categoria != CAT_ENCARGADO && categoria != CAT_DIRECTIVO) {
            throw new IllegalArgumentException("La categoria no esta permitida.");
        } else {
            this.categoria = categoria;
        }
    }

    public void setAntiguedad(int antiguedad) {
        if (antiguedad != ANT_NOVATO && antiguedad != ANT_MADURO && antiguedad != ANT_EXPERTO) {
            throw new IllegalArgumentException("La antiguedad no esta permitida.");
        } else {
            this.antiguedad = antiguedad;
        }
    }

    public double calcularSueldo() {
        double sueldoBase = 607;
        double sueldo = 0;
        
        switch (this.categoria) {
            case CAT_EMPLEADO:
                sueldo = sueldoBase + (sueldoBase * 0.15);
                break;
            case CAT_ENCARGADO:
                sueldo = sueldoBase + (sueldoBase * 0.35);
                break;
            case CAT_DIRECTIVO:
                sueldo = sueldoBase + (sueldoBase * 0.60);
                break;
            default:
                System.out.println("Ha habido un problema.");
                break;
        }
        switch (this.antiguedad) {
            case ANT_NOVATO:
                sueldo += 150;
                break;
            case ANT_MADURO:
                sueldo += 300;
                break;
            case ANT_EXPERTO:
                sueldo += 600;
                break;
            default:
                System.out.println("Ha habido un problema.");
                break;
        }

        return sueldo;
    }
}
