from PyQt6 import QtSql, QtWidgets

import clients
import products
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
            if var.ui.chkHistorico.isChecked():
                # Si el checkBox está marcado, selecciona todos los clientes
                query.prepare(
                    'SELECT id_cliente, nombre, apellido, fecha_nacimiento, telefono, email, categoria, alta FROM Cliente')
            else:
                # Si el checkBox no está marcado, selecciona solo los clientes dados de alta (alta = 1)
                query.prepare(
                    'SELECT id_cliente, nombre, apellido, fecha_nacimiento, telefono, email, categoria, alta FROM Cliente WHERE alta = 1')
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
            print("Tabla cargada!")
        except Exception as error:
            msg = QtWidgets.QMessageBox()
            msg.setWindowTitle("Aviso")
            msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
            msg.setText("Error en cargar tabla o selección de datos")
            msg.exec()

    def selectProductos(self):
        # Carga los datos de los productos para añadirlos a la tabla Productos
        try:
            registros = []
            query = QtSql.QSqlQuery()
            query.prepare('SELECT id_producto, nombre, precio, stock FROM Producto')
            if query.exec():
                while query.next():
                    row = [query.value(i) for i in range(query.record().count())]
                    registros.append(row)
                if registros:
                    products.Products.cargarTablaProductos(registros)
                else:
                    var.ui.tableProductos.setRowCount(0)
            else:
                var.ui.tableProductos.setRowCount(0)
            print("Tabla cargada!")
        except Exception as error:
            msg = QtWidgets.QMessageBox()
            msg.setWindowTitle("Aviso")
            msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
            msg.setText("Error en cargar tabla o selección de datos")
            msg.exec()

    @staticmethod
    def mostrarclientes(self):
        try:
            registros = []
            estado = 1
            Connection.selectClientes(estado)
            if registros:
                clients.Clients.cargarTablaClientes(registros)
                return registros
            else:
                var.ui.tableClientes.setRowCount(0)
        except Exception as error:
            print("Error al mostrar resultados: ", error)

    @staticmethod
    def mostrarproductos(self):
        try:
            registros = []
            estado = 1
            Connection.selectProductos(estado)
            if registros:
                products.Products.cargarTablaProductos(registros)
                return registros
            else:
                var.ui.tableProductos.setRowCount(0)
        except Exception as error:
            print("Error al mostrar resultados: ", error)

    def onecliente(id_cliente):
        try:
            registro = []
            query = QtSql.QSqlQuery()
            query.prepare('SELECT * FROM Cliente WHERE id_cliente = :id_cliente')
            query.bindValue(':id_cliente', int(id_cliente))
            if query.exec():
                while query.next():
                    for i in range(9):
                        registro.append(str(query.value(i)))
            return registro
        except Exception as error:
            print("Error en fichero conexion datos de un Cliente: ", error)

    def onecliente(id_producto):
        try:
            registro = []
            query = QtSql.QSqlQuery()
            query.prepare('SELECT * FROM Productos WHERE id_producto = :id_producto')
            query.bindValue(':id_producto', int(id_producto))
            if query.exec():
                while query.next():
                    for i in range(9):
                        registro.append(str(query.value(i)))
            return registro
        except Exception as error:
            print("Error en fichero conexion datos de un Producto: ", error)