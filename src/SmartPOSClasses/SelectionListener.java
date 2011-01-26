/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SmartPOSClasses;

import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Administrator
 */
public class SelectionListener implements ListSelectionListener {
    JTable table;
     String str_barcode = "";
     Connection connection;
     JLabel LabelTotalniIznos;
   int BarcodeIsInThisRow = 0;
        Boolean FLAG_ERASE_CURRENT_BARCODE_AND_RETURN_FOCUS_BACK_ONE_ROW = false;
   String Current_Value = "";
        ArrayList<String> BarkodArrayList = new ArrayList<String>();

    // It is necessary to keep the table since it is not possible
    // to determine the table from the event's source
    public SelectionListener(JTable table, Connection connection, JLabel LabelTotalniIznos) {
        this.table = table;
        this.connection = connection;
        this.LabelTotalniIznos = LabelTotalniIznos;
       
    }

    public String getBarcode(){
        return str_barcode;
    }

    public void valueChanged(ListSelectionEvent e) {
        // If cell selection is enabled, both row and column change events are fired
       // int last_col=0;
      //  int last_row=0;
        
     //GET CURRENT CELL
        if (e.getSource() == table.getSelectionModel()
              && table.getRowSelectionAllowed()) {
            // Column selection changed
            int first = e.getFirstIndex();
            int last_col = e.getLastIndex();

            System.out.println("COL FIRST, LAST : " + first +"," +last_col);
        } else if (e.getSource() == table.getColumnModel().getSelectionModel()
               && table.getColumnSelectionAllowed() ){
            // Row selection changed
            int first = e.getFirstIndex();
            int last_row = e.getLastIndex();
            System.out.println("ROW FIRST, LAST : " + first +"," +last_row);
        }
       int focusRow =  table.getSelectionModel().getAnchorSelectionIndex();
       int focusCol =  table.getColumnModel().getSelectionModel().getAnchorSelectionIndex();
       System.out.println("CURRENTLY IN CELL : " + focusRow +"," +focusCol);

     //  if(focusRow == 0 && focusCol == 4){
      //     System.out.println("EXCEPTION RAISED");
           //table.changeSelection(3, 0, false,false);
           //table.requestFocusInWindow();
    //   } else {

       table.changeSelection(focusRow, focusCol, false,false);
       table.requestFocusInWindow();
                         

       //LOOKUP BY BARCODE AND DISPLAY INVENTORY DATA ON SALES SHEET
       DefaultTableModel model = (DefaultTableModel) table.getModel();
       model.addRow(new Object[]{"", "", "", "", ""});

           if(focusRow-1 > -1 && focusCol == 0){
            if(model.getValueAt(focusRow-1,focusCol) != null){
                if(model.getValueAt(focusRow-1, focusCol).toString().compareTo("") != 0){
                    str_barcode = model.getValueAt(focusRow-1, focusCol).toString();
                    System.out.println("The Barcode is : " + str_barcode);
                /*
                 if(BarkodArrayList.contains(str_barcode)){
                        System.out.println("YES IT CONTAINS : " + str_barcode);
                        BarcodeIsInThisRow = BarkodArrayList.indexOf(str_barcode);
                        System.out.println("BarcodeIsInThisRow : "+BarcodeIsInThisRow);

                        //TURN ON FLAG TO ERASE CURRENT BARCODE AND RETURN FOCUS BACK ONE ROW
                        FLAG_ERASE_CURRENT_BARCODE_AND_RETURN_FOCUS_BACK_ONE_ROW = true;
                        int num_of_rows = model.getRowCount();
                        table.changeSelection(0, 4, false,false);

                       
                        
                 } */
                 ///else {
               ///        System.out.println("NO IT DOESNT CONTAIN");
               ///        BarkodArrayList.add(str_barcode);

                       SalesSheet sales_sheet_class = new SalesSheet();
                       sales_sheet_class.LookupInventoryByBarcode(connection, str_barcode, table, model, focusRow-1, focusCol, LabelTotalniIznos);
               ///  }
            }
           }

           }

   //     } //END OF EXCEPTION RAISED

       // System.out.println("LAST ROW, LAST COL: " + last_row +"," +last_col);
        if (e.getValueIsAdjusting()) {
            // The mouse button has not yet been released
       //     System.out.println("MOUSE VALUE IS ADJUSTING");
            
        }

       
    }
}
