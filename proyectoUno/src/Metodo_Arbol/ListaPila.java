package Metodo_Arbol;

public class ListaPila {

    public NodoPila inicio;
    public NodoPila fin;
    public int size;

    public ListaPila() {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
    }

    public void Apilar(NodoArbol nb) {
        NodoPila nuevo = new NodoPila(nb);
        if (this.inicio == null) {
            this.inicio = nuevo;
        }
        if (this.fin != null) {
            this.fin.sig = nuevo;
        }
        this.fin = nuevo;
        this.size += 1;
    }

    public void Eliminar(NodoArbol na) {
        if (this.inicio == this.fin) {
            if (this.inicio.nodo == na) {
                this.inicio = this.fin = null;
            }

        } else if (this.inicio.nodo == na) {
            this.inicio = this.inicio.sig;
        } else {
            NodoPila anterior, temporal;
            temporal = this.inicio.sig;
            anterior = this.inicio;
            while (temporal != null && temporal.nodo!=na) {
                anterior=anterior.sig;
                temporal=temporal.sig;
            }
            if(temporal!=null){
                anterior.sig=temporal.sig;              
            }
        }
        this.ActualizaFin();
        this.size -= 1;
    }

    private void ActualizaFin() {
        NodoPila aux = this.inicio;
        while (aux != null) {
            if (aux.sig == null) {
                this.fin = aux;
                break;
            }
            aux = aux.sig;
        }
    }
}
