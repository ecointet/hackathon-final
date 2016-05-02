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
	
		private String DBNAME= "df053859313094d5689c2bf8acf85cad2";
		private String HOSTNAME= "10.0.0.4";
		private String USER = "u9pc7tLiPYlPr";
		private String PASSWORD = "ppx43CvRxSJzH";
		private String PORT= "3306";
		
		
		
		private static final long serialVersionUID = 1L;
		private Connection mySqlConnection;
		private Statement sqlStatement;
		private ResultSet transactionsTable;
		private ResultSet bankAccountTable;
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			String prename;
			String lastname;
			String loginname;
			prename = request.getParameter("prename");
			lastname = request.getParameter("lastname");
			loginname = request.getParameter("login_name");
			
			/*Connect to db*/
			openDbSession();
			/*Check if DB is available & has correct setup*/
			checkDbAvailability();
			/*Do something with DB...*/
			
			try {
				PreparedStatement preparedSqlStatement = mySqlConnection.prepareStatement("INSERT INTO `bank_account` (pre_name, last_name, login_name) VALUES(?,?,?)");
				preparedSqlStatement.setString(1, prename);
				preparedSqlStatement.setString(2, lastname);
				preparedSqlStatement.setString(3, loginname);
				preparedSqlStatement.executeUpdate();
				bankAccountTable = sqlStatement.executeQuery("SELECT * FROM `bank_account`");
				System.out.println("\n Succesfully created account for: \t" +loginname + "\n");
			} catch (SQLException e) {
				System.out.println("\n Failed creating account for: \t" +loginname + "\n \t Reason:\n");
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
				sqlStatement.executeUpdate("CREATE TABLE `bank_account` ( `__account_id` int(11) NOT NULL, `pre_name` text, `last_name` text, `login_name` text, `balance` decimal(60,2) DEFAULT NULL ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
				sqlStatement.executeUpdate("ALTER TABLE `bank_account` MODIFY `__account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2, ADD PRIMARY KEY (`__account_id`), ADD KEY `account_id` (`__account_id`);");		
				bankAccountTable = sqlStatement.executeQuery("SELECT * FROM `bank_account`");
			}catch (SQLException e) {
				System.out.println("\t Skip - Bankaccount table already exists.\n");
			}
			try{
				sqlStatement.executeUpdate("CREATE TABLE `transaction` ( `__transaction_id` int(11) NOT NULL, `source` int(11) DEFAULT NULL, `target` int(11) DEFAULT NULL, `value` int(11) DEFAULT NULL, `purpose` text, `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, `_executive_account_id` int(11) DEFAULT NULL ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
	        	sqlStatement.executeUpdate("ALTER TABLE `transaction` ADD PRIMARY KEY (`__transaction_id`), ADD KEY `transaction_id` (`__transaction_id`), ADD KEY `executive_account_id` (`_executive_account_id`), MODIFY `__transaction_id` int(11) NOT NULL AUTO_INCREMENT, ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`_executive_account_id`) REFERENCES `bank_account` (`__account_id`);");
	        	transactionsTable = sqlStatement.executeQuery("SELECT * FROM `transaction`");
			} catch (SQLException e) {
				System.out.println("\t Skip - Transaction table already exists.\n");
			}
		}
			

}
