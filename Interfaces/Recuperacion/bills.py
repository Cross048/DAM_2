from PyQt6 import QtWidgets, QtCore

import connection
import events
import var


class Bills():
    def cargarTablaFacturas1(registros):
        # Añade los datos a la tabla Factura
        try:
            var.ui.tableFacturas1.clearContents()
            index = 0
            for registro in registros:
                var.ui.tableProductos.setRowCount(index + 1)
                var.ui.tableProductos.setItem(index, 0, QtWidgets.QTableWidgetItem(str(registro[0])))
                var.ui.tableProductos.setItem(index, 1, QtWidgets.QTableWidgetItem(str(registro[1])))
                var.ui.tableProductos.setItem(index, 2, QtWidgets.QTableWidgetItem(str(registro[2])))
                for col in range(var.ui.tableProductos.columnCount()):
                    var.ui.tableProductos.item(index, col).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                index += 1
        except Exception as error:
            print("Error al cargar datos en la tabla: ", error)

    def cargarTablaFacturas2(registros):
        # Añade los datos a la tabla Detalle
        try:
            var.ui.tableFacturas2.clearContents()
            index = 0
            for registro in registros:
                var.ui.tableProductos.setRowCount(index + 1)
                var.ui.tableProductos.setItem(index, 0, QtWidgets.QTableWidgetItem(str(registro[0])))
                var.ui.tableProductos.setItem(index, 1, QtWidgets.QTableWidgetItem(str(registro[1])))
                var.ui.tableProductos.setItem(index, 2, QtWidgets.QTableWidgetItem(str(registro[2])))
                for col in range(var.ui.tableProductos.columnCount()):
                    var.ui.tableProductos.item(index, col).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                index += 1
        except Exception as error:
            print("Error al cargar datos en la tabla: ", error)

    '''
    def cargarDatos(registro):
        try:
            datos = [var.ui.lblCodBD_2, var.ui.txtNombre_2, var.ui.txtPrecio, var.ui.spinStock]
            var.ui.lblCodBD_2.setText(str(registro[0]))
            var.ui.txtNombre_2.setText(str(registro[1]))
            var.ui.txtPrecio.setText(str(registro[2]))
            var.ui.spinStock.setValue(int(registro[3]))
        except Exception as error:
            print("Error al cargar datos en panel gestión: ", error)

    def auxiliar(registro):
        # Rellena los campos del panel de clientes con los datos del cliente seleccionado.
        try:
            datos = [var.ui.lblCodBD_2, var.ui.txtNombre_2, var.ui.txtPrecio, var.ui.spinStock]
            var.ui.lblCodBD_2.setText(str(registro[0]))
            var.ui.txtNombre_2.setText(str(registro[1]))
            var.ui.txtPrecio.setText(str(registro[2]))
            var.ui.spinStock.setText(str(registro[3]))
        except Exception as error:
            events.Events.error("Aviso", "No existe en la base de datos")

    def cargarProducto(self=None):
        try:
            row = var.ui.tableProductos.selectedItems()
            fila = [dato.text() for dato in row]
            registro = connection.Connection.oneproducto(fila[0])
            Products.cargarDatos(registro)
        except Exception as error:
            print("Error al cargar los datos de un cliente marcando en la tabla: ", error)
    '''