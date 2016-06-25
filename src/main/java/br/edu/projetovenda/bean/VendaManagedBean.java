package br.edu.projetovenda.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.projetovenda.dao.VendaDAO;
import br.edu.projetovenda.model.Venda;
@ViewScoped
@ManagedBean
public class VendaManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Venda venda;

	private VendaDAO dao;

	private List<Venda> vendas;

	@PostConstruct
	public void init() {
		venda = new Venda();
		dao = new VendaDAO();
	}

	public void novo() throws IOException {
		venda = new Venda();
		FacesContext.getCurrentInstance().getExternalContext().redirect("VendaCadastro.xhtml");
	}

	public void salvar() throws IOException {
		dao.salvar(venda);
		FacesContext.getCurrentInstance().getExternalContext().redirect("VendaPesquisa.xhtml");
	}

	public void editar(Venda venda) throws IOException {
		this.venda = venda;
		FacesContext.getCurrentInstance().getExternalContext().redirect("VendaCadastro.xhtml");
	}

	public void excluir(Venda venda) throws IOException {
		dao.excluir(venda);
		FacesContext.getCurrentInstance().getExternalContext().redirect("VendaPesquisa.xhtml");
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Venda> getVendas() {
		vendas = dao.findAll();
		return vendas;
	}

}
