package br.com.lumi.ejb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.lumi.server.model.CidadeModel;
import br.lumi.server.model.LogradouroModel;
import br.lumi.server.model.MarcaModel;
import br.lumi.server.model.filtro.FiltroCidadeModel;
import br.lumi.server.model.filtro.FiltroLogradouroModel;
import br.lumi.server.model.filtro.FiltroMarcaModel;


//@Stateless
public class LogradouroEAO {

	@PersistenceContext
	private EntityManager em;
	
	// Cidade
	private CidadeModel salvarCidade(CidadeModel cidade){
		return null;
	}
	private void excluirCidadePorId(Integer id){
		
	}
	private CidadeModel consultarCidadePorCodigo(Integer codigo){
		return null;
		
	}
	private List<CidadeModel> listarCidades(){
		return null;
		
	}
	private MarcaModel consultarCidadePorFiltro(FiltroCidadeModel filtro){
		return null;
		
	}
	// Logradouro
	private LogradouroModel salvarLogradouro(LogradouroModel marca){
		return null;
	}
	private void excluirLogradouroPorId(Integer id){
		
	}
	private LogradouroModel consultarLograduroPorCodigo(Integer codigo){
		return null;
		
	}
	private List<LogradouroModel> listarLogradouro(){
		return null;
		
	}
	private LogradouroModel consultarLogradouroPorFiltro(FiltroLogradouroModel filtro){
		return null;
		
	}
}
