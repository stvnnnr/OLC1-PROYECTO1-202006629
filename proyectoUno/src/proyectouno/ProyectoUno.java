package proyectouno;
import ventana.interfaz;
import estructuras.*;
public class ProyectoUno {
    public static ListaErrores errores;
    public static ListaExpRegular regularExpression;
    public static ListaConjuntos conjuntos;
  
    public static void main(String[] args) {
        System.out.println("Hello World!");
        interfaz ventanita = new interfaz();
        ventanita.setVisible(true);
        errores = new ListaErrores();
        regularExpression = new ListaExpRegular();
        conjuntos = new ListaConjuntos();
    }
    
}
