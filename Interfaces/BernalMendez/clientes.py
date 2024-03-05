from PyQt6 import QtWidgets, QtCore

import conexion
import eventos
import var


class Clientes():
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
                conexion.Conexion.selectClientes(estado)
            else:
                registros = conexion.Conexion.mostrarclientes(self)
                Clientes.cargartablaclientes(registros)
        except Exception as error:
            print("Error al limpiar panel driver: ", error)

    def cargardatos(registro):
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

    def cargacliente(self=None):
        try:
            row = var.ui.tabDrivers_2.selectedItems()
            fila = [dato.text() for dato in row]
            registro = conexion.Conexion.onecliente(fila[0])
            Clientes.auxiliar(registro)
            conexion.Conexion.mostrarclientes()
            Clientes.cargardatos(registro)
        except Exception as error:
            print("Error al cargar los datos de un cliente marcando en la tabla: ", error)

    def auxiliar(registro):
        """
        Rellena los campos del panel de clientes con los datos del cliente seleccionado.
        """
        try:
            datos = [var.ui.lblcodbd_2, var.ui.txtDni_2, var.ui.txtRazonSocial, var.ui.txtDirdriver_2, var.ui.txtMovil_2,
                     var.ui.cmbProv_2, var.ui.cmbMuni_2]
            for i, dato in enumerate(datos):
                if i == 5 or i == 6:
                    dato.setCurrentText(str(registro[i]))
                else:
                    dato.setText(str(registro[i]))
        except Exception as error:
            eventos.Eventos.error("Aviso", "No existe en la base de datos")

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
                var.ui.tabDrivers_2.item(index, 2).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                var.ui.tabDrivers_2.item(index, 3).setTextAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
                index += 1
        except Exception as error:
            print("Error al cargar datos en la tabla: ", error)

    def selEstado(self):
        if var.ui.rbtTodos_2.isChecked():
            estado = 0
            conexion.Conexion.selectClientes(estado)
        elif var.ui.rbtAlta_2.isChecked():
            estado = 1
            conexion.Conexion.selectClientes(estado)
        elif var.ui.rbtBaja_2.isChecked():
            estado = 2
            conexion.Conexion.selectClientes(estado)

    def altaCliente(self):
        try:
            dni = var.ui.txtDNI2.text()
            Clientes.limpiapanel2(self)
            conexion.Conexion.mostrarclientes(self)

            if not all([var.ui.txtDni_2.text(), var.ui.txtRazonSocial.text(), var.ui.txtMovil_2.text()]):
                eventos.Eventos.mensaje("Aviso", "Faltan datos obligatorios")
                return
            cliente = [
                var.ui.txtDni_2, var.ui.txtRazonSocial, var.ui.txtDirdriver_2, var.ui.txtMovil_2
            ]
            newCliente = []
            for i in cliente:
                newCliente.append(i.text().title())

            prov = var.ui.cmbProv_2.currentText()
            newCliente.insert(3, prov)
            muni = var.ui.cmbMuni_2.currentText()
            newCliente.insert(4, muni)
            valor=conexion.Conexion.guardarcli(newCliente)
            if valor==True:
                eventos.Eventos.mensaje("Aviso", "El cliente ha sido añadido con exito")
                conexion.Conexion.mostrarclientes()
            elif valor == False:
                eventos.Eventos.error("Aviso", "No se ha podido dar de alta al cliente")

        except Exception as error:
            print(str(error) + " en dar de alta al Cliente.")

    def modifCliente(self):
        try:
            driver=[var.ui.lblcodbd_2,var.ui.txtDni_2, var.ui.txtRazonSocial, var.ui.txtDirdriver_2, var.ui.txtMovil_2]
            modifCliente=[]
            for i in driver:
                modifCliente.append(i.text().title())
            prov = var.ui.cmbProv_2.currentText()
            modifCliente.insert(4,prov)
            muni = var.ui.cmbMuni_2.currentText()
            modifCliente.insert(5,muni)
            conexion.Conexion.modifCliente(modifCliente)
        except Exception as error:
            print(error, " al modificar el Cliente.")