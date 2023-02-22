package analizadores;

import java_cup.runtime.Symbol;
import static proyectouno.ProyectoUno.errores;


%%
//Variables que me serviran
%{
    String cadena="";
    String letter="";
    String comentario="";
    boolean banderaComentario=false;
%}

//Librerias creoo se llaman jaja
%cup
%class scanner
%public
%line
%char
%column
%full
//Metodos dentro del jflex
%state CADENA
%state LETRA
%state ID
%state MULTICOMENTARIOS
%state VERIFICACION


//Simbolos y caracteres especiales definidos dentro del rango ascii que nos dieron
DOSPUNTOS = ":"
GUIONC = "~"
PUNTOCOMA = ";"
LLAVE1 = "{"
LLAVE2 = "}"
FLECHA = "->"
SEPARADOR = "%%"
COMA = ","
CONCAT1 = "."
OR1 = "|"
KLEENE1 = "*"
POSITIVE1 = "+"
OPTIONAL1 = "?"
CARACTER = [\!\#\$\%\&\'\(\)\-\.\/\:\;\>\@\[\\\]\^\_\`]

//Espacios en blanco
SPACE = [\ \r\t\f\t]
ENTER = [\ \n]

//Palabra reservada
CONJ1 = "CONJ"

//Expresion regular
ENTERO = [0-9]+

//Comentarios
COM1 = \/\/.*
COM2 = \<

%%
//EXPRESION REGULAR                     CODIGO ASOCIADO                                                         QUE HACE

<YYINITIAL> {CONJ1}                     { return new Symbol(sym.CONJ1, yyline, yycolumn,yytext());}             //Reconoce la palabra reservada CONJ

<YYINITIAL> {DOSPUNTOS}                 { return new Symbol(sym.DOSPUNTOS, yyline, yycolumn,yytext());}         //Reconoce :

<YYINITIAL> {PUNTOCOMA}                 { return new Symbol(sym.PUNTOCOMA, yyline, yycolumn,yytext());}         //Reconoce ;

<YYINITIAL> {GUIONC}                    { return new Symbol(sym.GUIONC, yyline, yycolumn,yytext());}            //Reconoce ~

<YYINITIAL> {LLAVE1}                    { return new Symbol(sym.LLAVE1, yyline, yycolumn,yytext());}            //Reconoce {

<YYINITIAL> {LLAVE2}                    { return new Symbol(sym.LLAVE2, yyline, yycolumn,yytext());}            //Reconoce }

<YYINITIAL> {FLECHA}                    { return new Symbol(sym.FLECHA, yyline, yycolumn,yytext());}            //Reconoce ->

<YYINITIAL> {SEPARADOR}                 { return new Symbol(sym.SEPARADOR, yyline, yycolumn,yytext());}         //Reconoce %%

<YYINITIAL> {COMA}                      { return new Symbol(sym.COMA, yyline, yycolumn,yytext());}              //Reconoce ,

<YYINITIAL> [\"]                        { yybegin(CADENA); 
                                        cadena="_"; }                                                           //Reconoce " y se va a comprobar si es una cadena o existe un error

<YYINITIAL> {CONCAT1}                   { return new Symbol(sym.CONCAT1, yyline, yycolumn,yytext());}           //Reconoce .

<YYINITIAL> {OR1}                       { return new Symbol(sym.OR1, yyline, yycolumn,yytext());}               //Reconoce |

<YYINITIAL> {KLEENE1}                   { return new Symbol(sym.KLEENE1, yyline, yycolumn,yytext());}           //Reconoce *

<YYINITIAL> {POSITIVE1}                 { return new Symbol(sym.POSITIVE1, yyline, yycolumn,yytext());}         //Reconoce +

<YYINITIAL> {OPTIONAL1}                 { return new Symbol(sym.OPTIONAL1, yyline, yycolumn,yytext());}         //Reconoce ?

<YYINITIAL> {COM2}                      { yybegin(VERIFICACION); comentario=yytext();}                           //Reconoce < y se va a comprobar si es comentario multilinea o error

<YYINITIAL> {CARACTER}                  { return new Symbol(sym.CARACTER, yyline, yycolumn,yytext());}          //Reconoce si es algun caracter ascii

<YYINITIAL> [A-Za-zñÑ]                  { yybegin(LETRA); letter=yytext(); }                                    //Reconoce si es una letra y se va a comprobar si es solo letra o ID

<YYINITIAL> {ENTERO}                    { return new Symbol(sym.ENTERO, yyline, yycolumn,yytext());}            //Reconoce numeros

<YYINITIAL>                             {SPACE} {}                                                              //Reconoce " " (un espacio) y lo ignora

<YYINITIAL>                             {ENTER} {}                                                              //Reconoce \n  (un salto de linea) y lo ignora

<YYINITIAL>                             {COM1}  {}                                                              //Reconoce // que es un comentario hasta el salto de linea


// ---------------------------------------------------- --------Errores---------------------------------------------------------------------------
<YYINITIAL> .                           { errores.NewError("Lexico", "El caracter '"+yytext()+"' no pertenece al lenguaje",yyline+1,yycolumn+1);//|
                                        System.out.println("El caracter '"+yytext()+"' no pertenece al lenguaje");}                             //|
// ---------------------------------------------------- -------------------------------------------------------------------------------------------


// ------------------------------------------------------------------------------------METODOS----------------------------------------------------------------------------------------------------
//                                      EXPRESION REGULAR               CODIGO ASOCIADO                                                                         QUE HACE
<CADENA>                                { 
                                        [\"]                           {String tmp=cadena; 
                                                                        cadena="";                                                                              //Revisa si termina con " 
                                                                        yybegin(YYINITIAL);                                                                     //y si lo hace si es cadena
                                                                        return new Symbol(sym.CADENA, yychar,yyline,tmp);}                                      //y vuelve al inicio

                                        [\n]                            {cadena="";                                                                             //Revisa y si no cerro "
                                                                        errores.NewError("Lexico", "Se esperaba cierre de cadena",yyline+1,yycolumn+1);         //y hay un salto de linea es
                                                                        System.out.println("Se esperaba cierre de cadena");                                     //cadena invalida, marca error
                                                                        yybegin(YYINITIAL);                                                                     //y vuelve al inicio
                                                                        }

                                        (\\\")|(\\\")|(\\n)|[^\"]       { cadena+=yytext();}}                                                                   //Revisa y sigue añadiendo chars a la cadena

<LETRA>                                 { 
                                        [_0-9A-Za-zñÑ]                 {letter+=yytext();                                                                      //Revisa si viene otra letra o numero
                                                                        yybegin(ID);                                                                            //y sí si se va a a ver si es id
                                                                        }

                                        [^_0-9A-Za-zñÑ]                 {String tmp=letter;                                                                     //Revisa si viene algo diferente a 
                                                                        letter="";                                                                              //letra o numero y sí si significa
                                                                        yybegin(YYINITIAL);                                                                     //que solo es un letra y vuelve
                                                                        yypushback(1);                                                                          
                                                                        return new Symbol(sym.LETRA, yychar,yyline,tmp);}}

<ID>                                    { 
                                        [_0-9A-Za-zñÑ]                 { letter+=yytext();}                                                                    //Mientras siga siendo letra o
                                                                                                                                                                //numero, lo sigue concatenando

                                        [^_0-9A-Za-zñÑ]                 {String tmp=letter;                                                                     //Cuando ya sea distinto de letra
                                                                        letter="";                                                                              //o número termina el id
                                                                        yybegin(YYINITIAL);
                                                                        yypushback(1);
                                                                        return new Symbol(sym.ID, yychar,yyline,tmp);}}

<VERIFICACION>                          { 
                                        [^\!]                          {String tmp=comentario;                                                                 //Revisa y si luego del < no viene
                                                                        comentario="";                                                                          // un ! no es un comentario y es
                                                                        yybegin(YYINITIAL);                                                                     // un caracter, lo guarda y retorna
                                                                        yypushback(1);
                                                                        return new Symbol(sym.CARACTER, yychar,yyline,tmp);}

                                        [\!]                            {yybegin(MULTICOMENTARIOS);                                                             //comprueba que viene un ! y eso
                                                                        comentario="";}}                                                                        //es el inicio de un comentario 
                                                                                                                                                                //se va a comprobarlo

<MULTICOMENTARIOS>                      { 
                                        \!                             {banderaComentario=true;}                                                               //si viene ! puede ser comentario
                                                                                                                                                                //guardamos un true en la bandera

                                        \>                              {if(banderaComentario){                                                                 //si viene > es comentario y lo
                                                                        yybegin(YYINITIAL);                                                                     //ignoramos pero si no tenia antes
                                                                        }else{                                                                                  // el ! es un error lexico
                                                                        errores.NewError("Lexico", "Comentario Invalido",yyline+1,yycolumn+1);}}

                                        [^\!\>]                         {}}                                                                                     //Si viene algo distinto al cierre
                                                                                                                                                                //solo lo ignora

