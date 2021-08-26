package br.com.apitemperatura.Cidade;

import java.util.ArrayList;

public class CidadeDAOResponse {
	
	public CidadeDAOResponse() {}
	public CidadeDAOResponse(String MSG) {
		this.Msg=MSG;
	}
	
	private String nome;
	private ArrayList<Temperatura> temperaturas;
	private String Msg;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ArrayList<Temperatura> getTemperaturas() {
		return temperaturas;
	}
	public void setTemperaturas(ArrayList<Temperatura> temperaturas) {
		this.temperaturas = temperaturas;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	
	
	
}
