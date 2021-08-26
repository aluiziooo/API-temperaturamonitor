package br.com.apitemperatura;



import java.util.Timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.apitemperatura.Agendador.Agendador;
import br.com.apitemperatura.ConexaoBanco.Conexao;
import br.com.apitemperatura.Service.ApiService;






@SpringBootApplication
public class ApiTemperaturamonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTemperaturamonitorApplication.class, args);
		
		//Conexao.getConexaoMySQL();

		//System.out.println(Conexao.statusConection());
		
		ApiService ser = new ApiService();
		 
		Timer timer = new Timer();
		Agendador agendador = new Agendador();
		timer.schedule(agendador, 0, 120000);
		 
	}

}
