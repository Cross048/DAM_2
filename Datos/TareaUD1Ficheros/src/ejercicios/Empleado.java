package ejercicios;

public class Empleado {
    String DNI;
    String Nombre;
    String Apellidos;
    double Salario;

    public Empleado(String DNI, String Nombre, String Apellidos, double Salario) {
        this.DNI = DNI;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Salario = Salario;
    }

    public String getDNI() {
        return this.DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return this.Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public double getSalario() {
        return this.Salario;
    }

    public void setSalario(double Salario) {
        this.Salario = Salario;
    }

    public String toFile() {
        return getDNI() + "," + getNombre() + "," + getApellidos() + ","+ getSalario();
    }

    @Override
    public String toString() {
        return "{" + " DNI: " + getDNI() + " | "
        + "Nombre: " + getNombre() + " | "
        + "Apellidos: " + getApellidos() + " | "
        + "Salario: " + getSalario() + " }";
    }
}
