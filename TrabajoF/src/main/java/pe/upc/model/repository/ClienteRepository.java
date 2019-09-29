package pe.upc.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.upc.model.entity.Cliente;


@Named
public class ClienteRepository implements Serializable
{


    private static final long serialVersionUID = 1L;


    @PersistenceContext(unitName= "pwPU")

    private EntityManager em;

public Long insert(Cliente Cliente) throws Exception
{
    em.persist(Cliente);
		return Cliente.getId();
}


public Long update(Cliente Cliente) throws Exception
{
    em.merge(Cliente);
		return Cliente.getId();
}


public void delete(Cliente Cliente) throws Exception
{
    em.remove(Cliente);
}


public List<Cliente> findAll() throws Exception
{
    List<Cliente> Cliente=new ArrayList<>();
		
		TypedQuery<Cliente> query = em.createQuery("FROM Cliente"
                , Cliente.class);
		Cliente=query.getResultList();
		
		return Cliente;
	}
	public List<Cliente> findByName(String name) throws Exception
{
    List<Cliente> Cliente=new ArrayList<>();
		
		TypedQuery<Cliente> query = em.createQuery("FROM Cliente p WHERE p.name LIKE ?1"
                , Cliente.class);
		query.setParameter(1, "%"+name+"%");
		Cliente=query.getResultList();
		
		return Cliente;
	}
	
	
	

	
	

}
