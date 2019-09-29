package pe.upc.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.upc.model.entity.Servicio;

import pe.upc.model.repository.ServicioRepository;

@Named
public class ServicioBusiness implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServicioRepository problemaRepository;

	@Transactional
	public Long insert(Servicio admi) throws Exception {
		return problemaRepository.insert(admi);
	}

	
	@Transactional
	public Long update(Servicio admi) throws Exception{
		return problemaRepository.update(admi);
	}
	
	
	public List<Servicio> getAll() throws Exception {
		return problemaRepository.findAll();
	}
	
	public List<Servicio> getServicioByName(String name) throws Exception{
		return problemaRepository.findByName(name);
	}
}
