/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SmartPOSClasses;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Administrator
 */
public class SalesSheet {

    public void LookupInventoryByBarcode (Connection con, String BARCODE, JTable SalesSheetTable, DefaultTableModel model, int Row, int Col, JLabel LabelTotalniIznos){

        System.out.println("IN Sales Sheet Class, method LookupInventoryByBarcode, Barcode is : " + BARCODE);
        float TotalniIznos = 0;
        float IznosStavke = 0;
 //       int BarcodeIsInThisRow = 0;
 //       Boolean FLAG_ERASE_CURRENT_BARCODE_AND_RETURN_FOCUS_BACK_ONE_ROW = false;

        /*
        String Current_Value = "";
        ArrayList<String> BarkodArrayList = new ArrayList<String>();

        for( int nRow = 0 ; nRow < SalesSheetTable.getRowCount() ; nRow++ ){
            Current_Value = SalesSheetTable.getValueAt(nRow,0).toString();
            if(Current_Value != null){
                if(Current_Value.compareTo("") != 0){
                BarkodArrayList.add(Current_Value);
                }
            }
        }

        for(int i=0; i<BarkodArrayList.size(); i++){
            System.out.print(i + " " + BarkodArrayList.get(i) + "\n");
        }

        BarkodArrayList.remove(BarkodArrayList.size()-1);

        if(BarkodArrayList.contains(BARCODE)){
                        System.out.println("YES IT CONTAINS : " + BARCODE);
                        BarcodeIsInThisRow = BarkodArrayList.indexOf(BARCODE);
                        System.out.println("BarcodeIsInThisRow : "+BarcodeIsInThisRow);

                        //TURN ON FLAG TO ERASE CURRENT BARCODE AND RETURN FOCUS BACK ONE ROW
                        FLAG_ERASE_CURRENT_BARCODE_AND_RETURN_FOCUS_BACK_ONE_ROW = true;
                        
                 } else {
                       System.out.println("NO IT DOESNT CONTAIN");


         */
        try{


          Statement sta = con.createStatement();
          String statement = String.format("SELECT * FROM Products WHERE Barkod='%s'",BARCODE);
          ResultSet res = sta.executeQuery(statement);

          System.out.println("List of Contents: ");
       while (res.next()) {


           System.out.println("  "+res.getString("Barkod") +
                             ", "+res.getString("Naziv") +
                             ", "+res.getString("Kolicina") +
                             ", "+res.getString("Cijena")
                             );

String Barkod = res.getString("Barkod");
String Naziv = res.getString("Naziv");
int Kolicina = Integer.parseInt(res.getString("Kolicina"));
float Cijena = Float.parseFloat(res.getString("Cijena"));
          IznosStavke = Kolicina*Cijena;

           model.setValueAt(res.getString("Naziv"), Row, Col+1);
           model.setValueAt(res.getString("Kolicina"), Row, Col+2);
           model.setValueAt(res.getString("Cijena"), Row, Col+3);
//Stavi IznosStavke = Kolicina puta Cijena
           model.setValueAt(IznosStavke, Row, Col+4);
           


          }
          res.close();

//calculate TotalniIznos Stavki (add each cell)
    int num_of_rows = model.getRowCount();
    System.out.println("num_of_rows : " + num_of_rows);

    for(int ii=0; ii < num_of_rows; ii++){
        if(model.getValueAt(ii,Col+4) != null){
             if(model.getValueAt(ii,Col+4).toString().compareTo("") != 0){
                TotalniIznos = TotalniIznos + Float.parseFloat(model.getValueAt(ii,Col+4).toString());
             }
        }
    }

           LabelTotalniIznos.setText(Float.toString(TotalniIznos));
           TotalniIznos = 0;
 
         } catch(Exception e){
             System.out.println("Error Displaying Query by Barcode in Sales Sheet Table");
         }
    

     //   } //END OF ELSE BarkodArrayList.contains(str_barcode)

        /*
        if(FLAG_ERASE_CURRENT_BARCODE_AND_RETURN_FOCUS_BACK_ONE_ROW){
           System.out.println("NEED TO DO IT");
    int row =  SalesSheetTable.getSelectionModel().getAnchorSelectionIndex();
    int col =  SalesSheetTable.getColumnModel().getSelectionModel().getAnchorSelectionIndex();
       System.out.println("** 2 CURRENTLY IN CELL 2 : " + row +"," +col);

            SalesSheetTable.changeSelection(BarcodeIsInThisRow, 0, false,false);
       //     SalesSheetTable.requestFocusInWindow();
     //       table.changeSelection(row, col, false,false);
     //       table.requestFocusInWindow();

           FLAG_ERASE_CURRENT_BARCODE_AND_RETURN_FOCUS_BACK_ONE_ROW = false;

       } else {
           System.out.println("DONT NEED TO DO NOTHIN");
       }
         */

       
    }  //end of method

} // end of class
