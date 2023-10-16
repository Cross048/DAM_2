from Calendar import *
from dlgAcerca import *
from dlgSalir import *
from datetime import datetime
import var, drivers, eventos

class DlgSalir(QtWidget.QDialog):
    def __init__(self):
        super(DlgSalir, self).__init__()
        var.dlgsalir = Ui_dlgSalir()
        var.dlgsalir.setupUi(self)
        var.dlgsalir.btnSalir.clicked.connect(eventos.Eventos.salir)
        var.dlgsalir.btnCalendar.clicked.connect(eventos.Eventos.cerrarsalir)

class DlgAcerca(QtWidgets.QDialog):
    def __init__(self):
        super(DlgAcerca, self).__init__()
        var.dlgacerca = Ui_dlgAbout()
        var.dglacerca.setupUi(self)
        var.dlgcacerca.btnCerrar.clicked.connect(eventos.Eventos.cerraracercade)

class Calendar(QtWidgets.QDialog):
    def __init__(self):
        super(Calendar, self).__init__()
        var.calendar = Ui_dlgCalendar()
        var.calendar.setupUi(self)
        dia = datetime.now().day
        mes = datetime.now().month
        ano = datetime.now().year
        var.calendar.Calendar.setSelectedDate(QtCore.QDate(ano, mes, dia))
        var.calendar.Calendar.clicked.connect(drivers.Drivers.cargaFecha)

