
package Beans;

import DAO.TesisDAO;
import Modelo.Tesis;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class TesisBean {

    private List<Tesis> listaTesis;
    private Tesis tesis;
    private TesisDAO tesisDAO;
    private String accion;

    @PostConstruct
    public void init() {
        tesisDAO = new TesisDAO();
        tesis = new Tesis();
        listaTesis = tesisDAO.buscarTodo();
        accion = "Registrar";
    }

    public void limpiarFormulario() {
        tesis = new Tesis();
        accion = "Registrar";
    }

    public void acctionFormulario() {
        if ("Registrar".equals(accion)) {
            insertar();
        } else {
            actualizar();
        }
    }

    public void editar(Tesis tesis) {
        this.tesis = tesis;
        accion = "Editar";
    }

    public void borrar(Tesis tesis) {
        if (tesisDAO.borrar(tesis)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Tesis eliminado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
        }
        listaTesis = tesisDAO.buscarTodo();
    }

    public void insertar() {
        if (tesisDAO.insertar(tesis)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Tesis registrado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo registrar"));
        }
        listaTesis = tesisDAO.buscarTodo();
        limpiarFormulario();
    }

    public void actualizar() {
        if (tesisDAO.actualizar(tesis)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Tesis actualizado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar"));
        }
        listaTesis = tesisDAO.buscarTodo();
        limpiarFormulario();
    }
    
    public TesisBean() {
    }

    public List<Tesis> getListaTesis() {
        return listaTesis;
    }

    public void setListaTesis(List<Tesis> listaTesis) {
        this.listaTesis = listaTesis;
    }

    public Tesis getTesis() {
        return tesis;
    }

    public void setTesis(Tesis tesis) {
        this.tesis = tesis;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
}
