package br.com.lumi.ejb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.lumi.server.model.EsteticistaModel;
import br.lumi.server.model.MarcaModel;
import br.lumi.server.model.filtro.FiltroEsteticistaModel;
import br.lumi.server.model.filtro.FiltroMarcaModel;


//@Stateless
public class EsteticistaEAO {

	@PersistenceContext
	private EntityManager em;
	
	private EsteticistaModel salvarEsteticista(EsteticistaModel marca){
		return null;
	}
	private void excluirEsteticistaPorId(Integer id){
		
	}
	private EsteticistaModel consultarEsteticistaPorCodigo(Integer codigo){
		return null;
		
	}
	private List<EsteticistaModel> listarEsteticistas(){
		return null;
		
	}
	private EsteticistaModel consultarEsteticistaPorFiltro(FiltroEsteticistaModel filtro){
		return null;
		
	}
}
