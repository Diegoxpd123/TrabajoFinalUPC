package pe.upc.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.upc.model.entity.Cliente;

import pe.upc.model.repository.ClienteRepository;

@Named
public class ClienteBusiness implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteRepository clienteRepository;

	@Transactional
	public Long insert(Cliente admi) throws Exception {
		return clienteRepository.insert(admi);
	}

	
	@Transactional
	public Long update(Cliente admi) throws Exception{
		return clienteRepository.update(admi);
	}
	
	
	public List<Cliente> getAll() throws Exception {
		return clienteRepository.findAll();
	}
	
	public List<Cliente> getClienteByName(String name) throws Exception{
		return clienteRepository.findByName(name);
	}
}
