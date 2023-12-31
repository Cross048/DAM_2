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
            print("Error al limpiar panel driver: ", error)

    def cargaFecha(qDate):
        try:
            data = ('{:02d}/{:02d}/{:4d}'.format(qDate.day(), qDate.month(), qDate.year()))
            var.ui.txtDatadriver.setText(str(data))
            return data
            var.calendar.hide()
        except Exception as error:
            print("Error en cargar fecha: ", error)

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
                    var.ui.lblValidardni.setStyleSheet('color:green;')
                    var.ui.lblValidardni.setText('V')
                    return True
                else:
                    var.ui.lblValidardni.setStyleSheet('color:red;')
                    var.ui.lblValidardni.setText('X')
                    var.ui.txtDni.setText(None)
                    var.ui.txtDni.setFocus()
            else:
                var.ui.lblValidardni.setStyleSheet('color:red;')
                var.ui.lblValidardni.setText('X')
                var.ui.txtDni.setText(None)
                var.ui.txtDni.setFocus()

        except Exception as error:
            print("Error al validar DNI: ", error)

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
                mbox.setWindowTitle("Aviso")
                mbox.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
                mbox.setIcon(QtWidgets.QMessageBox.Icon.Information)
                mbox.setText("Empleado dado de alta")
                mbox.exec()
            elif valor == False:
                mbox = QtWidgets.QMessageBox()
                mbox.setWindowTitle("Aviso")
                mbox.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
                mbox.setIcon(QtWidgets.QMessageBox.Icon.Warning)
                mbox.setText("Asegúrese de que el conductor no existe")
                mbox.exec()
        except Exception as error:
            print("Error al dar de alta al cliente: ", error)

    def cargartabladri(registros):
        try:
            var.ui.tabDrivers.clearContents()
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
                var.ui.tabDrivers.item(index, 5).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                index += 1
        except Exception as error:
            print("Error al cargar datos en la tabla: ", error)

    def cargadriver(self = None):
        try:
            fila = var.ui.tabDrivers.selectedItems()
            row = [dato.text() for dato in fila]
            registro = conexion.Conexion.onedriver(row[0])
            Drivers.cargardatos(registro)
        except Exception as error:
            print("Error al cargar los datos de un cliente marcando en la tabla: ", error)

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
            print(error, " en busca de datos de un conductor")

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
        except Exception as error:
            print("Error al cargar datos en panel gestión: ", error)

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
            print("Error al modificar driver en Drivers", error)

    def borraDriv(self):
        try:
            dni = var.ui.txtDni.text()
            conexion.Conexion.borraDriv(dni)
            conexion.Conexion.selectDrivers(1)
        except Exception as error:
            mbox = QtWidgets.QMessageBox()
            mbox.setWindowTitle("Aviso")
            mbox.setIcon(QtWidgets.QMessageBox.Icon.Warning)
            mbox.setText("El conductor no existe o no se puede borrar")
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

    # Examen

    @staticmethod
    def limpiapanel2(self):
        try:
            listawidgets = [var.ui.lblcodbd_2, var.ui.txtRazonSocial, var.ui.txtMovil_2,
                            var.ui.txtDirdriver_2, var.ui.lblValidardni_2]
            for i in listawidgets:
                i.setText(None)
            var.ui.cmbProv_2.setCurrentText('')
            var.ui.cmbMuni_2.setCurrentText('')
            if var.ui.rbtAlta_2.isChecked():
                estado = 1
                conexion.Conexion.selectDrivers2(estado)
            else:
                registros = conexion.Conexion.mostrardrivers2(self)
                Drivers.cargartablaclientes(registros)
        except Exception as error:
            print("Error al limpiar panel driver: ", error)

    def cargardatos2(registro):
        try:
            datos = [var.ui.lblcodbd_2, var.ui.txtDni_2, var.ui.txtRazonSocial,
                     var.ui.txtDirdriver_2, var.ui.cmbProv_2, var.ui.cmbMuni_2, var.ui.txtMovil_2]
            for i, dato in enumerate(datos):
                if i == 6 or i == 7:
                    dato.setCurrentText(str(registro[i]))
                else:
                    dato.setText(str(registro[i]))
        except Exception as error:
            print("Error al cargar datos en panel gestión: ", error)

    def cargadriver2(self = None):
        try:
            fila = var.ui.tabDrivers_2.selectedItems()
            row = [dato.text() for dato in fila]
            registro = conexion.Conexion.onedriver2(row[0])
            Drivers.cargardatos2(registro)
        except Exception as error:
            print("Error al cargar los datos de un cliente marcando en la tabla: ", error)

    def cargartablaclientes(registros):
        try:
            var.ui.tabDrivers_2.clearContents()
            index = 0
            for registro in registros:
                var.ui.tabDrivers_2.setRowCount(index + 1)
                var.ui.tabDrivers_2.setItem(index, 0, QtWidgets.QTableWidgetItem(str(registro[0])))
                var.ui.tabDrivers_2.setItem(index, 1, QtWidgets.QTableWidgetItem(str(registro[1])))
                var.ui.tabDrivers_2.setItem(index, 2, QtWidgets.QTableWidgetItem(str(registro[2])))
                var.ui.tabDrivers_2.setItem(index, 3, QtWidgets.QTableWidgetItem(str(registro[3])))
                var.ui.tabDrivers_2.item(index, 0).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                var.ui.tabDrivers_2.item(index, 1).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                var.ui.tabDrivers_2.item(index, 2).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                var.ui.tabDrivers_2.item(index, 3).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                index += 1
        except Exception as error:
            print("Error al cargar datos en la tabla: ", error)

    def selEstado2(self):
        if var.ui.rbtTodos_2.isChecked():
            estado = 0
            conexion.Conexion.selectDrivers2(estado)
        elif var.ui.rbtAlta_2.isChecked():
            estado = 1
            conexion.Conexion.selectDrivers2(estado)
        elif var.ui.rbtBaja_2.isChecked():
            estado = 2
            conexion.Conexion.selectDrivers2(estado)