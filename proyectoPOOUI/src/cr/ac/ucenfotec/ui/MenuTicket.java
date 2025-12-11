package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

import java.util.List;

public class MenuTicket {

    private IO io = new IO();
    private Controller controller;

    public MenuTicket(Controller controller) {
        this.controller = controller;
    }

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

    private void registrar() {
        if (!controller.hayUsuarios() || !controller.hayDepartamentos()) {
            System.out.println("Debes tener al menos 1 Usuario y 1 Departamento registrados.");
            return;
        }

        System.out.println("\n> Elige Usuario reportante:");
        List<String> usuariosTxt = controller.obtenerUsuariosComoTexto();
        usuariosTxt.forEach(System.out::println);
        int uid = io.i("Id de usuario: ");
        if (!controller.existeUsuario(uid)) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.println("\n> Elige Departamento asignado:");
        List<String> deptosTxt = controller.obtenerDepartamentosComoTexto();
        deptosTxt.forEach(System.out::println);
        int did = io.i("Id de departamento: ");
        if (!controller.existeDepartamento(did)) {
            System.out.println("Departamento no encontrado.");
            return;
        }

        String titulo = io.str("Título: ");
        String desc   = io.str("Descripción: ");
        String estado = io.str("Estado (nuevo/en progreso/cerrado): ");

        controller.registrarTicket(titulo, desc, estado, uid, did);
        System.out.println("Ticket registrado.");

        // ====== ANÁLISIS BoW SOBRE LA DESCRIPCIÓN ======
        String[] analisis = controller.analizarDescripcionTicket(desc);

        System.out.println("\n>>> Análisis automático de la descripción (BoW) <<<");
        System.out.println("Estado de ánimo detectado: " + analisis[0]);
        System.out.println("Categoría técnica sugerida: " + analisis[1]);
        System.out.println("---------------------------------------------------");
    }

    private void listar() {
        System.out.println("\nTickets registrados:");
        List<String> ticketsTxt = controller.obtenerTicketsComoTexto();
        if (ticketsTxt.isEmpty()) {
            System.out.println("(sin tickets)");
            return;
        }
        ticketsTxt.forEach(System.out::println);
    }
}
