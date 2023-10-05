# import eventos
from MainWindow_ui import *
import sys, var, eventos
from Calendar_ui import *
from datetime import datetime

# Instalar PyQt6 con
# pip install pyqt6

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
        # Abrir Calendario
        var.ui.btnCalendar.clicked.connect(eventos.Eventos.abrirCalendar)

class Main(QtWidgets.QMainWindow):

    def __init__(self):
        super(Main, self).__init__()
        var.ui = Ui_mainWindow()
        var.ui.setupUi(self) # Encargado la interfaz
        var.calendar = Calendar() # Ventana del Calendario
        '''
        zona de eventos de botones
        '''
        # Cerrar programa
        # var.ui.btnSalir.clicked.connect(eventos.Eventos.salir)
        '''
        zona de eventos de botones
        '''
        # Cerrar programa
        # var.ui.actionSalir.triggered.connect(eventos.Eventos.salir)

if __name__ == '__main__':
    app = QtWidgets.QApplication([])
    window = Main()
    window.show()
    sys.exit(app.exec())
