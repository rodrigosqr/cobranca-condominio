package com.rodrigo.cobranca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigo.cobranca.model.Titulo;

public interface ITitulos extends JpaRepository<Titulo, Long>{
	List<Titulo> findByDescricaoContaining(String descricao);
}
