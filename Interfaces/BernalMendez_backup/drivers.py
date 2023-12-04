import conexion
import var
from PyQt6 import QtCore, QtWidgets

class Drivers():
    def limpiapanel(self):
        try:
            # TODO: completar listawidgets
            listawidgets = [var.ui.labelCodigo, var.ui.lineDNI]
            for i in listawidgets:
                i.setText(None)
            # TODO: completar checkLicencia
            checkLicencia = []
            for i in checkLicencia:
                i.setChecked(False)
            var.ui.comboBoxProvincia.setCurrentText('')
            var.ui.comboBoxLocalidad.setCurrentText('')
        except Exception as error:
            print('error al limpiar panel ', error)

    def validarDNI(self):
        try:
            dni = var.ui.lineDNI.text()
            dni = dni.upper()
            var.ui.lineDNI.setText(dni)
            tabla = "TRWAGMYFPDXBNJZSQVHLCKE"
            dig_ext = "XYZ"
            reemp_dig_ext = {'X':'0', 'Y':'1', 'Z':'2'}
            numeros = "1234567890"
            if len(dni) == 9:          # Comprueba que son nueve
                dig_control = dni[8]   # Tomo la letra del DNI
                dni = dni[:8]          # Tomo los números del DNI
                if dni[0] in dig_ext:
                    dni = dni.replace(dni[0], reemp_dig_ext[dni[0]])
                if len(dni) == len([n for n in dni if n in numeros]) and tabla[int(dni) % 23] == dig_control:
                    var.ui.labelDNI.setStyleSheet('color:green;')
                    var.ui.labelDNI.setText('V')
                else:
                    var.ui.labelDNI.setStyleSheet('color:red;')
                    var.ui.labelDNI.setText('F')
                    var.ui.lineDNI.setText(None)
                    var.ui.lineDNI.setFocus()
            else:
                var.ui.labelValidarDNI.setStyleSheet('color:red;')
                var.ui.lavelValidarDNI.setText('F')
                var.ui.lineDNI.setText(None)
                var.ui.lineDNI.setFocus()
        except Exception as error:
            print("error en validar DNI", error)

    def altadriver(self):
        try:
            driver = [var.ui.lineDNI, var.ui.lineFechaAlta, var.ui.lineApellidos, var.ui.lineNombre,
                      var.ui.lineDireccion, var.ui.lineMovil, var.ui.lineSalario]
            newdriver = []
            newdriver.append(1)
            for i in driver:
                newdriver.append(i.text().title)
            licencias = []
            checkBoxLicencia = [var.ui.checkBoxA, var.ui.checkBoxB, var.ui.checkBoxC, var.ui.checkBoxD]
            for i in checkBoxLicencia:
                if i.isChecked():
                    licencias.append(i.text())
            newdriver.append('-'.join(licencias))
            index = 0
            var.ui.tabDrivers.setRowCount(index + 1)   # Crea una fila
            var.ui.tabDrivers.setItem(index, 0, QtWidgets.QTableWidgetItem(str(newdriver[0])))
            var.ui.tabDrivers.setItem(index, 1, QtWidgets.QTableWidgetItem(str(newdriver[1])))
            var.ui.tabDrivers.setItem(index, 2, QtWidgets.QTableWidgetItem(str(newdriver[2])))
            var.ui.tabDrivers.setItem(index, 3, QtWidgets.QTableWidgetItem(str(newdriver[3])))
            var.ui.tabDrivers.setItem(index, 4, QtWidgets.QTableWidgetItem(str(newdriver[4])))
            var.ui.tabDrivers.item(index, 0).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
            var.ui.tabDrivers.item(index, 3).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
            var.ui.tabDrivers.item(index, 4).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)

        except Exception as error:
            print('error alta cliente ', error)

    def cargardriver(self):
        try:
            Drivers.limpiapanel()
            row = var.ui.tabClientes.selectedItems()
            fila = [ dato.text() for dato in row ]
            registro = conexion.Conexion.onedriver(fila[0])
            print(registro)
            datos = [var.ui.lineCodigo, var.ui.lineDNI, var.ui.lineFechaAlta, var.ui.lineApellidos, var.ui.lineNombre,
                     var.ui.lineDireccion, var.ui.comboBoxProvincia, var.ui.comboBoxLocalidad,
                     var.ui.lineMovil, var.ui.lineSalario]
            for i, dato in enumerate(datos):
                if 1 == 6 or i == 7:
                    dato.setCurrentText(str(registro[i]))
                else:
                    dato.setText(str(registro[i]))
            if 'A' in registro[10]:
                var.ui.checkBoxA.setChecked(True)
            if 'B' in registro[10]:
                var.ui.checkBoxB.setChecked(True)
            if 'C' in registro[10]:
                var.ui.checkBoxC.setChecked(True)
            if 'D' in registro[10]:
                var.ui.checkBoxD.setChecked(True)
        except Exception as error:
            print('Error cargar datos de 1 cliente marcando en la tabla: ', error)

    def buscaDri(self):
        try:
            dni = var.ui.lineDNI.text()
            conexion.Conexion.codDri(dni)

        except Exception as error:
            print('error al buscar driver ', error)