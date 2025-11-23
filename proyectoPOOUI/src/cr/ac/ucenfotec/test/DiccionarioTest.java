package cr.ac.ucenfotec.test;

import cr.ac.ucenfotec.bl.entities.Diccionario;
import cr.ac.ucenfotec.bl.entities.Palabra;

import java.util.List;

public class DiccionarioTest {

    public static void main(String[] args) {
        Diccionario dic = new Diccionario("emocional");

        Palabra p = new Palabra("frustrado", "negativo");

        dic.getPalabras().add(p);

        List<Palabra> palabras = dic.getPalabras();

        boolean ok = true;

        if (palabras.size() != 1) {
            ok = false;
        } else {
            Palabra primera = palabras.get(0);
            if (!"frustrado".equals(primera.getTexto())) ok = false;
            if (!"negativo".equals(primera.getCategoria())) ok = false;
        }

        if (ok) {
            System.out.println("DiccionarioTest: OK");
        } else {
            System.out.println("DiccionarioTest: FALLÓ");
        }
    }
}
