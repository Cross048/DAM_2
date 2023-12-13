package ejercicio4;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Cristian Bernal Méndez
 */
class PacienteSAXHandler extends DefaultHandler {
    private ArrayList<Paciente> listaPacientes = new ArrayList<>();
    private Paciente pacienteActual;
    private StringBuilder contenido = new StringBuilder();

    public ArrayList<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        contenido.setLength(0);

        if ("Paciente".equals(qName)) {
            pacienteActual = new Paciente();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        contenido.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("NIF".equals(qName)) {
            pacienteActual.setNIF(contenido.toString());
        } else if ("Nombre".equals(qName)) {
            pacienteActual.setNombre(contenido.toString());
        } else if ("Apellidos".equals(qName)) {
            pacienteActual.setApellidos(contenido.toString());
        } else if ("Direccion".equals(qName)) {
            pacienteActual.setDireccion(contenido.toString());
        } else if ("FechaUltimaVisita".equals(qName)) {
            pacienteActual.setFechaUltimaVisita(contenido.toString());
        } else if ("Alergia".equals(qName)) {
            pacienteActual.setAlergia(Boolean.parseBoolean(contenido.toString()));
        } else if ("Tipo".equals(qName)) {
            pacienteActual.setTipo(contenido.toString().charAt(0));
        } else if ("Paciente".equals(qName)) {
            listaPacientes.add(pacienteActual);
        }
    }
}