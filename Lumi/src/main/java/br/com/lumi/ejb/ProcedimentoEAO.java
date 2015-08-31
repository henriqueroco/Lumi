package br.com.lumi.ejb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.lumi.server.model.MarcaModel;
import br.lumi.server.model.filtro.FiltroMarcaModel;


//@Stateless
public class ProcedimentoEAO {

	@PersistenceContext
	private EntityManager em;
	
	private MarcaModel salvarMarca(MarcaModel marca){
		return null;
	}
	private void excluirMarcaPorId(Integer id){
		
	}
	private MarcaModel consultarMarcaPorCodigo(Integer codigo){
		return null;
		
	}
	private List<MarcaModel> listarMarcas(){
		return null;
		
	}
	private MarcaModel consultarMarcaPorFiltro(FiltroMarcaModel filtro){
		return null;
		
	}
}
