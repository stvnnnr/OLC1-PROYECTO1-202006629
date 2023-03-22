
package Thompson;

public class NodoLG {

    public int id;
    public Grafo g;
    public NodoLG sig;
    public NodoLG ant;

    public NodoLG(int id, Grafo g) {
        this.id = id;
        this.g = g;
        this.sig = null;
        this.ant = null;
    }

}
