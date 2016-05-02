package HybridIT;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class Succesfully_SubmittedServlet extends HttpServlet {
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
		private DatabaseMetaData metaInformation;
		private ResultSet bankAccountTable;
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			String source;
			String target;
			String value;
			String purpose;
			source = request.getParameter("source");
			target = request.getParameter("target");
			value = request.getParameter("value");
			purpose = request.getParameter("purpose");
			
			/*Connect to db*/
			openDbSession();
			/*Check if DB is available & has correct setup*/
			checkDbAvailability();
			/*Do something with DB...*/
			
			try {
				PreparedStatement preparedSqlStatement = mySqlConnection.prepareStatement("INSERT INTO `transaction` (source, target, value, purpose) VALUES(?,?,?,?)");
				preparedSqlStatement.setString(1, source);
				preparedSqlStatement.setString(2, target);
				preparedSqlStatement.setString(3, value);
				preparedSqlStatement.setString(4, purpose);
				preparedSqlStatement.executeUpdate();
				transactionsTable = sqlStatement.executeQuery("SELECT * FROM `transaction`");
				System.out.println("\n Succesfully transmitted transaction for: \t" + source + "\t to: \t" + target + "\n");
			} catch (SQLException e) {
				System.out.println("\n Failed creating transaction for: \t" + source + "\t to: \t" + target + "\n");
				e.printStackTrace();
			}
			
			
			output = response.getWriter();
			returnSet = "<!DOCTYPE html> <html> <head> <meta charset=\"utf-8\"> <title> Java Demo. </title> <link rel=\"stylesheet\" type=\"text/css\" href=\"/css/index.css\"> </head> <body> <div class = \"box_logo\"> <img src= \"/images/logo.png\" class =\"logo\"/> </div> <div class =\"box_main_part\">";
			returnSet = returnSet + "<h1> Succesfully submitted. </h1> What do you want to do? <br/><br/> <form action=\"do_transaction\" method=\"post\" ><button class =\"buttons\" >Do a transaction!</button></form> <br/><br/><br/> <form action=\"see_transactions\" method=\"get\"> Want to see all transactions on this server? <br/><button class =\"buttons\" >See all transactions on this server</button></form> <form action=\"see_bankaccounts\" method=\"get\"><button class=\"buttons\">See all bank accounts on this server</button></form><a href=\"/home.jsp\"><button class =\"buttons\" >Back to Start</button></a> ";
			returnSet = returnSet + "</div></body></html>";
			output.println(returnSet);
		}
		
		/*public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			output = response.getWriter();
			returnSet = "<!DOCTYPE html> <html> <head> <meta charset=\"utf-8\"> <title> Java Demo. </title> <link rel=\"stylesheet\" type=\"text/css\" href=\"/css/index.css\"> </head> <body> <div class = \"box_logo\"> <img src= \"/images/logo.png\" class =\"logo\"/> </div> <div class =\"box_main_part\">";
			returnSet = returnSet + "<h1> Succesfully submitted. </h1> What do you want to do? <br/><br/> <form action=\"do_transaction\" method=\"post\" ><button class =\"buttons\" >Do a transaction!</button></form> <br/><br/><br/> <form action=\"succesfully_submitted\" method=\"get\"> Want to retrieve your data? <br/> <button class =\"buttons\" >See all transactions on this server</button></form> <a href=\"/home.jsp\"><button class =\"buttons\" >Back to Start</button></a> ";
			returnSet = returnSet + "</div></body></html>";
			output.println(returnSet);
		}*/
	
		
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
