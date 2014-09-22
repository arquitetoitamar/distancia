package br.com.prova.rest.model;

import java.io.Serializable;

public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCidade;
	private String longitude;
	private String latitude;
	
	
	public Endereco(String codigoCidade, String longitude, String latitude) {
		super();
		this.codigoCidade = codigoCidade;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public String getCodigoCidade() {
		return codigoCidade;
	}
	public void setCodigoCidade(String codigoCidade) {
		this.codigoCidade = codigoCidade;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
}
