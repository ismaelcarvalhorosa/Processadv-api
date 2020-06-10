package br.inf.lucas.processadv.api.cliente_contato;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import br.inf.lucas.processadv.api.cliente.Cliente;

@Entity
@Table(name="cliente_contato", schema="public")
@SequenceGenerator(name = "seq_cliente_contato", sequenceName = "seq_cliente_contato", allocationSize = 1)
public class ClienteContato {
    @Id
	@Column(name = "CONTATO_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente_contato")
	private Long contato_id;
	
	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID")
	private Cliente cliente;
	
	@Column(name = "DESCRICAO")
	@Size(max = 80)
	private String descricao;
	
	@Column(name = "TELEFONE")
	@Size(max = 30)
	private String telefone;
	
	@Column(name = "STATUS")
	private Integer status;

	public Long getContato_id() {
		return contato_id;
	}

	public void setContato_id(Long contato_id) {
		this.contato_id = contato_id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}