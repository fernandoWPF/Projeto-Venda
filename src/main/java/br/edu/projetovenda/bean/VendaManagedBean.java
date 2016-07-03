package br.edu.projetovenda.bean;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.projetovenda.dao.VendaDAO;
import br.edu.projetovenda.dao.VendaItemDAO;
import br.edu.projetovenda.model.Cliente;
import br.edu.projetovenda.model.Produto;
import br.edu.projetovenda.model.Venda;
import br.edu.projetovenda.model.VendaItem;
import br.edu.projetovenda.util.FacesUtil;

@SessionScoped
@ManagedBean
public class VendaManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Venda venda;

	private VendaDAO dao;

	private VendaItemDAO itemDao;

	private List<Venda> vendas;

	private List<VendaItem> itens;

	private VendaItem itemTemp;

	@PostConstruct
	public void init() {
		venda = new Venda();
		dao = new VendaDAO();
		itens = new ArrayList<>();
		itemDao = new VendaItemDAO();
	}

	public void novo() throws IOException {
		itemTemp = new VendaItem();
		itens = new ArrayList<>();
		venda = new Venda();
		FacesContext.getCurrentInstance().getExternalContext().redirect("VendaCadastro.xhtml");
	}

	public void salvar() throws IOException {

		BigDecimal valorTotal = new BigDecimal("0");

		for (VendaItem v : itens) {
			v.setVenda(venda);
			valorTotal = valorTotal.add(v.getValor());
			venda.setValor(valorTotal);

		}
		dao.salvar(venda);

		FacesContext.getCurrentInstance().getExternalContext().redirect("VendaPesquisa.xhtml");
	}

	public void editar(Venda venda) throws IOException {
		itemTemp = new VendaItem();
		this.venda = venda;
		itens = itemDao.findByVendaId(venda.getId());
		FacesContext.getCurrentInstance().getExternalContext().redirect("VendaCadastro.xhtml");
	}

	public void excluir(Venda venda) throws IOException {
		dao.excluir(venda);
		FacesContext.getCurrentInstance().getExternalContext().redirect("VendaPesquisa.xhtml");
	}

	public void addProduto(Produto produto, BigDecimal qtde) {

		if (produto == null || qtde.equals("0") || qtde.equals(null)) {

			FacesUtil.addMsgError("Insira os dados corretamente!");

		} else {

			VendaItem item = new VendaItem();
			item.setProduto(produto);
			item.setQuantidade(qtde);
			item.setValor(produto.getValor().multiply(qtde));
			itens.add(item);
			venda.setVendaItem(itens);
			itemTemp = new VendaItem();
		}
	}

	public void addProdutoItemTemp(Produto produto) {
		itemTemp = new VendaItem();
		itemTemp.setProduto(produto);
	}

	public void removeItem(VendaItem item) {

		BigDecimal total;
		total = item.getValor();
		venda.setValor(venda.getValor().subtract(total));
		dao.salvar(venda);
		itemDao.excluir(item);
		itens = itemDao.findByVendaId(venda.getId());
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

	public void setCliente(Cliente cliente) {
		this.venda.setCliente(cliente);
	}

	public VendaItem getItemTemp() {
		return itemTemp;
	}

	public List<VendaItem> getItens() {
		return itens;
	}

}
