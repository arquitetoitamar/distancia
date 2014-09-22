package br.com.prova.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.prova.rest.model.Endereco;
import br.com.prova.util.ConnectionFactory;



public class DistanciaDAOImpl implements DistanciaDAO {
	Connection conn = null;
	/**
	 * Procedure para calcular distancia
	 */
	public String distanciaEntreDuasCidades(String codigoCidade1,
			String codigoCidade2) {
		String distancia = null;
		try {
			conn = ConnectionFactory.getConexao();
			CallableStatement cStmt = conn.prepareCall("{call calcularDistancia(?, ?, ?, ?)}");
			Endereco primeiraCidade = findByCod(codigoCidade1);
			Endereco segundaCidade = findByCod(codigoCidade2);
			cStmt.setString(1, primeiraCidade.getLatitude());
			cStmt.setString(2, primeiraCidade.getLongitude());
			cStmt.setString(3, segundaCidade.getLatitude());
			cStmt.setString(4, segundaCidade.getLongitude());
			
			ResultSet rs = cStmt.executeQuery();
			
			while (rs.next()) {
				return rs.getString("distancia");
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return distancia;
	}
	/**
	 * CarregaCidades
	 * @param cod
	 * @return
	 */
	private Endereco findByCod(String cod){
		try {
			conn = ConnectionFactory.getConexao();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM endereco WHERE codigo_cidade = ?");
			ps.setString(1, cod);
			
			ResultSet rs = ps.executeQuery();
				while (rs.next()) {
		        return new Endereco(rs.getString("codigo_cidade"), 
		        					   rs.getString("longitude"), 
		        					   rs.getString("latitude"));
		
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Endereco> listarDuasCidadesMaisProximas(Endereco enderecoPadrao) {
		List<Endereco> lista = new ArrayList<Endereco>();
		try {
			conn = ConnectionFactory.getConexao();
			CallableStatement cStmt = conn.prepareCall("{call listarCidadesPelaDistancia(?, ?, ?)}");
			cStmt.setString(1, enderecoPadrao.getLatitude());
			cStmt.setString(2, enderecoPadrao.getLongitude());
			cStmt.setInt(3, 10);
			
			ResultSet rs = cStmt.executeQuery();
			
			while (rs.next()) {
		        lista.add(new Endereco(rs.getString("codigo_cidade"), 
		        					   rs.getString("longitude"), 
		        					   rs.getString("latitude")));
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Endereco> listar() {
		List<Endereco> lista = new ArrayList<Endereco>();
		try {
			conn = ConnectionFactory.getConexao();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM endereco");
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
		        lista.add(new Endereco(rs.getString("codigo_cidade"), 
		        					   rs.getString("longitude"), 
		        					   rs.getString("latitude")));
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
