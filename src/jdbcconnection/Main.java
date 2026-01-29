package jdbcconnection;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {

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
            Connection connection =
                    DriverManager.getConnection(url, username, password);

//            Statement statement =  connection.createStatement();
            
            // SELECT OR FETCH DATA
            
//            String query = "select * from student";
//            ResultSet resultSet = statement.executeQuery(query);
//            while(resultSet.next()) {
//            	int id = resultSet.getInt("id");
//            	String name  = resultSet.getString("name");
//            	int age = resultSet.getInt("age");
//            	
//            	System.out.println("ID: "+id);
//            	System.out.println("NAME: "+name);
//            	System.out.println("AGE: "+age); 		
//            }
            
            
            // INSERT
            
//            String query = String.format("INSERT INTO student(name, age) VALUES('%s', '%o')", "Rahul", 23);       		
//            int rowsAffected = statement.executeUpdate(query);
//            if(rowsAffected>0) {
//            	System.out.println("Data Inserted Successfully");
//            }
//            else {
//            	System.out.println("Data Not Inserted ");
//            }
            
            
            // UPDATE
            
//            String query = String.format("UPDATE student SET age = %d WHERE id =%d", 24, 1);  
//            int rowsAffected = statement.executeUpdate(query);
//          if(rowsAffected>0) {
//          	System.out.println("Data Updated Successfully");
//          }
//          else {
//          	System.out.println("Data Not Updated ");
//          }
            
            //DELETE
            
//            String query = "DELETE FROM student WHERE ID =2";
//            int rowsAffected = statement.executeUpdate(query);
//            if(rowsAffected>0) {
//            	System.out.println("Data DELETED Successfully");
//            }
//            else {
//            	System.out.println("Data Not DELETED ");
//             }
            
            
            // Prepared Stmt. 😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎
            
            // INSERT PREPARED STMT. 
            
//          String query = "INSERT INTO student(name, age,course, city) VALUES(?,?,?,?)";  
//          
//          Statement statement = connection.createStatement();
//          PreparedStatement preparedStatement = connection.prepareStatement(query);
//          preparedStatement.setString(1, "Shanti");
//          preparedStatement.setInt(2, 20);
//          preparedStatement.setString(3, "C.S.E");
//          preparedStatement.setString(4, "Mumbai");
//          
//          
//          int rowsAffected = preparedStatement.executeUpdate();
//          if(rowsAffected>0) {
//          	System.out.println("Data Inserted Successfully");
//          }
//          else {
//          	System.out.println("Data Not Inserted ");
//          }
          
          
          // SELECT OR FIND AGE
            
//          String query = "SELECT age FROM student WHERE id = ?";  
//          PreparedStatement preparedStatement = connection.prepareStatement(query);
//          preparedStatement.setInt(1, 1);  // first 1 number of ? and second 1 -> id 
//          ResultSet resultSet = preparedStatement.executeQuery();
//          
//          if(resultSet.next()) {
//        	  int age = resultSet.getInt("age");
//        	  System.out.println("Age:"+ age);
//          }
//          else {
//        	  System.out.println("Age not found");
//          }
            
            // UPDATE
            
            String query = "UPDATE student SET age = ? WHERE id= ?";  
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 30);  // first 1 number of ? and second 30 -> age 
            preparedStatement.setInt(2, 6);  // first 1 number of ? and second 1 -> id 

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected>0) {
            	System.out.println("Data Updated Successfully");
            }
            else {
        	  System.out.println("Data Not Updated ");
             }
            
            
            System.out.println("MySQL connected successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
