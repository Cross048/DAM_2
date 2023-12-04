package bernalmendez_cristian_2dam_ad_examenud2;
import java.util.Objects;

public class Empleado {
    private String NIF;
    private String Nombre;
    private String Apellidos;
    private float Salario;

    public Empleado() {
    }

    public Empleado(String NIF, String Nombre, String Apellidos, float Salario) {
        this.NIF = NIF;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Salario = Salario;
    }

    public String getNIF() {
        return this.NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
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

    public float getSalario() {
        return this.Salario;
    }

    public void setSalario(float Salario) {
        this.Salario = Salario;
    }

    public Empleado NIF(String NIF) {
        setNIF(NIF);
        return this;
    }

    public Empleado Nombre(String Nombre) {
        setNombre(Nombre);
        return this;
    }

    public Empleado Apellidos(String Apellidos) {
        setApellidos(Apellidos);
        return this;
    }

    public Empleado Salario(float Salario) {
        setSalario(Salario);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Empleado)) {
            return false;
        }
        Empleado empleado = (Empleado) o;
        return Objects.equals(NIF, empleado.NIF) && Objects.equals(Nombre, empleado.Nombre) && Objects.equals(Apellidos, empleado.Apellidos) && Salario == empleado.Salario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(NIF, Nombre, Apellidos, Salario);
    }

    @Override
    public String toString() {
        return "NIF: " + getNIF() + " | " +
        "Nombre: " + getNombre() + " | " +
        "Apellidos: " + getApellidos() + " | " +
        "Salario: " + getSalario();
    }    
}
