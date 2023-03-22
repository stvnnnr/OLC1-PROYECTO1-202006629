package estructuras;

public class ListaExpRegular {

    public NodoExpRegular inicio;
    public NodoExpRegular fin;

    ;

    public ListaExpRegular() {
        this.inicio = null;
        this.fin = null;
    }

    public void AgregarExp(String id, ListaExpresiones le) {
        NodoExpRegular nuevo = new NodoExpRegular(id, le);
        if (this.inicio == null) {
            this.inicio = nuevo;
        }
        if (this.fin != null) {
            this.fin.sig = nuevo;
        }
        this.fin = nuevo;
    }

    public void ActualizaValor(String id, String valor) {
        NodoExpRegular aux = this.inicio;
        while (aux != null) {
            if (aux.id.equals(id)) {
                aux.valor.AgregarCadena(valor);
                break;
            }
            aux = aux.sig;
        }
    }

}
