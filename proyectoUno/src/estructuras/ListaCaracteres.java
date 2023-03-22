package estructuras;

public class ListaCaracteres {

    NodoCaracter inicio;
    NodoCaracter fin;

    public ListaCaracteres() {
        this.inicio = null;
        this.fin = null;
    }

    public void AgregarCaracter(String caracter) {
        NodoCaracter nuevo = new NodoCaracter(caracter);
        if (this.inicio == null) {
            this.inicio = nuevo;
        }
        if (this.fin != null) {
            this.fin.sig = nuevo;
        }
        this.fin = nuevo;
    }
    
    

}
