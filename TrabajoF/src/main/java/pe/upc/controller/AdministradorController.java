package pe.upc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

import pe.upc.business.AdministradorBusiness;
import pe.upc.model.entity.Administrador;

import pe.upc.util.Message;

@Named
@SessionScoped
public class AdministradorController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AdministradorBusiness administradorBusiness;

	private Administrador administrador;
	private List<Administrador> administradores;
	private Administrador administradorSelect;

	private String filterName;

	@PostConstruct
	public void init() {
		administrador = new Administrador();
		administradores = new ArrayList<Administrador>();
		getAllAdministradores();
	}
	
	
	

	public void getAllAdministradores() {
		try {
			administradores = administradorBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error :" + e.getMessage());
		}
	}

	public String newAdministrador() {
		resetForm();
		return "/administrador/registrar.xhtml";
	}

	public String listAdministrador() {
		return "/administrador/listar.xhtml";
	}

	public String saveAdministrador() {
		String view = "";
		try {
			 
			if (administrador.getId() != null) {
				administradorBusiness.update(administrador);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				administradorBusiness.insert(administrador);
				Message.messageInfo("Registro guardado exitosamente");
		      
			}
			this.getAllAdministradores();
			resetForm();
			view = "/administrador/listar.xhtml";
			    
		} catch (Exception e) {
			Message.messageError("Error Product :" + e.getStackTrace());
		}

		return view;
	}
	
	public String deleteAdministrador() {
		String view = "";
		try {
			if (this.administradorSelect != null){
				administradorBusiness.delete(administradorSelect);
				Message.messageInfo("Registro eliminado exitosamente");
			} else {
				Message.messageInfo("Debe seleccionar un Administrador");
			}
			this.getAllAdministradores();
			resetForm();
			view = "/administrador/listar.xhtml";
		} catch (Exception e) {
			Message.messageError("Error Administrador :" + e.getMessage());
		}
		
		return view;
	}
	
	public String editAdministrador() {
		String view = "";
		try {
			if (this.administradorSelect != null) {
				this.administrador = administradorSelect;

				view = "/administrador/actualizar.xhtml";
			} else {
				Message.messageInfo("Debe seleccionar un Administrador");
			}
		} catch (Exception e) {
			Message.messageError("Error Administrador :" + e.getMessage());
		}

		return view;
	}

	public void searchAdministradorByName() {
		try {

			administradores = administradorBusiness.getAdministradorByName(this.filterName.trim());
			resetForm();
			if (administradores.isEmpty()) {
				Message.messageInfo("No se encontraron productos");

			}

		} catch (Exception e) {
			Message.messageError("Error Product Search :" + e.getMessage());
		}
	}

	public void SelectAdministrador(SelectEvent e) {
		this.administradorSelect = (Administrador) e.getObject();
	}



	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public List<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdmi(List<Administrador> administradores) {
		this.administradores = administradores;
	}

	public Administrador getAdministradorSelect() {
		return administradorSelect;
	}

	public void setAdministradorSelect(Administrador administradorSelect) {
		this.administradorSelect = administradorSelect;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}


	

	public void resetForm() {
		this.filterName="";
		this.administrador = new Administrador();
	}

	

}