package SoftEng;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import java.awt.Font;

public class Customers extends JFrame {

	private JPanel contentPane;
	private JTextField txtFldFName;
	private JTextField txtFldSName;
	private JTextField txtFldAFM;
	private JTextField txtFldTelephone;
	private JTextField txtFldAddress;
	private JTextField txtFldMobile;
	private JTextField txtFldDebit;
	private JTextField txtFldAmount;
	private JTable table;
	private JTextField txtFldCity;
	private JTextField txtFldID;
	boolean checked=false;
	/**	 * Launch the application.	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customers frame = new Customers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**	 * Create the frame.	 */
	public Customers() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Διαχειριση Πελατολογιου");
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnExit = new JButton("");
		btnExit.setIcon(new ImageIcon(Customers.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnExit.setBounds(753, 528, 29, 33);
		contentPane.add(btnExit);
		
		JLabel lblFName = new JLabel("\u0395\u03C0\u03C9\u03BD\u03C5\u03BC\u03B9\u03B1");
		lblFName.setBounds(10, 373, 64, 14);
		contentPane.add(lblFName);
		
		txtFldFName = new JTextField();
		txtFldFName.setBounds(84, 370, 192, 20);
		contentPane.add(txtFldFName);
		txtFldFName.setColumns(10);
		
		JLabel lblSName = new JLabel("\u039F\u03BD\u03BF\u03BC\u03B1");
		lblSName.setBounds(286, 373, 46, 14);
		contentPane.add(lblSName);
		
		txtFldSName = new JTextField();
		txtFldSName.setBounds(342, 373, 116, 20);
		contentPane.add(txtFldSName);
		txtFldSName.setColumns(10);
		
		JLabel lblAFM = new JLabel("\u0391\u03A6\u039C");
		lblAFM.setBounds(506, 373, 46, 14);
		contentPane.add(lblAFM);
		
		txtFldAFM = new JTextField();
		txtFldAFM.setBounds(562, 373, 116, 20);
		contentPane.add(txtFldAFM);
		txtFldAFM.setColumns(10);
		
		JLabel lblTelephone = new JLabel("\u03A4\u03B7\u03BB\u03B5\u03C6\u03C9\u03BD\u03BF");
		lblTelephone.setBounds(384, 418, 74, 14);
		contentPane.add(lblTelephone);
		
		txtFldTelephone = new JTextField();
		txtFldTelephone.setBounds(456, 415, 86, 20);
		contentPane.add(txtFldTelephone);
		txtFldTelephone.setColumns(10);
		
		JLabel lblAddress = new JLabel("\u0394\u03B9\u03B5\u03C5\u03B8\u03C5\u03BD\u03C3\u03B7");
		lblAddress.setBounds(10, 418, 64, 14);
		contentPane.add(lblAddress);
		
		txtFldAddress = new JTextField();
		txtFldAddress.setBounds(84, 415, 116, 20);
		contentPane.add(txtFldAddress);
		txtFldAddress.setColumns(10);
		
		JLabel lblMobile = new JLabel("\u039A\u03B9\u03BD\u03B7\u03C4\u03BF \u03A4\u03B7\u03BB\u03B5\u03C6\u03C9\u03BD\u03BF");
		lblMobile.setBounds(562, 418, 116, 14);
		contentPane.add(lblMobile);
		
		txtFldMobile = new JTextField();
		txtFldMobile.setBounds(672, 415, 102, 20);
		contentPane.add(txtFldMobile);
		txtFldMobile.setColumns(10);
		
		JLabel lblDebit = new JLabel("\u03A7\u03C1\u03B5\u03C9\u03C3\u03C4\u03B9\u03BA\u03BF");
		lblDebit.setBounds(10, 464, 74, 14);
		contentPane.add(lblDebit);
		
		txtFldDebit = new JTextField();
		txtFldDebit.setBounds(84, 461, 86, 20);
		contentPane.add(txtFldDebit);
		txtFldDebit.setColumns(10);
		
		JLabel lblAmount = new JLabel("\u03A5\u03C0\u03BF\u03BB\u03BF\u03B9\u03C0\u03BF");
		lblAmount.setBounds(212, 464, 64, 14);
		contentPane.add(lblAmount);
		
		txtFldAmount = new JTextField();
		txtFldAmount.setBounds(286, 461, 86, 20);
		contentPane.add(txtFldAmount);
		txtFldAmount.setColumns(10);
		
		final JButton btnInsert = new JButton("\u039A\u0391\u03A4\u0391\u03A7\u03A9\u03A1\u0397\u03A3\u0397");
		btnInsert.setBounds(0, 538, 117, 23);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){

		Connection connInsert = null;
		Statement stmtInsert = null;
		ResultSet rsInsert = null;
		
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
			String connectionUser="root";
			String connectionPassword="19896";
			connInsert = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
			stmtInsert = connInsert.createStatement();
		
			
			String p_FName=txtFldFName.getText();
			String p_SName=txtFldSName.getText();
			String p_AFM=txtFldAFM.getText();
			String p_Telephone=txtFldTelephone.getText();
			String p_Address=txtFldAddress.getText();
			String p_Mobile=txtFldMobile.getText();
			String p_Debit=txtFldDebit.getText();
			String p_Amount=txtFldAmount.getText();
			
			if(txtFldFName.getText().length()==0 && txtFldTelephone.getText().length()==0 && txtFldAFM.getText().length()==0)
			{
				JOptionPane.showMessageDialog(btnInsert, "Λαθος καταχωρηση!","ERROR", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				String insert="Insert Into customers (fname,sname,afm,telephone,address,mobile,debit,amount)"+"VALUES ('"+p_FName+"','"+p_SName +"','"+p_AFM +"','"+ p_Telephone+"','"+p_Address+"','"+p_Mobile +"','"+p_Debit +"','"+p_Amount+"')";
				stmtInsert.executeUpdate(insert);
				stmtInsert.close();
				JOptionPane.showMessageDialog(btnInsert, "Successfull Registered!!");
			}
		}
		
		catch(Exception i)
		{
			i.printStackTrace();
		}
		
		finally
		{
			try{ if (rsInsert != null)rsInsert.close();}catch (SQLException i){i.printStackTrace();}
			
			try{ if (stmtInsert != null)stmtInsert.close();}catch(SQLException i){i.printStackTrace();}
			
			try{ if (connInsert != null)connInsert.close();}catch(SQLException i){i.printStackTrace();}
		}
			}
		});
		contentPane.add(btnInsert);
		
		JButton btnClear = new JButton("\u039A\u0391\u0398\u0391\u03A1\u0399\u03A3\u039C\u039F\u03A3");
		btnClear.setBounds(127, 538, 102, 23);
		contentPane.add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 764, 350);
		contentPane.add(scrollPane);
		
		table = new JTable();
		Vector<Object> columnNames = new Vector<Object>();
		Vector<Object> data = new Vector<Object>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
			String connectionUser="root";
			String connectionPassword="19896";
			conn = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id,fname,sname,telephone,afm,address,city,mobile,debit,amount FROM customers");
			ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
			int columns = md.getColumnCount();
			
			for(int i=1;i<=columns;i++){
				columnNames.addElement(md.getColumnName(i)	);
			}
			
			while (rs.next())
			{
				Vector<Object> row = new Vector<Object>(columns);
				
				for(int i =1;i<=columns;i++){
					row.addElement(rs.getObject(i));
				}
				data.addElement(row);
								
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			try{ if (rs != null)rs.close();}catch (SQLException e){e.printStackTrace();}
			
			try{ if (stmt != null)stmt.close();}catch(SQLException e){e.printStackTrace();}
			
			try{ if (conn != null)conn.close();}catch(SQLException e){e.printStackTrace();}
		}
		
		DefaultTableModel tablecustomers = new DefaultTableModel(data,columnNames){
			
			public Class getColumnClass(int column){
				for (int row=0;row<getRowCount();row++){
					Object o = getValueAt(row,column);
					if(o != null)
					{
						return o.getClass();
					}
				}
				return Object.class;
			}
			
		};
		
		table = new JTable(tablecustomers);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setBounds(0, 2, 783, 360);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		table.getColumnModel().getColumn(6).setPreferredWidth(60);
		table.getColumnModel().getColumn(7).setPreferredWidth(50);
		table.getColumnModel().getColumn(8).setPreferredWidth(20);
		table.getColumnModel().getColumn(9).setPreferredWidth(20);
		///"SELECT fname,sname,telephone,afm,address,mobile,debit,amount FROM customers"////
		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Ονομα");
		table.getColumnModel().getColumn(2).setHeaderValue("Επιθετο");
		table.getColumnModel().getColumn(3).setHeaderValue("Τηλεφωνο");
		table.getColumnModel().getColumn(4).setHeaderValue("ΑΦΜ");
		table.getColumnModel().getColumn(5).setHeaderValue("Διεθυνση");
		table.getColumnModel().getColumn(6).setHeaderValue("Πολη");
		table.getColumnModel().getColumn(7).setHeaderValue("Κινητο");
		table.getColumnModel().getColumn(8).setHeaderValue("Χρεωστικο Υπολοιπο");
		table.getColumnModel().getColumn(9).setHeaderValue("Υπολοιπο");
		scrollPane.setViewportView(table);
		
		JLabel lblCity = new JLabel("\u03A0\u03BF\u03BB\u03B7");
		lblCity.setBounds(237, 418, 46, 14);
		contentPane.add(lblCity);
		
		txtFldCity = new JTextField();
		txtFldCity.setBounds(270, 415, 86, 20);
		contentPane.add(txtFldCity);
		txtFldCity.setColumns(10);
		
		JLabel label = new JLabel("\u0391\u039D\u0391\u0396\u0397\u03A4\u0397\u03A3\u0397 \u03A0\u0395\u039B\u0391\u03A4\u0397");
		label.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label.setBounds(506, 446, 172, 14);
		contentPane.add(label);
		
		JLabel lblID = new JLabel("ID \u03A0\u03B5\u03BB\u03B1\u03C4\u03B7 :");
		lblID.setBounds(459, 474, 62, 14);
		contentPane.add(lblID);
		
		txtFldID = new JTextField();
		txtFldID.setBounds(531, 471, 86, 20);
		contentPane.add(txtFldID);
		txtFldID.setColumns(10);
		
		JButton btnSearch = new JButton("\u0391\u039D\u0391\u0396\u0397\u03A4\u0397\u03A3\u0397");
		btnSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent s){
			Connection connSrch = null;
			Statement stmtSrch = null;
			ResultSet rsSrch = null;
			String Srch_ID = txtFldID.getText();
			try
			{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
				String connectionUser="root";
				String connectionPassword="19896";
				connSrch = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
				stmtSrch = connSrch.createStatement();
				rsSrch = stmtSrch.executeQuery("Select *  from customers where id='"+Srch_ID+"'");
				
				while (rsSrch.next()){
					String dbuserID = rsSrch.getString("ID");
					///String dbuserUserName = rsSrch.getString("username");
					if( dbuserID.equals(txtFldID.getText())){
						txtFldFName.setText(rsSrch.getString("fname"));
						txtFldSName.setText(rsSrch.getString("sname"));
						txtFldAFM.setText(rsSrch.getString("afm"));
						txtFldTelephone.setText(rsSrch.getString("telephone"));
						txtFldAddress.setText(rsSrch.getString("address"));
						txtFldMobile.setText(rsSrch.getString("mobile"));
						txtFldDebit.setText(rsSrch.getString("debit"));
						txtFldAmount.setText(rsSrch.getString("amount"));
						txtFldCity.setText(rsSrch.getString("city"));
					}
				}
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try{ if (rsSrch != null)rsSrch.close();}catch (SQLException s1){s1.printStackTrace();}
				
				try{ if (stmtSrch != null)stmtSrch.close();}catch(SQLException s1){s1.printStackTrace();}
				
				try{ if (connSrch != null)connSrch.close();}catch(SQLException s1){s1.printStackTrace();}
			}
			}
			});
		btnSearch.setBounds(644, 470, 116, 23);
		contentPane.add(btnSearch);
		
		JButton btnSave = new JButton("\u0391\u03A0\u039F\u0398\u0397\u039A\u0395\u03A5\u03A3\u0397");
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent sv) {
		
		Connection connSave = null;
		Statement stmtSave = null;
		ResultSet rsSave = null;
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
			String connectionUser="root";
			String connectionPassword="19896";
			connSave = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
			stmtSave = connSave.createStatement();
			////rsSave = stmtSave.executeQuery("Update products set");
			String p_id=txtFldID.getText();
			String p_SName=txtFldSName.getText();
			String p_FName=txtFldFName.getText();
			int p_AFM=Integer.parseInt(txtFldAFM.getText());
			int p_Telephone=Integer.parseInt(txtFldTelephone.getText());
			int p_Mobile=Integer.parseInt(txtFldMobile.getText());
			int p_Debit=Integer.parseInt(txtFldDebit.getText());
			int p_Amount=Integer.parseInt(txtFldAmount.getText());
			String p_Address=txtFldAddress.getText();
			String p_City=txtFldCity.getText();
			
			
			if(txtFldID.getText().length()==0 && txtFldFName.getText().length()==0 && txtFldSName.getText().length()==0 && txtFldAFM.getText().length()==0 && txtFldMobile.getText().length()==0 && txtFldCity.getText().length()==0 && txtFldAddress.getText().length()==0 && txtFldTelephone.getText().length()==0)
			{
				JOptionPane.showMessageDialog(btnInsert, "Λαθος καταχωρηση!","ERROR", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				String update_sname="Update customers set sname = '"+p_SName+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_sname);
				String update_fname="Update customers set fname= '"+p_FName+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_fname);
				String update_afm="Update customers set afm = '"+p_AFM+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_afm);
				String update_telephone="Update customers set telephone= '"+p_Telephone+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_telephone);
				String update_mobile="Update customers set mobile = '"+p_Mobile+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_mobile);
				String update_debit="Update customers set debit = '"+p_Debit+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_debit);
				String update_amount="Update customers set amount = '"+p_Amount+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_amount);
				String update_address="Update customers set address = '"+p_Address+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_address);
				String update_city="Update customers set city = '"+p_City+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_city);
				stmtSave.close();
				JOptionPane.showMessageDialog(btnInsert, "Successfull Saved!!");
			}
		}
		
		catch(Exception e)
		{
			System.out.println("not a number"); 
			e.printStackTrace();
		}
		finally
		{
			try{ if (rsSave != null)rsSave.close();}catch (SQLException e){e.printStackTrace();}
			
			try{ if (stmtSave != null)stmtSave.close();}catch(SQLException e){e.printStackTrace();}
			
			try{ if (connSave != null)connSave.close();}catch(SQLException e){e.printStackTrace();}
		}
			}
		});
		btnSave.setBounds(243, 538, 129, 23);
		contentPane.add(btnSave);
	}
}
