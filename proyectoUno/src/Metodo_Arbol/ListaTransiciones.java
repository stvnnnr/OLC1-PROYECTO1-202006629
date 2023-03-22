package Metodo_Arbol;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class ListaTransiciones {

    public NodoTransicion inicio;
    public NodoTransicion fin;
    public int size;
    public String identificador;

    public ListaTransiciones(String id) {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
        this.identificador = id;
    }

    public void AgregarEstado(ListaPosiciones ls, ListaTerminales lt) {
        NodoTransicion nuevo = new NodoTransicion(this.size);
        nuevo.terminales = lt;
        nuevo.pos = ls;
        if (this.inicio == null) {
            this.inicio = nuevo;
        }
        if (this.fin != null) {
            this.fin.sig = nuevo;
        }
        this.fin = nuevo;
        this.size += 1;
    }

    public boolean VerificaEstado(ListaPosiciones nt) {
        NodoTransicion aux = this.inicio;
        while (aux != null) {
            if (aux.pos.SonIguales(nt)) {
                return false;
            }
            aux = aux.sig;
        }
        return true;
    }

    public int ObtenerEstado(ListaPosiciones pos) {
        NodoTransicion aux = this.inicio;
        while (aux != null) {
            if (aux.pos.SonIguales(pos)) {
                return aux.estado;
            }
            aux = aux.sig;
        }
        return -1;
    }

    public void CargaSiguientes(ListaSiguientes siguientes, NodoArbol raiz) {
        ListaTerminales lt = new ListaTerminales();
        NodoSiguientes aux = siguientes.inicio;

        while (aux != null) {
            lt.AgregaTerminal(aux.Terminal);
            aux = aux.sig;
        }

        this.AgregarEstado(raiz.primeros, lt);

        NodoTransicion aux2 = this.inicio;
        while (aux2 != null) {

            NodoPosicion position = aux2.pos.inicio;
            while (position != null) {
                String ayuda = siguientes.BuscaTerminal(position.posicion);
                NodoTerminal termina = aux2.terminales.BuscaTerminal(ayuda);

                ListaPosiciones variable = siguientes.BuscaSiguientes(position.posicion);
                if (variable != null) {
                    NodoPosicion auxVar = variable.inicio;

                    while (auxVar != null) {
                        termina.listapos.AgregarPosiciones(auxVar.posicion);
                        auxVar = auxVar.sig;
                    }
                }
                position = position.sig;
            }

            NodoTerminal auxilio = aux2.terminales.inicio;
            while (auxilio != null) {
                if (auxilio.listapos.inicio != null) {
                    if (this.VerificaEstado(auxilio.listapos)) {
                        lt = new ListaTerminales();
                        aux = siguientes.inicio;
                        while (aux != null) {
                            lt.AgregaTerminal(aux.Terminal);
                            aux = aux.sig;
                        }
                        this.AgregarEstado(auxilio.listapos, lt);
                    }
                }

                auxilio = auxilio.sig;
            }
            aux2 = aux2.sig;
        }

        aux2 = this.inicio;
        while (aux2 != null) {
            NodoTerminal aux3 = aux2.terminales.inicio;
            while (aux3 != null) {
                aux3.estado = this.ObtenerEstado(aux3.listapos);
                aux3 = aux3.sig;
            }
            aux2 = aux2.sig;
        }

        int acepta = siguientes.ObtenerFC();
        NodoTransicion aux4 = this.inicio;
        while (aux4 != null) {
            if (aux4.estado == 0) {
                aux4.inicial = true;
            }
            NodoPosicion aux5 = aux4.pos.inicio;
            while (aux5 != null) {
                if (aux5.posicion == acepta) {
                    aux4.aceptacion = true;
                    break;
                }
                aux5 = aux5.sig;
            }

            aux4 = aux4.sig;
        }
    }

    public void ReporteTransiciones() throws FileNotFoundException, DocumentException, IOException {

        File[] lista = null;
        int numero = 0;
        String directoryName = System.getProperty("user.dir");

        File directorio = new File(directoryName + "/TRANSICIONES_202006629");
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                JOptionPane.showMessageDialog(null, "error al crear el directorio");

            }
        } else {
            lista = directorio.listFiles();
        }

        if (lista == null) {
            numero = -1;
        } else {
            if (lista.length == 0) {
                numero = -1;
            } else {
                numero = lista.length;
            }
        }

        Document documento = new Document();
        FileOutputStream ficheroPdf;
        if (numero != -1) {
            ficheroPdf = new FileOutputStream(directoryName + "/TRANSICIONES_202006629/Transiciones" + numero + ".pdf");
        } else {
            ficheroPdf = new FileOutputStream(directoryName + "/TRANSICIONES_202006629/Transiciones" + numero + ".pdf");
        }

        PdfWriter.getInstance(documento, ficheroPdf);
        documento.open();
        documento.add(new Paragraph("Transiciones " + this.identificador, FontFactory.getFont("arial", 22, Font.BOLD, BaseColor.BLACK)));
        documento.add(Chunk.NEWLINE);
        documento.add(Chunk.NEWLINE);
        int sizeTable = 1 + this.inicio.terminales.size;
        PdfPTable tabla = new PdfPTable(sizeTable);
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);

        Phrase estad = new Phrase("Estado", boldFont);
        tabla.addCell(estad);

        NodoTerminal aux = this.inicio.terminales.inicio;
        while (aux != null) {
            Phrase termina = new Phrase(aux.terminal, boldFont);
            tabla.addCell(termina);
            aux = aux.sig;
        }

        Font boldNormal = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
        NodoTransicion aux2 = this.inicio;
        while (aux2 != null) {
            Phrase es = new Phrase("S" + aux2.estado + "{" + aux2.pos.CadenaListada() + "}", boldNormal);
            tabla.addCell(es);
            aux = aux2.terminales.inicio;
            while (aux != null) {
                Phrase te;
                if (aux.estado == -1) {
                    te = new Phrase("-", boldNormal);
                    tabla.addCell(te);
                } else {
                    te = new Phrase("S" + aux.estado, boldNormal);
                    tabla.addCell(te);
                }
                aux = aux.sig;
            }
            aux2 = aux2.sig;
        }
        documento.add(tabla);
        documento.close();
    }

    public String GenerarDot() {

        String cadena = "digraph AFD{\n";
        cadena += "rankdir=LR;\n";
        cadena += "nFlecha [label=\"flecha\" color=\"white\" fontcolor=\"white\"]\n";
        cadena += "nIdentificador [label=\"" + this.identificador + "\"]\n";

        //creando estados
        NodoTransicion aux = this.inicio;
        while (aux != null) {
            if (aux.aceptacion) {
                cadena += "n" + aux.estado + " [label=\"S" + aux.estado + "\" shape=doublecircle]\n";
            } else {
                cadena += "n" + aux.estado + " [label=\"S" + aux.estado + "\" shape=circle]\n";
            }
            aux = aux.sig;
        }
        cadena += "nFlecha->n0\n";
        //Realizando uniones
        aux = this.inicio;
        while (aux != null) {

            NodoTerminal aux2 = aux.terminales.inicio;
            while (aux2 != null) {
                if (aux2.estado != -1) {
                    String termina = String.valueOf(aux2.terminal);
                    if (!(termina.contains("\""))) {
                        termina = termina.replace("\\", "\\\\");
                    } else {
                        termina = termina.replace("\\\"", "\\\\\\\"");
                    }
                    cadena += "n" + aux.estado + "->n" + aux2.estado + "[label=\"" + termina + "\"];\n";
                }
                aux2 = aux2.sig;
            }
            aux = aux.sig;
        }
        cadena += "\n}";

        return cadena;
    }

    public void AFD_Graphviz() {
        File[] lista = null;
        int numero = 0;
        String directoryName = System.getProperty("user.dir");

        File directorio = new File(directoryName + "/AFD_202006629");
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                JOptionPane.showMessageDialog(null, "error al crear el directorio");
            }
        } else {
            lista = directorio.listFiles();
        }

        File f;
        ProcessBuilder pb;
        if (lista == null) {
            f = new File(directoryName + "/AFD_202006629/afd.dot");
            numero = -1;

        } else {
            if (lista.length == 0) {
                f = new File(directoryName + "/AFD_202006629/afd.dot");
                numero = -1;

            } else {
                f = new File(directoryName + "/AFD_202006629/afd" + lista.length + ".dot");
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
                pbuilder = new ProcessBuilder("dot", "-Tpdf", "-o", directoryName + "/AFD_202006629/afd.pdf", directoryName + "/AFD_202006629/afd.dot");

            } else {
                pbuilder = new ProcessBuilder("dot", "-Tpdf", "-o", directoryName + "/AFD_202006629/afd" + numero + ".pdf", directoryName + "/AFD_202006629/afd" + numero + ".dot");
            }
            pbuilder.redirectErrorStream(true);

            pbuilder.start();

        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }

    public NodoTransicion ObtenerEstadoNum(int num) {
        NodoTransicion aux = this.inicio;
        while (aux != null) {
            if (aux.estado == num) {
                return aux;
            }
            aux = aux.sig;
        }
        return null;
    }

    public boolean EstadoAceptacion(int e) {
        NodoTransicion aux = this.inicio;
        while (aux != null) {
            if (aux.estado == e) {
                return aux.aceptacion;
            }
            aux = aux.sig;
        }
        return false;
    }
}
