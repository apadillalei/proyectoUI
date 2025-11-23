package cr.ac.ucenfotec.test;

import cr.ac.ucenfotec.bl.entities.Departamento;

public class DepartamentoTest {

    public static void main(String[] args) {
        Departamento d = new Departamento("Informática", "Soporte técnico", "soporte@u.com");

        boolean ok = true;
        if (!"Informática".equals(d.getNombre())) ok = false;
        if (!"Soporte técnico".equals(d.getDescripcion())) ok = false;
        if (!"soporte@u.cr".equals(d.getCorreoContacto())) ok = false;

        if (ok) {
            System.out.println("DepartamentoTest: OK");
        } else {
            System.out.println("DepartamentoTest: FALLÓ");
        }
    }
}
