package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

/**
 * Menú principal del sistema HelpDesk U.
 * Este menú se muestra únicamente después de un inicio de sesión exitoso
 * y permite al usuario navegar hacia los distintos submenús del sistema.
 *
 * Desde aquí se accede a:
 * <ul>
 *     <li>Gestión de departamentos</li>
 *     <li>Gestión de tickets</li>
 *     <li>Gestión de diccionarios y palabras</li>
 * </ul>
 *
 * La clase forma parte de la capa de Interfaz de Usuario (UI) y delega
 * toda la lógica al {@link Controller}.
 *
 */
public class MenuPrincipal {

    private IO io = new IO();
    private Controller controller;

    private MenuDepartamento menuDepartamento;
    private MenuTicket       menuTicket;
    private MenuDiccionario  menuDiccionario;

    /**
     * Constructor del menú principal.
     *
     * @param controller instancia del controlador principal del sistema.
     */
    public MenuPrincipal(Controller controller) {
        this.controller = controller;
        this.menuDepartamento = new MenuDepartamento(controller);
        this.menuTicket       = new MenuTicket(controller);
        this.menuDiccionario  = new MenuDiccionario(controller);
    }

    /**
     * Inicia el menú principal y muestra sus opciones en un ciclo.
     * El menú se ejecuta hasta que el usuario selecciona la opción 0 (Salir).
     */
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
