import locale
import sys

import clientes
import informes
from MainWindow import *
from drivers import *
from windowaux import *

# Establecer la configuración regional en español
locale.setlocale(locale.LC_TIME, 'es_ES.UTF-8')
locale.setlocale(locale.LC_MONETARY, 'es_ES.UTF-8')

class Main(QtWidgets.QMainWindow):
    def __init__(self):
        super(Main, self).__init__()
        var.ui = Ui_MainWindow()
        var.ui.setupUi(self)
        var.calendar = Calendar()
        var.dlgacerca = DlgAcerca()
        var.dlgabrir = FileDialogAbrir()
        self.driver = Drivers()
        conexion.Conexion.conexion()
        conexion.Conexion.cargaprov()
        estado = 1
        conexion.Conexion.selectDrivers(estado)
        conexion.Conexion.selectClientes(estado)

        ''' zona de eventos de botones '''
        var.ui.btnCalendar.clicked.connect(eventos.Eventos.abrirCalendar)
        var.ui.btnAltaDriver.clicked.connect(drivers.Drivers.altadriver)
        var.ui.btnBuscadri.clicked.connect(drivers.Drivers.buscaDri)
        var.ui.btnModifDriver.clicked.connect(drivers.Drivers.modifDri)
        var.ui.btnBajaDriver.clicked.connect(drivers.Drivers.borraDriv)
        var.ui.btnAltaDriver_2.clicked.connect(clientes.Clientes.altaCliente)
        var.ui.btnModifDriver_2.clicked.connect(clientes.Clientes.modifCliente)

        ''' zona de eventos del menubar '''
        var.ui.actionSalir.triggered.connect(eventos.Eventos.mostrarsalir)
        var.ui.actionAcerca_de.triggered.connect(eventos.Eventos.acercade)
        var.ui.actionCrear_Copia_Seguridad.triggered.connect(eventos.Eventos.crearbackup)
        var.ui.actionRestaurar_Copia_Seguridad.triggered.connect(eventos.Eventos.restaurarbackup)
        var.ui.actionExportar_Datos_Excel.triggered.connect(eventos.Eventos.exportardatosclientesxls)
        var.ui.actionImportar_Datos_XLS.triggered.connect(eventos.Eventos.importarDatosclientesExcel)
        var.ui.actionGenerar_informe.triggered.connect(informes.Informes.reportdrivers)
        var.ui.actionGenerar_informe_clientes.triggered.connect(informes.Informes.reportclientes)

        ''' zona eventos cajas de texto '''
        var.ui.txtDni.editingFinished.connect(lambda: drivers.Drivers.validarDNI(var.ui.txtDni.text()))
        var.ui.txtNome.editingFinished.connect(eventos.Eventos.formatCajatexto)
        var.ui.txtApel.editingFinished.connect(eventos.Eventos.formatCajatexto)
        var.ui.txtSalario.editingFinished.connect(eventos.Eventos.formatCajatexto)
        var.ui.txtMovil.editingFinished.connect(eventos.Eventos.formatCajamovil)

        ''' eventos del toolbar '''
        var.ui.actionbarSalir.triggered.connect(eventos.Eventos.mostrarsalir)
        var.ui.actionlimpiaPaneldriver.triggered.connect(drivers.Drivers.limpiapanel)
        var.ui.actioncrearbackup.triggered.connect(eventos.Eventos.crearbackup)
        var.ui.actionrestaurarbackup.triggered.connect(eventos.Eventos.restaurarbackup)


        ''' eventos de tablas '''
        eventos.Eventos.resizeTabdrivers(self)
        var.ui.tabDrivers.clicked.connect(drivers.Drivers.cargadriver)
        eventos.Eventos.resizeTabclientes(self)
        var.ui.tabDrivers_2.clicked.connect(clientes.Clientes.cargacliente)

        ''' eventos combobox '''
        var.ui.cmbProv.currentIndexChanged.connect(conexion.Conexion.selMuni)
        var.ui.rtbGroup.buttonClicked.connect(drivers.Drivers.selEstado)
        conexion.Conexion.cargaprov2()
        var.ui.txtRazonSocial.editingFinished.connect(eventos.Eventos.formatCajatexto2)
        var.ui.txtMovil_2.editingFinished.connect(eventos.Eventos.formatCajamovil2)
        var.ui.cmbProv_2.currentIndexChanged.connect(conexion.Conexion.selMuni2)

    def closeEvent(self, event):
        mbox = QtWidgets.QMessageBox.information(self, "Salir", "¿Estás seguro de que quieres salir?",
                                                 QtWidgets.QMessageBox.StandardButton.Yes |
                                                 QtWidgets.QMessageBox.StandardButton.No)
        if mbox == QtWidgets.QMessageBox.StandardButton.Yes:
            event.accept()
        else:
            event.ignore()

if __name__ == '__main__':
    try:
        app = QtWidgets.QApplication([])
        window = Main()
        window.showMaximized()
        sys.exit(app.exec())
    except Exception as error:
        print(error)
