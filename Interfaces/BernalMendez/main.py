# import eventos
from MainWindow import *
import sys, var, eventos

# Instalar PyQt6 con
# pip install pyqt6

class Main(QtWidgets.QMainWindow):

    def __init__(self):
        super(Main, self).__init__()
        var.ui = Ui_mainWindow()
        var.ui.setupUi(self) #encargado la interfaz

        '''
        zona de eventos de botones
        '''
        var.ui.btnSalir.clicked.connect(eventos.Eventos.salir)

if __name__ == '__main__':
    app = QtWidgets.QApplication([])
    window = Main()
    window.show()
    sys.exit(app.exec())

