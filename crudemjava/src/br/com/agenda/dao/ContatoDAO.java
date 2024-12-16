package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

	/*
	 * CRUD
	 * C: CREATE
	 * R: READ
	 * U: UPDATE
	 * D: DELETE
	 */
	
	public void save(Contato contato) throws Exception {
		
		String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//cria uma conexão com o banco de dados
			conn =  ConnectionFactory.createConnectionToMySQL();
			
			//criamos a uma preparedStatement para execucao
			pstm = (PreparedStatement) conn.prepareCall(sql);
			
			//passa os valores esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			pstm.execute();
			
			System.out.println("Contato Salvo com sucesso!! ");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//ferchando as conexoes
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}	
	}	

	public List<Contato> getContatos(){
		
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar dados do banco
		ResultSet rset = null;
		
		try {
			conn =  ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareCall(sql);
					
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Contato contato = new Contato();
				contato.setId(rset.getInt("id"));
				contato.setNome(rset.getString("nome"));
				contato.setIdade(rset.getInt("idade"));
				contato.setDataCadastro(rset.getDate("datacadastro"));
				
				contatos.add(contato);
				
				
			}
			System.out.println("Contato Recuperado do banco");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//ferchando as conexoes
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
				if(rset != null) {
					rset.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return contatos;
	}

	public void update (Contato contato) {

		String sql = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ? " +
		"WHERE id = ?";
						
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn =  ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareCall(sql);
			
			//Adiciona os valores para atualizar
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			//qual o ID do registro deseja atualizar
			pstm.setInt(4, contato.getId());
			
			pstm.execute();
			
			System.out.println("Contato Atualizado com Sucesso!! ");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//ferchando as conexoes
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}			
	}
	
	public void deleteByID(int id) {
		
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			conn =  ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareCall(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
			System.out.println("Contato Deletado com sucesso!! ");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//ferchando as conexoes
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}	
		
		
	}
}
