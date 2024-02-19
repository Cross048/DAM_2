from PyQt6 import QtWidgets, QtCore

import conexion
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
            fila = var.ui.tabDrivers_2.selectedItems()
            row = [dato.text() for dato in fila]
            registro = conexion.Conexion.onecliente(row[0])
            Clientes.cargardatos(registro)
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