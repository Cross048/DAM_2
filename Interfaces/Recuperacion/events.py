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
                header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.Stretch)
        except Exception as error:
            print("Error en el resize en la tabla Clientes: ", error)

    @classmethod
    def resizeTableProductos(cls):
        # Redimensiona la tabla para ajustarla correctamente
        try:
            header = var.ui.tableProductos.horizontalHeader()
            for i in range(header.count()):
                header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.Stretch)
        except Exception as error:
            print("Error en el resize en la tabla Productos: ", error)

    @classmethod
    def resizeTableFacturas1(cls):
        # Redimensiona la tabla para ajustarla correctamente
        try:
            header = var.ui.tableFacturas1.horizontalHeader()
            for i in range(header.count()):
                header.setSectionResizeMode(i, QtWidgets.QHeaderView.ResizeMode.Stretch)
        except Exception as error:
            print("Error en el resize en la tabla Clientes: ", error)

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