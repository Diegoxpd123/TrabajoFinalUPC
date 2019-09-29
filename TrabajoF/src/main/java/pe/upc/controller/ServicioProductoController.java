package pe.upc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;



import pe.upc.business.ServicioProductoBusiness;
import pe.upc.model.entity.ServicioProducto;
import pe.upc.business.ServicioBusiness;
import pe.upc.model.entity.Servicio;
import pe.upc.business.ProductoBusiness;
import pe.upc.model.entity.Producto;

import pe.upc.util.Message;

@Named
@SessionScoped
public class ServicioProductoController implements Serializable
{


    private static final long serialVersionUID = 1L;

@Inject
    private ServicioProductoBusiness ServicioproductoBusiness;

private ServicioProducto servicioproducto;
private List<ServicioProducto> servicioproductos;
private ServicioProducto servicioproductoSelect;




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


public ServicioProductoBusiness getServicioproductoBusiness() {
	return ServicioproductoBusiness;
}

public void setServicioproductoBusiness(ServicioProductoBusiness servicioproductoBusiness) {
	ServicioproductoBusiness = servicioproductoBusiness;
}

public ServicioProducto getServicioproducto() {
	return servicioproducto;
}

public void setServicioproducto(ServicioProducto servicioproducto) {
	this.servicioproducto = servicioproducto;
}

public List<ServicioProducto> getServicioproductos() {
	return servicioproductos;
}

public void setServicioproductos(List<ServicioProducto> servicioproductos) {
	this.servicioproductos = servicioproductos;
}

public ServicioProducto getServicioproductoSelect() {
	return servicioproductoSelect;
}

public void setServicioproductoSelect(ServicioProducto servicioproductoSelect) {
	this.servicioproductoSelect = servicioproductoSelect;
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

@PostConstruct
    public void init()
{
	servicio = new Servicio();
    servicios = new ArrayList<Servicio>();
	
	producto = new Producto();
    productos = new ArrayList<Producto>();
    servicioproducto = new ServicioProducto();
    servicioproductos = new ArrayList<ServicioProducto>();
    getAllServicioproductos();
}

public void getAllServicioproductos()
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

public String newServicioproducto()
{
	try {
		this.servicios = ServicioBusiness.getAll();
		this.productos = ProductoBusiness.getAll();
    resetForm();
	}catch(Exception e) {
		
	}
    return "/servicioproducto/registrar.xhtml";
}

public String listServicioproducto()
{
    return "/servicio/listar.xhtml";
}

public String saverellenar()
{
    String view = "";
    try
    {

        if (servicioproducto.getId() != null)
        {
        	servicioproducto.setProducto(producto);
        	servicioproducto.setServicio(servicio);
            ServicioproductoBusiness.update(servicioproducto);
            Message.messageInfo("Registro actualizado exitosamente");
        }
        else
        {
        	servicioproducto.setProducto(producto);
        	servicioproducto.setServicio(servicio);
            ServicioproductoBusiness.insert(servicioproducto);
            Message.messageInfo("Registro guardado exitosamente");

        }
        this.getAllServicioproductos();
        resetForm();
        view = "/servicio/listar.xhtml";
    }
    catch (Exception e)
    {
        Message.messageError("Error Product :" + e.getStackTrace());
    }

    return view;
}
public String home()
{
    String view = "/servicioproducto/registrar.xhtml";
    return view;
 }
public String editServicioproducto()
{
    String view = "";
    try
    {
        if (this.servicioproductoSelect != null)
        {
            this.servicioproducto = servicioproductoSelect;

            view = "/servicioproducto/actualizar.xhtml";
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

public void searchServicioproductoByName()
{
    try
    {

        servicioproductos = ServicioproductoBusiness.getServicioByName(this.filterName.trim());
        resetForm();
        if (servicioproductos.isEmpty())
        {
            Message.messageInfo("No se encontraron servicios");

        }

    }
    catch (Exception e)
    {
        Message.messageError("Error Product Search :" + e.getMessage());
    }
}

public void SelectServicioproducto(SelectEvent e)
{
    this.servicioSelect = (Servicio)e.getObject();
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
    this.servicioproducto = new ServicioProducto();
}

	

}