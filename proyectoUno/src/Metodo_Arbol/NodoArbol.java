package Metodo_Arbol;

public class NodoArbol {

    public String lexema;
    public String tipo;
    public int id;
    public int id_grafica;
    public boolean anulable;
    //otros atributos
    public ListaPosiciones primeros;
    public ListaPosiciones ultimos;
    public NodoArbol izq = null;
    public NodoArbol der = null;

    public NodoArbol(String lexema, String tipo) {
        this.lexema = lexema;
        this.tipo = tipo;
        this.id_grafica=0;
        this.id=-1;
        this.anulable=false;
        this.primeros=new ListaPosiciones();
        this.ultimos=new ListaPosiciones();
        
    }

    public boolean EsHoja() {
        if (this.izq == null && this.der == null) {
            return true;
        }
        return false;
    }
    
    public void AgregarPrimero(int pos){
        this.primeros.AgregarPosiciones(pos);
    }
    
    public void AgregarUltimo(int pos){
        this.ultimos.AgregarPosiciones(pos);
    }

}
