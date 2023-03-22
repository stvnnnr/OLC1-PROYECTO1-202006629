
package Metodo_Arbol;

public class NodoSiguientes {

    public String Terminal;
    public int id_hoja;
    public ListaPosiciones siguientes;
    public NodoSiguientes sig;

    public NodoSiguientes(String t, int ih) {
        this.Terminal = t;
        this.id_hoja = ih;
        this.siguientes = new ListaPosiciones();
        this.sig = null;
    }

}
