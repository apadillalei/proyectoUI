package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

import java.util.List;

public class MenuDiccionario {

    private IO io = new IO();
    private Controller controller;

    public MenuDiccionario(Controller controller) {
        this.controller = controller;
    }

    public void mostrar(){
        int op;
        do{
            System.out.println("\n--- Diccionarios / Palabras ---");
            System.out.println("1) Crear diccionario (tipo: emocional/tecnico)");
            System.out.println("2) Listar diccionarios");
            System.out.println("3) Actualizar tipo de diccionario");
            System.out.println("4) Eliminar diccionario");
            System.out.println("5) Agregar palabra a un diccionario");
            System.out.println("6) Listar palabras de un diccionario");
            System.out.println("7) Actualizar palabra de un diccionario");
            System.out.println("8) Eliminar palabra de un diccionario");
            System.out.println("0) Volver");
            op = io.i("Opción: ");

            switch (op){
                case 1 -> crearDiccionario();
                case 2 -> listarDiccionarios();
                case 3 -> actualizarDiccionario();
                case 4 -> eliminarDiccionario();
                case 5 -> agregarPalabra();
                case 6 -> listarPalabras();
                case 7 -> actualizarPalabra();
                case 8 -> eliminarPalabra();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while(op != 0);
    }

    // ============ DICCIONARIOS ============

    private void crearDiccionario(){
        String tipo = io.str("Tipo (emocional/tecnico): ");
        controller.crearDiccionario(tipo);
        System.out.println("Diccionario creado.");
    }

    private void listarDiccionarios(){
        System.out.println("\nDiccionarios:");
        List<String> lista = controller.obtenerDiccionariosComoTexto();
        if (lista.isEmpty()){
            System.out.println("(sin diccionarios)");
            return;
        }
        lista.forEach(System.out::println);
    }

    private void actualizarDiccionario() {
        if (!controller.hayDiccionarios()){
            System.out.println("No hay diccionarios. Crea uno primero.");
            return;
        }

        listarDiccionarios();
        int id = io.i("Id del diccionario a actualizar: ");

        if (!controller.existeDiccionario(id)){
            System.out.println("Diccionario no encontrado.");
            return;
        }

        String nuevoTipo = io.str("Nuevo tipo (emocional/tecnico): ");
        boolean ok = controller.actualizarDiccionario(id, nuevoTipo);
        if (ok) {
            System.out.println("Diccionario actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el diccionario.");
        }
    }

    private void eliminarDiccionario() {
        if (!controller.hayDiccionarios()){
            System.out.println("No hay diccionarios.");
            return;
        }

        listarDiccionarios();
        int id = io.i("Id del diccionario a eliminar: ");

        if (!controller.existeDiccionario(id)){
            System.out.println("Diccionario no encontrado.");
            return;
        }

        boolean ok = controller.eliminarDiccionario(id);
        if (ok) {
            System.out.println("Diccionario eliminado (junto con sus palabras).");
        } else {
            System.out.println("No se pudo eliminar el diccionario.");
        }
    }

    // ============ PALABRAS ============

    private void agregarPalabra(){
        if (!controller.hayDiccionarios()) {
            System.out.println("No hay diccionarios. Crea uno primero.");
            return;
        }

        listarDiccionarios();
        int id = io.i("Id del diccionario: ");

        if (!controller.existeDiccionario(id)){
            System.out.println("Diccionario no encontrado.");
            return;
        }

        String texto     = io.str("Palabra: ");
        String categoria = io.str("Categoría/Emoción: ");

        boolean ok = controller.agregarPalabraADiccionario(id, texto, categoria);
        if (ok) {
            System.out.println("Palabra agregada.");
        } else {
            System.out.println("Esa palabra ya existe en este diccionario.");
        }
    }

    private void listarPalabras(){
        if (!controller.hayDiccionarios()){
            System.out.println("No hay diccionarios. Crea uno primero.");
            return;
        }

        listarDiccionarios();
        int id = io.i("Id del diccionario: ");

        if (!controller.existeDiccionario(id)){
            System.out.println("Diccionario no encontrado.");
            return;
        }

        System.out.println("\nPalabras:");
        List<String> palabras = controller.obtenerPalabrasDeDiccionarioComoTexto(id);
        if (palabras == null || palabras.isEmpty()){
            System.out.println("(sin palabras)");
            return;
        }
        palabras.forEach(System.out::println);
    }

    private void actualizarPalabra() {
        if (!controller.hayDiccionarios()){
            System.out.println("No hay diccionarios.");
            return;
        }

        listarDiccionarios();
        int idDic = io.i("Id del diccionario: ");

        if (!controller.existeDiccionario(idDic)){
            System.out.println("Diccionario no encontrado.");
            return;
        }

        System.out.println("\nPalabras actuales:");
        List<String> palabras = controller.obtenerPalabrasDeDiccionarioComoTexto(idDic);
        if (palabras == null || palabras.isEmpty()) {
            System.out.println("(sin palabras en este diccionario)");
            return;
        }
        palabras.forEach(System.out::println);

        String textoOriginal = io.str("Texto EXACTO de la palabra a actualizar: ");
        String nuevoTexto    = io.str("Nuevo texto: ");
        String nuevaCategoria= io.str("Nueva categoría/emoción: ");

        boolean ok = controller.actualizarPalabraEnDiccionario(idDic, textoOriginal, nuevoTexto, nuevaCategoria);
        if (ok) {
            System.out.println("Palabra actualizada correctamente.");
        } else {
            System.out.println("No se pudo actualizar la palabra (verifica el texto original).");
        }
    }

    private void eliminarPalabra() {
        if (!controller.hayDiccionarios()){
            System.out.println("No hay diccionarios.");
            return;
        }

        listarDiccionarios();
        int idDic = io.i("Id del diccionario: ");

        if (!controller.existeDiccionario(idDic)){
            System.out.println("Diccionario no encontrado.");
            return;
        }

        System.out.println("\nPalabras actuales:");
        List<String> palabras = controller.obtenerPalabrasDeDiccionarioComoTexto(idDic);
        if (palabras == null || palabras.isEmpty()) {
            System.out.println("(sin palabras en este diccionario)");
            return;
        }
        palabras.forEach(System.out::println);

        String texto = io.str("Texto EXACTO de la palabra a eliminar: ");

        boolean ok = controller.eliminarPalabraDeDiccionario(idDic, texto);
        if (ok) {
            System.out.println("Palabra eliminada.");
        } else {
            System.out.println("No se pudo eliminar la palabra (verifica el texto).");
        }
    }
}
