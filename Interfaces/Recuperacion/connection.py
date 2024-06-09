from PyQt6 import QtSql, QtWidgets
from PyQt6.QtWidgets import QMessageBox
from PyQt6.uic.properties import QtGui
from PyQt6 import QtWidgets, QtGui

import MainWindow
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
                SELECT id_factura, Detalle.id_detalle, Producto.nombre, Producto.precio, cantidad, Detalle.precio 
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
                if query.next():  # Solo esperamos un resultado
                    for i in range(4):
                        valor = query.value(i)
                        print(f"Valor en columna {i}: {valor} (Tipo: {type(valor)})")
                        registro.append(valor)
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

    def onefactura2(id_detalle):
        try:
            registro = []
            query = QtSql.QSqlQuery()
            query.prepare('''
                SELECT id_factura, Producto.id_producto, Producto.precio, cantidad 
                FROM Detalle 
                JOIN Producto ON Detalle.id_producto = Producto.id_producto
                WHERE id_detalle = :id_detalle''')
            query.bindValue(':id_detalle', int(id_detalle))
            if query.exec():
                while query.next():
                    for i in range(4):
                        registro.append(str(query.value(i)))
            print("Registro obtenido de la base de datos Detalle:", registro)
            return registro
        except Exception as error:
            print("Error en fichero conexión datos de una factura:", error)

    def facturaTotal(id_factura):
        try:
            total = 0
            query = QtSql.QSqlQuery()
            query.prepare('''
                SELECT SUM(precio) AS total
                FROM Detalle
                WHERE Detalle.id_factura = :id_factura
            ''')
            query.bindValue(':id_factura', int(id_factura))
            if query.exec():
                if query.next():
                    total = query.value(0)
            print("Total de la factura con id_factura", id_factura, "es:", total)
            return total
        except Exception as error:
            print("Error al calcular el total de la factura:", error)

    # Añadir a la base de datos
    @staticmethod
    def anyadirCliente(self):
        try:
            # Obtener los valores de los campos de texto
            nombre = var.ui.txtNombre.text()
            apellido = var.ui.txtApel.text()
            direccion = var.ui.txtDir.text()
            fecha_nacimiento = var.ui.txtData.text()
            telefono = var.ui.txtMovil.text()
            email = var.ui.txtEmail.text()
            categoria = "Particular" if var.ui.rbtnParticular.isChecked() else "Empresa"

            # Comprobar si el cliente ya existe en la base de datos
            dni = var.ui.txtDNI.text()
            query_check = QtSql.QSqlQuery()
            query_check.prepare('SELECT COUNT(*) FROM Cliente WHERE dni = :dni')
            query_check.bindValue(':dni', dni)
            if query_check.exec():
                query_check.first()
                if query_check.value(0) > 0:
                    # Si el cliente ya existe, mostrar un mensaje y abortar la inserción
                    mbox = QtWidgets.QMessageBox()
                    mbox.setIcon(QtWidgets.QMessageBox.Icon.Information)
                    mbox.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
                    mbox.setWindowTitle("Cliente existente")
                    mbox.setText("Ya existe un cliente con este DNI.")
                    mbox.exec()
                    return
            else:
                print("Error al verificar la existencia del cliente:", query_check.lastError().text())
                return

            # Obtener el último ID de la tabla "Cliente"
            query_last_id = QtSql.QSqlQuery()
            query_last_id.exec('SELECT MAX(id_cliente) FROM Cliente')
            if query_last_id.next():
                last_id = query_last_id.value(0)
                next_id = last_id + 1
            else:
                next_id = 1  # Si no hay ningún cliente en la tabla, comenzamos desde 1

            # Insertar el nuevo cliente en la base de datos
            query_insert = QtSql.QSqlQuery()
            query_insert.prepare(
                'INSERT INTO Cliente (id_cliente, dni, nombre, apellido, direccion, fecha_nacimiento, telefono, email, categoria) '
                'VALUES (:id_cliente, :dni, :nombre, :apellido, :direccion, :fecha_nacimiento, :telefono, :email, :categoria)')
            query_insert.bindValue(':id_cliente', next_id)
            query_insert.bindValue(':dni', dni)
            query_insert.bindValue(':nombre', nombre)
            query_insert.bindValue(':apellido', apellido)
            query_insert.bindValue(':direccion', direccion)
            query_insert.bindValue(':fecha_nacimiento', fecha_nacimiento)
            query_insert.bindValue(':telefono', telefono)
            query_insert.bindValue(':email', email)
            query_insert.bindValue(':categoria', categoria)

            if query_insert.exec():
                print("Cliente añadido correctamente.")
                connection_instance = Connection()
                connection_instance.selectClientes()  # Actualizar la vista de clientes
            else:
                print("Error al añadir el cliente:", query_insert.lastError().text())
        except Exception as error:
            print("Error al añadir cliente: ", error)

    @staticmethod
    def anyadirProducto():
        try:
            nombre = var.ui.txtNombre_2.text()
            precio = var.ui.txtPrecio.text()
            stock = var.ui.spinStock.value()

            # Comprobar si el producto ya existe en la base de datos
            query_check = QtSql.QSqlQuery()
            query_check.prepare('SELECT COUNT(*) FROM Producto WHERE nombre = :nombre')
            query_check.bindValue(':nombre', nombre)
            if query_check.exec():
                query_check.first()
                if query_check.value(0) > 0:
                    # Si el producto ya existe, mostrar un mensaje y abortar la inserción
                    mbox = QtWidgets.QMessageBox()
                    mbox.setIcon(QtWidgets.QMessageBox.Icon.Information)
                    mbox.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
                    mbox.setWindowTitle("Producto existente")
                    mbox.setText("Ya existe este producto.")
                    mbox.exec()
                    return
            else:
                print("Error al verificar la existencia del producto:", query_check.lastError().text())
                return

            # Insertar el nuevo producto en la base de datos
            query_insert = QtSql.QSqlQuery()
            query_insert.prepare('INSERT INTO Producto (nombre, precio, stock) '
                                 'VALUES (:nombre, :precio, :stock)')
            query_insert.bindValue(':nombre', nombre)
            query_insert.bindValue(':precio', precio)
            query_insert.bindValue(':stock', stock)

            if query_insert.exec():
                print("Producto añadido correctamente.")
                connection_instance = Connection()
                connection_instance.selectProductos()  # Actualizar la vista de productos
            else:
                print("Error al añadir el producto:", query_insert.lastError().text())
        except Exception as error:
            print("Error al añadir o actualizar producto: ", error)

    @staticmethod
    def crearFactura():
        try:
            # Obtener los valores de los campos de texto
            id_cliente = var.ui.txtNombre_3.text()
            fecha = var.ui.txtAlta_3.text()

            # Verificar si ya existe una factura con el mismo cliente y fecha
            query_check = QtSql.QSqlQuery()
            query_check.prepare('SELECT COUNT(*) FROM Factura WHERE id_cliente = :id_cliente AND fecha = :fecha')
            query_check.bindValue(':id_cliente', id_cliente)
            query_check.bindValue(':fecha', fecha)
            if query_check.exec():
                query_check.first()
                if query_check.value(0) > 0:
                    # Si ya existe una factura con el mismo cliente y fecha, mostrar un mensaje y abortar la inserción
                    mbox = QtWidgets.QMessageBox()
                    mbox.setIcon(QtWidgets.QMessageBox.Icon.Information)
                    mbox.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
                    mbox.setWindowTitle("Factura existente")
                    mbox.setText("Ya existe una factura para este cliente en la fecha especificada.")
                    mbox.exec()
                    return

            # Obtener el valor del último número de factura
            query_last_id = QtSql.QSqlQuery()
            query_last_id.exec('SELECT MAX(num_factura) FROM Factura')
            if query_last_id.next():
                last_num_factura = query_last_id.value(0)
                next_num_factura = last_num_factura + 1
            else:
                next_num_factura = 1  # Si no hay ninguna factura en la tabla, comenzamos desde 1

            # Crear la sentencia SQL para insertar una nueva factura
            query_insert = QtSql.QSqlQuery()
            query_insert.prepare(
                'INSERT INTO Factura (num_factura, id_cliente, fecha) '
                'VALUES (:num_factura, :id_cliente, :fecha)'
            )
            query_insert.bindValue(':num_factura', next_num_factura)
            query_insert.bindValue(':id_cliente', id_cliente)
            query_insert.bindValue(':fecha', fecha)

            # Ejecutar la consulta
            if query_insert.exec():
                print("Factura creada correctamente.")
                connection_instance = Connection()
                connection_instance.selectFacturas1()  # Actualizar la vista de facturas
            else:
                print("Error al crear la factura:", query_insert.lastError().text())
        except Exception as error:
            print("Error al crear factura: ", error)

    @staticmethod
    def anyadirDetalle():
        try:
            id_factura = var.ui.lblCodBD_4.text()  # Obtener el ID de la factura desde la interfaz de usuario
            id_producto = var.ui.txtProducto_4.text()  # Obtener el ID del producto desde la interfaz de usuario
            cantidad = var.ui.spinStock_4.value()  # Obtener la cantidad desde el QSpinBox
            precio_unitario = float(
                var.ui.txtPrecio_4.text())  # Obtener el precio unitario desde la interfaz de usuario

            # Calcular el precio total multiplicando el precio unitario por la cantidad
            precio_total = precio_unitario * cantidad

            # Buscar el detalle correspondiente en la tabla Detalle
            query_search = QtSql.QSqlQuery()
            query_search.prepare('''
                SELECT id_detalle, cantidad, precio 
                FROM Detalle 
                WHERE id_factura = :id_factura AND id_producto = :id_producto
            ''')
            query_search.bindValue(':id_factura', int(id_factura))
            query_search.bindValue(':id_producto', int(id_producto))
            if query_search.exec() and query_search.next():
                # Si ya existe un detalle, mostrar un mensaje de error
                mbox = QtWidgets.QMessageBox()
                mbox.setIcon(QtWidgets.QMessageBox.Icon.Information)
                mbox.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
                mbox.setWindowTitle("Factura existente")
                mbox.setText("Ya existe una factura con dicho producto.")
                mbox.exec()
            else:
                # Si no existe un detalle, insertar uno nuevo
                query_insert = QtSql.QSqlQuery()
                query_insert.prepare('''
                    INSERT INTO Detalle (id_factura, id_producto, cantidad, precio) 
                    VALUES (:id_factura, :id_producto, :cantidad, :precio)
                ''')
                query_insert.bindValue(':id_factura', int(id_factura))
                query_insert.bindValue(':id_producto', int(id_producto))
                query_insert.bindValue(':cantidad', cantidad)
                query_insert.bindValue(':precio', precio_total)
                if query_insert.exec():
                    print("Nuevo detalle de factura agregado correctamente.")
                    connection_instance = Connection()
                    connection_instance.selectFacturas2()  # Actualizar la vista de facturas
                else:
                    print("Error al agregar el nuevo detalle de factura:", query_insert.lastError().text())
        except Exception as error:
            print("Error al agregar detalle de factura: ", error)

    # Modificar la base de datos
    @staticmethod
    def modificarCliente():
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
    def modificarProducto():
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

    @staticmethod
    def modificarDetalle():
        try:
            id_factura = var.ui.lblCodBD_4.text()  # Obtener el ID de la factura desde la interfaz de usuario
            id_producto = var.ui.txtProducto_4.text()  # Obtener el ID del producto desde la interfaz de usuario
            cantidad = var.ui.spinStock_4.value()  # Obtener la cantidad desde el QSpinBox
            precio_unitario = float(var.ui.txtPrecio_4.text())  # Obtener el precio unitario desde la interfaz de usuario

            # Calcular el precio total multiplicando el precio unitario por la cantidad
            precio_total = precio_unitario * cantidad

            # Buscar el detalle correspondiente en la tabla Detalle
            query_search = QtSql.QSqlQuery()
            query_search.prepare('''
                SELECT id_detalle FROM Detalle 
                WHERE id_factura = :id_factura AND id_producto = :id_producto
            ''')
            query_search.bindValue(':id_factura', int(id_factura))
            query_search.bindValue(':id_producto', int(id_producto))
            if query_search.exec() and query_search.next():
                id_detalle = query_search.value(0)

                # Actualizar el detalle en la tabla Detalle
                query_update = QtSql.QSqlQuery()
                query_update.prepare('''
                    UPDATE Detalle 
                    SET precio = :precio, cantidad = :cantidad 
                    WHERE id_detalle = :id_detalle
                ''')
                query_update.bindValue(':id_detalle', int(id_detalle))
                query_update.bindValue(':precio', precio_total)  # Usar el precio total calculado
                query_update.bindValue(':cantidad', cantidad)
                if query_update.exec():
                    print("Detalle de factura modificado correctamente.")
                    connection_instance = Connection()
                    connection_instance.selectFacturas2()  # Actualizar la vista de facturas
                else:
                    print("Error al modificar el detalle de factura:", query_update.lastError().text())
            else:
                print("No se encontró el detalle en la base de datos.")
        except Exception as error:
            print("Error al modificar detalle de factura: ", error)

    # Borrar de la base de datos
    @staticmethod
    def borrarCliente():
        try:
            id_cliente = var.ui.lblCodBD.text()

            # Comprobar si el cliente existe en la base de datos
            query_check = QtSql.QSqlQuery()
            query_check.prepare('SELECT COUNT(*) FROM Cliente WHERE id_cliente = :id_cliente')
            query_check.bindValue(':id_cliente', id_cliente)
            if query_check.exec():
                query_check.first()
                if query_check.value(0) == 0:
                    mbox = QtWidgets.QMessageBox()
                    mbox.setIcon(QtWidgets.QMessageBox.Icon.Information)
                    mbox.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
                    mbox.setWindowTitle("Borrar cliente")
                    mbox.setText("No se ha podido borrar el cliente.")
                    mbox.exec()
                    return
            else:
                print("Error al verificar la existencia del cliente:", query_check.lastError().text())
                return

            # Eliminar el cliente de la base de datos
            query_delete = QtSql.QSqlQuery()
            query_delete.prepare('DELETE FROM Cliente WHERE id_cliente = :id_cliente')
            query_delete.bindValue(':id_cliente', id_cliente)

            if query_delete.exec():
                print("Cliente borrado correctamente.")
                connection_instance = Connection()
                connection_instance.selectClientes()  # Actualizar la vista de clientes
            else:
                print("Error al borrar el cliente:", query_delete.lastError().text())
        except Exception as error:
            print("Error al borrar cliente: ", error)

    @staticmethod
    def borrarProducto():
        try:
            id_producto = var.ui.lblCodBD_2.text()

            query = QtSql.QSqlQuery()
            query.prepare('DELETE FROM Producto WHERE id_producto = :id_producto')
            query.bindValue(':id_producto', id_producto)

            if query.exec():
                print("Producto eliminado correctamente.")
                connection_instance = Connection()
                connection_instance.selectProductos()  # Actualizar la vista de productos
            else:
                print("Error al eliminar el producto:", query.lastError().text())
        except Exception as error:
            print("Error al eliminar producto: ", error)
