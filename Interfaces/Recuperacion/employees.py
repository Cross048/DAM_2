from PyQt6 import QtWidgets, QtCore

import connection
import var


class Employees:
    def cargarTablaEmpleados(registros):
        # Añade los datos a la tabla Empleados
        try:
            var.ui.tableEmpleados.clearContents()
            index = 0
            for registro in registros:
                var.ui.tableEmpleados.setRowCount(index + 1)
                var.ui.tableEmpleados.setItem(index, 0, QtWidgets.QTableWidgetItem(str(registro[0])))
                var.ui.tableEmpleados.setItem(index, 1, QtWidgets.QTableWidgetItem(str(registro[1])))
                var.ui.tableEmpleados.setItem(index, 2, QtWidgets.QTableWidgetItem(str(registro[2])))
                var.ui.tableEmpleados.setItem(index, 3, QtWidgets.QTableWidgetItem(str(registro[3])))
                var.ui.tableEmpleados.setItem(index, 4, QtWidgets.QTableWidgetItem(str(registro[4])))
                for col in range(var.ui.tableEmpleados.columnCount()):
                    var.ui.tableEmpleados.item(index, col).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                index += 1
        except Exception as error:
            print("Error al cargar datos en la tabla Empleados: ", error)

    def cargarEmpleado(self=None):
        # Carga una fila y la prepara para cargarla en el formulario
        try:
            row = var.ui.tableEmpleados.selectedItems()
            fila = [dato.text() for dato in row]
            registro = connection.Connection.oneempleado(fila[0])
            Employees.cargarDatos(registro)
        except Exception as error:
            print("Error al cargar los datos de un empleado marcando en la tabla: ", error)

    def cargarDatos(registro):
        # Carga los datos del registro en el formulario
        try:
            var.ui.lblCodBD_5.setText(str(registro[0]))
            var.ui.txtNombre_5.setText(str(registro[1]))
            var.ui.cboxDepartamento.setCurrentText(str(registro[2]))
            var.ui.txtTelefono_5.setText(str(registro[3]))
            turno = str(registro[4])
            if turno == "Mañana":
                var.ui.rbtnManyana.setChecked(True)
                var.ui.rbtnTarde.setChecked(False)
            elif turno == "Tarde":
                var.ui.rbtnManyana.setChecked(False)
                var.ui.rbtnTarde.setChecked(True)
        except Exception as error:
            print("Error al cargar datos en panel gestión: ", error)