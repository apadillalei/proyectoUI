package cr.ac.ucenfotec.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase encargada de manejar la entrada de datos desde consola.
 * Utiliza BufferedReader para evitar los problemas comunes de Scanner
 * y garantizar una lectura limpia y controlada.
 *
 * Esta clase forma parte de la capa de Interfaz de Usuario (UI)
 * y su única responsabilidad es capturar texto y números ingresados
 * por el usuario.
 */
public class IO {

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Lee una línea completa como texto desde la consola.
     *
     * @param prompt Mensaje que se muestra al usuario.
     * @return La línea ingresada por el usuario, sin espacios alrededor.
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
     * Lee un número entero desde consola. Si el usuario ingresa algo inválido,
     * se retorna -1 como valor de error.
     *
     * @param prompt Mensaje que se muestra al usuario.
     * @return Número entero ingresado o -1 si ocurre un error.
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
