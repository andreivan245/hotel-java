package br.com.hotel.dto;

public class HospedesDTO {
	
	private int id;
	private String nome;
	private String sobrenome;
	private String dataNascimento;
	private String nacionalidade;
	private String telefone;
	private int idReserva;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	
	public String toString() {
		return getId() + " | " + getNome() + " | " + getSobrenome() + " | " + getDataNascimento() + " | " + getNacionalidade() + " | " + getTelefone() + " | " + getIdReserva(); 
	}
}
