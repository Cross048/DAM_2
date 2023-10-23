from PyQt6 import QtWidgets, QtSql, QtCore
import var

class Conexion():
    def conexion(self = None):
        db = QtSql.QSqlDatabase.addDatabase('QSQLITE')
        db.setDatabaseName('bbdd.sqlite')
        if not db.open(self):
            print('error de conexión')
        else:
            print('base de datos conectada')
            return True

    def cargaprov(self = None):
        try:
            var.ui.cmbProv.clear()   # Cambiar según mi configuración
            query = QtSql.QSqlQuery()
            query = query.prepare('select provincia from provincias')
            if query.exec():
                var.ui.cmbProv.addItem('')   # Cambiar según mi configuración
                while query.next():
                    var.ui.cmvProv.addItem(query.value(0))
        except:
            print('error en la carga del comboBox de Provincias')
