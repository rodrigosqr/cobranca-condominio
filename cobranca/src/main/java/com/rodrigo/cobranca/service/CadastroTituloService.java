package com.rodrigo.cobranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rodrigo.cobranca.enuns.StatusTituloEnum;
import com.rodrigo.cobranca.model.Titulo;
import com.rodrigo.cobranca.repository.ITitulos;
import com.rodrigo.cobranca.repository.filter.TituloFiltro;

@Service
public class CadastroTituloService {
	
	@Autowired
	private ITitulos titulos;
	
	public void salvar(Titulo titulo){
		try{
			this.titulos.save(titulo);
		}catch(DataIntegrityViolationException e){
			throw new IllegalArgumentException("Formato de data inválido!");
		}
	}

	public List<Titulo> buscarTodos() {
		return this.titulos.findAll();
	}

	public void excluir(Long codigo) {
		this.titulos.delete(codigo);
	}

	public void receber(Long codigo) {
		Titulo titulo = this.titulos.findOne(codigo);
		titulo.setStatus(StatusTituloEnum.RECEBIDO);
		
		this.titulos.save(titulo);
	}
	
	public List<Titulo> buscarPorDescricao(TituloFiltro filtro){
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return this.titulos.findByDescricaoContaining(descricao);
	}
}
