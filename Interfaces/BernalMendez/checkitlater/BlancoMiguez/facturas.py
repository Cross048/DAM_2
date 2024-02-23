
import conexion
import clientes
import eventos
import informes
import var

from PyQt6 import QtWidgets, QtCore, QtGui

class Facturas:

    def limpiarPanel3(self=None):
        """
        Limpia los widgets del panel de facturación.

        Se encarga de resetear los valores de los widgets relacionados con la facturación.
        """
        try:
            listawidgets=[var.ui.lblcodfacturacion, var.ui.txtcifcli, var.ui.txtfechafact, var.ui.txtkm, var.ui.lblsubtotal, var.ui.lbliva, var.ui.lbltotalfactura]
            for i in listawidgets:
                i.setText(None)
            var.ui.cmbCond.setCurrentText('')
            var.ui.cmbProbVentas.setCurrentText('')
            var.ui.cmbProbVentas2.setCurrentText('')
            var.ui.cmbMuniVentas.setCurrentText('')
            var.ui.cmbMuniVentas2.setCurrentText('')
        except Exception as error:
            print(str(error) + " en limpiarpanel 3")
    def validarKm(self=None):
        """
        Valida el campo de kilómetros en la facturación.

        Verifica que el campo de kilómetros contenga solo dígitos.
        """
        try:
            km = var.ui.txtkm.text()
            numeros = "1234567890"
            var.ui.txtkm.setText(km)  # Corrección aquí
            if len(km):
                if len(km) != len([n for n in km if n in numeros]):
                    raise Exception
            else:
                raise Exception
        except Exception as error:
            eventos.Eventos.error("Aviso", "Los km deben ser de enteros")
            var.ui.txtkm.setText("")

    def buscarClifact(self):
        """
        Busca un cliente para asociarlo a una factura.

        Obtiene el DNI del cliente ingresado y busca su código en la base de datos de clientes.
        """
        try:
            dni = var.ui.txtcifcli.text()
            registro = conexion.Conexion.codCli(dni)
            clientes.Clientes.auxiliar(registro)
            codigo = var.ui.lblcodbd2.text()
            var.ui.rbtTodos2.setChecked(True)
            conexion.Conexion.mostrarClientes()
            clientes.Clientes.colorearFila(codigo)
        except Exception as error:
            print(str(error) + " en cargarcliente clientes")

    def cargaFechafact(qDate):
        """
        Carga la fecha seleccionada en el widget de fecha de factura.

        Convierte la fecha seleccionada en el widget de fecha de factura y la carga en el campo correspondiente.
        """
        try:
            data=('{:02d}/{:02d}/{:4d}'.format(qDate.day(),qDate.month(),qDate.year()))
            var.ui.txtfechafact.setText(str(data))
            var.Altafact.hide()
        except Exception as error:
            print(str(error) + " en cargarfecha fact driver")

    def altafactura(self):
        """
        Realiza el alta de una factura.

        Obtiene los datos ingresados en los widgets y realiza el alta de la factura en la base de datos.
        """
        try:
            registro=[var.ui.txtcifcli.text(), var.ui.txtfechafact.text(), var.ui.cmbCond.currentText().split('.')[0]]
            conexion.Conexion.altafacturacion(registro)
        except Exception as error:
            print("error altafact",error)

    def cargarTablaFacturas(registros):
        """
        Carga la tabla de facturas con los registros obtenidos de la base de datos.

        Llena la tabla de facturas con los registros obtenidos de la base de datos.
        """
        try:
            index = 0
            for registro in registros:
                var.ui.tablaFacturas.setRowCount(index + 1)
                var.ui.tablaFacturas.setItem(index, 0, QtWidgets.QTableWidgetItem(str(registro[0])))
                var.ui.tablaFacturas.setItem(index, 1, QtWidgets.QTableWidgetItem(str(registro[1])))
                var.ui.tablaFacturas.item(index, 0).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                var.ui.tablaFacturas.item(index, 1).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                btn = QtWidgets.QPushButton()
                btn.setFixedSize(30, 28)
                btn.setIcon(QtGui.QIcon('./img/8medical-report_102120.ico'))
                btn.clicked.connect(informes.Informes.reportfactura)
                var.ui.tablaFacturas.setCellWidget(index, 2, btn)
                index += 1

        except Exception as error:
            print("error en cargarTablafacturas", error)

    def cargarFactura(self):
        """
        Carga los datos de una factura seleccionada en los widgets correspondientes.

        Obtiene los datos de la factura seleccionada en la tabla y los carga en los widgets correspondientes.
        """
        try:
            listawidgets = [var.ui.lblsubtotal, var.ui.lbliva, var.ui.lbltotalfactura]
            for i in listawidgets:
                i.setText(None)
            Facturas.limpiarPanel3(self)
            row = var.ui.tablaFacturas.selectedItems()
            fila = [dato.text() for dato in row]
            registro = conexion.Conexion.oneFactura(fila[0])
            Facturas.auxiliar(registro)
            conexion.Conexion.cargarfacturasClientes()
            Facturas.colorearFila(registro[0])
            conexion.Conexion.viajesFactura(registro[0])
        except Exception as error:
            print(str(error) + " en cargarfact")

    def auxiliar(registro):
        """
        Carga los datos de un conductor asociado a una factura en los widgets correspondientes.

        Obtiene los datos del conductor asociado a la factura y carga dichos datos en los widgets.
        """
        try:
            registro2 = conexion.Conexion.oneDriver(registro[3])
            driver = str(registro2[0]) + "." + registro2[3]
            datos = [var.ui.lblcodfacturacion, var.ui.txtcifcli, var.ui.txtfechafact, var.ui.cmbCond]
            for i, dato in enumerate(datos):
                if i == 3:
                    dato.setCurrentText(str(driver))
                else:
                    dato.setText(str(registro[i]))
        except Exception as error:
            eventos.Eventos.error("Aviso", "No existe en la base de datos")

    def colorearFila(numfactura):
        """
        Colorea la fila de la tabla de facturas correspondiente al número de factura.

        Colorea la fila de la tabla de facturas correspondiente al número de factura proporcionado.
        """
        for fila in range(var.ui.tablaFacturas.rowCount()):
            if var.ui.tablaFacturas.item(fila, 0).text() == str(numfactura):
                for columna in range(var.ui.tablaFacturas.columnCount()):
                    item = var.ui.tablaFacturas.item(fila, columna)
                    if item is not None:
                        item.setBackground(QtGui.QColor(255, 241, 150))

    def comprobarTarifa(self=None):
        """
        Comprueba la tarifa seleccionada en función de la provincia y localidad de origen y destino.

        Comprueba la tarifa seleccionada en función de la provincia y localidad de origen y destino,
        y marca los botones de radio correspondientes.
        """
        try:
            provinciaOrigen = var.ui.cmbProbVentas.currentText()
            provinciaDestino = var.ui.cmbProbVentas2.currentText()
            localidadOrigen = var.ui.cmbMuniVentas.currentText()
            localidadDestino = var.ui.cmbMuniVentas2.currentText()

            if provinciaOrigen == provinciaDestino:
                var.ui.rbtProvincial.setChecked(True)
                if localidadOrigen == localidadDestino:
                    var.ui.rbtLocal.setChecked(True)
            else:
                var.ui.rbtNacional.setChecked(True)
        except Exception as error:
            print(error)

    def cargaTablaViajes(valores):
        """
        Carga la tabla de viajes con los registros obtenidos de la base de datos.

        Llena la tabla de viajes con los registros obtenidos de la base de datos,
        calculando el subtotal, el IVA y el total de la factura.
        """
        try:
            var.ui.tabViajes.clearContents()
            var.ui.tabViajes.setRowCount(0)
            subtotal=0.0
            index = 0
            for registro in valores:
                var.ui.tabViajes.setRowCount(index + 1)
                var.ui.tabViajes.setItem(index, 0, QtWidgets.QTableWidgetItem(str(registro[0])))
                var.ui.tabViajes.setItem(index, 1, QtWidgets.QTableWidgetItem(str(registro[1])))
                var.ui.tabViajes.setItem(index, 2, QtWidgets.QTableWidgetItem(str(registro[2])))
                var.ui.tabViajes.setItem(index, 3, QtWidgets.QTableWidgetItem(str(registro[3])))
                var.ui.tabViajes.setItem(index, 4, QtWidgets.QTableWidgetItem(str(registro[4])))

                totalViaje = round(float(registro[4]) * float(registro[3]), 2)
                subtotal = subtotal + totalViaje
                iva=subtotal*0.21
                var.ui.lblsubtotal.setText(str('{:.2f}'.format(round(subtotal, 2))) + " €")
                var.ui.lbliva.setText(str('{:.2f}'.format(round(iva, 2))) + " €")
                var.ui.lbltotalfactura.setText(str('{:.2f}'.format(round(subtotal + iva, 2))) + " €")
                var.ui.tabViajes.setItem(index, 5, QtWidgets.QTableWidgetItem(str('{:.2f}'.format(totalViaje))))
                var.ui.tabViajes.item(index, 0).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                var.ui.tabViajes.item(index, 1).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                var.ui.tabViajes.item(index, 2).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                var.ui.tabViajes.item(index, 3).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                var.ui.tabViajes.item(index, 4).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                var.ui.tabViajes.item(index, 5).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                btn_borrar = QtWidgets.QPushButton()
                btn_borrar.setFixedSize(30, 28)
                btn_borrar.setIcon(QtGui.QIcon('./img/basura.png'))
                btn_borrar.clicked.connect(Facturas.borrarViaje)
                var.ui.tabViajes.horizontalHeader().setSectionResizeMode(6, QtWidgets.QHeaderView.ResizeMode.ResizeToContents)
                var.ui.tabViajes.setColumnWidth(6, 50)
                var.ui.tabViajes.setCellWidget(index, 6, btn_borrar)
                index += 1
        except Exception as error:
            print(str(error) + " en cargatablviajes facturas")



    def guardarViaje(self):
        """
        Guarda un nuevo viaje en la base de datos y actualiza la tabla de viajes y los totales de la factura.

        Guarda un nuevo viaje en la base de datos y actualiza la tabla de viajes y los totales de la factura.
        """
        try:
            if var.ui.lblcodfacturacion.text():
                if var.ui.txtkm.text() and str(var.ui.cmbMuniVentas.currentText()) != "" and str(var.ui.cmbMuniVentas2.currentText()) != "":
                    tarifa = '0.80'
                    if (var.ui.rbtLocal.isChecked()):
                        tarifa = '0.20'
                    elif(var.ui.rbtProvincial.isChecked()):
                        tarifa = '0.40'

                    viaje = [str(var.ui.lblcodfacturacion.text()), str(var.ui.cmbProbVentas.currentText()),
                             str(var.ui.cmbMuniVentas.currentText()), str(var.ui.cmbProbVentas2.currentText()),
                             str(var.ui.cmbMuniVentas2.currentText()), tarifa, str(var.ui.txtkm.text())]
                    conexion.Conexion.guardarViaje(viaje)
                    conexion.Conexion.viajesFactura(var.ui.lblcodfacturacion.text())
                else:
                    eventos.Eventos.error('Aviso',"Faltan campos obligatorios")
            else:
                eventos.Eventos.error('Aviso',"Selecciona primero una factura")

        except Exception as error:
            print(error)

    def cargarViaje(self):
        """
        Carga los datos de un viaje seleccionado en los widgets correspondientes.

        Obtiene los datos del viaje seleccionado en la tabla y los carga en los widgets correspondientes.
        """
        try:
            row = var.ui.tabViajes.selectedItems()
            fila = [dato.text() for dato in row]
            registro = conexion.Conexion.oneViajes(fila[0])

            provinciaOrigen, localidadOrigen = registro[2].split(" - ")
            provinciaDestino, localidadDestino = registro[3].split(" - ")

            var.ui.cmbProbVentas.setCurrentText(provinciaOrigen)
            var.ui.cmbMuniVentas.setCurrentText(localidadOrigen)
            var.ui.cmbProbVentas2.setCurrentText(provinciaDestino)
            var.ui.cmbMuniVentas2.setCurrentText(localidadDestino)

            var.ui.txtkm.setText(registro[5])

        except Exception as error:
            print(str(error) + " en cargarviaje")

    def borrarViaje(self):
        """
        Borra un viaje seleccionado y actualiza la tabla de viajes y los totales de la factura.

        Borra un viaje seleccionado y actualiza la tabla de viajes y los totales de la factura.
        """
        try:
            row = var.ui.tabViajes.selectedItems()
            fila = [dato.text() for dato in row]
            conexion.Conexion.borrarViaje(fila[0])
            conexion.Conexion.viajesFactura(var.ui.lblcodfacturacion.text())

        except Exception as error:
            print(error)

    def modifViaje(self):
        """
        Modifica la información de un viaje existente.

        Obtiene los datos del viaje desde los campos de la interfaz y actualiza la información en la base de datos.

        :raises Exception: Captura cualquier excepción que ocurra durante la ejecución del método.
        """
        try:
            if var.ui.lblcodfacturacion.text():
                if var.ui.txtkm.text() and str(var.ui.cmbMuniVentas.currentText()) != "" and str(
                        var.ui.cmbMuniVentas2.currentText()) != "":
                    tarifa = '0.80'
                    if (var.ui.rbtLocal.isChecked()):
                        tarifa = '0.20'
                    elif (var.ui.rbtProvincial.isChecked()):
                        tarifa = '0.40'
                    row = var.ui.tabViajes.selectedItems()
                    fila = [dato.text() for dato in row]
                    if fila:
                        id_viaje = fila[0]
                    km = var.ui.txtkm.text().title()

                    provincia_origen = var.ui.cmbProbVentas.currentText()
                    localidad_origen = var.ui.cmbMuniVentas.currentText()
                    provincia_destino = var.ui.cmbProbVentas2.currentText()
                    localidad_destino = var.ui.cmbMuniVentas2.currentText()

                    nuevo_origen = f"{provincia_origen} - {localidad_origen}"
                    nuevo_destino = f"{provincia_destino} - {localidad_destino}"
                    if conexion.Conexion.updateViaje(id_viaje, nuevo_origen, nuevo_destino, tarifa , km):
                        conexion.Conexion.viajesFactura(var.ui.lblcodfacturacion.text())
                        eventos.Eventos.mensaje("Aviso", "Viaje actualizado correctamente")
                    else:
                        eventos.Eventos.error("Error", "No se pudo actualizar el viaje")
                else:
                    eventos.Eventos.error('Aviso',"Faltan campos obligatorios")
            else:
                eventos.Eventos.error('Aviso',"Selecciona primero una factura")
        except Exception as error:
            print(error, " en modifViaje")

    def limpiarViajes(self=None):
        """
        Limpia los widgets del panel de facturación.

        Se encarga de resetear los valores de los widgets relacionados con la facturación.
        """
        try:
            listawidgets=[var.ui.txtkm]
            for i in listawidgets:
                i.setText(None)
            var.ui.cmbProbVentas.setCurrentText('')
            var.ui.cmbProbVentas2.setCurrentText('')
            var.ui.cmbMuniVentas.setCurrentText('')
            var.ui.cmbMuniVentas2.setCurrentText('')
        except Exception as error:
            print(str(error) + " en limpiarpanel 3")

