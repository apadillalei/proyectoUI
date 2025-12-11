package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

public class MenuUsuario {

    private IO io = new IO();
    private Controller controller;

    public MenuUsuario(Controller controller) {
        this.controller = controller;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("\n--- Usuarios ---");
            System.out.println("1) Registrar");
            System.out.println("2) Listar");
            System.out.println("3) Actualizar");
            System.out.println("4) Eliminar");
            System.out.println("0) Volver");
            op = io.i("Opción: ");

            switch (op) {
                case 1 -> registrar();
                case 2 -> listar();
                case 3 -> actualizar();
                case 4 -> eliminar();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    private void registrar() {
        String nombre = io.str("Nombre completo: ");
        String correo = io.str("Correo: ");
        String pass   = io.str("Contraseña: ");
        String tel    = io.str("Teléfono: ");
        String rol    = io.str("Rol: ");

        boolean ok = controller.registrarUsuario(nombre, correo, pass, tel, rol);

        if (ok) {
            System.out.println("Usuario registrado correctamente.");
        } else {
            System.out.println("Ya existe un usuario con ese correo. Intenta con otro.");
        }
    }

    private void listar() {
        System.out.println("\nUsuarios registrados:");
        var lista = controller.obtenerUsuariosComoTexto();
        if (lista.isEmpty()) {
            System.out.println("(sin usuarios)");
            return;
        }
        lista.forEach(System.out::println);
    }

    private void actualizar() {
        listar();
        if (!controller.hayUsuarios()) return;

        int id = io.i("Id de usuario a actualizar: ");
        if (!controller.existeUsuario(id)) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        String nombre = io.str("Nuevo nombre: ");
        String correo = io.str("Nuevo correo: ");
        String pass   = io.str("Nueva contraseña: ");
        String tel    = io.str("Nuevo teléfono: ");
        String rol    = io.str("Nuevo rol: ");

        boolean ok = controller.actualizarUsuario(id, nombre, correo, pass, tel, rol);
        if (ok) {
            System.out.println("Usuario actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el usuario.");
        }
    }

    private void eliminar() {
        listar();
        if (!controller.hayUsuarios()) return;

        int id = io.i("Id de usuario a eliminar: ");
        if (!controller.existeUsuario(id)) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        controller.eliminarUsuario(id);
        System.out.println("Usuario eliminado (si no tenía restricciones en BD).");
    }
}
