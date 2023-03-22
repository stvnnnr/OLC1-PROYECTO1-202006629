package Metodo_Arbol;


public class NodoTransicion {

    public int estado;
    public ListaPosiciones pos;
    public ListaTerminales terminales;
    public NodoTransicion sig;
    public boolean inicial;
    public boolean aceptacion;

    public NodoTransicion(int estado) {
        this.estado = estado;
        this.pos = null;
        this.terminales = new ListaTerminales();
        this.sig = null;
        this.aceptacion = false;
        this.inicial = false;
    }

}
