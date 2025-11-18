package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tl.Controller;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();

        MenuAutenticacion menuAutenticacion = new MenuAutenticacion(controller);
        boolean logged = menuAutenticacion.iniciar();

        if (logged) {
            MenuPrincipal menuPrincipal = new MenuPrincipal(controller);
            menuPrincipal.iniciar();
        }
    }
}
