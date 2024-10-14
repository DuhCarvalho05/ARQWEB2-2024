package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.Customer;

public class CustomerDao {
		
	private DataSource dataSource;
	
	public CustomerDao(DataSource ds) {
		this.dataSource = ds;
	}
	
	public Boolean save(Customer customer) throws SQLException {
		if(customer == null) {
			return false;
		}
		String sql = "INSERT INTO CUSTOMERS(NOME, EMAIL, TELEFONE, CPF) VALUES (?,?,?,?);";
		try(Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getMail());
			ps.setString(3, customer.getPhone());
			ps.setString(4, customer.getCPF());
			ps.executeUpdate();
		}
		return true;
			
	}

}
