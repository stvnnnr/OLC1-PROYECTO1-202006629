package estructuras;

public class NodoError {
    public String tipo;
    public String desc;
    public int linea;
    public int col;
    public NodoError sig=null;
    

    public NodoError(String tipo, String desc, int linea, int col) {
        this.tipo = tipo;
        this.desc = desc;
        this.linea = linea;
        this.col = col;
    }
    
    
}
