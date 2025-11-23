package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

/**
 * Clase principal del proyecto HelpDesk U.
 * Representa el punto de entrada de la aplicación por consola.
 *
 * Su responsabilidad es:
 * <ul>
 *     <li>Crear la instancia del {@link Controller}</li>
 *     <li>Mostrar el menú de autenticación</li>
 *     <li>Si el login es exitoso, iniciar el menú principal</li>
 * </ul>
 *
 * No contiene lógica de negocio, solo la orquestación inicial.
 *
 */
public class Main {

    /**
     * Método principal de la aplicación.
     *
     * @param args argumentos de línea de comando (no utilizados en este proyecto).
     */
    public static void main(String[] args) {
        Controller controller = new Controller();

        MenuAutenticacion menuAutenticacion = new MenuAutenticacion(controller);
        boolean logged = menuAutenticacion.iniciar();

        if (logged) {
            MenuPrincipal menuPrincipal = new MenuPrincipal(controller);
            menuPrincipal.iniciar();
        }
    }
}
