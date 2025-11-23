package cr.ac.ucenfotec.test;

import cr.ac.ucenfotec.bl.entities.Palabra;

public class PalabraTest {

    public static void main(String[] args) {
        Palabra p = new Palabra("dolor", "emocional");

        boolean ok = true;
        if (!"dolor".equals(p.getTexto())) ok = false;
        if (!"emocional".equals(p.getCategoria())) ok = false;

        if (ok) {
            System.out.println("PalabraTest: OK");
        } else {
            System.out.println("PalabraTest: FALLÓ");
        }
    }
}
