package pe.upc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import pe.upc.business.ProductoBusiness;
import pe.upc.model.entity.Producto;

import pe.upc.util.Message;

@Named
@SessionScoped
public class ProductoController implements Serializable
{


    private static final long serialVersionUID = 1L;

@Inject
    private ProductoBusiness ProductoBusiness;

private Producto producto;
private List<Producto> productos;
private Producto productoSelect;

private String filterName;

@PostConstruct
    public void init()
{
    producto = new Producto();
    productos = new ArrayList<Producto>();
    getAllProductos();
}

public void getAllProductos()
{
    try
    {
        productos = ProductoBusiness.getAll();
    }
    catch (Exception e)
    {
        Message.messageError("Error :" + e.getMessage());
    }
}

public String newProducto()
{
    resetForm();
    return "/producto/registrar.xhtml";
}

public String listProducto()
{
    return "/producto/listar.xhtml";
}

public String saveProducto()
{
    String view = "";
    try
    {

        if (producto.getId() != null)
        {
            ProductoBusiness.update(producto);
            Message.messageInfo("Registro actualizado exitosamente");
        }
        else
        {
            ProductoBusiness.insert(producto);
            Message.messageInfo("Registro guardado exitosamente");

        }
        this.getAllProductos();
        resetForm();
        view = "/producto/listar.xhtml";
    }
    catch (Exception e)
    {
        Message.messageError("Error Product :" + e.getStackTrace());
    }

    return view;
}

public String editProducto()
{
    String view = "";
    try
    {
        if (this.productoSelect != null)
        {
            this.producto = productoSelect;

            view = "/producto/actualizar.xhtml";
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

public void searchProductoByName()
{
    try
    {

        productos = ProductoBusiness.getClienteByName(this.filterName.trim());
        resetForm();
        if (productos.isEmpty())
        {
            Message.messageInfo("No se encontraron productos");

        }

    }
    catch (Exception e)
    {
        Message.messageError("Error Product Search :" + e.getMessage());
    }
}

public void SelectProducto(SelectEvent e)
{
    this.productoSelect = (Producto)e.getObject();
}



public Producto getProducto()
{
    return producto;
}

public void setProducto(Producto Cliente)
{
    this.producto = Cliente;
}

public List<Producto> getProductos()
{
    return productos;
}

public void setProducto(List<Producto> Clientees)
{
    this.productos = Clientees;
}

public Producto getProductoSelect()
{
    return productoSelect;
}

public void setProductoSelect(Producto ClienteSelect)
{
    this.productoSelect = ClienteSelect;
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
    this.producto = new Producto();
}

	

}