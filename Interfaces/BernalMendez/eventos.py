import var

class Eventos():
    def saludar(self):
        try:
            var.ui.lbTitulo("Hola has pulsado el botón")
        except Exception as eror:
            print(error, "en módulo eventos")