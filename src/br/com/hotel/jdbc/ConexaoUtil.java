package br.com.hotel.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoUtil {
	private static ConexaoUtil conexaoUtil;
	
	public static ConexaoUtil getInstance() {
		if (conexaoUtil== null) {
			conexaoUtil = new ConexaoUtil();
		}
		return conexaoUtil;
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		try {
			 Class.forName("org.postgresql.Driver");
				
			return DriverManager.getConnection("jdbc:postgresql://"+"ec2-44-199-143-43.compute-1.amazonaws.com/"+"d71v4f9490jlen","pvwfejbkptjtht","2ffbdd0952f89aa8211ba2cb38c584318e1280fe262734648b9261452f47f00c");
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null, "Falha na conexão com o servidor", "ERRO", 0);
			e.printStackTrace();
			
		}
		return null;
	}
	
//	public static void main(String[]args) {
//		try {
//			System.out.println(getInstance().getConnection());
//		} catch (Exception e) {
//			JOptionPane.showInternalMessageDialog(null, "Falha na conexão com o servidor", "ERRO", 0);
//			e.printStackTrace();
//		} 
//	}
}
