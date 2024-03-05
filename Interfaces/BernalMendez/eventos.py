import locale
import os.path
import shutil
import sys
import zipfile
from datetime import datetime

import xlrd
import xlwt
from PyQt6 import QtWidgets, QtCore, QtGui

import clientes
import conexion
import var

# Establecer la configuración regional en español
locale.setlocale(locale.LC_TIME, 'es_ES')
locale.setlocale(locale.LC_MONETARY, 'es_ES')

class Eventos():
    @staticmethod
    def abrirCalendar(self):
        try:
            var.calendar.show()
        except Exception as error:
            print("Error al abrir calendario: ", error)

    @staticmethod
    def acercade():
        try:
            var.dlgacerca.show()
        except Exception as error:
            print("Error al abrir ventana Acerca de: ", error)

    @staticmethod
    def cerraracercade():
        try:
            var.dlgacerca.hide()
        except Exception as error:
            print("Error al abrir ventana Acerca de: ", error)

    def mostrarsalir(self=None):
        mbox = QtWidgets.QMessageBox()
        mbox.setWindowTitle("Confirmar Salida")
        mbox.setIcon(QtWidgets.QMessageBox.Icon.Information)
        mbox.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
        mbox.setText("¿Está seguro de que desea salir?")
        mbox.setStandardButtons(QtWidgets.QMessageBox.StandardButton.Yes | QtWidgets.QMessageBox.StandardButton.No)
        mbox.button(QtWidgets.QMessageBox.StandardButton.Yes).setText("Si")
        mbox.button(QtWidgets.QMessageBox.StandardButton.No).setText("No")
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
            print("Error al cargar el statusbar: ", error)

    def resizeTabdrivers(self):
        try:
            header = var.ui.tabDrivers.horizontalHeader()
            for i in range(5):
                if i == 0 or i == 4 or i == 3:
                    header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.ResizeToContents)
                elif i == 1 or i == 2:
                    header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.Stretch)
        except Exception as error:
            print("Error en el resize en tab drivers: ", error)

    @staticmethod
    def formatCajatexto():
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
                    msg.setWindowTitle("Aviso")
                    msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
                    msg.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
                    msg.setText("Valor de Salario Incorrecto (00000000.00)")
                    msg.setStandardButtons(QtWidgets.QMessageBox.StandardButton.Ok)
                    msg.button(QtWidgets.QMessageBox.StandardButton.Ok).setText("Aceptar")
                    msg.setDefaultButton(QtWidgets.QMessageBox.StandardButton.Ok)
                    msg.exec()
                    var.ui.txtSalario.setText("")
                    break
            var.ui.txtSalario.setText(str(locale.currency(round(float(var.ui.txtSalario.text()),2),grouping=True)))
        except Exception as error:
            print("Error al poner letra capital en cajas de texto: ", error)

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
                    msg.setWindowTitle("Aviso")
                    msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
                    msg.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
                    msg.setText("Escriba un número de teléfono correcto")
                    msg.setStandardButtons(QtWidgets.QMessageBox.StandardButton.Ok)
                    msg.button(QtWidgets.QMessageBox.StandardButton.Ok).setText("Aceptar")
                    msg.setDefaultButton(QtWidgets.QMessageBox.StandardButton.Ok)
                    msg.exec()
                    var.ui.txtMovil.setText("")
                    break
        except Exception as error:
            print("Error al poner telefono: ", error)

    @staticmethod
    def crearbackup():
        try:
            fecha = datetime.today()
            fecha = fecha.strftime('%Y_%m_%d_%H_%M_%S')
            copia = str(fecha) + '_backup.zip'
            directorio, filename = var.dlgabrir.getSaveFileName(None, 'Guardar copia de seguridad', copia, '.zip')
            if var.dlgabrir.accept and filename != '':
                fichZip = zipfile.ZipFile(copia, 'w')
                fichZip.write(var.bbdd, os.path.basename(var.bbdd), zipfile.ZIP_DEFLATED)
                fichZip.close()
                shutil.move(str(copia), str(directorio))
                eventos.Eventos.mensaje("Aviso", "Copia de seguridad creada")

        except Exception as error:
            eventos.Eventos.error("Aviso", "Error al crear backup")

    def restaurarbackup(self):
        try:
            filename = var.dlgabrir.getOpenFileName(None, 'Restaurar copia de seguridad', '', '*.zip;;All Files(*)')
            file = filename[0]
            if filename[0]:
                with zipfile.ZipFile(str(file), 'r') as bbdd:
                    bbdd.extractall(pwd=None)
                bbdd.close()
                eventos.Eventos.mensaje("Aviso", "Copia de seguridad restaurada")
        except Exception as error:
            eventos.Eventos.error("Aviso", "Error al restaurar el backup")

    def exportardatosclientesxls(self):
        try:
            fecha = datetime.today()
            fecha = fecha.strftime('%Y_%m_%d_%H_%M_%S')
            file = str(fecha) + '_Datosclientes.xls'
            directorio, filename = var.dlgabrir.getSaveFileName(None, 'Exportar Datos en xls', file, '.xls')
            if var.dlgabrir.accept and filename:
                wb = xlwt.Workbook()
                sheet1 = wb.add_sheet('Clientes')
                sheet1.write(0, 0, 'Codigo')
                sheet1.write(0, 1, 'DNI')
                sheet1.write(0, 2, 'Razon Social')
                sheet1.write(0, 3, 'Dirección')
                sheet1.write(0, 4, 'Provincia')
                sheet1.write(0, 5, 'Municipio')
                sheet1.write(0, 6, 'Telefono')
                registros = conexion.Conexion.selectClientestodos()
                for fila, registro in enumerate(registros, 1):
                    for i, valor in enumerate(registro[:-1]):
                        sheet1.write(fila, i, str(valor))
                wb.save(directorio)
                eventos.Eventos.mensaje("Aviso", "Exportacion completada exitosamente")
        except Exception as error:
            eventos.Eventos.error("Aviso", "Error al exportar datos")

    def importarDatosclientesExcel(self):
        try:
            filename = var.dlgabrir.getOpenFileName(None, "Importar datos", "", "*.xls;;All File(*)")
            clientes.Clientes.limpiapanel2()
            if filename[0]:
                file = filename[0]
                documento = xlrd.open_workbook(file)
                datos = documento.sheet_by_index(0)
                filas = datos.nrows
                columnas = datos.ncols
                numFallo = 0
                for i in range(filas):
                    if i == 0:
                        pass
                    else:
                        new = []
                        for j in range(columnas):
                            if j != 0:
                                new.append(str(datos.cell_value(i, j)))

                        conexion.Conexion.guardarcli(new)

                        if i == filas - 1:
                            mbox = QtWidgets.QMessageBox()
                            mbox.setWindowTitle('Aviso')
                            mbox.setModal(True)
                            mbox.setIcon(QtWidgets.QMessageBox.Icon.Warning)
                            mbox.setText("Importacion exitosa,clientes no insertados " + str(numFallo))
                            mbox.exec()
                conexion.Conexion.mostrarclientes()
        except Exception as error:
            mbox = QtWidgets.QMessageBox()
            mbox.setWindowTitle('Aviso')
            mbox.setModal(True)
            mbox.setIcon(QtWidgets.QMessageBox.Icon.Warning)
            mbox.setText('Error al importar datos')
            mbox.exec()

    def formatCajamovil2(self=None):
        try:
            var.ui.txtApel.setText(var.ui.txtRazonSocial.text().title())
            movil = var.ui.txtMovil_2.text()
            valorm = "1234567890"
            for n in movil:
                if n in valorm and len(movil) == 9:
                    pass
                else:
                    msg = QtWidgets.QMessageBox()
                    msg.setWindowTitle("Aviso")
                    msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
                    msg.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
                    msg.setText("Escriba un número de teléfono correcto")
                    msg.setStandardButtons(QtWidgets.QMessageBox.StandardButton.Ok)
                    msg.button(QtWidgets.QMessageBox.StandardButton.Ok).setText("Aceptar")
                    msg.setDefaultButton(QtWidgets.QMessageBox.StandardButton.Ok)
                    msg.exec()
                    var.ui.txtMovil_2.setText("")
                    break
        except Exception as error:
            print("Error al poner telefono: ", error)

    def resizeTabclientes(self):
        try:
            header = var.ui.tabDrivers_2.horizontalHeader()
            for i in range(5):
                if i == 0 or i == 4 or i == 3:
                    header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.ResizeToContents)
                elif i == 1 or i == 2:
                    header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.Stretch)
        except Exception as error:
            print("Error en el resize en tab drivers: ", error)

    @staticmethod
    def formatCajatexto2():
        try:
            var.ui.txtRazonSocial.setText(var.ui.txtRazonSocial.text().title())
        except Exception as error:
            print("Error al poner letra capital en cajas de texto: ", error)

    def mensaje(title, text):
        mbox = QtWidgets.QMessageBox()
        mbox.setWindowTitle(title)
        mbox.setWindowIcon(QtGui.QIcon("img/limpiar.png"))
        mbox.setIcon(QtWidgets.QMessageBox.Icon.Information)
        mbox.setText(text)
        mbox.exec()

    def error(title, text):
        mbox = QtWidgets.QMessageBox()
        mbox.setWindowTitle(title)
        mbox.setWindowIcon(QtGui.QIcon("img/limpiar.png"))
        mbox.setIcon(QtWidgets.QMessageBox.Icon.Warning)
        mbox.setText(text)
        mbox.exec()