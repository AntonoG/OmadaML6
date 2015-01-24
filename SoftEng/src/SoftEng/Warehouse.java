package SoftEng;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.Observable;
import java.util.Observer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.JComboBox;
import javax.swing.DropMode;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class Warehouse extends JFrame {

	private JPanel contentPane;
	private JTextField txtFldName;
	private JTextField txtFldSerial;
	private JTextField txtFldID;
	private JTextField txtFldPrice;
	private JTextField txtFldSeller;
	private JTextField txtFldBarcode;
	private JTextField txtFldVAT;
	private JTextField txtFldCount;
	private JComboBox comboBoxVAT;
	private JTable table;
	private JTextField txtFldWarehouse;
	boolean checked=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Warehouse frame = new Warehouse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Warehouse() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("ΔΙΑΧΕΙΡΙΣΗ ΑΠΟΘΗΚΗΣ");
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
		
		final JButton btnInsert = new JButton("\u039A\u0391\u03A4\u0391\u03A7\u03A9\u03A1\u0397\u03A3\u0397");
		btnInsert.setBounds(10, 527, 125, 23);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent i){

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
		
			
			String p_Name=txtFldName.getText();
			String p_Serial=txtFldSerial.getText();
			String p_Count=txtFldCount.getText();
			String p_Price=txtFldPrice.getText();
			String p_Seller=txtFldSeller.getText();
			String p_Barcode=txtFldBarcode.getText();
			String p_VAT=txtFldVAT.getText();
			
			if(txtFldID.getText().length()==0 && txtFldName.getText().length()==0 && txtFldPrice.getText().length()==0)
			{
				JOptionPane.showMessageDialog(btnInsert, "Λαθος καταχωρηση!","ERROR", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				String insert="Insert Into products (Name,Serial,Price,seller_name,barcode,count,vat)"+"VALUES ('"+p_Name +"','"+p_Serial +"','"+ p_Price+"','"+p_Seller +"','"+p_Barcode +"','"+p_Count+"','"+p_VAT+"')";
				stmtInsert.executeUpdate(insert);
				String update_Warehouse="Update warehouse set products = products + '"+p_Count+"' ";
				stmtInsert.executeUpdate(update_Warehouse);
				stmtInsert.close();
				JOptionPane.showMessageDialog(btnInsert, "Successfull Inserted!!");
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			try{ if (rsInsert != null)rsInsert.close();}catch (SQLException e){e.printStackTrace();}
			
			try{ if (stmtInsert != null)stmtInsert.close();}catch(SQLException e){e.printStackTrace();}
			
			try{ if (connInsert != null)connInsert.close();}catch(SQLException e){e.printStackTrace();}
		}
			}
		});
		contentPane.add(btnInsert);
		
		final JButton btnDelete = new JButton("\u0394\u0399\u0391\u0393\u03A1\u0391\u03A6\u0397");
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent d){
				Connection connDelete = null;
				Statement stmtDelete = null;
				ResultSet rsDelete = null;
				
				try{
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
					String connectionUser="root";
					String connectionPassword="19896";
					connDelete = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
					stmtDelete = connDelete.createStatement();
					
					if(txtFldID.getText().length()==0 )
					{
						JOptionPane.showMessageDialog(btnDelete, "Λαθος καταχωρηση!","ERROR", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
					String p_ID=txtFldID.getText();
					String delete="Delete from products WHERE ID="+p_ID;
					stmtDelete.execute(delete);
					stmtDelete.close();
					JOptionPane.showMessageDialog(btnDelete, "Successfull Delete!!");
					}
				}
				catch(Exception d1){
					d1.printStackTrace();
				}
				finally{
					try{ if (rsDelete != null)rsDelete.close();}catch (SQLException i){i.printStackTrace();}
					
					try{ if (stmtDelete!= null)stmtDelete.close();}catch(SQLException i){i.printStackTrace();}
					
					try{ if (connDelete != null)connDelete.close();}catch(SQLException i){i.printStackTrace();}
				}
			}
		});
		btnDelete.setBounds(453, 527, 116, 23);
		contentPane.add(btnDelete);
		
		JButton btnClear = new JButton("\u039A\u0391\u0398\u0391\u03A1\u0399\u03A3\u039C\u039F\u03A3");
		btnClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent c){
				txtFldName.setText("");
				txtFldSerial.setText("");
				txtFldID.setText("");
				txtFldPrice.setText("");
				txtFldSeller.setText("");
				txtFldBarcode.setText("");
				txtFldID.setText("");
				txtFldVAT.setText("");
			}
		});
		btnClear.setBounds(308, 527, 116, 23);
		contentPane.add(btnClear);
		
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
			String p_Name=txtFldName.getText();
			int p_Serial= Integer.parseInt(txtFldSerial.getText());
			int p_Count=Integer.parseInt(txtFldCount.getText());
			int p_Price=Integer.parseInt(txtFldPrice.getText());
			String p_Seller=txtFldSeller.getText();
			int p_Barcode=Integer.parseInt(txtFldBarcode.getText());
			int p_VAT=Integer.parseInt(txtFldVAT.getText());
			
			if(txtFldID.getText().length()==0 && txtFldName.getText().length()==0 && txtFldPrice.getText().length()==0)
			{
				JOptionPane.showMessageDialog(btnInsert, "Λαθος καταχωρηση!","ERROR", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				String update_name="Update products set Name = '"+p_Name+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_name);
				String update_serial="Update products set Serial = '"+p_Serial+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_serial);
				String update_count="Update products set count = '"+p_Count+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_count);
				String update_price="Update products set price = '"+p_Price+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_price);
				String update_seller="Update products set seller_name = '"+p_Seller+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_seller);
				String update_barcode="Update products set barcode = '"+p_Barcode+"' where id = '"+p_id+" ' ";
				stmtSave.executeUpdate(update_barcode);
				String update_VAT="Update products set vat = '"+p_VAT+"' where id = '"+p_id+"'";
				stmtSave.executeUpdate(update_VAT);
				String update_Warehouse="Update warehouse set products = products + '"+p_Count+"'";
				stmtSave.executeUpdate(update_Warehouse);
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
		btnSave.setBounds(173, 527, 125, 23);
		contentPane.add(btnSave);
		
		JLabel lblName = new JLabel("\u039F\u039D\u039F\u039C\u0391 \u0395\u0399\u0394\u039F\u03A5\u03A3");
		lblName.setBounds(10, 87, 111, 14);
		contentPane.add(lblName);
		
		JLabel lblSerial = new JLabel("\u03A3\u0395\u0399\u03A1\u0399\u0391\u039A\u039F\u03A3 \u0391\u03A1\u0399\u0398\u039C\u039F\u03A3");
		lblSerial.setBounds(10, 127, 125, 14);
		contentPane.add(lblSerial);
		
		JLabel lblID = new JLabel("\u0391/\u0391");
		lblID.setBounds(10, 11, 24, 14);
		contentPane.add(lblID);
		
		JLabel lblPrice = new JLabel("\u03A4\u0399\u039C\u0397");
		lblPrice.setBounds(10, 171, 46, 14);
		contentPane.add(lblPrice);
		
		JLabel lblSeller = new JLabel("\u03A0\u03A1\u039F\u039C\u0397\u0398\u0395\u03A5\u03A4\u0397\u03A3");
		lblSeller.setBounds(10, 208, 77, 14);
		contentPane.add(lblSeller);
		
		JLabel lblWarehouse = new JLabel("\u0391\u03A0\u039F\u0398\u0397\u039A\u0397");
		lblWarehouse.setBounds(10, 288, 77, 14);
		contentPane.add(lblWarehouse);
		
		JLabel lblBarcode = new JLabel("BarCode");
		lblBarcode.setBounds(10, 324, 77, 14);
		contentPane.add(lblBarcode);
		
		txtFldName = new JTextField();
		txtFldName.setBounds(128, 84, 296, 20);
		contentPane.add(txtFldName);
		txtFldName.setColumns(10);
		
		txtFldSerial = new JTextField();
		txtFldSerial.setBounds(128, 124, 170, 20);
		contentPane.add(txtFldSerial);
		txtFldSerial.setColumns(10);
		
		txtFldID = new JTextField();
		txtFldID.setBounds(33, 8, 77, 20);
		contentPane.add(txtFldID);
		txtFldID.setColumns(10);
		
		txtFldPrice = new JTextField();
		txtFldPrice.setBounds(128, 168, 91, 20);
		contentPane.add(txtFldPrice);
		txtFldPrice.setColumns(10);
		
		txtFldSeller = new JTextField();
		txtFldSeller.setBounds(128, 205, 296, 20);
		contentPane.add(txtFldSeller);
		txtFldSeller.setColumns(10);
		
		txtFldBarcode = new JTextField();
		txtFldBarcode.setBounds(128, 321, 296, 20);
		contentPane.add(txtFldBarcode);
		txtFldBarcode.setColumns(10);
		
		
		final JComboBox comboWarehouse = new JComboBox();
		Connection connware = null;
		Statement stmtware = null;
		ResultSet rsware = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
			String connectionUser="root";
			String connectionPassword="19896";
			connware = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
			stmtware = connware.createStatement();
			rsware = stmtware.executeQuery("Select id,name from warehouse");
			while(rsware.next()){
				comboWarehouse.addItem(rsware.getString(2));
				}
			comboWarehouse.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent cw){
					Connection connware2 = null;
					Statement stmtware2 = null;
					ResultSet rsware2 = null;
					try{
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
					String connectionUser="root";
					String connectionPassword="19896";
					connware2 = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
					stmtware2 = connware2.createStatement();
					rsware2 = stmtware2.executeQuery("Select * from warehouse where name='"+comboWarehouse.getSelectedItem()+"'");
					if(rsware2.next()){
						txtFldWarehouse.setText(rsware2.getString(1));
						}
					}
					catch(Exception s1)
					{
						s1.printStackTrace();
					}
					
					finally
					{
						try{ if (rsware2 != null)rsware2.close();}catch (SQLException s1){s1.printStackTrace();}
						
						try{ if (stmtware2 != null)stmtware2.close();}catch(SQLException s1){s1.printStackTrace();}
						
						try{ if (connware2 != null)connware2.close();}catch(SQLException s1){s1.printStackTrace();}
					}
				}
							
				});
			}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		finally
		{
			try{ if (rsware != null)rsware.close();}catch (SQLException e){e.printStackTrace();}
			
			try{ if (stmtware != null)stmtware.close();}catch(SQLException e){e.printStackTrace();}
			
			try{ if (connware != null)connware.close();}catch(SQLException e){e.printStackTrace();}
		}
		comboWarehouse.setBounds(160, 285, 170, 20);
		contentPane.add(comboWarehouse);
		
		JLabel lblCount = new JLabel("\u03A0\u039F\u03A3\u039F\u03A4\u0397\u03A4\u0391");
		lblCount.setBounds(10, 388, 77, 14);
		contentPane.add(lblCount);
		
		txtFldCount = new JTextField();
		txtFldCount.setBounds(128, 385, 86, 20);
		contentPane.add(txtFldCount);
		txtFldCount.setColumns(10);
		
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
					rsSrch = stmtSrch.executeQuery("SELECT id,Name,Serial,Price,seller_name,barcode,count,vat,warehouse FROM products where id='"+Srch_ID+"'");
					
					while (rsSrch.next() ){
						String dbprodID = rsSrch.getString("ID");
						
						if (dbprodID.equals(txtFldID.getText())){
							
							txtFldName.setText(rsSrch.getString("name"));
							txtFldSerial.setText(rsSrch.getString("serial"));
							txtFldPrice.setText(rsSrch.getString("price"));
							txtFldBarcode.setText(rsSrch.getString("barcode"));
							txtFldCount.setText(rsSrch.getString("count"));
							txtFldVAT.setText(rsSrch.getString("vat"));
							txtFldWarehouse.setText(rsSrch.getString("warehouse"));
						}
						
					}
				}
				
				catch(Exception s1)
				{
					s1.printStackTrace();
				}
				
				finally
				{
					try{ if (rsSrch != null)rsSrch.close();}catch (SQLException s1){s1.printStackTrace();}
					
					try{ if (stmtSrch != null)stmtSrch.close();}catch(SQLException s1){s1.printStackTrace();}
					
					try{ if (connSrch != null)connSrch.close();}catch(SQLException s1){s1.printStackTrace();}
				}
			}

			
		});
		btnSearch.setBounds(159, 7, 139, 23);
		contentPane.add(btnSearch);
		
		txtFldVAT = new JTextField();
		txtFldVAT.setEnabled(false);
		txtFldVAT.setEditable(false);
		txtFldVAT.setBounds(128, 352, 24, 20);
		contentPane.add(txtFldVAT);
		txtFldVAT.setColumns(10);
		
		JLabel lblVAT = new JLabel("\u03A6\u03A0\u0391");
		lblVAT.setBounds(10, 363, 46, 14);
		contentPane.add(lblVAT);
		
		final JComboBox comboBoxVAT = new JComboBox();
		Connection connvat = null;
		Statement stmtvat = null;
		ResultSet rsvat = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
			String connectionUser="root";
			String connectionPassword="19896";
			connvat = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
			stmtvat = connvat.createStatement();
			rsvat = stmtvat.executeQuery("Select *  from vat");
			while (rsvat.next()){
				comboBoxVAT.addItem(rsvat.getString(2));
				}	
			comboBoxVAT.addActionListener(new ActionListener (){
				public void actionPerformed(ActionEvent vat){
					Connection connvat2 = null;
					Statement stmtvat2 = null;
					ResultSet rsvat2 = null;
					try{
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
					String connectionUser="root";
					String connectionPassword="19896";
					connvat2 = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
					stmtvat2 = connvat2.createStatement();
					rsvat2 = stmtvat2.executeQuery("Select * from vat where vatnumber='"+comboBoxVAT.getSelectedItem()+"'");
					if(rsvat2.next()){
						txtFldVAT.setText(rsvat2.getString(1));
							}
					}
					catch(Exception vat2){
						vat2.printStackTrace();
					}
					finally{
						try{ if (rsvat2 != null)rsvat2.close();}catch (SQLException vat2){vat2.printStackTrace();}
					
							}
						}	
					});
		}
		catch(Exception vat){
			vat.printStackTrace();
		}
		finally{
			try{ if (rsvat != null)rsvat.close();}catch (SQLException vat){vat.printStackTrace();}
				
			try{ if (stmtvat != null)stmtvat.close();}catch(SQLException vat){vat.printStackTrace();}
			
			try{ if (connvat != null)connvat.close();}catch(SQLException vat){vat.printStackTrace();}
		}
		comboBoxVAT.setBounds(159, 352, 56, 20);
		contentPane.add(comboBoxVAT);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(434, 32, 339, 469);
		contentPane.add(scrollPane);
		
		table = new JTable();
		//// Γεμισμα του πινακα με στοιχεια απο την Βαση/////
		final Vector<Object> columnNames = new Vector<Object>();
		final Vector<Object> data = new Vector<Object>();
		
		
		
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
			rs = stmt.executeQuery("SELECT id,name,count,price,serial FROM products");
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
		
		DefaultTableModel products = new DefaultTableModel(data,columnNames){
		/**			 *			 */
			private static final long serialVersionUID = 1L;

			///String[] columnNames = {"ID", "Name","Count","Price","Serial"};
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
	
		
		table = new JTable(products);
		table.setFillsViewportHeight(true);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(165);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Name");
		table.getColumnModel().getColumn(2).setHeaderValue("Count");
		table.getColumnModel().getColumn(3).setHeaderValue("Price");
		table.getColumnModel().getColumn(4).setHeaderValue("Serial");
		////table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setEnabled(false);
		table.setBounds(467, 24, 310, 410);
		
		///contentPane.add(table);
		scrollPane.setViewportView(table);
		
		txtFldWarehouse = new JTextField();
		txtFldWarehouse.setEnabled(false);
		txtFldWarehouse.setEditable(false);
		txtFldWarehouse.setBounds(128, 285, 24, 20);
		contentPane.add(txtFldWarehouse);
		txtFldWarehouse.setColumns(10);
		
		
	}
}
