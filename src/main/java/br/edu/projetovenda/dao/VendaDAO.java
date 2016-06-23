package br.edu.projetovenda.dao;

import java.io.Serializable;

import br.edu.projetovenda.model.Venda;

public class VendaDAO extends GenericDAO<Venda> implements Serializable{

	private static final long serialVersionUID = 1L;

	public VendaDAO() {
		super(Venda.class);
	}
	
}
