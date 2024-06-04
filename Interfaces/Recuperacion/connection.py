from PyQt6 import QtSql, QtWidgets

import bills
import clients
import products
import var


class Connection():
    # Conectar base de datos
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

    # Cargar Tablas
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
            print("Tabla Clientes cargada!")
        except Exception as error:
            msg = QtWidgets.QMessageBox()
            msg.setWindowTitle("Aviso")
            msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
            msg.setText("Error en cargar tabla Clientes o selección de datos")
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
            print("Tabla Productos cargada!")
        except Exception as error:
            msg = QtWidgets.QMessageBox()
            msg.setWindowTitle("Aviso")
            msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
            msg.setText("Error en cargar tabla Productos o selección de datos")
            msg.exec()

    def selectFacturas1(self):
        # Carga los datos de las Facturas para añadirlas a la tabla Factura1
        try:
            registros = []
            query = QtSql.QSqlQuery()
            query.prepare('''
                SELECT Factura.num_factura, Cliente.dni, Factura.fecha 
                FROM Factura 
                JOIN Cliente ON Factura.id_cliente = Cliente.id_cliente
            ''')
            if query.exec():
                while query.next():
                    row = [query.value(i) for i in range(query.record().count())]
                    registros.append(row)
                if registros:
                    bills.Bills.cargarTablaFacturas1(registros)
                else:
                    var.ui.tableFacturas1.setRowCount(0)
            else:
                var.ui.tableFacturas1.setRowCount(0)
            print("Tabla Facturas1 cargada!")
        except Exception as error:
            msg = QtWidgets.QMessageBox()
            msg.setWindowTitle("Aviso")
            msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
            msg.setText("Error en cargar tabla Facturas1 o selección de datos")
            msg.exec()

    def selectFacturas2(self):
        # Carga los datos de los Detalles para añadirlos a la tabla Factura2
        try:
            registros = []
            query = QtSql.QSqlQuery()
            query.prepare('''
                SELECT id_factura, Detalle.id_producto, Producto.precio, cantidad, Detalle.precio 
                FROM Detalle 
                JOIN Producto ON Detalle.id_producto = Producto.id_producto
            ''')
            if query.exec():
                while query.next():
                    row = [query.value(i) for i in range(query.record().count())]
                    registros.append(row)
                if registros:
                    bills.Bills.cargarTablaFacturas2(registros)
                else:
                    var.ui.tableFacturas2.setRowCount(0)
            else:
                var.ui.tableFacturas2.setRowCount(0)
            print("Tabla Facturas2 cargada!")
        except Exception as error:
            msg = QtWidgets.QMessageBox()
            msg.setWindowTitle("Aviso")
            msg.setIcon(QtWidgets.QMessageBox.Icon.Warning)
            msg.setText("Error en cargar tabla Facturas2 o selección de datos")
            msg.exec()

    # ToDo: Borrar métodos mostrarClientes() y mostrarProductos()
    '''
    @staticmethod
    def mostrarClientes(self):
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
    def mostrarProductos(self):
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
    '''

    # Cargar datos en formulario
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
            print("Registro obtenido de la base de datos:", registro)
            return registro
        except Exception as error:
            print("Error en fichero conexion datos de un Cliente: ", error)

    def oneproducto(id_producto):
        try:
            registro = []
            query = QtSql.QSqlQuery()
            query.prepare('SELECT * FROM Producto WHERE id_producto = :id_producto')
            query.bindValue(':id_producto', int(id_producto))
            if query.exec():
                while query.next():
                    for i in range(4):
                        registro.append(str(query.value(i)))
            print("Registro obtenido de la base de datos:", registro)
            return registro
        except Exception as error:
            print("Error en fichero conexion datos de un Producto: ", error)

    def onefactura1(num_factura):
        try:
            registro = []
            query = QtSql.QSqlQuery()
            query.prepare('SELECT * FROM Factura WHERE num_factura = :num_factura')
            query.bindValue(':num_factura', int(num_factura))
            if query.exec():
                while query.next():
                    for i in range(3):
                        registro.append(str(query.value(i)))
            print("Registro obtenido de la base de datos:", registro)
            return registro
        except Exception as error:
            print("Error en fichero conexion datos de una Factura: ", error)

    def onefactura2(num_detalle):
        try:
            registro = []
            query = QtSql.QSqlQuery()
            query.prepare('''
                SELECT Detalle.id_factura, Detalle.id_producto, Producto.precio, Detalle.cantidad 
                FROM Detalle 
                JOIN Producto ON Detalle.id_producto = Producto.id_producto
                WHERE num_detalle = :num_detalle''')
            query.bindValue(':num_detalle', int(num_detalle))
            if query.exec():
                while query.next():
                    for i in range(4):
                        registro.append(str(query.value(i)))
            print("Registro obtenido de la base de datos:", registro)
            return registro
        except Exception as error:
            print("Error en fichero conexion datos de una Factura: ", error)

    # Modificar base de datos
    @staticmethod
    def modificarCliente(self):
        try:
            id_cliente = var.ui.lblCodBD.text()
            dni = var.ui.txtDNI.text()
            nombre = var.ui.txtNombre.text()
            apellido = var.ui.txtApel.text()
            direccion = var.ui.txtDir.text()
            fecha_nacimiento = var.ui.txtData.text()
            telefono = var.ui.txtMovil.text()
            email = var.ui.txtEmail.text()
            categoria = "Particular" if var.ui.rbtnParticular.isChecked() else "Empresa"

            query = QtSql.QSqlQuery()
            query.prepare('UPDATE Cliente '
                          'SET dni = :dni, nombre = :nombre, apellido = :apellido, '
                          'direccion = :direccion, fecha_nacimiento = :fecha_nacimiento, '
                          'telefono = :telefono, email = :email, categoria = :categoria '
                          'WHERE id_cliente = :id_cliente')
            query.bindValue(':dni', dni)
            query.bindValue(':nombre', nombre)
            query.bindValue(':apellido', apellido)
            query.bindValue(':direccion', direccion)
            query.bindValue(':fecha_nacimiento', fecha_nacimiento)
            query.bindValue(':telefono', telefono)
            query.bindValue(':email', email)
            query.bindValue(':categoria', categoria)
            query.bindValue(':id_cliente', id_cliente)

            if query.exec():
                print("Registro actualizado correctamente.")
                connection_instance = Connection()
                connection_instance.selectClientes()
            else:
                print("Error al actualizar el registro:", query.lastError().text())
        except Exception as error:
            print("Error al modificar cliente: ", error)

    @staticmethod
    def modificarProducto(self):
        try:
            id_producto = var.ui.lblCodBD_2.text()
            nombre = var.ui.txtNombre_2.text()
            precio = var.ui.txtPrecio.text()
            stock = var.ui.spinStock.value()

            query = QtSql.QSqlQuery()
            query.prepare('UPDATE Producto '
                          'SET nombre = :nombre, precio = :precio, stock = :stock '
                          'WHERE id_producto = :id_producto')
            query.bindValue(':nombre', nombre)
            query.bindValue(':precio', precio)
            query.bindValue(':stock', stock)
            query.bindValue(':id_producto', id_producto)

            if query.exec():
                print("Registro de producto actualizado correctamente.")
                connection_instance = Connection()
                connection_instance.selectProductos()
            else:
                print("Error al actualizar el registro de producto:", query.lastError().text())
        except Exception as error:
            print("Error al modificar producto: ", error)
