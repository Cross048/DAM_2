from PyQt6 import QtWidgets, QtCore

import connection
import var


class Products:
    def cargarTablaProductos(registros):
        # Añade los datos a la tabla Productos
        try:
            var.ui.tableProductos.clearContents()
            index = 0
            for registro in registros:
                # Formatea el precio con dos decimales y el símbolo del euro
                precio = float(registro[2])
                precio_formateado = f"{precio:.2f}€"

                var.ui.tableProductos.setRowCount(index + 1)
                var.ui.tableProductos.setItem(index, 0, QtWidgets.QTableWidgetItem(str(registro[0])))
                var.ui.tableProductos.setItem(index, 1, QtWidgets.QTableWidgetItem(str(registro[1])))
                var.ui.tableProductos.setItem(index, 2, QtWidgets.QTableWidgetItem(precio_formateado))
                var.ui.tableProductos.setItem(index, 3, QtWidgets.QTableWidgetItem(str(registro[3])))
                for col in range(var.ui.tableProductos.columnCount()):
                    var.ui.tableProductos.item(index, col).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                index += 1
        except Exception as error:
            print("Error al cargar datos en la tabla Productos: ", error)

    def cargarProducto(self=None):
        # Prepara una fila de la tabla para cargarla en el formulario
        try:
            row = var.ui.tableProductos.selectedItems()
            fila = [dato.text() for dato in row]
            registro = connection.Connection.oneproducto(fila[0])
            Products.cargarDatos(registro)
        except Exception as error:
            print("Error al cargar los datos de un cliente marcando en la tabla: ", error)

    def cargarDatos(registro):
        # Carga los datos de un producto en el formulario
        try:
            precio = float(registro[2]) # Formatea el precio con dos decimales

            var.ui.lblCodBD_2.setText(str(registro[0]))
            var.ui.txtNombre_2.setText(str(registro[1]))
            var.ui.txtPrecio.setText(f"{precio:.2f}")

            stock = registro[3]
            if stock is None:
                stock = 0

            var.ui.spinStock.setValue(int(stock))
        except Exception as error:
            print("Error al cargar datos en panel gestión: ", error)
