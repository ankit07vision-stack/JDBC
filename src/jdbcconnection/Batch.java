package jdbcconnection;

import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.SQLException;

public class Batch {

    private static final String url =
            "jdbc:mysql://localhost:3306/mydb";
    private static final String username = "root";
    private static final String password = "75677567";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
        
//     Connection connection = DriverManager.getConnection(url, username, password);
            
            // using statement 🤩🤩🤩🤩
            
//            Statement statement = connection.createStatement();  
//            Scanner sc = new Scanner(System.in);
//            while(true) {
//            	System.out.print("Enter name: ");
//            	String name = sc.next();
//            	System.out.print("Enter age: ");
//            	int age = sc.nextInt();
//            	System.out.print("Enter course: ");
//            	String course = sc.next();
//            	System.out.print("Enter city: ");
//            	String city = sc.next();
//            	
//            	System.out.print("Enter more data(Y/N): ");
//            	String choice = sc.next();
//            	
//                String query = String.format("INSERT INTO student(name, age,course, city) VALUES('%s',%d,'%s','%s')",name, age, course,city);  
//                
//                statement.addBatch(query);
//            	
//            	if(choice.toUpperCase().equals("N")) {
//            		break;
//            	} 	
//            }
//            
//            int[] arr = statement.executeBatch();
//            
//            for(int i=0; i<arr.length; i++) {
//            	if(arr[i] == 0) {
//            		System.out.println("Query: "+i + "not executed Successfully!!");
//            	}
//            }
           
            
            // using prepared statement 🤩🤩🤩🤩
            
         Connection connection = DriverManager.getConnection(url, username, password);
         
         String query = "INSERT INTO student(name, age,course, city) VALUES(?,?,?,?)";  // here some change in prepared from normal statement earlier 

         PreparedStatement preparedStatement = connection.prepareStatement(query);
          Scanner sc = new Scanner(System.in);
          while(true) {
          	System.out.print("Enter name: ");
          	String name = sc.next();
          	System.out.print("Enter age: ");
          	int age = sc.nextInt();
          	System.out.print("Enter course: ");
          	String course = sc.next();
          	System.out.print("Enter city: ");
          	String city = sc.next();
          	
          	System.out.print("Enter more data(Y/N): ");
          	String choice = sc.next();
          	
          	preparedStatement.setString(1, name);
          	preparedStatement.setInt(2, age);
          	preparedStatement.setString(3, course);
          	preparedStatement.setString(4, city);
          	
              
          	preparedStatement.addBatch();
          	
          	if(choice.toUpperCase().equals("N")) {
          		break;
          	} 	
          }
          
          int[] arr = preparedStatement.executeBatch();
          
          for(int i=0; i<arr.length; i++) {
          	if(arr[i] == 0) {
          		System.out.println("Query: "+i + "not executed Successfully!!");
          	}
          }
            
            
         
          
            System.out.println("MySQL connected successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
