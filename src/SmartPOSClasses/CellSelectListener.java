/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SmartPOSClasses;

import java.sql.Connection;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Administrator
 */
public class CellSelectListener implements ListSelectionListener {
    JTable table;
     String str_barcode = "";
     Connection connection;

    // It is necessary to keep the table since it is not possible
    // to determine the table from the event's source
    public CellSelectListener(JTable table, Connection connection) {
        this.table = table;
        this.connection = connection;
    }

    public String getBarcode(){
        return str_barcode;
    }

    

    public void valueChanged(ListSelectionEvent e) {
     
       int focusRow =  table.getSelectionModel().getAnchorSelectionIndex();
       int focusCol =  table.getColumnModel().getSelectionModel().getAnchorSelectionIndex();
System.out.println("VALUE IS CHANGING");
 //     int row = table.getSelectedRow();
 //      int column = table.getSelectedColumn();
//       System.out.println("CELL("+row+","+column+")");

   //    System.out.println("CURRENTLY IN CELL : " + focusRow +"," +focusCol);

       //LOOKUP BY BARCODE AND DISPLAY INVENTORY DATA ON SALES SHEET
       DefaultTableModel model = (DefaultTableModel) table.getModel();
           if(focusRow-1 > -1 && focusCol == 1){
            if(model.getValueAt(focusRow-1,focusCol) != null){
                if(model.getValueAt(focusRow-1, focusCol).toString().compareTo("") != 0){
                    str_barcode = model.getValueAt(focusRow-1, focusCol).toString();
         //           System.out.println("The Barcode is : " + str_barcode);

            //       SalesSheet sales_sheet_class = new SalesSheet();
              //     sales_sheet_class.LookupInventoryByBarcode(connection, str_barcode, table, model, focusRow-1, focusCol);

                }
            }
           }



       // System.out.println("LAST ROW, LAST COL: " + last_row +"," +last_col);
        if (e.getValueIsAdjusting()) {
            // The mouse button has not yet been released
          //    int row = table.getSelectedRow();
          //    int column = table.getSelectedColumn();
          //    System.out.println("CELL("+row+","+column+")");

          //  System.out.println("MOUSE NOT YET RELEASED");
        }


    }
}
