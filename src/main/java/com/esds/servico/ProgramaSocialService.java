package com.esds.servico;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.esds.modelo.ProgramaSocial;

public interface ProgramaSocialService {
	
	public ProgramaSocial salvar(ProgramaSocial programa);
	public void atualizar(Integer id, ProgramaSocial programa);
	public ProgramaSocial buscarPorId(Integer id);
	public List<ProgramaSocial> buscarTodos();
	public void remover(Integer id);
	
	Page<ProgramaSocial> findAllPageable(Pageable pageable);
}
