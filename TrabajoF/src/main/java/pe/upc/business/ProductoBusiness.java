package pe.upc.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.upc.model.entity.Producto;

import pe.upc.model.repository.ProductoRepository;

@Named
public class ProductoBusiness implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProductoRepository productoRepository;

	@Transactional
	public Long insert(Producto admi) throws Exception {
		return productoRepository.insert(admi);
	}

	
	@Transactional
	public Long update(Producto admi) throws Exception{
		return productoRepository.update(admi);
	}
	
	
	public List<Producto> getAll() throws Exception {
		return productoRepository.findAll();
	}
	
	public List<Producto> getClienteByName(String name) throws Exception{
		return productoRepository.findByName(name);
	}
}
