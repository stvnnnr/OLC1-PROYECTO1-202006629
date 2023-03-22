
package Thompson;

public class Grafo {

    public NodoG inicio;
    public NodoG fin;
    public int size;

    public Grafo() {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
    }

    public int AgregarNodo(int id) {
        NodoG nuevo = new NodoG(id);
        if (this.inicio == null) {
            this.inicio = nuevo;
        }
        if (this.fin != null) {
            this.fin.sig = nuevo;
        }
        this.fin = nuevo;
        this.size += 1;
        return nuevo.id;
    }

    public NodoG BuscarNodo(int id) {
        NodoG aux = this.inicio;
        while (aux != null) {
            if (aux.id == id) {
                return aux;
            }
            aux = aux.sig;
        }
        return null;
    }

    public void AgregarTransicion(int id1, int id2, String peso) {
        NodoG actual = this.BuscarNodo(id1);
        if (actual != null) {
            actual.transition.AgregarTransicion(id2, peso);
        }
    }

    public NodoG ObtenerInicial() {
        NodoG aux = this.inicio;
        while (aux != null) {
            if (aux.inicial) {
                return aux;
            }
            aux = aux.sig;
        }
        return null;
    }

    public NodoG ObtenerFinal() {
        NodoG aux = this.inicio;
        while (aux != null) {
            if (aux.EsFinal) {
                return aux;
            }
            aux = aux.sig;
        }
        return null;
    }

    public Grafo UnificarGrafos1(Grafo gInicio, Grafo gFin) {
        //final aux.sig se une a inicio aux.sig.sig
        //eliminar grafo aux.sig y aux.sig.sig
        //guardar grafo nuevo        
        NodoG final1 = gInicio.ObtenerFinal();
        final1.EsFinal=false;
        NodoG inicio2 = gFin.ObtenerInicial();
        inicio2.inicial=false;
        int finalResultante=gFin.ObtenerFinal().id;

        //agregando los nodos de g2 a g1
        NodoG aux = gFin.inicio;
        while (aux != null) {
            if (aux.id!=inicio2.id) {
                gInicio.AgregarNodo(aux.id);
            }
            aux = aux.sig;
        }

        //agregando las transiciones de g2 a g1
        aux = gFin.inicio;
        while (aux != null) {
            NodoG aux2 = aux.transition.inicio;
            while (aux2 != null) {
                if(aux2.id!=inicio2.id && aux.id!=inicio2.id){
                    gInicio.AgregarTransicion(aux.id, aux2.id, aux2.peso);
                }else{
                    gInicio.AgregarTransicion(final1.id, aux2.id, aux2.peso);
                }
                aux2 = aux2.sig;
            }

            aux = aux.sig;
        }
        NodoG actualizaFinal= gInicio.BuscarNodo(finalResultante);
        actualizaFinal.EsFinal=true;
        return gInicio;

    }

}
