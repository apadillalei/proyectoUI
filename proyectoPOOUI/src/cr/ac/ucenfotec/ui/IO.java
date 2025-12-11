package cr.ac.ucenfotec.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Gestiona la lectura de datos desde la consola.
 * Proporciona métodos simples para leer texto y números enteros.
 */
public class IO {

    /** Lector de entrada estándar desde consola. */
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Lee una línea de texto desde la consola.
     *
     * @param prompt mensaje que se muestra al usuario
     * @return texto ingresado por el usuario, sin espacios al inicio ni al final;
     *         o cadena vacía si ocurre un error de lectura
     */
    public String str(String prompt) {
        System.out.print(prompt);
        try {
            return in.readLine().trim();
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Lee un número entero desde la consola.
     *
     * @param prompt mensaje que se muestra al usuario
     * @return número entero ingresado o {@code -1} si el valor no es válido
     */
    public int i(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(in.readLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
}
