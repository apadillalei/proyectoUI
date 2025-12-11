package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entities.Ticket;
import cr.ac.ucenfotec.tl.Controller;

import java.util.List;

/**
 * Menú encargado de gestionar los tickets dentro del sistema HelpDesk U.
 * Permite registrar nuevos tickets, listarlos y ejecutar el análisis
 * Bag of Words (BoW) sobre la descripción de un ticket.
 *
 * La clase pertenece a la capa de Interfaz de Usuario (UI) y delega la
 * lógica al {@link Controller}.
 */
public class MenuTicket {

    /** Utilidad para lectura desde consola. */
    private IO io = new IO();

    /** Controlador principal del sistema. */
    private Controller controller;

    /**
     * Construye el menú de tickets asociándolo al controlador principal.
     *
     * @param controller controlador del sistema
     */
    public MenuTicket(Controller controller) {
        this.controller = controller;
    }

    /**
     * Muestra el menú de tickets en un ciclo interactivo,
     * permitiendo registrar, listar o analizar tickets.
     */
    public void mostrar() {
        int op;
        do {
            System.out.println("\n────────────────────────────────────────");
            System.out.println("            GESTIÓN DE TICKETS");
            System.out.println("────────────────────────────────────────");
            System.out.println("Seleccione una opción:\n");
            System.out.println("  [1] Registrar ticket");
            System.out.println("  [2] Listar tickets");
            System.out.println("  [3] Analizar ticket (BoW)");
            System.out.println();
            System.out.println("  [0] Volver al menú principal");
            System.out.println("────────────────────────────────────────");

            op = io.i("Opción: ");

            switch (op) {
                case 1 -> registrar();
                case 2 -> listar();
                case 3 -> analizar();
                case 0 -> System.out.println("\nVolviendo al menú principal...\n");
                default -> System.out.println("\nOpción inválida. Intente nuevamente.\n");
            }
        } while (op != 0);
    }

    /**
     * Registra un nuevo ticket solicitando al usuario:
     * <ul>
     *     <li>Usuario reportante</li>
     *     <li>Departamento asignado</li>
     *     <li>Título, descripción y estado</li>
     * </ul>
     * Una vez registrado, realiza automáticamente un análisis BoW
     * sobre la descripción.
     */
    private void registrar() {
        System.out.println("\n────────────────────────────────────────");
        System.out.println("            REGISTRAR TICKET");
        System.out.println("────────────────────────────────────────");

        if (!controller.hayUsuarios() || !controller.hayDepartamentos()) {
            System.out.println("Debes tener al menos 1 Usuario y 1 Departamento registrados.\n");
            return;
        }

        // Selección de usuario
        System.out.println("> Elige Usuario reportante:");
        List<String> usuariosTxt = controller.obtenerUsuariosComoTexto();
        usuariosTxt.forEach(System.out::println);

        int uid = io.i("Id de usuario: ");
        if (!controller.existeUsuario(uid)) {
            System.out.println("\nUsuario no encontrado.\n");
            return;
        }

        // Selección de departamento
        System.out.println("\n> Elige Departamento asignado:");
        List<String> deptosTxt = controller.obtenerDepartamentosComoTexto();
        deptosTxt.forEach(System.out::println);

        int did = io.i("Id de departamento: ");
        if (!controller.existeDepartamento(did)) {
            System.out.println("\nDepartamento no encontrado.\n");
            return;
        }

        // Datos del ticket
        String titulo = io.str("Título: ");
        String desc   = io.str("Descripción: ");
        String estado = io.str("Estado (nuevo/en progreso/cerrado): ");

        controller.registrarTicket(titulo, desc, estado, uid, did);
        System.out.println("\nTicket registrado correctamente.");

        // Análisis automático
        String[] analisis = controller.analizarDescripcionTicket(desc);
        System.out.println("\n>>> Análisis automático de la descripción (BoW) <<<");
        System.out.println("Estado de ánimo detectado: " + analisis[0]);
        System.out.println("Categoría técnica sugerida: " + analisis[1]);
        System.out.println("---------------------------------------------------\n");
    }

    /**
     * Lista todos los tickets registrados en el sistema.
     * Si no hay tickets, muestra un mensaje correspondiente.
     */
    private void listar() {
        System.out.println("\n────────────────────────────────────────");
        System.out.println("             TICKETS REGISTRADOS");
        System.out.println("────────────────────────────────────────");

        List<String> ticketsTxt = controller.obtenerTicketsComoTexto();
        if (ticketsTxt.isEmpty()) {
            System.out.println("(sin tickets registrados)\n");
            return;
        }

        ticketsTxt.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Permite seleccionar un ticket y ejecutar un análisis
     * BoW detallado sobre su descripción, mostrando:
     * <ul>
     *     <li>Estado de ánimo detectado</li>
     *     <li>Categoría técnica sugerida</li>
     *     <li>Vector TF (palabra:frecuencia)</li>
     *     <li>Palabras detectadas tras preprocesamiento</li>
     * </ul>
     */
    private void analizar() {
        List<Ticket> tickets = controller.obtenerTickets();
        if (tickets.isEmpty()) {
            System.out.println("\nNo hay tickets registrados para analizar.\n");
            return;
        }

        System.out.println("\n────────────────────────────────────────");
        System.out.println("          ANÁLISIS BoW DE TICKETS");
        System.out.println("────────────────────────────────────────");
        System.out.println("Seleccione un ticket para analizar:\n");

        for (int i = 0; i < tickets.size(); i++) {
            System.out.println((i + 1) + ") " + tickets.get(i));
        }

        int opcion = io.i("\nNúmero de ticket: ");
        int indice = opcion - 1;

        if (indice < 0 || indice >= tickets.size()) {
            System.out.println("\nOpción inválida.\n");
            return;
        }

        Ticket t = tickets.get(indice);
        String descripcion = t.getDescripcion();

        if (descripcion == null || descripcion.isBlank()) {
            System.out.println("\nEl ticket no tiene descripción para analizar.\n");
            return;
        }

        String[] resultado = controller.analizarDescripcionTicketDetallado(descripcion);

        String estadoAnimo      = resultado[0];
        String categoriaTecnica = resultado[1];
        String tfComoTexto      = resultado[2];
        String palabrasDetect   = resultado[3];

        System.out.println("\n────────────────────────────────────────");
        System.out.println("        RESULTADO ANÁLISIS BoW");
        System.out.println("────────────────────────────────────────");
        System.out.println("Descripción original:");
        System.out.println("  " + descripcion + "\n");
        System.out.println("Estado de ánimo detectado: " + estadoAnimo);
        System.out.println("Categoría técnica sugerida: " + categoriaTecnica + "\n");
        System.out.println("Vector TF (palabra:frecuencia):");
        System.out.println("  " + tfComoTexto + "\n");
        System.out.println("Palabras detectadas (después de preprocesar):");
        System.out.println("  " + (palabrasDetect.isEmpty() ? "(ninguna)" : palabrasDetect));
        System.out.println();
    }
}
