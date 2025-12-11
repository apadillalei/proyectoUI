package cr.ac.ucenfotec.tl;

import cr.ac.ucenfotec.bl.entities.*;
import cr.ac.ucenfotec.bl.logic.Gestor;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador principal del sistema HelpDesk U.
 * <p>
 * Se ubica en la capa de Lógica de Aplicación (TL – Translation Layer).
 * Su función es coordinar la comunicación entre la Interfaz de Usuario (UI)
 * y la capa de lógica de negocio {@link Gestor}.
 * </p>
 *
 * El Controller:
 * <ul>
 *     <li>Orquesta las operaciones de usuarios, departamentos, tickets y diccionarios.</li>
 *     <li>Mantiene el estado de sesión del usuario actual.</li>
 *     <li>Proporciona métodos simplificados para la UI.</li>
 * </ul>
 */
public class Controller {

    /** Gestor que ejecuta la lógica de negocio. */
    private final Gestor gestor;

    /** Usuario actualmente autenticado en el sistema. */
    private Usuario usuarioActual;

    /**
     * Construye el controlador principal instanciando el {@link Gestor}.
     */
    public Controller() {
        this.gestor = new Gestor();
    }

    // =========================================================
    // USUARIOS
    // =========================================================

    /**
     * Registra un nuevo usuario.
     *
     * @param nombre nombre completo
     * @param correo correo electrónico
     * @param pass   contraseña
     * @param tel    teléfono
     * @param rol    rol dentro del sistema
     * @return true si se registró exitosamente, false si el correo ya existe
     */
    public boolean registrarUsuario(String nombre, String correo, String pass, String tel, String rol) {
        return gestor.registrarUsuario(nombre, correo, pass, tel, rol);
    }

    /**
     * @return lista completa de usuarios del sistema.
     */
    public List<Usuario> obtenerUsuarios() {
        return gestor.listarUsuarios();
    }

    /**
     * Convierte los usuarios a texto para mostrarlos en consola.
     *
     * @return lista de representaciones textuales de usuarios
     */
    public List<String> obtenerUsuariosComoTexto() {
        List<String> out = new ArrayList<>();
        for (Usuario u : obtenerUsuarios()) {
            out.add(u.toString());
        }
        return out;
    }

    /**
     * @return true si existen usuarios registrados.
     */
    public boolean hayUsuarios() {
        return !obtenerUsuarios().isEmpty();
    }

    /**
     * Verifica si un usuario existe por su ID.
     *
     * @param id identificador del usuario
     * @return true si existe, false si no
     */
    public boolean existeUsuario(int id) {
        return gestor.buscarUsuarioPorId(id) != null;
    }

    /**
     * Obtiene un usuario por id.
     */
    public Usuario buscarUsuarioPorId(int id) {
        return gestor.buscarUsuarioPorId(id);
    }

    /**
     * Actualiza la información de un usuario existente.
     */
    public boolean actualizarUsuario(int id,
                                     String nombre,
                                     String correo,
                                     String pass,
                                     String tel,
                                     String rol) {
        return gestor.actualizarUsuario(id, nombre, correo, pass, tel, rol);
    }

    /**
     * Elimina un usuario del sistema.
     */
    public boolean eliminarUsuario(int id) {
        return gestor.eliminarUsuario(id);
    }

    // =========================================================
    // AUTENTICACIÓN
    // =========================================================

    /**
     * Intenta iniciar sesión con las credenciales proporcionadas.
     *
     * @param correo correo ingresado
     * @param pass   contraseña ingresada
     * @return true si el login fue exitoso
     */
    public boolean iniciarSesion(String correo, String pass) {
        Usuario u = gestor.login(correo, pass);
        if (u != null) {
            usuarioActual = u;
            return true;
        }
        return false;
    }

    /**
     * @return nombre del usuario autenticado o null si no hay sesión activa.
     */
    public String getNombreUsuarioActual() {
        return (usuarioActual == null) ? null : usuarioActual.getNombre();
    }

    // =========================================================
    // DEPARTAMENTOS
    // =========================================================

    /**
     * Registra un nuevo departamento.
     */
    public void registrarDepartamento(String nombre, String desc, String contacto) {
        gestor.registrarDepartamento(nombre, desc, contacto);
    }

    /**
     * @return lista de departamentos.
     */
    public List<Departamento> obtenerDepartamentos() {
        return gestor.listarDepartamentos();
    }

    /**
     * Convierte los departamentos a texto para impresión.
     */
    public List<String> obtenerDepartamentosComoTexto() {
        List<String> out = new ArrayList<>();
        for (Departamento d : obtenerDepartamentos()) {
            out.add(d.toString());
        }
        return out;
    }

    /**
     * @return true si existen departamentos registrados.
     */
    public boolean hayDepartamentos() {
        return !obtenerDepartamentos().isEmpty();
    }

    /**
     * Verifica si un departamento existe por su ID.
     */
    public boolean existeDepartamento(int id) {
        return gestor.buscarDepartamentoPorId(id) != null;
    }

    public Departamento buscarDepartamentoPorId(int id) {
        return gestor.buscarDepartamentoPorId(id);
    }

    public boolean actualizarDepartamento(int id,
                                          String nombre,
                                          String desc,
                                          String contacto) {
        return gestor.actualizarDepartamento(id, nombre, desc, contacto);
    }

    public boolean eliminarDepartamento(int id) {
        return gestor.eliminarDepartamento(id);
    }

    // =========================================================
    // TICKETS
    // =========================================================

    /**
     * Registra un ticket con la información proporcionada.
     */
    public void registrarTicket(String titulo, String desc, String estado, int idUsuario, int idDepartamento) {
        gestor.registrarTicket(titulo, desc, estado, idUsuario, idDepartamento);
    }

    /**
     * @return lista de tickets registrados.
     */
    public List<Ticket> obtenerTickets() {
        return gestor.listarTickets();
    }

    public List<String> obtenerTicketsComoTexto() {
        List<String> out = new ArrayList<>();
        for (Ticket t : obtenerTickets()) {
            out.add(t.toString());
        }
        return out;
    }

    // =========================================================
    // DICCIONARIOS Y PALABRAS
    // =========================================================

    /**
     * Crea un nuevo diccionario BoW (emocional o técnico).
     */
    public void crearDiccionario(String tipo) {
        gestor.registrarDiccionario(tipo);
    }

    public List<Diccionario> obtenerDiccionarios() {
        return gestor.listarDiccionarios();
    }

    public List<String> obtenerDiccionariosComoTexto() {
        List<String> out = new ArrayList<>();
        for (Diccionario d : obtenerDiccionarios()) {
            out.add(d.toString());
        }
        return out;
    }

    public boolean hayDiccionarios() {
        return !obtenerDiccionarios().isEmpty();
    }

    public boolean existeDiccionario(int id) {
        return obtenerDiccionarios().stream().anyMatch(d -> d.getId() == id);
    }

    public Diccionario buscarDiccionarioPorId(int id) {
        return obtenerDiccionarios().stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean actualizarDiccionario(int idDiccionario, String nuevoTipo) {
        return gestor.actualizarDiccionario(idDiccionario, nuevoTipo);
    }

    public boolean eliminarDiccionario(int idDiccionario) {
        return gestor.eliminarDiccionario(idDiccionario);
    }

    public boolean agregarPalabraADiccionario(int idDiccionario, String texto, String categoria) {
        return gestor.agregarPalabraADiccionario(idDiccionario, texto, categoria);
    }

    public List<Palabra> obtenerPalabrasDeDiccionario(int idDiccionario) {
        return gestor.listarPalabrasDeDiccionario(idDiccionario);
    }

    public List<String> obtenerPalabrasDeDiccionarioComoTexto(int idDiccionario) {
        List<String> out = new ArrayList<>();
        List<Palabra> palabras = obtenerPalabrasDeDiccionario(idDiccionario);
        if (palabras != null) {
            for (Palabra p : palabras) {
                out.add(p.toString());
            }
        }
        return out;
    }

    public boolean actualizarPalabraEnDiccionario(int idDiccionario,
                                                  String textoOriginal,
                                                  String nuevoTexto,
                                                  String nuevaCategoria) {
        return gestor.actualizarPalabraEnDiccionario(idDiccionario, textoOriginal, nuevoTexto, nuevaCategoria);
    }

    public boolean eliminarPalabraDeDiccionario(int idDiccionario, String texto) {
        return gestor.eliminarPalabraDeDiccionario(idDiccionario, texto);
    }

    // =========================================================
    // ANÁLISIS BAG OF WORDS
    // =========================================================

    /**
     * Ejecuta un análisis rápido de BoW sobre la descripción del ticket.
     *
     * @return arreglo:
     *      [0] estado emocional detectado,
     *      [1] categoría técnica sugerida
     */
    public String[] analizarDescripcionTicket(String descripcion) {
        return gestor.analizarDescripcionTicket(descripcion);
    }

    /**
     * Ejecuta un análisis detallado de BoW.
     *
     * @return arreglo:
     *      [0] estado emocional,
     *      [1] categoría técnica,
     *      [2] vector TF como texto,
     *      [3] palabras detectadas
     */
    public String[] analizarDescripcionTicketDetallado(String descripcion) {
        return gestor.analizarDescripcionTicketDetallado(descripcion);
    }
}
