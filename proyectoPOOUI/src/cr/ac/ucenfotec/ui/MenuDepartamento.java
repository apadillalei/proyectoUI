package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entities.Departamento;
import cr.ac.ucenfotec.tl.Controller;

/**
 * Menú de gestión de departamentos.
 * Permite registrar nuevos departamentos y listar los existentes.
 *
 * Este menú se limita a la interacción con el usuario y delega las operaciones
 * de negocio al {@link Controller}.
 */
public class MenuDepartamento {

    private IO io = new IO();
    private Controller controller;

    /**
     * Constructor del menú de departamentos.
     *
     * @param controller controlador que gestiona la lógica de departamentos.
     */
    public MenuDepartamento(Controller controller) {
        this.controller = controller;
    }

    /**
     * Muestra el menú de departamentos y permite al usuario elegir
     * entre registrar o listar departamentos, o volver al menú anterior.
     */
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

    /**
     * Solicita los datos del nuevo departamento al usuario
     * y los envía al controlador para que sea registrado.
     */
    private void registrar(){
        String nombre   = io.str("Nombre: ");
        String desc     = io.str("Descripción: ");
        String contacto = io.str("Correo de contacto: ");

        controller.registrarDepartamento(nombre, desc, contacto);
        System.out.println("Departamento registrado.");
    }

    /**
     * Lista todos los departamentos registrados en el sistema
     * utilizando el controlador para obtener los datos.
     */
    private void listar(){
        System.out.println("\nDepartamentos registrados:");
        for (Departamento d : controller.obtenerDepartamentos()) {
            System.out.println(d);
        }
    }
}
