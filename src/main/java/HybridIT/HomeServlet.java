package HybridIT;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class HomeServlet extends HttpServlet {
		private PrintWriter output;
		private String returnSet;
	
		private String DBNAME= "d9d0a933534f0489bb81dbaa9da916c19";
		private String HOSTNAME= "10.0.0.4";
		private String USER = "uRMkUcYBHJVwh";
		private String PASSWORD = "pBoATDqho8IFh";
		private String PORT= "3306";
		
		
		
		private static final long serialVersionUID = 1L;
		private Connection mySqlConnection;
		private Statement sqlStatement;
		private ResultSet transactionsTable;
		private ResultSet bankAccountTable;
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			String name;
			String email;
			String pwd;
			String balance;
			name = request.getParameter("name");
			email = request.getParameter("email");
			pwd = request.getParameter("pwd");
			balance = request.getParameter("balance");
			/*Connect to db*/
			openDbSession();
			/*Check if DB is available & has correct setup*/
			checkDbAvailability();
			/*Do something with DB...*/
			
			try {
				PreparedStatement preparedSqlStatement = mySqlConnection.prepareStatement("INSERT INTO `bank_account` (name, email, pwd, balance) VALUES(?,?,?,?)");
				preparedSqlStatement.setString(1, name);
				preparedSqlStatement.setString(2, email);
				preparedSqlStatement.setString(3, pwd);
				preparedSqlStatement.setString(4, balance);
				preparedSqlStatement.executeUpdate();
				bankAccountTable = sqlStatement.executeQuery("SELECT * FROM `bank_account`");
				System.out.println("\n Succesfully created account for: \t" +name + "\n");
			} catch (SQLException e) {
				System.out.println("\n Failed creating account for: \t" +name + "\n \t Reason:\n");
				e.printStackTrace();
			}
			
			output = response.getWriter();
			returnSet = "<!DOCTYPE html> <html> <head> <meta charset=\"utf-8\"> <title> Java Demo. </title> <link rel=\"stylesheet\" type=\"text/css\" href=\"/css/index.css\"> </head> <body> <div class = \"box_logo\"> <img src= \"/images/logo.png\" class =\"logo\"/> </div> <div class =\"box_main_part\">";
			returnSet = returnSet + " <h1> Welcome to this Java Web MySQL demo! </h1><a href = \"/do_transaction.jsp\"><button class =\"buttons\" >Do a transaction</button></a><br/><br/> <form action=\"see_transactions\" method=\"get\"><button class =\"buttons\" >See all transactions on this server</button></form><form action=\"see_bankaccounts\" method=\"get\"><button class=\"buttons\">See all bank accounts on this server</button></form>";
			
			
			returnSet = returnSet + "</div></body></html>";
			output.println(returnSet);
		}
		
		private void openDbSession(){
	       System.out.println("\n Trying to connect to DB. \n");
			
			try {
				System.out.println("\n'Install driver... \n");
	            Class.forName("com.mysql.jdbc.Driver");
	            
	            System.out.println("\n Logging on... \n");
	            /*Initialize the main JDBC variable...*/
	            mySqlConnection = DriverManager.getConnection("jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DBNAME, USER, PASSWORD);
	            sqlStatement = mySqlConnection.createStatement();
			}catch (Exception ex) {
				System.out.println("\t Failed for following reasons:\n");
				ex.printStackTrace();
			}
		}
		
		private void checkDbAvailability(){
			try{
				sqlStatement.executeUpdate("CREATE TABLE `bank_account` (\n" +
						"  `id` int(11) NOT NULL  AUTO_INCREMENT,\n" +
						"  `name` varchar(30) NOT NULL,\n" +
						"  `email` varchar(30) NOT NULL,\n" +
						"  `pwd` varchar(15) NOT NULL,\n" +
						"  `balance` float NOT NULL DEFAULT '0',\n" +
						"\tPRIMARY KEY (ID)\n" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Information regarding the users and credentials';");
				bankAccountTable = sqlStatement.executeQuery("SELECT * FROM `bank_account`");
			}catch (SQLException e) {
				System.out.println("\t Skip - Bankaccount table already exists.\n");
			}
			try{
				sqlStatement.executeUpdate("CREATE TABLE `bank_transac` (\n" +
						"  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id transac',\n" +
						"  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Autofill Timestamp',\n" +
						"  `amount` int(11) NOT NULL,\n" +
						"  `from_id` int(11) NOT NULL COMMENT 'ID account from',\n" +
						"  `to_id` int(11) NOT NULL COMMENT 'ID account to',\n" +
						"  PRIMARY KEY (ID)\n" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='All bank transactions';");
	        	transactionsTable = sqlStatement.executeQuery("SELECT * FROM `bank_transac`");
			} catch (SQLException e) {
				System.out.println("\t Skip - Transaction table already exists.\n");
			}
		}


}
