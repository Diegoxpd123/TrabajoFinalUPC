package pe.upc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;



import pe.upc.business.ServicioBusiness;
import pe.upc.model.entity.Servicio;
import pe.upc.business.ClienteBusiness;
import pe.upc.model.entity.Cliente;
import pe.upc.business.AdministradorBusiness;
import pe.upc.model.entity.Administrador;

import pe.upc.util.Message;

@Named
@SessionScoped
public class ServicioController implements Serializable
{


    private static final long serialVersionUID = 1L;

@Inject
    private ServicioBusiness ServicioBusiness;

private Servicio servicio;
private List<Servicio> servicios;
private Servicio servicioSelect;

@Inject
private ClienteBusiness clienteBusiness;

private Cliente cliente;
private List<Cliente> clientes;
private Cliente clienteSelect;


private String filterName;



@Inject
private AdministradorBusiness administradorBusiness;

private Administrador administrador;
private List<Administrador> administradores;
private Administrador administradorSelect;

public Administrador getAdministradorSelect() {
	return administradorSelect;
}

public void setAdministradorSelect(Administrador administradorSelect) {
	this.administradorSelect = administradorSelect;
}

public AdministradorBusiness getAdministradorBusiness() {
	return administradorBusiness;
}

public void setAdministradorBusiness(AdministradorBusiness administradorBusiness) {
	this.administradorBusiness = administradorBusiness;
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

public void setAdministradores(List<Administrador> administradores) {
	this.administradores = administradores;
}

public ServicioBusiness getServicioBusiness() {
	return ServicioBusiness;
}

public void setServicios(List<Servicio> servicios) {
	this.servicios = servicios;
}

@PostConstruct
    public void init()
{
	administrador = new Administrador();
    administradores = new ArrayList<Administrador>();
	
	cliente = new Cliente();
    clientes = new ArrayList<Cliente>();
    servicio = new Servicio();
    servicios = new ArrayList<Servicio>();
    getAllServicios();
}

public void getAllServicios()
{
    try
    {
        servicios = ServicioBusiness.getAll();
    }
    catch (Exception e)
    {
        Message.messageError("Error :" + e.getMessage());
    }
}

public String newServicio()
{
	try {
		this.administradores = administradorBusiness.getAll();
		this.clientes = clienteBusiness.getAll();
    resetForm();
	}catch(Exception e) {
		
	}
    return "/servicio/registrar.xhtml";
}

public String listServicio()
{
    return "/servicio/listar.xhtml";
}
public String re()
{
    return "/servicio/registrar.xhtml";
}
public String saveServicio()
{
    String view = "";
    try
    {

        if (servicio.getId() != null)
        {
        	servicio.setAdministrador(administrador);
        	servicio.setCliente(cliente);
            ServicioBusiness.update(servicio);
            Message.messageInfo("Registro actualizado exitosamente");
        }
        else
        {
        	servicio.setAdministrador(administrador);
        	servicio.setCliente(cliente);
            ServicioBusiness.insert(servicio);
            Message.messageInfo("Registro guardado exitosamente");

        }
        this.getAllServicios();
        resetForm();
        view = "/servicio/listar.xhtml";
    }
    catch (Exception e)
    {
        Message.messageError("Error Product :" + e.getStackTrace());
    }

    return view;
}

public String sumar()
{
    String view = "";
    try
    {

        if (servicio.getId() != null)
        {
        	servicio.setAdministrador(administrador);
        	servicio.setCliente(cliente);
            ServicioBusiness.update(servicio);
            Message.messageInfo("Registro actualizado exitosamente");
        }
        else
        {
        	servicio.setAdministrador(administrador);
        	servicio.setCliente(cliente);
            ServicioBusiness.insert(servicio);
            Message.messageInfo("Registro guardado exitosamente");

        }
        this.getAllServicios();
        resetForm();
        view = "/servicio/listar.xhtml";
    }
    catch (Exception e)
    {
        Message.messageError("Error Product :" + e.getStackTrace());
    }

    return view;
}

public String editServicio()
{
    String view = "";
    try
    {
        if (this.servicioSelect != null)
        {
            this.servicio = servicioSelect;

            view = "/servicio/actualizar.xhtml";
        }
        else
        {
            Message.messageInfo("Debe seleccionar un Cliente");
        }
    }
    catch (Exception e)
    {
        Message.messageError("Error Cliente :" + e.getMessage());
    }

    return view;
}

public void searchServicioByName()
{
    try
    {

        servicios = ServicioBusiness.getServicioByName(this.filterName.trim());
        resetForm();
        if (servicios.isEmpty())
        {
            Message.messageInfo("No se encontraron servicios");

        }

    }
    catch (Exception e)
    {
        Message.messageError("Error Product Search :" + e.getMessage());
    }
}

public void SelectServicio(SelectEvent e)
{
    this.servicioSelect = (Servicio)e.getObject();
}



public Servicio getServicio()
{
    return servicio;
}

public void setServicio(Servicio Cliente)
{
    this.servicio = Cliente;
}

public List<Servicio> getServicios()
{
    return servicios;
}

public void setServicio(List<Servicio> Clientees)
{
    this.servicios = Clientees;
}

public Servicio getServicioSelect()
{
    return servicioSelect;
}

public void setServicioSelect(Servicio ClienteSelect)
{
    this.servicioSelect = ClienteSelect;
}

public static long getSerialversionuid()
{
    return serialVersionUID;
}

public String getFilterName()
{
    return filterName;
}

public void setFilterName(String filterName)
{
    this.filterName = filterName;
}

public ServicioBusiness getSerivicioBusiness() {
	return ServicioBusiness;
}

public void setServicioBusiness(ServicioBusiness ServicioBusiness) {
	this.ServicioBusiness = ServicioBusiness;
}

public ClienteBusiness getClienteBusiness() {
	return clienteBusiness;
}

public void setClienteBusiness(ClienteBusiness clienteBusiness) {
	this.clienteBusiness = clienteBusiness;
}
public Cliente getCliente()
{
    return cliente;
}

public void setCliente(Cliente Cliente)
{
    this.cliente = Cliente;
}

public List<Cliente> getClientes()
{
    return clientes;
}

public void setClientes(List<Cliente> Clientees)
{
    this.clientes = Clientees;
}

public Cliente getClienteSelect()
{
    return clienteSelect;
}

public void setClienteSelect(Cliente ClienteSelect)
{
    this.clienteSelect = ClienteSelect;
}

public void resetForm()
{
    this.filterName = "";
    this.servicio = new Servicio();
}

	

}