package Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.Date;
import java.util.Formatter;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Entity.Transaction;
import dao.MySQLAccessDao;
import dbConnection.ConnectionFactory;
import dbConnection.MySQLJDBCConnection;


public class Assingment2 {
	public static Connection single_instance;
	static Logger logger = Logger.getLogger("Main");
	static Handler fileHandler = null;
	static SimpleFormatter simpleFormatter = null;
	
	public static Connection getInstance() {
		if (single_instance == null) {
			MySQLJDBCConnection dbConnection = new MySQLJDBCConnection();
			single_instance = dbConnection.setupConnection();
		}

		return single_instance;
	}

	public static void main(String[] args) {
	
		try {
			 //Connection connection = getInstance();
			Assingment2 fw= new Assingment2();
			ConnectionFactory factory = new ConnectionFactory();
			Connection connection = factory.getConnection("mySQLJDBC");     
			fileHandler = new FileHandler("./sampleLogfile.log");
			
			simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
			logger.addHandler(fileHandler);
			 
			fw.DisplayMenu(connection);
			   
		
			if (connection != null) {
				connection.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public static void DisplayMenu(Connection connection) throws ParseException, SQLException {
	        Scanner userInput = new Scanner(System.in);
	        String READ_MENU;	
	        boolean flag;
	       
	        menu();
	        MySQLAccessDao dao = new MySQLAccessDao();
	       
	        READ_MENU = userInput.next();
	        logger.log(Level.INFO, "User Selcted the option "+READ_MENU);
	        Transaction trans = new Transaction();

	        switch (READ_MENU) {
	            case "1":	            
	            	trans = new Transaction();
	            	 logger.log(Level.INFO, "Create Transaction");
	            	System.out.println("Enter Id ");
	            	trans.id= Integer.valueOf(userInput.next());
	            	System.out.println("Enter Name on Card: ");
	            	trans.nameOnCard= userInput.next();
	            	System.out.println("Enter Card Type: ");
	                trans.CardType=userInput.next();
	            	System.out.println("Enter Card Number: ");
	                trans.cardNumber=userInput.next();
	            	System.out.println("Enter Unit price: ");
	            	trans.unitprice= Float.valueOf(userInput.next());
	            	System.out.println("Enter qunatity: ");
	            	trans.quantity=Integer.valueOf(userInput.next());
	            	System.out.println("Enter Total price: ");
	            	trans.TotalPrice= Float.valueOf(userInput.next());
	            	System.out.println("Enter Expiry Date: ");
	            	
	            	String dateOB = userInput.next();	
	            	
					 
					 SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					 Date date = df.parse(dateOB);	
					 df = new SimpleDateFormat("MM/yyyy");
				    trans.ExpDate=  df.format(date).toString();
				    
				    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");  
				    Date date2 = new Date(); 	
				    trans.CreatedOn= formatter2.format(date2);	
  	            	
	            	trans.createdBy= "Admin";
				  try {
					  String error = trans.Validation(trans);
		            	if(error.equals(""))
		            	{
		            	boolean getexistRecord=dao.getTransaction(connection ,Integer.valueOf(trans.id));	
		            	if(getexistRecord== true)
		            	{ 
		            		
		            		System.out.println("There is a record with same transaction Id "+trans.id);
		            		logger.log(Level.INFO,"There is a record with same transaction Id "+trans.id);
		            	}
		            	else {
				        flag=  dao.createTrxns(trans,connection); 				     
				         System.out.println(flag);
				         if(flag== true)
				         { 
				        logger.log(Level.INFO,flag+ "  User Created Successfully");
				         }
				         else
				         {
				        logger.log(Level.INFO,flag + "User Creation failed");
						      
				         }
		            	}
		            	}
		            	else
		            	{ 
		            		logger.log(Level.WARNING,error);
		            		System.out.println(error);
		            		break;
		            	
		            	}
		            	break;
				} catch (SQLException e) {
					
					e.printStackTrace();
					break;
				 }
	             
	            case "2":
	            	 logger.log(Level.INFO, "Read the Transaction record");
	            	trans = new Transaction();
	            	System.out.println("Enter Transaction Id  to view the record");
	            	trans.id= Integer.valueOf(userInput.next());          	
	           	
	            	dao.getTransaction(connection , trans.id);	    			
	    			 		
	                break;
	            case "3": 
	            	 logger.log(Level.INFO, "Update Transaction record");
                     trans = new Transaction();
                     String transid;
                     System.out.println("Enter Transaction id to update the record: ");
                     transid = userInput.next();
                     logger.log(Level.INFO, "User Entered the Transaction Id "+transid+"to update");
                 	boolean getRecord=dao.getTransaction(connection ,Integer.valueOf(transid));	  
                 	if(getRecord== false)
                 	{ 
                 	     logger.log(Level.INFO, "There is no record with the Transaction id "+transid+"to update");
                 		break;
                 	}else
                 	{
                                          
	            	System.out.println("Enter Id ");
	            	trans.id= Integer.valueOf(userInput.next());
	            	System.out.println("Enter Name on Card: ");
	            	trans.nameOnCard=userInput.next();
	            	System.out.println("Enter Card Number: ");
	                trans.cardNumber=(userInput.next());
	                System.out.println("Enter Card Type: ");
	                trans.CardType=(userInput.next());
	            	System.out.println("Enter Unit price: ");
	            	trans.unitprice= Float.valueOf(userInput.next());
	            	System.out.println("Enter qunatity: ");
	            	trans.quantity=Integer.valueOf(userInput.next());
	            	System.out.println("Enter Total price: ");
	            	trans.TotalPrice= Float.valueOf(userInput.next());
	            	System.out.println("Enter Expiry Date: ");
	            	
	            	String dateOBupdate = userInput.next();			
					 
					 SimpleDateFormat dfupdate = new SimpleDateFormat("dd-MM-yyyy");
					 Date dateupdate = dfupdate.parse(dateOBupdate);	
					 dfupdate = new SimpleDateFormat("MM/yyyy");
				    trans.ExpDate=  dfupdate.format(dateupdate).toString();
				    
				    SimpleDateFormat formatterupdate = new SimpleDateFormat("yyyy-MM-dd");  
				    Date createdonUpdate = new Date(); 	
				    trans.CreatedOn= formatterupdate.format(createdonUpdate);	
  	            	
	            	trans.createdBy= "Admin";
	            	
	            	String error = trans.Validation(trans);
	            	if(error.equals(""))
	            	{
	            	flag = dao.updateTransaction(connection, trans);
	            	 if(flag== true)
			         { 
			        logger.log(Level.INFO,flag+ "  User Updated Successfully");
			         }
			         else
			         {
			        logger.log(Level.INFO,flag + " User Updation failed");
					      
			         }
	            	System.out.println(flag);
	                break;
	            	}
	            	else
	            	{  
	            		 logger.log(Level.INFO,error);
	            		System.out.println(error);
	            		break;
	            	
	            	}
                 	}
	            case "4":
	            	 logger.log(Level.INFO, "Delete Transaction record");
	            	trans = new Transaction();
	            	System.out.println("Enter Transaction id to Delete the record: ");
	            	transid = userInput.next();
	            	  logger.log(Level.INFO, "User Entered the Transaction Id "+transid+"to Delete");
	            	boolean getdeleterecord=dao.getTransaction(connection ,Integer.valueOf(transid));	  
                 	if(getdeleterecord== false)
                 	{ 

                	    logger.log(Level.INFO, "There is no record with the Transaction id "+transid+"to delete");
                 		break;
                 	}else
                 	{
                    System.out.println("Are you sure to Delete the record:(Y/N) ");
    	              String confirmTransid = userInput.next();
    	              if(confirmTransid.equals("Y"))
    	              {
	            	   flag = dao.removeTransaction(connection, Integer.valueOf(transid));
	            	   logger.log(Level.INFO, flag+" Record Deleted Succesfully with the Transcationid");
	                 	
	            	   System.out.println(flag);
    	              }
    	              else 
    	              {
    	            	  System.exit(0);
    	              }
    	              }
	                break;
                 	
	            case "5":
	                System.exit(0);
	              //  nextRound=false;
	                break;
	            default:
	                System.out.println("Invalid selection");
	                logger.log(Level.INFO, "Invalid Selection");                 	
	                break; 
	        }
	        }	    

    public static  void menu() {
    	 System.out.println("*****************************************");
	        System.out.println("|       CREDIT CARD TRANSACTIONS           |");
	        System.out.println("*****************************************");
	        System.out.println("| Options:                                 |");
	        System.out.println("|        1. Create Transaction Records     |");
	        System.out.println("|        2. Read Transaction Records       |");
	        System.out.println("|        3. Update Transaction Records     |");
	        System.out.println("|        4. Delete Transaction Records     |");
	        System.out.println("|        5. Exit                        |");
	        System.out.println("*****************************************");

	        System.out.print("Select option: ");

	
}
}
