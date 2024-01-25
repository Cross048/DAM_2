import os, var, shutil
from PIL import Image
from PyQt6 import QtSql, QtWidgets
from reportlab.pdfgen import canvas
from datetime import datetime
from svglib.svglib import svg2rlg
import conexion

class Informes:
    def reportclientes(self):
        try:
            fecha = datetime.today()
            fecha = fecha.strftime('%Y_%m_%d_%H_%M_%S')
            nombre =  fecha + '_listadoclientes.pdf'
            var.report = canvas.Canvas('informes/' + nombre)
            titulo = 'LISTADO CLIENTES'
            Informes.topInforme(titulo)
            Informes.footInforme(titulo)
            items = ['Código', 'DNI', 'Razón Social', 'Municipio', 'Teléfono', 'Fecha Baja']
            var.report.setFont('Helvetica-Bold', size=10)
            var.report.drawString(55, 675, str(items[0]))
            var.report.drawString(130, 675, str(items[1]))
            var.report.drawString(205, 675, str(items[2]))
            var.report.drawString(335, 675, str(items[3]))
            var.report.drawString(450, 675, str(items[4]))
            var.report.line(50, 670, 525, 670) 
            var.report.line(50, 670, 525, 670)

            # Obtención de datos de la base de datos
            query = QtSql.QSqlQuery()
            query.prepare('select Codigo, DNI, RazonSocial, Municipio, Telefono, bajadri from ListadoClientes order by RazonSocial')
            var.report.setFont('Helvetica', size=9)
            if query.exec():
                i = 55
                j = 675
                while query.next():
                    if j <= 80:
                        var.report.drawString(450, 90, 'Página siguiente...')
                        var.report.showPage()  # Crea una nueva página
                        Informes.topInforme(titulo)
                        Informes.footInforme(titulo)
                        var.report.setFont('Helvetica-Bold', size=10)
                        var.report.drawString(55, 675, str(items[0]))
                        var.report.drawString(130, 675, str(items[1]))
                        var.report.drawString(205, 675, str(items[2]))
                        var.report.drawString(335, 675, str(items[3]))
                        var.report.drawString(450, 675, str(items[4]))
                        var.report.line(50, 670, 525, 670)
                        i = 55
                        j = 655
                    var.report.setFont('Helvetica', size=9)
                    var.report.drawString(i + 15, j, str(query.value(0)))
                    var.report.drawString(i + 60, j, str(query.value(1)))
                    var.report.drawString(i + 150, j, str(query.value(2)))
                    var.report.drawString(i + 280, j, str(query.value(3)))
                    var.report.drawString(i + 400, j, str(query.value(4)))
                    j = j - 100

            var.report.save()
            rootPath = '.\\informes'
            for file in os.listdir(rootPath):
                if file.endswith('listadoclientes.pdf'):
                    os.startfile('%s\\%s' % (rootPath, file))
        except Exception as error:
            print('Error LISTADO CLIENTES :', error)

    def topInforme(titulo):
        try:
            ruta_logo = '.\\img\\logo.ico'
            logo = Image.open(ruta_logo)

            # Asegúrate de que el objeto 'logo' sea de tipo 'PngImageFile'
            if isinstance(logo, Image.Image):
                var.report.line(50, 800, 525, 800)
                var.report.setFont('Helvetica-Bold', size=14)
                var.report.drawString(55, 785, 'Transportes Teis')
                var.report.drawString(240, 695, titulo)
                var.report.line(50, 690, 525, 690)

                # Dibuja la imagen en el informe
                var.report.drawImage(ruta_logo, 480, 725, width=40, height=40)

                var.report.setFont('Helvetica', size=9)
                var.report.drawString(55, 770, 'CIF: A12345678')
                var.report.drawString(55, 755, 'Avda. Galicia - 101')
                var.report.drawString(55, 740, 'Vigo - 36216 - España')
                var.report.drawString(55, 725, 'Teléfono: 986 132 456')
                var.report.drawString(55, 710, 'e-mail: cartesteisr@mail.com')
            else:
                print(f'Error: No se pudo cargar la imagen en {ruta_logo}')
        except Exception as error:
            print('Error en cabecera informe:', error)

    def footInforme(titulo):
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