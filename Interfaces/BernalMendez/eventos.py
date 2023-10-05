import var

class Eventos():
    def salir(self):
        try:
            sys.exit(0)
        except Exception as eror:
            print(error, "en módulo eventos")

    def abrirCalendar(self):
        try:
            var.calendar.show()
        except Exception as error:
            print('error en abrir calendar', error)
    
    def acercade():
        try:
            pass
        except Exception as eror:
            print("error abrir ventana acerca de", error)