package estructuras;

public class NodoCaracter {

    public String caracter;
    public NodoCaracter sig = null;

    public NodoCaracter(String caracter) {
        this.caracter = caracter;
    }

    public int ascii() {
        char b = this.caracter.charAt(0);
        int c = (int) b;
        return c;
    }

}
