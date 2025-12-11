package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

import java.util.List;

/**
 * Menú de gestión de departamentos. Permite registrar, listar,
 * actualizar y eliminar departamentos desde la interfaz de consola.
 */
public class MenuDepartamento {

    /** Utilidad para lectura desde consola. */
    private IO io = new IO();

    /** Controlador principal para la gestión de datos. */
    private Controller controller;

    /**
     * Crea un menú de gestión de departamentos.
     *
     * @param controller controlador del sistema
     */
    public MenuDepartamento(Controller controller) {
        this.controller = controller;
    }

    /**
     * Muestra el menú principal de departamentos y gestiona
     * las opciones seleccionadas por el usuario.
     */
    public void mostrar() {
        int op;
        do {
            System.out.println("\n────────────────────────────────────────");
            System.out.println("        GESTIÓN DE DEPARTAMENTOS");
            System.out.println("────────────────────────────────────────");
            System.out.println("Seleccione una opción:\n");
            System.out.println("  [1] Registrar departamento");
            System.out.println("  [2] Listar departamentos");
            System.out.println("  [3] Actualizar departamento");
            System.out.println("  [4] Eliminar departamento");
            System.out.println();
            System.out.println("  [0] Volver al menú principal");
            System.out.println("────────────────────────────────────────");

            op = io.i("Opción: ");

            switch (op) {
                case 1 -> registrar();
                case 2 -> listar();
                case 3 -> actualizar();
                case 4 -> eliminar();
                case 0 -> System.out.println("\nVolviendo al menú principal...");
                default -> System.out.println("\nOpción inválida. Intente nuevamente.\n");
            }
        } while (op != 0);
    }

    /**
     * Solicita los datos de un departamento y lo registra mediante el controlador.
     */
    private void registrar() {
        System.out.println("\n────────────────────────────────────────");
        System.out.println("       REGISTRAR NUEVO DEPARTAMENTO");
        System.out.println("────────────────────────────────────────");

        String nombre = io.str("Nombre del departamento: ");
        String desc   = io.str("Descripción: ");
        String correo = io.str("Correo de contacto (opcional): ");

        controller.registrarDepartamento(nombre, desc, correo);
        System.out.println("\nDepartamento registrado correctamente.\n");
    }

    /**
     * Muestra la lista de departamentos registrados.
     */
    private void listar() {
        System.out.println("\n────────────────────────────────────────");
        System.out.println("        DEPARTAMENTOS REGISTRADOS");
        System.out.println("────────────────────────────────────────");

        List<String> lista = controller.obtenerDepartamentosComoTexto();
        if (lista.isEmpty()) {
            System.out.println("(sin departamentos registrados)\n");
            return;
        }

        lista.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Permite actualizar un departamento existente solicitando sus nuevos datos.
     */
    private void actualizar() {
        System.out.println();
        listar();
        if (!controller.hayDepartamentos()) return;

        System.out.println("────────────────────────────────────────");
        System.out.println("        ACTUALIZAR DEPARTAMENTO");
        System.out.println("────────────────────────────────────────");

        int id = io.i("Id del departamento a actualizar: ");
        if (!controller.existeDepartamento(id)) {
            System.out.println("\nDepartamento no encontrado.\n");
            return;
        }

        String nombre = io.str("Nuevo nombre: ");
        String desc   = io.str("Nueva descripción: ");
        String correo = io.str("Nuevo correo de contacto: ");

        boolean ok = controller.actualizarDepartamento(id, nombre, desc, correo);
        if (ok) {
            System.out.println("\nDepartamento actualizado correctamente.\n");
        } else {
            System.out.println("\nNo se pudo actualizar el departamento.\n");
        }
    }

    /**
     * Elimina un departamento existente según el id ingresado.
     */
    private void eliminar() {
        System.out.println();
        listar();
        if (!controller.hayDepartamentos()) return;

        System.out.println("────────────────────────────────────────");
        System.out.println("         ELIMINAR DEPARTAMENTO");
        System.out.println("────────────────────────────────────────");

        int id = io.i("Id del departamento a eliminar: ");
        if (!controller.existeDepartamento(id)) {
            System.out.println("\nDepartamento no encontrado.\n");
            return;
        }

        controller.eliminarDepartamento(id);
        System.out.println("\nDepartamento eliminado.\n");
    }
}
