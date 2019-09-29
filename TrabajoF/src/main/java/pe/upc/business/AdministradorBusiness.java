package pe.upc.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.upc.model.entity.Administrador;

import pe.upc.model.repository.AdministradorRepository;

@Named
public class AdministradorBusiness implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AdministradorRepository administradorRepository;

	@Transactional
	public Long insert(Administrador admi) throws Exception {
		return administradorRepository.insert(admi);
	}

	
	@Transactional
	public Long update(Administrador admi) throws Exception{
		return administradorRepository.update(admi);
	}
	
	public void delete(Administrador admi) throws Exception{
		administradorRepository.update(admi);
	}
	
	public List<Administrador> getAll() throws Exception {
		return administradorRepository.findAll();
	}
	
	public List<Administrador> getAdministradorByName(String name) throws Exception{
		return administradorRepository.findByName(name);
	}
}
