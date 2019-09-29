package pe.upc.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.upc.model.entity.ServicioProducto;


@Named
public class ServicioProductoRepository implements Serializable
{


    private static final long serialVersionUID = 1L;


    @PersistenceContext(unitName= "pwPU")

    private EntityManager em;

public Long insert(ServicioProducto problema) throws Exception
{
    em.persist(problema);
		return problema.getId();
}


public Long update(ServicioProducto problema) throws Exception
{
    em.merge(problema);
		return problema.getId();
}


public void delete(ServicioProducto problema) throws Exception
{
    em.remove(problema);
}


public List<ServicioProducto> findAll() throws Exception
{
    List<ServicioProducto> problema=new ArrayList<>();
		
		TypedQuery<ServicioProducto> query = em.createQuery("FROM ServicioProducto"
                , ServicioProducto.class);
		problema=query.getResultList();
		
		return problema;
	}
	public List<ServicioProducto> findByName(String name) throws Exception
{
    List<ServicioProducto> problema=new ArrayList<>();
		
		TypedQuery<ServicioProducto> query = em.createQuery("FROM ServicioProducto p WHERE p.name LIKE ?1"
                , ServicioProducto.class);
		query.setParameter(1, "%"+name+"%");
		problema=query.getResultList();
		
		return problema;
	}
	
	
	

	
	

}
