package br.com.lumi.ejb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


//@Stateless
public class EstoqueEAO {

	@PersistenceContext
	private EntityManager em;
	
//	private EstoqueModel salvarEstoque(EstoqueModel marca){
//		return null;
//	}
//	private void excluirEstoquePorId(Integer id){
//		
//	}
//	private EstoqueModel consultarEstoquePorCodigo(Integer codigo){
//		return null;
//		
//	}
//	private List<EstoqueModel> listarEstoques(){
//		return null;
//		
//	}
//	private EstoqueModel consultarEstoquePorFiltro(FiltroEstoqueModel filtro){
//		return null;
//		
//	}
//	// Movimentação de estoque
//	private MovimentoEstoqueModel salvarmovimentacap(MovimentoEstoqueModel movimentacao){
//		return null;
//	}
//	private void excluirMovimentoEstoquePorId(Integer id){
//		
//	}
//	private MovimentoEstoqueModel consultarMovimentoEstoquePorCodigo(Integer codigo){
//		return null;
//		
//	}
//	private List<MovimentoEstoqueModel> listarMovimentoEstoque(){
//		return null;
//		
//	}
//	private MovimentoEstoqueModel consultarMovimentoEstoquePorFiltro(FiltroMovimentoEstoqueModel filtro){
//		return null;
//		
//	}
}
