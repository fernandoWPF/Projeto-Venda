package br.edu.projetovenda.dao;

import java.io.Serializable;

import br.edu.projetovenda.model.Cliente;

public class ClienteDAO extends GenericDAO<Cliente> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public ClienteDAO() {
		super(Cliente.class);
	}

}
