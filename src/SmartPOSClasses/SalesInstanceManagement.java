/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SmartPOSClasses;

import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author Emir
 */
public class SalesInstanceManagement {

    public void getDatafromTable_And_InsertIntoMySqlSalesInstanceTable(JTable SalesSheetTable, String TotalSaleValue){
           String cell_content="";
        //System.out.println("COLUMN COUNT : "+ SalesSheetTable.getColumnCount());
        int row_count = SalesSheetTable.getRowCount();

        ArrayList<String> Barkod = new ArrayList<String>();
        ArrayList<String> Naziv = new ArrayList<String>();
        ArrayList<String> Kolicina = new ArrayList<String>();
        ArrayList<String> Cijena = new ArrayList<String>();
        ArrayList<String> Iznos = new ArrayList<String>();

        for( int nRow = 0 ; nRow < SalesSheetTable.getRowCount() ; nRow++ ){
           for( int nColumn = 0 ; nColumn < SalesSheetTable.getColumnCount(); nColumn++ ){
                cell_content = SalesSheetTable.getValueAt(nRow , nColumn).toString();
                if(cell_content.compareTo("") != 0){
                    //System.out.println(cell_content);
                    if(nColumn == 0) {Barkod.add(cell_content); }
                    if(nColumn == 1) {Naziv.add(cell_content); }
                    if(nColumn == 2) {Kolicina.add(cell_content); }
                    if(nColumn == 3) {Cijena.add(cell_content); }
                    if(nColumn == 4) {Iznos.add(cell_content); }
               }
            }
        }

        /*
         System.out.println("DATA ARRAY CONTENTS");
        for(int i=0; i<Barkod.size(); i++){
            System.out.print(Barkod.get(i) + " ");
            System.out.print(Naziv.get(i) + " ");
            System.out.print(Kolicina.get(i) + " ");
            System.out.print(Cijena.get(i) + " ");
            System.out.println(Iznos.get(i));
        }
         */

        this.insertDataintoMySqlSalesCompletedTables(Barkod, Naziv, Kolicina, Cijena, Iznos, TotalSaleValue);

    } //end of getdatafromtable

    public void insertDataintoMySqlSalesCompletedTables(ArrayList<String> Barkod,
                                                        ArrayList<String> Naziv,
                                                        ArrayList<String> Kolicina,
                                                        ArrayList<String> Cijena,
                                                        ArrayList<String> Iznos,
                                                        String TotalSaleValue){

    MySQL mysql = new MySQL();
    Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");
   // mysql.insertintoTable(connection,"Users", User, FirstName, LastName, UserPassword, Workbucket);
    mysql.insertintoSalesCompletedParent(connection,"sales_completed_parent", "NOW()", "LOCATION", TotalSaleValue);

    connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");
    String Result_ID = mysql.GetNumberOfRowsInSalesCompletedParent(connection, "sales_completed_parent");

    connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");
     for(int i=0; i<Barkod.size(); i++){
         System.out.println("ITERATION : " + i);
        mysql.insertintoSalesCompletedChild(connection,"sales_completed_child", Result_ID,
                                                                                Barkod.get(i).toString(),
                                                                                Naziv.get(i).toString(),
                                                                                Kolicina.get(i).toString(),
                                                                                Cijena.get(i).toString(),
                                                                                Iznos.get(i).toString()
                                                                                );
     }
 

    System.out.println("END OF METHOD");
    }

}
