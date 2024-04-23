from PyQt6 import QtSql, QtWidgets, QtCore

import clients
import var


class Connection():
    def conexion(self = None):
        # Cargar la base de datos SQLite
        db = QtSql.QSqlDatabase.addDatabase('QSQLITE')
        db.setDatabaseName('ddbb/ddbb.sqlite')
        if not db.open():
            print("Error de conexión")
            QtWidgets.QMessageBox.critical(None, 'Error', 'No se pudo abrir la base de datos')
            return False
        else:
            print("Base datos conectada!")
            return True

    def selectClientes(self):
        # Carga los datos de los clientes para añadirlos a la tabla Clientes
        try:
            registros = []
            query = QtSql.QSqlQuery()
            query.prepare(
                'SELECT id_cliente, nombre, apellido, fecha_nacimiento, telefono, email, categoria FROM Cliente')
            if query.exec():
                while query.next():
                    row = [query.value(i) for i in range(query.record().count())]
                    registros.append(row)
                if registros:
                    clients.Clients.cargarTablaClientes(registros)
                else:
                    var.ui.tableClientes.setRowCount(0)
            else:
                var.ui.tableClientes.setRowCount(0)
        except Exception as error:
            msg = QtWidgets.QMessageBox()
            msg.setWindowTitle("Aviso")
            msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
            msg.setText("Error en cargar tabla o selección de datos")
            msg.exec()