package br.com.apitemperatura.Agendador;

import java.util.ArrayList;
import java.util.TimerTask;

import br.com.apitemperatura.Service.ApiService;

public class Agendador extends TimerTask{
	ApiService apis = new ApiService();

	@Override
	public void run() {
		ArrayList<String> cidades = apis.buscarCidades();
		int sit;
				
		for (String cidade : cidades) {
			if(apis.verificarSit(cidade)==1) {
				apis.getTemperatura(cidade);
			}else {
				continue;
			}
		
		}
	}	
}
