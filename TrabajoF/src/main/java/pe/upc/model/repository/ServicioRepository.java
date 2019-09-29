package pe.upc.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.upc.model.entity.Servicio;


@Named
public class ServicioRepository implements Serializable
{


    private static final long serialVersionUID = 1L;


    @PersistenceContext(unitName= "pwPU")

    private EntityManager em;

public Long insert(Servicio problema) throws Exception
{
    em.persist(problema);
		return problema.getId();
}


public Long update(Servicio problema) throws Exception
{
    em.merge(problema);
		return problema.getId();
}


public void delete(Servicio problema) throws Exception
{
    em.remove(problema);
}


public List<Servicio> findAll() throws Exception
{
    List<Servicio> problema=new ArrayList<>();
		
		TypedQuery<Servicio> query = em.createQuery("FROM Servicio"
                , Servicio.class);
		problema=query.getResultList();
		
		return problema;
	}
	public List<Servicio> findByName(String name) throws Exception
{
    List<Servicio> problema=new ArrayList<>();
		
		TypedQuery<Servicio> query = em.createQuery("FROM Servicio p WHERE p.name LIKE ?1"
                , Servicio.class);
		query.setParameter(1, "%"+name+"%");
		problema=query.getResultList();
		
		return problema;
	}
	

	

	
	

}
