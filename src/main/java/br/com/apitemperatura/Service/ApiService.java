package br.com.apitemperatura.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.apitemperatura.Cidade.Cidade;
import br.com.apitemperatura.Cidade.CidadeDao;
import br.com.apitemperatura.Cidade.Temperatura;
import br.com.apitemperatura.ConexaoBanco.Conexao;



public class ApiService {
	public void atualizarTemperaturas() {
		ArrayList<String> cidades = buscarCidades();
		
		for (String cidade : cidades) {
			getTemperatura(cidade);
		}
	}
	
	//SALVAR CIDADE NO BANCO
	public void salvarCidade(String nome) {
		
		Connection con = Conexao.getConexaoMySQL();
		
		String sql = "insert into cidade(nome) values(?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nome);
			ps.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public ArrayList<String> buscarCidades(){
		Connection con = Conexao.getConexaoMySQL();
		
		ArrayList<String> cidades = new ArrayList<>();
		String sql = "Select nome from cidade";
		PreparedStatement ps;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				cidades.add(rs.getNString("nome"));
			}
			con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(cidades);
		return cidades;
		
		
		
	}
	//SELECIONO A CIDADE DA QUAL QUERO VER AS TEMPERATURAS
	@SuppressWarnings("null")
	public Cidade getCidade(String cidade) {
		Connection con = Conexao.getConexaoMySQL();
		Cidade cidadeObj = new Cidade();
		ArrayList<Temperatura> temperaturas = new ArrayList<Temperatura>();
		
		String sql = "SELECT c.nome, t.graus, t.dataehora\r\n" + 
				"FROM cidade as c\r\n" + 
				"LEFT JOIN temperatura as t\r\n" + 
				"                on c.id = t.id_cidade where c.nome = ?";
		
		String sql2 = "SELECT situacao from cidade where nome=?";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, cidade);
			ResultSet rs = ps.executeQuery();
			rs.next();
			cidadeObj.setNome(rs.getString("nome"));
			temperaturas.add(new Temperatura(rs.getFloat("graus"),rs.getString("dataehora")));
			while(rs.next()) {
				temperaturas.add(new Temperatura(rs.getFloat("graus"),rs.getString("dataehora")));
			}
			cidadeObj.setTemperaturas(temperaturas);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cidadeObj;
		
		
	}
	
		//VOU NA API PEGAR A TEMPERATURA NO HORARIO
	public void getTemperatura(String cidade) {
		
		Connection con = Conexao.getConexaoMySQL();
		//https://api.hgbrasil.com/
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("api.hgbrasil.com")
				.path("weather?array_limit=2&fields=only_results,temp,city_name&key=7e819ff3&city_name="+cidade)
			    .build();
		
		RestTemplate restTemplate = new RestTemplate();
		
			
		ResponseEntity<CidadeDao> cidadeET = restTemplate.getForEntity(uri.toString(), CidadeDao.class);
		System.out.println(cidadeET.getBody().getTemperatura());
		System.out.println(cidadeET);
		System.out.println(cidadeET.getBody().getNome());
		
		
		this.cadastrarTemperatura(cidadeET);
	}
	//CADASTRAR A TEMPERATURA NO BANCO
	public void cadastrarTemperatura(ResponseEntity<CidadeDao> cidadedao) {
		Connection con = Conexao.getConexaoMySQL();
		int cod_cidade = 0;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	    String dataehora = dtf.format(LocalDateTime.now());
		
	    String sql = "select id from cidade where nome =?;";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, cidadedao.getBody().getNome());
			ResultSet rs = ps.executeQuery();
			rs.next();
			cod_cidade = rs.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "insert into temperatura(dataehora, graus,id_cidade) values (?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dataehora);
			System.out.println(dataehora);
			ps.setFloat(2, cidadedao.getBody().getTemperatura());
			ps.setInt(3, cod_cidade);
			ps.execute();
			con.close();
			System.out.println("INSERÇÃO FEITA!!!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public void desativarCidade(String cidade) {
		
		Connection con = Conexao.getConexaoMySQL();
		
		String sql = "update cidade set situacao=0 where nome = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cidade);
			ps.execute();
			con.close();
			System.out.println("Cidade desativada do monitoramento!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Não foi possivel remover cidade do monitoramento!");
		}
	}
}
