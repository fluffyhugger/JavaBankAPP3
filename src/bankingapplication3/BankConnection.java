package bankingapplication3;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sirapob
 */
public class BankConnection {
    public static Connection connect(){
        String URL = "jdbc:mysql://localhost:3306/mydb3";
        String username = "root";
        String password = "147258369";
        Connection connection = null;
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL,username,password);
        }catch (ClassNotFoundException ex){
            Logger.getLogger(BankConnection.class.getName()).log(Level.SEVERE, null, ex);
        }catch (SQLException ex) {
            Logger.getLogger(BankConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}