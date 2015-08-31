package br.com.lumi.ejb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.lumi.server.model.MarcaModel;
import br.lumi.server.model.ProdutoModel;
import br.lumi.server.model.filtro.FiltroMarcaModel;
import br.lumi.server.model.filtro.FiltroProdutoModel;


//@Stateless
public class ProdutoEAO {

	@PersistenceContext
	private EntityManager em;
	
	private ProdutoModel salvarProduto(ProdutoModel marca){
		return null;
	}
	private void excluirProdutoPorId(Integer id){
		
	}
	private ProdutoModel consultarProdutoPorCodigo(Integer codigo){
		return null;
		
	}
	private List<ProdutoModel> listarProdutos(){
		return null;
		
	}
	private ProdutoModel consultarProdutoPorFiltro(FiltroProdutoModel filtro){
		return null;
		
	}
	
	// MARCA
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
