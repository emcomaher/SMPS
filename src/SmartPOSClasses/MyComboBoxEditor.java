/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SmartPOSClasses;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

/**
 *
 * @author Emir
 */
public class MyComboBoxEditor extends DefaultCellEditor {
    public MyComboBoxEditor(String[] items) {
        super(new JComboBox(items));
    }
}

