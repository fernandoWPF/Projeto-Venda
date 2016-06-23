package br.edu.projetovenda.dao;

import java.io.Serializable;

import br.edu.projetovenda.model.VendaItem;

public class VendaItemDAO extends GenericDAO<VendaItem> implements Serializable{

	private static final long serialVersionUID = 1L;

	public VendaItemDAO() {
		super(VendaItem.class);
	}
	
}
