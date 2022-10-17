package br.com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.postgresql.util.PSQLException;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import br.com.hotel.dto.ReservasDTO;
import br.com.hotel.jdbc.ConexaoUtil;
import views.telaCarregamento;


public class ReservasDAO {
	
	public void inserir(ReservasDTO reservaDTO) {
		try {
		Connection connection = ConexaoUtil.getInstance().getConnection();
		
		String sql = "INSERT INTO reservas(DataEntrada,DataSaida,Valor,FormaPagamento) VALUES(?::date,?::date,?,?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, reservaDTO.getDataEntrada());
		statement.setString(2, reservaDTO.getDataSaida());
		statement.setDouble(3, reservaDTO.getValor());
		statement.setString(4, reservaDTO.getFormaPagamento());
		
		statement.execute();
		connection.close();
		
		System.out.println("Reserva feita com Sucesso");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void remover(int id) {
		try {
			//REMOVER O HOSPEDE VINCULADA A RESERVA ANTES
			HospedesDAO hospede = new HospedesDAO();
			hospede.remover(id);
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "DELETE FROM reservas WHERE Id = ?";
			
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			
			statement.setInt(1, id);
			statement.execute();
			
			connection.close();
			
			System.out.println("Reserva removida com Sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ReservasDTO> listarTodos () {
		List<ReservasDTO> listaReservas = new ArrayList<ReservasDTO>();
		
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM reservas";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet resultset = statement.executeQuery();
			
			while(resultset.next()) {
				ReservasDTO reservaDTO = new ReservasDTO();
				reservaDTO.setId(resultset.getInt("Id"));
				reservaDTO.setDataEntrada(resultset.getString("DataEntrada"));
				reservaDTO.setDataSaida(resultset.getString("DataSaida"));
				reservaDTO.setValor(resultset.getDouble("Valor"));
				reservaDTO.setFormaPagamento(resultset.getString("FormaPagamento"));
				
				
				listaReservas.add(reservaDTO);
			}
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaReservas;
	}
	
	public void atualizar(String Id, String Campo, String Valor,DefaultTableModel modelo) {
		
		try {
			if(Id=="Número de Reserva") {
				return;
			}
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "";
			
			if(Campo == "DataEntrada") {
				sql = "UPDATE reservas SET DataEntrada = ?::date WHERE Id = ?";
			}
			
			if(Campo == "DataSaida") {
				sql = "UPDATE reservas SET DataSaida = ?::date WHERE Id = ?";
			}
			
			if(Campo == "Valor") {
				sql = "UPDATE reservas SET Valor = ? WHERE Id = ?";
			}
			
			if(Campo == "FormaPagamento") {
				sql = "UPDATE reservas SET FormaPagamento = ? WHERE Id = ?";
			}
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			if(Campo == "Valor") {
				statement.setFloat(1, Float.parseFloat(Valor));
			}else {
				statement.setString(1, Valor);
			}
			
			statement.setInt(2, Integer.parseInt(Id));
			
			
			statement.execute();
			
			connection.close();
		}catch(PSQLException e) {
			JOptionPane.showInternalMessageDialog(null, "DATA INVÁLIDA!", "ERROR", 0);
			ReservasDAO reservaDAO = new ReservasDAO();
			reservaDAO.limpar(modelo);
			reservaDAO.buscarReserva(modelo);
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//RETORNA O NUMERO DA ÚLTIMA RESERVA FEITA
	public int ultimoIdReserva() {
		int reserva = 0;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT Id FROM reservas ORDER BY Id DESC LIMIT 1;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet resultset = statement.executeQuery();
			
			if(resultset.next()) {
				
				reserva = resultset.getInt("Id");
			}
			
			
			
			connection.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reserva;
	}
	
	//ADICIONA TODOS OS VALORES QUE ESTÁ NO BANCO DE DADOS PARA TABELA
	public void buscarReserva(DefaultTableModel modelo) {
		try {
		ReservasDAO reservaDAO = new ReservasDAO();
		List<ReservasDTO> listaReservas = new ArrayList<>();
		listaReservas = reservaDAO.listarTodos();
		for(ReservasDTO i : listaReservas) {
			
			modelo.addRow(new Object[]{i.getId(), i.getDataEntrada(),i.getDataSaida(), "R$ " + i.getValor(),i.getFormaPagamento()});
		}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	//BUSCAR A RESERVA PELO ID
	public void  pesquisaReserva(DefaultTableModel modelo,String Id) {
		
			List<ReservasDTO> listaReservas = new ArrayList<ReservasDTO>();
			
			try {
				Connection connection = ConexaoUtil.getInstance().getConnection();
				
				String sql = "SELECT * FROM reservas WHERE Id = ?";
				
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, Integer.parseInt(Id));
				
				ResultSet resultset = statement.executeQuery();
				
				while(resultset.next()) {
					ReservasDTO reservaDTO = new ReservasDTO();
					reservaDTO.setId(resultset.getInt("Id"));
					reservaDTO.setDataEntrada(resultset.getString("DataEntrada"));
					reservaDTO.setDataSaida(resultset.getString("DataSaida"));
					reservaDTO.setValor(resultset.getDouble("Valor"));
					reservaDTO.setFormaPagamento(resultset.getString("FormaPagamento"));
					
					
					listaReservas.add(reservaDTO);
				}
				connection.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//REMOVE TODAS AS LINHAS MENOS A PRIMEIRA DOS TITULOS DAS COLUNAS
			ReservasDAO reservaDAO = new ReservasDAO();
			reservaDAO.limpar(modelo);
			
			//CASO NÃO EXISTA O VALOR PESQUISADO NO BD
			if(listaReservas.size() == 0) {
				JOptionPane.showInternalMessageDialog(null, "NÚMERO DE RESERVA inexistente no Banco de Dados do Hotel!", "Sem Resultados", 1);
				reservaDAO.limpar(modelo);
				reservaDAO.buscarReserva(modelo);
			}
			//CARREGA O VALOR QUE TEM O ID DA BUSCA
			for(ReservasDTO i : listaReservas) {
				
				modelo.addRow(new Object[]{i.getId(), i.getDataEntrada(),i.getDataSaida(), "R$ " + i.getValor(),i.getFormaPagamento()});
			}
			
		
		
	}
	//APAGA TODOS OS VALORES DA TABELA
	public void limpar(DefaultTableModel modelo) {
		int indice = modelo.getRowCount();
		while(indice != 1) {
			modelo.removeRow(indice-1);
			indice--;
		}
	}
	
	
	
}



