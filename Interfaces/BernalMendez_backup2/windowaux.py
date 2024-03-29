from datetime import datetime

import drivers
import eventos
import var
from CalendarWindow import *
from dlgAcerca import *


class DlgAcerca(QtWidgets.QDialog):
    def __init__(self):
        super(DlgAcerca, self).__init__()
        var.dlgacerca = Ui_dlgAbout()
        var.dlgacerca.setupUi(self)
        var.dlgacerca.btnCerrar.clicked.connect(eventos.Eventos.cerraracercade)
        var.dlgacerca.lblVersion.setText("Versión: " + var.version)

class Calendar(QtWidgets.QDialog):
    def __init__(self):
        super(Calendar, self).__init__()
        var.calendar = Ui_dlgCalendar()
        var.calendar.setupUi(self)
        dia = datetime.now().day
        mes = datetime.now().month
        ano = datetime.now().year
        var.calendar.Calendar.setSelectedDate((QtCore.QDate(ano,mes,dia)))
        var.calendar.Calendar.clicked.connect(drivers.Drivers.cargaFecha)

class FileDialogAbrir(QtWidgets.QFileDialog):
    def __init__(self):
        super(FileDialogAbrir, self).__init__()
