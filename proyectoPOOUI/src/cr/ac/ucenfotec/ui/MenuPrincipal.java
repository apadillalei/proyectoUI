package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

public class MenuPrincipal {

    private IO io = new IO();
    private Controller controller;

    private MenuDepartamento menuDepartamento;
    private MenuTicket       menuTicket;
    private MenuDiccionario  menuDiccionario;

    public MenuPrincipal(Controller controller) {
        this.controller = controller;
        this.menuDepartamento = new MenuDepartamento(controller);
        this.menuTicket       = new MenuTicket(controller);
        this.menuDiccionario  = new MenuDiccionario(controller);
    }

    public void iniciar(){
        int op;
        do{
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1) Departamentos (registrar / listar)");
            System.out.println("2) Tickets (registrar / listar)");
            System.out.println("3) Diccionarios/Palabras (crear / agregar / listar)");
            System.out.println("0) Salir");
            op = io.i("Opción: ");

            switch (op){
                case 1 -> menuDepartamento.mostrar();
                case 2 -> menuTicket.mostrar();
                case 3 -> menuDiccionario.mostrar();
                case 0 -> System.out.println("¡Adiós!");
                default -> System.out.println("Opción inválida.");
            }
        } while(op != 0);
    }
}
