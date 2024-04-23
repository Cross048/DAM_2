import var
from PyQt6 import QtSql, QtWidgets, QtCore


class Clients():
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
            print("Error al cargar datos en la tabla: ", error)