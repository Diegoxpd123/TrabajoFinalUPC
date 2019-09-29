package pe.upc.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.upc.model.entity.Problema;

import pe.upc.model.repository.ProblemaRepository;

@Named
public class ProblemaBusiness implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProblemaRepository problemaRepository;

	@Transactional
	public Long insert(Problema admi) throws Exception {
		return problemaRepository.insert(admi);
	}

	
	@Transactional
	public Long update(Problema admi) throws Exception{
		return problemaRepository.update(admi);
	}
	
	
	public List<Problema> getAll() throws Exception {
		return problemaRepository.findAll();
	}
	
	public List<Problema> getProblemaByName(String name) throws Exception{
		return problemaRepository.findByName(name);
	}
}
