package br.edu.projetovenda.dao;

import java.io.Serializable;

import br.edu.projetovenda.model.Produto;

public class ProdutoDAO extends GenericDAO<Produto> implements Serializable{

	private static final long serialVersionUID = 1L;

	public ProdutoDAO() {
		super(Produto.class);
	}
}
