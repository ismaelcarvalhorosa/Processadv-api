package br.inf.lucas.processadv.api.cliente_obs;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="cliente_obs", schema="public")
@SequenceGenerator(name = "SEQ_CLIENTE_OBS", sequenceName = "SEQ_CLIENTE_OBS", allocationSize = 1)
public class Cliente_obs {

	@Id
	@Column(name = "OBSERVACAO_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTE_OBS")
	private Long observacao_id;
	
	@Column(name = "CLIENTE_ID")
	private Integer cliente_id;
	
	@Column(name = "DATA")
	private Date data;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "DATA_ATUALIZACAO")
	private Date data_atualizacao;
	
	@Column(name = "OBSERVACAO")
	private String observacao;

	public Long getObservacao_id() {
		return observacao_id;
	}

	public void setObservacao_id(Long observacao_id) {
		this.observacao_id = observacao_id;
	}

	public Integer getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Integer cliente_id) {
		this.cliente_id = cliente_id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getData_atualizacao() {
		return data_atualizacao;
	}

	public void setData_atualizacao(Date data_atualizacao) {
		this.data_atualizacao = data_atualizacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}