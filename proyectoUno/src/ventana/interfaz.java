package ventana;

import Metodo_Arbol.MetodoArbol;
import Thompson.MetodoThompson;
import com.itextpdf.text.DocumentException;
import estructuras.ListaConjuntos;
import estructuras.ListaErrores;
import estructuras.ListaExpRegular;
import estructuras.NodoCadena;
import estructuras.NodoExpRegular;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import static proyectouno.ProyectoUno.conjuntos;
import static proyectouno.ProyectoUno.errores;
import static proyectouno.ProyectoUno.regularExpression;
import analizadores.scanner;
import analizadores.parser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class interfaz extends javax.swing.JFrame {

    public static String entrada = "";
    public static String pathEntrada = "";
    parser sintactico;
    private static final long serialVersionUID = 1L;
    private JFileChooser fileChooser;

    public interfaz() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        archivoTextArea = new java.awt.TextArea();
        archivoNombre = new java.awt.Label();
        generarBoton = new java.awt.Button();
        analizarBoton = new java.awt.Button();
        label2 = new java.awt.Label();
        consola = new java.awt.TextArea();
        afdBoton = new java.awt.Button();
        archivoBox = new javax.swing.JComboBox<>();
        afndBoton = new java.awt.Button();
        salidasBoton = new java.awt.Button();
        arbolBoton = new java.awt.Button();
        siguientesBoton = new java.awt.Button();
        errorBoton = new java.awt.Button();
        transBoton1 = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        archivoNombre.setBackground(new java.awt.Color(255, 255, 204));
        archivoNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        archivoNombre.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        archivoNombre.setText("Archivo 1");

        generarBoton.setBackground(new java.awt.Color(255, 255, 255));
        generarBoton.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        generarBoton.setLabel("Generar Automata");
        generarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarBotonActionPerformed(evt);
            }
        });

        analizarBoton.setBackground(new java.awt.Color(255, 255, 255));
        analizarBoton.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        analizarBoton.setLabel("Analizar Entrada");
        analizarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizarBotonActionPerformed(evt);
            }
        });

        label2.setBackground(new java.awt.Color(204, 255, 255));
        label2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        label2.setText("Consola");

        afdBoton.setBackground(new java.awt.Color(255, 255, 255));
        afdBoton.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        afdBoton.setLabel("AFD");
        afdBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                afdBotonActionPerformed(evt);
            }
        });

        archivoBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Archivo", "Abrir", "Guardar", "Guardar Como" }));
        archivoBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archivoBoxActionPerformed(evt);
            }
        });

        afndBoton.setBackground(new java.awt.Color(255, 255, 255));
        afndBoton.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        afndBoton.setLabel("AFND");
        afndBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                afndBotonActionPerformed(evt);
            }
        });

        salidasBoton.setBackground(new java.awt.Color(255, 255, 255));
        salidasBoton.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        salidasBoton.setLabel("Salidas");
        salidasBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salidasBotonActionPerformed(evt);
            }
        });

        arbolBoton.setBackground(new java.awt.Color(255, 255, 255));
        arbolBoton.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        arbolBoton.setLabel("Arboles");
        arbolBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arbolBotonActionPerformed(evt);
            }
        });

        siguientesBoton.setBackground(new java.awt.Color(255, 255, 255));
        siguientesBoton.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        siguientesBoton.setLabel("Siguientes");
        siguientesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguientesBotonActionPerformed(evt);
            }
        });

        errorBoton.setBackground(new java.awt.Color(255, 255, 255));
        errorBoton.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        errorBoton.setLabel("Errores");
        errorBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                errorBotonActionPerformed(evt);
            }
        });

        transBoton1.setBackground(new java.awt.Color(255, 255, 255));
        transBoton1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        transBoton1.setLabel("Transiciones");
        transBoton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transBoton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(consola, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(generarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                .addComponent(analizarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(archivoBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 936, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(archivoTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(160, 160, 160)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(afdBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(afndBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salidasBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(arbolBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(siguientesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(transBoton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(errorBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(archivoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(795, 795, 795)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(archivoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(archivoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(archivoTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(arbolBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(afdBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(afndBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(siguientesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salidasBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(transBoton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errorBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(analizarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(consola, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void analizarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizarBotonActionPerformed
        try {
            errores = new ListaErrores();
            regularExpression = new ListaExpRegular();
            conjuntos = new ListaConjuntos();
            
            String texto = archivoTextArea.getText();
            scanner scan = new scanner(new BufferedReader(new StringReader(texto)));
            parser parser = new parser(scan);
            parser.parse();
            
            JOptionPane.showMessageDialog(this, "Escaneo finalizado");

            ListaExpRegular ler = regularExpression;
            ListaConjuntos cc = conjuntos;

            String directoryName = System.getProperty("user.dir");
            File[] lista = null;

            File directorio = new File(directoryName + "/ERRORES_202006629");
            if (!directorio.exists()) {
                if (!directorio.mkdirs()) {
                    JOptionPane.showMessageDialog(null, "error al crear el directorio");
                }
            } else {
                lista = directorio.listFiles();
            }

            File f;
            if (lista == null) {
                f = new File(directoryName + "/ERRORES_202006629/errores.html");
            } else {
                if (lista.length == 0) {
                    f = new File(directoryName + "/ERRORES_202006629/errores.html");
                } else {
                    f = new File(directoryName + "/ERRORES_202006629/errores" + lista.length + ".html");
                }
            }

            try {
                FileWriter br = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(br);
                PrintWriter pr = new PrintWriter(bw);
                pr.write(errores.ReporteHTML());
                pr.close();
                bw.close();

            } catch (IOException ex) {
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_analizarBotonActionPerformed

    private void afdBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_afdBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_afdBotonActionPerformed

    private void archivoBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archivoBoxActionPerformed
        if (archivoBox.getSelectedItem().equals("Abrir")) {
            System.out.println("Presionaste el Abrir Archivo");
            archivoTextArea.setText("");
            JFileChooser fileElector = new JFileChooser();
            FileNameExtensionFilter filtroOCL = new FileNameExtensionFilter(".olc", "olc");
            fileElector.setFileFilter(filtroOCL);
            int seleccion = fileElector.showOpenDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File fichero = fileElector.getSelectedFile();
                pathEntrada = fichero.getAbsolutePath();
                try ( FileReader fr = new FileReader(fichero)) {
                    String cadena = "";
                    int valor = fr.read();
                    while (valor != -1) {
                        cadena += (char) valor;
                        valor = fr.read();
                    }
                    archivoTextArea.setText(cadena);
                    System.out.println("Si se abrió el archivo");
                    System.out.println("Estaba en la ruta");
                    System.out.println(pathEntrada);
                    JOptionPane.showMessageDialog(null, "Archivo cargado con exito", "Excelente", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception e) {
                    System.out.println("Hubo un error en la lectura del archivo");
                    JOptionPane.showMessageDialog(null, "Archivo no se pudo cargar", "OK", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
        //---------------------------------------------------------------------------
        if (archivoBox.getSelectedItem().equals("Guardar")) {
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(".olc", "olc");
            fc.setFileFilter(filtro);
            int seleccion = fc.showSaveDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File fichero = fc.getSelectedFile();
                try ( FileWriter fw = new FileWriter(fichero)) {
                    fw.write(archivoTextArea.getText());
                    JOptionPane.showMessageDialog(null, "Archivo guardado con exito", "Excelente", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception e) {
                    System.out.println("Hubo error en algo de guardar");
                    JOptionPane.showMessageDialog(null, "Archivo no se pudo guardar", "OK", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
        //---------------------------------------------------------------------------
        if (archivoBox.getSelectedItem().equals("Guardar Como")) {
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(".olc", "olc");
            fc.setFileFilter(filtro);
            int seleccion = fc.showSaveDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File fichero = fc.getSelectedFile();
                try ( FileWriter fw = new FileWriter(fichero)) {
                    fw.write(archivoTextArea.getText());
                    JOptionPane.showMessageDialog(null, "Archivo guardado con exito", "Excelente", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception e) {
                    System.out.println("Hubo error en algo de guardar");
                    JOptionPane.showMessageDialog(null, "Archivo no se pudo guardar", "OK", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }


    }//GEN-LAST:event_archivoBoxActionPerformed

    private void generarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarBotonActionPerformed
        NodoExpRegular ner = regularExpression.inicio;
        JSONArray ja = new JSONArray();
        while (ner != null) {
            try {
                MetodoThompson mt = new MetodoThompson(ner.le, ner.id);
                mt.Ejecutar();
                mt.AFND_Graphviz();

                MetodoArbol me = new MetodoArbol(ner.le, ner.id);
                try {
                    me.Ejecutar();
                    if (ner.valor.inicio != null) {
                        NodoCadena auxiliar = ner.valor.inicio;
                        while (auxiliar != null) {
                            boolean validar = me.EscaneaCadena(auxiliar.cadena);
                            String txt = me.Salida(validar, auxiliar.cadena);

                            String txt2 = me.GenerandoJSON(validar, auxiliar.cadena);
                            JSONObject ob = new JSONObject(txt2);
                            ja.put(ob);
                            consola.append(txt + "\n");
                            auxiliar = auxiliar.sig;
                        }

                    }
                } catch (IOException | DocumentException ex) {
                }
            } catch (NullPointerException npe) {
                System.out.println("Null Pointer Encontrado");
            }

            ner = ner.sig;
        }

        File[] lista = null;
        int numero = 0;
        String directoryName = System.getProperty("user.dir");

        File directorio = new File(directoryName + "/SALIDAS_202006629");
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                JOptionPane.showMessageDialog(null, "error al crear el directorio");
            }
        } else {
            lista = directorio.listFiles();
        }

        File f;
        if (lista == null) {
            f = new File(directoryName + "/SALIDAS_202006629/salida.json");

            numero = -1;

        } else {
            if (lista.length == 0) {
                f = new File(directoryName + "/SALIDAS_202006629/salida.json");
                numero = -1;

            } else {
                f = new File(directoryName + "/SALIDAS_202006629/salida" + lista.length + ".json");
                numero = lista.length;
            }
        }
        try {
            FileWriter br = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(br);
            PrintWriter pr = new PrintWriter(bw);
            pr.write(ja.toString());
            pr.close();
            bw.close();

        } catch (IOException ex) {
        }
    }//GEN-LAST:event_generarBotonActionPerformed

    private void afndBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_afndBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_afndBotonActionPerformed

    private void salidasBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salidasBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salidasBotonActionPerformed

    private void arbolBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arbolBotonActionPerformed
        try {
            Runtime.getRuntime().exec("C:\\Windows\\System32\\cmd.exe /K start D:\\SEPTIMO SEMESTRE\\Compi 1\\OLC1-PROYECTO1-202006629\\proyectoUno");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_arbolBotonActionPerformed

    private void siguientesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguientesBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siguientesBotonActionPerformed

    private void errorBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_errorBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_errorBotonActionPerformed

    private void transBoton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transBoton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_transBoton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button afdBoton;
    private java.awt.Button afndBoton;
    private java.awt.Button analizarBoton;
    private java.awt.Button arbolBoton;
    private javax.swing.JComboBox<String> archivoBox;
    private java.awt.Label archivoNombre;
    private java.awt.TextArea archivoTextArea;
    private java.awt.TextArea consola;
    private java.awt.Button errorBoton;
    private java.awt.Button generarBoton;
    private java.awt.Label label2;
    private java.awt.Button salidasBoton;
    private java.awt.Button siguientesBoton;
    private java.awt.Button transBoton1;
    // End of variables declaration//GEN-END:variables
}
