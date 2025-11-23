package cr.ac.ucenfotec.test;

import cr.ac.ucenfotec.bl.entities.*;

public class TicketTest {

    public static void main(String[] args) {
        Usuario u = new Usuario("Ana", "ana@gmail.cr", "123", "2222-2222", "Estudiante");
        Departamento d = new Departamento("Registro", "Gestión académica", "reg@u.cr");

        Ticket t = new Ticket("Error matrícula", "No carga el sistema", "Nuevo", u, d);

        boolean ok = true;
        if (!"Error matrícula".equals(t.getAsunto())) ok = false;
        if (!"Nuevo".equals(t.getEstado())) ok = false;
        if (t.getUsuario() != u) ok = false;
        if (t.getDepartamento() != d) ok = false;

        if (ok) {
            System.out.println("TicketTest: OK");
        } else {
            System.out.println("TicketTest: FALLÓ");
        }
    }
}
