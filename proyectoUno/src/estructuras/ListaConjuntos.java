package estructuras;

public class ListaConjuntos {

    public NodoConjunto inicio;
    public NodoConjunto fin;

    public ListaConjuntos() {
        this.inicio = null;
        this.fin = null;
    }

    public void AgregarConjunto(NodoConjunto nuevo) {
        if (this.inicio == null) {
            this.inicio = nuevo;
        }
        if (this.fin != null) {
            this.fin.sig = nuevo;
        }
        this.fin = nuevo;
    }

    public NodoConjunto ObtenerConjunto(String id) {
        NodoConjunto aux = this.inicio;
        while (aux != null) {
            if (aux.nombre.equals(id)) {
                return aux;
            }
            aux=aux.sig;
        }
        return null;
    }

    public boolean VerificaConjunto(String id) {
        NodoConjunto aux = this.inicio;
        while (aux != null) {
            if (aux.nombre.equals(id)) {
                return true;
            }
            aux = aux.sig;
        }
        return false;
    }

    public boolean CumpleRegla(NodoConjunto nc, char c) {
        int asciiC = (int) c;
        if (nc.rango) {
            int inicial = nc.regla.inicio.ascii();
            int fin = nc.regla.fin.ascii();

            return (inicial <= asciiC && asciiC <= fin);

        } else {
            NodoCaracter aux = nc.regla.inicio;
            while (aux != null) {
                if (aux.ascii() == asciiC) {
                    return true;
                }
                aux = aux.sig;
            }
        }
        return false;
    }

}
