package Thompson;

public class NodoDoble {

    public String lexema;
    public String tipo;
    public int id;
    public NodoDoble ant;
    public NodoDoble sig;

    public NodoDoble(String lexema, String tipo, int id) {
        this.lexema = lexema;
        this.tipo = tipo;
        this.id = id;
        this.ant = null;
        this.sig = null;
    }
}
