package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

public class MenuAutenticacion {

    private IO io = new IO();
    private Controller controller;
    private MenuUsuario menuUsuario;

    public MenuAutenticacion(Controller controller) {
        this.controller = controller;
        this.menuUsuario = new MenuUsuario(controller);
    }

    public boolean iniciar() {
        int op;
        boolean logged = false;

        do {
            System.out.println("\n=== MENÚ INICIAL ===");
            System.out.println("1) Usuarios (registrar / listar)");
            System.out.println("2) Iniciar sesión");
            System.out.println("0) Salir");
            op = io.i("Opción: ");

            switch (op) {
                case 1 -> {
                    menuUsuario.mostrar();
                }
                case 2 -> {
                    if (controller.obtenerUsuarios().isEmpty()) {
                        System.out.println("No hay usuarios registrados. Registra uno primero en la opción 1.");
                        break;
                    }

                    System.out.println("\n=== INICIO DE SESIÓN ===");
                    String correo = io.str("Correo: ");
                    String pass   = io.str("Contraseña: ");

                    boolean ok = controller.iniciarSesion(correo, pass);
                    if (ok) {
                        System.out.println("Sesión iniciada. Bienvenido, " +
                                controller.getUsuarioActual().getNombre() + "!");
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

            if (logged) {
                return true;
            }

        } while (true);
    }
}
