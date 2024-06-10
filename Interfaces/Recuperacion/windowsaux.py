from datetime import datetime

import bills
import clients
import var
from CalendarWindow import *


class Calendar(QtWidgets.QDialog):
    # Ventana de calendario
    def __init__(self):
        super(Calendar, self).__init__()
        var.calendar = Ui_dlgCalendar()
        var.calendar.setupUi(self)
        dia = datetime.now().day
        mes = datetime.now().month
        ano = datetime.now().year
        var.calendar.Calendar.setSelectedDate((QtCore.QDate(ano,mes,dia)))
        var.calendar.Calendar.clicked.connect(clients.Clients.cargarFecha)
        var.calendar.Calendar.clicked.connect(bills.Bills.cargarFecha)
