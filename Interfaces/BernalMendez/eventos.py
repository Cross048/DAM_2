import var, sys

class Eventos():
    def salir(self):
        try:
            sys.exit(0)
        except Exception as error:
            print(error, "en módulo eventos")

    def abrirCalendar(self):
        try:
            var.calendar.show()
        except Exception as error:
            print('error en abrir calendar', error)
    
    def acercaDe(self):
        try:
            pass
        except Exception as error:
            print("error abrir ventana acerca de", error)