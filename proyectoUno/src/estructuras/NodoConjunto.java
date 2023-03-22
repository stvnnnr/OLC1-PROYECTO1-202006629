package estructuras;

public class NodoConjunto {

    public String nombre;
    public boolean rango;
    public ListaCaracteres regla=new ListaCaracteres();
    NodoConjunto sig = null;

    public NodoConjunto(String nombre) {
        this.nombre = nombre;
    } 

    public void setRango(boolean rango) {
        this.rango = rango;
    }
    

    public void AgregaReglas(String caracter) {
        this.regla.AgregarCaracter(caracter);
    }

}
