
package Metodo_Arbol;

public class NodoTerminal {

    public String terminal;
    public int estado;
    public NodoTerminal sig;
    public ListaPosiciones listapos;

    public NodoTerminal(String terminal) {
        this.terminal = terminal;
        this.sig = null;
        this.listapos = new ListaPosiciones();
        this.estado = -1;
    }

}
