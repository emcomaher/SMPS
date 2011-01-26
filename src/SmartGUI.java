
import SmartPOSClasses.CalendarOperations;
import SmartPOSClasses.Chart;
import SmartPOSClasses.DateUtils;
import SmartPOSClasses.EnterKeyPressed;
import SmartPOSClasses.MyComboBoxEditor;
import SmartPOSClasses.MyComboBoxRenderer;
import SmartPOSClasses.MySQL;
import SmartPOSClasses.MyTableModel;
import SmartPOSClasses.ProductManagement;
import SmartPOSClasses.SalesInstanceManagement;
import SmartPOSClasses.SelectionListener;
import SmartPOSClasses.UserManagement;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.DefaultFontMapper;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.String;
import java.net.URL;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SmartGUI.java
 *
 * Created on Apr 1, 2010, 5:21:56 PM
 */

/**
 *
 * @author Emir
 */
public class SmartGUI extends javax.swing.JFrame {

    //Weird count is a counter used to make sure that the sales sheet has been focused only once
    //to initialize barcode selection otherwise the selection is weird

    int weird_count = 1;
 
    DefaultTableModel user_model;
    DefaultTableModel inventory_model;
    DefaultTableModel salessheet_model;
    DefaultTableModel results_model_po_kolicini;
    DefaultTableModel results_model_po_iznosu;

    /** Creates new form SmartGUI */
    public SmartGUI() {
        initComponents();

       Date defaultDate = new Date();
    //   System.out.println("New Date: " + defaultDate);
        jXDatePicker1.setDate(defaultDate);
        jXDatePicker2.setDate(defaultDate);

        DateFormat[] formats = {new SimpleDateFormat("E dd/MM/yyyy")};
        jXDatePicker1.setFormats(formats);
        jXDatePicker2.setFormats(formats);
        
 //ADD COMBO BOX EDITOR BEGIN
       //    System.out.println("wird count 0 : " + weird_count);
//DefaultTableModel model = (DefaultTableModel)TableUsers.getModel();

// Add some columns
//model.addColumn("A", new Object[]{"item1"});
//model.addColumn("B", new Object[]{"item2"});

// These are the combobox values
String[] values = new String[]{"User", "Manager", "Administrator"};

// Set the combobox editor on the 1st visible column
int vColIndex = 4;
TableColumn col = TableUsers.getColumnModel().getColumn(vColIndex);
col.setCellEditor(new MyComboBoxEditor(values));

// If the cell should appear like a combobox in its
// non-editing state, also set the combobox renderer
col.setCellRenderer(new MyComboBoxRenderer(values));

        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");

        //GENERATE TABLE PO KOLICINI
               //The model is set to MyTableModel which disallows editing
       ResultsTablePoKolicini.setModel( (DefaultTableModel) new MyTableModel() );
       String sql_query = "SELECT Barkod, Naziv, SUM(Kolicina) as Kolicina, Cijena, (SUM(Kolicina)*Cijena) AS Iznos FROM sales_completed_child GROUP BY Naziv ORDER BY Kolicina DESC;";
        mysql.generateTablebasedonResultofSQL(connection, ResultsTablePoKolicini, results_model_po_iznosu, sql_query);

        //GENERATE TABLE PO IZNOSU
        //The model is set to MyTableModel which disallows editing
       ResultsTablePoIznosu.setModel( (DefaultTableModel) new MyTableModel() );
       String sql_query2 = "select Barkod, Naziv, SUM(Kolicina) as Kolicina, Cijena, (SUM(Kolicina)*Cijena) AS Iznos FROM sales_completed_child GROUP BY Naziv ORDER BY ABS(SUM(Kolicina)*Cijena) DESC;";
        mysql.generateTablebasedonResultofSQL(connection, ResultsTablePoIznosu, results_model_po_kolicini, sql_query2);

       //ADDs KEY LISTENER TO TableWorkbuckets
   //    CellSelectListener listener2 = new CellSelectListener(TableUsers, connection);
   //    TableUsers.getSelectionModel().addListSelectionListener(listener2);
   //    TableUsers.getColumnModel().getSelectionModel().addListSelectionListener(listener2);

        //ADDs KEY LISTENER TO SALES SHEET TABLE
        SelectionListener listener = new SelectionListener(SalesSheetTable, connection, LabelTotalniIznos);
        SalesSheetTable.getSelectionModel().addListSelectionListener(listener);
        SalesSheetTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

        //Display USERTableData
        
        user_model = mysql.DisplayUserTableData(user_model, connection, TableUsers);
        inventory_model = mysql.DisplayInventoryTableData(inventory_model, connection, InventoryTable);
        salessheet_model=(DefaultTableModel) SalesSheetTable.getModel();

    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jButton3 = new javax.swing.JButton();
        AddUserFrame = new javax.swing.JFrame();
        CreateUser1 = new javax.swing.JButton();
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
        WorkbucketComboBox = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        AddProductFrame = new javax.swing.JFrame();
        AddProduct1 = new javax.swing.JButton();
        BarkodField = new javax.swing.JTextField();
        NazivArtiklaField = new javax.swing.JTextField();
        KolicinaField = new javax.swing.JTextField();
        CijenaField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableUsers = new javax.swing.JTable();
        CreateUser = new javax.swing.JButton();
        DeleteUser = new javax.swing.JButton();
        CreateTableUsers = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SalesSheetTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        LabelTotalniIznos = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        AddProduct = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        InventoryTable = new javax.swing.JTable();
        DeleteProduct = new javax.swing.JButton();
        CreateTableProductsButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ResultsTablePoKolicini = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ResultsTablePoIznosu = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel56 = new javax.swing.JPanel();
        LabelBrojPostojecihDanaValue = new javax.swing.JLabel();
        LabelBrojPostojecihDana = new javax.swing.JLabel();
        LabelBrojDanaValue = new javax.swing.JLabel();
        LabelBrojDana = new javax.swing.JLabel();
        LabelCountingPeriod = new javax.swing.JLabel();
        LabelCountingPeriodValue = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        LabelTotalValueofSalesValue = new javax.swing.JLabel();
        LabelTotalValueofSales = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        LabelWorkingDayValue = new javax.swing.JLabel();
        LabelDaily = new javax.swing.JLabel();
        LabelDailyValue = new javax.swing.JLabel();
        LabelHourly = new javax.swing.JLabel();
        LabelAverageCounting = new javax.swing.JLabel();
        LabelHourlyValue = new javax.swing.JLabel();
        LabelVozila = new javax.swing.JLabel();
        LabelWorkingDay = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        LabelHigherTotalDayValue = new javax.swing.JLabel();
        LabelHigherIntervalValue = new javax.swing.JLabel();
        LabelHigherTotalDay = new javax.swing.JLabel();
        LabelHigherInterval = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        LabelOverallQuantityofItemsSold = new javax.swing.JLabel();
        LabelOverallNumberofSales = new javax.swing.JLabel();
        LabelOverallQuantityofItemsSoldValue = new javax.swing.JLabel();
        LabelOverallNumberofSalesValue = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Print = new javax.swing.JButton();
        ExportPDF = new javax.swing.JButton();
        ExportExcel = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        Prikaz = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        ActionMenu = new javax.swing.JMenu();
        PrintMenu = new javax.swing.JMenuItem();
        ExcelMenu = new javax.swing.JMenuItem();
        PDFMenu = new javax.swing.JMenuItem();
        LogMenu = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        SettingsMenu = new javax.swing.JMenu();
        JezikMenu = new javax.swing.JMenu();
        EnglishMenu = new javax.swing.JMenuItem();
        ArabicMenu = new javax.swing.JMenuItem();
        Deutsch = new javax.swing.JMenuItem();
        IzgledMenu = new javax.swing.JMenu();
        Zima = new javax.swing.JMenuItem();
        TronMenu = new javax.swing.JMenuItem();
        HelpMenu = new javax.swing.JMenu();
        AboutMenu = new javax.swing.JMenu();

        jButton3.setText("Frame Button");

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jButton3)
                .addContainerGap(230, Short.MAX_VALUE))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jButton3)
                .addContainerGap(211, Short.MAX_VALUE))
        );

        AddUserFrame.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AddUserFrameKeyReleased(evt);
            }
        });

        CreateUser1.setText("Create User");
        CreateUser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateUser1ActionPerformed(evt);
            }
        });
        CreateUser1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CreateUser1KeyReleased(evt);
            }
        });

        UserField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserFieldActionPerformed(evt);
            }
        });
        UserField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UserFieldKeyReleased(evt);
            }
        });

        FirstNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FirstNameFieldKeyReleased(evt);
            }
        });

        UserPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UserPasswordFieldKeyReleased(evt);
            }
        });

        UserConfirmPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UserConfirmPasswordFieldKeyReleased(evt);
            }
        });

        LastNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                LastNameFieldKeyReleased(evt);
            }
        });

        jLabel10.setText("User :");

        jLabel11.setText("First :");

        jLabel12.setText("Last :");

        jLabel13.setText("Password :");

        jLabel14.setText("Confirm :");

        WorkbucketComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "User", "Manager", "Administrator" }));
        WorkbucketComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                WorkbucketComboBoxKeyReleased(evt);
            }
        });

        jLabel8.setText("Workbucket :");

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
                    .addComponent(jLabel14)
                    .addComponent(jLabel8))
                .addGap(33, 33, 33)
                .addGroup(AddUserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(WorkbucketComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, 111, Short.MAX_VALUE)
                    .addComponent(CreateUser1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
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
                .addGroup(AddUserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WorkbucketComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(11, 11, 11)
                .addComponent(CreateUser1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        AddProduct1.setText("Add");
        AddProduct1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddProduct1ActionPerformed(evt);
            }
        });
        AddProduct1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AddProduct1KeyReleased(evt);
            }
        });

        BarkodField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BarkodFieldKeyReleased(evt);
            }
        });

        NazivArtiklaField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NazivArtiklaFieldKeyReleased(evt);
            }
        });

        KolicinaField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                KolicinaFieldKeyReleased(evt);
            }
        });

        CijenaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CijenaFieldActionPerformed(evt);
            }
        });
        CijenaField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CijenaFieldKeyReleased(evt);
            }
        });

        jLabel18.setText("Barkod : ");

        jLabel21.setText("Naziv : ");

        jLabel22.setText("Kolicina : ");

        jLabel23.setText("Cijena : ");

        javax.swing.GroupLayout AddProductFrameLayout = new javax.swing.GroupLayout(AddProductFrame.getContentPane());
        AddProductFrame.getContentPane().setLayout(AddProductFrameLayout);
        AddProductFrameLayout.setHorizontalGroup(
            AddProductFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddProductFrameLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(AddProductFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel18)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addGap(26, 26, 26)
                .addGroup(AddProductFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CijenaField, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(KolicinaField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(NazivArtiklaField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(BarkodField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                .addGap(116, 116, 116))
            .addGroup(AddProductFrameLayout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(AddProduct1)
                .addContainerGap(177, Short.MAX_VALUE))
        );
        AddProductFrameLayout.setVerticalGroup(
            AddProductFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddProductFrameLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(AddProductFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BarkodField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(AddProductFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NazivArtiklaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(AddProductFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(KolicinaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(AddProductFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CijenaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(47, 47, 47)
                .addComponent(AddProduct1)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jMenu4.setText("File");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar2.add(jMenu5);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseReleased(evt);
            }
        });
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
        });
        jTabbedPane1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTabbedPane1PropertyChange(evt);
            }
        });

        jTabbedPane4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTabbedPane4KeyReleased(evt);
            }
        });

        TableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User", "FirstName", "LastName", "Password", "Workbucket"
            }
        ));
        TableUsers.setRowHeight(21);
        TableUsers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TableUsers.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TableUsersPropertyChange(evt);
            }
        });
        jScrollPane3.setViewportView(TableUsers);
        TableUsers.getColumnModel().getColumn(4).setPreferredWidth(100);

        CreateUser.setText("Create User");
        CreateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateUserActionPerformed(evt);
            }
        });

        DeleteUser.setText("Delete User");
        DeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteUserActionPerformed(evt);
            }
        });

        CreateTableUsers.setText("Create Table Users");
        CreateTableUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateTableUsersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DeleteUser)
                    .addComponent(CreateUser)
                    .addComponent(CreateTableUsers))
                .addContainerGap(203, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(CreateUser)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteUser)
                        .addGap(18, 18, 18)
                        .addComponent(CreateTableUsers)))
                .addContainerGap(296, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Manage Users", jPanel12);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addGap(76, 76, 76))
        );

        jTabbedPane1.addTab("Administration", jPanel1);

        jPanel2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel2FocusGained(evt);
            }
        });

        SalesSheetTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Barkod", "Naziv", "Kolicina", "Cijena", "Iznos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SalesSheetTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SalesSheetTableFocusGained(evt);
            }
        });
        SalesSheetTable.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                SalesSheetTableCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        SalesSheetTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                SalesSheetTablePropertyChange(evt);
            }
        });
        SalesSheetTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SalesSheetTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(SalesSheetTable);

        jLabel1.setText("Lokacija");

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("PDV : ");

        jLabel3.setText("Popust : ");

        jLabel4.setText("Iznos : ");

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("Total : ");

        LabelTotalniIznos.setText("TotalniIznos");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(LabelTotalniIznos, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LabelTotalniIznos)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel7.setText("Datum");

        jLabel19.setText("Vrijeme");

        jButton1.setText("<HTML>CREATE TABLE<P>SALES COMPLETED PARENT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("<HTML>NEW SALES<P>INSTANCE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Date Test");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Zavrsi Prodaju");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton8.setText("<HTML>CREATE TABLE<P>SALES COMPLETED CHILD");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("ArrayListTest");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("GET CURRENT ROW AND COL");

        jButton11.setText("GET VALUES FROM COL");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(jLabel19)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton9)
                                        .addComponent(jButton4))))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10)
                            .addComponent(jButton11))))
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel7)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel19)
                                .addGap(28, 28, 28))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5)
                                .addGap(11, 11, 11)))
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton11)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sales Sheet", jPanel2);

        AddProduct.setText("Add Product");
        AddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddProductActionPerformed(evt);
            }
        });

        InventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Barkod", "Naziv", "Kolicina", "Cijena", "Iznos"
            }
        ));
        InventoryTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                InventoryTableFocusGained(evt);
            }
        });
        InventoryTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                InventoryTablePropertyChange(evt);
            }
        });
        InventoryTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                InventoryTableKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(InventoryTable);
        InventoryTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        InventoryTable.getColumnModel().getColumn(4).setPreferredWidth(10);

        DeleteProduct.setText("Delete Product");
        DeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteProductActionPerformed(evt);
            }
        });

        CreateTableProductsButton.setText("<HTML>Create Table<P>Products");
        CreateTableProductsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateTableProductsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CreateTableProductsButton, 0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(DeleteProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(AddProduct)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteProduct)
                        .addGap(68, 68, 68)
                        .addComponent(CreateTableProductsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Add/View Available Products", jPanel8);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inventory", jPanel3);

        ResultsTablePoKolicini.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(ResultsTablePoKolicini);

        jLabel25.setText("Najprodavaniji artikli :");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel25))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Po Kolicini", jPanel15);

        ResultsTablePoIznosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(ResultsTablePoIznosu);

        jLabel26.setText("Artikli sortirani po ukupnom iznosu prodaje :");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jLabel26))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Po Iznosu", jPanel16);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("tab3", jPanel17);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("tab4", jPanel18);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("tab5", jPanel19);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Tabele", jPanel11);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("Results Prikaz");

        jPanel56.setBackground(new java.awt.Color(255, 255, 255));
        jPanel56.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LabelBrojPostojecihDanaValue.setText("broj postojecih dana");

        LabelBrojPostojecihDana.setFont(new java.awt.Font("Tahoma", 1, 11));
        LabelBrojPostojecihDana.setText("Broj dana za koje postoje podaci: ");

        LabelBrojDanaValue.setText("broj dana");

        LabelBrojDana.setFont(new java.awt.Font("Tahoma", 1, 11));
        LabelBrojDana.setText("Broj Dana:");

        LabelCountingPeriod.setFont(new java.awt.Font("Tahoma", 1, 11));
        LabelCountingPeriod.setText("Period:");

        LabelCountingPeriodValue.setText("period");

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addComponent(LabelBrojPostojecihDana)
                        .addGap(18, 18, 18)
                        .addComponent(LabelBrojPostojecihDanaValue))
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addComponent(LabelCountingPeriod)
                        .addGap(22, 22, 22)
                        .addComponent(LabelCountingPeriodValue))
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addComponent(LabelBrojDana)
                        .addGap(18, 18, 18)
                        .addComponent(LabelBrojDanaValue)))
                .addContainerGap())
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelCountingPeriod)
                    .addComponent(LabelCountingPeriodValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelBrojDana)
                    .addComponent(LabelBrojDanaValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelBrojPostojecihDana)
                    .addComponent(LabelBrojPostojecihDanaValue)))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        LabelTotalValueofSalesValue.setText("total value");

        LabelTotalValueofSales.setFont(new java.awt.Font("Tahoma", 1, 11));
        LabelTotalValueofSales.setText("Total Value of Sales :");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LabelTotalValueofSales)
                .addGap(18, 18, 18)
                .addComponent(LabelTotalValueofSalesValue)
                .addGap(21, 21, 21))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(LabelTotalValueofSalesValue)
                .addComponent(LabelTotalValueofSales))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(364, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LabelWorkingDayValue.setText("radnim danom");

        LabelDaily.setFont(new java.awt.Font("Tahoma", 1, 11));
        LabelDaily.setText("Dnevni");

        LabelDailyValue.setText("dnevni");

        LabelHourly.setFont(new java.awt.Font("Tahoma", 1, 11));
        LabelHourly.setText("Po satu");

        LabelAverageCounting.setFont(new java.awt.Font("Tahoma", 1, 11));
        LabelAverageCounting.setText("Prosjek:");

        LabelHourlyValue.setText("satni");

        LabelVozila.setFont(new java.awt.Font("Tahoma", 1, 11));
        LabelVozila.setText("Prometa:");

        LabelWorkingDay.setFont(new java.awt.Font("Tahoma", 1, 11));
        LabelWorkingDay.setText("Radni dan");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelAverageCounting)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(LabelVozila)))
                .addGap(28, 28, 28)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelHourly)
                    .addComponent(LabelHourlyValue))
                .addGap(36, 36, 36)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelDailyValue)
                    .addComponent(LabelDaily))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelWorkingDay)
                    .addComponent(LabelWorkingDayValue))
                .addGap(20, 20, 20))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelAverageCounting)
                    .addComponent(LabelHourly)
                    .addComponent(LabelDaily)
                    .addComponent(LabelWorkingDay, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelHourlyValue)
                    .addComponent(LabelDailyValue)
                    .addComponent(LabelVozila)
                    .addComponent(LabelWorkingDayValue))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        LabelHigherTotalDayValue.setText("najvisi total za dan");

        LabelHigherIntervalValue.setText("najvisi interval");

        LabelHigherTotalDay.setFont(new java.awt.Font("Tahoma", 1, 11));
        LabelHigherTotalDay.setText("Najviši total za dan:");

        LabelHigherInterval.setFont(new java.awt.Font("Tahoma", 1, 11));
        LabelHigherInterval.setText("Najviši interval:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Artikal koji se najviše prodavao:");

        jLabel20.setText("najvisi artikal");

        jLabel24.setText("Articles that brought the highest profit");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelHigherTotalDay)
                            .addComponent(LabelHigherInterval))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LabelHigherIntervalValue, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelHigherTotalDayValue, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20))
                    .addComponent(jLabel24))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelHigherTotalDayValue, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelHigherTotalDay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelHigherInterval)
                    .addComponent(LabelHigherIntervalValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(306, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        LabelOverallQuantityofItemsSold.setFont(new java.awt.Font("Tahoma", 1, 11));
        LabelOverallQuantityofItemsSold.setText("OVERALL QUANTITY of items sold :");

        LabelOverallNumberofSales.setFont(new java.awt.Font("Tahoma", 1, 11));
        LabelOverallNumberofSales.setText("OVERALL NUMBER OF SALES : ");

        LabelOverallQuantityofItemsSoldValue.setText("overall quantity");

        LabelOverallNumberofSalesValue.setText("overall num of sales");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(LabelOverallNumberofSales)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LabelOverallNumberofSalesValue))
                            .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(LabelOverallQuantityofItemsSold)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LabelOverallQuantityofItemsSoldValue))
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap(30, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelOverallNumberofSales)
                    .addComponent(LabelOverallNumberofSalesValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelOverallQuantityofItemsSold)
                    .addComponent(LabelOverallQuantityofItemsSoldValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jLabel15.setText("Od :");

        jLabel16.setText("Do :");

        Print.setText("Print");

        ExportPDF.setText("Export PDF");

        ExportExcel.setText("Export Excel");

        Prikaz.setText("Prikaz Rezultata");
        Prikaz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrikazActionPerformed(evt);
            }
        });

        jButton6.setText("Help");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton12.setText("Get Dates");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Get Total Sum");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Get LV of PK ID");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jXDatePicker2, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 293, Short.MAX_VALUE)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(Prikaz))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Print)
                                    .addComponent(ExportPDF)
                                    .addComponent(ExportExcel)
                                    .addComponent(jButton6))))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton12)
                            .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton14))
                        .addGap(55, 55, 55)))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Prikaz)
                            .addComponent(jButton12))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(Print)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ExportPDF)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ExportExcel)
                                .addGap(29, 29, 29)
                                .addComponent(jButton6))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton13)
                                .addGap(18, 18, 18)
                                .addComponent(jButton14))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Prikaz", jPanel7);

        jPanel6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel6KeyReleased(evt);
            }
        });

        jLabel17.setText("Add JFreechart Stuff Here");

        jButton7.setText("Pie Chart");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton15.setText("Ukupna Vrijednost Po Mjesecu");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Ukupna Kolicina Po Mjesecu");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Get Num Of Days For Month");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("Ukupna Vrijednost Po Danu za Mjesec");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Ukupna Kolicina Po Danu za Mjesec");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton18)
                    .addComponent(jButton19)
                    .addComponent(jButton7)
                    .addComponent(jLabel17)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton17, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(593, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton17)
                .addGap(18, 18, 18)
                .addComponent(jButton15)
                .addGap(18, 18, 18)
                .addComponent(jButton16)
                .addGap(18, 18, 18)
                .addComponent(jButton18)
                .addGap(22, 22, 22)
                .addComponent(jButton19)
                .addContainerGap(234, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Charts", jPanel6);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Reports", jPanel4);

        ActionMenu.setText("Akcije");

        PrintMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/printer_icon.png"))); // NOI18N
        PrintMenu.setText("Odštampaj");
        ActionMenu.add(PrintMenu);

        ExcelMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/excel_icon.jpg"))); // NOI18N
        ExcelMenu.setText("Eksport u Excel");
        ActionMenu.add(ExcelMenu);

        PDFMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pdf_icon.jpg"))); // NOI18N
        PDFMenu.setText("Eksport u PDF");
        ActionMenu.add(PDFMenu);

        LogMenu.setText("LOG Programa");
        ActionMenu.add(LogMenu);

        jMenu3.setText("jMenu3");

        jMenuItem5.setText("jMenuItem5");
        jMenu3.add(jMenuItem5);

        ActionMenu.add(jMenu3);

        jMenuItem4.setText("jMenuItem4");
        ActionMenu.add(jMenuItem4);
        ActionMenu.add(jSeparator1);

        jMenuItem2.setText("jMenuItem2");
        ActionMenu.add(jMenuItem2);
        ActionMenu.add(jSeparator2);

        jMenuItem3.setText("jMenuItem3");
        ActionMenu.add(jMenuItem3);

        jMenuBar1.add(ActionMenu);

        SettingsMenu.setText("Postavke");

        JezikMenu.setText("Jezik");

        EnglishMenu.setText("English");
        JezikMenu.add(EnglishMenu);

        ArabicMenu.setText("العربي");
        JezikMenu.add(ArabicMenu);

        Deutsch.setText("Deutsch");
        JezikMenu.add(Deutsch);

        SettingsMenu.add(JezikMenu);

        IzgledMenu.setText("Izgled");

        Zima.setText("Zima");
        IzgledMenu.add(Zima);

        TronMenu.setText("Tron");
        IzgledMenu.add(TronMenu);

        SettingsMenu.add(IzgledMenu);

        jMenuBar1.add(SettingsMenu);

        HelpMenu.setText("Pomoć");
        HelpMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HelpMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(HelpMenu);

        AboutMenu.setText("O Programu");
        jMenuBar1.add(AboutMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 886, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CreateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateUserActionPerformed
        // TODO add your handling code here:
       AddUserFrame.pack();
       AddUserFrame.setVisible(true);
        
    }//GEN-LAST:event_CreateUserActionPerformed

    private void CreateUser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateUser1ActionPerformed
        // TODO add your handling code here:

        UserManagement um = new UserManagement();
        um.CreateUser(jPanel12,
                      UserField,
                      FirstNameField,
                      LastNameField,
                      UserPasswordField,
                      UserConfirmPasswordField,
                      WorkbucketComboBox,
                      AddUserFrame,
                      user_model);
        
/*
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
            Connection connection = mysql.logintoMySQLdatabase("root","","smartpos","3306","localhost");
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
 */
        
}//GEN-LAST:event_CreateUser1ActionPerformed

    private void UserFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserFieldActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_UserFieldActionPerformed

    private void DeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteUserActionPerformed
        // TODO add your handling code here:      
        int i = TableUsers.getSelectedRow();
        //System.out.println("*** i :" + i);
        //IF NO TABLE ROW IS SELECTED DONT DO ANYTHING
        if(i == -1){
            JOptionPane.showMessageDialog(jPanel12, "Select a user to delete");
        }

        if(i != -1){

        Object[] options = {"Da",
                    "Ne",
                    "Odustani"};
        int n = JOptionPane.showOptionDialog(jPanel2,
                  "Da li želite da izbrišete"
                  + "ovog Korisnika ?",
                  "Izbrisati Korisnika",
                  JOptionPane.YES_NO_CANCEL_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[2]);

          //System.out.println("N IS: " + n);

          if(n == 0){
          //System.out.println("YES");

                      
                       //System.out.println("YOU SELECTED ROW : " + i);
                       if(i >= 0){                            
                            String User = user_model.getValueAt(i, 0).toString();
                            String FirstName = user_model.getValueAt(i, 1).toString();
                            String LastName = user_model.getValueAt(i, 2).toString();
                            String Password = user_model.getValueAt(i, 3).toString();
                            String Workbucket = user_model.getValueAt(i, 4).toString();
                            //System.out.println("*** " + User + "," + FirstName + ","+ LastName +" ***");
                            user_model.removeRow(i);
                            MySQL mysql = new MySQL();
                            Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");
                            mysql.deletefromUserTable(connection,"Users", User, FirstName, LastName, Password, Workbucket);
                       }
          }

        }
    }//GEN-LAST:event_DeleteUserActionPerformed

    private void SalesSheetTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SalesSheetTableKeyReleased
        // TODO add your handling code here:
       // JOptionPane.showMessageDialog(jPanel5, "Someting happend : key released");
/////THIS ONE WONT WORK WITH ANY CODE BECAUSE KEY IS RELEASED LIKE TEN TIMES FOR EACH BARCODE INPUT

  //      int focusRow =  SalesSheetTable.getSelectionModel().getAnchorSelectionIndex();
  ///        int focusCol =  SalesSheetTable.getColumnModel().getSelectionModel().getAnchorSelectionIndex();

          ///     SalesSheetTable.changeSelection(focusRow+1, 0, false,false);

   ///        System.out.println("SKEY RELEASED SSHTABLE  Currently in : " + "(" + focusRow + "," + focusCol + ")");


    ////       TableModel model = SalesSheetTable.getModel();
      ////     if(focusRow != -1 && focusCol != -1){
    ///        if(model.getValueAt(focusRow,focusCol) != null){
     ///           if(model.getValueAt(focusRow, focusCol).toString().compareTo("") != 0){
      ///              String str_barcode = model.getValueAt(focusRow, focusCol).toString();
      ///              System.out.println("str_barcode : " + str_barcode + " at (" + focusRow + "," + focusCol + ")");
      ///          }
     ///       }
           
    }//GEN-LAST:event_SalesSheetTableKeyReleased

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
        // TODO add your handling code here:
 ///       System.out.println("jTabbedPane1 FocusGained");
        SalesSheetTable.requestFocus();
             int focusRow =  SalesSheetTable.getSelectionModel().getAnchorSelectionIndex();
              int focusCol =  SalesSheetTable.getColumnModel().getSelectionModel().getAnchorSelectionIndex();
   ///             System.out.println("FOCUS GAINED EVENT OF TABBED PANE :  Currently in : " + "(" + focusRow + "," + focusCol + ")");
          
      //  SalesSheetTable.requestFocus();
     //   SalesSheetTable.editCellAt(0, 0);
  //      SalesSheetTable.requestFocus();
  //      SalesSheetTable.editCellAt(1, 0);
     //   int focusRow =  SalesSheetTable.getSelectionModel().getAnchorSelectionIndex();
     // int focusCol =  SalesSheetTable.getColumnModel().getSelectionModel().getAnchorSelectionIndex();

    }//GEN-LAST:event_jTabbedPane1FocusGained

    private void jTabbedPane1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseReleased
        // TODO add your handling code here:
///        SalesSheetTable.setSurrendersFocusOnKeystroke(true);
  //      SalesSheetTable.setCellSelectionEnabled(true);
   //     SalesSheetTable.changeSelection(0, 0, false, false);
    //    SalesSheetTable.getSelectionModel().setAnchorSelectionIndex(0);
   //   int focusRow =  SalesSheetTable.getSelectionModel().getAnchorSelectionIndex();
   //   int focusCol =  SalesSheetTable.getColumnModel().getSelectionModel().getAnchorSelectionIndex();

   //   System.out.println("focus Row : " + focusRow);
   //   System.out.println("focus Col : " + focusCol);
  ///      SalesSheetTable.setColumnSelectionAllowed(true);
  //      SalesSheetTable.setColumnSelectionInterval(0, 0);
      //  SalesSheetTable.setRowSelectionInterval(0, 0);
        
    }//GEN-LAST:event_jTabbedPane1MouseReleased

    private void jPanel2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel2FocusGained
        // TODO add your handling code here:
        System.out.println("jPanel2 Focus Gained");
     
    }//GEN-LAST:event_jPanel2FocusGained

    private void SalesSheetTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SalesSheetTableFocusGained
        // TODO add your handling code here:
      //  SalesSheetTable.requestFocus();
        
    }//GEN-LAST:event_SalesSheetTableFocusGained

    private void InventoryTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_InventoryTableFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_InventoryTableFocusGained

    private void InventoryTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InventoryTableKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_InventoryTableKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
         String pathToHS = "/appwithhelp/docs/appwithhelp-hs.xml";
    //Create a URL for the location of the help set
   // URL hsURL = getClass().getResource(pathToHS);
   // HelpSet hs = new HelpSet(null, hsURL);
    try {
         URL hsURL = getClass().getResource(pathToHS);
        HelpSet hs = new HelpSet(null, hsURL);
        HelpBroker hb = hs.createHelpBroker();
         hb.setDisplayed(true);
     // URL hsURL = getClass().getResource(pathToHS);
       // HelpSet hs = new HelpSet(null, hsURL);
    } catch (Exception ee) {
        // Print info to the console if there is an exception
        System.out.println( "HelpSet " + ee.getMessage());
        System.out.println("Help Set "+ pathToHS +" not found");
        return;
    }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jPanel6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel6KeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode() == KeyEvent.VK_A){
        JOptionPane.showMessageDialog(jPanel6, "sdfsdf");
     }
         System.out.println("some key released");
    }//GEN-LAST:event_jPanel6KeyReleased

    private void jTabbedPane4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane4KeyReleased
        // TODO add your handling code here:
        System.out.println("Ah indeed, the tabbed pane is focused");
    }//GEN-LAST:event_jTabbedPane4KeyReleased

    private void jTabbedPane1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1PropertyChange
        // TODO add your handling code here:
     ///   System.out.println("jTabbedPanel Property Changed");
    }//GEN-LAST:event_jTabbedPane1PropertyChange

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
  /*

     //   System.out.println("wird count 1 : " + weird_count);
  if(weird_count == 2){

 ////       System.out.println("jTabbedPanel State Changed");
        int CurrentIndex = jTabbedPane1.getSelectedIndex();
 ////       System.out.println("Current Index : " + CurrentIndex);

        String Title = jTabbedPane1.getTitleAt(CurrentIndex);
///            System.out.println("We are in " + Title);

        ///    SalesSheetTable.changeSelection(0, 0, false,false);
            if(CurrentIndex == 1){
              SalesSheetTable.requestFocus();
          //    SalesSheetTable.editCellAt(0, 0);
              int focusRow =  SalesSheetTable.getSelectionModel().getAnchorSelectionIndex();
              int focusCol =  SalesSheetTable.getColumnModel().getSelectionModel().getAnchorSelectionIndex();
             // SalesSheetTable.editCellAt(focusRow+1, 0);
             // SalesSheetTable.getModel().addTableModelListener(SalesSheetTable);
            SalesSheetTable.changeSelection(focusRow+1, 0, false,false);

 ///           System.out.println("*****  Currently in : " + "(" + focusRow + "," + focusCol + ")");
            TableModel model = SalesSheetTable.getModel();
           if(focusRow != -1 && focusCol != -1){
            if(model.getValueAt(focusRow,focusCol) != null){
                if(model.getValueAt(focusRow, focusCol).toString().compareTo("") != 0){
                    String str_barcode = model.getValueAt(focusRow, focusCol).toString();
 /////                   System.out.println("str_barcode : " + str_barcode);
                }
            }
           }
            
           }
   
  }
   weird_count++;
 //  System.out.println("wird count 2 : " + weird_count);

   
   */
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        // create a dataset...
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Category 1", 43.2);
        dataset.setValue("Category 2", 27.9);
        dataset.setValue("Category 3", 79.5);

        // create a chart...
        JFreeChart chart = ChartFactory.createPieChart(
        "Sample Pie Chart",
        dataset,
        true, // legend?
        true, // tooltips?
        false // URLs?
        );

        // create and display a frame...
        ChartFrame frame = new ChartFrame("Test", chart);
        frame.pack();
        frame.setVisible(true);

        // write the chart to a PDF file...
        File fileName = new File(System.getProperty("user.home") + "/jfreechart1.pdf");
        System.out.println("fileName : " + fileName.toString());

        Chart ch = new Chart();
        try {
            ch.saveChartAsPDF(fileName, chart, 400, 300, new DefaultFontMapper());
        } catch (IOException ex) {
            Logger.getLogger(SmartGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(SmartGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

}//GEN-LAST:event_jButton7ActionPerformed

    private void AddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddProductActionPerformed
        // TODO add your handling code here:
        AddProductFrame.pack();
        AddProductFrame.setVisible(true);
    }//GEN-LAST:event_AddProductActionPerformed

    private void CijenaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CijenaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CijenaFieldActionPerformed

    private void AddProduct1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddProduct1ActionPerformed
        // TODO add your handling code here:
        ProductManagement pm = new ProductManagement();
        pm.AddProduct(jPanel12,
                      BarkodField,
                      NazivArtiklaField,
                      KolicinaField,
                      CijenaField,
                      AddProductFrame,
                      inventory_model);
        /*
        String Barkod = BarkodField.getText();
        String NazivArtikla = NazivArtiklaField.getText();
        String Kolicina = KolicinaField.getText();
        String Cijena = CijenaField.getText();
        

        ProductManagement prodmgmt = new ProductManagement();
        boolean validated = prodmgmt.ValidateAddProductForm(jPanel12, Barkod, NazivArtikla, Kolicina, Cijena);

        if(validated){

        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","","smartpos","3306","localhost");
        mysql.insertintoTable(connection,"Products", Barkod, NazivArtikla, Kolicina, Cijena);

        inventory_model.addRow(new Object[]{Barkod, NazivArtikla, Kolicina, Cijena, "WHATEVER"});

        System.out.println("Product Added !!!");

        BarkodField.setText("");
        NazivArtiklaField.setText("");
        KolicinaField.setText("");
        CijenaField.setText("");

        AddProductFrame.dispose();
        
        }
        
         */

    }//GEN-LAST:event_AddProduct1ActionPerformed

    private void CreateTableProductsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateTableProductsButtonActionPerformed
        // TODO add your handling code here:
        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");
        mysql.createTable(connection,"Products", "Barkod", "Naziv", "Kolicina", "Cijena");
    }//GEN-LAST:event_CreateTableProductsButtonActionPerformed

    private void DeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteProductActionPerformed
        // TODO add your handling code here:
          int i = InventoryTable.getSelectedRow();
        //System.out.println("*** i :" + i);
        //IF NO TABLE ROW IS SELECTED DONT DO ANYTHING
        if(i == -1){
            JOptionPane.showMessageDialog(jPanel12, "Select item to delete");
        }

        if(i != -1){

        Object[] options = {"Da",
                    "Ne",
                    "Odustani"};
        int n = JOptionPane.showOptionDialog(jPanel2,
                  "Da li želite da izbrišete"
                  + "ovaj proizvod ?",
                  "Izbrisati Proizvod",
                  JOptionPane.YES_NO_CANCEL_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[2]);

          //System.out.println("N IS: " + n);

          if(n == 0){
          //System.out.println("YES");


                       //System.out.println("YOU SELECTED ROW : " + i);
                       if(i >= 0){
                            String Barkod = inventory_model.getValueAt(i, 0).toString();
                            String Naziv = inventory_model.getValueAt(i, 1).toString();
                            String Kolicina = inventory_model.getValueAt(i, 2).toString();
                            String Cijena = inventory_model.getValueAt(i, 3).toString();
                            //String Iznos = inventory_model.getValueAt(i, 4).toString();
                            //System.out.println("*** " + User + "," + FirstName + ","+ LastName +" ***");
                            inventory_model.removeRow(i);
                            MySQL mysql = new MySQL();
                            Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");
                            mysql.deletefromProductsTable(connection,"Products", Barkod, Naziv, Kolicina, Cijena);
                       }
          }

        }
    }//GEN-LAST:event_DeleteProductActionPerformed

    private void CreateUser1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CreateUser1KeyReleased
        // TODO add your handling code here:
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
        System.out.println("ENTER pressed for add user button");

        UserManagement um = new UserManagement();
        um.CreateUser(jPanel12,
                      UserField,
                      FirstNameField,
                      LastNameField,
                      UserPasswordField,
                      UserConfirmPasswordField,
                      WorkbucketComboBox,
                      AddUserFrame,
                      user_model);
        }

    }//GEN-LAST:event_CreateUser1KeyReleased

    private void AddUserFrameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddUserFrameKeyReleased
        // TODO add your handling code here:
         int key = evt.getKeyCode();
         System.out.println("Add User Frame is focused");

        if (key == KeyEvent.VK_ENTER) {
        System.out.println("ENTER pressed");
        }
        if (key == KeyEvent.VK_SPACE) {
        System.out.println("SPACE pressed");
        }

    }//GEN-LAST:event_AddUserFrameKeyReleased

    private void UserFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserFieldKeyReleased
        // IF THE ENTER KEY IS PRESSED PRESSES TAB TO MOVE TO THE NEXT FIELD

        EnterKeyPressed ekp = new EnterKeyPressed();
        ekp.EnterKeyPressed(evt.getKeyCode());
       
    }//GEN-LAST:event_UserFieldKeyReleased

    private void FirstNameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FirstNameFieldKeyReleased

        EnterKeyPressed ekp = new EnterKeyPressed();
        ekp.EnterKeyPressed(evt.getKeyCode());
        
    }//GEN-LAST:event_FirstNameFieldKeyReleased

    private void LastNameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LastNameFieldKeyReleased

        EnterKeyPressed ekp = new EnterKeyPressed();
        ekp.EnterKeyPressed(evt.getKeyCode());
        
    }//GEN-LAST:event_LastNameFieldKeyReleased

    private void UserPasswordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserPasswordFieldKeyReleased

        EnterKeyPressed ekp = new EnterKeyPressed();
        ekp.EnterKeyPressed(evt.getKeyCode());
        
    }//GEN-LAST:event_UserPasswordFieldKeyReleased

    private void UserConfirmPasswordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserConfirmPasswordFieldKeyReleased

        EnterKeyPressed ekp = new EnterKeyPressed();
        ekp.EnterKeyPressed(evt.getKeyCode());
        
    }//GEN-LAST:event_UserConfirmPasswordFieldKeyReleased

    private void WorkbucketComboBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WorkbucketComboBoxKeyReleased

        EnterKeyPressed ekp = new EnterKeyPressed();
        ekp.EnterKeyPressed(evt.getKeyCode());
    }//GEN-LAST:event_WorkbucketComboBoxKeyReleased

    private void BarkodFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BarkodFieldKeyReleased
        // TODO add your handling code here:
        EnterKeyPressed ekp = new EnterKeyPressed();
        ekp.EnterKeyPressed(evt.getKeyCode());
    }//GEN-LAST:event_BarkodFieldKeyReleased

    private void NazivArtiklaFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NazivArtiklaFieldKeyReleased
        // TODO add your handling code here:
        EnterKeyPressed ekp = new EnterKeyPressed();
        ekp.EnterKeyPressed(evt.getKeyCode());
    }//GEN-LAST:event_NazivArtiklaFieldKeyReleased

    private void KolicinaFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KolicinaFieldKeyReleased
        // TODO add your handling code here:
        EnterKeyPressed ekp = new EnterKeyPressed();
        ekp.EnterKeyPressed(evt.getKeyCode());
    }//GEN-LAST:event_KolicinaFieldKeyReleased

    private void CijenaFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CijenaFieldKeyReleased
        // TODO add your handling code here:
        EnterKeyPressed ekp = new EnterKeyPressed();
        ekp.EnterKeyPressed(evt.getKeyCode());
    }//GEN-LAST:event_CijenaFieldKeyReleased

    private void AddProduct1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddProduct1KeyReleased
        // TODO add your handling code here:
         int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
        System.out.println("ENTER pressed for add user button");

        ProductManagement pm = new ProductManagement();
        pm.AddProduct(jPanel12,
                      BarkodField,
                      NazivArtiklaField,
                      KolicinaField,
                      CijenaField,
                      AddProductFrame,
                      inventory_model);
        }
    }//GEN-LAST:event_AddProduct1KeyReleased

    private void SalesSheetTableCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_SalesSheetTableCaretPositionChanged
        // TODO add your handling code here:
     ///   System.out.println("cARET POSITION CHANGED");
    }//GEN-LAST:event_SalesSheetTableCaretPositionChanged

    private void InventoryTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_InventoryTablePropertyChange
        // TODO add your handling code here:
           int row = InventoryTable.getSelectedRow();
           int column = InventoryTable.getSelectedColumn();
           DefaultTableModel wb_model = (DefaultTableModel) InventoryTable.getModel();

        if(row > -1 && column > -1){
              if(wb_model.getValueAt(row,column) != null){
                if(wb_model.getValueAt(row, column).toString().compareTo("") != 0){
                    String New_Text = wb_model.getValueAt(row, column).toString();
                    System.out.println("NewText : " + New_Text);

                    String ColumnName = InventoryTable.getColumnModel().getColumn(column).getHeaderValue().toString();
                    System.out.println("ColumnName : " + ColumnName);

                    String Barkod = wb_model.getValueAt(row, 0).toString();
                    System.out.println("Barkod : " + Barkod);
                    ProductManagement pm = new ProductManagement();
                    pm.UpdateProduct(ColumnName, New_Text, Barkod);

                 }
              }
           }
    }//GEN-LAST:event_InventoryTablePropertyChange

    private void CreateTableUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateTableUsersActionPerformed
        // TODO add your handling code here:
         MySQL mysql = new MySQL();
         Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");
         mysql.createTable(connection,"Users", "User", "FirstName", "LastName", "Password", "Workbucket");
    }//GEN-LAST:event_CreateTableUsersActionPerformed

    private void TableUsersPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TableUsersPropertyChange
        // TODO add your handling code here:
           int row = TableUsers.getSelectedRow();
           int column = TableUsers.getSelectedColumn();
           DefaultTableModel wb_model = (DefaultTableModel) TableUsers.getModel();

           //IF THE CELL SELWCTED IS IN THE FIRST COLUMN CALL THE METHOD TO UPDATE THE USERNAME
        if(column == 0){

          if(row > -1 && column > -1){
              if(wb_model.getValueAt(row,column) != null){
                if(wb_model.getValueAt(row, column).toString().compareTo("") != 0){
                    String New_Text = wb_model.getValueAt(row, column).toString();
                    System.out.println("NewText : " + New_Text);

                    String ColumnName = TableUsers.getColumnModel().getColumn(column).getHeaderValue().toString();
           //         System.out.println("ColumnName : " + ColumnName);

                    String User = wb_model.getValueAt(row, 0).toString();
           //         System.out.println("User : " + User);
                    String FirstName = wb_model.getValueAt(row, 1).toString();
           //         System.out.println("FirstName : " + FirstName);
                    String LastName = wb_model.getValueAt(row, 2).toString();
           //         System.out.println("LastName : " + LastName);

                    UserManagement um = new UserManagement();
                    um.UpdateUserName(ColumnName, New_Text, User, FirstName, LastName);

                 }
              }
           }

          //OTHERWISE IF THE CELL SELECTED IS IN ANOTHER COLUMN CALL THE METHOD TO UPDATE USER DATA
        }else{

        if(row > -1 && column > -1){
              if(wb_model.getValueAt(row,column) != null){
                if(wb_model.getValueAt(row, column).toString().compareTo("") != 0){
                    String New_Text = wb_model.getValueAt(row, column).toString();
                    System.out.println("NewText : " + New_Text);

                    String ColumnName = TableUsers.getColumnModel().getColumn(column).getHeaderValue().toString();
           //         System.out.println("ColumnName : " + ColumnName);

                    String User = wb_model.getValueAt(row, 0).toString();
           //         System.out.println("User : " + User);
           //         String FirstName = wb_model.getValueAt(row, 1).toString();
           //         System.out.println("FirstName : " + FirstName);
           //         String LastName = wb_model.getValueAt(row, 2).toString();
           //         System.out.println("LastName : " + LastName);
           
                    UserManagement um = new UserManagement();
                    um.UpdateUserData(ColumnName, New_Text, User);

                 }
              }
           }

          }
    }//GEN-LAST:event_TableUsersPropertyChange

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
   
      Object[] options = {" Da ", " Ne "};
      int n = JOptionPane.showOptionDialog(jPanel2,
            "Nova Prodaja?",
            "Nova Prodaja",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[1]);

      if(n == 0){
          System.out.println("YES");

         SalesSheetTable.changeSelection(0, 0, false,false);
         SalesSheetTable.requestFocusInWindow();

          //CLEAR TABLE CONTENTS
        for( int nRow = 0 ; nRow < SalesSheetTable.getRowCount() ; nRow++ ){
           for( int nColumn = 0 ; nColumn < SalesSheetTable.getColumnCount(); nColumn++ ){
                SalesSheetTable.setValueAt("" , nRow , nColumn );
           }
        }

//SET ROW COUNT TO 4
          //   salessheet_model.setRowCount(4);
          for( int i = salessheet_model.getRowCount() - 1; i >= 4; i-- )
         {
            salessheet_model.removeRow(i);
         }

        } else {
          System.out.println("NO");
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        /*
     System.out.println(DateUtils.now("dd MMMMM yyyy"));
     System.out.println(DateUtils.now("yyyyMMdd"));
     System.out.println(DateUtils.now("dd.MM.yy"));
     System.out.println(DateUtils.now("MM/dd/yy"));
     System.out.println(DateUtils.now("yyyy.MM.dd G 'at' hh:mm:ss z"));
     System.out.println(DateUtils.now("EEE, MMM d, ''yy"));
     System.out.println(DateUtils.now("h:mm a"));
     System.out.println(DateUtils.now("H:mm:ss:SSS"));
     System.out.println(DateUtils.now("K:mm a,z"));
     System.out.println(DateUtils.now("yyyy.MMMMM.dd GGG hh:mm aaa"));
        */
      //  System.out.println(DateUtils.now("dd.MM.yyyy hh:mm:ss"));
        System.out.println(DateUtils.now("yyyy-MM-dd hh:mm:ss"));

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");
        mysql.createSalesCompletedParentTable(connection, "sales_completed_parent");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         //CLEAR TABLE CONTENTS

     String TotalSaleValue = LabelTotalniIznos.getText();

     SalesInstanceManagement sim = new SalesInstanceManagement();
     sim.getDatafromTable_And_InsertIntoMySqlSalesInstanceTable(SalesSheetTable, TotalSaleValue);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");
        mysql.createSalesCompletedChildTable(connection, "sales_completed_child");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
         ArrayList<String> BarkodArrayList = new ArrayList<String>();
         BarkodArrayList.add("Paris");
         BarkodArrayList.add("London");

         if(BarkodArrayList.contains("nEW yORK")){
              System.out.println("YES");
         }
         else{
                System.out.println("NO");
             }
System.out.println("index of London : " + BarkodArrayList.indexOf("London"));
System.out.println("index of Paris : " + BarkodArrayList.indexOf("Paris"));



    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        //String Barkod = "";
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
        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void SalesSheetTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_SalesSheetTablePropertyChange
        // TODO add your handling code here:
       int row = SalesSheetTable.getSelectedRow();
           int column = SalesSheetTable.getSelectedColumn();
           DefaultTableModel wb_model = (DefaultTableModel) SalesSheetTable.getModel();
       float TotalValue = 0;

      if(column == 2){

          //UPDATE IZNOS
        if(row > -1 && column > -1){
              if(wb_model.getValueAt(row,column) != null){
                if(wb_model.getValueAt(row, column).toString().compareTo("") != 0){
                    String New_Text = wb_model.getValueAt(row, column).toString();
                    System.out.println("NewText : " + New_Text);
                    int Kolicina = Integer.parseInt(New_Text);
                    int Cijena = Integer.parseInt(wb_model.getValueAt(row, column+1).toString());
                    float Iznos = Kolicina * Cijena;
                    System.out.println("NOVI IZNOS = " + Iznos);
                    wb_model.setValueAt(Iznos ,row, column+2);

 
                 }
              }
           }

               //UPDATE TOTALNI IZNOS
        for( int nRow = 0 ; nRow < SalesSheetTable.getRowCount() ; nRow++ ){
            String Current_Value = SalesSheetTable.getValueAt(nRow,4).toString();
            if(Current_Value != null){
                if(Current_Value.compareTo("") != 0){
                    TotalValue = TotalValue + Float.parseFloat(Current_Value);
                }
            }
        }

        LabelTotalniIznos.setText(Float.toString(TotalValue));

       }

    }//GEN-LAST:event_SalesSheetTablePropertyChange

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        CalendarOperations co = new CalendarOperations();
        //co.getDatesfromJXCalendars(jXDatePicker1, jXDatePicker2);
        String begin_date = co.getInitialDatefromJXCalendar(jXDatePicker1);
        String end_date = co.getFinalDatefromJXCalendar(jXDatePicker2);
        co.calculateNumofDaysbetweenDates(jPanel2, begin_date, end_date);
}//GEN-LAST:event_jButton12ActionPerformed

    private void PrikazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrikazActionPerformed
        // TODO add your handling code here:
     //SET PERIOD OD DO
     CalendarOperations co = new CalendarOperations();
     String begin_date = co.getInitialDatefromJXCalendar(jXDatePicker1);
     String end_date = co.getFinalDatefromJXCalendar(jXDatePicker2); 
     LabelCountingPeriodValue.setText(String.format("Od %s Do %s", begin_date, end_date));
     
     //SET NUMBER OF DAYS BETWEEN DATES
     int days_between = co.calculateNumofDaysbetweenDates(jPanel2, begin_date, end_date);
     LabelBrojDanaValue.setText(Integer.toString(days_between));   
     
     //SET OVERALL NUM OF SALES
     MySQL mysql = new MySQL();
     Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");
     String overall_num_of_sales = mysql.getNumberOfRowsInTableColumn(connection, "sales_completed_parent", "sale_pk_id");
     LabelOverallNumberofSalesValue.setText(overall_num_of_sales);

     //SET TOTAL VALUE OF SALES
     String sum_of_all_sales = mysql.getTotalSumfromTableColumn(connection, "sales_completed_parent", "total_sale_value", "", "");
     LabelTotalValueofSalesValue.setText(sum_of_all_sales);

     //SET TOTAL QUANTITY OF SALES
     String overall_quantity_of_items_sold = mysql.getTotalSumfromTableColumn(connection, "sales_completed_child", "Kolicina", "","");
     LabelOverallQuantityofItemsSoldValue.setText(overall_quantity_of_items_sold);

     
     
    }//GEN-LAST:event_PrikazActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");
        mysql.getTotalSumfromTableColumn(connection, "sales_completed_parent", "total_sale_value", "", "");
        mysql.getTotalSumfromTableColumn(connection, "sales_completed_child", "Cijena", "", "");
        mysql.getTotalSumfromTableColumn(connection, "sales_completed_child", "Iznos", "", "");
        mysql.getTotalSumfromTableColumn(connection, "sales_completed_child", "Kolicina", "Naziv","ExcelZa10Min");
        mysql.getTotalSumfromTableColumn(connection, "sales_completed_child", "Kolicina", "Naziv","SardinaJadranska");
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");
        mysql.getNumberOfRowsInTableColumn(connection, "sales_completed_parent", "sale_pk_id");
        
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:

        //GET DATA
        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");

        CalendarOperations co = new CalendarOperations();
        //CREATE DATASET
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String result = "";
        Double sum_for_month = 0.0;

        for(int i = 1; i<13; i++ ){
                 result = mysql.getTotalSumfromTableColumnForMonth(connection, "sales_completed_child", "Iznos", i);
                    if (result == null){
                    } else {
                        sum_for_month = Double.parseDouble(result);
                    }
             dataset.addValue(sum_for_month, "Total", co.convertIntNumtoStringMonth(i-1));
             sum_for_month = 0.0;
             result= "";
        }

 /*
            dataset.addValue(1.0, "Row 1", "Jan");
            dataset.addValue(5.0, "Row 1", "Feb");
            dataset.addValue(10.0, "Row 1", "Mar");
            dataset.addValue(2.0, "Row 1", "Apr");
            dataset.addValue(3.0, "Row 1", "Maj");
            dataset.addValue(2.0, "Row 1", "Juni");
            dataset.addValue(1.0, "Row 1", "Juli");
            dataset.addValue(5.0, "Row 1", "Avg");
            dataset.addValue(10.0, "Row 1", "Sep");
            dataset.addValue(2.0, "Row 1", "Okt");
            dataset.addValue(3.0, "Row 1", "Nov");
            dataset.addValue(2.0, "Row 1", "Dec");
*/

        //CREATE CHART
            JFreeChart chart = ChartFactory.createBarChart(
            "UKUPNA VRIJEDNOST PO MJESECU", // chart title
            "Mjesec", // domain axis label
            "Ukupna Vrijednost", // range axis label
            dataset, // data
            PlotOrientation.VERTICAL, // orientation
            false, // include legend
            true, // tooltips?
            false // URLs?
            );

            Paint p = new GradientPaint(0, 0, Color.white, 0, 1000, Color.gray);
            chart.setBackgroundPaint(p);
            
            Plot plot = chart.getPlot();
            plot.setBackgroundPaint(Color.LIGHT_GRAY);
            

            // get an axis reference...
            CategoryPlot category_plot = chart.getCategoryPlot();
         //   CategoryAxis domainAxis = category_plot.getDomainAxis();
            // change axis properties...
         //   domainAxis.setLabel("Mjesec");
            

            BarRenderer renderer = (BarRenderer) category_plot.getRenderer();
            renderer.setSeriesPaint(0, Color.GREEN);
        //  renderer.setSeriesPaint(0, Color.cyan);
            renderer.setDrawBarOutline(false);
            

         ///   renderer.setItemMargin(0.0);

       //DISPLAY CHART
            ChartFrame frame = new ChartFrame("Bar Test", chart);
            frame.pack();
            frame.setVisible(true);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
               //GET DATA
        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");

        CalendarOperations co = new CalendarOperations();
        //CREATE DATASET
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String result = "";
        Double sum_for_month = 0.0;

        for(int i = 1; i<13; i++ ){
                 result = mysql.getTotalSumfromTableColumnForMonth(connection, "sales_completed_child", "Kolicina", i);
                    if (result == null){
                    } else {
                        sum_for_month = Double.parseDouble(result);
                    }
             dataset.addValue(sum_for_month, "Kolicina", co.convertIntNumtoStringMonth(i-1));
             sum_for_month = 0.0;
             result= "";
        }

        //CREATE CHART
            JFreeChart chart = ChartFactory.createBarChart(
            "UKUPNA KOLICINA PO MJESECU", // chart title
            "Mjesec", // domain axis label
            "Ukupna Kolicina", // range axis label
            dataset, // data
            PlotOrientation.VERTICAL, // orientation
            false, // include legend
            true, // tooltips?
            false // URLs?
            );

            Paint p = new GradientPaint(0, 0, Color.white, 0, 1000, Color.gray);
            chart.setBackgroundPaint(p);

            Plot plot = chart.getPlot();
            plot.setBackgroundPaint(Color.LIGHT_GRAY);


            // get an axis reference...
            CategoryPlot category_plot = chart.getCategoryPlot();
         //   CategoryAxis domainAxis = category_plot.getDomainAxis();
            // change axis properties...
         //   domainAxis.setLabel("Mjesec");


            BarRenderer renderer = (BarRenderer) category_plot.getRenderer();
            renderer.setSeriesPaint(0, Color.GREEN);
        //  renderer.setSeriesPaint(0, Color.cyan);
            renderer.setDrawBarOutline(false);


         ///   renderer.setItemMargin(0.0);

       //DISPLAY CHART
            ChartFrame frame = new ChartFrame("Bar Test", chart);
            frame.pack();
            frame.setVisible(true);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
       CalendarOperations co = new CalendarOperations();
       int num_of_days = co.getNumOfDaysForMonthInYear(10, 2010);
       System.out.println("NUM OF DAYS : " + num_of_days);
       
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");

        //10 JE NOVEMBAR
        //11 JE DECEMBAR
        int month = 9;

        CalendarOperations co = new CalendarOperations();
        int num_of_days = co.getNumOfDaysForMonthInYear(month, 2010);

        //CREATE DATASET
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String result = "";
        Double sum_for_day = 0.0;

        for(int i = 1; i < num_of_days+1; i++){
            result = mysql.getTotalSumfromTableColumnForDay(connection, "sales_completed_child", "Iznos", month+1, i);
                if (result == null){
                    } else {
                        sum_for_day = Double.parseDouble(result);
                    }
            dataset.addValue(sum_for_day, "Total", Integer.toString(i));
            sum_for_day = 0.0;
            result = "";

        }

        //CREATE CHART
            JFreeChart chart = ChartFactory.createBarChart(
            String.format("UKUPNA VRIJEDNOST PO DANU ZA MJESEC %s", co.convertIntNumtoStringMonth(month)), // chart title
            "Dan", // domain axis label
            "Ukupna Vrijednost", // range axis label
            dataset, // data
            PlotOrientation.VERTICAL, // orientation
            false, // include legend
            true, // tooltips?
            false // URLs?
            );

            Paint p = new GradientPaint(0, 0, Color.white, 0, 1000, Color.gray);
            chart.setBackgroundPaint(p);

            Plot plot = chart.getPlot();
            plot.setBackgroundPaint(Color.LIGHT_GRAY);


            // get an axis reference...
            CategoryPlot category_plot = chart.getCategoryPlot();
         //   CategoryAxis domainAxis = category_plot.getDomainAxis();
            // change axis properties...
         //   domainAxis.setLabel("Mjesec");


            BarRenderer renderer = (BarRenderer) category_plot.getRenderer();
            renderer.setSeriesPaint(0, Color.GREEN);
        //  renderer.setSeriesPaint(0, Color.cyan);
            renderer.setDrawBarOutline(false);


         ///   renderer.setItemMargin(0.0);

       //DISPLAY CHART
            ChartFrame frame = new ChartFrame("Bar Test", chart);
            frame.pack();
            frame.setVisible(true);

    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
           MySQL mysql = new MySQL();
        Connection connection = mysql.logintoMySQLdatabase("root","ada","smartpos","3306","localhost");

        //10 JE NOVEMBAR
        //11 JE DECEMBAR
        int month = 9;

        CalendarOperations co = new CalendarOperations();
        int num_of_days = co.getNumOfDaysForMonthInYear(month, 2010);

        //CREATE DATASET
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String result = "";
        Double sum_for_day = 0.0;

        for(int i = 1; i < num_of_days+1; i++){
            result = mysql.getTotalSumfromTableColumnForDay(connection, "sales_completed_child", "Kolicina", month+1, i);
                if (result == null){
                    } else {
                        sum_for_day = Double.parseDouble(result);
                    }
            dataset.addValue(sum_for_day, "Total", Integer.toString(i));
            sum_for_day = 0.0;
            result = "";

        }

        //CREATE CHART
            JFreeChart chart = ChartFactory.createBarChart(
            String.format("UKUPNA KOLICINA PO DANU ZA MJESEC %s", co.convertIntNumtoStringMonth(month)), // chart title
            "Dan", // domain axis label
            "Ukupna Kolicina", // range axis label
            dataset, // data
            PlotOrientation.VERTICAL, // orientation
            false, // include legend
            true, // tooltips?
            false // URLs?
            );

            Paint p = new GradientPaint(0, 0, Color.white, 0, 1000, Color.gray);
            chart.setBackgroundPaint(p);

            Plot plot = chart.getPlot();
            plot.setBackgroundPaint(Color.LIGHT_GRAY);


            // get an axis reference...
            CategoryPlot category_plot = chart.getCategoryPlot();
         //   CategoryAxis domainAxis = category_plot.getDomainAxis();
            // change axis properties...
         //   domainAxis.setLabel("Mjesec");


            BarRenderer renderer = (BarRenderer) category_plot.getRenderer();
            renderer.setSeriesPaint(0, Color.GREEN);
        //  renderer.setSeriesPaint(0, Color.cyan);
            renderer.setDrawBarOutline(false);


         ///   renderer.setItemMargin(0.0);

       //DISPLAY CHART
            ChartFrame frame = new ChartFrame("Bar Test", chart);
            frame.pack();
            frame.setVisible(true);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void HelpMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HelpMenuMouseClicked
        // TODO add your handling code here:
        System.out.println("Mouse clicked");

        String pathToHS = "/appwithhelp/docs/appwithhelp-hs.xml";

        try {
            URL hsURL = getClass().getResource(pathToHS);
            HelpSet hs = new HelpSet(null, hsURL);
            HelpBroker hb = hs.createHelpBroker();
            hb.setDisplayed(true);
        } catch (Exception ee) {
            // Print info to the console if there is an exception
            System.out.println( "HelpSet " + ee.getMessage());
            System.out.println("Help Set "+ pathToHS +" not found");
            return;
        }
    }//GEN-LAST:event_HelpMenuMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SmartGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AboutMenu;
    private javax.swing.JMenu ActionMenu;
    private javax.swing.JButton AddProduct;
    private javax.swing.JButton AddProduct1;
    private javax.swing.JFrame AddProductFrame;
    private javax.swing.JFrame AddUserFrame;
    private javax.swing.JMenuItem ArabicMenu;
    private javax.swing.JTextField BarkodField;
    private javax.swing.JTextField CijenaField;
    private javax.swing.JButton CreateTableProductsButton;
    private javax.swing.JButton CreateTableUsers;
    private javax.swing.JButton CreateUser;
    private javax.swing.JButton CreateUser1;
    private javax.swing.JButton DeleteProduct;
    private javax.swing.JButton DeleteUser;
    private javax.swing.JMenuItem Deutsch;
    private javax.swing.JMenuItem EnglishMenu;
    private javax.swing.JMenuItem ExcelMenu;
    private javax.swing.JButton ExportExcel;
    private javax.swing.JButton ExportPDF;
    private javax.swing.JTextField FirstNameField;
    private javax.swing.JMenu HelpMenu;
    private javax.swing.JTable InventoryTable;
    private javax.swing.JMenu IzgledMenu;
    private javax.swing.JMenu JezikMenu;
    private javax.swing.JTextField KolicinaField;
    private javax.swing.JLabel LabelAverageCounting;
    private javax.swing.JLabel LabelBrojDana;
    private javax.swing.JLabel LabelBrojDanaValue;
    private javax.swing.JLabel LabelBrojPostojecihDana;
    private javax.swing.JLabel LabelBrojPostojecihDanaValue;
    private javax.swing.JLabel LabelCountingPeriod;
    private javax.swing.JLabel LabelCountingPeriodValue;
    private javax.swing.JLabel LabelDaily;
    private javax.swing.JLabel LabelDailyValue;
    private javax.swing.JLabel LabelHigherInterval;
    private javax.swing.JLabel LabelHigherIntervalValue;
    private javax.swing.JLabel LabelHigherTotalDay;
    private javax.swing.JLabel LabelHigherTotalDayValue;
    private javax.swing.JLabel LabelHourly;
    private javax.swing.JLabel LabelHourlyValue;
    private javax.swing.JLabel LabelOverallNumberofSales;
    private javax.swing.JLabel LabelOverallNumberofSalesValue;
    private javax.swing.JLabel LabelOverallQuantityofItemsSold;
    private javax.swing.JLabel LabelOverallQuantityofItemsSoldValue;
    private javax.swing.JLabel LabelTotalValueofSales;
    private javax.swing.JLabel LabelTotalValueofSalesValue;
    private javax.swing.JLabel LabelTotalniIznos;
    private javax.swing.JLabel LabelVozila;
    private javax.swing.JLabel LabelWorkingDay;
    private javax.swing.JLabel LabelWorkingDayValue;
    private javax.swing.JTextField LastNameField;
    private javax.swing.JMenuItem LogMenu;
    private javax.swing.JTextField NazivArtiklaField;
    private javax.swing.JMenuItem PDFMenu;
    private javax.swing.JButton Prikaz;
    private javax.swing.JButton Print;
    private javax.swing.JMenuItem PrintMenu;
    private javax.swing.JTable ResultsTablePoIznosu;
    private javax.swing.JTable ResultsTablePoKolicini;
    private javax.swing.JTable SalesSheetTable;
    private javax.swing.JMenu SettingsMenu;
    private javax.swing.JTable TableUsers;
    private javax.swing.JMenuItem TronMenu;
    private javax.swing.JPasswordField UserConfirmPasswordField;
    private javax.swing.JTextField UserField;
    private javax.swing.JPasswordField UserPasswordField;
    private javax.swing.JComboBox WorkbucketComboBox;
    private javax.swing.JMenuItem Zima;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    // End of variables declaration//GEN-END:variables

}
