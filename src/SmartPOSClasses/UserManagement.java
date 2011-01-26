/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SmartPOSClasses;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class UserManagement {

    public void UpdateUserName(String ColumnName, String New_Text, String User, String FirstName, String LastName){
        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");

         try {

            Statement sta = connection.createStatement();

            String statement = String.format("UPDATE users SET %s = '%s' WHERE FirstName = '%s' AND LastName = '%s'", ColumnName, New_Text, FirstName, LastName);
            System.out.println("Statement is : " + statement);

             sta.executeUpdate(statement);

            connection.close();

        } catch (SQLException ex) {
            System.out.println("Error updating username in users table");
        }

    }

    public void UpdateUserData(String ColumnName, String New_Text, String User){
        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");

         try {

            Statement sta = connection.createStatement();

            String statement = String.format("UPDATE users SET %s = '%s' WHERE User = '%s'", ColumnName, New_Text, User);
            System.out.println("Statement is : " + statement);


             sta.executeUpdate(statement);


            connection.close();

        } catch (SQLException ex) {
            System.out.println("Error updating user data in user table");
        }

    }

    public void DisplayUsers(){
        
    }
    
    public void CreateUser(JPanel jPanel12,
                           JTextField UserField,
                           JTextField FirstNameField,
                           JTextField LastNameField,
                           JPasswordField UserPasswordField,
                           JPasswordField UserConfirmPasswordField,
                           JComboBox WorkbucketComboBox,
                           JFrame AddUserFrame,
                           DefaultTableModel user_model){

        String User = UserField.getText();
        String FirstName = FirstNameField.getText();
        String LastName = LastNameField.getText();
        String UserPassword =  new String(UserPasswordField.getPassword());
        String ConfirmPassword =  new String(UserConfirmPasswordField.getPassword());
        String Workbucket = WorkbucketComboBox.getSelectedItem().toString();


        UserManagement usermgmt = new UserManagement();
        boolean validated = usermgmt.ValidateCreateUserForm(jPanel12, User,FirstName,LastName,UserPassword,ConfirmPassword, Workbucket);

        if(validated){
            MySQL mysql = new MySQL();
            Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");
            mysql.insertintoTable(connection,"Users", User, FirstName, LastName, UserPassword, Workbucket);

            user_model.addRow(new Object[]{User, FirstName, LastName, UserPassword, Workbucket});

            UserField.setText("");
            FirstNameField.setText("");
            LastNameField.setText("");
            UserPasswordField.setText("");
            UserConfirmPasswordField.setText("");
            WorkbucketComboBox.setSelectedItem("User");

            AddUserFrame.dispose();

        }
        
    }
    
    public void DeleteUser(){
        
    }

    public void AddUsertoWorkbucket(){

    }

    public void RemoveUserfromWorkbucket(){

    }

    public boolean ValidateCreateUserForm(JPanel jPanel5, String User, String FirstName, String LastName, String UserPassword, String ConfirmPassword, String Workbucket){
     boolean valid = true;

     if(User.length() < 4){ JOptionPane.showMessageDialog(jPanel5, "Username Should be at least 4 characters long"); valid = false; return valid;}
     if(FirstName.length() == 0){ JOptionPane.showMessageDialog(jPanel5, "Please enter the user's first name"); valid = false; return valid;}
     if(LastName.length() == 0){ JOptionPane.showMessageDialog(jPanel5, "Please enter the user's last name"); valid = false; return valid;}
     if(UserPassword.length() < 6){ JOptionPane.showMessageDialog(jPanel5, "The password should be at least 6 characters long"); valid = false; return valid;}
     if(ConfirmPassword.compareTo(UserPassword) != 0){ JOptionPane.showMessageDialog(jPanel5, "The password does not match"); valid = false; return valid;}

     return valid;
    }

}
