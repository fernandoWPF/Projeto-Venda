package br.edu.projetovenda.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "VENDA")
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Data da venda é obrigatória")
	@Temporal(TemporalType.DATE)
	private Date dataVenda;

	@ManyToOne
	@JoinColumn
	@NotNull(message = "Cliente é obrigatório!")
	private Cliente cliente;

	@Column(scale = 2, precision = 12)
	private BigDecimal valor;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "venda", targetEntity = VendaItem.class)
	private List<VendaItem> vendaItem = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setVendaItem(List<VendaItem> vendaItem) {
		this.vendaItem = vendaItem;
	}
	
	public List<VendaItem> getVendaItem() {
		return vendaItem;
	}
}
