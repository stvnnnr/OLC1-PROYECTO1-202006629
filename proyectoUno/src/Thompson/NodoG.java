package Thompson;

public class NodoG {

    public int id;
    public String peso;
    boolean inicial;
    boolean EsFinal;
    public Transiciones transition;
    NodoG sig;

    public NodoG(int id) {
        this.id = id;
        this.peso = "";
        this.transition = new Transiciones();
        this.sig = null;
        this.inicial = false;
        this.EsFinal = false;
    }

}
