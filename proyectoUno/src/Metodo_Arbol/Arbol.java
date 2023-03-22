package Metodo_Arbol;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ventana.interfaz;

public class Arbol {
    public String identificador;
    public NodoArbol raiz;
    public boolean flag;
    public ListaPila NonTerm;
    public int contador;
    private int contador2;

    public Arbol(String id) {
        this.raiz = null;
        this.flag = false;
        this.NonTerm = new ListaPila();
        this.contador = 1;
        this.contador2 = 1;
        this.identificador = id;
    }

    public void AgregarNodo(String lexema, String tipo) {
        NodoArbol nuevo = new NodoArbol(lexema, tipo);
        nuevo.id_grafica = this.contador;
        this.contador += 1;
        if (!flag) {
            if (this.raiz == null) {
                this.raiz = nuevo;
            } else if (tipo.equals("FINCADENA")) {
                this.raiz.der = nuevo;
            } else if (!tipo.equals("FINCADENA") && this.raiz.izq == null) {
                this.raiz.izq = nuevo;
                if (!nuevo.tipo.equals("TERMINAL")) {
                    this.flag = true;
                    this.NonTerm.Apilar(this.raiz.izq);
                }
            } else {
                this.raiz.izq = this.AgregarNodo2(this.raiz.izq, nuevo);

            }
        } else {
            if (this.NonTerm.fin.nodo.tipo.equals("OR1") || this.NonTerm.fin.nodo.tipo.equals("CONCAT1")) {
                if (this.NonTerm.fin.nodo.izq == null) {
                    this.NonTerm.fin.nodo.izq = this.AgregarNodo2(this.NonTerm.fin.nodo.izq, nuevo);
                } else {
                    this.NonTerm.fin.nodo.der = this.AgregarNodo2(this.NonTerm.fin.nodo.der, nuevo);
                }
            } else {
                this.NonTerm.fin.nodo.der = this.AgregarNodo2(this.NonTerm.fin.nodo.der, nuevo);

            }

            NodoPila np = this.NonTerm.inicio;

            while (np != null) {
                NodoArbol aux = np.nodo;

                while (aux != null) {
                    if (aux.tipo.equals("TERMINAL")) {
                        this.NonTerm.Eliminar(np.nodo);
                        break;
                    }
                    aux = aux.der;
                }
                np = np.sig;
            }

            if (this.NonTerm.size == 0) {
                this.flag = false;
            }
        }

    }

    public NodoArbol AgregarNodo2(NodoArbol raiz_actual, NodoArbol nuevo) {

        if (raiz_actual != null) {

            if (raiz_actual.tipo.equals("CONCAT1") || raiz_actual.tipo.equals("OR1")) {
                if (raiz_actual.izq == null) {
                    raiz_actual.izq = this.AgregarNodo2(raiz_actual.izq, nuevo);
                } else {
                    raiz_actual.der = this.AgregarNodo2(raiz_actual.der, nuevo);
                }

            } else if (!raiz_actual.tipo.equals("TERMINAL")) {
                raiz_actual.der = this.AgregarNodo2(raiz_actual.der, nuevo);

            }
        } else {
            raiz_actual = nuevo;
            if (!nuevo.tipo.equals("TERMINAL")) {
                this.flag = true;
                this.NonTerm.Apilar(raiz_actual);
            }
            return raiz_actual;
        }
        return raiz_actual;
    }

    public void Identifica_Hojas(NodoArbol root) {
        if (root != null) {
            this.Identifica_Hojas(root.izq);
            this.Identifica_Hojas(root.der);

            if (root.EsHoja()) {
                root.id = this.contador2;
                this.contador2 += 1;
            }
        }
    }

    public void Anulables(NodoArbol root) {
        if (root != null) {
            this.Anulables(root.izq);
            this.Anulables(root.der);

            if (!(root.EsHoja())) {
                if (root.tipo.equals("OR1")) {
                    if (root.der.anulable || root.izq.anulable) {
                        root.anulable = true;
                    }

                } else if (root.tipo.equals("CONCAT1")) {
                    if (root.der.anulable && root.izq.anulable) {
                        root.anulable = true;
                    }

                } else if (root.tipo.equals("KLEENE")) {
                    root.anulable = true;

                } else if (root.tipo.equals("POSITIVE1")) {
                    if (root.der.anulable) {
                        root.anulable = true;
                    }

                } else if (root.tipo.equals("OPTIONAL1")) {
                    root.anulable = true;
                }

            }
        }
    }

    public void Primeros(NodoArbol root) {
        if (root != null) {
            this.Primeros(root.izq);
            this.Primeros(root.der);

            if (root.EsHoja()) {
                root.AgregarPrimero(root.id);
            } else {
                if (root.tipo.equals("OR1")) {
                    NodoPosicion izquierda = root.izq.primeros.inicio;
                    while (izquierda != null) {
                        root.AgregarPrimero(izquierda.posicion);
                        izquierda = izquierda.sig;
                    }
                    NodoPosicion derecha = root.der.primeros.inicio;
                    while (derecha != null) {
                        root.AgregarPrimero(derecha.posicion);
                        derecha = derecha.sig;
                    }

                } else if (root.tipo.equals("CONCAT1")) {
                    if (root.izq.anulable) {
                        NodoPosicion izquierda = root.izq.primeros.inicio;
                        while (izquierda != null) {
                            root.AgregarPrimero(izquierda.posicion);
                            izquierda = izquierda.sig;
                        }
                        NodoPosicion derecha = root.der.primeros.inicio;
                        while (derecha != null) {
                            root.AgregarPrimero(derecha.posicion);
                            derecha = derecha.sig;
                        }
                    } else {
                        NodoPosicion izquierda = root.izq.primeros.inicio;
                        while (izquierda != null) {
                            root.AgregarPrimero(izquierda.posicion);
                            izquierda = izquierda.sig;
                        }
                    }

                } else if (root.tipo.equals("KLEENE")) {
                    NodoPosicion derecha = root.der.primeros.inicio;
                    while (derecha != null) {
                        root.AgregarPrimero(derecha.posicion);
                        derecha = derecha.sig;
                    }

                } else if (root.tipo.equals("POSITIVE1")) {
                    NodoPosicion derecha = root.der.primeros.inicio;
                    while (derecha != null) {
                        root.AgregarPrimero(derecha.posicion);
                        derecha = derecha.sig;
                    }

                } else if (root.tipo.equals("OPTIONAL1")) {
                    NodoPosicion derecha = root.der.primeros.inicio;
                    while (derecha != null) {
                        root.AgregarPrimero(derecha.posicion);
                        derecha = derecha.sig;
                    }
                }
            }
        }
    }

    public void Ultimos(NodoArbol root) {
        if (root != null) {
            this.Ultimos(root.izq);
            this.Ultimos(root.der);

            if (root.EsHoja()) {
                root.AgregarUltimo(root.id);
            } else {
                if (root.tipo.equals("OR1")) {
                    NodoPosicion izquierda = root.izq.ultimos.inicio;
                    while (izquierda != null) {
                        root.AgregarUltimo(izquierda.posicion);
                        izquierda = izquierda.sig;
                    }
                    NodoPosicion derecha = root.der.ultimos.inicio;
                    while (derecha != null) {
                        root.AgregarUltimo(derecha.posicion);
                        derecha = derecha.sig;
                    }

                } else if (root.tipo.equals("CONCAT1")) {
                    if (root.der.anulable) {
                        NodoPosicion izquierda = root.izq.ultimos.inicio;
                        while (izquierda != null) {
                            root.AgregarUltimo(izquierda.posicion);
                            izquierda = izquierda.sig;
                        }
                        NodoPosicion derecha = root.der.ultimos.inicio;
                        while (derecha != null) {
                            root.AgregarUltimo(derecha.posicion);
                            derecha = derecha.sig;
                        }
                    } else {
                        NodoPosicion derecha = root.der.ultimos.inicio;
                        while (derecha != null) {
                            root.AgregarUltimo(derecha.posicion);
                            derecha = derecha.sig;
                        }
                    }

                } else if (root.tipo.equals("KLEENE")) {
                    NodoPosicion derecha = root.der.ultimos.inicio;
                    while (derecha != null) {
                        root.AgregarUltimo(derecha.posicion);
                        derecha = derecha.sig;
                    }

                } else if (root.tipo.equals("POSITIVE1")) {
                    NodoPosicion derecha = root.der.ultimos.inicio;
                    while (derecha != null) {
                        root.AgregarUltimo(derecha.posicion);
                        derecha = derecha.sig;
                    }

                } else if (root.tipo.equals("OPTIONAL1")) {
                    NodoPosicion derecha = root.der.ultimos.inicio;
                    while (derecha != null) {
                        root.AgregarUltimo(derecha.posicion);
                        derecha = derecha.sig;
                    }
                }
            }
        }
    }

    public String GenerarDot() {
        String cadena = "digraph arbol {\n";
        cadena += this.GenerarNodos(this.raiz);
        cadena += this.EnlazarNodos(this.raiz);
        cadena += "n_anulable [label=\"anulable\" color=\"red\" fontcolor=\"red\"]\n";
        cadena += "n_ejemplo[label=\"" + "lexema" + "\\n" + "primeros" + "\\n" + "ultimos" + "\\n" + "id (hojas)" + "\"]\n";
        cadena += "n_identificador [label=\"" + this.identificador + "\"]\n";
        cadena += "\n}";
        System.out.println(cadena);
        return cadena;

    }

    public String GenerarNodos(NodoArbol na) {
        String nodos = "";
        if (na != null) {
            nodos += this.GenerarNodos(na.izq);
            nodos += this.GenerarNodos(na.der);
            String lexer = String.valueOf(na.lexema);
            if (!(lexer.contains("\""))) {
                lexer = lexer.replace("\\", "\\\\");
            } else {
                lexer = lexer.replace("\\\"", "\\\\\\\"");
            }

            if (na.EsHoja()) {
                nodos += "n" + na.id_grafica + "[label=\"" + lexer + "\\n" + na.id + "\\n" + na.id + "\\n" + na.id + "\"]\n";
            } else {
                String primeros = na.primeros.CadenaListada();
                String ultimos = na.ultimos.CadenaListada();
                if (na.anulable) {
                    nodos += "n" + na.id_grafica + "[label=\"" + lexer + "\\n" + primeros + "\\n" + ultimos + "\\n" + "\" color=\"red\" fontcolor=\"red\"]\n";
                } else {
                    nodos += "n" + na.id_grafica + "[label=\"" + lexer + "\\n" + primeros + "\\n" + ultimos + "\\n" + "\"]\n";
                }

            }

        }

        return nodos;
    }

    public String EnlazarNodos(NodoArbol na) {
        String cadena = "";
        if (na != null) {
            cadena += this.EnlazarNodos(na.izq);
            cadena += this.EnlazarNodos(na.der);

            if (na.izq != null) {
                cadena += "n" + na.id_grafica + "-> n" + na.izq.id_grafica + "\n";
            }
            if (na.der != null) {
                cadena += "n" + na.id_grafica + "-> n" + na.der.id_grafica + "\n";
            }
        }
        return cadena;
    }

    public void GenerarReporteGraphviz() {
        File[] lista = null;
        int numero = 0;
        String directoryName = System.getProperty("user.dir");

        File directorio = new File(directoryName + "/ARBOLES_202006629");
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                JOptionPane.showMessageDialog(null, "error al crear el directorio");
            }
        } else {
            lista = directorio.listFiles();
        }

        File f;

        if (lista == null) {
            f = new File(directoryName + "/ARBOLES_202006629/arbol.dot");
            numero = -1;

        } else {
            if (lista.length == 0) {
                f = new File(directoryName + "/ARBOLES_202006629/arbol.dot");
                numero = -1;

            } else {
                f = new File(directoryName + "/ARBOLES_202006629/arbol" + lista.length + ".dot");
                numero = lista.length;

            }
        }

        try {
            FileWriter br = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(br);
            PrintWriter pr = new PrintWriter(bw);
            pr.write(this.GenerarDot());
            pr.close();
            bw.close();
            ProcessBuilder pbuilder;
            if (numero == -1) {
                pbuilder = new ProcessBuilder("dot", "-Tpdf", "-o", directoryName + "/ARBOLES_202006629/arbol.pdf", directoryName + "/ARBOLES_202006629/arbol.dot");

            } else {
                pbuilder = new ProcessBuilder("dot", "-Tpdf", "-o", directoryName + "/ARBOLES_202006629/arbol" + numero + ".pdf", directoryName + "/ARBOLES_202006629/arbol" + numero + ".dot");
            }
            pbuilder.redirectErrorStream(true);

            pbuilder.start();

        } catch (IOException ex) {
        }
    }
}
