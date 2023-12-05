package ejercicio3;

import java.util.Objects;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Paciente {
    private String NIF;
    private String Nombre;
    private String Apellidos;
    private String Direccion;
    private String FechaUltimaVisita; // Uso String al final porque daba problemas de formato :(
    private Boolean Alergia;
    private char Tipo;
    private static final boolean SiAlergia = true;
    private static final boolean NoAlergia = false;
    private static final char Privado = 'P';
    private static final char SeguridadSocial = 'S';

    public Paciente(String NIF, String Nombre, String Apellidos,String Direccion, String FechaUltimaVisita, Boolean Alergia, char Tipo) {
        if (NIF.length() == 9) {
            this.NIF = NIF;
            // No se me ocurre cómo detectar la letra :(
        }
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Direccion = Direccion;
        this.FechaUltimaVisita = FechaUltimaVisita; 
        if (Alergia == SiAlergia) {
            this.Alergia = true;
        } else if (Alergia == NoAlergia) {
            this.Alergia = false;
        }
        if (Tipo == Privado) {
            this.Tipo = Tipo;
        } else if (Tipo == SeguridadSocial) {
            this.Tipo = Tipo;
        }
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getFechaUltimaVisita() {
        return FechaUltimaVisita;
    }

    public void setFechaUltimaVisita(String FechaUltimaVisita) {
        this.FechaUltimaVisita = FechaUltimaVisita;
    }

    public Boolean getAlergia() {
        return Alergia;
    }

    public void setAlergia(Boolean Alergia) {
        this.Alergia = Alergia;
    }

    public char getTipo() {
        return Tipo;
    }

    public void setTipo(char Tipo) {
        this.Tipo = Tipo;
    }

    @Override
    public String toString() {
        return NIF + "," + Nombre + "," + Apellidos + "," + Direccion + "," + FechaUltimaVisita + "," + Alergia + "," + Tipo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paciente other = (Paciente) obj;
        return Objects.equals(this.NIF, other.NIF);
    }
}
