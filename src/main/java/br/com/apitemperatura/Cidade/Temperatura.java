package br.com.apitemperatura.Cidade;

public class Temperatura {
	private float temp;
	private String data;
	
	public Temperatura(float tempa, String dataa) {
		this.temp=tempa;
		this.data = dataa;
	}
	
	
	public float getTemp() {
		return temp;
	}
	public void setTemp(float temp) {
		this.temp = temp;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
