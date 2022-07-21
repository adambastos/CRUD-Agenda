package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

	public void save(Contato contato) {
		String sql = "INSERT INTO contatos (nome, idade, dataCadastro) VALUES (?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL(); //Criar uma conexão com o banco de dados
			pstm = (PreparedStatement) conn.prepareStatement(sql); //Criada uma PreparedStatement para executar uma query
			//Adicionar os valores que são esoerados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			//Executar a query
			pstm.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			//Fechar as conexões
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	public List<Contato> getContatos() {
		String sql = "SELECT * FROM contatos";
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		//Classe que recupera os dados do BD *SELECT*
		ResultSet rset = null;
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Contato contato = new Contato();
				
				//Recuperar o id
				contato.setId(rset.getInt("id"));
				//Recuperar o nome
				contato.setNome(rset.getString("nome"));
				//Recuperar a idade
				contato.setIdade(rset.getInt("idade"));
				//Recuperar a data
				contato.setDataCadastro(rset.getDate("dataCadastro"));
				contatos.add(contato);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rset!=null) {
					rset.close();
				}
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return contatos;
	}
}
