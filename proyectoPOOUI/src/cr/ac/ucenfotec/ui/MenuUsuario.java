package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

/**
 * Menú de gestión de usuarios dentro del sistema HelpDesk U.
 * Permite registrar, listar, actualizar y eliminar usuarios.
 *
 * Esta clase corresponde a la capa de Interfaz de Usuario (UI)
 * y delega toda la lógica de negocio al {@link Controller}.
 */
public class MenuUsuario {

    /** Utilidad para lectura desde consola. */
    private IO io = new IO();

    /** Controlador principal del sistema. */
    private Controller controller;

    /**
     * Construye el menú de usuarios.
     *
     * @param controller controlador del sistema
     */
    public MenuUsuario(Controller controller) {
        this.controller = controller;
    }

    /**
     * Muestra el menú de gestión de usuarios en un ciclo interactivo.
     * El menú permanece activo hasta que el usuario selecciona la opción 0.
     */
    public void mostrar() {
        int op;
        do {
            System.out.println("\n────────────────────────────────────────");
            System.out.println("           GESTIÓN DE USUARIOS");
            System.out.println("────────────────────────────────────────");
            System.out.println("Seleccione una opción:\n");
            System.out.println("  [1] Registrar usuario");
            System.out.println("  [2] Listar usuarios");
            System.out.println("  [3] Actualizar usuario");
            System.out.println("  [4] Eliminar usuario");
            System.out.println();
            System.out.println("  [0] Volver al menú anterior");
            System.out.println("────────────────────────────────────────");

            op = io.i("Opción: ");

            switch (op) {
                case 1 -> registrar();
                case 2 -> listar();
                case 3 -> actualizar();
                case 4 -> eliminar();
                case 0 -> System.out.println("\nVolviendo al menú anterior...\n");
                default -> System.out.println("\nOpción inválida. Intente nuevamente.\n");
            }
        } while (op != 0);
    }

    /**
     * Solicita los datos necesarios para registrar un nuevo usuario:
     * <ul>
     *     <li>Nombre completo</li>
     *     <li>Correo electrónico</li>
     *     <li>Contraseña</li>
     *     <li>Teléfono</li>
     *     <li>Rol dentro del sistema</li>
     * </ul>
     *
     * Verifica si el correo ya existe antes de registrar.
     */
    private void registrar() {
        System.out.println("\n────────────────────────────────────────");
        System.out.println("           REGISTRAR USUARIO");
        System.out.println("────────────────────────────────────────");

        String nombre = io.str("Nombre completo: ");
        String correo = io.str("Correo: ");
        String pass   = io.str("Contraseña: ");
        String tel    = io.str("Teléfono: ");
        String rol    = io.str("Rol (ej: estudiante, profesor, admin): ");

        boolean ok = controller.registrarUsuario(nombre, correo, pass, tel, rol);

        if (ok) {
            System.out.println("\nUsuario registrado correctamente.\n");
        } else {
            System.out.println("\nYa existe un usuario con ese correo. Intente con otro.\n");
        }
    }

    /**
     * Lista todos los usuarios registrados en el sistema.
     * Muestra un mensaje si no existe ninguno.
     */
    private void listar() {
        System.out.println("\n────────────────────────────────────────");
        System.out.println("            USUARIOS REGISTRADOS");
        System.out.println("────────────────────────────────────────");

        var lista = controller.obtenerUsuariosComoTexto();
        if (lista.isEmpty()) {
            System.out.println("(sin usuarios registrados)\n");
            return;
        }

        lista.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Permite actualizar un usuario existente seleccionándolo por su ID.
     * Se solicitan nuevamente todos los campos del usuario.
     */
    private void actualizar() {
        System.out.println();
        listar();
        if (!controller.hayUsuarios()) return;

        System.out.println("────────────────────────────────────────");
        System.out.println("           ACTUALIZAR USUARIO");
        System.out.println("────────────────────────────────────────");

        int id = io.i("Id de usuario a actualizar: ");
        if (!controller.existeUsuario(id)) {
            System.out.println("\nUsuario no encontrado.\n");
            return;
        }

        String nombre = io.str("Nuevo nombre: ");
        String correo = io.str("Nuevo correo: ");
        String pass   = io.str("Nueva contraseña: ");
        String tel    = io.str("Nuevo teléfono: ");
        String rol    = io.str("Nuevo rol: ");

        boolean ok = controller.actualizarUsuario(id, nombre, correo, pass, tel, rol);

        if (ok) {
            System.out.println("\nUsuario actualizado correctamente.\n");
        } else {
            System.out.println("\nNo se pudo actualizar el usuario.\n");
        }
    }

    /**
     * Elimina un usuario existente seleccionándolo por su ID.
     */
    private void eliminar() {
        System.out.println();
        listar();
        if (!controller.hayUsuarios()) return;

        System.out.println("────────────────────────────────────────");
        System.out.println("            ELIMINAR USUARIO");
        System.out.println("────────────────────────────────────────");

        int id = io.i("Id de usuario a eliminar: ");
        if (!controller.existeUsuario(id)) {
            System.out.println("\nUsuario no encontrado.\n");
            return;
        }

        controller.eliminarUsuario(id);
        System.out.println("\n🗑 Usuario eliminado correctamente.\n");
    }
}
