package br.com.apitemperatura.Cidade;

import java.util.ArrayList;

public class Cidade {
	private String nome;
	private ArrayList<Temperatura> temperaturas;
	
	
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
}
