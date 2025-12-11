package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

/**
 * Gestiona el flujo de autenticación del sistema, incluyendo la
 * creación de cuentas y el inicio de sesión de usuarios.
 */
public class MenuAutenticacion {

    /** Utilidad para lectura desde consola. */
    private IO io = new IO();

    /** Controlador principal del sistema. */
    private Controller controller;

    /** Menú de gestión de usuarios. */
    private MenuUsuario menuUsuario;

    /**
     * Crea una nueva instancia de {@code MenuAutenticacion} asociada a un controlador.
     *
     * @param controller controlador principal del sistema
     */
    public MenuAutenticacion(Controller controller) {
        this.controller = controller;
        this.menuUsuario = new MenuUsuario(controller);
    }

    /**
     * Muestra el menú de autenticación y gestiona la creación de usuarios
     * y el inicio de sesión.
     *
     * @return {@code true} si el usuario inicia sesión correctamente;
     *         {@code false} si decide salir del sistema
     */
    public boolean iniciar() {
        int op;
        boolean logged = false;

        do {
            System.out.println("\n────────────────────────────────────────");
            System.out.println("           HELP DESK U - ACCESO");
            System.out.println("────────────────────────────────────────");
            System.out.println("Bienvenido/a al sistema de soporte HelpDesk U.");
            System.out.println("Seleccione una opción para continuar:\n");
            System.out.println("  [1] Crear cuenta de usuario");
            System.out.println("  [2] Iniciar sesión");
            System.out.println();
            System.out.println("  [0] Salir del sistema");
            System.out.println("────────────────────────────────────────");
            op = io.i("Opción: ");

            switch (op) {
                case 1 -> menuUsuario.mostrar();

                case 2 -> {
                    if (!controller.hayUsuarios()) {
                        System.out.println("\nNo hay usuarios registrados todavía.");
                        System.out.println("   Primero debe crear al menos un usuario.\n");
                        break;
                    }

                    System.out.println("\n────────────────────────────────────────");
                    System.out.println("             INICIO DE SESIÓN");
                    System.out.println("────────────────────────────────────────");
                    String correo = io.str("Correo electrónico: ");
                    String pass   = io.str("Contraseña: ");

                    boolean ok = controller.iniciarSesion(correo, pass);
                    if (ok) {
                        System.out.println("\nSesión iniciada correctamente.");
                        System.out.println("   Bienvenido/a, " + controller.getNombreUsuarioActual() + "!\n");
                        logged = true;
                    } else {
                        System.out.println("\nCredenciales incorrectas. Intente de nuevo.\n");
                    }
                }

                case 0 -> {
                    System.out.println("\nPrograma finalizado. ¡Hasta pronto!");
                    return false;
                }

                default -> System.out.println("\nOpción inválida. Intente nuevamente.\n");
            }

            if (logged) {
                return true;
            }

        } while (true);
    }
}
