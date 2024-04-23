import sys, var, locale
from PyQt6 import QtWidgets, QtCore, QtGui

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
