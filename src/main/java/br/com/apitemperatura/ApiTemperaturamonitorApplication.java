package br.com.apitemperatura;



import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
		LocalDateTime dateTime = LocalDateTime.now();
		long ms = 1000 * dateTime.toEpochSecond(ZoneOffset.UTC);
		
		System.out.println(ms%3600000);
		
		
		
		///////////////////////////////////////////////////////////
		
		Long start = 3600000-(ms%3600000);
		
		ApiService ser = new ApiService();
		
		
		Timer timer = new Timer();
		Agendador agendador = new Agendador();
		timer.schedule(agendador, start, 3600000);
		 
	}

}
