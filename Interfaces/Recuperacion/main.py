import locale
import sys

import connection
import events
import products
from MainWindow import *
from windowsaux import *

# Establecer la configuración regional en español
locale.setlocale(locale.LC_TIME, 'es_ES.UTF-8')
locale.setlocale(locale.LC_MONETARY, 'es_ES.UTF-8')


class Main(QtWidgets.QMainWindow):
    def __init__(self):
        super(Main, self).__init__()
        var.ui = Ui_MainWindow()
        var.calendar = Calendar()
        self.clients = clients.Clients()
        self.products = products.Products()
        var.ui.setupUi(self)

        # Conexión a la base de datos
        self.connection = connection.Connection()
        self.connection.conexion()

        ''' Eventos de Botones '''
        # Clientes: Abrir calendario con su botón asignado
        var.ui.btnCalendar.clicked.connect(events.Events.abrirCalendar)
        # Clientes: Modifica los datos del Cliente que se está editando en formulario
        var.ui.btnMod.clicked.connect(connection.Connection.modificarCliente)
        # Productos: Modifica los datos del Producto que se está editando en formulario
        var.ui.btnMod_2.clicked.connect(connection.Connection.modificarProducto)

        ''' Eventos del Menubar '''
        # Confirmar salida
        var.ui.actionSalir.triggered.connect(events.Events.salir)

        ''' Eventos Cajas de Texto '''

        ''' Eventos del Toolbar '''

        ''' Eventos de Tablas '''
        # Clientes: Reajusta las dimensiones de la tabla Clientes
        events.Events.resizeTableClientes()
        # Clientes: Cargar los datos de los clientes al abrir el programa y meterlos en la tabla Clientes
        self.connection.selectClientes()
        # Clientes: Al cambiar el estado de Histórico, actualiza la tabla
        var.ui.chkHistorico.clicked.connect(self.connection.selectClientes)
        # Clientes: Carga todos los datos de un cliente en el formulario
        var.ui.tableClientes.clicked.connect(clients.Clients.cargarCliente)
        # Productos: Reajusta las dimensiones de la tabla Productos
        events.Events.resizeTableProductos()
        # Productos: Cargar los datos de los clientes al abrir el programa y meterlos en la tabla Productos
        self.connection.selectProductos()
        # Productos: Carga todos los datos de un producto en el formulario
        var.ui.tableProductos.clicked.connect(products.Products.cargarProducto)

    def closeEvent(self, event):
        # Ventana de emergencia al intentar salir del programa
        mbox = QtWidgets.QMessageBox.information(self, "Salir", "¿Estás seguro de que quieres salir?",
                                                 QtWidgets.QMessageBox.StandardButton.Yes |
                                                 QtWidgets.QMessageBox.StandardButton.No)
        if mbox == QtWidgets.QMessageBox.StandardButton.Yes:
            print("Programa cerrado")
            event.accept()
        else:
            event.ignore()


if __name__ == '__main__':
    try:
        app = QtWidgets.QApplication([])
        window = Main()
        window.showMaximized()
        sys.exit(app.exec())
    except Exception as error:
        print(error)
