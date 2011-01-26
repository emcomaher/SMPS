/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SmartPOSClasses;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 *
 * @author Administrator
 */
public class MySQL {

    private String driver = "com.mysql.jdbc.Driver";
    public Connection con;

public String getTotalSumfromTableColumnForDay(Connection connection, String table, String columnName, int month, int day){
        String sum = "";

         try{
          Statement sta = connection.createStatement();
          String sta_formatted = "";

          sta_formatted = String.format("select SUM(%s) from %s WHERE MONTH(date_time) = '%d' AND DAY(date_time) = '%d';", columnName, table, month, day);

          System.out.println("sta_formatted : " + sta_formatted);
          ResultSet res = sta.executeQuery(sta_formatted);

          while (res.next()) {
              String col_formatted = String.format("SUM(%s)", columnName);
              System.out.println("col_formatted : " + col_formatted);
              sum = res.getString(col_formatted);
              System.out.println(res.getString(col_formatted));

          }
          res.close();

         } catch(Exception e){
             System.out.println("Error Getting table last row number");
         }

        System.out.println("SUM is : " + sum);

        return sum;
    }
   
public String getTotalSumfromTableColumnForMonth(Connection connection, String table, String columnName, int month){
        String sum = "";

         try{
          Statement sta = connection.createStatement();
          String sta_formatted = "";

          sta_formatted = String.format("select SUM(%s) from %s WHERE MONTH(date_time) = '%d';", columnName, table, month);
          
          System.out.println("sta_formatted : " + sta_formatted);
          ResultSet res = sta.executeQuery(sta_formatted);

          while (res.next()) {
              String col_formatted = String.format("SUM(%s)", columnName);
              System.out.println("col_formatted : " + col_formatted);
              sum = res.getString(col_formatted);
              System.out.println(res.getString(col_formatted));

          }
          res.close();

         } catch(Exception e){
             System.out.println("Error Getting table last row number");
         }

        System.out.println("SUM is : " + sum);

        return sum;
    }

    public void generateTablebasedonResultofSQL(Connection con, JTable table, DefaultTableModel model, String sql_query){

        model=(DefaultTableModel) table.getModel();

        int RowCount = table.getRowCount();
        int ColumnCount = table.getColumnCount();


        //GENERATE TABLE ONLY IF IT IS EMPTY (OTHERWISE DO NOTHING)
        if((RowCount == 0) && (ColumnCount == 0)){

            //GENERATE THE TABLE
            try{
           Statement sta = con.createStatement();
          // ResultSet res = sta.executeQuery(String.format("SELECT * FROM %s", mysql_table));
           ResultSet res = sta.executeQuery(sql_query);

           // get result set meta data
           ResultSetMetaData rsMetaData = res.getMetaData();
           int numberOfColumns = rsMetaData.getColumnCount();

           ArrayList<String> columnNames = new ArrayList<String>();

          // get the column names; column indexes start from 1
           for (int i = 1; i < numberOfColumns + 1; i++) {
              String columnName = rsMetaData.getColumnName(i);
              // Get the name of the column's table name
              String tableName = rsMetaData.getTableName(i);
              System.out.println("column name=" + columnName + " table=" + tableName + "");
              columnNames.add(columnName);
          }
    
          System.out.println("NOW PRINTING COLUMN NAMES : ");
           for(int i = 0; i< columnNames.size(); i++){
                System.out.println(String.format("Col %d : %s", i, columnNames.get(i)));
                model.addColumn(columnNames.get(i));
               // TableColumn col = table.getColumnModel().getColumn(i);
               // col.setPreferredWidth(50);
                
          }


          ArrayList matrix = new ArrayList();

          int count = 0;
          while (res.next()) {
               matrix.add(new ArrayList());

                for(int i = 0; i<columnNames.size(); i++){
                 ((ArrayList)matrix.get(count)).add(res.getString(columnNames.get(i)));
                }

          count= count+ 1;
          }
          res.close();

          System.out.println(String.format("Count is : %d", count));
               // display contents of matrix

          String current_string = "";
          String[] string_array = null;

          System.out.println("ARRAY CONTENTS : ");
           for(int i = 0; i < matrix.size();i++){
            for(int j = 0; j < ((ArrayList)matrix.get(i)).size(); j++){
               System.out.print( (String)((ArrayList)matrix.get(i)).get(j) +" ");
               current_string = current_string.concat((String)((ArrayList)matrix.get(i)).get(j)).concat("%");
               
            }
            string_array = current_string.split("%");
            model.addRow(string_array);
            
            for(int x=0; x<string_array.length; x++){
                System.out.println(String.format("string_array[%d] : %s", x, string_array[x]));
            }
            string_array = null;
            current_string = "";
           System.out.println();
          //System.out.print(String.format("%d --> ", i));
          }

         } catch(Exception e){
             System.out.println("Error Displaying Results Table Data");
         }
        }
       // return model;

    }

    public String getNumberOfRowsInTableColumn (Connection con, String table, String columnName){

        String Result = "";

         try{
          Statement sta = con.createStatement();
          String sta_formatted = String.format("SELECT * FROM %s order by %s DESC Limit 1", table, columnName);
          System.out.println("sta_formatted : " + sta_formatted);
          ResultSet res = sta.executeQuery(sta_formatted);

          while (res.next()) {

           Result = res.getString(columnName);
           System.out.println(res.getString(columnName));

          }
          res.close();

         } catch(Exception e){
             System.out.println("Error Getting table last row number");
         }

        System.out.println("Result ID is : " + Result);
        return Result;

     } //End of GetNumberOfRowsInTableColumn

    public String getTotalSumfromTableColumn(Connection connection, String table, String columnName, String id, String article){
        String sum = "";

         try{
          Statement sta = connection.createStatement();
          String sta_formatted = "";

          if(article.compareTo("") == 0){
          sta_formatted = String.format("select SUM(%s) from %s;", columnName, table);
          } else {
          sta_formatted = String.format("select SUM(%s) from %s WHERE %s = '%s';", columnName, table, id, article);
          }
          
          System.out.println("sta_formatted : " + sta_formatted);
          ResultSet res = sta.executeQuery(sta_formatted);

          while (res.next()) {
              String col_formatted = String.format("SUM(%s)", columnName);
              System.out.println("col_formatted : " + col_formatted);
              sum = res.getString(col_formatted);
              System.out.println(res.getString(col_formatted));

          }
          res.close();

         } catch(Exception e){
             System.out.println("Error Getting table last row number");
         }

        System.out.println("SUM is : " + sum);

        return sum;
    }


    public Connection logintoMySQLdatabase (String Username, String Password, String Database, String Port, String Host){

       try {
     
        String CONNECTION_STRING = String.format("jdbc:mysql://%s:%s/%s", Host, Port, Database);

        Class.forName(driver);

        // Connect with a url string
               con = DriverManager.getConnection(
       //// "jdbc:derby://localhost:1527/mamamia;create=true","root", "ada");
              //"jdbc:mysql://localhost:3306/bosnian","root","ada"
               CONNECTION_STRING, Username, Password
         );

            System.out.println("Mysql connection ok.");
  //          System.out.println("First Connection :" + con);
            

         } catch (Exception e){
             System.err.println("Exception: "+e.getMessage());
             System.out.println("Error Connecting");
         }

       return con;

    }

    public void createTable (Connection connection, String TableName, String... Columns){

        String ColumnArguments = "";
        
        try {
           
            Statement sta = connection.createStatement();
          
            for(int i = 0; i<Columns.length; i++){
              if(i < Columns.length -1 ) { ColumnArguments = ColumnArguments.concat(Columns[i]).concat(" VARCHAR(40),");} else
                                         { ColumnArguments = ColumnArguments.concat(Columns[i]).concat(" VARCHAR(40)");}
            }

 //           System.out.println("ColumnArguments : "+ ColumnArguments);


           // String statement = String.format("CREATE TABLE %s(%s VARCHAR(40), %s VARCHAR(40))",TableName, Columns[0], Columns[1]);
            String statement = String.format("CREATE TABLE %s(%s)",TableName, ColumnArguments);
 //           System.out.println("Statement is : " + statement);

            
             sta.executeUpdate(statement);
               
            connection.close();

        } catch (SQLException ex) {
            System.out.println("Error creating table");
        }

    } //END OF CREATE TABLE

    public void createSalesCompletedParentTable (Connection connection, String TableName){

        try {

            Statement sta = connection.createStatement();

            String statement = String.format("CREATE TABLE %s(sale_pk_id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT, date_time DATETIME DEFAULT '0000-00-00 00:00:00', location VARCHAR(100), total_sale_value INT)",TableName);
            System.out.println("Create Sales Completed Parent Statement is : " + statement);

            sta.executeUpdate(statement);

            connection.close();

        } catch (SQLException ex) {
            System.out.println("Error creating Sales Completed Parent table");
        }

    } //END OF CREATE SALES COMPLETED PARENT TABLE

    public void createSalesCompletedChildTable (Connection connection, String TableName){

        try {

            Statement sta = connection.createStatement();

            String statement = String.format("CREATE TABLE %s(sale_fk_id INT UNSIGNED NOT NULL, FOREIGN KEY(sale_fk_id) REFERENCES sales_completed_parent(sale_pk_id), Barkod VARCHAR(40), Naziv VARCHAR(40), Kolicina VARCHAR(20), Cijena VARCHAR(20), Iznos VARCHAR(20), date_time DATETIME DEFAULT '0000-00-00 00:00:00')",TableName);
            System.out.println("Create Sales Completed Child Statement is : " + statement);

            sta.executeUpdate(statement);

            connection.close();

        } catch (SQLException ex) {
            System.out.println("Error creating Sales Completed Child table");
        }

    } //END OF CREATE SALES COMPLETED CHILD TABLE

    public void insertintoTable (Connection connection, String TableName, String... Values){

        String ValueArguments = "";

        try {

            Statement sta = connection.createStatement();


            for(int i = 0; i<Values.length; i++){
              if(i < Values.length -1 ) { ValueArguments = ValueArguments.concat("'").concat(Values[i]).concat("'").concat(",");} else
                                         { ValueArguments = ValueArguments.concat("'").concat(Values[i]).concat("'"); }
            }

 //           System.out.println("ValueArguments : "+ ValueArguments);

           // String statement = String.format("CREATE TABLE %s(%s VARCHAR(40), %s VARCHAR(40))",TableName, Values[0], Values[1]);
            String statement = String.format("INSERT INTO %s VALUES(%s)",TableName, ValueArguments);
 //           System.out.println("Statement is : " + statement);

      
             sta.executeUpdate(statement);


            connection.close();

        } catch (SQLException ex) {
            System.out.println("Error inserting into table");
        }

    }//end of insert into table

public void insertintoSalesCompletedParent (Connection connection, String TableName, String DateTime, String Location, String Total_Sale_Value){

        try {

            Statement sta = connection.createStatement();

            String statement = String.format("INSERT INTO %s (date_time, location, total_sale_value) VALUES(%s, '%s', '%s')",TableName,  DateTime, Location, Total_Sale_Value);
            System.out.println("PARENT Statement is : " + statement);


             sta.executeUpdate(statement);


            connection.close();

        } catch (SQLException ex) {
            System.out.println("Error inserting into sales_completed_parent table");
        }

    }//end of insert into sales completed Parent

public String GetNumberOfRowsInSalesCompletedParent (Connection con, String TableName){

        String Result = "";

         try{
          Statement sta = con.createStatement();
          ResultSet res = sta.executeQuery("SELECT * FROM sales_completed_parent order by sale_pk_id DESC Limit 1");

  //        System.out.println("List of Contents: ");
          while (res.next()) {


              Result = res.getString("sale_pk_id");
//           System.out.println(res.getString("sale_pk_id"));



          }
          res.close();


         } catch(Exception e){
             System.out.println("Error Getting table last row number");
         }

        System.out.println("Result ID is : " + Result);
        return Result;

     } //End of GetNumberOfRowsInSalesCompletedParent

public void insertintoSalesCompletedChild (Connection connection, String TableName, String... Values){

  
         String ValueArguments = "";

        try {

            Statement sta = connection.createStatement();

            for(int i = 0; i<Values.length; i++){
              if(i < Values.length -1 ) { ValueArguments = ValueArguments.concat("'").concat(Values[i]).concat("'").concat(",");} else
                                         { ValueArguments = ValueArguments.concat("'").concat(Values[i]).concat("'"); }
            }

            String statement = String.format("INSERT INTO %s ( sale_fk_id , Barkod , Naziv , Kolicina , Cijena , Iznos, date_time ) VALUES(%s , NOW())",TableName, ValueArguments);
            System.out.println("CHILD Statement is : " + statement);


             sta.executeUpdate(statement);
          

        } catch (SQLException ex) {
            System.out.println("Error inserting into sales_completed_child table");
        }

    }//end of insert into sales completed Child

    public void deletefromUserTable (Connection connection, String TableName, String... Values){
        
        try {

            Statement sta = connection.createStatement();           
         
           // String statement = String.format("CREATE TABLE %s(%s VARCHAR(40), %s VARCHAR(40))",TableName, Values[0], Values[1]);
            String statement = String.format("DELETE FROM %s WHERE User='%s' and FirstName='%s' and LastName='%s' and Password='%s' and Workbucket = '%s'",TableName, Values[0], Values[1], Values[2], Values[3], Values[4]);
  //          System.out.println("Statement is : " + statement);


             sta.executeUpdate(statement);


            connection.close();

        } catch (SQLException ex) {
            System.out.println("Error deleting from user table");
        }
    }//end of delete from User table

        public void deletefromProductsTable (Connection connection, String TableName, String... Values){

        try {

            Statement sta = connection.createStatement();

           // String statement = String.format("CREATE TABLE %s(%s VARCHAR(40), %s VARCHAR(40))",TableName, Values[0], Values[1]);
            String statement = String.format("DELETE FROM %s WHERE Barkod='%s' and Naziv='%s' and Kolicina='%s' and Cijena='%s'",TableName, Values[0], Values[1], Values[2], Values[3]);
  //          System.out.println("Statement is : " + statement);


             sta.executeUpdate(statement);


            connection.close();

        } catch (SQLException ex) {
            System.out.println("Error deleting from products table");
        }
    }//end of delete from Products table

     public DefaultTableModel DisplayUserTableData (DefaultTableModel model, Connection con, JTable table){
         model=(DefaultTableModel) table.getModel();

         try{
          Statement sta = con.createStatement();
          ResultSet res = sta.executeQuery("SELECT * FROM Users");

 //         System.out.println("List of Contents: ");
          while (res.next()) {

/*
          System.out.println("  "+res.getString("User") +
                             ", "+res.getString("FirstName") +
                             ", "+res.getString("LastName") +
                             ", "+res.getString("Password") +
                             ", "+res.getString("Workbucket")
                             );
*/

          model.addRow(new Object[]{res.getString("User"),
                                    res.getString("FirstName"),
                                    res.getString("LastName"),
                                    res.getString("Password"),
                                    res.getString("Workbucket")
                                   });

          }
          res.close();


         } catch(Exception e){
             System.out.println("Error Displaying User Table Data");
         }

         return model;
     } //end of display user table data

     public DefaultTableModel DisplayInventoryTableData (DefaultTableModel model, Connection con, JTable table){
         model=(DefaultTableModel) table.getModel();

         try{
          Statement sta = con.createStatement();
          ResultSet res = sta.executeQuery("SELECT * FROM Products");

  //        System.out.println("List of Contents: ");
          while (res.next()) {

/*
           System.out.println("  "+res.getString("Barkod") +
                             ", "+res.getString("Naziv") +
                             ", "+res.getString("Kolicina") +
                             ", "+res.getString("Cijena")
                             );
*/

          model.addRow(new Object[]{res.getString("Barkod"),
                                    res.getString("Naziv"),
                                    res.getString("Kolicina"),
                                    res.getString("Cijena")
                                   });

          }
          res.close();


         } catch(Exception e){
             System.out.println("Error Displaying Inventory Table Data");
         }

         return model;
     }

        
   

} //end of MySQL Class
