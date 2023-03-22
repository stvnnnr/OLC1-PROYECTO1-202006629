package Metodo_Arbol;

import com.itextpdf.text.DocumentException;
import estructuras.ListaConjuntos;
import estructuras.ListaExpresiones;
import estructuras.NodoConjunto;
import estructuras.NodoExpresion;
import java.io.FileNotFoundException;
import java.io.IOException;
import static proyectouno.ProyectoUno.conjuntos;

public class MetodoArbol {

    public String identificador;
    public ListaExpresiones expresiones;
    public Arbol a;
    public ListaSiguientes siguientes;
    public ListaTransiciones transiciones;

    public MetodoArbol(ListaExpresiones expresiones, String id) {
        this.identificador = id;
        this.expresiones = expresiones;
        this.a = new Arbol(id);
        this.CargarArbol();
        this.siguientes = new ListaSiguientes(id);
        this.transiciones = new ListaTransiciones(id);
    }

    public void CargarArbol() {
        NodoExpresion aux = this.expresiones.inicio;
        this.a.AgregarNodo(".", "CONCAT1");
        while (aux != null) {

            this.a.AgregarNodo(aux.lexema, aux.tipo);
            aux = aux.sig;
        }
        this.a.AgregarNodo("#", "FINCADENA");
    }

    public boolean EscaneaCadena(String analizarCadena) {
        String cadena = analizarCadena;
        int estadoActual = 0;
        String buffer = "";
        ListaConjuntos conjAux = conjuntos;

        for (int i = 1; i < cadena.length(); i++) {
            if (estadoActual == -1) {
                return false;
            }
            char c = cadena.charAt(i);
            NodoTransicion estado = this.transiciones.ObtenerEstadoNum(estadoActual);
            NodoTerminal aux2 = estado.terminales.inicio;
            buffer += c;
            while (aux2 != null) {
                if (conjuntos.VerificaConjunto(aux2.terminal) || conjuntos.VerificaConjunto(buffer)) {
                    NodoConjunto conj = conjuntos.ObtenerConjunto(aux2.terminal);
                    if (conjuntos.CumpleRegla(conj, c)) {
                        estadoActual = aux2.estado;
                        buffer = "";
                        break;
                    }
                } else {

                    if (aux2.terminal.equals(String.valueOf(c))) {
                        estadoActual = aux2.estado;
                        buffer = "";
                        break;
                    }

                    if (aux2.terminal.equals(buffer)) {
                        estadoActual = aux2.estado;
                        buffer = "";
                        break;
                    }

                }

                aux2 = aux2.sig;
            }
        }

        if (buffer.length() > 0) {
            return false;
        }

        return this.transiciones.EstadoAceptacion(estadoActual);
    }

    public String Salida(boolean valida, String analizarCadena) {
        String valor = analizarCadena;
        String exp = this.identificador;
        String cadena;
        if (valor.charAt(0) == '_') {
            valor = valor.substring(1);
        }

        if (valida) {
            cadena = "La expresion \"" + valor + "\" es valida con la expresion regular " + exp;
        } else {
            cadena = "La expresion \"" + valor + "\" es invalida con la expresion regular " + exp;
        }
        return cadena;
    }

    public String GenerandoJSON(boolean valida, String analizarCadena) {
        String valor = analizarCadena;
        String exp = this.identificador;
        String cadena;
        if (valor.charAt(0) == '_') {
            valor = valor.substring(1);
        }
        if (valida) {
            cadena = "{\"Valor\":\"" + valor + "\", \"ExpresionRegular\":\"" + exp + "\", \"Resultado\":\"Cadena Valida\"}";
        } else {
            cadena = "{\"Valor\":\"" + valor + "\", \"ExpresionRegular\":\"" + exp + "\", \"Resultado\":\"Cadena Invalida\"}";
        }
        return cadena;
    }

    public void Ejecutar() throws IOException, FileNotFoundException, DocumentException {
        this.a.Identifica_Hojas(this.a.raiz);
        this.a.Anulables(this.a.raiz);
        this.a.Primeros(this.a.raiz);
        this.a.Ultimos(this.a.raiz);
        this.siguientes.CrearNodos(this.a.raiz);
        this.siguientes.AgregandoSiguientes(this.a.raiz);
        this.a.GenerarReporteGraphviz();
        this.siguientes.GraficaPDF();
        this.transiciones.CargaSiguientes(this.siguientes, this.a.raiz);
        this.transiciones.ReporteTransiciones();
        this.transiciones.AFD_Graphviz();

    }

}
