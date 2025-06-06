
package Beans;

import DAO.LibroDAO;
import Modelo.Libro;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;



@ManagedBean
@RequestScoped
public class LibroBean {

   private List<Libro> listaLibro;
    private Libro libro;
    private LibroDAO libroDAO;
    private String accion;

    @PostConstruct
    public void init() {
        libroDAO = new LibroDAO();
        libro = new Libro();
        listaLibro = libroDAO.buscarTodo();
        accion = "Registrar";
    }

    public void limpiarFormulario() {
        libro = new Libro();
        accion = "Registrar";
    }

    public void acctionFormulario() {
        if ("Registrar".equals(accion)) {
            insertar();
        } else {
            actualizar();
        }
    }

    public void editar(Libro libro) {
        this.libro = libro;
        accion = "Editar";
    }

    public void borrar(Libro libro) {
        if (libroDAO.borrar(libro)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Libro eliminado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
        }
        listaLibro = libroDAO.buscarTodo();
    }

    public void insertar() {
        if (libroDAO.insertar(libro)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Libro registrado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo registrar"));
        }
        listaLibro = libroDAO.buscarTodo();
        limpiarFormulario();
    }

    public void actualizar() {
        if (libroDAO.actualizar(libro)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Libro actualizado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar"));
        }
        listaLibro = libroDAO.buscarTodo();
        limpiarFormulario();
    }
    
    public LibroBean() {
    }

    public List<Libro> getListaLibro() {
        return listaLibro;
    }

    public void setListaLibro(List<Libro> listaLibro) {
        this.listaLibro = listaLibro;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    
}
