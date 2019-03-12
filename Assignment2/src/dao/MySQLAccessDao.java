package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

import Entity.Transaction;


public class MySQLAccessDao {

	
	
	public boolean getTransaction(Connection connection,int id) {
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from java.transaction where Id="+id);
			
			if(resultSet.next()){
		     System.out.println("--------Transaction Data------");	
			System.out.println("ID -"+resultSet.getString("ID"));
			System.out.println("Name on Card -"+resultSet.getString("NameOnCard"));
			System.out.println("Card NUmber -"+resultSet.getString("CardNumber"));
			System.out.println("Card Type -"+resultSet.getString("CardType"));
			System.out.println("Unit Price -"+resultSet.getString("UnitPrice"));
			System.out.println("Qunatity -"+resultSet.getString("Quantity"));
			System.out.println("Total Price -"+resultSet.getString("Totalprice"));
			System.out.println("ExpDate"+resultSet.getString("ExpDate"));
			System.out.println("CreatedOn -"+resultSet.getString("CreatedOn"));
			System.out.println("CreatedBy -"+resultSet.getString("CreatedBy"));
			System.out.println("---------------");
						
			}			
			else {
				System.out.println("No records found with this transaction ID");
				return false;
			}		

		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			statement = null;
			resultSet = null;
		}
	 return true;
	}

	public boolean createTrxns(Transaction result,Connection connection) throws SQLException {
		   
		boolean flag= false;
		Statement statement = null;
		int resultSet ;
			statement = connection.createStatement();
			resultSet = statement.executeUpdate("INSERT  INTO  java.transaction "
					+ "(ID, NameOnCard, CardNumber,CardType,UnitPrice,Quantity,TotalPrice,ExpDate,CreatedOn,CreatedBy)"
					+ " values ('" +result.id+"','"+result.nameOnCard+"','"+result.cardNumber+"','"+ result.CardType+"','"+result.unitprice+"','" 
					              +result.quantity+"','"+result.TotalPrice+"','"+result.ExpDate+"','"+result.CreatedOn+"','"+result.createdBy+"')");
			
			if(resultSet==1)
			{ 
				System.out.println("Transaction created Succesfully");
			  
				flag= true;
			}

			 return flag;
			 
		}	

	
	public boolean updateTransaction(Connection connection,Transaction trxn) throws SQLException {
		boolean update = false;
		Statement statement = null;
		int resultSet ;
		statement = connection.createStatement();
		resultSet= statement.executeUpdate("UPDATE java.transaction SET ID = '" + trxn.id + "',NameOnCard = '" + trxn.nameOnCard + 
				"',CardNumber = '" + trxn.cardNumber +"',CardType = '" + trxn.CardType + "',UnitPrice = '"
				+ trxn.unitprice + "',Quantity = '" + trxn.quantity + "',TotalPrice = '" + trxn.TotalPrice + 
				"',ExpDate = '" + trxn.ExpDate + "',Createdon = '" + trxn.CreatedOn+"',CreatedBy='"+trxn.createdBy+"' WHERE ID = '" + trxn.id+"'");	
			
		if(resultSet==1)
		{
			update= true;
		}

		
		return update;
		
	} 
	
	public boolean removeTransaction(Connection connection,int trxnid) throws SQLException {
		boolean delete = false;
		Statement statement = null;
		int resultSet ;
		statement = connection.createStatement();
		resultSet= statement.executeUpdate("DELETE FROM java.transaction WHERE ID =" + trxnid);
			
		if(	resultSet==1)
		{
			delete= true;
		}

		
		return delete;
		
	} 

}
