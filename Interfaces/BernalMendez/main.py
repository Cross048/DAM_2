import sys, var, eventos, drivers, conexion
from datetime import datetime
# Importa las ventanas
from MainWindow import *
from windowsaux import *
from Calendar import *
from dlgSalir import *
# Define la máquina en el idioma local (Español)
import locale
locale.setlocale(locale.LC_TIME, 'es_ES.UTF-8')

# Instalar PyQt6 y setuptools
# pip install pyqt6
# pip install setuptools

class Main(QtWidgets.QMainWindow):
    def __init__(self):
        super(Main, self).__init__()
        var.ui = Ui_mainWindow()
        var.ui.setupUi(self)          # Encargado de la interfaz
        var.calendar = Calendar()     # Ventana del Calendario
        var.dlgacerca = DlgAcerca()   # Ventana del Acerca De
        var.dlgsalir = DlgSalir()     # Ventana al salir
        self.drivers = Drivers()
        conexion.Conexion.conexion()

    def closeEvent(self, event):
        mbox = QtWidgets.QMessageBox.information(self, 'Salir', '¿Estás seguro de que quieres salir?',
                                                 QtWidgets.QMessageBox.StandardButton.Yes | QtWidgets.QMessageBox.StandardButton.No)
        if mbox == QtWidgets.QMessageBox.StandardButton.Yes:
            app.quit()
        else:
            event.ignore()

        ''' zona de eventos de botones '''
        var.ui.btnCalendar.clicked.connect(eventos.Eventos.abrirCalendar)   # Abrir calendario

        ''' zona de eventos de botones '''
        var.ui.actionSalir.triggered.connect(eventos.Eventos.mostrarSalir)   # Cerrar programa

        ''' eventos del toolbar '''
        var.ui.actionbarSalir.triggered.connect(eventos.Eventos.mostrarsalir)           # Cerrar programa
        var.ui.actionlimpiaPaneldriver.triggered.connect(drivers.Drivers.limpiapanel)   # Limpiar panel

        ''' eventos comboBox'''
        var.ui.comboBoxProvincia.currentIndexChanged.connect(conexion.Conexion.selMuni)

        ''' Formatear la fecha según el formato deseadofecha_actual.strftime() statusbar '''
        fecha = datetime.now().strftime("%A - " + "%d/%m/%Y")
        self.labelstatus = QtWidgets.QLabel(fecha, self)
        self.labelstatus.setAlignment(QtCore.Qt.AlignmentFlag.AlignHCenter)
        var.ui.statusbar.addPermanentWidget(self.labelstatus, 1)
        self.labelstatusversion = QtWidgets.QLabel("Version: " + var.version, self)
        self.labelstatusversion.setAlignment(QtCore.Qt.AlignmentFlag.AlignRight)
        var.ui.statusbar.addPermanentWidget(self.labelstatusversion, 2)

        ''' ejecución de diferentes funciones al lanzar la aplicación '''
        eventos.Eventos.cargastatusbar()

class Calendar(QtWidgets.QDialog):
    def __init__(self):
        super(Calendar, self).__init__()
        var.calendar = Ui_dlgCalendar()
        var.calendar.setupUi(self)
        dia = datetime.now().day
        mes = datetime.now().month
        ano = datetime.now().year

        ''' zona de eventos de botones '''
        var.ui.btnCalendar.clicked.connect(eventos.Eventos.abrirCalendar)   # Abrir Calendario

        ''' zona de eventos de cajas de texto '''
        var.ui.lineDNI.editingFinished.connect(drivers.Drivers.validarDNI)   # Validar DNI

if __name__ == '__main__':
    app = QtWidgets.QApplication([])
    window = Main()
    window.show()
    sys.exit(app.exec())
