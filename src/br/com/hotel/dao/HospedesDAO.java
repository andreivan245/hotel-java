package br.com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.postgresql.util.PSQLException;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import br.com.hotel.dto.HospedesDTO;
import br.com.hotel.dto.ReservasDTO;
import br.com.hotel.jdbc.ConexaoUtil;
import views.telaCarregamento;

public class HospedesDAO {
	public void inserir(HospedesDTO hospedeDTO) {
		try {
		Connection connection = ConexaoUtil.getInstance().getConnection();
		
		String sql = "INSERT INTO hospedes(Nome,Sobrenome,DataNascimento,Nacionalidade,Telefone, IdReserva) VALUES(?,?,?::date,?,?,?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, hospedeDTO.getNome());
		statement.setString(2, hospedeDTO.getSobrenome());
		statement.setString(3, hospedeDTO.getDataNascimento());
		statement.setString(4, hospedeDTO.getNacionalidade());
		statement.setString(5, hospedeDTO.getTelefone());
		statement.setInt(6, hospedeDTO.getIdReserva());
		
		statement.execute();
		connection.close();
		
		System.out.println("Hospede adicionado com Sucesso");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void remover(int id) {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "DELETE FROM hospedes WHERE IdReserva = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			statement.execute();
			connection.close();
			
			
			System.out.println("Hospede removido com Sucesso");
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<HospedesDTO> listarTodos () {
		List<HospedesDTO> listaHospedes = new ArrayList<HospedesDTO>();
		
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM hospedes";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet resultset = statement.executeQuery();
			
			while(resultset.next()) {
				HospedesDTO hospedeDTO = new HospedesDTO();
				hospedeDTO.setId(resultset.getInt("Id"));
				hospedeDTO.setNome(resultset.getString("Nome"));
				hospedeDTO.setSobrenome(resultset.getString("Sobrenome"));
				hospedeDTO.setDataNascimento(resultset.getString("DataNascimento"));
				hospedeDTO.setNacionalidade(resultset.getString("Nacionalidade"));
				hospedeDTO.setTelefone(resultset.getString("Telefone"));
				hospedeDTO.setIdReserva(resultset.getInt("IdReserva"));
				
				
				listaHospedes.add(hospedeDTO);
			}
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaHospedes;
	}
	
	public void atualizar(String Id, String Campo, String Valor,DefaultTableModel modeloHospedes) {
		
		try {
			
			if(Id=="Número Hóspede") {
				return;
			}
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "";
			
			if(Campo == "Nome") {
				sql = "UPDATE hospedes SET Nome = ? WHERE Id = ?";
			}
			if(Campo == "Sobrenome") {
				sql = "UPDATE hospedes SET Sobrenome = ? WHERE Id = ?";
			}
			if(Campo == "DataNascimento") {
				sql = "UPDATE hospedes SET DataNascimento = ?::date WHERE Id = ?";
			}
			if(Campo == "Nacionalidade") {
				sql = "UPDATE hospedes SET Nacionalidade = ? WHERE Id = ?";
			}
			if(Campo == "Telefone") {
				sql = "UPDATE hospedes SET Telefone = ? WHERE Id = ?";
			}
			if(Campo == "IdReserva") {
				sql = "UPDATE hospedes SET IdReserva = ? WHERE Id = ?";
			}
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			if(Campo == "IdReserva") {
				statement.setInt(1, Integer.parseInt(Valor));
			}else {
				statement.setString(1, Valor);
			}
			
			
			statement.setInt(2, Integer.parseInt(Id));
			
			statement.execute();
			connection.close();
		}catch(PSQLException e) {
			JOptionPane.showInternalMessageDialog(null, "DATA INVÁLIDA!", "ERROR", 0);
			HospedesDAO hospedeDAO = new HospedesDAO();
			hospedeDAO.limpar(modeloHospedes);
			hospedeDAO.buscarHospedes(modeloHospedes);
			e.printStackTrace();
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
}
	public void buscarHospedes(DefaultTableModel modeloHospedes) {
		HospedesDAO hospedeDAO = new HospedesDAO();
		List<HospedesDTO> listaHospedes = new ArrayList<>();
		listaHospedes= hospedeDAO.listarTodos();
		for(HospedesDTO i : listaHospedes) {
			
			modeloHospedes.addRow(new Object[]{i.getId(), i.getNome(),i.getSobrenome(), i.getDataNascimento(), i.getNacionalidade(), i.getTelefone(), i.getIdReserva()});
		}
	}
	
	public void  pesquisaHospedes(DefaultTableModel modeloHospedes,String sobrenome) {
		
		List<HospedesDTO> listaHospedes = new ArrayList<HospedesDTO>();
		
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM hospedes WHERE Sobrenome = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, sobrenome);
			
			ResultSet resultset = statement.executeQuery();
			
			while(resultset.next()) {
				HospedesDTO hospedeDTO = new HospedesDTO();
				hospedeDTO.setId(resultset.getInt("Id"));
				hospedeDTO.setNome(resultset.getString("Nome"));
				hospedeDTO.setSobrenome(resultset.getString("Sobrenome"));
				hospedeDTO.setDataNascimento(resultset.getString("DataNascimento"));
				hospedeDTO.setNacionalidade(resultset.getString("Nacionalidade"));
				hospedeDTO.setTelefone(resultset.getString("Telefone"));
				hospedeDTO.setIdReserva(resultset.getInt("IdReserva"));
				
				
				listaHospedes.add(hospedeDTO);
			}
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//REMOVE TODAS AS LINHAS MENOS A PRIMEIRA DOS TITULOS DAS COLUNAS
		HospedesDAO hospedesDAO = new HospedesDAO();
		hospedesDAO.limpar(modeloHospedes);
		
		//CASO NÃO EXISTA O VALOR PESQUISADO NO BD
		if(listaHospedes.size() == 0) {
			JOptionPane.showInternalMessageDialog(null, "SOBRENOME inexistente no Banco de Dados do Hotel!", "Sem Resultados", 1);
			hospedesDAO.limpar(modeloHospedes);
			hospedesDAO.buscarHospedes(modeloHospedes);
		}
		
		//CARREGA O VALOR QUE TEM O ID DA BUSCA
		for(HospedesDTO i : listaHospedes) {
			
			modeloHospedes.addRow(new Object[]{i.getId(), i.getNome(),i.getSobrenome(), i.getDataNascimento(), i.getNacionalidade(), i.getTelefone(), i.getIdReserva()});
			
		}
		
	
	
}
	public void limpar(DefaultTableModel modeloHospedes) {
		int indice = modeloHospedes.getRowCount();
		while(indice != 1) {
			modeloHospedes.removeRow(indice-1);
			indice--;
		}
	}
	
	

}
