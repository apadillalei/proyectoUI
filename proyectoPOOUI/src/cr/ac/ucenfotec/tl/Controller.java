package cr.ac.ucenfotec.tl;

import cr.ac.ucenfotec.bl.entities.*;
import cr.ac.ucenfotec.bl.logic.Gestor;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private final Gestor gestor;
    private Usuario usuarioActual;

    public Controller() {
        this.gestor = new Gestor();
    }

    // =========================================================
    // USUARIOS
    // =========================================================

    public boolean registrarUsuario(String nombre, String correo, String pass, String tel, String rol) {
        return gestor.registrarUsuario(nombre, correo, pass, tel, rol);
    }

    public List<Usuario> obtenerUsuarios() {
        return gestor.listarUsuarios();
    }

    public List<String> obtenerUsuariosComoTexto() {
        List<String> out = new ArrayList<>();
        for (Usuario u : obtenerUsuarios()) {
            out.add(u.toString());
        }
        return out;
    }

    public boolean hayUsuarios() {
        return !obtenerUsuarios().isEmpty();
    }

    public boolean existeUsuario(int id) {
        return gestor.buscarUsuarioPorId(id) != null;
    }

    public Usuario buscarUsuarioPorId(int id) {
        return gestor.buscarUsuarioPorId(id);
    }

    public boolean actualizarUsuario(int id,
                                     String nombre,
                                     String correo,
                                     String pass,
                                     String tel,
                                     String rol) {
        return gestor.actualizarUsuario(id, nombre, correo, pass, tel, rol);
    }

    public boolean eliminarUsuario(int id) {
        return gestor.eliminarUsuario(id);
    }

    // ================= LOGIN =================

    public boolean iniciarSesion(String correo, String pass) {
        Usuario u = gestor.login(correo, pass);
        if (u != null) {
            usuarioActual = u;
            return true;
        }
        return false;
    }

    public String getNombreUsuarioActual() {
        return (usuarioActual == null) ? null : usuarioActual.getNombre();
    }

    // =========================================================
    // DEPARTAMENTOS
    // =========================================================

    public void registrarDepartamento(String nombre, String desc, String contacto) {
        gestor.registrarDepartamento(nombre, desc, contacto);
    }

    public List<Departamento> obtenerDepartamentos() {
        return gestor.listarDepartamentos();
    }

    public List<String> obtenerDepartamentosComoTexto() {
        List<String> out = new ArrayList<>();
        for (Departamento d : obtenerDepartamentos()) {
            out.add(d.toString());
        }
        return out;
    }

    public boolean hayDepartamentos() {
        return !obtenerDepartamentos().isEmpty();
    }

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

    public void registrarTicket(String titulo, String desc, String estado, int idUsuario, int idDepartamento) {
        gestor.registrarTicket(titulo, desc, estado, idUsuario, idDepartamento);
    }

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
    // DICCIONARIOS
    // =========================================================

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
    // ANÁLISIS BoW
    // =========================================================

    public String[] analizarDescripcionTicket(String descripcion) {
        return gestor.analizarDescripcionTicket(descripcion);
    }

    public String[] analizarDescripcionTicketDetallado(String descripcion) {
        return gestor.analizarDescripcionTicketDetallado(descripcion);
    }
}
