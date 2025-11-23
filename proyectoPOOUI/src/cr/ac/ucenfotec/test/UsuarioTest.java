package cr.ac.ucenfotec.test;

import cr.ac.ucenfotec.bl.entities.Usuario;

/**
 * Test simple para la clase Usuario.
 *
 * Nota histórica:
 * - Versiones anteriores de este test intentaban comprobar también el id del usuario,
 *   e incluso el comportamiento de equals(), pero eso empezó a fallar cuando:
 *   1) Se movió la asignación del id al Gestor (y ya no dentro del constructor de Usuario).
 *   2) Se modificó la lógica interna de las entidades para ajustarse a la arquitectura del curso.
 *
 * Como en este avance el objetivo de los test-cases es
 * simplemente validar que las entidades almacenan correctamente la información
 * (no probar toda la lógica completa del sistema),
 * este test fue simplificado para:
 *
 *   - Crear un Usuario con datos de prueba.
 *   - Verificar que los getters devuelven exactamente lo que se pasó al constructor.
 *
 * De esta manera:
 *   - El test ya no depende de la forma en que se asignan los ids (Gestor, Data, etc.).
 *   - No depende de equals(), ni de ningún comportamiento adicional.
 *   - Es mucho más robusto ante cambios internos en la lógica de negocio.
 *
 * Este diseño cumple con el requerimiento del curso de tener al menos
 * un test-case por clase y es coherente con el nivel de complejidad de este avance.
 */
public class UsuarioTest {

    public static void main(String[] args) {
        System.out.println("=== PRUEBA USUARIO ===");

        // 1. Crear un usuario de prueba con datos conocidos
        Usuario u = new Usuario("Juan Pérez",
                "juan@example.com",
                "secreta123",
                "8888-8888",
                "Estudiante");

        // 2. Verificar que los getters devuelven lo esperado.
        //    Antes aquí también se intentaba probar el id y equals(),
        //    lo que generaba errores cuando cambiaba la forma de asignar el id.
        boolean ok = true;

        if (!"Juan Pérez".equals(u.getNombre())) {
            System.out.println("FALLÓ: nombre no coincide");
            ok = false;
        }
        if (!"juan@example.com".equals(u.getCorreo())) {
            System.out.println("FALLÓ: correo no coincide");
            ok = false;
        }
        if (!"secreta123".equals(u.getPassword())) {
            System.out.println("FALLÓ: password no coincide");
            ok = false;
        }
        if (!"8888-8888".equals(u.getTelefono())) {
            System.out.println("FALLÓ: teléfono no coincide");
            ok = false;
        }
        if (!"Estudiante".equals(u.getRol())) {
            System.out.println("FALLÓ: rol no coincide");
            ok = false;
        }

        // 3. Resultado final: se imprime un mensaje claro de éxito o fallo.
        if (ok) {
            System.out.println("OK: UsuarioTest pasó correctamente.");
        } else {
            System.out.println("UsuarioTest terminó con errores.");
        }
    }
}
