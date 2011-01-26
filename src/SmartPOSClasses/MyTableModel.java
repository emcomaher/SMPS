/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SmartPOSClasses;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Emir
 */

//THIS CLASS EXTENDS THE DEFAULT TABLE MODEL AND MAKES EDITING IMPOSSIBLE FOR THE TABLE
// I USE IT LIKE THIS :  ResultsTable.setModel( (DefaultTableModel) new MyTableModel() );

public class MyTableModel extends DefaultTableModel {
    @Override
    public boolean isCellEditable(int row, int col) {      
           return false;     
   }


}
