from PyQt6 import QtWidgets, QtCore, QtGui

import conexion
import var

class Drivers():
    @staticmethod
    def limpiapanel(self):
        try:
            listawidgets = [var.ui.lblcodbd, var.ui.txtDni, var.ui.txtDatadriver, var.ui.txtApel, var.ui.txtNome,
                            var.ui.txtDirdriver, var.ui.txtMovil, var.ui.txtSalario, var.ui.lblValidardni]
            for i in listawidgets:
                i.setText(None)
            chklicencia = [var.ui.chkA, var.ui.chkB, var.ui.chkC, var.ui.chkD]
            for i in chklicencia:
                i.setChecked(False)
            var.ui.cmbProv.setCurrentText('')
            var.ui.cmbMuni.setCurrentText('')
            if var.ui.rbtAlta.isChecked():
                estado = 1
                conexion.Conexion.selectDrivers(estado)
            else:
                registros = conexion.Conexion.mostrardrivers(self)
                Drivers.cargartabladri(registros)
        except Exception as error:
            print('error limpia panel driver: ', error)

    def cargaFecha(qDate):
        try:
            data = ('{:02d}/{:02d}/{:4d}'.format(qDate.day(), qDate.month(), qDate.year()))
            var.ui.txtDatadriver.setText(str(data))
            return data
            var.calendar.hide()
        except Exception as error:
            print("error en cargar fecha: ", error)

    def validarDNI(dni):
        try:
            dni = str(dni).upper()
            var.ui.txtDni.setText(str(dni))
            tabla = "TRWAGMYFPDXBNJZSQVHLCKE"
            dig_ext = "XYZ"
            reemp_dig_ext = {'X': '0', 'Y': '1', 'Z': '2'}
            numeros = "1234567890"
            if len(dni) == 9:
                dig_control = dni[8]
                dni = dni[:8]
                if dni[0] in dig_ext:
                    dni = dni.replace(dni[0], reemp_dig_ext[dni[0]])
                if len(dni) == len([n for n in dni if n in numeros]) and tabla[int(dni) % 23] == dig_control:
                    var.ui.lblValidardni.setStyleSheet('color:green;')  # si es válido se pone una V en color verde
                    var.ui.lblValidardni.setText('V')
                    return True
                else:
                    var.ui.lblValidardni.setStyleSheet('color:red;')  # y si no un aspa en color rojo
                    var.ui.lblValidardni.setText('X')
                    var.ui.txtDni.setText(None)
                    var.ui.txtDni.setFocus()
            else:
                var.ui.lblValidardni.setStyleSheet('color:red;')
                var.ui.lblValidardni.setText('X')
                var.ui.txtDni.setText(None)
                var.ui.txtDni.setFocus()

        except Exception as error:
            print("error en validar dni ", error)

    def altadriver(self):
        try:
            driver = [var.ui.txtDni, var.ui.txtDatadriver, var.ui.txtApel, var.ui.txtNome,
                      var.ui.txtDirdriver, var.ui.txtMovil, var.ui.txtSalario]
            newdriver = []
            for i in driver:
                newdriver.append(i.text().title())
            prov = var.ui.cmbProv.currentText()
            newdriver.insert(5, prov)
            muni = var.ui.cmbMuni.currentText()
            newdriver.insert(6, muni)
            licencias = []
            chklicencia = [var.ui.chkA, var.ui.chkB, var.ui.chkC, var.ui.chkD]
            for i in chklicencia:
                if i.isChecked():
                    licencias.append(i.text())
            newdriver.append('-'.join(licencias))
            valor = conexion.Conexion.guardardri(newdriver)
            if valor == True:
                mbox = QtWidgets.QMessageBox()
                mbox.setWindowTitle('Aviso')
                mbox.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
                mbox.setIcon(QtWidgets.QMessageBox.Icon.Information)
                mbox.setText("Empleado dado de alta")
                mbox.exec()
            elif valor == False:
                mbox = QtWidgets.QMessageBox()
                mbox.setWindowTitle('Aviso')
                mbox.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
                mbox.setIcon(QtWidgets.QMessageBox.Icon.Warning)
                mbox.setText("Asegúrese de que el conductor no existe")
                mbox.exec()
        except Exception as error:
            print("error alta cliente", error)

    def cargartabladri(registros):
        try:
            var.ui.tabDrivers.clearContents()
            index = 0
            for registro in registros:
                var.ui.tabDrivers.setRowCount(index + 1)  # crea una fila
                var.ui.tabDrivers.setItem(index, 0, QtWidgets.QTableWidgetItem(str(registro[0])))
                var.ui.tabDrivers.setItem(index, 1, QtWidgets.QTableWidgetItem(str(registro[1])))
                var.ui.tabDrivers.setItem(index, 2, QtWidgets.QTableWidgetItem(str(registro[2])))
                var.ui.tabDrivers.setItem(index, 3, QtWidgets.QTableWidgetItem(str(registro[3])))
                var.ui.tabDrivers.setItem(index, 4, QtWidgets.QTableWidgetItem(str(registro[4])))
                var.ui.tabDrivers.setItem(index, 5, QtWidgets.QTableWidgetItem(str(registro[5])))
                var.ui.tabDrivers.item(index, 0).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                var.ui.tabDrivers.item(index, 3).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                var.ui.tabDrivers.item(index, 4).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                var.ui.tabDrivers.item(index, 5).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                index += 1
        except Exception as error:
            print("error cargar dato en tabla", error)

    def cargadriver(self = None):
        try:
            #var.ui.tabDrivers.clearContents()
            #Drivers.cargartabladri(conexion.Conexion.mostrardrivers(self))
            fila = var.ui.tabDrivers.selectedItems()
            row = [dato.text() for dato in fila]
            registro = conexion.Conexion.onedriver(row[0])
            Drivers.cargardatos(registro)
        except Exception as error:
            print('error cargar datos de 1 cliente marcando en la tabla: ', error)

    def buscaDri(self):
        try:
            dni = var.ui.txtDni.text()
            registro = conexion.Conexion.codDri(dni)
            Drivers.cargardatos(registro)
            if var.ui.rbtTodos.isChecked():
                estado = 0
                conexion.Conexion.selectDrivers(estado)
            elif var.ui.rbtAlta.isChecked():
                estado = 1
                conexion.Conexion.selectDrivers(estado)
            elif var.ui.rbtBaja.isChecked():
                estado = 2
                conexion.Conexion.selectDrivers(estado)
            codigo = var.ui.lblcodbd.text()
            for fila in range(var.ui.tabDrivers.rowCount()):
                if var.ui.tabDrivers.item(fila, 0).text() == str(codigo):
                    for columna in range(var.ui.tabDrivers.columnCount()):
                        item = var.ui.tabDrivers.item(fila, columna)
                        if item is not None:
                            item.setBackground(QtGui.QColor(255, 241, 150))
        except Exception as error:
            print(error, "en busca de datos de un conductor")

    def cargardatos(registro):
        try:
            datos = [var.ui.lblcodbd, var.ui.txtDni, var.ui.txtDatadriver, var.ui.txtApel, var.ui.txtNome,
                     var.ui.txtDirdriver, var.ui.cmbProv, var.ui.cmbMuni, var.ui.txtMovil, var.ui.txtSalario]
            for i, dato in enumerate(datos):
                if i == 6 or i == 7:
                    dato.setCurrentText(str(registro[i]))
                else:
                    dato.setText(str(registro[i]))
            if 'A' in registro[10]:
                var.ui.chkA.setChecked(True)
            else:
                var.ui.chkA.setChecked(False)
            if 'B' in registro[10]:
                var.ui.chkB.setChecked(True)
            else:
                var.ui.chkB.setChecked(False)
            if 'C' in registro[10]:
                var.ui.chkC.setChecked(True)
            else:
                var.ui.chkC.setChecked(False)
            if 'D' in registro[10]:
                var.ui.chkD.setChecked(True)
            else:
                var.ui.chkD.setChecked(False)
            #Drivers.cargartabladri(conexion.Conexion.mostrardrivers())
        except Exception as error:
            print("cargar datos en panel gestión", error)

    def modifDri(self):
        try:
            driver = [var.ui. lblcodbd, var.ui.txtDni, var.ui.txtDatadriver,
                      var.ui.txtApel, var.ui.txtNome, var.ui.txtDirdriver,
                      var.ui.txtMovil, var.ui.txtSalario]
            modifdriver = []
            for i in driver:
                modifdriver.append(i.text().title())
            prov = var.ui.cmbProv.currentText()
            modifdriver.insert(6, prov)
            muni = var.ui.cmbMuni.currentText()
            modifdriver.insert(7, muni)
            licencias = []
            chklicencia = [var.ui.chkA, var.ui.chkB, var.ui.chkC, var.ui.chkD]
            for i in chklicencia:
                if i.isChecked():
                    licencias.append(i.text())
            modifdriver.append('-'.join(licencias))
            conexion.Conexion.modifDriver(modifdriver)
        except Exception as error:
            print('error en modif drivaer en Drivers', error)

    def borraDriv(self):
        try:
            dni = var.ui.txtDni.text()
            conexion.Conexion.borraDriv(dni)
            conexion.Conexion.selectDrivers(1)
        except Exception as error:
            mbox = QtWidgets.QMessageBox()
            mbox.setWindowTitle('Aviso')
            mbox.setIcon(QtWidgets.QMessageBox.Icon.Warning)
            mbox.setText('El conductor no existe o no se puede borrar')
            mbox.exec()

    def selEstado(self):
        if var.ui.rbtTodos.isChecked():
            estado = 0
            conexion.Conexion.selectDrivers(estado)
        elif var.ui.rbtAlta.isChecked():
            estado = 1
            conexion.Conexion.selectDrivers(estado)
        elif var.ui.rbtBaja.isChecked():
            estado = 2
            conexion.Conexion.selectDrivers(estado)
