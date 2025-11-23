package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entities.Departamento;
import cr.ac.ucenfotec.bl.entities.Ticket;
import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.tl.Controller;

import java.util.List;

/**
 * Menú responsable de la gestión de tickets dentro del sistema.
 * Permite registrar nuevos tickets y listar los existentes.
 *
 * La lógica de negocio (creación y almacenamiento del ticket)
 * es manejada por el {@link Controller}, mientras que este menú
 * se centra en la interacción con el usuario.
 *
 */
public class MenuTicket {

    private IO io = new IO();
    private Controller controller;

    /**
     * Constructor del menú de tickets.
     *
     * @param controller controlador principal para delegar las operaciones.
     */
    public MenuTicket(Controller controller) {
        this.controller = controller;
    }

    /**
     * Muestra el menú de tickets y permite elegir entre registrar
     * un nuevo ticket, listar los existentes o volver al menú anterior.
     */
    public void mostrar() {
        int op;
        do {
            System.out.println("\n--- Tickets ---");
            System.out.println("1) Registrar ticket");
            System.out.println("2) Listar tickets");
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
     * Proceso de registro de un nuevo ticket.
     * Se valida que exista al menos un usuario y un departamento,
     * se seleccionan desde listas y luego se solicita la información
     * del ticket al usuario.
     */
    private void registrar() {
        List<Usuario> usuarios = controller.obtenerUsuarios();
        List<Departamento> departamentos = controller.obtenerDepartamentos();

        if (usuarios.isEmpty() || departamentos.isEmpty()) {
            System.out.println("Debes tener al menos 1 Usuario y 1 Departamento registrados.");
            return;
        }

        System.out.println("\n> Elige Usuario reportante:");
        usuarios.forEach(System.out::println);
        int uid = io.i("Id de usuario: ");
        Usuario u = controller.buscarUsuarioPorId(uid);
        if (u == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.println("\n> Elige Departamento asignado:");
        departamentos.forEach(System.out::println);
        int did = io.i("Id de departamento: ");
        Departamento d = controller.buscarDepartamentoPorId(did);
        if (d == null) {
            System.out.println("Departamento no encontrado.");
            return;
        }

        String titulo = io.str("Título: ");
        String desc   = io.str("Descripción: ");
        String estado = io.str("Estado (nuevo/en progreso/cerrado): ");

        controller.registrarTicket(titulo, desc, estado, u, d);
        System.out.println("Ticket registrado.");
    }

    /**
     * Lista todos los tickets registrados en el sistema.
     * Si no existen tickets, muestra un mensaje indicándolo.
     */
    private void listar() {
        System.out.println("\nTickets registrados:");
        List<Ticket> tickets = controller.obtenerTickets();
        if (tickets.isEmpty()) {
            System.out.println("(sin tickets)");
            return;
        }
        tickets.forEach(System.out::println);
    }
}
