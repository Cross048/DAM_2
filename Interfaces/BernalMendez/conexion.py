from PyQt6 import QtWidgets, QtSql, QtCore

import drivers
import var

class Conexion():
    def conexion(self = None):
        db = QtSql.QSqlDatabase.addDatabase('QSQLITE')
        db.setDatabaseName('bbdd.sqlite')
        if not db.open(self):
            print('error de conexión')
            return False
        else:
            print('base de datos conectada')
            return True

    def cargaprov(self = None):
        try:
            var.ui.cmbProv.clear()   # Cambiar según mi configuración
            query = QtSql.QSqlQuery()
            query = query.prepare('select provincia from provincias')
            if query.exec():
                var.ui.comboBoxProvincia.addItem('')   # Cambiar según mi configuración
                while query.next():
                    var.ui.comboBoxProvincia.addItem(query.value(0))
        except Exception as error:
            print('error en la carga del comboBox de Provincias', error)

    def selMuni(self = None):
        try:
            id = 0
            prov = var.ui.comboBoxProvincia.currentText()
            query = QtSql.QSqlQuery()
            query.prepare('select id from provincias where provincia = :prov')
            query.bindValue(':prov', prov)
            if query.exec():
                while query.next():
                    id = query.value(0)
            query1 = QtSql.QSqlQuery()
            query1.prepare('select municipio from municipios qhere idprov = :id')
            query1.bindValue(':id', int(id))
            if query1.exec():
                var.ui.comboBoxLocalidad.addItem('')
                while query1.next():
                    var.ui.comboBoxLocalidad.addItem(query1.value(0))
        except Exception as error:
            print('error seleccion municipios', error)

    # TODO: completar guardardri()
    # def guardardri(self):

    def mostrardrivers(selfself):
        try:
            registros = []
            query1 = QtSql.QSqlQuery()
            query1.prepare('select codigo, apeldri, nombredri, movildri, carnet, bajadri from drivers')
            if query1.exec():
                while query1.next():
                    row = [query1.value(i) for i in range(query1.record().count())]
                    registros.append(row)
            drivers.Drivers.cargartabladri(registros)
        except Exception as error:
            print('error mostrar resultados ', error)

    def onedriver(codigo):
        try:
            registro = []
            query = QtSql.QSqlQuery()
            query.prepare('select * from drivers where codigo = :codigo')
            query.bindValue(':codigo', int(codigo))
            if query.exec():
                while query.next():
                    for i in range(12):
                        registro.append(str(query.value(i)))
            return registro
        except Exception as error:
            print('error en fichero conexion datos de 1 driver: ', error)

    def codDri(dni):
        try:
            query = QtSql.QSqlQuery()
            query.prepare('select codigo from drivers where dnidri = :dni')
            query.bindValue(':dni', str(dni))
            if query.exec():
                while query.next():
                    codigo = query.value(0)
            registro = Conexion.onedriver(codigo)
            return registro
        except Exception as error:
            print('error en busqueda codigo conductor ', error)