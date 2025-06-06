
package Beans;

import DAO.UsuarioDAO;
import Modelo.Usuario;
import Modelo.Usuario.EstadoCuenta;
import Modelo.Usuario.Rol;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.RequestScoped;


@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{

    private Usuario usuario = new Usuario();
    private List<Usuario> listaUsuarios;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @PostConstruct
    public void init() {
        listar();
    }

    public void insertar() {
        if (usuarioDAO.insertar(usuario)) {
            limpiar();
            listar();
        }
    }

    public void actualizar() {
        if (usuarioDAO.actualizar(usuario)) {
            limpiar();
            listar();
        }
    }

    public void eliminar(Usuario u) {
        if (usuarioDAO.borrar(u)) {
            listar();
        }
    }

    public void listar() {
        listaUsuarios = usuarioDAO.buscarTodo();
    }

    public void seleccionar(Usuario u) {
        this.usuario = u;
    }

    public void limpiar() {
        usuario = new Usuario();
    }

    // Getters y Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Rol[] getRoles() {
        return Rol.values();
    }

    public EstadoCuenta[] getEstados() {
        return EstadoCuenta.values();
    }
    
}
