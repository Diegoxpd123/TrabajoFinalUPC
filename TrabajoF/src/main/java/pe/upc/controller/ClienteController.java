package pe.upc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import pe.upc.business.ClienteBusiness;
import pe.upc.model.entity.Cliente;

import pe.upc.util.Message;

@Named
@SessionScoped
public class ClienteController implements Serializable
{


    private static final long serialVersionUID = 1L;

@Inject
    private ClienteBusiness ClienteBusiness;

private Cliente cliente;
private List<Cliente> clientes;
private Cliente clienteSelect;

private String filterName;

@PostConstruct
    public void init()
{
    cliente = new Cliente();
    clientes = new ArrayList<Cliente>();
    getAllClientes();
}

public void getAllClientes()
{
    try
    {
        clientes = ClienteBusiness.getAll();
    }
    catch (Exception e)
    {
        Message.messageError("Error :" + e.getMessage());
    }
}

public String newCliente()
{
    resetForm();
    return "/cliente/registrar.xhtml";
}

public String listCliente()
{
    return "/cliente/listar.xhtml";
}

public String saveCliente()
{
    String view = "";
    try
    {  

        if (cliente.getId() != null)
        {
            ClienteBusiness.update(cliente);
            Message.messageInfo("Registro actualizado exitosamente");
        }
        else
        {
            ClienteBusiness.insert(cliente);
            Message.messageInfo("Registro guardado exitosamente");

        }
    
        this.getAllClientes();
        resetForm();
        view = "/cliente/listar.xhtml";
   
    }
    catch (Exception e)
    {
        Message.messageError("Error Product :" + e.getStackTrace());
    }

    return view;
}

public String editCliente()
{
    String view = "";
    try
    {
        if (this.clienteSelect != null)
        {
            this.cliente = clienteSelect;

            view = "/cliente/actualizar.xhtml";
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

public void searchClienteByName()
{
    try
    {

        clientes = ClienteBusiness.getClienteByName(this.filterName.trim());
        resetForm();
        if (clientes.isEmpty())
        {
            Message.messageInfo("No se encontraron productos");

        }

    }
    catch (Exception e)
    {
        Message.messageError("Error Product Search :" + e.getMessage());
    }
}

public void SelectCliente(SelectEvent e)
{
    this.clienteSelect = (Cliente)e.getObject();
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
    this.cliente = new Cliente();
}

	

}