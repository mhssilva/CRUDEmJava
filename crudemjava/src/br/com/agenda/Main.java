package br.com.agenda;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Contato contato = new Contato();
				
		ContatoDAO contatoDao = new ContatoDAO();
		
		//salva o contato
		contatoDao.save(contato);
		
		//Deleta o contato
		contatoDao.deleteByID(5);
		
		//atualiza o contato
		Contato c1 = new Contato();
		c1.setId(0);
		contatoDao.update(c1);
				
		//visualização de todos os registros
		for (Contato c : contatoDao.getContatos()) {
			System.out.println(c.getNome());
			
		}

	}

}
