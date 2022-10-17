package br.com.hotel.dto;

public class ReservasDTO {
	
	private int id;
	private String dataEntrada;
	private String dataSaida;
	private Double valor;
	private String formaPagamento;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public String toString() {
		return getId() + " | " + getDataEntrada() + " | " + getDataSaida() + " | " + getValor() + " | " + getFormaPagamento(); 
	}
	
}
