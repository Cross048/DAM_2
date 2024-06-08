import sys

from PyQt6 import QtWidgets, QtGui

import var


class Events:
    def salir(self=None):
        # Ventana de emergencia para confirmar salida del programa
        mbox = QtWidgets.QMessageBox()
        mbox.setWindowTitle("Confirmar Salida")
        mbox.setIcon(QtWidgets.QMessageBox.Icon.Information)
        mbox.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
        mbox.setText("¿Está seguro de que desea salir?")
        mbox.setStandardButtons(QtWidgets.QMessageBox.StandardButton.Yes | QtWidgets.QMessageBox.StandardButton.No)
        mbox.button(QtWidgets.QMessageBox.StandardButton.Yes).setText("Si")
        mbox.button(QtWidgets.QMessageBox.StandardButton.No).setText("No")
        mbox.setDefaultButton(QtWidgets.QMessageBox.StandardButton.Yes)
        mbox.setDefaultButton(QtWidgets.QMessageBox.StandardButton.No)
        if mbox.exec() == QtWidgets.QMessageBox.StandardButton.Yes:
            print("Programa cerrado")
            sys.exit()
        else:
            mbox.hide()

    @classmethod
    def resizeTableClientes(cls):
        # Redimensiona la tabla para ajustarla correctamente
        try:
            header = var.ui.tableClientes.horizontalHeader()
            for i in range(header.count()):
                if i == 0:  # Establecer ancho fijo para la primera columna
                    header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.Fixed)
                    header.resizeSection(i, 50)  # Ancho deseado para la primera columna
                else:
                    header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.Stretch)
        except Exception as error:
            print("Error en el resize en la tabla Clientes: ", error)

    @classmethod
    def resizeTableProductos(cls):
        # Redimensiona la tabla para ajustarla correctamente
        try:
            header = var.ui.tableProductos.horizontalHeader()
            for i in range(header.count()):
                if i == 0:  # Establecer ancho fijo para la primera columna
                    header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.Fixed)
                    header.resizeSection(i, 50)  # Ancho deseado para la primera columna
                else:
                    header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.Stretch)
        except Exception as error:
            print("Error en el resize en la tabla Productos: ", error)

    @classmethod
    def resizeTableFacturas1(cls):
        # Redimensiona la tabla para ajustarla correctamente
        try:
            header = var.ui.tableFacturas1.horizontalHeader()
            for i in range(header.count()):
                if i == 0:
                    header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.Fixed)
                    header.resizeSection(i, 75)  # Ancho deseado para la primera columna
                else:
                    header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.Stretch)
        except Exception as error:
            print("Error en el resize en la tabla Facturas1: ", error)

    @classmethod
    def resizeTableFacturas2(cls):
        # Redimensiona la tabla para ajustarla correctamente
        try:
            header = var.ui.tableFacturas2.horizontalHeader()
            for i in range(header.count()):
                if i in (0, 1):
                    header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.Fixed)
                    header.resizeSection(i, 100)  # Ancho deseado para la primera columna
                else:
                    header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.Stretch)
        except Exception as error:
            print("Error en el resize en la tabla Facturas2: ", error)

    @staticmethod
    def abrirCalendar(self):
        try:
            var.calendar.show()
            print("Calendario abierto")
        except Exception as error:
            print("Error al abrir calendario: ", error)

    def error(title, text):
        mbox = QtWidgets.QMessageBox()
        mbox.setWindowTitle(title)
        mbox.setWindowIcon(QtGui.QIcon("img/limpiar.png"))
        mbox.setIcon(QtWidgets.QMessageBox.Icon.Warning)
        mbox.setText(text)
        mbox.exec()