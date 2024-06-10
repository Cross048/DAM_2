from PyQt6 import QtWidgets, QtCore

import connection
import var


class Clients:
    def cargarTablaClientes(registros):
        # Añade los datos a la tabla Clientes
        try:
            var.ui.tableClientes.clearContents()
            index = 0
            for registro in registros:
                var.ui.tableClientes.setRowCount(index + 1)
                var.ui.tableClientes.setItem(index, 0, QtWidgets.QTableWidgetItem(str(registro[0])))
                var.ui.tableClientes.setItem(index, 1, QtWidgets.QTableWidgetItem(str(registro[1])))
                var.ui.tableClientes.setItem(index, 2, QtWidgets.QTableWidgetItem(str(registro[2])))
                var.ui.tableClientes.setItem(index, 3, QtWidgets.QTableWidgetItem(str(registro[3])))
                var.ui.tableClientes.setItem(index, 4, QtWidgets.QTableWidgetItem(str(registro[4])))
                var.ui.tableClientes.setItem(index, 5, QtWidgets.QTableWidgetItem(str(registro[5])))
                var.ui.tableClientes.setItem(index, 6, QtWidgets.QTableWidgetItem(str(registro[6])))
                for col in range(var.ui.tableClientes.columnCount()):
                    var.ui.tableClientes.item(index, col).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                index += 1
        except Exception as error:
            print("Error al cargar datos en la tabla Clientes: ", error)

    def cargarFecha(qDate):
        # Carga la fecha con el formato adecuado
        try:
            data = ('{:02d}/{:02d}/{:4d}'.format(qDate.day(), qDate.month(), qDate.year()))
            var.ui.txtData.setText(str(data))
            return data
            var.calendar.hide()
        except Exception as error:
            print("Error en cargar fecha: ", error)

    def cargarCliente(self=None):
        # Carga una fila y la prepara para cargarla en el formulario
        try:
            row = var.ui.tableClientes.selectedItems()
            fila = [dato.text() for dato in row]
            registro = connection.Connection.onecliente(fila[0])
            Clients.cargarDatos(registro)
        except Exception as error:
            print("Error al cargar los datos de un cliente marcando en la tabla: ", error)

    def cargarDatos(registro):
        # Carga los datos del registro en el formulario
        try:
            var.ui.lblCodBD.setText(str(registro[0]))
            var.ui.txtDNI.setText(str(registro[1]))
            var.ui.txtNombre.setText(str(registro[2]))
            var.ui.txtApel.setText(str(registro[3]))
            var.ui.txtDir.setText(str(registro[4]))
            var.ui.txtData.setText(str(registro[5]))
            var.ui.txtMovil.setText(str(registro[6]))
            var.ui.txtEmail.setText(str(registro[7]))
            categoria = str(registro[8])
            if categoria == "Particular":
                var.ui.rbtnParticular.setChecked(True)
                var.ui.rbtnEmpresa.setChecked(False)
            elif categoria == "Empresa":
                var.ui.rbtnParticular.setChecked(False)
                var.ui.rbtnEmpresa.setChecked(True)
        except Exception as error:
            print("Error al cargar datos en panel gestión: ", error)
