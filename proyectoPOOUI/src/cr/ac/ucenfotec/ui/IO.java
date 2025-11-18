package cr.ac.ucenfotec.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IO {

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public String str(String prompt) {
        System.out.print(prompt);
        try {
            return in.readLine().trim();
        } catch (IOException e) {
            System.out.println("Error leyendo texto.");
            return "";
        }
    }

    public int i(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(in.readLine().trim());
        } catch (Exception e) {
            System.out.println("Valor inválido, ingrese un número.");
            return -1;
        }
    }
}
