

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ZLOOKK.java
 *
 * Created on Dec 19, 2009, 7:35:36 PM
 */

import SmartPOSClasses.MySQL;
import SmartPOSClasses.UserManagement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ustamuji
 */
public class ZLOOKK extends javax.swing.JFrame {

    
     private String framework = "embedded";
    private String driver = "com.mysql.jdbc.Driver";
    private String protocol = "jdbc:mysql:";

    DefaultListModel list1_model = new DefaultListModel();
    DefaultListModel list2_model = new DefaultListModel();

    String Username = "";
String Password = "";

    /** Creates new form ZLOOKK */
    public ZLOOKK() {
        initComponents();
       // this.setFocusable(true);
     
   //     jList1.setModel(list1_model);
        jList2.setModel(list2_model);

        //JOptionPane.showMessageDialog(jPanel1,"whatever");
  // Components related to "login" field
    JLabel label_loginname = new JLabel("Enter your login name:");
    JTextField loginname = new JTextField(15);
    // loginname.setText("EnterLoginNameHere"); // Pre-set some text


    // Components related to "password" field
    JLabel label_password = new JLabel("Enter your password:");
    JPasswordField password = new JPasswordField();
     password.setEchoChar('*'); // Sets @ as masking character
    // password.setEchoChar('\000'); // Turns off masking


     loginname.setText("root");
    // password.setText("ada");
     password.setText("");

     DefaultTableModel model = new DefaultTableModel();

     jTable1.setModel(model);

// Create a couple of columns
model.addColumn("First");
model.addColumn("Last");
model.addColumn("Workbucket");

// Append a row
model.addRow(new Object[]{"First", "Last", "Workbucket"});
// there are now 2 rows with 2 columns


    JCheckBox rememberCB = new JCheckBox("Remember me");

      Object[] array = { label_loginname,
                       loginname,
                       label_password,
                       password,
                       rememberCB };

      boolean login_successful = false;
      boolean login_iscancelled = false;

      while (login_successful == false && login_iscancelled == false){

      int result = JOptionPane.showConfirmDialog(null, array, "Login",
                                            JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.PLAIN_MESSAGE);

       // User hit OK
    if (result == JOptionPane.OK_OPTION) { System.out.println( "OK_OPTION" );

        try {


Username = loginname.getText();
Password = password.getText();

String Port = "3306";
String Database = "bosnian";
String Host = "localhost";

//String Port = jTextFieldPort.getText();
//String Database = jTextFieldDatabase.getText();
//String Host = jTextFieldHost.getText();

System.out.println("Username : " + Username);
System.out.println("Password : " + Password);

/*
if(Username.compareTo("") == 0){
    System.out.println("Username is blank");
} else {
    System.out.println("Username is not blank");
}
if(Password.compareTo("") == 0){
    System.out.println("Password is blank");
} else {
    System.out.println("Password is not blank");
}
*/
  //  Username = String.format("\"%s\"", Username);
  //  Password = String.format("\"%s\"", Password);

    String CONNECTION_STRING = String.format("jdbc:mysql://%s:%s/%s", Host, Port, Database);

    System.out.println("CONNECTION_STRING : " + CONNECTION_STRING);


Class.forName(driver);

// Connect with a url string
      Connection con = DriverManager.getConnection(
       //// "jdbc:derby://localhost:1527/mamamia;create=true","root", "ada");
              //"jdbc:mysql://localhost:3306/bosnian","root","ada"
               CONNECTION_STRING, Username, Password
               );



      System.out.println("Mysql connection ok.");
      login_successful = true;


      jTabbedPane1.removeTabAt(0);

      Statement sta = con.createStatement();
// getting the data back
     // int c =
              sta.executeUpdate(
              "INSERT INTO dictionary (word,meaning) VALUES('monty', 'works')");

      ResultSet res = sta.executeQuery(
      "SELECT * FROM dictionary");

      System.out.println("List of Contents: ");
      while (res.next()) {

           jTextFieldUser.setText(res.getString("word"));
          jTextFieldPassword.setText(res.getString("meaning"));
          System.out.println(
           "  "+res.getString("word")
           + ", "+res.getString("meaning"));

      }
      res.close();

      sta.close();



      con.close();

    } catch (Exception e) {
      System.err.println("Exception: "+e.getMessage());
      System.out.println("Error Connecting");
      
      login_successful = false;


    }

    }

    // User hit CANCEL
    if (result == JOptionPane.CANCEL_OPTION) { System.out.println( "CANCEL_OPTION" ); login_iscancelled = true; System.exit(0);}

    // User closed the window without hitting any button
    if (result == JOptionPane.CLOSED_OPTION) { System.out.println( "CLOSED_OPTION" ); login_iscancelled = true; System.exit(0);}

       // Output data in "login" field, if any
    String newloginname = loginname.getText();
    System.out.println( "newloginname: " + newloginname );

    // Output data in "password" field, if any
    String newpassword = new String(password.getPassword());
    System.out.println( "newpassword: " + newpassword );

    // Output state of "remember me" check box
    boolean selectedCB = rememberCB.isSelected();
    System.out.println( "selectedCB: " + selectedCB );


      
      } //end of while login successful is false

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        NewWorkBucketFrame = new javax.swing.JFrame();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        AddUserFrame = new javax.swing.JFrame();
        CreateUserOld = new javax.swing.JButton();
        UserField = new javax.swing.JTextField();
        FirstNameField = new javax.swing.JTextField();
        UserPasswordField = new javax.swing.JPasswordField();
        UserConfirmPasswordField = new javax.swing.JPasswordField();
        LastNameField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jTextFieldDatabase = new javax.swing.JTextField();
        jTextFieldHost = new javax.swing.JTextField();
        jTextFieldPort = new javax.swing.JTextField();
        jButtonLogin = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jButton9 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        InsertIntoTable = new javax.swing.JButton();
        ConnecttoDatabase = new javax.swing.JButton();
        CreateTable = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTextFieldPassword = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton15 = new javax.swing.JButton();
        DeleteUser = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jButton12.setText("Add >");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("< Remove");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("<< Remove All");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        jScrollPane4.setViewportView(jList2);

        javax.swing.GroupLayout NewWorkBucketFrameLayout = new javax.swing.GroupLayout(NewWorkBucketFrame.getContentPane());
        NewWorkBucketFrame.getContentPane().setLayout(NewWorkBucketFrameLayout);
        NewWorkBucketFrameLayout.setHorizontalGroup(
            NewWorkBucketFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewWorkBucketFrameLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(NewWorkBucketFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NewWorkBucketFrameLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton14))
                    .addGroup(NewWorkBucketFrameLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButton12))
                    .addGroup(NewWorkBucketFrameLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        NewWorkBucketFrameLayout.setVerticalGroup(
            NewWorkBucketFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewWorkBucketFrameLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(NewWorkBucketFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(NewWorkBucketFrameLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton12)
                        .addGap(18, 18, 18)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14)))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        CreateUserOld.setText("Create User");
        CreateUserOld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateUserOldActionPerformed(evt);
            }
        });

        UserField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserFieldActionPerformed(evt);
            }
        });

        jLabel10.setText("User :");

        jLabel11.setText("First :");

        jLabel12.setText("Last :");

        jLabel13.setText("Password :");

        jLabel14.setText("Confirm :");

        javax.swing.GroupLayout AddUserFrameLayout = new javax.swing.GroupLayout(AddUserFrame.getContentPane());
        AddUserFrame.getContentPane().setLayout(AddUserFrameLayout);
        AddUserFrameLayout.setHorizontalGroup(
            AddUserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddUserFrameLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(AddUserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(33, 33, 33)
                .addGroup(AddUserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CreateUserOld, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(UserPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(UserConfirmPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(UserField, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(FirstNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(LastNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                .addGap(138, 138, 138))
        );
        AddUserFrameLayout.setVerticalGroup(
            AddUserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddUserFrameLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(AddUserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddUserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(16, 16, 16)
                .addGroup(AddUserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddUserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(AddUserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UserConfirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addComponent(CreateUserOld)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setText("jLabel4");

        jLabel1.setText("User :");

        jLabel2.setText("Password:");

        jTextFieldDatabase.setText("bosnian");

        jTextFieldHost.setText("localhost");

        jTextFieldPort.setText("3306");

        jButtonLogin.setText("Login");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldHost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                            .addComponent(jTextFieldDatabase, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                            .addComponent(jTextFieldPort, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                        .addComponent(jTextFieldUser, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButtonLogin)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLogin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel8.setText("MANAGE USERS");

        jButton2.setText("CREATE USER");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setText("CREATE NEW USER (MAKE AS POPUP)");

        jButton9.setText("DELETE USER");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel9.setText("SELECTION MODEL PROPERTY OF TABLE HAS TO BE SET TO single selection");

        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addContainerGap(268, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel6)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Feature 1", jPanel2);

        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(244, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(24, 24, 24))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(50, 50, 50))
        );

        jTabbedPane1.addTab("Feature 2", jPanel3);

        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(237, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(31, 31, 31))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Feature 3", jPanel4);

        InsertIntoTable.setText("Insert into Table");
        InsertIntoTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertIntoTableActionPerformed(evt);
            }
        });

        ConnecttoDatabase.setText("Connect to db");
        ConnecttoDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnecttoDatabaseActionPerformed(evt);
            }
        });

        CreateTable.setText("Create Table");
        CreateTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPasswordField2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPasswordField3, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                                .addGap(46, 46, 46)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton2)
                                    .addComponent(jButton9))))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ConnecttoDatabase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(InsertIntoTable, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(CreateTable)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(InsertIntoTable))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ConnecttoDatabase))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2))
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                        .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CreateTable)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel9)
                .addContainerGap())
        );

        jButton4.setText("DELETE SELECTED USER (ARE YOU SURE ?)");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "First", "Last", "Workbucket", "Title 4"
            }
        ));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("Maps, Sets");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Java Snippets");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Start");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Stop");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("CREATE DIRS");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton1.setText("Interact with MySQL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                    .addComponent(jButton6))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                    .addComponent(jButton7)))
                            .addGap(50, 50, 50))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton8)
                            .addContainerGap(141, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton7))
                .addGap(33, 33, 33)
                .addComponent(jButton8)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jButton10.setText("Create New User Popup");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Create New Workbucket Popup");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel8))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, 0, 0, Short.MAX_VALUE)
                        .addGap(10, 10, 10)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(472, 472, 472))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addContainerGap(831, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(70, 70, 70)
                            .addComponent(jButton4))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton10)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(236, Short.MAX_VALUE))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jButton15.setText("Create User");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        DeleteUser.setText("Delete User");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(DeleteUser)
                                .addGap(18, 18, 18)
                                .addComponent(jButton15)))
                        .addGap(49, 49, 49)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(674, 674, 674))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton15)
                            .addComponent(DeleteUser))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:




         try {

////NetworkServerControl server = new NetworkServerControl();
////server.start (null);


Class.forName(driver);

// Connect with a url string
      Connection con = DriverManager.getConnection(
       //// "jdbc:derby://localhost:1527/mamamia;create=true","root", "ada");
               //"jdbc:mysql://localhost:3306/bosnian","root","ada");
               "jdbc:mysql://localhost:3306/bosnian","root","");
      System.out.println("Mysql connection ok.");





      Statement sta = con.createStatement();
// getting the data back
     // int c =
              sta.executeUpdate(
              "INSERT INTO dictionary (word,meaning) VALUES('mark', 'hurd')");

      ResultSet res = sta.executeQuery(
      "SELECT * FROM dictionary");

      System.out.println("List of Contents: ");
      while (res.next()) {

           jTextFieldUser.setText(res.getString("word"));
          jTextFieldPassword.setText(res.getString("meaning"));
          System.out.println(
           "  "+res.getString("word")
           + ", "+res.getString("meaning"));

      }
      res.close();

      sta.close();



      con.close();

    } catch (Exception e) {
      System.err.println("Exception: "+e.getMessage());
    }
        


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Map ageMap = new TreeMap();
        ageMap.put("PG", 18);
        //parental Guidance
        ageMap.put("G", 4);
        //general viewing
        ageMap.put("A", 27);

        Map numMap = new TreeMap();
        numMap.put(2, "second");
        numMap.put(3, "third");
        numMap.put(1, "first");

        //Adult material

        System.out.println(ageMap);
         System.out.println(numMap);
        

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.arch"));
        System.out.println(System.getProperty("os.version"));

        int delay = 3000; //milliseconds
  
  

  ActionListener taskPerformer = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
          //...Perform a task...
          System.out.println("Action Event Fired");
      }
  };
  
  final javax.swing.Timer t = new Timer(delay, taskPerformer);

  ActionListener stopper = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
          //...Perform a task...
          System.out.println("Action Event Stopped");
          t.stop();
      }
  };

  javax.swing.Timer t2 = new Timer(delay, stopper);

  t.start();
  t.setRepeats(false);
  //t.stop();
  t2.start();
  t2.setRepeats(false);

  
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));




    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:

        int delay = 1500; //milliseconds


        ActionListener taskPerformer = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
          //...Perform a task...
          System.out.println("Action Event Fired");
      }
  };

 javax.swing.Timer t = new Timer(delay, taskPerformer);
 

  t.start();

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:

       int delay = 1500;


        ActionListener stopper = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
          //...Perform a task...
          System.out.println("Action Event Stopped");
      //    t.stop();
      }
  };

  javax.swing.Timer t2 = new Timer(delay, stopper);

  t2.start();
  t2.setRepeats(false);

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        String Str_Year = "2008";
        String Str_Month = "Maj";
        String Str_Day = "27";

        String DATApath = "C:\\Documents and Settings\\ustamuji\\Desktop\\DATA";
        String YEARpath = DATApath.concat("\\").concat(Str_Year);
        String MONTHpath = DATApath.concat("\\").concat(Str_Year).concat("\\").concat(Str_Month);

        File year_dir = new File(YEARpath);
        File month_dir = new File(MONTHpath);


        
            //year begin
            if(year_dir.exists()){
               if(year_dir.isDirectory() == true){

                   System.out.println("Year Dir exists : " + year_dir);

                        //month begin
                        if(month_dir.exists()){
                           if(month_dir.isDirectory() == true){

                                System.out.println("Month Dir exists : " + month_dir);

                           }
                        }

                        if(!month_dir.exists()){
                                System.out.println("Month Dir does not exist : " + month_dir);
                        }
                        //month end
               }
            }
            if(!year_dir.exists()){
                System.out.println("Year File does not exist : " + year_dir);
            }
            //year end
        


    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        // TODO add your handling code here:
        

        try {



String Username = jTextFieldUser.getText();
String Password = jPasswordField1.getText();
String Port = jTextFieldPort.getText();
String Database = jTextFieldDatabase.getText();
String Host = jTextFieldHost.getText();

System.out.println("Username : " + Username);
System.out.println("Password : " + Password);

/*
if(Username.compareTo("") == 0){
    System.out.println("Username is blank");
} else {
    System.out.println("Username is not blank");
}
if(Password.compareTo("") == 0){
    System.out.println("Password is blank");
} else {
    System.out.println("Password is not blank");
}
*/
  //  Username = String.format("\"%s\"", Username);
  //  Password = String.format("\"%s\"", Password);
 
    String CONNECTION_STRING = String.format("jdbc:mysql://%s:%s/%s", Host, Port, Database);
    
    System.out.println("CONNECTION_STRING : " + CONNECTION_STRING);


Class.forName(driver);

// Connect with a url string
      Connection con = DriverManager.getConnection(
       //// "jdbc:derby://localhost:1527/mamamia;create=true","root", "ada");
              //"jdbc:mysql://localhost:3306/bosnian","root","ada"
               CONNECTION_STRING, Username, Password
               );



      System.out.println("Mysql connection ok.");

//if(con.isClosed()){
   // System.out.println("Error Connecting");
//}
    //  jPanel6.setVisible(false);
 //     jPanel2.setVisible(false);
    //  jTabbedPane1.setVisible(false);

 //     jPanel2.revalidate();
 //     jPanel2.repaint();

      jTabbedPane1.removeTabAt(0);


//jTabbedPane1.revalidate();
//jTabbedPane1.repaint();


//   jPanel1.revalidate();
//   jPanel1.repaint();



      Statement sta = con.createStatement();
// getting the data back
     // int c =
              sta.executeUpdate(
              "INSERT INTO dictionary (word,meaning) VALUES('mark', 'hurd')");

      ResultSet res = sta.executeQuery(
      "SELECT * FROM dictionary");

      System.out.println("List of Contents: ");
      while (res.next()) {

           jTextFieldUser.setText(res.getString("word"));
          jTextFieldPassword.setText(res.getString("meaning"));
          System.out.println(
           "  "+res.getString("word")
           + ", "+res.getString("meaning"));

      }
      res.close();

      sta.close();



      con.close();

    } catch (Exception e) {
      System.err.println("Exception: "+e.getMessage());
      System.out.println("Error Connecting");
    }

         
}//GEN-LAST:event_jButtonLoginActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
DefaultTableModel model=(DefaultTableModel) jTable1.getModel();

String inputUsername = jTextField1.getText();
char[] inputPassword = jPasswordField2.getPassword();
char[] inputConfirmPassword = jPasswordField3.getPassword();


String str_inputPassword = "";

for(int i = 0; i < inputPassword.length ; i++){
	str_inputPassword = str_inputPassword + inputPassword[i];
}

System.out.println("str_inputPassword : " + str_inputPassword);

if (Arrays.equals(inputPassword, inputConfirmPassword) ){
      if (inputUsername.compareTo("") != 0){
            if (str_inputPassword.compareTo("") != 0){
                    if(inputPassword.length >= 6){
                            if(inputUsername.length() >=4 ){
                                model.addRow(new Object[]{inputUsername, str_inputPassword});

                             
                                String Port = "3306";
                                String Database = "bosnian";
                                String Host = "localhost";

                                 String CONNECTION_STRING = String.format("jdbc:mysql://%s:%s/%s", Host, Port, Database);
                                 System.out.println("CONNECTION_STRING : " + CONNECTION_STRING);

                               try {

                                 Class.forName(driver);
                                 // Connect with a url string
                                 Connection con = DriverManager.getConnection(CONNECTION_STRING, Username, Password);

                                 Statement sta = con.createStatement();
                                 sta.executeUpdate("INSERT INTO user_data (username,password) VALUES('monty', 'works')");
                                 sta.close();

                               } catch(Exception e){
                                   e.printStackTrace();
                               }
                            } else { JOptionPane.showMessageDialog(jPanel1, "Username has to be equal to or longer than 4 chars");}
                    } else { JOptionPane.showMessageDialog(jPanel1, "Password has to be equal to or longer than 6 chars");}
            } else { JOptionPane.showMessageDialog(jPanel1, "Password is blank "); }
      } else { JOptionPane.showMessageDialog(jPanel1, "Username is blank "); }
} else { JOptionPane.showMessageDialog(jPanel1, "Password doesnt match. Please Confirm password"); }
        /*
        TableColumn TableColumn1 = jTable1.getColumnModel().getColumn(0);
int w = TableColumn1.getModelIndex();
//jTable1.removeColumn(TableColumn1);

TableModel model = new TableModel();
model = jTable1.getModel();



String stuff = jTable1.getModel().getValueAt(0, 0).toString();
//String stuff = jTable1.getModel()
System.out.println("stuff : " +  stuff);


        

       

       

    // jTable1.setModel(model);

    // model.fireTableRowsInserted(ERROR, ERROR)


// Create a couple of columns
model.addColumn("Col4");
model.addColumn("Col25");

     model.addRow(new Object[]{"v16", "v82"});
*/

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        int i = jTable1.getSelectedRow();
        //System.out.println("YOU SELECTED ROW : " + i);
        if(i >= 0){
            model.removeRow(i);
        }
}//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    JLabel label_loginname = new JLabel("Enter user name:");
    JTextField loginname = new JTextField(15);
    // loginname.setText("EnterLoginNameHere"); // Pre-set some text

    // Components related to "password" field
    JLabel label_password = new JLabel("Enter password:");
    JPasswordField password = new JPasswordField();

    JLabel label_confirm_password = new JLabel("Confirm password:");
    JPasswordField confirm_password = new JPasswordField();

  //   Username = loginname.getText();
  //   Password = password.getText();

  //   System.out.println("Username : " + Username);
  //   System.out.println("Password : " + Password);

         Object[] array = { label_loginname,
                       loginname,
                       label_password,
                       password,
                       label_confirm_password,
                       confirm_password
                         };

        int result = JOptionPane.showConfirmDialog(null, array, "Create New User",
                                            JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.PLAIN_MESSAGE);

        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();

String inputUsername = loginname.getText();
char[] inputPassword = password.getPassword();
char[] inputConfirmPassword = confirm_password.getPassword();


String str_inputPassword = "";

for(int i = 0; i < inputPassword.length ; i++){
	str_inputPassword = str_inputPassword + inputPassword[i];
}

System.out.println("str_inputPassword : " + str_inputPassword);

if (Arrays.equals(inputPassword, inputConfirmPassword) ){
      if (inputUsername.compareTo("") != 0){
            if (str_inputPassword.compareTo("") != 0){
                    if(inputPassword.length >= 6){
                            if(inputUsername.length() >=4 ){
                                model.addRow(new Object[]{inputUsername, str_inputPassword});


                                String Port = "3306";
                                String Database = "bosnian";
                                String Host = "localhost";

                                 String CONNECTION_STRING = String.format("jdbc:mysql://%s:%s/%s", Host, Port, Database);
                                 System.out.println("CONNECTION_STRING : " + CONNECTION_STRING);

                               try {

                                 Class.forName(driver);
                                 // Connect with a url string
                                 Connection con = DriverManager.getConnection(CONNECTION_STRING, Username, Password);

                                 Statement sta = con.createStatement();
                                 // sta.executeUpdate("INSERT INTO user_data (username,password) VALUES('monty', 'works')");
                                 String sta_string = String.format("INSERT INTO user_data (username,password) VALUES('%s', '%s')", inputUsername, str_inputPassword);
                                 sta.executeUpdate(sta_string);
                                 sta.close();

                               } catch(Exception e){
                                   e.printStackTrace();
                               }
                            } else { JOptionPane.showMessageDialog(jPanel1, "Username has to be equal to or longer than 4 chars");}
                    } else { JOptionPane.showMessageDialog(jPanel1, "Password has to be equal to or longer than 6 chars");}
            } else { JOptionPane.showMessageDialog(jPanel1, "Password is blank "); }
      } else { JOptionPane.showMessageDialog(jPanel1, "Username is blank "); }
} else { JOptionPane.showMessageDialog(jPanel1, "Password doesnt match. Please Confirm password"); }

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:

        NewWorkBucketFrame.pack();
        NewWorkBucketFrame.setVisible(true);

        list1_model.addElement("whateva1");
        list2_model.addElement("whateva2");

                         

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
         boolean item_exists = false;

         if (jList1.isSelectionEmpty() == false){

        for(int i =0; i< list2_model.getSize(); i++){
        System.out.println("list1_model.get(WIDTH) : " + list2_model.get(i).toString());

        if(list2_model.get(i).toString().compareTo(jList1.getSelectedValue().toString()) == 0){
            System.out.println(list2_model.get(i).toString() +" Item already exists as " + jList1.getSelectedValue().toString());
            item_exists = true;
        }

        }

        if(item_exists == false){
         list2_model.addElement(jList1.getSelectedValue());
        }

         }
        
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        if (jList2.isSelectionEmpty() == false){
         list2_model.removeElement(jList2.getSelectedValue());
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        list2_model.removeAllElements();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:

       AddUserFrame.pack();
       AddUserFrame.setVisible(true);
        

    }//GEN-LAST:event_jButton15ActionPerformed

    private void UserFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserFieldActionPerformed

    private void CreateUserOldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateUserOldActionPerformed
        // TODO add your handling code here:
        String User = UserField.getText();
        String FirstName = FirstNameField.getText();
        String LastName = LastNameField.getText();
        String UserPassword =  new String(UserPasswordField.getPassword());
        String ConfirmPassword =  new String(UserConfirmPasswordField.getPassword());

        String Workbucket = "";

        UserManagement usermgmt = new UserManagement();
        boolean validated = usermgmt.ValidateCreateUserForm(jPanel5, User,FirstName,LastName,UserPassword,ConfirmPassword, Workbucket);

        if(validated){
        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","","smartpos","3306","localhost");
        mysql.insertintoTable(connection,"Users", User, FirstName, LastName, UserPassword);

        AddUserFrame.dispose();
        }
    }//GEN-LAST:event_CreateUserOldActionPerformed

    private void ConnecttoDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnecttoDatabaseActionPerformed
        // TODO add your handling code here:
        MySQL mysql = new MySQL();
        mysql.logintoMySQLdatabase("root","","bosnian","3306","localhost");
        
    }//GEN-LAST:event_ConnecttoDatabaseActionPerformed

    private void CreateTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateTableActionPerformed
        // TODO add your handling code here:
        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","","smartpos","3306","localhost");
        mysql.createTable(connection,"Users", "User", "FirstName", "LastName", "Password", "Workbucket");

    }//GEN-LAST:event_CreateTableActionPerformed

    private void InsertIntoTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertIntoTableActionPerformed
        // TODO add your handling code here:
        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","","smartpos","3306","localhost");
        mysql.insertintoTable(connection,"workbucket_admin", "clark", "kent");
    }//GEN-LAST:event_InsertIntoTableActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ZLOOKK().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame AddUserFrame;
    private javax.swing.JButton ConnecttoDatabase;
    private javax.swing.JButton CreateTable;
    private javax.swing.JButton CreateUserOld;
    private javax.swing.JButton DeleteUser;
    private javax.swing.JTextField FirstNameField;
    private javax.swing.JButton InsertIntoTable;
    private javax.swing.JTextField LastNameField;
    private javax.swing.JFrame NewWorkBucketFrame;
    private javax.swing.JPasswordField UserConfirmPasswordField;
    private javax.swing.JTextField UserField;
    private javax.swing.JPasswordField UserPasswordField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldDatabase;
    private javax.swing.JTextField jTextFieldHost;
    private javax.swing.JTextField jTextFieldPassword;
    private javax.swing.JTextField jTextFieldPort;
    private javax.swing.JTextField jTextFieldUser;
    // End of variables declaration//GEN-END:variables

}
