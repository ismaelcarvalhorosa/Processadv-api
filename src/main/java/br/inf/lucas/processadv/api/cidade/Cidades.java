package br.inf.lucas.processadv.api.cidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.inf.lucas.processadv.api.UF;

@Entity
@Table(name="cidade", schema="public")
@SequenceGenerator(name = "seq_cidade_id", sequenceName = "seq_cidade_id", allocationSize = 1)
public class Cidades {
	@Id
	@Column(name = "CIDADE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cidade_id")
	private Long cidade_id;

	@NotNull
	@Size(max = 60)
	private String cidade_nome;

	@NotNull
	@Enumerated(EnumType.STRING)
	private UF cidade_uf;
		
	@Column(name = "STATUS")
	private Integer status;

	public Long getCidade_id() {
		return cidade_id;
	}

	public void setCidade_id(Long cidade_id) {
		this.cidade_id = cidade_id;
	}

	public String getCidade_nome() {
		return cidade_nome;
	}

	public void setCidade_nome(String cidade_nome) {
		this.cidade_nome = cidade_nome;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public UF getCidade_uf() {
		return cidade_uf;
	}

	public void setCidade_uf(UF cidade_uf) {
		this.cidade_uf = cidade_uf;
	}
}