
package Beans;

import DAO.CDDAO;
import Modelo.CD;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class CDBean {
    
    private List<CD> listaCD;
    private CD cd;
    private CDDAO cdDAO;
    private String accion;

    @PostConstruct
    public void init() {
        cdDAO = new CDDAO();
        cd = new CD();
        listaCD = cdDAO.buscarTodo();
        accion = "Registrar";
    }

    public void limpiarFormulario() {
        cd = new CD();
        accion = "Registrar";
    }

    public void acctionFormulario() {
        if ("Registrar".equals(accion)) {
            insertar();
        } else {
            actualizar();
        }
    }

    public void editar(CD cd) {
        this.cd = cd;
        accion = "Editar";
    }

    public void borrar(CD cd) {
        if (cdDAO.borrar(cd)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "CD eliminado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
        }
        listaCD = cdDAO.buscarTodo();
    }

    public void insertar() {
        if (cdDAO.insertar(cd)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "CD registrado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo registrar"));
        }
        listaCD = cdDAO.buscarTodo();
        limpiarFormulario();
    }

    public void actualizar() {
        if (cdDAO.actualizar(cd)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "CD actualizado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar"));
        }
        listaCD = cdDAO.buscarTodo();
        limpiarFormulario();
    }
    
    public CDBean() {
    }

    public List<CD> getListaCD() {
        return listaCD;
    }

    public void setListaCD(List<CD> listaCD) {
        this.listaCD = listaCD;
    }

    public CD getCd() {
        return cd;
    }

    public void setCd(CD cd) {
        this.cd = cd;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
}
