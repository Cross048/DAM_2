import os.path
from datetime import datetime

from PyQt6 import QtWidgets, QtCore, QtGui

import locale
import sys

import drivers
import var
import zipfile
import shutil
import conexion
import xlwt
import xlrd

# Establecer la configuración regional en español
locale.setlocale(locale.LC_TIME, 'es_ES')
locale.setlocale(locale.LC_MONETARY, 'es_ES')

class Eventos():
    @staticmethod
    def abrirCalendar(self):
        try:
            var.calendar.show()
        except Exception as error:
            print('error en abrir calendar: ', error)

    @staticmethod
    def acercade():
        try:
            var.dlgacerca.show()
        except Exception as error:
            print('error abrir ventana acerca: ', error)
    @staticmethod
    def cerraracercade():
        try:
            var.dlgacerca.hide()
        except Exception as error:
            print('error abrir ventana acerca: ', error)

    def mostrarsalir(self=None):
        mbox = QtWidgets.QMessageBox()
        mbox.setWindowTitle('Confirmar Salida')
        mbox.setIcon(QtWidgets.QMessageBox.Icon.Information)
        mbox.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
        mbox.setText('¿Está seguro de que desea salir?')
        mbox.setStandardButtons(QtWidgets.QMessageBox.StandardButton.Yes | QtWidgets.QMessageBox.StandardButton.No)
        mbox.button(QtWidgets.QMessageBox.StandardButton.Yes).setText('Si')
        mbox.button(QtWidgets.QMessageBox.StandardButton.No).setText('No')
        mbox.setDefaultButton(QtWidgets.QMessageBox.StandardButton.Yes)
        mbox.setDefaultButton(QtWidgets.QMessageBox.StandardButton.No)
        if mbox.exec() == QtWidgets.QMessageBox.StandardButton.Yes:
            sys.exit()
        else:
            mbox.hide()

    def cargastatusbar(self):
        try:
            fecha = datetime.now().strftime("%A  -  " + "%d/%m/%Y")
            self.labelstatus = QtWidgets.QLabel(fecha, self)
            self.labelstatus.setAlignment(QtCore.Qt.AlignmentFlag.AlignHCenter)
            var.ui.statusbar.addPermanentWidget(self.labelstatus, 1)
            self.labelstatusversion = QtWidgets.QLabel("Version: " + var.version, self)
            self.labelstatusversion.setAlignment(QtCore.Qt.AlignmentFlag.AlignRight)
            var.ui.statusbar.addPermanentWidget(self.labelstatusversion, 0)
        except Exception as error:
            print('Error cargar el statusbar: ', error)

    def resizeTabdrivers(self):
        try:
            header = var.ui.tabDrivers.horizontalHeader()
            for i in range(5):
                if i == 0 or i == 4 or i == 3:
                    header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.ResizeToContents)
                elif i == 1 or i == 2:
                    header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.Stretch)
        except Exception as error:
            print('error resize en tab drivers', error)

    @staticmethod
    def formatCajatexto(self = None):
        try:
            var.ui.txtApel.setText(var.ui.txtApel.text().title())
            var.ui.txtNome.setText(var.ui.txtNome.text().title())
            salario = var.ui.txtSalario.text()
            valores = "1234567890."
            for n in salario:
                if n in valores:
                    pass
                else:
                    msg = QtWidgets.QMessageBox()
                    msg.setWindowTitle('Aviso')
                    msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
                    msg.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
                    msg.setText('Valor de Salario Incorrecto (00000000.00)')
                    msg.setStandardButtons(QtWidgets.QMessageBox.StandardButton.Ok)
                    msg.button(QtWidgets.QMessageBox.StandardButton.Ok).setText('Aceptar')
                    msg.setDefaultButton(QtWidgets.QMessageBox.StandardButton.Ok)
                    msg.exec()
                    var.ui.txtSalario.setText("")
                    break
            var.ui.txtSalario.setText(str(locale.currency(round(float(var.ui.txtSalario.text()),2),grouping=True)))
        except Exception as error:
            print('error poner letra capital cajas text', error)

    def formatCajamovil(self=None):
        try:
            var.ui.txtApel.setText(var.ui.txtApel.text().title())
            var.ui.txtNome.setText(var.ui.txtNome.text().title())
            movil = var.ui.txtMovil.text()
            valorm = "1234567890"
            for n in movil:
                if n in valorm and len(movil) == 9:
                    pass
                else:
                    msg = QtWidgets.QMessageBox()
                    msg.setWindowTitle('Aviso')
                    msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
                    msg.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
                    msg.setText('Escriba un número de móvil correcto')
                    msg.setStandardButtons(QtWidgets.QMessageBox.StandardButton.Ok)
                    msg.button(QtWidgets.QMessageBox.StandardButton.Ok).setText('Aceptar')
                    msg.setDefaultButton(QtWidgets.QMessageBox.StandardButton.Ok)
                    msg.exec()
                    var.ui.txtMovil.setText("")
                    break
        except Exception as error:
            print('error poner movil', error)

    def crearbackup(self):
        try:
            fecha = datetime.today()
            fecha = fecha.strftime('%Y_%m_%d_%H_%M_%S')
            copia = str(fecha)+'_backup.zip'
            directorio, filename = var.dlgabrir.getSaveFileName(None, 'Guardar Copia Seguridad', copia, '.zip')
            if var.dlgabrir.accept and filename:
                fichzip = zipfile.ZipFile(copia,'w')
                fichzip.write(var.bbdd, os.path.basename(var.bbdd), zipfile.ZIP_DEFLATED)
                fichzip.close()
                shutil.move(str(copia),str(directorio))
                msg = QtWidgets.QMessageBox()
                msg.setWindowTitle('Aviso')
                msg.setIcon(QtWidgets.QMessageBox.Icon.Information)
                msg.setText('Copia de Seguridad Creada')
                msg.exec()
        except Exception as error:
            msg = QtWidgets.QMessageBox()
            msg.setWindowTitle('Aviso')
            msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
            msg.setText('Error en Copia de Seguridad', error)
            msg.exec()

    def restaurarbackup(self):
        try:
            filename = var.dlgabrir.getOpenFileName(None,'Restaurar Copia de Seguridad',
                                                    '','*.zip;;All Files(*)')
            file = filename[0]
            if file:
                with zipfile.ZipFile(str(file), 'r') as bbdd:
                    bbdd.extractall(pwd=None)
                bbdd.close()
                msg = QtWidgets.QMessageBox()
                msg.setModal(True)
                msg.setWindowTitle('Aviso')
                msg.setIcon(QtWidgets.QMessageBox.Icon.Information)
                msg.setText('Copia de Seguridad Restaurada')
                msg.exec()
                conexion.Conexion.mostrardrivers(self)
        except Exception as error:
            msg = QtWidgets.QMessageBox()
            msg.setWindowTitle('Aviso')
            msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
            msg.setText('Error Restauración de la Copia de Seguridad', error)
            msg.exec()

    def exportardatosxls():
        try:
            fecha = datetime.today()
            fecha = fecha.strftime('%Y_%m_%d_%H_%M_%S')
            file = (str(fecha)+'_Datos.xls')
            directorio, filename = var.dlgabrir.getSaveFileName(None, 'Exportar Datos en XLS', file, '.xls')
            if filename:
                wb = xlwt.Workbook()
                sheet1 = wb.add_sheet('Conductores')
                sheet1.write(0, 0, 'ID')
                sheet1.write(0, 1, 'DNI')
                sheet1.write(0, 2, 'Fecha Alta')
                sheet1.write(0, 3, 'Apellidos')
                sheet1.write(0, 4, 'Nombre')
                sheet1.write(0, 5, 'Dirección')
                sheet1.write(0, 6, 'Provincia')
                sheet1.write(0, 7, 'Municipio')
                sheet1.write(0, 8, 'Móvil')
                sheet1.write(0, 9, 'Salario')
                sheet1.write(0, 10, 'Carnet')
                registros = conexion.Conexion.selectDriverstodos()
                for fila, registro in enumerate(registros, 1):
                    for i, valor in enumerate(registro[:-1]):
                        sheet1.write(fila, i, str(valor))
                wb.save(directorio)
                msg = QtWidgets.QMessageBox()
                msg.setModal(True)
                msg.setWindowTitle('Aviso')
                msg.setIcon(QtWidgets.QMessageBox.Icon.Information)
                msg.setText('Exportación de Datos Realizada')
                msg.exec()
        except Exception as error:
            msg = QtWidgets.QMessageBox()
            msg.setWindowTitle('Aviso')
            msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
            msg.setText('Error Exportar Datos en Hoja de Cálculo', error)
            msg.exec()

    def importardatosxls(self):
        try:
            estado = 0
            drivers.Drivers.limpiapanel(self)
            filename, _ = var.dlgabrir.getOpenFileName(None, 'Importar datos',
                                                    '', '*.xls;;All Files (*)')
            if filename:
                file = filename
                documento = xlrd.open_workbook(file)
                datos = documento.sheet_by_index(0)
                filas = datos.nrows
                columnas = datos.ncols
                for i in range(filas):
                    if i == 0:
                        pass
                    else:
                        new = []
                        for j in range(columnas):
                            if j == 1:
                                dato = xlrd.xldate_as_datetime(datos.cell_value(i, j), documento.datemode)
                                dato = dato.strftime('%d/%m/%Y')
                                new.append(str(dato))
                            else:
                                new.append(str(datos.cell_value(i, j)))
                        if drivers.Drivers.validarDNI(str(new[0])):
                            conexion.Conexion.guardardri(new)
                        elif estado == 0:
                            estado = 1
                            msg = QtWidgets.QMessageBox()
                            msg.setModal(True)
                            msg.setWindowTitle('Aviso')
                            msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
                            msg.setText('Hay DNI incorrectos')
                            msg.exec()
                var.ui.lblValidardni.setText('')
                var.ui.txtDni.setText('')
                msg = QtWidgets.QMessageBox()
                msg.setModal(True)
                msg.setWindowTitle('Aviso')
                msg.setIcon(QtWidgets.QMessageBox.Icon.Information)
                msg.setText('Importación de Datos Realizada')
                var.ui.lblValidardni.setText('')
                msg.exec()
            conexion.Conexion.selectDrivers(1)
        except Exception as error:
            msg = QtWidgets.QMessageBox()
            msg.setModal(True)
            msg.setWindowTitle('Aviso')
            msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
            msg.setText('Error', error)
            msg.exec()
