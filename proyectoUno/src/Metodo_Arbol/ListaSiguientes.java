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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ListaSiguientes {

    public NodoSiguientes inicio;
    public NodoSiguientes fin;
    public int size;
    public String identificador;

    public ListaSiguientes(String id) {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
        this.identificador = id;
    }

    public void AgregarTerminales(String t, int i) {
        NodoSiguientes nuevo = new NodoSiguientes(t, i);
        if (this.inicio == null) {
            this.inicio = nuevo;
        }
        if (this.fin != null) {
            this.fin.sig = nuevo;
        }
        this.fin = nuevo;
        this.size += 1;
    }

    public void AgregarSiguientes(int id, int sig) {
        NodoSiguientes aux = this.inicio;
        while (aux != null) {
            if (aux.id_hoja == id) {
                aux.siguientes.AgregarPosiciones(sig);
                break;
            }
            aux = aux.sig;
        }
    }

    public void CrearNodos(NodoArbol root) {
        if (root != null) {

            this.CrearNodos(root.izq);
            this.CrearNodos(root.der);

            if (root.EsHoja()) {
                this.AgregarTerminales(root.lexema, root.id);
            }
        }
    }

    public void AgregandoSiguientes(NodoArbol root) {
        if (root != null) {
            this.AgregandoSiguientes(root.izq);
            this.AgregandoSiguientes(root.der);

            if (!(root.EsHoja())) {
                if (root.tipo.equals("CONCAT1")) {
                    NodoPosicion aux = root.izq.ultimos.inicio;
                    while (aux != null) {
                        NodoPosicion aux2 = root.der.primeros.inicio;
                        while (aux2 != null) {
                            this.AgregarSiguientes(aux.posicion, aux2.posicion);
                            aux2 = aux2.sig;
                        }
                        aux = aux.sig;
                    }

                } else if (root.tipo.equals("KLEENE") || root.tipo.equals("POSITIVE1")) {
                    NodoPosicion aux = root.der.ultimos.inicio;
                    while (aux != null) {
                        NodoPosicion aux2 = root.der.primeros.inicio;
                        while (aux2 != null) {
                            this.AgregarSiguientes(aux.posicion, aux2.posicion);
                            aux2 = aux2.sig;
                        }

                        aux = aux.sig;
                    }
                }
            }
        }
    }

    public void GraficaPDF() throws FileNotFoundException, DocumentException, IOException {
        File[] lista = null;
        int numero = 0;
        String directoryName = System.getProperty("user.dir");

        File directorio = new File(directoryName + "/SIGUIENTES_202006629");
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
            ficheroPdf = new FileOutputStream(directoryName + "/SIGUIENTES_202006629/Siguientes" + numero + ".pdf");
        } else {
            ficheroPdf = new FileOutputStream(directoryName + "/SIGUIENTES_202006629/Siguientes" + numero + ".pdf");
        }

        PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
        documento.open();
        documento.add(new Paragraph("Siguientes " + this.identificador,FontFactory.getFont("arial", 22, Font.BOLD, BaseColor.BLACK)));
        documento.add(Chunk.NEWLINE);
        documento.add(Chunk.NEWLINE);

        PdfPTable tabla = new PdfPTable(3);
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        Phrase lex = new Phrase("Lexema", boldFont);
        Phrase id = new Phrase("ID", boldFont);
        Phrase sigui = new Phrase("Siguientes", boldFont);

        tabla.addCell(lex);
        tabla.addCell(id);
        tabla.addCell(sigui);

        Font boldNormal = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

        NodoSiguientes aux = this.inicio;
        while (aux != null) {
            Phrase lex2 = new Phrase(aux.Terminal, boldNormal);
            Phrase id2 = new Phrase("" + aux.id_hoja, boldNormal);
            Phrase sigui2 = new Phrase(aux.siguientes.CadenaListada(), boldNormal);

            tabla.addCell(lex2);
            tabla.addCell(id2);
            tabla.addCell(sigui2);

            aux = aux.sig;
        }
        documento.add(tabla);

        documento.close();
    }

    public ListaPosiciones BuscaSiguientes(int pos) {
        NodoSiguientes aux = this.inicio;
        while (aux != null) {
            if (aux.id_hoja == pos) {
                return aux.siguientes;
            }
            aux = aux.sig;
        }

        return null;
    }

    public String BuscaTerminal(int pos) {
        NodoSiguientes aux = this.inicio;
        while (aux != null) {
            if (aux.id_hoja == pos) {
                return aux.Terminal;
            }
            aux = aux.sig;
        }

        return "";
    }

    public int ObtenerFC() {
        NodoSiguientes aux = this.inicio;
        while (aux != null) {
            if (aux.Terminal.equals("#")) {
                return aux.id_hoja;
            }
            aux = aux.sig;
        }
        return -1;
    }
}
