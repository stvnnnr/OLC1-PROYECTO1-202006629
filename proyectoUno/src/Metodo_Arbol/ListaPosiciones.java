
package Metodo_Arbol;

public class ListaPosiciones {

    public NodoPosicion inicio;
    public NodoPosicion fin;
    public int size;

    public ListaPosiciones() {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
    }

    public void AgregarPosiciones(int pos) {
        NodoPosicion nuevo = new NodoPosicion(pos);
        if (this.VerificaExistencia(pos)) {
            if (this.inicio == null) {
                this.inicio = nuevo;
            }
            if (this.fin != null) {
                this.fin.sig = nuevo;
            }
            this.fin = nuevo;
            this.size += 1;
        }

    }

    public boolean VerificaExistencia(int pos) {
        NodoPosicion aux = this.inicio;
        while (aux != null) {
            if (aux.posicion == pos) {
                return false;
            }
            aux = aux.sig;
        }
        return true;
    }

    public String CadenaListada() {
        String cadena = "";
        NodoPosicion aux = this.inicio;
        while (aux != null) {
            if (aux == this.inicio) {
                cadena += "" + aux.posicion;
            } else {
                cadena += " ," + aux.posicion;
            }
            aux = aux.sig;
        }
        return cadena;
    }

    public boolean SonIguales(ListaPosiciones lp) {
        int CantIguales = 0;
        if (this.size != lp.size) {
            return false;
        } else {
            NodoPosicion aux = lp.inicio;
            while (aux != null) {
                NodoPosicion aux2 = this.inicio;
                while (aux2 != null) {
                    if (aux2.posicion == aux.posicion) {
                        CantIguales += 1;
                    }
                    aux2 = aux2.sig;
                }
                aux = aux.sig;
            }
            return CantIguales == this.size;
        }
    }

    public boolean EstaEnLista(int pos) {
        NodoPosicion aux = this.inicio;
        while (aux != null) {
            if (aux.posicion == pos) {
                return true;
            }
            aux = aux.sig;
        }
        return false;
    }
}
