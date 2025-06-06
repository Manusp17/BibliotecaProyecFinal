
package Beans;

import DAO.MaterialDAO;
import Modelo.Material;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@RequestScoped
public class MaterialBean {
    
    private List<Material> listaMaterial;
    private MaterialDAO materialDAO;
    private Material material;
    private String accion;

    @PostConstruct
    public void init() {
    materialDAO = new MaterialDAO();
    listaMaterial = materialDAO.buscarTodo();
    material = new Material();
    material.setIdMaterial(0);
    accion = "Registrar";
    
    }
    
    public void limpiarFormulario() {
        this.material = new Material();
        this.material.setIdMaterial(0);
        accion = "Registrar";
    }

    public void acctionFormulario() {
        if (accion == "Registrar") {
            insertar();
        } else {
            actualizar();
        }
    }

    public void editar(Material material) {
        this.material = material;
        accion = "Editar";
    }

    public void borrar(Material material) {
        boolean flag = materialDAO.borrar(material);
        if (flag) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", "Su material fué borrado exitosamente.");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Su material NO fué Borrado.");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
        listaMaterial = materialDAO.buscarTodo();
    }

    private void insertar() {
        boolean flag = materialDAO.insertar(material);
        if (flag) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", "Su material fué guardado exitosamente.");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Su material NO fué Guardado.");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
        listaMaterial = materialDAO.buscarTodo();
        limpiarFormulario();
    }

    private void actualizar() {
        boolean flag = materialDAO.actualizar(material);
        if (flag) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", "Su material fué Actualizado Correctamente.");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Su material NO fué Actualizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
        listaMaterial = materialDAO.buscarTodo();
        limpiarFormulario();
    }
   
    public MaterialBean() {
    }

    public List<Material> getListaMaterial() {
        return listaMaterial;
    }

    public void setListaMaterial(List<Material> listaMaterial) {
        this.listaMaterial = listaMaterial;
    }

    public MaterialDAO getMaterialDAO() {
        return materialDAO;
    }

    public void setMaterialDAO(MaterialDAO materialDAO) {
        this.materialDAO = materialDAO;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    
}
