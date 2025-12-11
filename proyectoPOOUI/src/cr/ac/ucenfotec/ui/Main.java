package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;
import cr.ac.ucenfotec.ui.MenuAutenticacion;
import cr.ac.ucenfotec.ui.MenuPrincipal;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        MenuAutenticacion menuLogin = new MenuAutenticacion(controller);

        System.out.println("=== SISTEMA HELP DESK U ===");
        boolean logged = menuLogin.iniciar();

        if (logged) {
            MenuPrincipal menu = new MenuPrincipal(controller);
            menu.iniciar();
        }

        System.out.println("\nPrograma finalizado... ");
    }
}
