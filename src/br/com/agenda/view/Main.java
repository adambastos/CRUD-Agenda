package br.com.agenda.view;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {
		ContatoDAO contatoDao = new ContatoDAO();
		
		Contato contato = new Contato();
		contato.setNome("Felipe Castro");
		contato.setIdade(27);
		contato.setDataCadastro(new Date());
		
		contatoDao.save(contato);
		
		//Visualizar registros do BD
		for(Contato c: contatoDao.getContatos()) {
			System.out.println("Contato: " +c.getId()+ ", " + c.getNome() + ", " + c.getIdade() + ", " + c.getDataCadastro());
		}
		
		

	}

}
