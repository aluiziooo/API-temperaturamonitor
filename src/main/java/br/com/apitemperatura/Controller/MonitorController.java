package br.com.apitemperatura.Controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.apitemperatura.Cidade.Cidade;
import br.com.apitemperatura.Service.ApiService;


@RestController
public class MonitorController {
	
	ApiService apis = new ApiService();
	
	@GetMapping("/cities")
	public ArrayList<String> Getcidades() {
		
		
		ArrayList<String> cidades = apis.buscarCidades();
		System.out.println(cidades);
		apis.atualizarTemperaturas();
 		return cidades;
		
		
		
		//fzer array das cidades no banco
		
		//pfazer as requisições a API
		//devolver os jasons
	}
	@GetMapping("/cities/{nome}")
	public Cidade buscarCidade(@PathVariable("nome") String nome) {
		return apis.getCidade(nome);
	}
	@PostMapping("/cities/{nome}")
	public void salvarCidade(@RequestBody Cidade cidade){
		System.out.println(cidade.getNome());
		apis.salvarCidade(cidade.getNome());
	}
	@DeleteMapping("/cities/{nome}")
	public ResponseEntity<String> deletarCidade(@PathVariable("nome") String nome){
		apis.desativarCidade(nome);
		return new ResponseEntity<String>("Cidade deletada com sucesso!",HttpStatus.ACCEPTED);
	}
}