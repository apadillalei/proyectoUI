package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

/**
 * Menú principal del sistema HelpDesk U. Se muestra después de un
 * inicio de sesión exitoso y permite acceder a los submenús de
 * departamentos, tickets y diccionarios.
 *
 * La clase pertenece a la capa de Interfaz de Usuario (UI) y delega
 * toda la lógica operativa al {@link Controller}.
 */
public class MenuPrincipal {

    /** Utilidad para entrada desde la consola. */
    private IO io = new IO();

    /** Controlador principal del sistema. */
    private Controller controller;

    /** Submenú de departamentos. */
    private MenuDepartamento menuDepartamento;

    /** Submenú de tickets. */
    private MenuTicket menuTicket;

    /** Submenú de diccionarios. */
    private MenuDiccionario menuDiccionario;

    /**
     * Construye el menú principal y crea las instancias de
     * los submenús específicos del sistema.
     *
     * @param controller controlador principal
     */
    public MenuPrincipal(Controller controller) {
        this.controller = controller;
        this.menuDepartamento = new MenuDepartamento(controller);
        this.menuTicket       = new MenuTicket(controller);
        this.menuDiccionario  = new MenuDiccionario(controller);
    }

    /**
     * Inicia el menú principal en un ciclo interactivo que se mantiene
     * hasta que el usuario selecciona la opción de cerrar sesión.
     */
    public void iniciar() {
        int op;
        do {
            System.out.println("\n────────────────────────────────────────");
            System.out.println("                HELP DESK U");
            System.out.println("              MENÚ PRINCIPAL");
            System.out.println("────────────────────────────────────────");

            String nombreUsuario = controller.getNombreUsuarioActual();
            if (nombreUsuario != null && !nombreUsuario.isBlank()) {
                System.out.println("Usuario conectado: " + nombreUsuario + "\n");
            }

            System.out.println("¿Qué desea gestionar?\n");
            System.out.println("  [1] Departamentos");
            System.out.println("  [2] Tickets");
            System.out.println("  [3] Diccionarios y palabras\n");
            System.out.println("  [0] Cerrar sesión y salir");
            System.out.println("────────────────────────────────────────");

            op = io.i("Opción: ");

            switch (op) {
                case 1 -> menuDepartamento.mostrar();
                case 2 -> menuTicket.mostrar();
                case 3 -> menuDiccionario.mostrar();
                case 0 -> System.out.println("\nCerrando sesión... ¡Hasta pronto!");
                default -> System.out.println("\nOpción inválida. Intente nuevamente.\n");
            }
        } while (op != 0);
    }
}
