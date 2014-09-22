package br.com.prova.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.prova.dao.DistanciaDAOImpl;
import br.com.prova.rest.model.Endereco;

@Path("/json/distancia")
public class JSONService {

	@GET
	@Path("/obterCidadesMaisPerto")
	@Produces("application/json")
	public List<Endereco> getCidadesInJSON() {
		return new DistanciaDAOImpl().listarDuasCidadesMaisProximas(new Endereco("",
				"-46.633525399999996", "-23.5454668"));
	}
	
	@GET
	@Path("calcularDistancia/{cod1}/{cod2}")
	@Produces("application/json")
	public String calcularDistancia(@PathParam("cod1") String id,
											@PathParam("cod2") String id2) {
		return new DistanciaDAOImpl().distanciaEntreDuasCidades(id, id2);
	}
	@GET
	@Path("/listarTodas")
	@Produces("application/json")
	public List<Endereco> getTodasCidadesInJSON() {
		return new DistanciaDAOImpl().listar();
	}

}