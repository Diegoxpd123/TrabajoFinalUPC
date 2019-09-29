package pe.upc.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.upc.model.entity.Problema;


@Named
public class ProblemaRepository implements Serializable
{


    private static final long serialVersionUID = 1L;


    @PersistenceContext(unitName= "pwPU")

    private EntityManager em;

public Long insert(Problema problema) throws Exception
{
    em.persist(problema);
		return problema.getId();
}


public Long update(Problema problema) throws Exception
{
    em.merge(problema);
		return problema.getId();
}


public void delete(Problema problema) throws Exception
{
    em.remove(problema);
}


public List<Problema> findAll() throws Exception
{
    List<Problema> problema=new ArrayList<>();
		
		TypedQuery<Problema> query = em.createQuery("FROM Problema"
                , Problema.class);
		problema=query.getResultList();
		
		return problema;
	}
	public List<Problema> findByName(String name) throws Exception
{
    List<Problema> problema=new ArrayList<>();
		
		TypedQuery<Problema> query = em.createQuery("FROM Problema p WHERE p.name LIKE ?1"
                , Problema.class);
		query.setParameter(1, "%"+name+"%");
		problema=query.getResultList();
		
		return problema;
	}
	
	
	

	
	

}
