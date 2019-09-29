package pe.upc.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.upc.model.entity.ServicioProducto;

import pe.upc.model.repository.ServicioProductoRepository;

@Named
public class ServicioProductoBusiness implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServicioProductoRepository problemaRepository;

	@Transactional
	public Long insert(ServicioProducto admi) throws Exception {
		return problemaRepository.insert(admi);
	}

	
	@Transactional
	public Long update(ServicioProducto admi) throws Exception{
		return problemaRepository.update(admi);
	}
	
	
	public List<ServicioProducto> getAll() throws Exception {
		return problemaRepository.findAll();
	}
	
	public List<ServicioProducto> getServicioByName(String ip) throws Exception{
		return problemaRepository.findByName(ip);
	}
}
