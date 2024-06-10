import os
from datetime import datetime

from PIL import Image
from PyQt6 import QtWidgets, QtGui, QtSql
from reportlab.pdfgen import canvas

import var


class Reports:
    @staticmethod
    def reportclientes():
        # Reporte de todos los clientes
        try:
            directory = './informes'
            if not os.path.exists(directory):
                os.makedirs(directory)

            fecha = datetime.today()
            fecha = fecha.strftime('%Y_%m_%d_%H_%M_%S')
            nombre = fecha + '_listadoclientes.pdf'
            var.report = canvas.Canvas(os.path.join(directory, nombre))
            titulo = 'LISTADO CLIENTES'
            Reports.topInforme(titulo)
            Reports.footInforme(titulo)
            items = ['ID', 'NOMBRE', 'APELLIDO', 'CORREO', 'TELEFONO', 'CATEGORIA']

            var.report.setFont('Helvetica-Bold', size=9)
            var.report.drawString(60, 675, str(items[0]))
            var.report.drawString(100, 675, str(items[1]))
            var.report.drawString(180, 675, str(items[2]))
            var.report.drawString(260, 675, str(items[3]))
            var.report.drawString(380, 675, str(items[4]))
            var.report.drawString(460, 675, str(items[5]))
            var.report.line(50, 670, 525, 670)

            query = QtSql.QSqlQuery()
            query.prepare('''
                SELECT id_cliente, nombre, apellido, email, telefono, categoria 
                FROM Cliente 
                ORDER BY apellido, nombre
            ''')
            var.report.setFont('Helvetica', size=9)

            if query.exec():
                j = 655
                while query.next():
                    if j <= 80:
                        var.report.drawString(525, 80, 'Página siguiente...')
                        var.report.showPage()
                        Reports.topInforme(titulo)
                        Reports.footInforme(titulo)
                        var.report.setFont('Helvetica-Bold', size=9)
                        var.report.drawString(60, 675, str(items[0]))
                        var.report.drawString(100, 675, str(items[1]))
                        var.report.drawString(180, 675, str(items[2]))
                        var.report.drawString(260, 675, str(items[3]))
                        var.report.drawString(380, 675, str(items[4]))
                        var.report.drawString(460, 675, str(items[5]))
                        var.report.line(50, 670, 525, 670)
                        j = 655
                    var.report.setFont('Helvetica', size=9)
                    var.report.drawString(60, j, str(query.value(0)))
                    var.report.drawString(100, j, str(query.value(1)))
                    var.report.drawString(180, j, str(query.value(2)))
                    var.report.drawString(260, j, str(query.value(3)))
                    var.report.drawString(380, j, str(query.value(4)))
                    var.report.drawString(460, j, str(query.value(5)))
                    j -= 20

            var.report.save()
            for file in os.listdir(directory):
                if file.endswith(nombre):
                    os.startfile(os.path.join(directory, file))
        except Exception as error:
            print('Error al listar clientes:', error)

    @staticmethod
    def reportproductos():
        # Reporte de todos los productos
        try:
            fecha = datetime.today()
            fecha = fecha.strftime('%Y_%m_%d_%H_%M_%S')
            nombre = fecha + '_listadoproductos.pdf'
            var.report = canvas.Canvas('informes/' + nombre)
            titulo = 'LISTADO PRODUCTOS'
            Reports.topInforme(titulo)
            Reports.footInforme(titulo)
            items = 'ID', 'NOMBRE', 'PRECIO', 'STOCK'

            var.report.setFont('Helvetica-Bold', size=9)
            var.report.drawString(80, 675, str(items[0]))
            var.report.drawString(150, 675, str(items[1]))
            var.report.drawString(350, 675, str(items[2]))
            var.report.drawString(460, 675, str(items[3]))
            var.report.line(50, 670, 525, 670)

            query = QtSql.QSqlQuery()
            query.prepare('SELECT * FROM Producto ORDER BY id_producto')
            var.report.setFont('Helvetica', size=9)
            if query.exec():
                i = 60
                j = 655
                while query.next():
                    if j <= 80:
                        var.report.drawString(450, 80, 'Página siguiente...')
                        var.report.showPage()
                        Reports.topInforme(titulo)
                        Reports.footInforme(titulo)
                        var.report.setFont('Helvetica', size=10)
                        var.report.drawString(80, 675, str(items[0]))
                        var.report.drawString(150, 675, str(items[1]))
                        var.report.drawString(350, 675, str(items[2]))
                        var.report.drawString(460, 675, str(items[3]))
                        var.report.line(50, 670, 525, 670)
                        i = 60
                        j = 655
                    var.report.setFont('Helvetica', size=9)
                    var.report.drawString(80, j, str(query.value(0)))
                    var.report.drawString(150,j, str(query.value(1)))
                    var.report.drawString(350,j, str(query.value(2)))
                    var.report.drawString(460,j, str(query.value(3)))
                    j = j - 20
            var.report.save()
            rootPath = '.\\informes'
            for file in os.listdir(rootPath):
                if file.endswith(nombre):
                    os.startfile('%s\\%s' % (rootPath, file))
        except Exception as error:
            print('Error al listar productos:', error)

    @staticmethod
    def reportfactura():
        # Reporte de una factura seleccionada en la pestaña Facturas
        try:
            if var.ui.lblCodBD_3.text() == "":
                mbox = QtWidgets.QMessageBox()
                mbox.setIcon(QtWidgets.QMessageBox.Icon.Information)
                mbox.setWindowIcon(QtGui.QIcon('./img/logo.ico'))
                mbox.setWindowTitle("Factura no seleccionada")
                mbox.setText("Debes seleccionar una factura en el apartado Facturas.")
                mbox.exec()
                return  # No hace nada más si no hay una factura seleccionada

            fecha = datetime.today()
            fecha = fecha.strftime('%Y_%m_%d_%H_%M_%S')
            nombre = fecha + '_detalle_factura.pdf'
            var.report = canvas.Canvas('informes/' + nombre)
            titulo = 'DETALLE DE FACTURA'
            Reports.topInforme(titulo)
            Reports.footInforme(titulo)
            items = 'ID', 'PRODUCTO', 'PRECIO', 'CANTIDAD', 'TOTAL'

            var.report.setFont('Helvetica-Bold', size=9)
            var.report.drawString(80, 675, str(items[0]))
            var.report.drawString(130, 675, str(items[1]))
            var.report.drawString(300, 675, str(items[2]))
            var.report.drawString(370, 675, str(items[3]))
            var.report.drawString(460, 675, str(items[4]))
            var.report.line(50, 670, 525, 670)

            # Obtiene el valor de id_factura desde var.ui.lblCodBD_3
            id_factura = var.ui.lblCodBD_3.text()

            query = QtSql.QSqlQuery()
            query.prepare('''
                SELECT D.id_detalle, P.nombre, P.precio, D.cantidad, D.precio * D.cantidad 
                FROM Detalle AS D 
                INNER JOIN Producto AS P ON D.id_producto = P.id_producto 
                WHERE D.id_factura = ?
            ''')
            query.addBindValue(id_factura)
            var.report.setFont('Helvetica', size=9)
            if query.exec():
                i = 60
                j = 655
                while query.next():
                    if j <= 80:
                        var.report.drawString(525, 80, 'Página siguiente...')
                        var.report.showPage()
                        Reports.topInforme(titulo)
                        Reports.footInforme(titulo)
                        var.report.setFont('Helvetica', size=10)
                        var.report.drawString(80, 675, str(items[0]))
                        var.report.drawString(130, 675, str(items[1]))
                        var.report.drawString(300, 675, str(items[2]))
                        var.report.drawString(370, 675, str(items[3]))
                        var.report.drawString(460, 675, str(items[4]))
                        j = 655
                    var.report.setFont('Helvetica', size=9)
                    var.report.drawString(80, j, str(query.value(0)))
                    var.report.drawString(130, j, str(query.value(1)))
                    var.report.drawString(300, j, str(query.value(2)))
                    var.report.drawString(390, j, str(query.value(3)))
                    var.report.drawString(460, j, str(query.value(4)))
                    j -= 20
            var.report.save()
            rootPath = '.\\informes'
            for file in os.listdir(rootPath):
                if file.endswith(nombre):
                    os.startfile('%s\\%s' % (rootPath, file))
        except Exception as error:
            print('Error al generar el detalle de factura:', error)

    def topInforme(titulo):
        # Header del informe con los datos de nuestra empresa
        try:
            ruta_logo = '.\\img\\logo.ico'
            logo = Image.open(ruta_logo)

            if isinstance(logo, Image.Image):
                var.report.line(50, 800, 525, 800)
                var.report.setFont('Helvetica-Bold', size=14)
                var.report.drawString(55, 785, 'Libreria Bernal')
                var.report.drawString(240, 695, titulo)
                var.report.line(50, 690, 525, 690)

                var.report.drawImage(ruta_logo, 480, 725, width=40, height=40)

                var.report.setFont('Helvetica', size=9)
                var.report.drawString(55, 770, 'CIF: A12345678')
                var.report.drawString(55, 755, 'Avda. Galicia - 101')
                var.report.drawString(55, 740, 'Vigo - 36216 - España')
                var.report.drawString(55, 725, 'Teléfono: 986 132 456')
                var.report.drawString(55, 710, 'e-mail: libreriabernal@mail.com')
            else:
                print(f'Error: No se pudo cargar la imagen en {ruta_logo}')
        except Exception as error:
            print('Error en cabecera informe:', error)

    def footInforme(titulo):
        # Footer del informe con información de la página
        try:
            var.report.line(50, 50, 525, 50)
            fecha = datetime.today()
            fecha = fecha.strftime('%d-%m-%Y %H:%M:%S')
            var.report.setFont('Helvetica-Oblique', size=7)
            var.report.drawString(50, 40, str(fecha))
            var.report.drawString(250, 40, str(titulo))
            var.report.drawString(490, 40, str('Página %s' % var.report.getPageNumber()))

        except Exception as error:
            print('Error en pie informe de cualquier tipo: ', error)
