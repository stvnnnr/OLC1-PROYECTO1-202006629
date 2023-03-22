package estructuras;

public class ListaCadenas {

    public NodoCadena inicio;
    public NodoCadena fin;

    public ListaCadenas() {
        this.inicio = null;
        this.fin = null;
    }

    public void AgregarCadena(String cadena) {
        NodoCadena nuevo = new NodoCadena(cadena);
        if (this.inicio == null) {
            this.inicio = nuevo;
        }
        if (this.fin != null) {
            this.fin.sig = nuevo;
        }
        this.fin = nuevo;
    }
}
