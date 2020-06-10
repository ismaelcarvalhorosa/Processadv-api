package br.inf.lucas.processadv.api.cliente;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="cliente", schema="public")
@SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "SEQ_CLIENTE", allocationSize = 1)
public class Cliente {

	@Id
	@Column(name = "CLIENTE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTE")
	private Long cliente_id;
	
	@Column(name = "NOME")
	@Size(max = 60)
	private String nome;
	
	@Column(name = "RG")
	@Size(max = 20)
	private String rg;
	
	@Column(name = "ORGAO")
	@Size(max = 20)
	private String orgao;
	
	@Column(name = "CPF")
	@Size(max = 18)
	private String cpf;
	
	@Column(name = "RAZAO_SOCIAL")
	@Size(max = 80)
	private String razao_social;
	
	@Column(name = "ESTADO_CIVIL")
	private Integer estado_civil;
	
	@Column(name = "CONJUGUE")
	@Size(max = 60)
	private String conjugue;
	
	@Column(name = "PROFISSAO")
	@Size(max = 60)
	private String profissao;
	
	@Column(name = "PAI")
	@Size(max = 60)
	private String pai;
	
	@Column(name = "MAE")
	@Size(max = 60)
	private String mae;
	
	@Column(name = "NIT")
	@Size(max = 60)
	private String nit;
	
	@Column(name = "APOSENTADO")
	private Integer aposentado;
	
	@Column(name = "TIPO_APOSENTA")
	private Integer tipo_aposenta;
	
	@Column(name = "NB")
	@Size(max = 60)
	private String nb;
	
	@Column(name = "DIB")
	private Date dib;
	
	@Column(name = "RMI")
	private double rmi;
	
	@Column(name = "TC")
	@Size(max = 60)
	private String tc;
	
	@Column(name = "ENDERECO")
	@Size(max = 80)
	private String endereco;
	
	@Column(name = "END_NRO")
	@Size(max = 10)
	private String end_nro;
	
	@Column(name = "BAIRRO")
	@Size(max = 60)
	private String bairro;
	
	@Column(name = "COMPLEMENTO")
	@Size(max = 40)
	private String complemento;
	
	@Column(name = "CEP")
	@Size(max = 10)
	private String cep;
	
	@Column(name = "EMAIL")
	@Size(max = 50)
	private String email;
	
	@Column(name = "TELEFONE")
	@Size(max = 30)
	private String telefone;
	
	@Column(name = "FOTO")
	private String foto;
	
	@Column(name = "DATA_NASCIMENTO")
	private Date data_nascimento;
	
	@Column(name = "DDB")
	private Date ddb;
	
	@Column(name = "DER")
	private Date der;
	
	@Column(name = "DCB")
	private Date dcb;
	
	@Column(name = "APS")
	@Size(max = 100)
	private String aps;
	
	@Column(name = "ESP")
	@Size(max = 3)
	private String esp;
	
	@Column(name = "SB")
	private Double sb;
	
	@Column(name = "DIP")
	private Date dip;
	
	@Column(name = "OP")
	@Size(max = 100)
	private String op;
	
	@Column(name = "NACIONALIDADE")
	@Size(max = 60)
	private String nacionalidade;
	
	@Column(name = "MEU_INSS")
	@Size(max = 50)
	private String meu_inss;

	public Long getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Long cliente_id) {
		this.cliente_id = cliente_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgao() {
		return orgao;
	}

	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

	public Integer getEstado_civil() {
		return estado_civil;
	}

	public void setEstado_civil(Integer estado_civil) {
		this.estado_civil = estado_civil;
	}

	public String getConjugue() {
		return conjugue;
	}

	public void setConjugue(String conjugue) {
		this.conjugue = conjugue;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public Integer getAposentado() {
		return aposentado;
	}

	public void setAposentado(Integer aposentado) {
		this.aposentado = aposentado;
	}

	public Integer getTipo_aposenta() {
		return tipo_aposenta;
	}

	public void setTipo_aposenta(Integer tipo_aposenta) {
		this.tipo_aposenta = tipo_aposenta;
	}

	public String getNb() {
		return nb;
	}

	public void setNb(String nb) {
		this.nb = nb;
	}

	public Date getDib() {
		return dib;
	}

	public void setDib(Date dib) {
		this.dib = dib;
	}

	public double getRmi() {
		return rmi;
	}

	public void setRmi(double rmi) {
		this.rmi = rmi;
	}

	public String getTc() {
		return tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEnd_nro() {
		return end_nro;
	}

	public void setEnd_nro(String end_nro) {
		this.end_nro = end_nro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public Date getDdb() {
		return ddb;
	}

	public void setDdb(Date ddb) {
		this.ddb = ddb;
	}

	public Date getDer() {
		return der;
	}

	public void setDer(Date der) {
		this.der = der;
	}

	public Date getDcb() {
		return dcb;
	}

	public void setDcb(Date dcb) {
		this.dcb = dcb;
	}

	public String getAps() {
		return aps;
	}

	public void setAps(String aps) {
		this.aps = aps;
	}

	public String getEsp() {
		return esp;
	}

	public void setEsp(String esp) {
		this.esp = esp;
	}

	public Double getSb() {
		return sb;
	}

	public void setSb(Double sb) {
		this.sb = sb;
	}

	public Date getDip() {
		return dip;
	}

	public void setDip(Date dip) {
		this.dip = dip;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getMeu_inss() {
		return meu_inss;
	}

	public void setMeu_inss(String meu_inss) {
		this.meu_inss = meu_inss;
	}
}