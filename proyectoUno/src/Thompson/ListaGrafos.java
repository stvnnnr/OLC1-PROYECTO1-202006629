
package Thompson;

public class ListaGrafos {

    public NodoLG inicio;
    public NodoLG fin;
    public int size;
    public int contador = 0;

    public ListaGrafos() {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
        this.contador = 0;
    }

    public int AgregarGrafo(Grafo g) {
        NodoLG nuevo = new NodoLG(this.contador, g);
        if (this.inicio == null) {
            this.inicio = nuevo;
        } else {
            this.fin.sig = nuevo;
            nuevo.ant = this.fin;
        }
        this.fin = nuevo;
        this.size += 1;
        this.contador += 1;

        return nuevo.id;
    }

    public NodoLG BuscaGrafo(int id) {
        NodoLG aux = this.inicio;
        while (aux != null) {
            if (aux.id == id) {
                return aux;
            }
            aux = aux.sig;
        }
        return null;
    }

    public void EliminarGrafo(int id) {
        if (this.inicio == this.fin && this.inicio.id == id) {
            this.inicio = this.fin = null;
            this.size = 0;
        } else if (this.inicio.id == id) {
            this.inicio = this.inicio.sig;
            this.inicio.ant = null;
            this.size -= 1;
        } else if (this.fin.id == id) {
            this.fin = this.fin.ant;
            this.fin.sig = null;
            this.size -= 1;
        } else {
            NodoLG temporal = this.BuscaGrafo(id);
            if (temporal != null) {
                temporal.ant.sig = temporal.sig;
                temporal.sig.ant = temporal.ant;
                this.size -= 1;
            }
        }
    }

}
