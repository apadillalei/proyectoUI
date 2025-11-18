package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.tl.Controller;

public class MenuUsuario {

    private  IO io = new IO();
    private  Controller controller;

    public MenuUsuario(Controller controller) {
        this.controller = controller;
    }

    public void mostrar(){
        int op;
        do{
            System.out.println("\n--- Usuarios ---");
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
        String nombre = io.str("Nombre completo: ");
        String correo = io.str("Correo: ");
        String pass   = io.str("Contraseña: ");
        String tel    = io.str("Teléfono: ");
        String rol    = io.str("Rol: ");

        controller.registrarUsuario(nombre, correo, pass, tel, rol);
        System.out.println("Usuario registrado.");
    }

    private void listar(){
        System.out.println("\nUsuarios registrados:");
        for (Usuario u : controller.obtenerUsuarios()) {
            System.out.println(u);
        }
    }
}
