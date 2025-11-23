package cr.ac.ucenfotec.test;

import cr.ac.ucenfotec.bl.entities.Usuario;

public class UsuarioTest {

    public static void main(String[] args) {
        Usuario u = new Usuario("Thomas", "thomas@gmail.com", "1234", "8888-8888", "Estudiante");

        boolean ok = true;
        if (!"Thomas".equals(u.getNombre())) ok = false;
        if (!"t@u.cr".equals(u.getCorreo())) ok = false;
        if (!"1234".equals(u.getPassword())) ok = false;
        if (!"8888-8888".equals(u.getTelefono())) ok = false;
        if (!"Estudiante".equals(u.getRol())) ok = false;

        if (ok) {
            System.out.println("UsuarioTest: OK");
        } else {
            System.out.println("UsuarioTest: FALLÓ");
        }
    }
}
