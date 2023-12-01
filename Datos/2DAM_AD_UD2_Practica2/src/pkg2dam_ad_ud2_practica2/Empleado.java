package pkg2dam_ad_ud2_practica2;

import java.util.Date;
import java.util.Objects;

public class Empleado {
    int NSS;
    String Nombre;
    String Apel1;               
    String Apel2;
    char Sexo;
    String Direccion;
    Date Nacimiento;
    int Salario;
    int Deparmanteo;
    int Supervisor;
    String NIF;

    public Empleado() {
    }

    public Empleado(int NSS, String Nombre, String Apel1, String Apel2, char Sexo, String Direccion, Date Nacimiento, int Salario, int Deparmanteo, int Supervisor, String NIF) {
        this.NSS = NSS;
        this.Nombre = Nombre;
        this.Apel1 = Apel1;
        this.Apel2 = Apel2;
        this.Sexo = Sexo;
        this.Direccion = Direccion;
        this.Nacimiento = Nacimiento;
        this.Salario = Salario;
        this.Deparmanteo = Deparmanteo;
        this.Supervisor = Supervisor;
        this.NIF = NIF;
    }

    public int getNSS() {
        return this.NSS;
    }

    public void setNSS(int NSS) {
        this.NSS = NSS;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApel1() {
        return this.Apel1;
    }

    public void setApel1(String Apel1) {
        this.Apel1 = Apel1;
    }

    public String getApel2() {
        return this.Apel2;
    }

    public void setApel2(String Apel2) {
        this.Apel2 = Apel2;
    }

    public char getSexo() {
        return this.Sexo;
    }

    public void setSexo(char Sexo) {
        this.Sexo = Sexo;
    }

    public String getDireccion() {
        return this.Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public Date getNacimiento() {
        return this.Nacimiento;
    }

    public void setNacimiento(Date Nacimiento) {
        this.Nacimiento = Nacimiento;
    }

    public int getSalario() {
        return this.Salario;
    }

    public void setSalario(int Salario) {
        this.Salario = Salario;
    }

    public int getDeparmanteo() {
        return this.Deparmanteo;
    }

    public void setDeparmanteo(int Deparmanteo) {
        this.Deparmanteo = Deparmanteo;
    }

    public int getSupervisor() {
        return this.Supervisor;
    }

    public void setSupervisor(int Supervisor) {
        this.Supervisor = Supervisor;
    }

    public String getNIF() {
        return this.NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public Empleado NSS(int NSS) {
        setNSS(NSS);
        return this;
    }

    public Empleado Nombre(String Nombre) {
        setNombre(Nombre);
        return this;
    }

    public Empleado Apel1(String Apel1) {
        setApel1(Apel1);
        return this;
    }

    public Empleado Apel2(String Apel2) {
        setApel2(Apel2);
        return this;
    }

    public Empleado Sexo(char Sexo) {
        setSexo(Sexo);
        return this;
    }

    public Empleado Direccion(String Direccion) {
        setDireccion(Direccion);
        return this;
    }

    public Empleado Nacimiento(Date Nacimiento) {
        setNacimiento(Nacimiento);
        return this;
    }

    public Empleado Salario(int Salario) {
        setSalario(Salario);
        return this;
    }

    public Empleado Deparmanteo(int Deparmanteo) {
        setDeparmanteo(Deparmanteo);
        return this;
    }

    public Empleado Supervisor(int Supervisor) {
        setSupervisor(Supervisor);
        return this;
    }

    public Empleado NIF(String NIF) {
        setNIF(NIF);
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
        return NSS == empleado.NSS && Objects.equals(Nombre, empleado.Nombre) && Objects.equals(Apel1, empleado.Apel1) && Objects.equals(Apel2, empleado.Apel2) && Sexo == empleado.Sexo && Objects.equals(Direccion, empleado.Direccion) && Objects.equals(Nacimiento, empleado.Nacimiento) && Salario == empleado.Salario && Deparmanteo == empleado.Deparmanteo && Supervisor == empleado.Supervisor && Objects.equals(NIF, empleado.NIF);
    }

    @Override
    public String toString() {
        return "NSS: " + getNSS() + " | " +
            "Nombre: " + getNombre() + " | " +
            "Apellido 1: " + getApel1() + " | " +
            "Apellido 2: " + getApel2() + " | " +
            "Sexo: " + getSexo() +
            "\nDireccion: " + getDireccion() + " | " +
            "Nacimiento: " + getNacimiento() + " | " +
            "Salario: " + getSalario() + " | " +
            "Deparmanteo: " + getDeparmanteo() + " | " +
            "Supervisor: " + getSupervisor() + " | " +
            "NIF: " + getNIF();
    }
    
}
