package br.com.apitemperatura.Controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.apitemperatura.Cidade.Cidade;
import br.com.apitemperatura.Cidade.CidadeDAOResponse;
import br.com.apitemperatura.Cidade.CidadeDao;
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
	public ResponseEntity<CidadeDAOResponse> buscarCidade(@PathVariable("nome") String nome) {
		if(apis.verificarSit(nome)==1) {
			return new ResponseEntity<CidadeDAOResponse>(apis.getCidade(nome), HttpStatus.ACCEPTED); 
		}
		return new ResponseEntity<CidadeDAOResponse>(new CidadeDAOResponse("Cidade Desativada para monitoramento!!!"), HttpStatus.FORBIDDEN);
		
	}
	@PostMapping("/cities/{nome}")
	public void salvarCidade(@RequestBody Cidade cidade){
		apis.salvarCidade(cidade.getNome());
	}
	@DeleteMapping("/cities/{nome}")
	public ResponseEntity<String> deletarCidade(@PathVariable("nome") String nome){
		apis.desativarCidade(nome);
		return new ResponseEntity<String>("Cidade deletada com sucesso!",HttpStatus.ACCEPTED);
	}
	@PatchMapping("/cities/{nome}")
	public void apagarHistorico(@PathVariable("nome") String cidade) {
		apis.apagarHistorico(cidade);
	}
	@GetMapping("/cities/max_temperatures")
	public ArrayList<CidadeDao> maioresTemps() {
		return apis.maioresTemperaturas();
	}
	@PostMapping("/cities/cep/{cep}")
	public ResponseEntity<String> salvarPorCep(@PathVariable("cep") String cep) {
		if(apis.verificaCEP(cep)) {
			apis.getCidadePorCep(cep);
			return new ResponseEntity<String>("Cidade cadastrada",HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("CEP invalido",HttpStatus.BAD_GATEWAY);
		
	}
}
