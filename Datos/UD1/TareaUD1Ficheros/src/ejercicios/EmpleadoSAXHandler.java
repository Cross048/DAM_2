package ejercicios;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EmpleadoSAXHandler extends DefaultHandler {
    private StringBuilder data;
    private boolean inEmpleado = false;
    private String DNI;
    private String nombre;
    private String apellidos;
    private double salario;
    private ArrayList<Empleado> empleadosSAX = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("empleado")) {
            inEmpleado = true;
            DNI = "";
            nombre = "";
            apellidos = "";
            salario = 0.0;
        }
        data = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (inEmpleado) {
            if (qName.equalsIgnoreCase("DNI")) {
                DNI = data.toString();
            } else if (qName.equalsIgnoreCase("Nombre")) {
                nombre = data.toString();
            } else if (qName.equalsIgnoreCase("Apellidos")) {
                apellidos = data.toString();
            } else if (qName.equalsIgnoreCase("Salario")) {
                salario = Double.parseDouble(data.toString());
            } else if (qName.equalsIgnoreCase("empleado")) {
                Empleado empleado = new Empleado(DNI, nombre, apellidos, salario);
                empleadosSAX.add(empleado);
                inEmpleado = false;
            }
        }
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleadosSAX;
    }
}
