package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

/**
 * Menú encargado de gestionar las operaciones relacionadas con los usuarios.
 * Permite registrar nuevos usuarios y listar los usuarios existentes.
 *
 * Forma parte de la capa de UI, y su responsabilidad es interactuar con el usuario
 * y delegar toda la lógica al Controller correspondiente.
 */
public class MenuUsuario {

    private IO io = new IO();
    private Controller controller;

    /**
     * Constructor del menú de usuarios.
     *
     * @param controller Controlador que administra la lógica del sistema.
     */
    public MenuUsuario(Controller controller) {
        this.controller = controller;
    }

    /**
     * Muestra el menú de gestión de usuarios y permite seleccionar opciones.
     * El ciclo finaliza cuando el usuario selecciona la opción 0.
     */
    public void mostrar() {
        int op;
        do {
            System.out.println("\n--- Usuarios ---");
            System.out.println("1) Registrar");
            System.out.println("2) Listar");
            System.out.println("0) Volver");
            op = io.i("Opción: ");

            switch (op) {
                case 1 -> registrar();
                case 2 -> listar();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    /**
     * Captura los datos necesarios para registrar un nuevo usuario
     * y delega el proceso al Controller.
     */
    private void registrar() {
        String nombre = io.str("Nombre completo: ");
        String correo = io.str("Correo: ");
        String pass   = io.str("Contraseña: ");
        String tel    = io.str("Teléfono: ");
        String rol    = io.str("Rol: ");

        controller.registrarUsuario(nombre, correo, pass, tel, rol);
        System.out.println("Usuario registrado.");
    }

    /**
     * Muestra en consola la lista de usuarios registrados
     * obtenida desde el Controller en formato de texto.
     */
    private void listar() {
        System.out.println("\nUsuarios registrados:");
        var lista = controller.obtenerUsuariosComoTexto();
        if (lista.isEmpty()) {
            System.out.println("(sin usuarios)");
            return;
        }
        for (String linea : lista) {
            System.out.println(linea);
        }
    }
}
