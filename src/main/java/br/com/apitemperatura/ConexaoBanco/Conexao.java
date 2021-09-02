package br.com.apitemperatura.ConexaoBanco;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	 public static String status = "Não conectou...";

		//Método Construtor da Classe//

		 public Conexao() {}
		 
		 public static java.sql.Connection getConexaoMySQL() {
			 URI jdbUri;
			try {
				jdbUri = new URI(System.getenv("mysql://od3ro5a3y795jdt6:jimegjnhf0n2d8bj@ik1eybdutgxsm0lo.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/kbil0xf9m4uydczp\r\n" + 
						"\r\n" + 
						""));
				  String username = jdbUri.getUserInfo().split(":")[0];
				  String password = jdbUri.getUserInfo().split(":")[1];
				  String port = String.valueOf(jdbUri.getPort());
				  String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();
				  try {
					return DriverManager.getConnection(jdbUrl, username, password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			

			  

			  
			 
			 /*Connection connection = null;          //atributo do tipo Connection


			
				try {
			
				// Carregando o JDBC Driver padrão
			
					String driverName = "com.mysql.cj.jdbc.Driver";
				
					Class.forName(driverName);
				
				
				
					// Configurando a nossa conexão com um banco de dados//
				
					        String serverName = "localhost";    //caminho do servidor do BD
				
					        String mydatabase ="api_monitor";        //nome do seu banco de dados
				
					        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
				
					        String username = "root";        //nome de um usuário de seu BD
				
					        String password = "123456";      //sua senha de acesso
				
					        connection = DriverManager.getConnection(url, username, password);
				
				
				
					        //Testa sua conexão//
				
					        if (connection != null) {
				
					            status = ("STATUS--->Conectado com sucesso!");
				
					        } else {
				
					            status = ("STATUS--->Não foi possivel realizar conexão");
				
					        }
				
				
				
					        return connection;
			
			
				        } catch (ClassNotFoundException e) {  //Driver não encontrado
			
			
			
				            System.out.println("O driver expecificado nao foi encontrado.");
			
				            return null;
			
				        } catch (SQLException  e) {
			
				//Não conseguindo se conectar ao banco
			
				            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
			
				            return null;
			
				        }*/
		 }
				
		//Método que retorna o status da sua conexão//
		 public static String statusConection() {

			        return status;

		 }
		
		 //Método que fecha sua conexão//
		 public static boolean FecharConexao() {

		        try {

		            Conexao.getConexaoMySQL().close();

		            return true;

		        } catch (SQLException e) {

		            return false;

		        }




		    }

		//Método que reinicia sua conexão//

		 public static java.sql.Connection ReiniciarConexao() {

		       FecharConexao();
		       return Conexao.getConexaoMySQL();

		    }
}
