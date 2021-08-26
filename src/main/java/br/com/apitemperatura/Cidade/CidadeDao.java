package br.com.apitemperatura.Cidade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CidadeDao {
	@JsonProperty("city_name")
	private String nome;
	@JsonProperty("temp")
	private float temperatura;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(float temperatura) {
		this.temperatura = temperatura;
	}
}