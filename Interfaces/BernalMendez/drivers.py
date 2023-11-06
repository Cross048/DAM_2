import conexion
import var

class Drivers():
    def validarDNI(self):
        try:
            dni = var.ui.lineDNI.text()
            dni = dni.upper()
            var.ui.lineDNI.setText(dni)
            tabla = "TRWAGMYFPDXBNJZSQVHLCKE"
            dig_ext = "XYZ"
            reemp_dig_ext = {'X':'0', 'Y':'1', 'Z':'2'}
            numeros = "1234567890"
            if len(dni) == 9:          # Comprueba que son nueve
                dig_control = dni[8]   # Tomo la letra del DNI
                dni = dni[:8]          # Tomo los números del DNI
                if dni[0] in dig_ext:
                    dni = dni.replace(dni[0], reemp_dig_ext[dni[0]])
                if len(dni) == len([n for n in dni if n in numeros]) and tabla[int(dni) % 23] == dig_control:
                    var.ui.labelDNI.setStyleSheet('color:green;')
                    var.ui.labelDNI.setText('V')
                else:
                    var.ui.labelDNI.setStyleSheet('color:red;')
                    var.ui.labelDNI.setText('F')
                    var.ui.txtDNI.setText(None)
                    var.ui.txtDNI.setFocus()
            else:
                var.ui.labelValidarDNI.setStyleSheet('color:red;')
                var.ui.lavelValidarDNI.setText('F')
                var.ui.txtDNI.setText(None)
                var.ui.txtDNI.setFocus()
        except Exception as error:
            print("error en validar DNI", error)

    def cargardriver(self):
        try:
            Drivers.limpiapanel()
            row = var.ui.tabClientes.selectedItems()
            fila = [ dato.text() for dato in row ]
            registro = conexion.Conexion.onedriver(fila[0])
            print(registro)
            datos = [var.ui.lblcodbd, var.ui.lineDNI, var.ui.txtDataDriver, var.ui.lineApellidos, var.ui.lineNombre,
            var.ui.txtDirdriver, var.ui.comboBoxProvincia, var.ui.comboBoxLocalidad, var.ui.lineMovil, var.ui.lineSalario]
            for i in datos:
                datos[i].setText(str(registro[i]))
                
        except Exception as error:
            print('Error cargar datos de 1 cliente marcando en la tabla: ', error)
