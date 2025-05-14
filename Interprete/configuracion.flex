package mx.unam.aragon.interprete.lexico;
import java_cup.runtime.*;
import java.util.LinkedList;

%%
%{
    public static LinkedList<TError> TablaEL = new LinkedList<TError>();
%}

%public
%class Lexico
%cupsym Simbolos
%cup
%char
%column
%full
%ignorecase
%line
%unicode

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn, yytext());
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

// Expresiones regulares
PUNTOCOMA = ";"
LLAVE = "{"
FINLLAVE = "}"
LPR = "limpiar"
PS = "posicion"
CIR = "circulo"
REC = "rectangulo"
LIN = "linea"
C = "color"
COMA = ","
IGUAL = "="
F = "fondo"
PAR_DER = ")"
PAR_IZQ = "("
MIENTRAS = "mientras"
MV = "mover"
OB = "ob"
MAYORQUE = ">"
MENORQUE = "<"
MAS = "+"
ENTERO = [0-9]+
ID = [a-zA-Z_ñÑ][a-zA-Z0-9_ñÑ]*
ESPACIO = [ \t\r\n\f]
COMENTARIO = "#".*

%%

// Reglas léxicas
{PUNTOCOMA}   { return symbol(sym.PUNTOCOMA); }
{LLAVE}       { return symbol(sym.LLAVE); }
{FINLLAVE}    { return symbol(sym.FINLLAVE); }
{LPR}         { return symbol(sym.LPR); }
{PS}          { return symbol(sym.PS); }
{CIR}         { return symbol(sym.CIR); }
{REC}         { return symbol(sym.REC); }
{LIN}         { return symbol(sym.LIN); }
{C}           { return symbol(sym.C); }
{COMA}        { return symbol(sym.COMA); }
{IGUAL}       { return symbol(sym.IGUAL); }
{F}           { return symbol(sym.F); }
{PAR_DER}     { return symbol(sym.PAR_DER); }
{PAR_IZQ}     { return symbol(sym.PAR_IZQ); }
{MIENTRAS}    { return symbol(sym.MIENTRAS); }
{MV}          { return symbol(sym.MV); }
{OB}          { return symbol(sym.OB); }
{MAYORQUE}    { return symbol(sym.MAYORQUE); }
{MENORQUE}    { return symbol(sym.MENORQUE); }
{MAS}         { return symbol(sym.MAS); }

{ENTERO}      { return symbol(sym.ENTERO, Integer.parseInt(yytext())); }
{ID}          {
                 // Verificar que no sea palabra reservada
                 if (!yytext().matches("limpiar|posicion|circulo|rectangulo|linea|color|fondo|mientras|mover|ob")) {
                     return symbol(sym.ID, yytext());
                 }
              }

{ESPACIO}     { /* Ignorar espacios */ }
{COMENTARIO}  { /* Ignorar comentarios */ }

. {
    System.err.println("Error léxico: '" + yytext() + "' en línea " + (yyline+1) + ", columna " + (yycolumn+1));
    return symbol(sym.ERROR);
}
