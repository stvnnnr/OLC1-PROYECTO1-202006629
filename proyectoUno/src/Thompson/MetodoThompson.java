
package Thompson;

import estructuras.ListaExpresiones;
import estructuras.NodoExpresion;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class MetodoThompson {

    public ListaDoble terminales; //terminales a analizar
    public String identificador; //nombre de la expresion regular 
    public ListaGrafos listaGrafos; //listado de los grafos generados
    public ListaExpresiones expresiones;
    public Grafo grafo;

    public MetodoThompson(ListaExpresiones expresiones, String id) {
        this.expresiones = expresiones;
        this.identificador = id;
        this.terminales = new ListaDoble();
        this.listaGrafos = new ListaGrafos();
        this.CargarListaDoble();
        this.grafo = new Grafo();
    }

    public void CargarListaDoble() {
        NodoExpresion aux = expresiones.inicio;
        while (aux != null) {
            terminales.Insertar(aux.lexema, aux.tipo);
            aux = aux.sig;
        }
    }

    public void Ejecutar() {
        int contador = 0;

        while (this.terminales.size > 1) {

            //creando estructura por defecto del metacaracter correspondiente
            NodoDoble aux = this.terminales.fin;
            Grafo grafoAuxiliar;
            while (aux != null) {
                if (aux.tipo.equals("CONCAT1")) {
                    if (aux.sig != null) {
                        if (aux.sig.sig != null) {
                            grafoAuxiliar = new Grafo();

                            if (aux.sig.tipo.equals("GRAFO") && aux.sig.sig.tipo.equals("GRAFO")) { //G G
                                //final aux.sig se une a inicio aux.sig.sig
                                //eliminar grafo aux.sig y aux.sig.sig
                                //guardar grafo nuevo
                                try {
                                    int id_buscar1 = Integer.parseInt(aux.sig.lexema);
                                    Grafo grafito1 = this.listaGrafos.BuscaGrafo(id_buscar1).g;
                                    int id_buscar2 = Integer.parseInt(aux.sig.sig.lexema);
                                    Grafo grafito2 = this.listaGrafos.BuscaGrafo(id_buscar2).g;

                                    Grafo resultante = grafoAuxiliar.UnificarGrafos1(grafito1, grafito2);
                                    int NewGraph = listaGrafos.AgregarGrafo(resultante);

                                    this.terminales.Actualizar(aux.id, "GRAFO", "" + NewGraph);
                                    this.terminales.Eliminar(aux.sig.sig.id);
                                    this.terminales.Eliminar(aux.sig.id);
                                    break;

                                } catch (NumberFormatException e) {
                                    System.out.println("Ocurrio un error en T G");

                                }
                            } else if (aux.sig.sig.tipo.equals("GRAFO") && (!(aux.sig.equals("GRAFO")))) { //T G
                                //crear transicion e1 a e2 con aux.sig
                                //unir e2 con inicio aux.sig.sig
                                //despreciar e3
                                //eliminar grafo aux.sig.sig
                                //guardar grafo nuevo

                                try {
                                    int id_buscar = Integer.parseInt(aux.sig.sig.lexema);
                                    Grafo grafito = this.listaGrafos.BuscaGrafo(id_buscar).g;
                                    NodoG inicia = grafito.ObtenerInicial();
                                    inicia.inicial = false;
                                    int nuevo = grafito.AgregarNodo(contador);
                                    grafito.AgregarTransicion(nuevo, inicia.id, aux.sig.lexema);
                                    contador += 1;
                                    NodoG auxiliar = grafito.BuscarNodo(nuevo);
                                    auxiliar.inicial = true;
                                    int NewGraph = listaGrafos.AgregarGrafo(grafito);

                                    this.terminales.Actualizar(aux.id, "GRAFO", "" + NewGraph);

                                    this.terminales.Eliminar(aux.sig.sig.id);
                                    this.terminales.Eliminar(aux.sig.id);
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Ocurrio un error en T G");

                                }

                            } else if (aux.sig.tipo.equals("GRAFO") && (!(aux.sig.sig.equals("GRAFO")))) { // G T
                                //crear transicion e2 a e3 con aux.sig.sig
                                //unir aux.sig final a e2
                                //eliminar grafo aux.sig
                                //guardar grafo nuevo
                                try {
                                    int id_buscar = Integer.parseInt(aux.sig.lexema);
                                    Grafo grafito = this.listaGrafos.BuscaGrafo(id_buscar).g;
                                    //NodoG inicia = grafito.ObtenerInicial();
                                    NodoG finalito = grafito.ObtenerFinal();
                                    finalito.EsFinal = false;
                                    int nuevo = grafito.AgregarNodo(contador);
                                    grafito.AgregarTransicion(finalito.id, nuevo, aux.sig.sig.lexema);
                                    contador += 1;
                                    NodoG auxiliar = grafito.BuscarNodo(nuevo);
                                    auxiliar.EsFinal = true;

                                    int NewGraph = listaGrafos.AgregarGrafo(grafito);

                                    this.terminales.Actualizar(aux.id, "GRAFO", "" + NewGraph);
                                    this.terminales.Eliminar(aux.sig.sig.id);
                                    this.terminales.Eliminar(aux.sig.id);
                                    break;

                                } catch (NumberFormatException e) {
                                    System.out.println("Ocurrio un error en G T");

                                }

                            } else { // T T
                                //transicion e1 a e2 con aux.sig
                                //transicion e2 a e3 con aux.sig.sig
                                //guardar grafo
                                int e1 = grafoAuxiliar.AgregarNodo(contador);
                                int e2 = grafoAuxiliar.AgregarNodo(contador + 1);
                                int e3 = grafoAuxiliar.AgregarNodo(contador + 2);
                                contador += 3;

                                grafoAuxiliar.AgregarTransicion(e1, e2, aux.sig.lexema);
                                grafoAuxiliar.AgregarTransicion(e2, e3, aux.sig.sig.lexema);
                                NodoG auxiliar = grafoAuxiliar.BuscarNodo(e1);
                                auxiliar.inicial = true;
                                auxiliar = grafoAuxiliar.BuscarNodo(e3);
                                auxiliar.EsFinal = true;

                                int idLG = listaGrafos.AgregarGrafo(grafoAuxiliar);
                                this.terminales.Actualizar(aux.id, "GRAFO", "" + idLG);
                                this.terminales.Eliminar(aux.sig.sig.id);
                                this.terminales.Eliminar(aux.sig.id);
                                break;

                            }

                        }
                    }

                } else if (aux.tipo.equals("OR1")) {
                    if (aux.sig != null) {
                        if (aux.sig.sig != null) {
                            grafoAuxiliar = new Grafo();
                            if (aux.sig.tipo.equals("GRAFO") && aux.sig.sig.tipo.equals("GRAFO")) { //G G
                                try {
                                    int e1 = grafoAuxiliar.AgregarNodo(contador);
                                    int e2 = grafoAuxiliar.AgregarNodo(contador + 1);
                                    contador += 2;

                                    int id_buscar = Integer.parseInt(aux.sig.lexema);
                                    Grafo grafito = this.listaGrafos.BuscaGrafo(id_buscar).g;
                                    NodoG inicia = grafito.ObtenerInicial();
                                    NodoG finaliza = grafito.ObtenerFinal();
                                    inicia.inicial = false;
                                    finaliza.EsFinal = false;

                                    //Agregar Estados de grafito a grafoAuxiliar
                                    int inservible;
                                    NodoG auxi = grafito.inicio;
                                    while (auxi != null) {
                                        inservible = grafoAuxiliar.AgregarNodo(auxi.id);
                                        auxi = auxi.sig;
                                    }

                                    //agregar transiciones
                                    auxi = grafito.inicio;
                                    while (auxi != null) {
                                        NodoG auxi2 = auxi.transition.inicio;
                                        while (auxi2 != null) {
                                            grafoAuxiliar.AgregarTransicion(auxi.id, auxi2.id, auxi2.peso);
                                            auxi2 = auxi2.sig;
                                        }
                                        auxi = auxi.sig;
                                    }

                                    //agregando ultimas transiciones
                                    grafoAuxiliar.AgregarTransicion(e1, inicia.id, "EPSILON");
                                    grafoAuxiliar.AgregarTransicion(finaliza.id, e2, "EPSILON");

                                    id_buscar = Integer.parseInt(aux.sig.sig.lexema);
                                    grafito = this.listaGrafos.BuscaGrafo(id_buscar).g;
                                    inicia = grafito.ObtenerInicial();
                                    finaliza = grafito.ObtenerFinal();
                                    inicia.inicial = false;
                                    finaliza.EsFinal = false;

                                    //Agregar Estados de grafito a grafoAuxiliar
                                    auxi = grafito.inicio;
                                    while (auxi != null) {
                                        inservible = grafoAuxiliar.AgregarNodo(auxi.id);
                                        auxi = auxi.sig;
                                    }

                                    //agregar transiciones
                                    auxi = grafito.inicio;
                                    while (auxi != null) {
                                        NodoG auxi2 = auxi.transition.inicio;
                                        while (auxi2 != null) {
                                            grafoAuxiliar.AgregarTransicion(auxi.id, auxi2.id, auxi2.peso);
                                            auxi2 = auxi2.sig;
                                        }
                                        auxi = auxi.sig;
                                    }

                                    //agregando ultimas transiciones
                                    grafoAuxiliar.AgregarTransicion(e1, inicia.id, "EPSILON");
                                    grafoAuxiliar.AgregarTransicion(finaliza.id, e2, "EPSILON");

                                    NodoG auxiliar = grafoAuxiliar.BuscarNodo(e1);
                                    auxiliar.inicial = true;
                                    auxiliar = grafoAuxiliar.BuscarNodo(e2);
                                    auxiliar.EsFinal = true;

                                    int IdLG = listaGrafos.AgregarGrafo(grafoAuxiliar);
                                    this.terminales.Actualizar(aux.id, "GRAFO", "" + IdLG);
                                    this.terminales.Eliminar(aux.sig.sig.id);
                                    this.terminales.Eliminar(aux.sig.id);
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Error en T G |");
                                }

                            } else if (aux.sig.sig.tipo.equals("GRAFO") && (!(aux.sig.equals("GRAFO")))) { //T G
                                try {
                                    int e1 = grafoAuxiliar.AgregarNodo(contador);
                                    int e2 = grafoAuxiliar.AgregarNodo(contador + 1);
                                    int e3 = grafoAuxiliar.AgregarNodo(contador + 2);
                                    int e4 = grafoAuxiliar.AgregarNodo(contador + 3);

                                    contador += 4;

                                    grafoAuxiliar.AgregarTransicion(e1, e2, "EPSILON");
                                    grafoAuxiliar.AgregarTransicion(e2, e3, aux.sig.lexema);
                                    grafoAuxiliar.AgregarTransicion(e3, e4, "EPSILON");

                                    int id_buscar = Integer.parseInt(aux.sig.sig.lexema);
                                    Grafo grafito = this.listaGrafos.BuscaGrafo(id_buscar).g;
                                    NodoG inicia = grafito.ObtenerInicial();
                                    NodoG finaliza = grafito.ObtenerFinal();
                                    inicia.inicial = false;
                                    finaliza.EsFinal = false;

                                    //Agregar Estados de grafito a grafoAuxiliar
                                    int inservible;
                                    NodoG auxi = grafito.inicio;
                                    while (auxi != null) {
                                        inservible = grafoAuxiliar.AgregarNodo(auxi.id);
                                        auxi = auxi.sig;
                                    }

                                    //agregar transiciones
                                    auxi = grafito.inicio;
                                    while (auxi != null) {
                                        NodoG auxi2 = auxi.transition.inicio;
                                        while (auxi2 != null) {
                                            grafoAuxiliar.AgregarTransicion(auxi.id, auxi2.id, auxi2.peso);
                                            auxi2 = auxi2.sig;
                                        }
                                        auxi = auxi.sig;
                                    }

                                    //agregando ultimas transiciones
                                    grafoAuxiliar.AgregarTransicion(e1, inicia.id, "EPSILON");
                                    grafoAuxiliar.AgregarTransicion(finaliza.id, e4, "EPSILON");

                                    NodoG auxiliar = grafoAuxiliar.BuscarNodo(e1);
                                    auxiliar.inicial = true;
                                    auxiliar = grafoAuxiliar.BuscarNodo(e4);
                                    auxiliar.EsFinal = true;

                                    int IdLG = listaGrafos.AgregarGrafo(grafoAuxiliar);
                                    this.terminales.Actualizar(aux.id, "GRAFO", "" + IdLG);
                                    this.terminales.Eliminar(aux.sig.sig.id);
                                    this.terminales.Eliminar(aux.sig.id);
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Error en T G |");
                                }

                            } else if (aux.sig.tipo.equals("GRAFO") && (!(aux.sig.sig.equals("GRAFO")))) { // G T
                                try {
                                    int e1 = grafoAuxiliar.AgregarNodo(contador);
                                    int e2 = grafoAuxiliar.AgregarNodo(contador + 1);
                                    int e3 = grafoAuxiliar.AgregarNodo(contador + 2);
                                    int e4 = grafoAuxiliar.AgregarNodo(contador + 3);

                                    contador += 4;

                                    grafoAuxiliar.AgregarTransicion(e1, e2, "EPSILON");
                                    grafoAuxiliar.AgregarTransicion(e2, e3, aux.sig.sig.lexema);
                                    grafoAuxiliar.AgregarTransicion(e3, e4, "EPSILON");

                                    int id_buscar = Integer.parseInt(aux.sig.lexema);
                                    Grafo grafito = this.listaGrafos.BuscaGrafo(id_buscar).g;
                                    NodoG inicia = grafito.ObtenerInicial();
                                    NodoG finaliza = grafito.ObtenerFinal();
                                    inicia.inicial = false;
                                    finaliza.EsFinal = false;

                                    //Agregar Estados de grafito a grafoAuxiliar
                                    int inservible;
                                    NodoG auxi = grafito.inicio;
                                    while (auxi != null) {
                                        inservible = grafoAuxiliar.AgregarNodo(auxi.id);
                                        auxi = auxi.sig;
                                    }

                                    //agregar transiciones
                                    auxi = grafito.inicio;
                                    while (auxi != null) {
                                        NodoG auxi2 = auxi.transition.inicio;
                                        while (auxi2 != null) {
                                            grafoAuxiliar.AgregarTransicion(auxi.id, auxi2.id, auxi2.peso);
                                            auxi2 = auxi2.sig;
                                        }
                                        auxi = auxi.sig;
                                    }

                                    //agregando ultimas transiciones
                                    grafoAuxiliar.AgregarTransicion(e1, inicia.id, "EPSILON");
                                    grafoAuxiliar.AgregarTransicion(finaliza.id, e4, "EPSILON");

                                    NodoG auxiliar = grafoAuxiliar.BuscarNodo(e1);
                                    auxiliar.inicial = true;
                                    auxiliar = grafoAuxiliar.BuscarNodo(e4);
                                    auxiliar.EsFinal = true;

                                    int IdLG = listaGrafos.AgregarGrafo(grafoAuxiliar);
                                    this.terminales.Actualizar(aux.id, "GRAFO", "" + IdLG);
                                    this.terminales.Eliminar(aux.sig.sig.id);
                                    this.terminales.Eliminar(aux.sig.id);
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Error en T G |");
                                }
                            } else { // T T
                                int e1 = grafoAuxiliar.AgregarNodo(contador);
                                int e2 = grafoAuxiliar.AgregarNodo(contador + 1);
                                int e3 = grafoAuxiliar.AgregarNodo(contador + 2);
                                int e4 = grafoAuxiliar.AgregarNodo(contador + 3);
                                int e5 = grafoAuxiliar.AgregarNodo(contador + 4);
                                int e6 = grafoAuxiliar.AgregarNodo(contador + 5);
                                contador += 6;

                                grafoAuxiliar.AgregarTransicion(e1, e2, "EPSILON");
                                grafoAuxiliar.AgregarTransicion(e2, e3, aux.sig.lexema);
                                grafoAuxiliar.AgregarTransicion(e3, e6, "EPSILON");
                                grafoAuxiliar.AgregarTransicion(e1, e4, "EPSILON");
                                grafoAuxiliar.AgregarTransicion(e4, e5, aux.sig.sig.lexema);
                                grafoAuxiliar.AgregarTransicion(e5, e6, "EPSILON");

                                NodoG auxiliar = grafoAuxiliar.BuscarNodo(e1);
                                auxiliar.inicial = true;
                                auxiliar = grafoAuxiliar.BuscarNodo(e6);
                                auxiliar.EsFinal = true;

                                int IdLG = listaGrafos.AgregarGrafo(grafoAuxiliar);
                                this.terminales.Actualizar(aux.id, "GRAFO", "" + IdLG);
                                this.terminales.Eliminar(aux.sig.sig.id);
                                this.terminales.Eliminar(aux.sig.id);
                                break;

                            }

                        }
                    }

                } else if (aux.tipo.equals("KLEENE")) {
                    if (aux.sig != null) {
                        grafoAuxiliar = new Grafo();
                        if (aux.sig.tipo.equals("GRAFO")) { // G*
                            try {
                                int e1 = grafoAuxiliar.AgregarNodo(contador);
                                int e2 = grafoAuxiliar.AgregarNodo(contador + 1);
                                contador += 2;

                                int id_buscar = Integer.parseInt(aux.sig.lexema);
                                Grafo grafito = this.listaGrafos.BuscaGrafo(id_buscar).g;
                                NodoG inicia = grafito.ObtenerInicial();
                                NodoG finaliza = grafito.ObtenerFinal();
                                inicia.inicial = false;
                                finaliza.EsFinal = false;

                                //Agregando estados de grafito a grafoAuxiliar
                                int inservible;
                                NodoG auxi = grafito.inicio;
                                while (auxi != null) {
                                    inservible = grafoAuxiliar.AgregarNodo(auxi.id);
                                    auxi = auxi.sig;
                                }

                                //Agregando transiciones
                                auxi = grafito.inicio;
                                while (auxi != null) {
                                    NodoG auxi2 = auxi.transition.inicio;
                                    while (auxi2 != null) {
                                        grafoAuxiliar.AgregarTransicion(auxi.id, auxi2.id, auxi2.peso);
                                        auxi2 = auxi2.sig;
                                    }
                                    auxi = auxi.sig;
                                }

                                //Agregando transiciones finales
                                grafoAuxiliar.AgregarTransicion(e1, inicia.id, "EPSILON");
                                grafoAuxiliar.AgregarTransicion(inicia.id, e2, "EPSILON");
                                grafoAuxiliar.AgregarTransicion(finaliza.id, e2, "EPSILON");
                                grafoAuxiliar.AgregarTransicion(finaliza.id, inicia.id, "EPSILON");

                                NodoG auxiliar = grafoAuxiliar.BuscarNodo(e1);
                                auxiliar.inicial = true;
                                auxiliar = grafoAuxiliar.BuscarNodo(e2);
                                auxiliar.EsFinal = true;
                                int IdLG = listaGrafos.AgregarGrafo(grafoAuxiliar);
                                this.terminales.Actualizar(aux.id, "GRAFO", "" + IdLG);
                                this.terminales.Eliminar(aux.sig.id);
                                break;

                            } catch (NumberFormatException e) {
                                System.out.println("Error en T G |");
                            }

                        } else { // T*
                            int e1 = grafoAuxiliar.AgregarNodo(contador);
                            int e2 = grafoAuxiliar.AgregarNodo(contador + 1);
                            int e3 = grafoAuxiliar.AgregarNodo(contador + 2);
                            int e4 = grafoAuxiliar.AgregarNodo(contador + 3);
                            contador += 4;

                            grafoAuxiliar.AgregarTransicion(e1, e2, "EPSILON");
                            grafoAuxiliar.AgregarTransicion(e2, e3, aux.sig.lexema);
                            grafoAuxiliar.AgregarTransicion(e3, e4, "EPSILON");
                            grafoAuxiliar.AgregarTransicion(e3, e2, "EPSILON");
                            grafoAuxiliar.AgregarTransicion(e2, e4, "EPSILON");

                            NodoG auxiliar = grafoAuxiliar.BuscarNodo(e1);
                            auxiliar.inicial = true;
                            auxiliar = grafoAuxiliar.BuscarNodo(e4);
                            auxiliar.EsFinal = true;

                            int idLG = listaGrafos.AgregarGrafo(grafoAuxiliar);
                            this.terminales.Actualizar(aux.id, "GRAFO", "" + idLG);
                            this.terminales.Eliminar(aux.sig.id);
                            break;

                        }
                    }

                } else if (aux.tipo.equals("POSITIVE1")) {
                    if (aux.sig != null) {
                        grafoAuxiliar = new Grafo();
                        if (aux.sig.tipo.equals("GRAFO")) { // G*
                            try {
                                int e1 = grafoAuxiliar.AgregarNodo(contador);
                                int e2 = grafoAuxiliar.AgregarNodo(contador + 1);
                                contador += 2;

                                int id_buscar = Integer.parseInt(aux.sig.lexema);
                                Grafo grafito = this.listaGrafos.BuscaGrafo(id_buscar).g;
                                NodoG inicia = grafito.ObtenerInicial();
                                NodoG finaliza = grafito.ObtenerFinal();
                                inicia.inicial = false;
                                finaliza.EsFinal = false;

                                //Agregando estados de grafito a grafoAuxiliar
                                int inservible;
                                NodoG auxi = grafito.inicio;
                                while (auxi != null) {
                                    inservible = grafoAuxiliar.AgregarNodo(auxi.id);
                                    auxi = auxi.sig;
                                }

                                //Agregando transiciones
                                auxi = grafito.inicio;
                                while (auxi != null) {
                                    NodoG auxi2 = auxi.transition.inicio;
                                    while (auxi2 != null) {
                                        grafoAuxiliar.AgregarTransicion(auxi.id, auxi2.id, auxi2.peso);
                                        auxi2 = auxi2.sig;
                                    }
                                    auxi = auxi.sig;
                                }

                                //Agregando transiciones finales
                                grafoAuxiliar.AgregarTransicion(e1, inicia.id, "EPSILON");
                                grafoAuxiliar.AgregarTransicion(finaliza.id, e2, "EPSILON");
                                grafoAuxiliar.AgregarTransicion(finaliza.id, inicia.id, "EPSILON");

                                NodoG auxiliar = grafoAuxiliar.BuscarNodo(e1);
                                auxiliar.inicial = true;
                                auxiliar = grafoAuxiliar.BuscarNodo(e2);
                                auxiliar.EsFinal = true;
                                int IdLG = listaGrafos.AgregarGrafo(grafoAuxiliar);
                                this.terminales.Actualizar(aux.id, "GRAFO", "" + IdLG);
                                this.terminales.Eliminar(aux.sig.id);
                                break;

                            } catch (NumberFormatException e) {
                                System.out.println("Error en T G |");
                            }

                        } else { // T*
                            int e1 = grafoAuxiliar.AgregarNodo(contador);
                            int e2 = grafoAuxiliar.AgregarNodo(contador + 1);
                            int e3 = grafoAuxiliar.AgregarNodo(contador + 2);
                            int e4 = grafoAuxiliar.AgregarNodo(contador + 3);
                            contador += 4;

                            grafoAuxiliar.AgregarTransicion(e1, e2, "EPSILON");
                            grafoAuxiliar.AgregarTransicion(e2, e3, aux.sig.lexema);
                            grafoAuxiliar.AgregarTransicion(e3, e4, "EPSILON");
                            grafoAuxiliar.AgregarTransicion(e3, e2, "EPSILON");

                            NodoG auxiliar = grafoAuxiliar.BuscarNodo(e1);
                            auxiliar.inicial = true;
                            auxiliar = grafoAuxiliar.BuscarNodo(e4);
                            auxiliar.EsFinal = true;

                            int idLG = listaGrafos.AgregarGrafo(grafoAuxiliar);
                            this.terminales.Actualizar(aux.id, "GRAFO", "" + idLG);
                            this.terminales.Eliminar(aux.sig.id);
                            break;

                        }
                    }

                } else if (aux.tipo.equals("OPTIONAL1")) {
                    if (aux.sig != null) {
                        grafoAuxiliar = new Grafo();
                        if (aux.sig.tipo.equals("GRAFO")) { // G?
                            try {
                                int e1 = grafoAuxiliar.AgregarNodo(contador);
                                int e2 = grafoAuxiliar.AgregarNodo(contador + 1);
                                contador += 2;

                                int id_buscar = Integer.parseInt(aux.sig.lexema);
                                Grafo grafito = this.listaGrafos.BuscaGrafo(id_buscar).g;
                                NodoG inicia = grafito.ObtenerInicial();
                                NodoG finaliza = grafito.ObtenerFinal();
                                inicia.inicial = false;
                                finaliza.EsFinal = false;

                                //Agregando estados de grafito a grafoAuxiliar
                                int inservible;
                                NodoG auxi = grafito.inicio;
                                while (auxi != null) {
                                    inservible = grafoAuxiliar.AgregarNodo(auxi.id);
                                    auxi = auxi.sig;
                                }

                                //Agregando transiciones
                                auxi = grafito.inicio;
                                while (auxi != null) {
                                    NodoG auxi2 = auxi.transition.inicio;
                                    while (auxi2 != null) {
                                        grafoAuxiliar.AgregarTransicion(auxi.id, auxi2.id, auxi2.peso);
                                        auxi2 = auxi2.sig;
                                    }
                                    auxi = auxi.sig;
                                }

                                //Agregando transiciones finales
                                grafoAuxiliar.AgregarTransicion(e1, inicia.id, "EPSILON");
                                grafoAuxiliar.AgregarTransicion(finaliza.id, e2, "EPSILON");
                                grafoAuxiliar.AgregarTransicion(e1, e2, "EPSILON");

                                NodoG auxiliar = grafoAuxiliar.BuscarNodo(e1);
                                auxiliar.inicial = true;
                                auxiliar = grafoAuxiliar.BuscarNodo(e2);
                                auxiliar.EsFinal = true;
                                int IdLG = listaGrafos.AgregarGrafo(grafoAuxiliar);
                                this.terminales.Actualizar(aux.id, "GRAFO", "" + IdLG);
                                this.terminales.Eliminar(aux.sig.id);
                                break;

                            } catch (NumberFormatException e) {
                                System.out.println("Error en T G |");
                            }

                        } else { // T?
                            int e1 = grafoAuxiliar.AgregarNodo(contador);
                            int e2 = grafoAuxiliar.AgregarNodo(contador + 1);
                            int e3 = grafoAuxiliar.AgregarNodo(contador + 2);
                            int e4 = grafoAuxiliar.AgregarNodo(contador + 3);
                            contador += 4;

                            grafoAuxiliar.AgregarTransicion(e1, e2, "EPSILON");
                            grafoAuxiliar.AgregarTransicion(e2, e3, aux.sig.lexema);
                            grafoAuxiliar.AgregarTransicion(e3, e4, "EPSILON");
                            grafoAuxiliar.AgregarTransicion(e1, e4, "EPSILON");

                            NodoG auxiliar = grafoAuxiliar.BuscarNodo(e1);
                            auxiliar.inicial = true;
                            auxiliar = grafoAuxiliar.BuscarNodo(e4);
                            auxiliar.EsFinal = true;

                            int idLG = listaGrafos.AgregarGrafo(grafoAuxiliar);
                            this.terminales.Actualizar(aux.id, "GRAFO", "" + idLG);
                            this.terminales.Eliminar(aux.sig.id);
                            break;

                        }
                    }

                } else {
                    //es un terminal
                }
                aux = aux.ant;
            }
        }

        try {
            int id_buscar = Integer.parseInt(this.terminales.inicio.lexema);
            Grafo grafito = this.listaGrafos.BuscaGrafo(id_buscar).g;
            this.grafo = grafito;
        } catch (NumberFormatException e) {
            System.out.println("Ocurrio un error en G T");

        }

    }

    public String GenerarDot() {
        String cadena = "digraph AFND{\n";
        cadena += "rankdir=LR;\n";
        cadena += "nFlecha[label=\"flecha\" color=\"white\" fontcolor=\"white\"]\n";
        cadena += "nIdentificador [label=\"" + this.identificador + "\"];\n";

        //creando estados
        NodoG aux = this.grafo.inicio;
        while (aux != null) {
            if (aux.EsFinal) {
                cadena += "n" + aux.id + " [label=\"S" + aux.id + "\" shape=doublecircle]\n";
            } else {
                cadena += "n" + aux.id + " [label=\"S" + aux.id + "\" shape=circle]\n";
            }
            aux = aux.sig;
        }

        //Realizando uniones
        aux = this.grafo.inicio;

        while (aux != null) {
            if (aux.inicial) {
                cadena += "nFlecha->n" + aux.id + "\n";
            }
            NodoG aux2 = aux.transition.inicio;
            while (aux2 != null) {
                String termina = String.valueOf(aux2.peso);
                if (!(termina.contains("\\\""))) {
                    termina = termina.replace("\\", "\\\\");
                }else{
                    termina = termina.replace("\\\"", "\\\\\\\"");
                }
                cadena += "n" + aux.id + "->n" + aux2.id + "[label=\"" + termina + "\"];\n";
                aux2 = aux2.sig;
            }
            aux = aux.sig;
        }

        cadena += "\n}";
        return cadena;
    }

    public void AFND_Graphviz() {
        File[] lista = null;
        int numero = 0;
        String directoryName = System.getProperty("user.dir");

        File directorio = new File(directoryName + "/AFND_202006629");
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
            f = new File(directoryName + "/AFND_202006629/afnd.dot");
            numero = -1;

        } else {
            if (lista.length == 0) {
                f = new File(directoryName + "/AFND_202006629/afnd.dot");
                numero = -1;

            } else {
                f = new File(directoryName + "/AFND_202006629/afnd" + lista.length + ".dot");
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
                pbuilder = new ProcessBuilder("dot", "-Tpdf", "-o", directoryName + "/AFND_202006629/afnd.pdf", directoryName + "/AFND_202006629/afnd.dot");

            } else {
                pbuilder = new ProcessBuilder("dot", "-Tpdf", "-o", directoryName + "/AFND_202006629/afnd" + numero + ".pdf", directoryName + "/AFND_202006629/afnd" + numero + ".dot");
            }
            pbuilder.redirectErrorStream(true);

            pbuilder.start();

        } catch (Exception ex) {
            System.out.println("" + ex);
        }
    }
}
