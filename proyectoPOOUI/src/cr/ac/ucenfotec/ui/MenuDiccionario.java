package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entities.Diccionario;
import cr.ac.ucenfotec.bl.entities.Palabra;
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

    private void crearDiccionario(){
        String tipo = io.str("Tipo (emocional/tecnico): ");
        controller.crearDiccionario(tipo);
        System.out.println("Diccionario creado.");
    }

    private void agregarPalabra(){
        List<Diccionario> diccionarios = controller.obtenerDiccionarios();
        if (diccionarios.isEmpty()){
            System.out.println("No hay diccionarios. Crea uno primero.");
            return;
        }

        listarDiccionarios();
        int id = io.i("Id del diccionario: ");
        Diccionario dic = controller.buscarDiccionarioPorId(id);
        if (dic == null){
            System.out.println("Diccionario no encontrado.");
            return;
        }

        String texto = io.str("Palabra: ");
        String categoria = io.str("Categoría/Emoción: ");

        controller.agregarPalabraADiccionario(dic, texto, categoria);
        System.out.println("Palabra agregada.");
    }

    private void listarDiccionarios(){
        System.out.println("\nDiccionarios:");
        List<Diccionario> lista = controller.obtenerDiccionarios();
        if (lista.isEmpty()){
            System.out.println("(sin diccionarios)");
            return;
        }
        lista.forEach(System.out::println);
    }

    private void listarPalabras(){
        List<Diccionario> diccionarios = controller.obtenerDiccionarios();
        if (diccionarios.isEmpty()){
            System.out.println("No hay diccionarios. Crea uno primero.");
            return;
        }

        listarDiccionarios();
        int id = io.i("Id del diccionario: ");
        Diccionario dic = controller.buscarDiccionarioPorId(id);
        if (dic == null){
            System.out.println("Diccionario no encontrado.");
            return;
        }

        System.out.println("\nPalabras:");
        List<Palabra> palabras = controller.obtenerPalabrasDeDiccionario(dic);
        if (palabras == null || palabras.isEmpty()){
            System.out.println("(sin palabras)");
            return;
        }
        palabras.forEach(System.out::println);
    }
}
