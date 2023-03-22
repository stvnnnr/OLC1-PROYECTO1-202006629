package estructuras;
import estructuras.*;

public class NodoExpRegular {

    public String id;
    public ListaExpresiones le;
    public NodoExpRegular sig;
    public ListaCadenas valor;

    public NodoExpRegular(String id,ListaExpresiones le) {
        this.id = id;
        this.le = le;
        this.valor=new ListaCadenas();
        this.sig = null;
    }
}
