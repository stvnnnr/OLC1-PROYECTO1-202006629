package Metodo_Arbol;

public class ListaTerminales {

    public NodoTerminal inicio;
    public NodoTerminal fin;
    public int size;

    public ListaTerminales() {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
    }

    public void AgregaTerminal(String terminal) {
        NodoTerminal nuevo = new NodoTerminal(terminal);
        if (this.VerificaTerminal(terminal)) {
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

    public boolean VerificaTerminal(String terminal) {
        NodoTerminal aux = this.inicio;
        while (aux != null) {
            if (aux.terminal.equals(terminal)) {
                return false;
            }
            if (terminal.equals("#")) {
                return false;
            }
            aux = aux.sig;
        }
        return true;
    }

    public NodoTerminal BuscaTerminal(String terminal) {
        NodoTerminal aux = this.inicio;
        while (aux != null) {
            if (aux.terminal.equals(terminal)) {
                return aux;
            }
            aux = aux.sig;
        }

        return null;
    }

}
