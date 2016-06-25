package br.edu.projetovenda.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.projetovenda.dao.FornecedorDAO;
import br.edu.projetovenda.model.Fornecedor;

@SessionScoped
@ManagedBean
public class FornecedorManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Fornecedor fornecedor;

	private FornecedorDAO dao;

	private List<Fornecedor> fornecedores;

	@PostConstruct
	public void init() {
		fornecedor = new Fornecedor();
		dao = new FornecedorDAO();
	}

	public void novo() throws IOException {
		fornecedor = new Fornecedor();
		FacesContext.getCurrentInstance().getExternalContext().redirect("FornecedorCadastro.xhtml");
	}

	public void salvar() throws IOException {
		dao.salvar(fornecedor);
		FacesContext.getCurrentInstance().getExternalContext().redirect("FornecedorPesquisa.xhtml");
	}

	public void editar(Fornecedor fornecedor) throws IOException {
		this.fornecedor = fornecedor;
		FacesContext.getCurrentInstance().getExternalContext().redirect("FornecedorCadastro.xhtml");
	}

	public void excluir(Fornecedor fornecedor) throws IOException {
		dao.excluir(fornecedor);
		FacesContext.getCurrentInstance().getExternalContext().redirect("FornecedorPesquisa.xhtml");
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		fornecedores = dao.findAll();
		return fornecedores;
	}

}
