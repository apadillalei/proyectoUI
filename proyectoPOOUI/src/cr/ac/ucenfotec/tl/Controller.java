package cr.ac.ucenfotec.tl;

import cr.ac.ucenfotec.bl.entities.Departamento;
import cr.ac.ucenfotec.bl.entities.Diccionario;
import cr.ac.ucenfotec.bl.entities.Palabra;
import cr.ac.ucenfotec.bl.entities.Ticket;
import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.bl.logic.Gestor;

import java.util.List;

public class Controller {

    private Gestor gestor;
    private Usuario usuarioActual;

    public Controller() {
        this.gestor = new Gestor();
    }

    // ========== USUARIOS ==========

    public void registrarUsuario(String nombre, String correo, String pass, String tel, String rol) {
        Usuario u = new Usuario(nombre, correo, pass, tel, rol);
        gestor.guardarUsuario(u);
    }

    public List<Usuario> obtenerUsuarios() {
        return gestor.listarUsuarios();
    }

    public Usuario buscarUsuarioPorId(int id) {
        return gestor.buscarUsuarioPorId(id);
    }

    // ========== LOGIN ==========

    public boolean iniciarSesion(String correo, String pass) {
        Usuario u = gestor.login(correo, pass);
        if (u != null) {
            usuarioActual = u;
            return true;
        }
        return false;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    // ========== DEPARTAMENTOS ==========

    public void registrarDepartamento(String nombre, String desc, String contacto) {
        Departamento d = new Departamento(nombre, desc, contacto);
        gestor.guardarDepartamento(d);
    }

    public List<Departamento> obtenerDepartamentos() {
        return gestor.listarDepartamentos();
    }

    public Departamento buscarDepartamentoPorId(int id) {
        return gestor.buscarDepartamentoPorId(id);
    }

    // ========== DICCIONARIOS ==========

    public void crearDiccionario(String tipo) {
        Diccionario d = new Diccionario(tipo);
        gestor.guardarDiccionario(d);
    }

    public List<Diccionario> obtenerDiccionarios() {
        return gestor.listarDiccionarios();
    }

    public Diccionario buscarDiccionarioPorId(int id) {
        return gestor.buscarDiccionarioPorId(id);
    }

    public void agregarPalabraADiccionario(Diccionario dic, String texto, String categoria) {
        Palabra p = new Palabra(texto, categoria);
        gestor.agregarPalabraADiccionario(dic, p);
    }

    public List<Palabra> obtenerPalabrasDeDiccionario(Diccionario dic) {
        return gestor.listarPalabrasDeDiccionario(dic);
    }

    // ========== TICKETS ==========

    public void registrarTicket(String titulo, String desc, String estado, Usuario usuario, Departamento departamento) {
        Ticket t = new Ticket(titulo, desc, estado, usuario, departamento);
        gestor.guardarTicket(t);
    }

    public List<Ticket> obtenerTickets() {
        return gestor.listarTickets();
    }
}
