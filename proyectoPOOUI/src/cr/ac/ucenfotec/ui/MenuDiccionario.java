package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

import java.util.List;

/**
 * Menú de gestión de diccionarios y palabras para el análisis Bag of Words.
 * Permite crear, listar, actualizar y eliminar diccionarios, así como
 * administrar las palabras asociadas a cada uno.
 */
public class MenuDiccionario {

    /** Utilidad para lectura desde consola. */
    private IO io = new IO();

    /** Controlador principal del sistema. */
    private Controller controller;

    /**
     * Crea una nueva instancia del menú de diccionarios.
     *
     * @param controller controlador del sistema
     */
    public MenuDiccionario(Controller controller) {
        this.controller = controller;
    }

    /**
     * Muestra el menú de diccionarios y procesa las opciones seleccionadas.
     */
    public void mostrar() {
        int op;
        do {
            System.out.println("\n────────────────────────────────────────");
            System.out.println("         DICCIONARIOS Y PALABRAS");
            System.out.println("────────────────────────────────────────");
            System.out.println("Gestione los diccionarios BoW y sus palabras.\n");

            System.out.println("  DICCIONARIOS:");
            System.out.println("    [1] Crear diccionario (tipo: emocional/técnico)");
            System.out.println("    [2] Listar diccionarios");
            System.out.println("    [3] Actualizar tipo de diccionario");
            System.out.println("    [4] Eliminar diccionario\n");

            System.out.println("  PALABRAS:");
            System.out.println("    [5] Agregar palabra a un diccionario");
            System.out.println("    [6] Listar palabras de un diccionario");
            System.out.println("    [7] Actualizar palabra de un diccionario");
            System.out.println("    [8] Eliminar palabra de un diccionario\n");

            System.out.println("  [0] Volver al menú principal");
            System.out.println("────────────────────────────────────────");

            op = io.i("Opción: ");

            switch (op) {
                case 1 -> crearDiccionario();
                case 2 -> listarDiccionarios();
                case 3 -> actualizarDiccionario();
                case 4 -> eliminarDiccionario();
                case 5 -> agregarPalabra();
                case 6 -> listarPalabras();
                case 7 -> actualizarPalabra();
                case 8 -> eliminarPalabra();
                case 0 -> System.out.println("\nVolviendo al menú principal...\n");
                default -> System.out.println("\nOpción inválida. Intente nuevamente.\n");
            }
        } while (op != 0);
    }

    /**
     * Solicita los datos y registra un nuevo diccionario.
     */
    private void crearDiccionario() {
        System.out.println("\n────────────────────────────────────────");
        System.out.println("           CREAR DICCIONARIO");
        System.out.println("────────────────────────────────────────");
        System.out.println("Tipos disponibles: emocional / tecnico\n");

        String tipo = io.str("Tipo (emocional/tecnico): ");
        controller.crearDiccionario(tipo);
        System.out.println("\nDiccionario creado correctamente.\n");
    }

    /**
     * Muestra la lista de diccionarios registrados.
     */
    private void listarDiccionarios() {
        System.out.println("\n────────────────────────────────────────");
        System.out.println("             DICCIONARIOS");
        System.out.println("────────────────────────────────────────");

        List<String> lista = controller.obtenerDiccionariosComoTexto();
        if (lista.isEmpty()) {
            System.out.println("(sin diccionarios registrados)\n");
            return;
        }
        lista.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Permite actualizar el tipo de un diccionario existente.
     */
    private void actualizarDiccionario() {
        if (!controller.hayDiccionarios()) {
            System.out.println("\nNo hay diccionarios. Cree uno primero.\n");
            return;
        }

        listarDiccionarios();
        System.out.println("────────────────────────────────────────");
        System.out.println("        ACTUALIZAR DICCIONARIO");
        System.out.println("────────────────────────────────────────");

        int id = io.i("Id del diccionario a actualizar: ");

        if (!controller.existeDiccionario(id)) {
            System.out.println("\nDiccionario no encontrado.\n");
            return;
        }

        String nuevoTipo = io.str("Nuevo tipo (emocional/tecnico): ");
        boolean ok = controller.actualizarDiccionario(id, nuevoTipo);
        if (ok) {
            System.out.println("\nDiccionario actualizado correctamente.\n");
        } else {
            System.out.println("\nNo se pudo actualizar el diccionario.\n");
        }
    }

    /**
     * Elimina un diccionario existente.
     */
    private void eliminarDiccionario() {
        if (!controller.hayDiccionarios()) {
            System.out.println("\nNo hay diccionarios.\n");
            return;
        }

        listarDiccionarios();
        System.out.println("────────────────────────────────────────");
        System.out.println("         ELIMINAR DICCIONARIO");
        System.out.println("────────────────────────────────────────");

        int id = io.i("Id del diccionario a eliminar: ");

        if (!controller.existeDiccionario(id)) {
            System.out.println("\nDiccionario no encontrado.\n");
            return;
        }

        boolean ok = controller.eliminarDiccionario(id);
        if (ok) {
            System.out.println("\nDiccionario eliminado (junto con sus palabras).\n");
        } else {
            System.out.println("\nNo se pudo eliminar el diccionario.\n");
        }
    }

    /**
     * Agrega una palabra a un diccionario seleccionado.
     */
    private void agregarPalabra() {
        if (!controller.hayDiccionarios()) {
            System.out.println("\nNo hay diccionarios. Cree uno primero.\n");
            return;
        }

        listarDiccionarios();
        System.out.println("────────────────────────────────────────");
        System.out.println("          AGREGAR PALABRA");
        System.out.println("────────────────────────────────────────");

        int id = io.i("Id del diccionario: ");

        if (!controller.existeDiccionario(id)) {
            System.out.println("\nDiccionario no encontrado.\n");
            return;
        }

        String texto     = io.str("Palabra: ");
        String categoria = io.str("Categoría/Emoción: ");

        boolean ok = controller.agregarPalabraADiccionario(id, texto, categoria);
        if (ok) {
            System.out.println("\nPalabra agregada correctamente.\n");
        } else {
            System.out.println("\nEsa palabra ya existe en este diccionario.\n");
        }
    }

    /**
     * Muestra las palabras asociadas a un diccionario.
     */
    private void listarPalabras() {
        if (!controller.hayDiccionarios()) {
            System.out.println("\nNo hay diccionarios. Cree uno primero.\n");
            return;
        }

        listarDiccionarios();
        int id = io.i("Id del diccionario: ");

        if (!controller.existeDiccionario(id)) {
            System.out.println("\nDiccionario no encontrado.\n");
            return;
        }

        System.out.println("\n────────────────────────────────────────");
        System.out.println("          PALABRAS DEL DICCIONARIO");
        System.out.println("────────────────────────────────────────");

        List<String> palabras = controller.obtenerPalabrasDeDiccionarioComoTexto(id);
        if (palabras == null || palabras.isEmpty()) {
            System.out.println("(sin palabras en este diccionario)\n");
            return;
        }
        palabras.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Actualiza una palabra existente dentro de un diccionario.
     */
    private void actualizarPalabra() {
        if (!controller.hayDiccionarios()) {
            System.out.println("\nNo hay diccionarios.\n");
            return;
        }

        listarDiccionarios();
        int idDic = io.i("Id del diccionario: ");

        if (!controller.existeDiccionario(idDic)) {
            System.out.println("\nDiccionario no encontrado.\n");
            return;
        }

        System.out.println("\n────────────────────────────────────────");
        System.out.println("         ACTUALIZAR PALABRA");
        System.out.println("────────────────────────────────────────");
        System.out.println("Palabras actuales:");
        List<String> palabras = controller.obtenerPalabrasDeDiccionarioComoTexto(idDic);
        if (palabras == null || palabras.isEmpty()) {
            System.out.println("(sin palabras en este diccionario)\n");
            return;
        }
        palabras.forEach(System.out::println);
        System.out.println();

        String textoOriginal = io.str("Texto EXACTO de la palabra a actualizar: ");
        String nuevoTexto    = io.str("Nuevo texto: ");
        String nuevaCategoria= io.str("Nueva categoría/emoción: ");

        boolean ok = controller.actualizarPalabraEnDiccionario(idDic, textoOriginal, nuevoTexto, nuevaCategoria);
        if (ok) {
            System.out.println("\nPalabra actualizada correctamente.\n");
        } else {
            System.out.println("\nNo se pudo actualizar la palabra (verifique el texto original).\n");
        }
    }

    /**
     * Elimina una palabra de un diccionario.
     */
    private void eliminarPalabra() {
        if (!controller.hayDiccionarios()) {
            System.out.println("\nNo hay diccionarios.\n");
            return;
        }

        listarDiccionarios();
        int idDic = io.i("Id del diccionario: ");

        if (!controller.existeDiccionario(idDic)) {
            System.out.println("\nDiccionario no encontrado.\n");
            return;
        }

        System.out.println("\n────────────────────────────────────────");
        System.out.println("          ELIMINAR PALABRA");
        System.out.println("────────────────────────────────────────");
        System.out.println("Palabras actuales:");
        List<String> palabras = controller.obtenerPalabrasDeDiccionarioComoTexto(idDic);
        if (palabras == null || palabras.isEmpty()) {
            System.out.println("(sin palabras en este diccionario)\n");
            return;
        }
        palabras.forEach(System.out::println);
        System.out.println();

        String texto = io.str("Texto EXACTO de la palabra a eliminar: ");

        boolean ok = controller.eliminarPalabraDeDiccionario(idDic, texto);
        if (ok) {
            System.out.println("\nPalabra eliminada correctamente.\n");
        } else {
            System.out.println("\nNo se pudo eliminar la palabra (verifique el texto).\n");
        }
    }
}
