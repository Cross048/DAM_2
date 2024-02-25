package bdxml;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Collection;

public class main {
    // Declaración del driver de XML:DB para eXist
    String driver = "org.exist.xmldb.DatabaseImpl";
    
    // Inicializar el driver
    Class cl = Class.forName(driver); // cargar el driver 
    Database database = (Database) cl.getDeclaredConstructor().newInstance();
    DatabaseManager.registerDatabase(database); // registro de la BD

    // Creamos una colección con un import del tipo:
    // import org.xmldb.api.base.Collection;
    // Servirá para conectanos a la base de datos eXist-DB
    // Indicamos los datos básicos de conexión (uri, user, password):
    String uri = "xmldb:exist://localhost:8080/exist/xmlrpc/db/ColeccionesXML/ColeccionPruebas ";
    String user = "admin"; String pass = "admin"; 
    Collection col = (Collection) DatabaseManager.getCollection(uri, user, pass);

}
