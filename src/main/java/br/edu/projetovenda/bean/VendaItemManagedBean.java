package br.edu.projetovenda.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.projetovenda.dao.VendaItemDAO;
import br.edu.projetovenda.model.VendaItem;
@ViewScoped
@ManagedBean
public class VendaItemManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private VendaItem item;

	private VendaItemDAO dao;

	private List<VendaItem> itens;

	@PostConstruct
	public void init() {
		item = new VendaItem();
		dao = new VendaItemDAO();
	}

	public void novo() {
		item = new VendaItem();
	}

	public void salvar() {
		dao.salvar(item);
	}

	public void editar(VendaItem item) {
		this.item = item;
	}

	public void excluir(VendaItem item) {
		this.item = item;
	}

	public VendaItem getItem() {
		return item;
	}

	public void setItem(VendaItem item) {
		this.item = item;
	}

	public List<VendaItem> getItens() {
		return itens;
	}

}
