package jdbcconnection;

import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.SQLException;

public class Transaction {

    private static final String url =
            "jdbc:mysql://localhost:3306/lenden";   // db is lenden now that we are using
    private static final String username = "root";
    private static final String password = "75677567";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
        	Connection connection = DriverManager.getConnection(url,username, password);
        	connection.setAutoCommit(false);  // by deafult true hota hai 
        	
        	String debit_query = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
        	String credit_query = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
        	PreparedStatement debitPreparedStatement = connection.prepareStatement(debit_query);
        	PreparedStatement creditPreparedStatement = connection.prepareStatement(credit_query);
        	
        	Scanner sc = new Scanner(System.in);
        	System.out.println("Enter Account number: ");
        	int account_number = sc.nextInt();
        	
        	System.out.println("Enter Amount: ");
        	double amount = sc.nextDouble();
        	
        	debitPreparedStatement.setDouble(1, amount);
        	debitPreparedStatement.setInt(2, account_number);
        	
        	creditPreparedStatement.setDouble(1, amount);
        	creditPreparedStatement.setInt(2, 11);
        	
            debitPreparedStatement.executeUpdate();
            creditPreparedStatement.executeUpdate();
        	
        	if(isSufficient(connection, account_number, amount)) {
        		connection.commit();    // commit
        		System.out.println("Transaction Successfull");
        	}else {
        		connection.rollback();    // rollback
        		System.out.println("Transaction Failed!!!");
        		
        	}
       	
        	debitPreparedStatement.close();
        	debitPreparedStatement.close();
        	connection.close();
        	sc.close();
        	
        	
        	 	
        } catch(SQLException e) {
        	System.out.println(e.getMessage());
        }
        
//    
    }
    
    // check suffient balance after debit here
    static boolean isSufficient(Connection connection, int account_number, double amount) {
        try {
            String query = "SELECT balance FROM accounts WHERE account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, account_number);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double current_balance = resultSet.getDouble("balance");
                if (amount > current_balance) {
                    return false;
                } else {
                    return true;
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    
}
