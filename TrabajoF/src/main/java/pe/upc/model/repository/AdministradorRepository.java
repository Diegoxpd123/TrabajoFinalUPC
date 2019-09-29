package pe.upc.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.upc.model.entity.Administrador;


@Named
public class AdministradorRepository implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName="pwPU")
	private EntityManager em;
	

	public Long insert(Administrador administrador) throws Exception {
		em.persist(administrador);
		return administrador.getId();
	}
	
	
	public Long update(Administrador administrador) throws Exception {
		em.merge(administrador);
		return administrador.getId();
	}
	
	
	public void delete(Administrador administrador) throws Exception {
		 em.remove(administrador);
	}
	
	
	public List<Administrador> findAll() throws Exception{
		List<Administrador> administrador=new ArrayList<>();
		
		TypedQuery<Administrador> query=em.createQuery("FROM Administrador"
				,Administrador.class);
		administrador=query.getResultList();
		
		return administrador;
	}
	public List<Administrador> findByName(String name) throws Exception{
		List<Administrador> administrador=new ArrayList<>();
		
		TypedQuery<Administrador> query=em.createQuery("FROM Administrador p WHERE p.name LIKE ?1"
				,Administrador.class);
		query.setParameter(1, "%"+name+"%");
		administrador=query.getResultList();
		
		return administrador;
	}
	
	
	

	
	

}
