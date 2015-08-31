package br.com.lumi.ejb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.lumi.server.model.EstoqueModel;
import br.lumi.server.model.MarcaModel;
import br.lumi.server.model.MovimentoEstoqueModel;
import br.lumi.server.model.filtro.FiltroEstoqueModel;
import br.lumi.server.model.filtro.FiltroMarcaModel;
import br.lumi.server.model.filtro.FiltroMovimentoEstoqueModel;


//@Stateless
public class EstoqueEAO {

	@PersistenceContext
	private EntityManager em;
	
	private EstoqueModel salvarEstoque(EstoqueModel marca){
		return null;
	}
	private void excluirEstoquePorId(Integer id){
		
	}
	private EstoqueModel consultarEstoquePorCodigo(Integer codigo){
		return null;
		
	}
	private List<EstoqueModel> listarEstoques(){
		return null;
		
	}
	private EstoqueModel consultarEstoquePorFiltro(FiltroEstoqueModel filtro){
		return null;
		
	}
	// Movimentação de estoque
	private MovimentoEstoqueModel salvarmovimentacap(MovimentoEstoqueModel movimentacao){
		return null;
	}
	private void excluirMovimentoEstoquePorId(Integer id){
		
	}
	private MovimentoEstoqueModel consultarMovimentoEstoquePorCodigo(Integer codigo){
		return null;
		
	}
	private List<MovimentoEstoqueModel> listarMovimentoEstoque(){
		return null;
		
	}
	private MovimentoEstoqueModel consultarMovimentoEstoquePorFiltro(FiltroMovimentoEstoqueModel filtro){
		return null;
		
	}
}
