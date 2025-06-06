
package Beans;

import DAO.RevistaDAO;
import Modelo.Revista;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


@ManagedBean
@RequestScoped
public class RevistaBean {

    private List<Revista> listaRevista;
    private Revista revista;
    private RevistaDAO revistaDAO;
    private String accion;

    @PostConstruct
    public void init() {
        revistaDAO = new RevistaDAO();
        revista = new Revista();
        listaRevista = revistaDAO.buscarTodo();
        accion = "Registrar";
    }

    public void limpiarFormulario() {
        revista = new Revista();
        accion = "Registrar";
    }

    public void acctionFormulario() {
        if ("Registrar".equals(accion)) {
            insertar();
        } else {
            actualizar();
        }
    }

    public void editar(Revista revista) {
        this.revista = revista;
        accion = "Editar";
    }

    public void borrar(Revista revista) {
        if (revistaDAO.borrar(revista)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Revista eliminado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
        }
        listaRevista = revistaDAO.buscarTodo();
    }

    public void insertar() {
        if (revistaDAO.insertar(revista)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Revista registrado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo registrar"));
        }
        listaRevista = revistaDAO.buscarTodo();
        limpiarFormulario();
    }

    public void actualizar() {
        if (revistaDAO.actualizar(revista)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Revista actualizado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar"));
        }
        listaRevista = revistaDAO.buscarTodo();
        limpiarFormulario();
    }
    
    public RevistaBean() {
    }

    public List<Revista> getListaRevista() {
        return listaRevista;
    }

    public void setListaRevista(List<Revista> listaRevista) {
        this.listaRevista = listaRevista;
    }

    public Revista getRevista() {
        return revista;
    }

    public void setRevista(Revista revista) {
        this.revista = revista;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
}
