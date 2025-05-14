package mx.unam.aragon.interprete.lexico;

import java.io.FileReader;

public class inico {
    private boolean error = false;
    private static Tokens tokens = null;
    private static Lexico analizador = null;

    public static void main(String[] args) {
        try {
            // 1. Crear el lexer
            Lexico lexer = new Lexico(new FileReader("archivo.txt"));

            // 2. Crear el parser pasándole el lexer
            Parser sintactico = new Parser(lexer);

            // 3. Ejecutar el análisis sintáctico
            Sym resultado = sintactico.Paser();

            // 4. Mostrar resultado final
            System.out.println("Análisis completado correctamente");
        } catch (Exception e) {
            System.err.println("Error durante el análisis:");
            e.printStackTrace();
        }
    }

}
