from MainWindow_ui import *
import sys, var, eventos, drivers
from Calendar_ui import *
from datetime import datetime

# Instalar PyQt6 y setuptools
# pip install pyqt6
# pip install setuptools

class Calendar(QtWidgets.QDialog):
    def __init__(self):
        super(Calendar, self).__init__()
        var.calendar = Ui_dlgCalendar()
        var.calendar.setupUi(self)
        dia = datetime.now().day
        mes = datetime.now().month
        ano = datetime.now().year
        '''
        zona de eventos de botones
        '''
        var.ui.btnCalendar.clicked.connect(eventos.Eventos.abrirCalendar)    # Abrir Calendario
        '''
        zona de eventos de cajas de texto
        '''
        var.ui.lineDNI.editingFinished.connect(drivers.Drivers.validarDNI)   # Validar DNI

class Main(QtWidgets.QMainWindow):

    def __init__(self):
        super(Main, self).__init__()
        var.ui = Ui_mainWindow()
        var.ui.setupUi(self)        # Encargado la interfaz
        var.calendar = Calendar()   # Ventana del Calendario
        '''
        zona de eventos de botones
        '''
        # var.ui.btnSalir.clicked.connect(eventos.Eventos.salir)        # Cerrar programa
        '''
        zona de eventos de botones
        '''
        # var.ui.actionSalir.triggered.connect(eventos.Eventos.salir)   # Cerrar programa

if __name__ == '__main__':
    app = QtWidgets.QApplication([])
    window = Main()
    window.show()
    sys.exit(app.exec())
