package br.edu.projetovenda.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.projetovenda.dao.ClienteDAO;
import br.edu.projetovenda.model.Cliente;

@SessionScoped
@ManagedBean
public class ClienteManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;

	private ClienteDAO dao;

	private List<Cliente> clientes;

	@PostConstruct
	public void init() {
		cliente = new Cliente();
		dao = new ClienteDAO();
	}

	public void novo() throws IOException {
		cliente = new Cliente();
		FacesContext.getCurrentInstance().getExternalContext().redirect("ClienteCadastro.xhtml");
	}

	public void salvar() throws IOException {
		dao.salvar(cliente);
		FacesContext.getCurrentInstance().getExternalContext().redirect("ClientePesquisa.xhtml");
	}

	public void editar(Cliente cliente) throws IOException {
		this.cliente = cliente;
		System.out.println(this.cliente.getNome());
		FacesContext.getCurrentInstance().getExternalContext().redirect("ClienteCadastro.xhtml");
	}

	public void excluir(Cliente cliente) throws IOException {
		dao.excluir(dao.getById(cliente.getId()));
		FacesContext.getCurrentInstance().getExternalContext().redirect("ClientePesquisa.xhtml");
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		clientes = dao.findAll();
		return clientes;
	}

}
