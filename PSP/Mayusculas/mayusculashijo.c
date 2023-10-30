#include <stdio.h>
#include <string.h>
#include <ctype.h>
int main(int argc, char **argv)
{
        char buf[1024];
        int longitud;
        int i;
        
        char fin[2];
        fin[0]='f';
        fin[1]='f';
        // Cadena para terminar proceso, cuando se reciba "ff" se termina proceso hijo
        fgets(buf, 1024, stdin);

        while( buf[0]!='f' && buf[1]!='f' ) {
                longitud = strlen(buf);
                for(i = 0; i < longitud; i++) {
                        buf[i] = toupper(buf[i]);
                }
                fputs(buf, stdout);// Envio cadena tratada al proceso padre
                fflush(stdout); // IMP: para asegurar que se reciben los datos
                fgets(buf, 1024, stdin); // Sigo leyendo
        } // Fin while hemos recibido "ff"
        
        fputs("kk", stdout); // Envio al proceso padre se�al "kk"
        
        return 0; // Termine correcto
}
