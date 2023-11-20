import os.path

import var, sys
from PyQt6 import QtCore, QtWidgets
from datetime import datetime
import zipfile
import shutil

class Eventos():
    def salir(self):
        try:
            var.dlgsalir.show()
            if var.dlgsalir.exec():
                sys.exit(0)
            else:
                var.dlgsalir.hide()
        except Exception as error:
            print("Error en módulo salir: ", error)

    def abrirCalendar(self):
        try:
            var.dlgcalendar.show()
        except Exception as error:
            print('error en abrir calendar', error)
    
    def acercaDe(self):
        try:
            var.dlgacerca.show()
        except Exception as error:
            print("error abrir ventana acerca de", error)

    def mostrarSalir(self):
        try:
            var.dlgSalir.show()
        except Exception as error:
            print("error en mostrar ventana salir: ", error)

    def cargastatusbar(self):
        ''' Formatear la fecha según el formato deseadofecha_actual.strftime() statusbar '''
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
        except:
            print('error')

    def crearBackup(self):
        try:
            fecha = datetime.today()
            fecha = fecha.strtime('%Y_%m_%d_%H_%M_%S')
            copia = str(fecha) + "_backup.zip"
            directorio, filename = var.dglabrir.getSaveFileName(None, "Guardar Copia Seguridad", copia, ".zip")
            if var.dlgabrir.accept and filename != '':
                fichzip = zipfile.ZipFile(copia, 'w')
                fichzip.write(var.bbdd, os.path.basename(var.bbdd), zipfile.ZIP_DEFLATED)
                fichzip.close()
                shutil.move(str(copia), str(directorio))
                msg = QtWidgets.QMessageBox()
                msg.setWindowTitle("Aviso")
                msg.setIcon(QtWidgets.QMessageBox.Icon.Information)
                msg.setText("Copia de seguridad realizada!")
                msg.exec()
        except Exception as error:
            msg = QtWidgets.QMessageBox()
            msg.setWindowTitle("Aviso")
            msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
            msg.setText("Error en copia de seguridad: ", error)
            msg.exec()
