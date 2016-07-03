package br.edu.projetovenda.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.projetovenda.dao.ProdutoDAO;
import br.edu.projetovenda.model.Fornecedor;
import br.edu.projetovenda.model.Produto;
import br.edu.projetovenda.util.FacesUtil;

@SessionScoped
@ManagedBean
public class ProdutoManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;

	private ProdutoDAO dao;

	private List<Produto> produtos;

	@PostConstruct
	public void init() {
		produto = new Produto();
		dao = new ProdutoDAO();
	}

	public void novo() throws IOException {
		produto = new Produto();
		FacesContext.getCurrentInstance().getExternalContext().redirect("ProdutoCadastro.xhtml");
	}

	public void salvar() throws IOException {
		dao.salvar(produto);
		FacesContext.getCurrentInstance().getExternalContext().redirect("ProdutoPesquisa.xhtml");
	}

	public void editar(Produto produto) throws IOException {
		this.produto = produto;
		FacesContext.getCurrentInstance().getExternalContext().redirect("ProdutoCadastro.xhtml");
	}

	public void excluir(Produto produto) {
		try {
			dao.excluir(produto);
			FacesContext.getCurrentInstance().getExternalContext().redirect("ProdutoPesquisa.xhtml");
		} catch (Exception exception) {
			FacesUtil.addMsgError("Produto vinculado a uma venda. Verifique as vendas e refaça a operação!");
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		dao = null; // isso por conta do problema do sessionScoped
		dao = new ProdutoDAO(); // isso por conta do problema do sessionScoped
		produtos = dao.findAll();
		for (Produto p : produtos) {
			System.out.println(p.getSaldo());
		}
		return produtos;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.produto.setFornecedor(fornecedor);
	}

}
