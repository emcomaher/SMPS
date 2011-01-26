/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SmartPOSClasses;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class ProductManagement {

    public void AddProduct(JPanel jPanel12,
                           JTextField BarkodField,
                           JTextField NazivArtiklaField,
                           JTextField KolicinaField,
                           JTextField CijenaField,
                           JFrame AddProductFrame,
                           DefaultTableModel inventory_model){

           String Barkod = BarkodField.getText();
        String NazivArtikla = NazivArtiklaField.getText();
        String Kolicina = KolicinaField.getText();
        String Cijena = CijenaField.getText();


        ProductManagement prodmgmt = new ProductManagement();
        boolean validated = prodmgmt.ValidateAddProductForm(jPanel12, Barkod, NazivArtikla, Kolicina, Cijena);

        if(validated){

        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");
        mysql.insertintoTable(connection,"Products", Barkod, NazivArtikla, Kolicina, Cijena);

        inventory_model.addRow(new Object[]{Barkod, NazivArtikla, Kolicina, Cijena, "WHATEVER"});

        System.out.println("Product Added !!!");

        BarkodField.setText("");
        NazivArtiklaField.setText("");
        KolicinaField.setText("");
        CijenaField.setText("");

        AddProductFrame.dispose();

        }
    
    }

    public boolean ValidateAddProductForm(JPanel jPanel5, String Barkod, String NazivArtikla, String Kolicina, String Cijena){
     boolean valid = true;

     StringOperations sops = new StringOperations();
     
     if(Barkod.length() == 0){ JOptionPane.showMessageDialog(jPanel5, "Please enter the barcode"); valid = false; return valid;}
     if(!sops.containsOnlyNumbers(Barkod)){ JOptionPane.showMessageDialog(jPanel5, "Barcode Should be a number"); valid = false; return valid;}
     if(NazivArtikla.length() == 0){ JOptionPane.showMessageDialog(jPanel5, "Please enter the name of the article"); valid = false; return valid;}
     if(Kolicina.length() == 0){ JOptionPane.showMessageDialog(jPanel5, "Please enter the quantity"); valid = false; return valid;}
     if(!sops.containsOnlyNumbers(Kolicina)){ JOptionPane.showMessageDialog(jPanel5, "Quantity Should be a number"); valid = false; return valid;}
     if(Cijena.length() == 0){ JOptionPane.showMessageDialog(jPanel5, "Please enter the price"); valid = false; return valid;}
     
     return valid;
    }

    public void UpdateProduct(String ColumnName, String New_Text, String Barkod){
        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");

         try {

            Statement sta = connection.createStatement();

            String statement = String.format("UPDATE Products SET %s = '%s' WHERE Barkod = %s", ColumnName, New_Text, Barkod);
            System.out.println("Statement is : " + statement);


             sta.executeUpdate(statement);


            connection.close();

        } catch (SQLException ex) {
            System.out.println("Error updating products table");
        }

        
        
    }

    public void RemoveProduct(){
        
    }

}
