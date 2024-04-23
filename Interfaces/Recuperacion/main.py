import locale
import sys, var, events, connection

from MainWindow import *

# Establecer la configuración regional en español
locale.setlocale(locale.LC_TIME, 'es_ES.UTF-8')
locale.setlocale(locale.LC_MONETARY, 'es_ES.UTF-8')


class Main(QtWidgets.QMainWindow):
    def __init__(self):
        super(Main, self).__init__()
        var.ui = Ui_MainWindow()
        var.ui.setupUi(self)

        # Conexión a la base de datos
        self.connection = connection.Connection()
        self.connection.conexion()

        ''' Eventos de Botones '''

        ''' Eventos del Menubar '''
        # Confirmar salida
        var.ui.actionSalir.triggered.connect(events.Events.salir)

        ''' Eventos Cajas de Texto '''

        ''' Eventos del Toolbar '''

        ''' Eventos de Tablas '''
        # Reajusta las dimensiones de la tabla Clientes
        events.Events.resizeTableClientes()
        # Cargar los datos de los clientes al abrir el programa y meterlos en la tabla Clientes
        self.connection.selectClientes()

        ''' Eventos Combobox '''

    def closeEvent(self, event):
        # Ventana de emergencia al intentar salir del programa
        mbox = QtWidgets.QMessageBox.information(self, "Salir", "¿Estás seguro de que quieres salir?",
                                                 QtWidgets.QMessageBox.StandardButton.Yes |
                                                 QtWidgets.QMessageBox.StandardButton.No)
        if mbox == QtWidgets.QMessageBox.StandardButton.Yes:
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
