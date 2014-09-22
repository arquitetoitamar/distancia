package br.com.prova.dao;

import java.util.List;

import br.com.prova.rest.model.Endereco;

public interface DistanciaDAO {

	public String distanciaEntreDuasCidades(String codigoCidade1, String codigoCidade2);
	public List<Endereco> listarDuasCidadesMaisProximas(Endereco enderecoPadrao);
	public List<Endereco> listar();
}
