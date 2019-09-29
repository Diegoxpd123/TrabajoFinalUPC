package pe.upc.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.upc.model.entity.Producto;


@Named
public class ProductoRepository implements Serializable
{


    private static final long serialVersionUID = 1L;


    @PersistenceContext(unitName= "pwPU")

    private EntityManager em;

public Long insert(Producto producto) throws Exception
{
    em.persist(producto);
		return producto.getId();
}


public Long update(Producto Cliente) throws Exception
{
    em.merge(Cliente);
		return Cliente.getId();
}


public void delete(Producto Cliente) throws Exception
{
    em.remove(Cliente);
}


public List<Producto> findAll() throws Exception
{
    List<Producto> Cliente=new ArrayList<>();
		
		TypedQuery<Producto> query = em.createQuery("FROM Producto"
                , Producto.class);
		Cliente=query.getResultList();
		
		return Cliente;
	}
	public List<Producto> findByName(String name) throws Exception
{
    List<Producto> Cliente=new ArrayList<>();
		
		TypedQuery<Producto> query = em.createQuery("FROM Producto p WHERE p.name LIKE ?1"
                , Producto.class);
		query.setParameter(1, "%"+name+"%");
		Cliente=query.getResultList();
		
		return Cliente;
	}
	
	
	

	
	

}
