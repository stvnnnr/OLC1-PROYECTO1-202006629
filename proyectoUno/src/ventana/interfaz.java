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

    public interfaz() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        verBox = new javax.swing.JComboBox<>();
        archivoTextArea = new java.awt.TextArea();
        archivoNombre = new java.awt.Label();
        generarBoton = new java.awt.Button();
        analizarBoton = new java.awt.Button();
        label2 = new java.awt.Label();
        consola = new java.awt.TextArea();
        imagenPanel = new java.awt.Panel();
        anteriorBoton = new java.awt.Button();
        siguienteBoton = new java.awt.Button();
        arbolesBox = new javax.swing.JComboBox<>();
        archivoBox = new javax.swing.JComboBox<>();
        siguientesBox = new javax.swing.JComboBox<>();
        transicionesBox = new javax.swing.JComboBox<>();
        automatasBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        verBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ver", "Arboles", "Siguientes", "Transiciones", "Automatas" }));

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

        imagenPanel.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout imagenPanelLayout = new javax.swing.GroupLayout(imagenPanel);
        imagenPanel.setLayout(imagenPanelLayout);
        imagenPanelLayout.setHorizontalGroup(
            imagenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        imagenPanelLayout.setVerticalGroup(
            imagenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        anteriorBoton.setBackground(new java.awt.Color(255, 255, 255));
        anteriorBoton.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        anteriorBoton.setLabel("Anterior");
        anteriorBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteriorBotonActionPerformed(evt);
            }
        });

        siguienteBoton.setBackground(new java.awt.Color(255, 255, 255));
        siguienteBoton.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        siguienteBoton.setLabel("Siguiente");
        siguienteBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteBotonActionPerformed(evt);
            }
        });

        arbolesBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Arboles" }));

        archivoBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Archivo", "Abrir", "Guardar", "Guardar Como" }));
        archivoBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archivoBoxActionPerformed(evt);
            }
        });

        siguientesBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tabla de Siguientes" }));

        transicionesBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tabla de Transiciones" }));

        automatasBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Automatas" }));

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
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(archivoTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(archivoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(generarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(analizarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(75, 75, 75)
                                        .addComponent(arbolesBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(86, 86, 86)
                                        .addComponent(automatasBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(siguientesBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(transicionesBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(archivoBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(verBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(imagenPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(anteriorBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                                    .addComponent(siguienteBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(archivoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(archivoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(imagenPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(archivoTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(analizarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(generarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(siguienteBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(anteriorBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(arbolesBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(siguientesBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(transicionesBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(automatasBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void siguienteBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siguienteBotonActionPerformed

    private void anteriorBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anteriorBotonActionPerformed

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

        File directorio = new File(directoryName + "/SALIDAS_202003919");
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                JOptionPane.showMessageDialog(null, "error al crear el directorio");
            }
        } else {
            lista = directorio.listFiles();
        }

        File f;
        if (lista == null) {
            f = new File(directoryName + "/SALIDAS_202003919/salida.json");

            numero = -1;

        } else {
            if (lista.length == 0) {
                f = new File(directoryName + "/SALIDAS_202003919/salida.json");
                numero = -1;

            } else {
                f = new File(directoryName + "/SALIDAS_202003919/salida" + lista.length + ".json");
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
    private java.awt.Button analizarBoton;
    private java.awt.Button anteriorBoton;
    private javax.swing.JComboBox<String> arbolesBox;
    private javax.swing.JComboBox<String> archivoBox;
    private java.awt.Label archivoNombre;
    private java.awt.TextArea archivoTextArea;
    private javax.swing.JComboBox<String> automatasBox;
    private java.awt.TextArea consola;
    private java.awt.Button generarBoton;
    private java.awt.Panel imagenPanel;
    private java.awt.Label label2;
    private java.awt.Button siguienteBoton;
    private javax.swing.JComboBox<String> siguientesBox;
    private javax.swing.JComboBox<String> transicionesBox;
    private javax.swing.JComboBox<String> verBox;
    // End of variables declaration//GEN-END:variables
}
