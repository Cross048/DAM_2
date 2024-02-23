from PyQt6 import QtWidgets, QtSql, QtGui
from datetime import date,datetime

import clientes
import conexion
import eventos
import drivers
import var
import informes
import facturas

class Conexion():
    def conexion(self=None):
        """
        Establece la conexión a la base de datos SQLite.

        :param self: Parámetro opcional, puede ser nulo.
        :return: True si la conexión es exitosa, False en caso contrario.
        :rtype: bool
        """
        var.bbdd='bbdd.sqlite'
        db=QtSql.QSqlDatabase.addDatabase('QSQLITE')
        db.setDatabaseName(var.bbdd)
        if not db.open():
            print('error conexion')
            return False
        else:
            print('base datos encontrada')
            return True

    def cargarprov(self=None):
        """
        Carga las provincias en el combo box correspondiente.

        :param self: Parámetro opcional, puede ser nulo.
        """
        try:
            var.ui.cmbProv.clear()
            query=QtSql.QSqlQuery()
            query.prepare('select provincia from provincias')
            if query.exec():
                var.ui.cmbProv.addItem('')
                while query.next():
                    var.ui.cmbProv.addItem(query.value(0))
        except Exception as error:
            print(error, " en cargarprov")

    def selMuni(self=None):
        """
        Selecciona y carga los municipios en el combo box correspondiente según la provincia seleccionada.

        :param self: Parámetro opcional, puede ser nulo.
        """
        try:
            id=0;
            var.ui.cmbMuni.clear()
            prov = var.ui.cmbProv.currentText()
            query = QtSql.QSqlQuery()
            query.prepare('select idprov from provincias where provincia = :prov')
            query.bindValue(':prov', prov)
            if query.exec():
                while query.next():
                    id=query.value(0)
            query1 = QtSql.QSqlQuery()
            query1.prepare('select municipio from municipios where idprov = :id')
            query1.bindValue(':id',int(id))
            if query1.exec():
                var.ui.cmbMuni.addItem('')
                while query1.next():
                    var.ui.cmbMuni.addItem(query1.value(0))

        except Exception as error:
            print(error, " en selMuni")

    @staticmethod
    def guardardri(newDriver):
        """
        Guarda un nuevo conductor en la base de datos.

        :param newDriver: Lista con los detalles del nuevo conductor.
        :return: True si la inserción es exitosa, False en caso contrario.
        :rtype: bool
        """
        try:
            query = QtSql.QSqlQuery()
            query.prepare('insert into drivers (dnidri, altadri, apeldri, nombredri, direcciondri, provdri, '
                          'munidri, movildri, salario, carnet) VALUES(:dni, :alta, :apel, :nombre, :direccion, '
                          ':provincia, :municipio, :movil, :salario, :carnet)')
            query.bindValue(':dni', str(newDriver[0]))
            query.bindValue(':alta', str(newDriver[1]))
            query.bindValue(':apel', str(newDriver[2]))
            query.bindValue(':nombre', str(newDriver[3]))
            query.bindValue(':direccion', str(newDriver[4]))
            query.bindValue(':provincia', str(newDriver[5]))
            query.bindValue(':municipio', str(newDriver[6]))
            query.bindValue(':movil', str(newDriver[7]))
            query.bindValue(':salario', str(newDriver[8]))
            query.bindValue(':carnet', str(newDriver[9]))
            if query.exec():
                return True
            else:
                return False

        except Exception as error:
            print(error, " en guardardri")

    def mostrardriver(self= None):
        """
        Muestra los conductores en la tabla según la opción seleccionada (todos, alta, baja).

        :param self: Parámetro opcional, puede ser nulo.
        :return: Lista de listas con los detalles de los conductores.
        :rtype: list
        """
        try:
            registros = []
            query = QtSql.QSqlQuery()
            if var.ui.rbtTodos.isChecked():
                query.prepare('select codigo, apeldri, nombredri, movildri, carnet, bajadri from drivers')
            if var.ui.rbtAlta.isChecked():
                query.prepare('select codigo, apeldri, nombredri, movildri, carnet, bajadri from drivers where bajadri is null')
            if var.ui.rbtBaja.isChecked():
                query.prepare('select codigo, apeldri, nombredri, movildri, carnet, bajadri from drivers where bajadri is not null')

            if query.exec():
                while query.next():
                    row = [query.value(i) for i in range(query.record().count())]
                    registros.append(row)

            if registros:
                drivers.Drivers.cargarTabladri(registros)
            else:
                var.ui.tabDrivers.setRowCount(0)
            return registros


        except Exception as error:
            print('error mostrardricver', error)

    def oneDriver(codigo):
        """
        Recupera los detalles de un conductor según el código proporcionado.

        :param codigo: Código único del conductor.
        :return: Lista con los detalles del conductor.
        :rtype: list
        """
        try:
            registro=[]
            query = QtSql.QSqlQuery()
            query.prepare('select * from drivers where codigo=:codigo')
            query.bindValue(':codigo', int(codigo))
            if query.exec():
                while query.next():
                    for i in range(12):
                        registro.append(str(query.value(i)))

            return registro
        except Exception as error:
            print('error oneDriver', error)

    def codDri(dni):
        """
        Recupera los detalles de un conductor según el DNI proporcionado.

        :param dni: DNI del conductor.
        :return: Lista con los detalles del conductor.
        :rtype: list
        """
        try:
            registro=[]
            query = QtSql.QSqlQuery()
            query.prepare('select * from drivers where dnidri=:dnidri')
            query.bindValue(':dnidri', str(dni))
            if query.exec():
                while query.next():
                    for i in range(12):
                        registro.append(str(query.value(i)))
            return registro

        except Exception as error:
            print('error codDri', error)

    def modifDriver(modifDriver):
        """
        Modifica los detalles de un conductor en la base de datos.

        :param modifDriver: Lista con los nuevos detalles del conductor.
        """
        try:
            query = QtSql.QSqlQuery()
            query.prepare('update drivers set dnidri= :dni, altadri= :alta, apeldri= :apel, nombredri= :nombre, '
                          ' direcciondri= :direccion, provdri= :provincia, munidri= :municipio, '
                          ' movildri= :movil, salario= :salario, carnet= :carnet where codigo= :codigo')
            query.bindValue(':codigo', int(modifDriver[0]))
            query.bindValue(':dni', str(modifDriver[1]))
            query.bindValue(':alta', str(modifDriver[2]))
            query.bindValue(':apel', str(modifDriver[3]))
            query.bindValue(':nombre', str(modifDriver[4]))
            query.bindValue(':direccion', str(modifDriver[5]))
            query.bindValue(':provincia', str(modifDriver[6]))
            query.bindValue(':municipio', str(modifDriver[7]))
            query.bindValue(':movil', str(modifDriver[8]))
            query.bindValue(':salario', str(modifDriver[9]))
            query.bindValue(':carnet', str(modifDriver[10]))
            if query.exec():
                eventos.Eventos.mensaje("Aviso", "Conductor modificado con exito")
                Conexion.mostrardriver()
            else:
                eventos.Eventos.error("Aviso", query.lastError().text())

        except Exception as error:
            print('error modifdriver', error)

    def borrarDri(dni, fecha):
        """
        Da de baja a un conductor en la base de datos.

        :param dni: DNI del conductor.
        :param fecha: Fecha de la baja del conductor.
        """
        try:
            query1 = QtSql.QSqlQuery()
            query1.prepare('select bajadri from drivers where '
                          ' dnidri=:dni')
            query1.bindValue(':dni',str(dni))
            if query1.exec():
                while query1.next():
                    valor=query1.value(0)
                query = QtSql.QSqlQuery()
                query.prepare('update drivers set bajadri= :fechabaja where '
                              ' dnidri=:dni')
                query.bindValue(':fechabaja',str(fecha))
                query.bindValue(':dni',str(dni))
                if query.exec():
                    Conexion.mostrardriver()
                    var.Baja.hide()
                    eventos.Eventos.mensaje("Aviso", "Conductor dado de baja")
        except Exception as error:
            print('error modifdriver', error)
    @staticmethod
    def selectDriverstodos():
        """
        Selecciona todos los conductores y devuelve una lista con sus detalles.

        :return: Lista de listas con los detalles de todos los conductores.
        :rtype: list
        """
        try:
            registros=[]
            query1 = QtSql.QSqlQuery()
            query1.prepare('select * from drivers order by apeldri')
            if query1.exec():
                while query1.next():
                    row=[query1.value(i) for i in range(query1.record().count())]
                    registros.append(row)
            return registros


        except Exception as error:

            print('error selectDrivertodos', error)

    @staticmethod
    def verificarDri(dni):
        """
        Verifica si un conductor con el DNI proporcionado ya existe en la base de datos.

        :param dni: DNI del conductor a verificar.
        :return: True si el conductor ya existe, False si no.
        :rtype: bool
        """
        try:
            query = QtSql.QSqlQuery()
            consulta = "SELECT COUNT(*) FROM drivers WHERE dnidri = :dni"
            query.prepare(consulta)
            query.bindValue(':dni', dni)

            if query.exec():
                query.next()
                cantidad = query.value(0)
                return cantidad > 0

        except Exception as error:
            print("Error:", str(error))

    def volverDarAlta(dni):
        """
        Pregunta al usuario si desea dar de alta a un conductor que está dado de baja.

        :param dni: DNI del conductor.
        """

        try:
            mbox = QtWidgets.QMessageBox()
            mbox.setWindowTitle("Dar Alta")
            mbox.setWindowIcon(QtGui.QIcon("img/4043233-anime-away-face-no-nobody-spirited_113254.ico"))
            mbox.setIcon(QtWidgets.QMessageBox.Icon.Question)
            mbox.setText("El conductor está dado de baja.\n¿Desea darlo de alta de nuevo?")
            mbox.setStandardButtons(QtWidgets.QMessageBox.StandardButton.Yes | QtWidgets.QMessageBox.StandardButton.No)
            mbox.button(QtWidgets.QMessageBox.StandardButton.Yes).setText('Si')
            mbox.button(QtWidgets.QMessageBox.StandardButton.No).setText('No')
            mbox.setDefaultButton(QtWidgets.QMessageBox.StandardButton.Yes)
            mbox.setDefaultButton(QtWidgets.QMessageBox.StandardButton.No)

            if mbox.exec() == QtWidgets.QMessageBox.StandardButton.Yes:
                query = QtSql.QSqlQuery()
                query.prepare("update drivers set bajadri = :baja where dnidri = :dni")
                query.bindValue(":dni", dni)
                query.bindValue(":baja", None)
                if query.exec():
                    eventos.Eventos.mensaje("Aviso", "Conductor dado de alta")
                    conexion.Conexion.cargarconductor()
                else:
                    eventos.Eventos.error("Aviso", "El conductor no se pudo dar de alta")
            else:
                eventos.Eventos.error("Aviso", "Conductor no dado de alta")

        except Exception as error:
            print("Error al dar alta de nuevo conductor en BD", error)

    def oneCliente(codigo):
        """
        Recupera los detalles de un cliente según el código proporcionado.

        :param codigo: Código único del cliente.
        :return: Lista con los detalles del cliente.
        :rtype: list
        """
        try:
            registro = []
            query = QtSql.QSqlQuery()
            query.prepare("select * from clientes where codigo = :codigo")
            query.bindValue(":codigo", int(codigo))
            if query.exec():
                while query.next():
                    for i in range(8):
                        registro.append(str(query.value(i)))
            return registro
        except Exception as error:
            print("error en oneCliente", error)

    def mostrarClientes(self=None):
        """
        Muestra los clientes en la tabla según la opción seleccionada (todos, alta, baja).

        :param self: Parámetro opcional, puede ser nulo.
        """
        try:
            registros = []
            query = QtSql.QSqlQuery()
            if var.ui.rbtTodos2.isChecked():
                query.prepare('select codigo, social, movilcli, provcli from clientes')
            if var.ui.rbtAlta2.isChecked():
                query.prepare(
                    'select codigo, social, movilcli, provcli from clientes where bajacli is null')
            if var.ui.rbtBaja2.isChecked():
                query.prepare(
                    'select codigo, social, movilcli, provcli from clientes where bajacli is not null')
            if query.exec():
                while query.next():
                    row = [query.value(i) for i in range(query.record().count())]
                    registros.append(row)
            if registros:
                clientes.Clientes.cargarTablaClientes(registros)
            else:
                var.ui.tabClientes.setRowCount(0)
        except Exception as error:
            print("error en mostrarclientes", error)

    def selMuni2(self=None):
        """
        Selecciona y carga los municipios en el combo box cmbMuni2 según la provincia seleccionada en clientes.

        :param self: Parámetro opcional, puede ser nulo.
        """
        try:
            id=0;
            var.ui.cmbMuni2.clear()
            prov = var.ui.cmbProv2.currentText()
            query = QtSql.QSqlQuery()
            query.prepare('select idprov from provincias where provincia = :prov')
            query.bindValue(':prov', prov)
            if query.exec():
                while query.next():
                    id=query.value(0)
            query1 = QtSql.QSqlQuery()
            query1.prepare('select municipio from municipios where idprov = :id')
            query1.bindValue(':id',int(id))
            if query1.exec():
                var.ui.cmbMuni2.addItem('')
                while query1.next():
                    var.ui.cmbMuni2.addItem(query1.value(0))

        except Exception as error:
            print(error, " en selMuni2")
    def cargarprov2(self=None):
        """
        Carga las provincias en el combo box cmbProv2 que es la de clientes.

        :param self: Parámetro opcional, puede ser nulo.
        """
        try:
            var.ui.cmbProv2.clear()
            query=QtSql.QSqlQuery()
            query.prepare('select provincia from provincias')
            if query.exec():
                var.ui.cmbProv2.addItem('')
                while query.next():
                    var.ui.cmbProv2.addItem(query.value(0))
        except Exception as error:
            print(error, " en cargarprov")

    def verificarCli(dni):
        """
        Verifica si un cliente con el DNI proporcionado ya existe en la base de datos.

        :param dni: DNI del cliente a verificar.
        :return: True si el cliente ya existe, False si no.
        :rtype: bool
        """
        try:
            query = QtSql.QSqlQuery()
            consulta = "SELECT COUNT(*) FROM clientes WHERE dnicli = :dni"
            query.prepare(consulta)
            query.bindValue(':dni', dni)

            if query.exec():
                query.next()
                cantidad = query.value(0)
                return cantidad > 0

        except Exception as error:
            print("Error:", str(error))

    @staticmethod
    def guardarcli(newCliente):
        """
        Guarda un nuevo cliente en la base de datos.

        :param newCliente: Lista con los datos del nuevo cliente.
        :return: True si se guarda correctamente, False si hay algún error.
        :rtype: bool
        """
        try:
            query = QtSql.QSqlQuery()
            query.prepare('insert into clientes (dnicli, social, direccioncli, provcli, '
                          'municli, movilcli) VALUES(:dni, :social, :direccion, '
                          ':provincia, :municipio, :movil)')
            query.bindValue(':dni', str(newCliente[0]))
            query.bindValue(':social', str(newCliente[1]))
            query.bindValue(':direccion', str(newCliente[2]))
            query.bindValue(':provincia', str(newCliente[3]))
            query.bindValue(':municipio', str(newCliente[4]))
            query.bindValue(':movil', str(newCliente[5]))
            if query.exec():
                return True
            else:
                return False

        except Exception as error:
            print(error, " en guardarcli")

    def volverDarAlta2(dni):
        """
        Pregunta al usuario si desea dar de alta a un cliente que está dado de baja.

        :param dni: DNI del cliente.
        """
        try:
            mbox = QtWidgets.QMessageBox()
            mbox.setWindowTitle("Dar Alta")
            mbox.setWindowIcon(QtGui.QIcon("img/4043233-anime-away-face-no-nobody-spirited_113254.ico"))
            mbox.setIcon(QtWidgets.QMessageBox.Icon.Question)
            mbox.setText("El cliente está dado de baja.\n¿Desea darlo de alta de nuevo?")
            mbox.setStandardButtons(QtWidgets.QMessageBox.StandardButton.Yes | QtWidgets.QMessageBox.StandardButton.No)
            mbox.button(QtWidgets.QMessageBox.StandardButton.Yes).setText('Si')
            mbox.button(QtWidgets.QMessageBox.StandardButton.No).setText('No')
            mbox.setDefaultButton(QtWidgets.QMessageBox.StandardButton.Yes)
            mbox.setDefaultButton(QtWidgets.QMessageBox.StandardButton.No)

            if mbox.exec() == QtWidgets.QMessageBox.StandardButton.Yes:
                query = QtSql.QSqlQuery()
                query.prepare("update clientes set bajacli = :baja where dnicli = :dni")
                query.bindValue(":dni", dni)
                query.bindValue(":baja", None)
                if query.exec():
                    eventos.Eventos.mensaje("Aviso", "Cliente dado de alta")
                else:
                    eventos.Eventos.error("Aviso", "El cliente no se pudo dar de alta")
            else:
                eventos.Eventos.error("Aviso", "Cliente no dado de alta")

        except Exception as error:
            print("Error al dar alta de nuevo cliente en BD", error)

    def modifCliente(modifCliente):
        """
        Modifica un cliente en la base de datos.

        :param modifCliente: Lista con los nuevos datos del cliente.
        """
        try:
            query = QtSql.QSqlQuery()
            query.prepare('update clientes set dnicli= :dni, social= :social, '
                          ' direccioncli= :direccion, provcli= :provincia, municli= :municipio, '
                          ' movilcli= :movil where codigo= :codigo')
            query.bindValue(':codigo', int(modifCliente[0]))
            query.bindValue(':dni', str(modifCliente[1]))
            query.bindValue(':social', str(modifCliente[2]))
            query.bindValue(':direccion', str(modifCliente[3]))
            query.bindValue(':provincia', str(modifCliente[4]))
            query.bindValue(':municipio', str(modifCliente[5]))
            query.bindValue(':movil', str(modifCliente[6]))
            if query.exec():
                eventos.Eventos.mensaje("Aviso", "Cliente modificado con exito")
            else:
                eventos.Eventos.error("Aviso", query.lastError().text())

        except Exception as error:
            print('error modifclienter', error)

    def borrarCli(dni, fecha):
        """
        Da de baja a un cliente en la base de datos.

        :param dni: DNI del cliente.
        :param fecha: Fecha de baja.
        """
        try:
            query1 = QtSql.QSqlQuery()
            query1.prepare('select bajacli from clientes where '
                          ' dnicli=:dni')
            query1.bindValue(':dni',str(dni))
            if query1.exec():
                while query1.next():
                    valor=query1.value(0)
                query = QtSql.QSqlQuery()
                query.prepare('update clientes set bajacli= :fechabaja where '
                              ' dnicli=:dni')
                query.bindValue(':fechabaja',str(fecha))
                query.bindValue(':dni',str(dni))
                if query.exec():
                    Conexion.mostrarClientes()
                    var.Bajacli.hide()
                    eventos.Eventos.mensaje("Aviso", "Cliente dado de baja")
        except Exception as error:
            print('error borrarcvli', error)

    @staticmethod
    def selectClientesstodos():
        """
        Selecciona todos los clientes y devuelve una lista con sus detalles.

        :return: Lista de listas con los detalles de todos los clientes.
        :rtype: list
        """
        try:
            registros = []
            query1 = QtSql.QSqlQuery()
            query1.prepare('select * from clientes order by social')
            if query1.exec():
                while query1.next():
                    row = [query1.value(i) for i in range(query1.record().count())]
                    registros.append(row)
            return registros


        except Exception as error:

            print('error selectDrivertodos', error)
    def codCli(dni):
        """
        Recupera los detalles de un cliente según el DNI proporcionado.

        :param dni: DNI del cliente.
        :return: Lista con los detalles del cliente.
        :rtype: list
        """
        try:
            registro=[]
            query = QtSql.QSqlQuery()
            query.prepare('select * from clientes where dnicli=:dnicli')
            query.bindValue(':dnicli', str(dni))
            if query.exec():
                while query.next():
                    for i in range(12):
                        registro.append(str(query.value(i)))
            return registro

        except Exception as error:
            print('error codcli', error)
    
    def cargarconductor(self=None):
        """
        Carga los conductores disponibles en el combo box cmbCond.

        :param self: Parámetro opcional, puede ser nulo.
        """
        try:
            var.ui.cmbCond.clear()
            query=QtSql.QSqlQuery()
            query.prepare('select codigo, apeldri from drivers where bajadri is null order by codigo')
            if query.exec():
                var.ui.cmbCond.addItem('')
                while query.next():
                    var.ui.cmbCond.addItem(str(query.value(0))+"."+str(query.value(1)))
        except Exception as error:
            print(error, " cargar cond")

    def verificarClibaja(dni):
        """
        Verifica si un cliente con el DNI proporcionado existe y no está dado de baja.

        :param dni: DNI del cliente a verificar.
        :return: True si el cliente existe y no está dado de baja, False en caso contrario.
        :rtype: bool
        """
        try:
            query = QtSql.QSqlQuery()
            consulta = "SELECT COUNT(*) FROM clientes WHERE dnicli = :dni and bajacli is null"
            query.prepare(consulta)
            query.bindValue(':dni', dni)

            if query.exec():
                query.next()
                cantidad = query.value(0)
                return cantidad > 0

        except Exception as error:
            print("Error:", str(error))
    def altafacturacion(registro):
        """
        Realiza el alta de una factura en la base de datos.

        :param registro: Lista con los datos de la factura a dar de alta.
        """
        try:
            if not all([var.ui.txtcifcli.text(), var.ui.txtfechafact.text(), var.ui.cmbCond.currentText().split('.')[0]]):
                eventos.Eventos.error("Aviso", "Faltan datos obligatorios")
            else:
                dni = var.ui.txtcifcli.text()
                if Conexion.verificarClibaja(dni):
                    query = QtSql.QSqlQuery()
                    query.prepare('insert into facturas(dnicli, fecha, driver) values(:dni, :fecha, :driver)')
                    query.bindValue(":dni",str(registro[0]))
                    query.bindValue(":fecha", str(registro[1]))
                    query.bindValue(":driver", str(registro[2]))
                    if query.exec():
                        eventos.Eventos.mensaje("Aviso", "Factura guardada")
                        Conexion.cargarfacturas()
                else:
                    eventos.Eventos.error("Aviso", "Cliente no existente o dado de baja")
        except Exception as error:
            print(error, " cargar cond")

    @staticmethod
    def cargarfacturas():
        """
        Carga los números de factura y DNIs de clientes desde la tabla facturas.

        """
        try:
            registros=[]
            query = QtSql.QSqlQuery()
            query.prepare('select numfac, dnicli from facturas')
            if query.exec():
                while query.next():
                    row = [query.value(i) for i in range(query.record().count())]
                    registros.append(row)
            facturas.Facturas.cargarTablaFacturas(registros)

        except Exception as error:
            print(error, "cargarfacturas")
    def oneFactura(codigo):
        """
        Recupera los detalles de una factura según el código proporcionado.

        :param codigo: Número de factura.
        :return: Lista con los detalles de la factura.
        :rtype: list
        """
        try:
            registro = []
            query = QtSql.QSqlQuery()
            query.prepare("select * from facturas where numfac = :codigo")
            query.bindValue(":codigo", int(codigo))
            if query.exec():
                while query.next():
                    for i in range(4):
                        registro.append(str(query.value(i)))
            return registro
        except Exception as error:
            print("error en onefactura", error)

    def selMuni3(self=None):
        """
        Selecciona los municipios según la provincia elegida en cmbProbVentas que es el origen y los carga en cmbMuniVentas que tambien es del origen.

        :param self: Parámetro opcional, puede ser nulo.
        """
        try:
            id=0;
            var.ui.cmbMuniVentas.clear()
            prov = var.ui.cmbProbVentas.currentText()
            query = QtSql.QSqlQuery()
            query.prepare('select idprov from provincias where provincia = :prov')
            query.bindValue(':prov', prov)
            if query.exec():
                while query.next():
                    id=query.value(0)
            query1 = QtSql.QSqlQuery()
            query1.prepare('select municipio from municipios where idprov = :id')
            query1.bindValue(':id',int(id))
            if query1.exec():
                var.ui.cmbMuniVentas.addItem('')
                Conexion.datosViaje()
                while query1.next():
                    var.ui.cmbMuniVentas.addItem(query1.value(0))

        except Exception as error:
            print(error, " en selMuni2")
    def cargarprov3(self=None):
        """
        Carga las provincias en el combo box cmbProbVentas que es el de origen.

        :param self: Parámetro opcional, puede ser nulo.
        """
        try:
            var.ui.cmbProbVentas.clear()
            query=QtSql.QSqlQuery()
            query.prepare('select provincia from provincias')
            if query.exec():
                var.ui.cmbProbVentas.addItem('')
                Conexion.datosViaje()
                while query.next():
                    var.ui.cmbProbVentas.addItem(query.value(0))
        except Exception as error:
            print(error, " en cargarprov")
    def selMuni4(self=None):
        """
        Selecciona los municipios según la provincia elegida en cmbProbVentas2 que es el destino y los carga en cmbMuniVentas2 que tambien es del destino.

        :param self: Parámetro opcional, puede ser nulo.
        """
        try:
            id=0;
            var.ui.cmbMuniVentas2.clear()
            prov = var.ui.cmbProbVentas2.currentText()
            query = QtSql.QSqlQuery()
            query.prepare('select idprov from provincias where provincia = :prov')
            query.bindValue(':prov', prov)
            if query.exec():
                while query.next():
                    id=query.value(0)
            query1 = QtSql.QSqlQuery()
            query1.prepare('select municipio from municipios where idprov = :id')
            query1.bindValue(':id',int(id))
            if query1.exec():
                var.ui.cmbMuniVentas2.addItem('')
                Conexion.datosViaje()
                while query1.next():
                    var.ui.cmbMuniVentas2.addItem(query1.value(0))

        except Exception as error:
            print(error, " en selMuni2")
    def cargarprov4(self=None):
        """
        Carga las provincias en el combo box cmbProbVentas2 que es el de destino.

        :param self: Parámetro opcional, puede ser nulo.
        """
        try:
            var.ui.cmbProbVentas2.clear()
            query=QtSql.QSqlQuery()
            query.prepare('select provincia from provincias')
            if query.exec():
                var.ui.cmbProbVentas2.addItem('')
                Conexion.datosViaje()
                while query.next():
                    var.ui.cmbProbVentas2.addItem(query.value(0))
        except Exception as error:
            print(error, " en cargarprov")


    @staticmethod
    def datosViaje():
        """
        Recupera detalles de viaje basados en parámetros seleccionados y tasas de tarifas.

        :return: Lista que contiene datos de viaje, incluyendo origen, destino y tarifa aplicable.
        :rtype: list
        """
        try:
            facturas.Facturas.comprobarTarifa()
            tarifas=[0.20, 0.40, 0.80]
            datosviaje=[var.ui.cmbProbVentas.currentText(), var.ui.cmbMuniVentas, var.ui.cmbProbVentas2.currentText(), var.ui.cmbMuniVentas2]
            if str(datosviaje[0])== str(datosviaje[2]):
                if str(datosviaje[1]) == str(datosviaje[3]):
                    datosviaje.append(str(tarifas[2]))
                    return datosviaje
                else:
                    datosviaje.append(str(tarifas[1]))
                    return datosviaje
            else:
                datosviaje.append(str(tarifas[0]))
                return datosviaje

        except Exception as error:
            print(error, " en datos viaje")

    def viajesFactura(dato):
        """
        Recupera los detalles de los viajes asociados a una factura específica.

        :param dato: Número de factura.
        """
        try:
            valores = []
            query = QtSql.QSqlQuery()
            query.prepare("select idviaje, origen, destino, tarifa, km from viajes where factura = :dato")
            query.bindValue(':dato', str(dato))
            if query.exec():
                while query.next():
                    row = [query.value(i) for i in range(query.record().count())]
                    valores.append(row)

            facturas.Facturas.cargaTablaViajes(valores)
        except Exception as error:
            print("ERROR CARGAR viaje a la vista", error)



    def cargarLineaViaje(registro):
        """
        Carga un nuevo viaje en la base de datos a partir de un registro proporcionado.

        :param registro: Información del viaje a ser cargado.
        """
        try:
            if any(not elemento.strip() for elemento in registro):
                eventos.Eventos.error("Aviso", "Faltan datos del viaje o numero de factura")
            else:
                query = QtSql.QSqlQuery()
                query.prepare("insert into viajes(factura, origen, destino, tarifa, km) values (:factura, :origen, :destino, :tarifa, :kilometros)")
                query.bindValue(":factura", int(registro[5]))
                query.bindValue(":origen", int(registro[1]))
                query.bindValue(":destino", int(registro[2]))
                query.bindValue(":tarifa", int(registro[3]))
                query.bindValue(":kilometros", str(registro[4]))
                if query.exec():
                    eventos.Eventos.mensaje('Aviso','Viaje grabado en base de datos')
                    facturas.Facturas.cargaTablaViajes()

        except Exception as error:
            print(error, " en cargarprov")

    def guardarViaje(viaje):
        """
        Guarda un nuevo viaje en la base de datos.

        :param viaje: Información del viaje a ser guardado.
        """
        try:
            query = QtSql.QSqlQuery()
            query.prepare("insert into viajes (factura, origen, destino, tarifa, km) values (:factura, :origen, :destino, :tarifa, :kilometros)")
            query.bindValue(":factura", viaje[0])
            query.bindValue(":origen", viaje[1]+" - "+viaje[2])
            query.bindValue(":destino", viaje[3]+" - "+viaje[4])
            query.bindValue(":tarifa", viaje[5])
            query.bindValue(":kilometros", viaje[6])
            if query.exec():
                eventos.Eventos.mensaje('Aviso',"Viaje guardado")
            else:
                eventos.Eventos.error('Aviso',"Error al guardar el viaje")
        except Exception as error:
            print(error)


    def oneViajes(codigo):
        """
        Recupera un registro de un viaje según el código proporcionado.

        :param codigo: Código único del viaje.
        :return: Lista con los detalles del viaje.
        :rtype: list
        """
        try:
            registro = []
            query = QtSql.QSqlQuery()
            query.prepare("select * from viajes where idviaje = :codigo")
            query.bindValue(":codigo", int(codigo))
            if query.exec():
                while query.next():
                    for i in range(6):
                        registro.append(str(query.value(i)))
            return registro
        except Exception as error:
            print("error en oneviaje", error)

    def borrarViaje(id):
        """

        Borra un viaje de la base de datos según el ID proporcionado.

        :param id: ID único del viaje.
        """
        try:
            query = QtSql.QSqlQuery()
            query.prepare("delete from viajes where idviaje = :id")
            query.bindValue(":id", int(id))
            if query.exec():
                eventos.Eventos.mensaje('Aviso',"Viaje eliminado correctamente")
            else:
                eventos.Eventos.error('Aviso',"Error al borrar el viaje")

        except Exception as error:
            print(error)

    def updateViaje(viaje_id, nuevo_origen, nuevo_destino,tarifa, km):
        """
        Actualiza un viaje en la base de datos.

        :param viaje_id: ID del viaje a ser actualizado.
        :param nuevo_origen: Nueva ubicación de origen del viaje.
        :param nuevo_destino: Nueva ubicación de destino del viaje.
        :param tarifa: Nueva tarifa del viaje.
        :param km: Nuevos kilómetros del viaje.
        :return: True si la actualización fue exitosa, False en caso contrario.
        """

        try:
            query = QtSql.QSqlQuery()
            query.prepare("update viajes set origen=:origen, destino=:destino, tarifa=:tarifa, km=:kilometros where idviaje=:id")
            query.bindValue(":id", viaje_id)
            query.bindValue(":origen", nuevo_origen)
            query.bindValue(":destino", nuevo_destino)
            query.bindValue(":tarifa", tarifa)
            query.bindValue(":kilometros", km)

            return query.exec()

        except Exception as error:
            print("Error en updateViaje:", error)
            return False

    def cargarfacturasClientes(self=None):
        """

        Carga los números de factura y DNIs de clientes desde la tabla facturas para un DNI específico.

        """
        try:
            dni = var.ui.txtcifcli.text()
            if var.ui.txtcifcli!="":
                registros = []
                query = QtSql.QSqlQuery()
                query.prepare('select numfac, dnicli from facturas where dnicli = :dnicli')
                query.bindValue(':dnicli', str(dni))
                if query.exec():
                    while query.next():
                        row = [query.value(i) for i in range(query.record().count())]
                        registros.append(row)
                facturas.Facturas.cargarTablaFacturas(registros)
            else:
                eventos.Eventos.error("Aviso","Debe introducir un dni")

        except Exception as error:
            print(error, "cargarfacturas")