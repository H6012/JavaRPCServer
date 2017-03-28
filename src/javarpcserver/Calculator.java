/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarpcserver;

import java.sql.*;


/**
 *
 * @author David Powell
 */
public class Calculator {
    
   public Integer sum(Integer x, Integer y){
      return new Integer(x+y);
   }
   
   public Integer lookup2(Integer x, Integer y){
       String ret_val = "db: ";
       return new Integer(3);
   }
   
   public String lookup(String field, String val){
       String ret_val = "";
      
       try
            {
              // create our mysql database connection
              String myDriver = "com.mysql.jdbc.Driver"; //"org.gjt.mm.mysql.Driver";
              String myUrl = "jdbc:mysql://localhost:3306/miotdb?useSSL=false";
              Class.forName(myDriver);
              Connection conn = DriverManager.getConnection(myUrl, "miotuser", "****");

              // our SQL SELECT query. 
              // if you only need a few columns, specify them by name instead of using "*"
              String query = "SELECT * FROM itbcontacts WHERE "+field+"='"+val+"'";

              System.out.println("Connecting to db");
              // create the java statement
              Statement st = conn.createStatement();

              // execute the query, and get a java resultset
              ResultSet rs = st.executeQuery(query);

              // iterate through the java resultset
              while (rs.next())
              {
                int id = rs.getInt("id");
                String firstName = rs.getString("fname");
                String lastName = rs.getString("lname");
                String email = rs.getString("email");
                
                String phone = rs.getString("phone");

                int staffid = rs.getInt("id");

                // print the results
                ret_val+=(String.format("%s, %s, %s, %s, %s\n", id, firstName, lastName, email, phone));
                System.out.println(String.format("%s, %s, %s, %s, \n", id, firstName, lastName, email));
              }
              st.close();
            }
            catch (Exception e)
            {
              ret_val = ("Got an exception! ");
              ret_val.concat(e.getMessage());
            }
       
       return new String(ret_val);
       
}
   
    
}
