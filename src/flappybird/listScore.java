/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.sql.*;
import java.sql.Statement;
import java.io.*;
/**
 *
 * @author Sang
 */
public class listScore {
    
    
        
    
    public void saveScore(String s,int Point){
        
        Connection conn = null;
        Statement stmt = null;
    
        String diem = ""+Point;
            
            try{
             // Mo mot ket noi
              
              conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName=Flappybird; user=sa; password=201289;");
              
              
              //Thuc hien 1 truy van
              stmt = conn.createStatement();
              String sql = "INSERT INTO Highscore VALUES (" + " '"+ s +"','"+ diem +"' );";
              stmt.executeUpdate(sql);
              
              conn.close();
              stmt.close();
          
         }         
         catch(SQLException se){}
        
    }
    
    
    public void showScore(Graphics2D g2){
        
      
      Connection conn = null;
      Statement stmt = null;
      
          
         try{
             // Mo mot ket noi
              
              conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName=Flappybird; user=sa; password=201289;");
              
              
              //Thuc hien 1 truy van
              stmt = conn.createStatement();
              String sql = "SELECT Top 5* FROM Highscore order by Score desc;";
              ResultSet rs = stmt.executeQuery(sql);
              
              
              int i = 150;
              g2.setColor(Color.BLUE);
              g2.setFont(new Font("arial",Font.BOLD,20));
              g2.drawString("Top 5 Score:" , 20, 100);
              //Trich xuat du lieu tu ket qua tra ve
              while(rs.next()){
              
                  // lay du lieu tuong ung voi cac cot ten
                  
                  String Name = rs.getString("Name");
                  int Score = rs.getInt("Score");
                  
                  // Hien thi gia tri
                  
                  
                  
                  g2.drawString("" +Name+ ":  " +Score, 20, i);
                  
                  i = i +20;
                  
                  
                  
                  
                  
              }
              conn.close();
              stmt.close();
          
         }         
         catch(SQLException se){
                 System.out.println("Error:"+se.toString());
                 }
    }
    
}
