from PyQt6 import QtWidgets, QtCore

import connection
import events
import var


class Bills():
    # Tabla 1
    def cargarTablaFacturas1(registros):
        # Añade los datos a la tabla Factura
        try:
            var.ui.tableFacturas1.clearContents()
            index = 0
            for registro in registros:
                var.ui.tableFacturas1.setRowCount(index + 1)
                var.ui.tableFacturas1.setItem(index, 0, QtWidgets.QTableWidgetItem(str(registro[0])))
                var.ui.tableFacturas1.setItem(index, 1, QtWidgets.QTableWidgetItem(str(registro[1])))
                var.ui.tableFacturas1.setItem(index, 2, QtWidgets.QTableWidgetItem(str(registro[2])))
                for col in range(var.ui.tableFacturas1.columnCount()):
                    var.ui.tableFacturas1.item(index, col).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                index += 1
        except Exception as error:
            print("Error al cargar datos en la tabla Facturas1: ", error)

    def cargarFactura1(self=None):
        try:
            row = var.ui.tableFacturas1.selectedItems()
            fila = [dato.text() for dato in row]
            registro = connection.Connection.onefactura1(fila[0])
            Bills.cargarDatos1(registro)
        except Exception as error:
            print("Error al cargar los datos de un client")

    def cargarDatos1(registro):
        try:
            datos = [var.ui.lblCodBD_3, var.ui.txtNombre_3, var.ui.txtAlta_3]
            var.ui.lblCodBD_3.setText(str(registro[0]))
            var.ui.txtNombre_3.setText(str(registro[1]))
            var.ui.txtAlta_3.setText(str(registro[2]))
        except Exception as error:
            print("Error al cargar datos en panel gestión 1: ", error)

    def cargarFecha(qDate):
        try:
            data = ('{:02d}/{:02d}/{:4d}'.format(qDate.day(), qDate.month(), qDate.year()))
            var.ui.txtAlta_3.setText(str(data))
            return data
            var.calendar.hide()
        except Exception as error:
            print("Error en cargar fecha: ", error)

    # Tabla 2
    def cargarTablaFacturas2(registros):
        # Añade los datos a la tabla Facturas2
        try:
            var.ui.tableFacturas2.clearContents()
            index = 0
            for registro in registros:
                var.ui.tableFacturas2.setRowCount(index + 1)
                var.ui.tableFacturas2.setItem(index, 0, QtWidgets.QTableWidgetItem(str(registro[0])))
                var.ui.tableFacturas2.setItem(index, 1, QtWidgets.QTableWidgetItem(str(registro[1])))
                var.ui.tableFacturas2.setItem(index, 2, QtWidgets.QTableWidgetItem(str(registro[2])))

                # Formatear el precio con dos decimales y el símbolo del euro para el registro 3
                precio = float(registro[3])
                precio_formateado = f"{precio:.2f}€"
                var.ui.tableFacturas2.setItem(index, 3, QtWidgets.QTableWidgetItem(precio_formateado))

                var.ui.tableFacturas2.setItem(index, 4, QtWidgets.QTableWidgetItem(str(registro[4])))
                var.ui.tableFacturas2.setItem(index, 5, QtWidgets.QTableWidgetItem(str(registro[5])))

                for col in range(var.ui.tableFacturas2.columnCount()):
                    var.ui.tableFacturas2.item(index, col).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                index += 1
        except Exception as error:
            print("Error al cargar datos en la tabla Facturas2: ", error)

    def cargarFactura2(self=None):
        try:
            row = var.ui.tableFacturas2.selectedItems()
            fila = [dato.text() for dato in row]
            registro1 = connection.Connection.onefactura2(fila[1])
            total_factura = connection.Connection.facturaTotal(fila[0])  # Obtener el total de la factura
            Bills.cargarDatos2(registro1, total_factura)
        except Exception as error:
            print("Error al cargar los datos de un cliente:", error)

    def cargarDatos2(registro1, total_factura):
        try:
            datos = [var.ui.lblCodBD_4, var.ui.txtProducto_4, var.ui.txtPrecio_4, var.ui.spinStock_4]
            var.ui.lblCodBD_4.setText(str(registro1[0]))
            var.ui.txtProducto_4.setText(str(registro1[1]))

            # Formatear el precio con dos decimales
            precio_4 = float(registro1[2])
            var.ui.txtPrecio_4.setText(f"{precio_4:.2f}")

            var.ui.spinStock_4.setValue(int(registro1[3]))

            subtotal = float(total_factura)
            iva = subtotal * 0.04
            total = subtotal + iva

            var.ui.txtSubtotal.setText(f"{subtotal:.2f}€")
            var.ui.txtIVA.setText(f"{iva:.2f}€")
            var.ui.txtTotalFactura.setText(f"{total:.2f}€")
        except Exception as error:
            print("Error al cargar datos en panel gestión 2:", error)
