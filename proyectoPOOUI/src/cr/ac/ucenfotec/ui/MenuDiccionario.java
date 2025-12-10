package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

import java.util.List;

/**
 * Menú encargado de la administración de diccionarios y palabras.
 * Permite crear diccionarios, agregar palabras y listar tanto diccionarios
 * como el contenido de cada uno.
 *
 * Este módulo será la base para la futura funcionalidad de análisis
 * Bag of Words (BoW), aunque en este avance solo se trabaja con
 * almacenamiento en memoria.
 */
public class MenuDiccionario {

    private IO io = new IO();
    private Controller controller;

    /**
     * Constructor del menú de diccionarios.
     *
     * @param controller controlador utilizado para acceder a la lógica de negocio.
     */
    public MenuDiccionario(Controller controller) {
        this.controller = controller;
    }

    /**
     * Muestra el menú de diccionarios y permite crear, agregar palabras,
     * listar diccionarios o listar las palabras de un diccionario.
     */
    public void mostrar(){
        int op;
        do{
            System.out.println("\n--- Diccionarios / Palabras ---");
            System.out.println("1) Crear diccionario (tipo: emocional/tecnico)");
            System.out.println("2) Agregar palabra a un diccionario");
            System.out.println("3) Listar diccionarios");
            System.out.println("4) Listar palabras de un diccionario");
            System.out.println("0) Volver");
            op = io.i("Opción: ");

            switch (op){
                case 1 -> crearDiccionario();
                case 2 -> agregarPalabra();
                case 3 -> listarDiccionarios();
                case 4 -> listarPalabras();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while(op != 0);
    }

    /**
     * Solicita el tipo de diccionario (por ejemplo, emocional o técnico)
     * y lo envía al controlador para que sea creado.
     */
    private void crearDiccionario(){
        String tipo = io.str("Tipo (emocional/tecnico): ");
        controller.crearDiccionario(tipo);
        System.out.println("Diccionario creado.");
    }

    /**
     * Permite agregar una nueva palabra a un diccionario previamente creado.
     * Se selecciona el diccionario por id y luego se solicita el término
     * y su categoría.
     */
    private void agregarPalabra(){
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

        String texto = io.str("Palabra: ");
        String categoria = io.str("Categoría/Emoción: ");

        controller.agregarPalabraADiccionario(id, texto, categoria);
        System.out.println("Palabra agregada.");
    }

    /**
     * Lista todos los diccionarios registrados en el sistema.
     */
    private void listarDiccionarios(){
        System.out.println("\nDiccionarios:");
        List<String> lista = controller.obtenerDiccionariosComoTexto();
        if (lista.isEmpty()){
            System.out.println("(sin diccionarios)");
            return;
        }
        lista.forEach(System.out::println);
    }

    /**
     * Lista las palabras de un diccionario seleccionado por el usuario.
     * Si el diccionario no existe o no tiene palabras, se notifica en consola.
     */
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
}
