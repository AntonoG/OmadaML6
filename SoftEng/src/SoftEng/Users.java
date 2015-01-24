package SoftEng;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;

import java.awt.Font;


public class Users extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtFldUsername;
	private JTextField txtFldLastName;
	private JTextField txtFldFirstName;
	private JTextField txtFldPassword;
	private JTable table_1;
	private JTextField txtFldLevel;
	private JTextField txtFldID;
	boolean checked=false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Users frame = new Users();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**	 * Create the frame.	 */
	public Users() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("ΔΙΑΧΕΙΡΙΣΗ ΧΡΗΣΤΩΝ");
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnExit.setBounds(685, 527, 89, 23);
		contentPane.add(btnExit);
		
		final JButton btnInsert = new JButton("\u0394\u0397\u039C\u0399\u039F\u03A5\u03A1\u0393\u0399\u0391");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
				
					
					String p_User=txtFldUsername.getText();
					String p_Last=txtFldLastName.getText();
					String p_First=txtFldFirstName.getText();
					String p_Password=txtFldPassword.getText();
					String p_Level=txtFldLevel.getText();
					
					
					if(txtFldUsername.getText().length()==0 && txtFldPassword.getText().length()==0 && txtFldFirstName.getText().length()==0)
					{
						JOptionPane.showMessageDialog(btnInsert, "Λαθος καταχωρηση!","ERROR", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						String insert="Insert Into users (username,sname,fname,password,level)"+"VALUES ('"+p_User+"','"+p_Last+"','"+p_First+"','"+ p_Password+"','"+p_Level+"')";
						stmtInsert.executeUpdate(insert);
						stmtInsert.close();
						JOptionPane.showMessageDialog(btnInsert, "Successfull Login!!");
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
					
		btnInsert.setBounds(3, 527, 161, 23);
		contentPane.add(btnInsert);
		
		JButton btnDelete = new JButton("\u0394\u0399\u0391\u0393\u03A1\u0391\u03A6\u0397");
		btnDelete.setBounds(432, 527, 143, 23);
		contentPane.add(btnDelete);
		
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
			String p_Username=txtFldUsername.getText();
			String p_LName=txtFldLastName.getText();
			String p_FName=txtFldFirstName.getText();
			int p_Password=Integer.parseInt(txtFldPassword.getText());
			String p_Level=txtFldLevel.getText();
			
			
			if(txtFldID.getText().length()==0 && txtFldUsername.getText().length()==0 && txtFldPassword.getText().length()==0)
			{
				JOptionPane.showMessageDialog(btnInsert, "Λαθος καταχωρηση!","ERROR", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				String update_username="Update users set username = '"+p_Username+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_username);
				String update_password="Update users set password= '"+p_Password+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_password);
				String update_lname="Update users set sname = '"+p_LName+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_lname);
				String update_fname="Update users set fname= '"+p_FName+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_fname);
				String update_level="Update users set level = '"+p_Level+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_level);
				
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
		btnSave.setBounds(236, 527, 165, 23);
		contentPane.add(btnSave);
		
		JLabel lblUserName = new JLabel("The UserName");
		lblUserName.setBounds(51, 42, 89, 14);
		contentPane.add(lblUserName);
		
		JLabel lblSurName = new JLabel("\u0395\u03C0i\u03B8\u03B5\u03C4\u03BF");
		lblSurName.setBounds(51, 83, 89, 14);
		contentPane.add(lblSurName);
		
		JLabel lblFirstName = new JLabel("\u039F\u03BD\u03BF\u03BC\u03B1");
		lblFirstName.setBounds(51, 122, 89, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(51, 164, 89, 14);
		contentPane.add(lblPassword);
		
		JLabel lblLevel = new JLabel("\u03A0\u03C1\u03BF\u03C3\u03B2\u03B1\u03C3\u03B9\u03BC\u03BF\u03C4\u03B7\u03C4\u03B1");
		lblLevel.setBounds(51, 202, 113, 14);
		contentPane.add(lblLevel);
		
		JLabel lblStatus = new JLabel("\u0395\u03BD\u03B5\u03C1\u03B3\u03BF\u03C2");
		lblStatus.setBounds(51, 242, 89, 14);
		contentPane.add(lblStatus);
		
		txtFldUsername = new JTextField();
		txtFldUsername.setBounds(161, 39, 265, 20);
		contentPane.add(txtFldUsername);
		txtFldUsername.setColumns(10);
		
		txtFldLastName = new JTextField();
		txtFldLastName.setBounds(161, 80, 265, 20);
		contentPane.add(txtFldLastName);
		txtFldLastName.setColumns(10);
		
		txtFldFirstName = new JTextField();
		txtFldFirstName.setBounds(161, 119, 265, 20);
		contentPane.add(txtFldFirstName);
		txtFldFirstName.setColumns(10);
		
		txtFldPassword = new JTextField();
		txtFldPassword.setBounds(161, 161, 265, 20);
		contentPane.add(txtFldPassword);
		txtFldPassword.setColumns(10);
		
		JCheckBox chckbxStatus = new JCheckBox("");
		chckbxStatus.setBounds(267, 233, 97, 23);
		contentPane.add(chckbxStatus);
		
		final JComboBox comboBoxLevel = new JComboBox();
		Connection connlevel = null;
		Statement stmtlevel = null;
		ResultSet rslevel = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
			String connectionUser="root";
			String connectionPassword="19896";
			connlevel = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
			stmtlevel = connlevel.createStatement();
			rslevel = stmtlevel.executeQuery("Select *  from userlevel");
			while (rslevel.next()){
				comboBoxLevel.addItem(rslevel.getString(3));
				}	
			comboBoxLevel.addActionListener(new ActionListener (){
				public void actionPerformed(ActionEvent vat){
					Connection connlevel2 = null;
					Statement stmtlevel2 = null;
					ResultSet rslevel2 = null;
					try{
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
					String connectionUser="root";
					String connectionPassword="19896";
					connlevel2 = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
					stmtlevel2 = connlevel2.createStatement();
					rslevel2 = stmtlevel2.executeQuery("Select * from userlevel where level_name='"+comboBoxLevel.getSelectedItem()+"'");
					if(rslevel2.next()){
						txtFldLevel.setText(rslevel2.getString(2));
							}
					}
					catch(Exception vat2){
						vat2.printStackTrace();
					}
					finally{
						try{ if (rslevel2 != null)rslevel2.close();}catch (SQLException vat2){vat2.printStackTrace();}
					
							}
						}	
					});
		}
		catch(Exception vat){
			vat.printStackTrace();
		}
		finally{
			try{ if (rslevel != null)rslevel.close();}catch (SQLException vat){vat.printStackTrace();}
			
			
			
			try{ if (stmtlevel != null)stmtlevel.close();}catch(SQLException vat){vat.printStackTrace();}
			
			try{ if (connlevel != null)connlevel.close();}catch(SQLException vat){vat.printStackTrace();}
		}
		comboBoxLevel.setBounds(236, 199, 89, 20);
		contentPane.add(comboBoxLevel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(464, 11, 310, 324);
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
			conn = (Connection) DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id,username,sname,fname FROM users");
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
		
		DefaultTableModel model = new DefaultTableModel(data,columnNames){
			
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
		table = new JTable(model);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		///"SELECT id,username,sname,fname FROM users"////
		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("UserName");
		table.getColumnModel().getColumn(2).setHeaderValue("Επιθετο");
		table.getColumnModel().getColumn(3).setHeaderValue("Ονομα");
		
		table.setBounds(571, 11, 215, 217);
		///contentPane.add(table);
		scrollPane.setViewportView(table);
		
		txtFldLevel = new JTextField();
		txtFldLevel.setEnabled(false);
		txtFldLevel.setEditable(false);
		txtFldLevel.setBounds(185, 199, 28, 20);
		contentPane.add(txtFldLevel);
		txtFldLevel.setColumns(10);
		
		JLabel lblSearch = new JLabel("\u0391\u039D\u0391\u0396\u0397\u03A4\u0397\u03A3\u0397");
		lblSearch.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSearch.setBounds(556, 342, 154, 28);
		contentPane.add(lblSearch);
		
		txtFldID = new JTextField();
		txtFldID.setBounds(558, 377, 52, 20);
		contentPane.add(txtFldID);
		txtFldID.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
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
				rsSrch = stmtSrch.executeQuery("Select *  from users where id='"+Srch_ID+"'");
				
				while (rsSrch.next()){
					String dbuserID = rsSrch.getString("ID");
					///String dbuserUserName = rsSrch.getString("username");
					if( dbuserID.equals(txtFldID.getText())){
						txtFldUsername.setText(rsSrch.getString("username"));
						txtFldLastName.setText(rsSrch.getString("sname"));
						txtFldFirstName.setText(rsSrch.getString("fname"));
						txtFldPassword.setText(rsSrch.getString("password"));
						txtFldLevel.setText(rsSrch.getString("level"));
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
		btnSearch.setBounds(655, 376, 89, 23);
		contentPane.add(btnSearch);
		
		JLabel lblSrchID = new JLabel("ID :");
		lblSrchID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSrchID.setBounds(522, 379, 36, 14);
		contentPane.add(lblSrchID);
	}
}
