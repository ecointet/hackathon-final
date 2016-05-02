package HybridIT;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


public class See_TransactionsServlet extends HttpServlet {
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
		
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			int numberOfColumns = 6; 
			int i;
			
			/*Connect to db*/
			openDbSession();
			/*Check if DB is available & has correct setup*/
			checkDbAvailability();
			/*Do something with DB...*/
			
			output = response.getWriter();
			
			returnSet = "<!DOCTYPE html> <html> <head> <meta charset=\"utf-8\"> <title> Java Demo. </title> <link rel=\"stylesheet\" type=\"text/css\" href=\"/css/index.css\"> </head> <body> <div class = \"box_logo\"> <img src= \"/images/logo.png\" class =\"logo\"/> </div> <div class =\"box_main_part\"><h1> Available transactions on this server: </h1>";
			
			try {
				transactionsTable = sqlStatement.executeQuery("SELECT * FROM `transaction`");
				/*Setup of resulting table... */
				returnSet = returnSet + "<table><tr><td>Transact. ID</td><td>Source</td><td>Target</td><td>Value</td><td>Purpose</td><td>Timestamp</td></tr>";
				/*Go through the table row wise...*/
				while(transactionsTable.next()){
					returnSet = returnSet + "<tr>";
					
					/*Print the value column wise... */
					for(i = 1; i <= numberOfColumns; i++){
						returnSet = returnSet + "<td>" + transactionsTable.getString(i) + "</td>" ;
					}
					
					returnSet = returnSet + "</tr>";
				}
				returnSet = returnSet + "</table>";
			} catch (SQLException e) {
				e.printStackTrace();
				returnSet = returnSet + "<br/><br/>Failed to retrieve data<br/><br/>";
			}
			
			returnSet = returnSet + "<a href=\"/home.jsp\"><button class =\"buttons\" >Back to start</button></a>";
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
