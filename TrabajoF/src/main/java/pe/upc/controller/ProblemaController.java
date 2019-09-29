package pe.upc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import pe.upc.business.ProblemaBusiness;
import pe.upc.model.entity.Problema;
import pe.upc.business.ServicioBusiness;
import pe.upc.model.entity.Servicio;
import pe.upc.business.ProductoBusiness;
import pe.upc.model.entity.Producto;
import pe.upc.util.Message;

@Named
@SessionScoped
public class ProblemaController implements Serializable
{


    private static final long serialVersionUID = 1L;

@Inject
    private ProblemaBusiness ProblemaBusiness;

private Problema problema;
private List<Problema> problemas;
private Problema problemaSelect;

private String filterName;

@Inject
private ServicioBusiness ServicioBusiness;

private Servicio servicio;
private List<Servicio> servicios;
private Servicio servicioSelect;


@Inject
private ProductoBusiness ProductoBusiness;

private Producto producto;
private List<Producto> productos;
private Producto productoSelect;

@PostConstruct
    public void init()
{	servicio = new Servicio();
servicios = new ArrayList<Servicio>();

producto = new Producto();
productos = new ArrayList<Producto>();
    problema = new Problema();
    problemas = new ArrayList<Problema>();
    getAllProblemas();
}

public void getAllProblemas()
{
    try
    {
        problemas = ProblemaBusiness.getAll();
    }
    catch (Exception e)
    {
        Message.messageError("Error :" + e.getMessage());
    }
}

public ProblemaBusiness getProblemaBusiness() {
	return ProblemaBusiness;
}

public void setProblemaBusiness(ProblemaBusiness problemaBusiness) {
	ProblemaBusiness = problemaBusiness;
}

public ServicioBusiness getServicioBusiness() {
	return ServicioBusiness;
}

public void setServicioBusiness(ServicioBusiness servicioBusiness) {
	ServicioBusiness = servicioBusiness;
}

public Servicio getServicio() {
	return servicio;
}

public void setServicio(Servicio servicio) {
	this.servicio = servicio;
}

public List<Servicio> getServicios() {
	return servicios;
}

public void setServicios(List<Servicio> servicios) {
	this.servicios = servicios;
}

public Servicio getServicioSelect() {
	return servicioSelect;
}

public void setServicioSelect(Servicio servicioSelect) {
	this.servicioSelect = servicioSelect;
}

public ProductoBusiness getProductoBusiness() {
	return ProductoBusiness;
}

public void setProductoBusiness(ProductoBusiness productoBusiness) {
	ProductoBusiness = productoBusiness;
}

public Producto getProducto() {
	return producto;
}

public void setProducto(Producto producto) {
	this.producto = producto;
}

public List<Producto> getProductos() {
	return productos;
}

public void setProductos(List<Producto> productos) {
	this.productos = productos;
}

public Producto getProductoSelect() {
	return productoSelect;
}

public void setProductoSelect(Producto productoSelect) {
	this.productoSelect = productoSelect;
}

public void setProblemas(List<Problema> problemas) {
	this.problemas = problemas;
}

public String newProblema()
{
	
	try {
		this.servicios = ServicioBusiness.getAll();
		this.productos = ProductoBusiness.getAll();
    resetForm();
	}catch(Exception e) {
		
	}
    return "/problema/registrar.xhtml";
}

public String listProblema()
{
    return "/problema/listar.xhtml";
}
public String r()
{
    return "/problema/registrar.xhtml";
}
public String saveProblema()
{
    String view = "";
    try
    {

        if (problema.getId() != null)
        {
        	problema.setProducto(producto);
        	problema.setServicio(servicio);
            ProblemaBusiness.update(problema);
            Message.messageInfo("Registro actualizado exitosamente");
        }
        else
        {
           	problema.setProducto(producto);
        	problema.setServicio(servicio);
            ProblemaBusiness.insert(problema);
            Message.messageInfo("Registro guardado exitosamente");

        }
        this.getAllProblemas();
        resetForm();
        view = "/problema/listar.xhtml";
    }
    catch (Exception e)
    {
        Message.messageError("Error Product :" + e.getStackTrace());
    }

    return view;
}

public String editProblema()
{
    String view = "";
    try
    {
        if (this.problemaSelect != null)
        {
            this.problema = problemaSelect;

            view = "/problema/actualizar.xhtml";
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

public void searchProblemaByName()
{
    try
    {

        problemas = ProblemaBusiness.getProblemaByName(this.filterName.trim());
        resetForm();
        if (problemas.isEmpty())
        {
            Message.messageInfo("No se encontraron productos");

        }

    }
    catch (Exception e)
    {
        Message.messageError("Error Problema Search :" + e.getMessage());
    }
}

public void SelectProblema(SelectEvent e)
{
    this.problemaSelect = (Problema)e.getObject();
}



public Problema getProblema()
{
    return problema;
}

public void setProblema(Problema Cliente)
{
    this.problema = Cliente;
}

public List<Problema> getProblemas()
{
    return problemas;
}

public void setProblema(List<Problema> Clientees)
{
    this.problemas = Clientees;
}

public Problema getProblemaSelect()
{
    return problemaSelect;
}

public void setProblemaSelect(Problema ClienteSelect)
{
    this.problemaSelect = ClienteSelect;
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




public void resetForm()
{
    this.filterName = "";
    this.problema = new Problema();
}

	

}