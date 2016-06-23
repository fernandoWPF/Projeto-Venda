package br.edu.projetovenda.dao;

import java.io.Serializable;

import br.edu.projetovenda.model.Fornecedor;

public class FornecedorDAO extends GenericDAO<Fornecedor> implements Serializable{

	private static final long serialVersionUID = 1L;

	public FornecedorDAO() {
		super(Fornecedor.class);
	}
}
