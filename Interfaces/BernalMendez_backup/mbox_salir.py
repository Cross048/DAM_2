from PyQt6 import QtWidgets, QtSql, QtCore
# Este método crea la messagebox y devuelve un valor verdadero o falso para que cuando llame al método
# exitApp me cierre el programa si el valor retornado es verdadero

def closeDlg():
    mbox = QtWidgets.QMessageBox.question(None, 'Salir', '¿Estás seguro que quieres salir?',
                                          QtWidgets.QMessageBox.StandardButton.Yes | QtWidgets.QMessageBox.StandardButton.No)

    if mbox == QtWidgets.QMessageBox.StandardButton.Yes:
        return True
    else:
        return False

def exitApp():
    if closeDlg():
        try:
            exit()
        except Exception as error:
            print(error, "Error al intentar cerrar la aplicación")

# Este es el método que sobrecargamos en la ventana principal que se encarga de cerrar la aplicación
# como este método requiere un argumento, para que la aplicación no se cierre hay que llamar desde este mismo
# parámetro al método ignore(). De ahí que utilice un método distinto para crear la messagebox y otro para cerrar

def closeEvent(self, event):
    if closeDlg():
        exitApp(event)
    else:
        event.ignore()