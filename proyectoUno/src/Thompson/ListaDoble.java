package Thompson;

public class ListaDoble {

    public NodoDoble inicio;
    public NodoDoble fin;
    public int size;

    public ListaDoble() {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
    }

    public void Insertar(String lex, String tip) {
        NodoDoble nuevo = new NodoDoble(lex, tip, this.size);

        if (this.inicio == null) {
            this.inicio = nuevo;
        } else {
            this.fin.sig = nuevo;
            nuevo.ant = this.fin;
        }
        this.fin = nuevo;
        this.size += 1;
    }

    public NodoDoble Buscar(int id) {
        NodoDoble aux = this.inicio;
        while (aux != null) {
            if (aux.id == id) {
                return aux;
            }
            aux = aux.sig;
        }
        return null;
    }

    public void Actualizar(int id, String tipo, String lexema) {
        NodoDoble nodo = this.Buscar(id);
        if (nodo != null) {
            nodo.lexema = lexema;
            nodo.tipo = tipo;
        }
    }

    public void Eliminar(int id) {
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
            NodoDoble temporal = this.Buscar(id);
            if (temporal != null) {
                temporal.ant.sig = temporal.sig;
                temporal.sig.ant = temporal.ant;
                this.size -= 1;
            }
        }
    }

}
