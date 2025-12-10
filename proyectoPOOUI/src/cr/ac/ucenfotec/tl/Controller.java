package cr.ac.ucenfotec.tl;

import cr.ac.ucenfotec.bl.logic.Gestor;

import java.util.List;

/**
 * Controlador principal de la capa de UI.
 * Actúa como puente entre los menús y la lógica de negocio (Gestor).
 *
 * La UI NUNCA maneja objetos de negocio directamente. Únicamente
 * utiliza tipos simples (String, int, List<String>) y delega toda la
 * lógica y acceso a entidades al Gestor.
 */
public class Controller {

    private final Gestor gestor;
    /** Nombre del usuario autenticado actualmente (no exponemos el objeto Usuario). */
    private String nombreUsuarioActual;

    public Controller() {
        this.gestor = new Gestor();
    }

    // =========================================================
    // USUARIOS
    // =========================================================

    public void registrarUsuario(String nombre,
                                 String correo,
                                 String pass,
                                 String tel,
                                 String rol) {
        gestor.registrarUsuario(nombre, correo, pass, tel, rol);
    }

    /** Indica si existe al menos un usuario registrado. */
    public boolean hayUsuarios() {
        return !gestor.listarUsuarios().isEmpty();
    }

    /** Verifica si existe un usuario con el id indicado. */
    public boolean existeUsuario(int id) {
        return gestor.buscarUsuarioPorId(id) != null;
    }

    /**
     * Intenta iniciar sesión y guarda internamente el nombre del usuario autenticado.
     *
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean iniciarSesion(String correo, String pass) {
        String nombre = gestor.iniciarSesion(correo, pass);
        if (nombre != null) {
            this.nombreUsuarioActual = nombre;
            return true;
        }
        return false;
    }

    /** Devuelve el nombre del usuario autenticado actualmente. */
    public String getNombreUsuarioActual() {
        return nombreUsuarioActual;
    }

    /**
     * Devuelve los usuarios en formato de texto para mostrar en la UI.
     * Ej: "[1] Juan Pérez - juan@correo.com"
     */
    public List<String> obtenerUsuariosComoTexto() {
        return gestor.listarUsuariosComoTexto();
    }

    // =========================================================
    // DEPARTAMENTOS
    // =========================================================

    public void registrarDepartamento(String nombre,
                                      String desc,
                                      String contacto) {
        gestor.registrarDepartamento(nombre, desc, contacto);
    }

    public boolean hayDepartamentos() {
        return !gestor.listarDepartamentos().isEmpty();
    }

    public boolean existeDepartamento(int id) {
        return gestor.buscarDepartamentoPorId(id) != null;
    }

    /** Devuelve los departamentos en formato texto para mostrarlos en la UI. */
    public List<String> obtenerDepartamentosComoTexto() {
        return gestor.listarDepartamentosComoTexto();
    }

    // =========================================================
    // TICKETS
    // =========================================================

    /**
     * Registra un ticket usando únicamente IDs de usuario y departamento.
     * La búsqueda de las entidades se hace dentro del Gestor.
     */
    public void registrarTicket(String titulo,
                                String desc,
                                String estado,
                                int idUsuario,
                                int idDepartamento) {
        gestor.registrarTicket(titulo, desc, estado, idUsuario, idDepartamento);
    }

    /** Devuelve los tickets en formato texto para la UI. */
    public List<String> obtenerTicketsComoTexto() {
        return gestor.listarTicketsComoTexto();
    }

    // =========================================================
    // DICCIONARIOS Y PALABRAS
    // =========================================================

    public void crearDiccionario(String tipo) {
        gestor.registrarDiccionario(tipo);
    }

    public boolean hayDiccionarios() {
        return !gestor.listarDiccionarios().isEmpty();
    }

    public boolean existeDiccionario(int id) {
        return gestor.buscarDiccionarioPorId(id) != null;
    }

    /** Devuelve los diccionarios en formato texto. */
    public List<String> obtenerDiccionariosComoTexto() {
        return gestor.listarDiccionariosComoTexto();
    }

    /**
     * Agrega una palabra a un diccionario identificado por su id.
     */
    public void agregarPalabraADiccionario(int idDiccionario,
                                           String texto,
                                           String categoria) {
        gestor.agregarPalabraADiccionario(idDiccionario, texto, categoria);
    }

    /**
     * Devuelve las palabras de un diccionario en formato texto.
     */
    public List<String> obtenerPalabrasDeDiccionarioComoTexto(int idDiccionario) {
        return gestor.listarPalabrasDeDiccionarioComoTexto(idDiccionario);
    }
}
