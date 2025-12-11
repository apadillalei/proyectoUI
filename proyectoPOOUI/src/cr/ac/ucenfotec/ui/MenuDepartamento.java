package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

import java.util.List;

public class MenuDepartamento {

    private IO io = new IO();
    private Controller controller;

    public MenuDepartamento(Controller controller) {
        this.controller = controller;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("\n--- Departamentos ---");
            System.out.println("1) Registrar departamento");
            System.out.println("2) Listar departamentos");
            System.out.println("3) Actualizar departamento");
            System.out.println("4) Eliminar departamento");
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
        String nombre = io.str("Nombre del departamento: ");
        String desc   = io.str("Descripción: ");
        String correo = io.str("Correo de contacto (opcional): ");

        controller.registrarDepartamento(nombre, desc, correo);
        System.out.println("Departamento registrado.");
    }

    private void listar() {
        System.out.println("\nDepartamentos registrados:");
        List<String> lista = controller.obtenerDepartamentosComoTexto();
        if (lista.isEmpty()) {
            System.out.println("(sin departamentos)");
            return;
        }
        lista.forEach(System.out::println);
    }

    private void actualizar() {
        listar();
        if (!controller.hayDepartamentos()) return;

        int id = io.i("Id de departamento a actualizar: ");
        if (!controller.existeDepartamento(id)) {
            System.out.println("Departamento no encontrado.");
            return;
        }

        String nombre = io.str("Nuevo nombre: ");
        String desc   = io.str("Nueva descripción: ");
        String correo = io.str("Nuevo correo de contacto: ");

        boolean ok = controller.actualizarDepartamento(id, nombre, desc, correo);
        if (ok) {
            System.out.println("Departamento actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el departamento.");
        }
    }

    private void eliminar() {
        listar();
        if (!controller.hayDepartamentos()) return;

        int id = io.i("Id de departamento a eliminar: ");
        if (!controller.existeDepartamento(id)) {
            System.out.println("Departamento no encontrado.");
            return;
        }

        controller.eliminarDepartamento(id);
        System.out.println("Departamento eliminado (si no tenía tickets asociados en BD).");
    }
}
