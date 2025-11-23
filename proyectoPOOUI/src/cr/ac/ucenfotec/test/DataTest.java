package cr.ac.ucenfotec.test;

import cr.ac.ucenfotec.dl.Data;
import cr.ac.ucenfotec.bl.entities.*;

public class DataTest {

    public static void main(String[] args) {

        Data data = new Data();

        // Test 1
        Usuario u = new Usuario("Luis", "l@u.cr", "pass", "5555-5555", "Funcionario");
        data.getUsuarios().add(u);
        boolean testUsuarios = (data.getUsuarios().size() == 1);

        // Test 2
        Departamento d = new Departamento("TI", "Tecnología", "ti@u.cr");
        data.getDepartamentos().add(d);
        boolean testDeptos = (data.getDepartamentos().size() == 1);

        // Test 3
        Ticket t = new Ticket("PC dañada", "No enciende", "Nuevo", u, d);
        data.getTickets().add(t);
        boolean testTickets = (data.getTickets().size() == 1);

        if (testUsuarios && testDeptos && testTickets) {
            System.out.println("DataTest: OK");
        } else {
            System.out.println("DataTest: FALLÓ");
        }
    }
}
