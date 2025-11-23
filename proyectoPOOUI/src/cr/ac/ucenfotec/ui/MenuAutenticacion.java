package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

/**
 * Menú encargado de gestionar el proceso de autenticación del sistema.
 * Permite registrar usuarios, iniciar sesión y validar credenciales.
 *
 * Esta clase pertenece a la capa de UI, por lo que su función es únicamente
 * interactuar con el usuario y delegar la lógica al Controller.
 */
public class MenuAutenticacion {

    private IO io = new IO();
    private Controller controller;
    private MenuUsuario menuUsuario;

    /**
     * Constructor del menú de autenticación.
     *
     * @param controller Instancia del controlador que gestiona la lógica de negocio.
     */
    public MenuAutenticacion(Controller controller) {
        this.controller = controller;
        this.menuUsuario = new MenuUsuario(controller);
    }

    /**
     * Inicia el flujo de autenticación. Presenta el menú principal inicial,
     * permite registrar usuarios o iniciar sesión.
     *
     * @return true si el usuario logró iniciar sesión correctamente,
     *         false si el programa se cierra.
     */
    public boolean iniciar() {
        int op;
        boolean logged = false;

        do {
            System.out.println("\n=== MENÚ INICIAL ===");
            System.out.println("1) Registrar usuarios");
            System.out.println("2) Iniciar sesión");
            System.out.println("0) Salir");
            op = io.i("Opción: ");

            switch (op) {
                case 1 -> menuUsuario.mostrar();
                case 2 -> {
                    if (controller.obtenerUsuarios().isEmpty()) {
                        System.out.println("No hay usuarios registrados.");
                        break;
                    }

                    System.out.println("\n=== INICIO DE SESIÓN ===");
                    String correo = io.str("Correo: ");
                    String pass = io.str("Contraseña: ");

                    boolean ok = controller.iniciarSesion(correo, pass);
                    if (ok) {
                        System.out.println("Bienvenido, "
                                + controller.getUsuarioActual().getNombre() + "!");
                        logged = true;
                    } else {
                        System.out.println("Credenciales incorrectas.");
                    }
                }
                case 0 -> {
                    System.out.println("Programa finalizado.");
                    return false;
                }
                default -> System.out.println("Opción inválida.");
            }

            if (logged) return true;

        } while (true);
    }
}
