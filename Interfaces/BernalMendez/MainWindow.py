# Form implementation generated from reading ui file 'MainWindow.ui'
#
# Created by: PyQt6 UI code generator 6.5.2
#
# WARNING: Any manual changes made to this file will be lost when pyuic6 is
# run again.  Do not edit this file unless you know what you are doing.


from PyQt6 import QtCore, QtGui, QtWidgets


class Ui_mainWindow(object):
    def setupUi(self, mainWindow):
        mainWindow.setObjectName("mainWindow")
        mainWindow.resize(978, 600)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Policy.Expanding, QtWidgets.QSizePolicy.Policy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(mainWindow.sizePolicy().hasHeightForWidth())
        mainWindow.setSizePolicy(sizePolicy)
        mainWindow.setMinimumSize(QtCore.QSize(800, 600))
        font = QtGui.QFont()
        font.setPointSize(9)
        mainWindow.setFont(font)
        self.centralwidget = QtWidgets.QWidget(parent=mainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.gridLayout = QtWidgets.QGridLayout(self.centralwidget)
        self.gridLayout.setObjectName("gridLayout")
        self.tabWidget = QtWidgets.QTabWidget(parent=self.centralwidget)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Policy.Expanding, QtWidgets.QSizePolicy.Policy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.tabWidget.sizePolicy().hasHeightForWidth())
        self.tabWidget.setSizePolicy(sizePolicy)
        self.tabWidget.setMinimumSize(QtCore.QSize(120, 20))
        self.tabWidget.setMaximumSize(QtCore.QSize(1090, 850))
        self.tabWidget.setObjectName("tabWidget")
        self.tab = QtWidgets.QWidget()
        self.tab.setObjectName("tab")
        self.formLayout_2 = QtWidgets.QFormLayout(self.tab)
        self.formLayout_2.setObjectName("formLayout_2")
        self.btnSalir = QtWidgets.QPushButton(parent=self.tab)
        self.btnSalir.setObjectName("btnSalir")
        self.formLayout_2.setWidget(1, QtWidgets.QFormLayout.ItemRole.FieldRole, self.btnSalir)
        self.frame = QtWidgets.QFrame(parent=self.tab)
        self.frame.setFrameShape(QtWidgets.QFrame.Shape.StyledPanel)
        self.frame.setFrameShadow(QtWidgets.QFrame.Shadow.Sunken)
        self.frame.setLineWidth(2)
        self.frame.setObjectName("frame")
        self.gridLayout_2 = QtWidgets.QGridLayout(self.frame)
        self.gridLayout_2.setObjectName("gridLayout_2")
        self.lineDireccion = QtWidgets.QLineEdit(parent=self.frame)
        self.lineDireccion.setObjectName("lineDireccion")
        self.gridLayout_2.addWidget(self.lineDireccion, 4, 1, 1, 5)
        self.lineApellidos = QtWidgets.QLineEdit(parent=self.frame)
        self.lineApellidos.setObjectName("lineApellidos")
        self.gridLayout_2.addWidget(self.lineApellidos, 2, 1, 1, 5)
        self.labelDNI = QtWidgets.QLabel(parent=self.frame)
        self.labelDNI.setText("")
        self.labelDNI.setObjectName("labelDNI")
        self.gridLayout_2.addWidget(self.labelDNI, 0, 8, 1, 1)
        self.lineDNI = QtWidgets.QLineEdit(parent=self.frame)
        self.lineDNI.setObjectName("lineDNI")
        self.gridLayout_2.addWidget(self.lineDNI, 0, 5, 1, 1)
        self.txtDNI = QtWidgets.QLabel(parent=self.frame)
        self.txtDNI.setObjectName("txtDNI")
        self.gridLayout_2.addWidget(self.txtDNI, 0, 4, 1, 1)
        self.txtCodigo = QtWidgets.QLabel(parent=self.frame)
        self.txtCodigo.setObjectName("txtCodigo")
        self.gridLayout_2.addWidget(self.txtCodigo, 0, 0, 1, 1)
        self.lineCodigo = QtWidgets.QLineEdit(parent=self.frame)
        self.lineCodigo.setAutoFillBackground(False)
        self.lineCodigo.setObjectName("lineCodigo")
        self.gridLayout_2.addWidget(self.lineCodigo, 0, 1, 1, 2)
        self.txtApellidos = QtWidgets.QLabel(parent=self.frame)
        self.txtApellidos.setObjectName("txtApellidos")
        self.gridLayout_2.addWidget(self.txtApellidos, 2, 0, 1, 1)
        self.labelDireccion = QtWidgets.QLabel(parent=self.frame)
        self.labelDireccion.setObjectName("labelDireccion")
        self.gridLayout_2.addWidget(self.labelDireccion, 4, 0, 1, 1)
        self.labelMovil = QtWidgets.QLabel(parent=self.frame)
        self.labelMovil.setObjectName("labelMovil")
        self.gridLayout_2.addWidget(self.labelMovil, 7, 0, 1, 1)
        self.lineMovil = QtWidgets.QLineEdit(parent=self.frame)
        self.lineMovil.setObjectName("lineMovil")
        self.gridLayout_2.addWidget(self.lineMovil, 7, 1, 1, 3)
        self.lineSalario = QtWidgets.QLineEdit(parent=self.frame)
        self.lineSalario.setObjectName("lineSalario")
        self.gridLayout_2.addWidget(self.lineSalario, 7, 5, 1, 1)
        self.labelSalario = QtWidgets.QLabel(parent=self.frame)
        self.labelSalario.setObjectName("labelSalario")
        self.gridLayout_2.addWidget(self.labelSalario, 7, 4, 1, 1)
        self.labelProvincia = QtWidgets.QLabel(parent=self.frame)
        self.labelProvincia.setObjectName("labelProvincia")
        self.gridLayout_2.addWidget(self.labelProvincia, 4, 6, 1, 1)
        self.txtNombre = QtWidgets.QLabel(parent=self.frame)
        self.txtNombre.setObjectName("txtNombre")
        self.gridLayout_2.addWidget(self.txtNombre, 2, 6, 1, 1)
        self.txtFechaAlta = QtWidgets.QLabel(parent=self.frame)
        self.txtFechaAlta.setObjectName("txtFechaAlta")
        self.gridLayout_2.addWidget(self.txtFechaAlta, 0, 6, 1, 1)
        self.btnCalendar = QtWidgets.QPushButton(parent=self.frame)
        self.btnCalendar.setAutoFillBackground(False)
        self.btnCalendar.setText("")
        icon = QtGui.QIcon()
        icon.addPixmap(QtGui.QPixmap("img/calendar-icon.jpg"), QtGui.QIcon.Mode.Normal, QtGui.QIcon.State.Off)
        self.btnCalendar.setIcon(icon)
        self.btnCalendar.setIconSize(QtCore.QSize(30, 30))
        self.btnCalendar.setObjectName("btnCalendar")
        self.gridLayout_2.addWidget(self.btnCalendar, 0, 10, 1, 1)
        self.lineFechaAlta = QtWidgets.QLineEdit(parent=self.frame)
        self.lineFechaAlta.setObjectName("lineFechaAlta")
        self.gridLayout_2.addWidget(self.lineFechaAlta, 0, 7, 1, 1)
        self.lineNombre = QtWidgets.QLineEdit(parent=self.frame)
        self.lineNombre.setObjectName("lineNombre")
        self.gridLayout_2.addWidget(self.lineNombre, 2, 7, 1, 1)
        self.comboBoxProvincia = QtWidgets.QComboBox(parent=self.frame)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Policy.Expanding, QtWidgets.QSizePolicy.Policy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.comboBoxProvincia.sizePolicy().hasHeightForWidth())
        self.comboBoxProvincia.setSizePolicy(sizePolicy)
        self.comboBoxProvincia.setObjectName("comboBoxProvincia")
        self.gridLayout_2.addWidget(self.comboBoxProvincia, 4, 7, 1, 1)
        self.labelLocalidad = QtWidgets.QLabel(parent=self.frame)
        self.labelLocalidad.setObjectName("labelLocalidad")
        self.gridLayout_2.addWidget(self.labelLocalidad, 7, 6, 1, 1)
        self.comboBoxLocalidad = QtWidgets.QComboBox(parent=self.frame)
        self.comboBoxLocalidad.setObjectName("comboBoxLocalidad")
        self.gridLayout_2.addWidget(self.comboBoxLocalidad, 7, 7, 1, 1)
        self.formLayout_2.setWidget(0, QtWidgets.QFormLayout.ItemRole.SpanningRole, self.frame)
        self.tabWidget.addTab(self.tab, "")
        self.tab_2 = QtWidgets.QWidget()
        self.tab_2.setObjectName("tab_2")
        self.gridLayout_3 = QtWidgets.QGridLayout(self.tab_2)
        self.gridLayout_3.setObjectName("gridLayout_3")
        self.lblTitulo_2 = QtWidgets.QLabel(parent=self.tab_2)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Policy.Expanding, QtWidgets.QSizePolicy.Policy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.lblTitulo_2.sizePolicy().hasHeightForWidth())
        self.lblTitulo_2.setSizePolicy(sizePolicy)
        self.lblTitulo_2.setMinimumSize(QtCore.QSize(120, 20))
        self.lblTitulo_2.setMaximumSize(QtCore.QSize(240, 40))
        font = QtGui.QFont()
        font.setPointSize(11)
        font.setBold(True)
        font.setWeight(75)
        self.lblTitulo_2.setFont(font)
        self.lblTitulo_2.setAlignment(QtCore.Qt.AlignmentFlag.AlignCenter)
        self.lblTitulo_2.setObjectName("lblTitulo_2")
        self.gridLayout_3.addWidget(self.lblTitulo_2, 0, 0, 1, 1)
        self.tabWidget.addTab(self.tab_2, "")
        self.gridLayout.addWidget(self.tabWidget, 0, 0, 1, 1)
        mainWindow.setCentralWidget(self.centralwidget)
        self.menuBar = QtWidgets.QMenuBar(parent=mainWindow)
        self.menuBar.setGeometry(QtCore.QRect(0, 0, 978, 21))
        self.menuBar.setObjectName("menuBar")
        self.menuArchivo = QtWidgets.QMenu(parent=self.menuBar)
        self.menuArchivo.setObjectName("menuArchivo")
        self.menuAyuda = QtWidgets.QMenu(parent=self.menuBar)
        self.menuAyuda.setObjectName("menuAyuda")
        mainWindow.setMenuBar(self.menuBar)
        self.statusBar = QtWidgets.QStatusBar(parent=mainWindow)
        self.statusBar.setObjectName("statusBar")
        mainWindow.setStatusBar(self.statusBar)
        self.actionSalir = QtGui.QAction(parent=mainWindow)
        self.actionSalir.setObjectName("actionSalir")
        self.actionSalir_2 = QtGui.QAction(parent=mainWindow)
        self.actionSalir_2.setObjectName("actionSalir_2")
        self.actionAcerca_de = QtGui.QAction(parent=mainWindow)
        self.actionAcerca_de.setObjectName("actionAcerca_de")
        self.menuArchivo.addAction(self.actionSalir_2)
        self.menuAyuda.addAction(self.actionAcerca_de)
        self.menuBar.addAction(self.menuArchivo.menuAction())
        self.menuBar.addAction(self.menuAyuda.menuAction())

        self.retranslateUi(mainWindow)
        self.tabWidget.setCurrentIndex(0)
        QtCore.QMetaObject.connectSlotsByName(mainWindow)

    def retranslateUi(self, mainWindow):
        _translate = QtCore.QCoreApplication.translate
        mainWindow.setWindowTitle(_translate("mainWindow", "CarTeis"))
        self.btnSalir.setText(_translate("mainWindow", "SALIR"))
        self.txtDNI.setText(_translate("mainWindow", "DNI:"))
        self.txtCodigo.setText(_translate("mainWindow", "Código"))
        self.txtApellidos.setText(_translate("mainWindow", "Apellidos:"))
        self.labelDireccion.setText(_translate("mainWindow", "Dirección:"))
        self.labelMovil.setText(_translate("mainWindow", "Móvil:"))
        self.labelSalario.setText(_translate("mainWindow", "Salario:"))
        self.labelProvincia.setText(_translate("mainWindow", "Provincia:"))
        self.txtNombre.setText(_translate("mainWindow", "Nombre"))
        self.txtFechaAlta.setText(_translate("mainWindow", "Fecha Alta:"))
        self.labelLocalidad.setText(_translate("mainWindow", "Localidad:"))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab), _translate("mainWindow", "Conductor"))
        self.lblTitulo_2.setText(_translate("mainWindow", "Segunda pestaña"))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab_2), _translate("mainWindow", "Tab 2"))
        self.menuArchivo.setTitle(_translate("mainWindow", "Archivo"))
        self.menuAyuda.setTitle(_translate("mainWindow", "Ayuda"))
        self.actionSalir.setText(_translate("mainWindow", "Salir"))
        self.actionSalir_2.setText(_translate("mainWindow", "Salir"))
        self.actionAcerca_de.setText(_translate("mainWindow", "Acerca de..."))