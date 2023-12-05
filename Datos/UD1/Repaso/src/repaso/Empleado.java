package repaso;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Empleado {
    private String nombre;
    private int antiguedad;
    private static double salarioBase = 1000;
    private final static double incrementoAnual = 10;
    private final static double porcentaje = 10;

    public Empleado(String nombre, int antiguedad) {
        this.nombre = nombre;
        this.antiguedad = antiguedad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public static double getSalarioBase() {
        return salarioBase;
    }

    public static void setSalarioBase(double salarioBase) {
        Empleado.salarioBase = salarioBase;
    }
    
    private static void aumentarSalario(){
        salarioBase = salarioBase * (100 + porcentaje) / 100;
    }
    
    public double salario() {
        return salarioBase + incrementoAnual + antiguedad;
    }
}
