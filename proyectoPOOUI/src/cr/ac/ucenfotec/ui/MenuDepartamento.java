package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

public class MenuDepartamento {

    private IO io = new IO();
    private Controller controller;

    public MenuDepartamento(Controller controller) {
        this.controller = controller;
    }

    public void mostrar(){
        int op;
        do{
            System.out.println("\n--- Departamentos ---");
            System.out.println("1) Registrar");
            System.out.println("2) Listar");
            System.out.println("0) Volver");
            op = io.i("Opción: ");
            switch (op){
                case 1 -> registrar();
                case 2 -> listar();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while(op != 0);
    }

    private void registrar(){
        String nombre   = io.str("Nombre: ");
        String desc     = io.str("Descripción: ");
        String contacto = io.str("Correo de contacto: ");

        controller.registrarDepartamento(nombre, desc, contacto);
        System.out.println("Departamento registrado.");
    }

    private void listar(){
        System.out.println("\nDepartamentos registrados:");
        var lista = controller.obtenerDepartamentosComoTexto();
        if (lista.isEmpty()){
            System.out.println("(sin departamentos)");
            return;
        }
        lista.forEach(System.out::println);
    }
}
