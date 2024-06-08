import locale
import sys

from PyQt6.QtGui import QIcon

import bills
import connection
import events
import products
from MainWindow import *
from windowsaux import *
import ctypes

# import sys
# from PyQt6.QtWidgets import QApplication
#
# app = QApplication(sys.argv)
# app.setStyle("Fusion")  # Establecer el estilo en Fusion


# Establecer la configuración regional en español
locale.setlocale(locale.LC_TIME, 'es_ES.UTF-8')
locale.setlocale(locale.LC_MONETARY, 'es_ES.UTF-8')

# Establecer icono en la barra de tareas
myappid = 'cristianbernal.recuperacion'
ctypes.windll.shell32.SetCurrentProcessExplicitAppUserModelID(myappid)

class Main(QtWidgets.QMainWindow):
    def __init__(self):
        super(Main, self).__init__()
        var.ui = Ui_MainWindow()
        var.calendar = Calendar()
        self.clients = clients.Clients()
        self.products = products.Products()
        self.bills = bills.Bills()
        var.ui.setupUi(self)

        # Conexión a la base de datos
        self.connection = connection.Connection()
        self.connection.conexion()

        ''' Eventos de Botones '''
        # Clientes: Abrir calendario con su botón asignado
        var.ui.btnCalendar.clicked.connect(events.Events.abrirCalendar)
        # Clientes: Añadir los datos del Cliente que se está creando en formulario
        var.ui.btnAlta.clicked.connect(connection.Connection.anyadirCliente)
        # Clientes: Modifica los datos del Cliente que se está editando en formulario
        var.ui.btnMod.clicked.connect(connection.Connection.modificarCliente)
        # Clientes: Borrar los datos del Cliente que se está eliminando en formulario
        var.ui.btnBaja.clicked.connect(connection.Connection.borrarCliente)

        # Productos: Añadir los datos del Producto que se está creando en formulario
        var.ui.btnAnyadir.clicked.connect(connection.Connection.anyadirProducto)
        # Productos: Modifica los datos del Producto que se está editando en formulario
        var.ui.btnMod_2.clicked.connect(connection.Connection.modificarProducto)
        # Productos: Borrar los datos del Producto que se está eliminando en formulario
        var.ui.btnEliminar.clicked.connect(connection.Connection.borrarProducto)

        # Facturas: Abrir calendario con su botón asignado
        var.ui.btnCalendar_3.clicked.connect(events.Events.abrirCalendar)
        # Facturas: Crea la factura del cliente
        var.ui.btnFacturar.clicked.connect(connection.Connection.crearFactura)
        # Facturas: Añadir los detalles de la factura
        var.ui.btnAnyadir_4.clicked.connect(connection.Connection.anyadirDetalle)
        # Facturas: Modifica los detalles de la factura
        var.ui.btnMod_4.clicked.connect(connection.Connection.modificarDetalle)

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

        # Facturas: Reajusta las dimensiones de la tabla Facturas1
        events.Events.resizeTableFacturas1()
        # Facturas: Cargar los datos de las facturas al abrir el programa y meterlas en la tabla Facturas1
        self.connection.selectFacturas1()
        # Facturas: Carga todos los datos de una factura en el formulario
        var.ui.tableFacturas1.clicked.connect(bills.Bills.cargarFactura1)

        # Facturas: Reajusta las dimensiones de la tabla Facturas2
        events.Events.resizeTableFacturas2()
        # Facturas: Cargar los datos de las facturas al abrir el programa y meterlas en la tabla Facturas2
        self.connection.selectFacturas2()
        # Facturas: Carga todos los datos de una factura en el formulario
        var.ui.tableFacturas2.clicked.connect(bills.Bills.cargarFactura2)

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
