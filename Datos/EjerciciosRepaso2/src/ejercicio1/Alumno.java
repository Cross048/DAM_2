package ejercicio1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Alumno {
    private String nombre;
    private String localidad;
    private ArrayList<Modulo> modulos;
    
    // Constructor
    public Alumno(String nombre, String localidad) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.modulos = new ArrayList<Modulo>();
    }

    public Alumno(String nombre, String localidad, ArrayList<Modulo> modulos) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.modulos = modulos;
    }
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    
    // Añade un módulo a la lista
    public void anyadirModulo(Modulo a) {
        this.modulos.add(a);
    }
    
    // Devuelve un array con todos los módulos listados
    public String[] getNombreModulos() {
        String[] modulosAlumno = (String[]) this.modulos.toArray();
        
        return modulosAlumno;
    }
    
    // Devuelve el total de horas que tiene el alumno
    // (suma de horas de todos los módulos).
    public double getNumeroHoras() {
        double totalHoras = 0;
        Iterator<Modulo> iterator = modulos.iterator();
        
        while (iterator.hasNext()) {
            Modulo modulo = iterator.next();
            totalHoras += modulo.getHoras();
        }
        
        return totalHoras;
    }
}
