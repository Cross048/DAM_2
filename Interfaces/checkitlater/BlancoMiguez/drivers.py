from datetime import datetime
import var
import locale, conexion
locale.setlocale(locale.LC_MONETARY, 'es_ES.UTF-8')
from PyQt6.QtGui import QPixmap
from PyQt6 import QtWidgets,QtCore, QtGui
import eventos
class Drivers():

    def limpiarPanel(self):
        """
        Limpia los widgets del panel de conductores.

        Limpia los textos y checkboxes del panel de conductores.
        """
        try:
            listawidgets=[var.ui.lblcodbd, var.ui.txtDNI, var.ui.txtfecha, var.ui.txtapellidos, var.ui.txtnombre,
                          var.ui.txtdir, var.ui.txtmovil, var.ui.txtsalario, var.ui.lblValidarDNI ]
            for i in listawidgets:
                i.setText(None)
            chklicencia = [var.ui.chkA, var.ui.chkB, var.ui.chkC, var.ui.chkD]
            for i in chklicencia:
                i.setChecked(False)
            var.ui.cmbProv.setCurrentText('')
            var.ui.cmbMuni.setCurrentText('')
        except Exception as error:
            print(str(error) + " en validar drivers")
    def cargaFecha(qDate):
        """
        Carga la fecha seleccionada en el widget de fecha.

        :param qDate: Fecha seleccionada.
        :type qDate: QDate
        """
        try:
            data=('{:02d}/{:02d}/{:4d}'.format(qDate.day(),qDate.month(),qDate.year()))
            var.ui.txtfecha.setText(str(data))
            var.calendar.hide()
        except Exception as error:
            print(str(error) + " en validar drivers")

    def validarDNI(dni):
        """
        Valida el formato de un DNI y muestra un ícono de verificación o error.

        :param dni: Número de DNI a validar.
        :type dni: str
        :return: True si el DNI es válido, False en caso contrario.
        :rtype: bool
        """
        try:
            var.ui.txtDNI.setText(dni)  # Corrección aquí
            tabla = "TRWAGMYFPDXBNJZSKVHLCKE"
            digExt = "XYZ"
            reempDigExt = {"X": '0', "Y": '1', "Z": '2'}
            numeros = "1234567890"
            imgCorrecto = QPixmap('img/tickcirclehd_106142.ico')
            imgIncorrecto = QPixmap('img/crosscircleregular_106260.ico')

            if len(dni) == 9:
                digControl = dni[8]
                dni = dni[:8]
                if dni[0] in digExt:
                    dni = dni.replace(dni[0], reempDigExt[dni[0]])
                if len(dni) == len([n for n in dni if n in numeros]) and tabla[int(dni) % 23] == digControl:
                    var.ui.lblValidarDNI.setPixmap(imgCorrecto)
                    var.ui.txtfecha.setFocus()
                    return True
                else:
                    var.ui.lblValidarDNI.setPixmap(imgIncorrecto)
                    var.ui.txtDNI.setText(None)
                    var.ui.txtDNI.setFocus()
                    return False
            else:
                var.ui.lblValidarDNI.setPixmap(imgIncorrecto)
                var.ui.txtDNI.setText(None)
                var.ui.txtDNI.setFocus()
                return False
        except Exception as error:
            print(str(error) + " en validar drivers")
    def altaDriver(self):
        """
        Da de alta a un conductor en la base de datos.

        Obtiene los datos del conductor desde los widgets, los valida y los guarda en la base de datos.
        """
        try:
            dni = var.ui.txtDNI.text()
            if conexion.Conexion.verificarDri(dni):
                conexion.Conexion.volverDarAlta(dni)

                Drivers.limpiarPanel(self)
                conexion.Conexion.mostrardriver()
            else:
                if not all([var.ui.txtDNI.text(), var.ui.txtnombre.text(), var.ui.txtapellidos.text(),
                            var.ui.txtmovil.text()]):
                    eventos.Eventos.error("Aviso", "Faltan datos obligatorios")
                    return
                driver = [
                    var.ui.txtDNI, var.ui.txtfecha, var.ui.txtapellidos, var.ui.txtnombre,
                    var.ui.txtdir, var.ui.txtmovil, var.ui.txtsalario
                ]
                newDriver = []
                for i in driver:
                    newDriver.append(i.text().title())

                prov = var.ui.cmbProv.currentText()
                newDriver.insert(5, prov)
                muni = var.ui.cmbMuni.currentText()
                newDriver.insert(6, muni)

                licencias = []
                chklicencia = [var.ui.chkA, var.ui.chkB, var.ui.chkC, var.ui.chkD]
                for i in chklicencia:
                    if i.isChecked():
                        licencias.append(i.text())
                newDriver.append('-'.join(licencias))
                valor=conexion.Conexion.guardardri(newDriver)
                if valor==True:
                    eventos.Eventos.mensaje("Aviso", "El conductor fue añadido con exito")
                    conexion.Conexion.mostrardriver()
                    conexion.Conexion.cargarconductor()
                elif valor == False:
                    eventos.Eventos.error("Aviso", "No se ha podido dar de alta")

        except Exception as error:
            print(str(error) + " en altadriver drivers")
    def validarMovil(self=None):
        """
        Valida el formato de un número de teléfono.

        Valida que el número de teléfono sea una cadena de 9 números enteros.
        """
        try:
            movil = var.ui.txtmovil.text()
            numeros = "1234567890"
            var.ui.txtmovil.setText(movil)  # Corrección aquí
            if len(movil) == 9:
                digControl = movil[:9]
                if len(movil) != len([n for n in movil if n in numeros])== digControl:
                    raise Exception
            else:
                raise Exception
        except Exception as error:
            eventos.Eventos.error("Aviso", "El telefono debe ser una cadena de 9 numeros enteros")
            var.ui.txtmovil.setText("")

    def validarSalario(self=None):
        """
        Valida el formato de un salario.

        Valida que el salario sea un valor numérico y lo formatea como una cadena de moneda.
        """
        try:
            sal = var.ui.txtmovil.text()
            numeros = "1234567890"
            var.ui.txtmovil.setText(sal)# Corrección aquí
            if len(sal) == len([n for n in sal if n in numeros]):
                var.ui.txtsalario.setText(str(locale.currency(float(var.ui.txtsalario.text()),grouping=True)))
            else:
                raise Exception
        except Exception as error:
            eventos.Eventos.error("Aviso", "Valor de Salario Incorrecto (00000000.00)")
            var.ui.txtsalario.setText("")

    def cargarTabladri(registros):
        """
        Carga los registros en la tabla de conductores.

        :param registros: Lista de registros de conductores.
        :type registros: list
        """
        try:
            index = 0

            for registro in registros:
                var.ui.tabDrivers.setRowCount(index + 1)
                var.ui.tabDrivers.setItem(index, 0, QtWidgets.QTableWidgetItem(str(registro[0])))
                var.ui.tabDrivers.setItem(index, 1, QtWidgets.QTableWidgetItem(str(registro[1])))
                var.ui.tabDrivers.setItem(index, 2, QtWidgets.QTableWidgetItem(str(registro[2])))
                var.ui.tabDrivers.setItem(index, 3, QtWidgets.QTableWidgetItem(str(registro[3])))
                var.ui.tabDrivers.setItem(index, 4, QtWidgets.QTableWidgetItem(str(registro[4])))
                var.ui.tabDrivers.setItem(index, 5, QtWidgets.QTableWidgetItem(str(registro[5])))
                var.ui.tabDrivers.item(index, 0).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                var.ui.tabDrivers.item(index, 3).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                var.ui.tabDrivers.item(index, 4).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                index += 1
        except Exception as error:
            print(str(error) + " en cargartabladri drivers")

    def cargarDriver(self):
        """
        Carga un conductor seleccionado en el panel de conductores.

        Obtiene los datos del conductor seleccionado en la tabla y los carga en los widgets del panel.
        """
        try:
            Drivers.limpiarPanel(self)
            row = var.ui.tabDrivers.selectedItems()
            fila =[dato.text() for dato in row]
            registro = conexion.Conexion.oneDriver(fila[0])
            Drivers.auxiliar(registro)
            conexion.Conexion.mostrardriver()
            Drivers.colorearFila(registro[0])
        except Exception as error:
            print(str(error) + " en cargarDriver drivers")

    def auxiliar(registro):
        """
        Carga un registro en los widgets del panel de conductores.

        :param registro: Lista con los datos del conductor.
        :type registro: list
        """
        try:
            datos=[var.ui.lblcodbd, var.ui.txtDNI, var.ui.txtfecha, var.ui.txtapellidos, var.ui.txtnombre,
                   var.ui.txtdir, var.ui.cmbProv, var.ui.cmbMuni, var.ui.txtmovil, var.ui.txtsalario]
            for i,dato in enumerate(datos):
                if i == 6 or i == 7 :
                    dato.setCurrentText(str(registro[i]))
                else:
                    dato.setText(str(registro[i]))
            if 'A' in registro [10]:
                var.ui.chkA.setChecked(True)
            if 'B' in registro [10]:
                var.ui.chkA.setChecked(True)
            if 'C' in registro [10]:
                var.ui.chkA.setChecked(True)
            if 'D' in registro [10]:
                var.ui.chkA.setChecked(True)
        except Exception as error:
            eventos.Eventos.error("Aviso", "No existe en la base de datos")

    def buscaDri(self):
        """
        Busca un conductor por su DNI en la base de datos.

        Obtiene el DNI del widget, busca el conductor en la base de datos y carga los datos en el panel.
        """
        try:
            dni = var.ui.txtDNI.text()
            registro = conexion.Conexion.codDri(dni)
            Drivers.auxiliar(registro)
            codigo = var.ui.lblcodbd.text()
            var.ui.rbtTodos.setChecked(True)
            conexion.Conexion.mostrardriver()
            Drivers.colorearFila(codigo)
        except Exception as error:
            print(error, "en busca de buscadri")

    def colorearFila(codigo):
        """
        Colorea la fila del conductor seleccionado en la tabla.

        :param codigo: Código del conductor seleccionado.
        :type codigo: str
        """
        for fila in range(var.ui.tabDrivers.rowCount()):
            if var.ui.tabDrivers.item(fila, 0).text() == str(codigo):
                for columna in range(var.ui.tabDrivers.columnCount()):
                    item = var.ui.tabDrivers.item(fila, columna)
                    if item is not None:
                        item.setBackground(QtGui.QColor(255, 241, 150))

    def modifDri(self):
        """
        Modifica los datos de un conductor en la base de datos.

        Obtiene los datos del panel, los valida y los guarda en la base de datos.
        """
        try:
            driver=[var.ui.lblcodbd,var.ui.txtDNI, var.ui.txtfecha, var.ui.txtapellidos, var.ui.txtnombre,
                    var.ui.txtdir, var.ui.txtmovil, var.ui.txtsalario]
            modifDriver=[]
            for i in driver:
                modifDriver.append(i.text().title())
            prov = var.ui.cmbProv.currentText()
            modifDriver.insert(6,prov)
            muni = var.ui.cmbMuni.currentText()
            modifDriver.insert(7,muni)
            licencias=[]
            chklicencia = [var.ui.chkA, var.ui.chkB, var.ui.chkC, var.ui.chkD]
            for i in chklicencia:
                if i.isChecked():
                    licencias.append(i.text())
            modifDriver.append('-'.join(licencias))
            conexion.Conexion.modifDriver(modifDriver)
        except Exception as error:
            print(error, " en modifdri")

    def borraDri(qDate):
        """
        Borra un conductor de la base de datos.

        Obtiene la fecha seleccionada en el widget, el DNI del conductor y realiza la eliminación en la base de datos.
        """
        try:
            data = ('{:02d}/{:02d}/{:4d}'.format(qDate.day(), qDate.month(), qDate.year()))
            var.Baja.hide()
            dni = var.ui.txtDNI.text()
            conexion.Conexion.borrarDri(dni, str(data))
            conexion.Conexion.mostrardriver()
            conexion.Conexion.cargarconductor()
        except Exception as error:
            eventos.Eventos.error("Aviso", "El conductor no existe o no se puede borrar")