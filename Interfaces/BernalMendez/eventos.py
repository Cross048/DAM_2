import var, sys
from PyQt6 import QtCore, QtWidgets
from datetime import datetime

class Eventos():
    def salir(self):
        try:
            sys.exit(0)
        except Exception as error:
            print(error, "en módulo eventos")

    def abrirCalendar(self):
        try:
            var.calendar.show()
        except Exception as error:
            print('error en abrir calendar', error)
    
    def acercaDe(self):
        try:
            pass
        except Exception as error:
            print("error abrir ventana acerca de", error)

    def mostrarsalir(self):
        try:
            var.dlgsalir.show()
        except Exception as error:
            print("error en mostrar ventana salir: ", error)

    def cargastatusbar(self):
        '''
        Formatear la fecha según el formato deseadofecha_actual.strftime()
        statusbar
        '''
        try:
            fecha = datetime.now().strftime("%A - " + "%d/%m/%Y")
            self.labelstatus = QtWidgets.QLabel(fecha, self)
            self.labelstatus.setAlignment(QtCore.Qt.AlignmentFlag.AlignHCenter)
            var.ui.statusbar.addPermanentWidget(self.labelstatus, 1)
            self.labelstatusversion = QtWidgets.QLabel("Version: " + var.version, self)
            self.labelstatusversion.setAlignment(QtCore.Qt.AlignmentFlag.AlignRight)
            var.ui.statusbar.addPermanentWidget(self.labelstatusversion, 0)
        except Exception as error:
            print("Error cargar el statusbar: ", error)

    def cargaprov(self = None):
        try:
            prov = ["A Coruña", "Lugo", "Pontevedra"]
            var.ui.comboBoxProvincia.clear()
            var.ui.comboBoxProvincia.addItem('')
