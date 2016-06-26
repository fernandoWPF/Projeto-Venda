package br.edu.projetovenda.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

import br.edu.projetovenda.model.VendaItem;

public class VendaItemDAO extends GenericDAO<VendaItem> implements Serializable{

	private static final long serialVersionUID = 1L;

	public VendaItemDAO() {
		super(VendaItem.class);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VendaItem> findByVendaId(Long id){
		TypedQuery query = (TypedQuery) getEntityManager()
				.createQuery("SELECT o FROM VendaItem o  WHERE venda_id = ?");
		query.setParameter(1,id);
		return query.getResultList();
	}
	
}
