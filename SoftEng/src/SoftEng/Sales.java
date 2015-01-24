package SoftEng;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Sales extends JFrame {

	private JPanel contentPane;
	private JTextField txtFldUsername;
	private JTextField txtFldShowSales;
	private JTextField txtFldCountDB;
	private JTextField txtFldPercentDB;
	private JTextField txtFldCustomers;
	private JTextField txtFldWarehousePercent;
	private JTextField txtFldWarehouseSum;
	private JTable tableWarehouse;

	/**	 * Launch the application.	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sales frame = new Sales();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**	 * Create the frame.	 */
	public Sales() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 760, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				setVisible(false);
			}
		});
		btnExit.setBounds(649, 507, 89, 23);
		contentPane.add(btnExit);
		
		txtFldPercentDB = new JTextField();
		txtFldPercentDB.setEditable(false);
		txtFldPercentDB.setBounds(605, 114, 86, 20);
		contentPane.add(txtFldPercentDB);
		txtFldPercentDB.setColumns(10);
		
		
		txtFldCountDB = new JTextField();
		txtFldCountDB.setEditable(false);
		txtFldCountDB.setBounds(605, 74, 86, 20);
		contentPane.add(txtFldCountDB);
		txtFldCountDB.setColumns(10);
		

		txtFldCustomers = new JTextField();
		txtFldCustomers.setEditable(false);
		txtFldCustomers.setBounds(605, 148, 86, 20);
		contentPane.add(txtFldCustomers);
		txtFldCustomers.setColumns(10);
		
		
		String shwavrwhall = null;
		String shpercall = null;
		String shcustall = null;
		Connection connWareAll = null;
		Statement stmtWareAll = null;
		ResultSet rsWareAll = null;
		ResultSet rsCustAll = null;
		//ResultSet rsCust = null;
		int sumwhall = 0;
		int tempwhall = 0;
		int averperall = 0;
		int tempperall = 0;
		int sumcustall = 0;
		
		double averageall = 0;
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
			String connectionUser="root";
			String connectionPassword="19896";
			connWareAll = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
			stmtWareAll = connWareAll.createStatement();
			rsWareAll = stmtWareAll.executeQuery("Select * from warehouse");
			
			////rsCust = stmtWareAll.executeQuery("select * from customers");
			while(rsWareAll.next()){
				tempwhall = rsWareAll.getInt("products");
				sumwhall = sumwhall + tempwhall;
				tempperall = rsWareAll.getInt("max_prod");
				averperall = averperall + tempperall;
				averageall = (sumwhall*100)/averperall;
				shwavrwhall = Integer.toString(sumwhall);
				shpercall = Double.toString(averageall);						
			}
			rsCustAll = stmtWareAll.executeQuery("Select count(id) From customers");
			while(rsCustAll.next()){
				sumcustall = sumcustall + rsCustAll.getInt(1);
				shcustall = Integer.toString(sumcustall);
				txtFldCustomers.setText(shcustall);
			}
			
		}
		catch(Exception wa){
			wa.printStackTrace();
		}
		finally{
			try{ if (rsWareAll != null)rsWareAll.close();}catch (SQLException wa){wa.printStackTrace();}
			
			try{ if (stmtWareAll != null)stmtWareAll.close();}catch(SQLException wa){wa.printStackTrace();}
			
			try{ if (connWareAll != null)connWareAll.close();}catch(SQLException wa){wa.printStackTrace();}
		}
		
		txtFldPercentDB.setText(shpercall+"%");
		
		
		txtFldCountDB.setText(shwavrwhall);
		
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setBounds(24, 51, 63, 14);
		contentPane.add(lblUsername);
		
		txtFldUsername = new JTextField();
		txtFldUsername.setBounds(101, 48, 86, 20);
		contentPane.add(txtFldUsername);
		txtFldUsername.setColumns(10);
		
		final JComboBox comboBoxUsername = new JComboBox();
			Connection connUser = null;
			Statement stmtUser = null;
			ResultSet rsWS = null;
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
				String connectionUser="root";
				String connectionPassword="19896";
				connUser = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
				stmtUser = connUser.createStatement();
				rsWS = stmtUser.executeQuery("Select username From Users");
					while (rsWS.next()){
						comboBoxUsername.addItem(rsWS.getString(1));
					}
					comboBoxUsername.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent un){
							Connection connsetuser = null;
							Statement stmtsetuser = null;
							ResultSet rssetuser = null;
							try{
								Class.forName("com.mysql.jdbc.Driver").newInstance();
								String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
								String connectionUser="root";
								String connectionPassword="19896";
								connsetuser = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
								stmtsetuser = connsetuser.createStatement();
								rssetuser = stmtsetuser.executeQuery("Select username From users where username = '"+comboBoxUsername.getSelectedItem()+"'");
								if(rssetuser.next()){
									txtFldUsername.setText(rssetuser.getString(1));
								}
							}
							catch(Exception su){
								su.printStackTrace();
							}
							finally{
								try{ if (rssetuser	 != null)rssetuser.close();}catch (SQLException vat){vat.printStackTrace();}
								
								try{ if (stmtsetuser != null)stmtsetuser.close();}catch(SQLException vat){vat.printStackTrace();}
								
								try{ if (connsetuser != null)connsetuser.close();}catch(SQLException vat){vat.printStackTrace();}
							}
						}
					});
			}
			catch(Exception u){
				u.printStackTrace();
			}
			finally{
				try{ if (rsWS	 != null)rsWS.close();}catch (SQLException vat){vat.printStackTrace();}
				
				try{ if (stmtUser != null)stmtUser.close();}catch(SQLException vat){vat.printStackTrace();}
				
				try{ if (connUser != null)connUser.close();}catch(SQLException vat){vat.printStackTrace();}
			}
		comboBoxUsername.setBounds(222, 48, 85, 20);
		contentPane.add(comboBoxUsername);
		
		JButton btnSearchSales = new JButton("\u03A0\u03A9\u039B\u0397\u03A3\u0395\u0399\u03A3");
		btnSearchSales.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent sl){
				String s_user = txtFldUsername.getText();
				int sum =0;
				String temp = null;
				String showsum = null;
				Connection connSsales = null;	
				Statement stmtSsales = null;
				ResultSet rsSsales = null;
				ResultSet rsSname = null;
				
								try{
									Class.forName("com.mysql.jdbc.Driver").newInstance();
									String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
									String connectionUser="root";
									String connectionPassword="19896";
									connSsales = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
									stmtSsales = connSsales.createStatement();
									rsSname = stmtSsales.executeQuery("Select * from users where username = '"+s_user+"'");
									while (rsSname.next()){
									temp = rsSname.getString("id");}
									rsSsales = stmtSsales.executeQuery("Select * from sales where user = '"+temp+"' ");
									while (rsSsales.next()){
										 sum = sum + rsSsales.getInt("items");
										  showsum = Integer.toString(sum);									
											}
									txtFldShowSales.setText(showsum);
									}
								catch(Exception Ss){
									Ss.printStackTrace();
									}
								finally{
									try{ if (rsSsales != null)rsSsales.close();}catch (SQLException vat){vat.printStackTrace();}
									
									try{ if (rsSname != null)rsSname.close();}catch (SQLException vat){vat.printStackTrace();}
									
									try{ if (stmtSsales != null)stmtSsales.close();}catch(SQLException vat){vat.printStackTrace();}
									
									try{ if (connSsales != null)connSsales.close();}catch(SQLException vat){vat.printStackTrace();}
									}
				
				}
				
			
		});
		btnSearchSales.setBounds(24, 113, 101, 23);
		contentPane.add(btnSearchSales);
		
		JButton btnClear = new JButton("\u039A\u0391\u0398\u0391\u03A1\u0399\u03A3\u039C\u039F\u03A3");
		btnClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent c){
				txtFldUsername.setText("");
			}
		});
		btnClear.setBounds(10, 147, 129, 23);
		contentPane.add(btnClear);
		
		JLabel lblShowSales = new JLabel("\u03A0\u03A9\u039B\u0397\u03A3\u0395\u0399\u03A3");
		lblShowSales.setBounds(205, 123, 78, 14);
		contentPane.add(lblShowSales);
		
		txtFldShowSales = new JTextField();
		txtFldShowSales.setEditable(false);
		txtFldShowSales.setBounds(197, 148, 86, 20);
		contentPane.add(txtFldShowSales);
		txtFldShowSales.setColumns(10);
		
		JLabel lblSalesForUser = new JLabel("\u03A0\u03A9\u039B\u0397\u03A3\u0395\u0399\u03A3 \u0391\u039D\u0391 \u03A7\u0395\u0399\u03A1\u0399\u03A3\u03A4\u0397");
		lblSalesForUser.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblSalesForUser.setBounds(45, 11, 238, 14);
		contentPane.add(lblSalesForUser);
		
		JLabel lblStatisticWarehouses = new JLabel("\u03A3\u03A4\u0391\u03A4\u0399\u03A3\u03A4\u0399\u039A\u0391 \u0391\u03A0\u039F\u0398\u0397\u039A\u03A9\u039D");
		lblStatisticWarehouses.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblStatisticWarehouses.setBounds(465, 33, 244, 14);
		contentPane.add(lblStatisticWarehouses);
	
		JLabel lblAvailableWarehouses = new JLabel("\u0394\u0399\u0391\u0398\u0395\u03A3\u0397\u039C\u0391 \u0395\u0399\u0394\u0397");
		lblAvailableWarehouses.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAvailableWarehouses.setBounds(442, 77, 153, 14);
		contentPane.add(lblAvailableWarehouses);
		
		JLabel lblPersentWarehouses = new JLabel("\u03A0\u039F\u03A3\u039F\u03A3\u03A4\u039F \u0391\u03A0\u039F\u0398\u0397\u039A\u03A9\u039D");
		lblPersentWarehouses.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPersentWarehouses.setBounds(442, 117, 153, 14);
		contentPane.add(lblPersentWarehouses);
		
	
		
		JLabel lblCustomers = new JLabel("\u03A0\u0395\u039B\u0391\u03A4\u0395\u03A3");
		lblCustomers.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCustomers.setBounds(442, 156, 153, 14);
		contentPane.add(lblCustomers);
		
		final JComboBox comboBoxWarehouse = new JComboBox();
			Connection connBWh = null;
			Statement stmtBWh = null;
			ResultSet rsBWh = null;
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
				String connectionUser="root";
				String connectionPassword="19896";
				connBWh = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
				stmtBWh = connBWh.createStatement();
				rsBWh = stmtBWh.executeQuery("Select * from warehouse");
				while (rsBWh.next()){
					comboBoxWarehouse.addItem(rsBWh.getString(2));
				}
				comboBoxWarehouse.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ws){
						int sumwh = 0;
						int averagewh =0;
						int tempwh = 0;
						String shwavrwh = null;
						Connection connWs = null;
						Statement stmtWs = null;
						ResultSet rsWs = null;
						try{
							Class.forName("com.mysql.jdbc.Driver").newInstance();
							String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
							String connectionUser="root";
							String connectionPassword="19896";
							connWs = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
							stmtWs = connWs.createStatement();
							rsWs = stmtWs.executeQuery("Select products,max_prod From warehouse where name = '"+comboBoxWarehouse.getSelectedItem()+"'");
							if(rsWs.next()){
								txtFldWarehouseSum.setText(rsWs.getString(1));
								tempwh = rsWs.getInt("products");
								sumwh = rsWs.getInt("max_prod");
								averagewh = (tempwh*100)/sumwh;
								shwavrwh = Integer.toString(averagewh);
								txtFldWarehousePercent.setText(shwavrwh+"%");
							}
						}
						catch(Exception w){
							w.printStackTrace();
						}
						finally {
							try{ if (rsWs != null)rsWs.close();}catch (SQLException vat){vat.printStackTrace();}
							
							try{ if (stmtWs != null)stmtWs.close();}catch(SQLException vat){vat.printStackTrace();}
							
							try{ if (connWs != null)connWs.close();}catch(SQLException vat){vat.printStackTrace();}
						}
					}
				});
			}
			catch(Exception cw){
				cw.printStackTrace();
			}
			finally{
				try{ if (rsBWh	 != null)rsBWh.close();}catch (SQLException vat){vat.printStackTrace();}
				
				try{ if (stmtBWh != null)stmtBWh.close();}catch(SQLException vat){vat.printStackTrace();}
				
				try{ if (connBWh != null)connBWh.close();}catch(SQLException vat){vat.printStackTrace();}
			}
		comboBoxWarehouse.setBounds(90, 315, 153, 20);
		contentPane.add(comboBoxWarehouse);
		
		txtFldWarehousePercent = new JTextField();
		txtFldWarehousePercent.setEditable(false);
		txtFldWarehousePercent.setBounds(39, 376, 86, 20);
		contentPane.add(txtFldWarehousePercent);
		txtFldWarehousePercent.setColumns(10);
		
		txtFldWarehouseSum = new JTextField();
		txtFldWarehouseSum.setEditable(false);
		txtFldWarehouseSum.setBounds(188, 376, 86, 20);
		contentPane.add(txtFldWarehouseSum);
		txtFldWarehouseSum.setColumns(10);
		
		JLabel lblWarehouseStatistic = new JLabel("\u03A3\u03A4\u0391\u03A4\u0399\u03A3\u03A4\u0399\u039A\u0391 \u0391\u03A0\u039F\u0398\u0397\u039A\u0397\u03A3");
		lblWarehouseStatistic.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblWarehouseStatistic.setBounds(65, 273, 180, 14);
		contentPane.add(lblWarehouseStatistic);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(336, 219, 402, 277);
		contentPane.add(scrollPane);
		
		tableWarehouse = new JTable();
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
						rs = stmt.executeQuery("SELECT name,products,max_prod,product_in,product_out FROM warehouse");
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
						
		tableWarehouse = new JTable(products);
		tableWarehouse.setFillsViewportHeight(true);
		tableWarehouse.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableWarehouse.getColumnModel().getColumn(0).setPreferredWidth(165);
		tableWarehouse.getColumnModel().getColumn(1).setPreferredWidth(70);
		tableWarehouse.getColumnModel().getColumn(2).setPreferredWidth(70);
		tableWarehouse.getColumnModel().getColumn(3).setPreferredWidth(40);
		tableWarehouse.getColumnModel().getColumn(4).setPreferredWidth(40);
		tableWarehouse.getColumnModel().getColumn(0).setHeaderValue("Warehouse Name");
		tableWarehouse.getColumnModel().getColumn(1).setHeaderValue("Products");
		tableWarehouse.getColumnModel().getColumn(2).setHeaderValue("Max Capacity");
		tableWarehouse.getColumnModel().getColumn(3).setHeaderValue("Product IN");
		tableWarehouse.getColumnModel().getColumn(4).setHeaderValue("Product OUT");
		////table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableWarehouse.setEnabled(false);
		//tableWarehouse.setBounds(1, 1, 200, 200);
		scrollPane.setColumnHeaderView(tableWarehouse);
		scrollPane.setViewportView(tableWarehouse);
		
		JLabel lblWarehousePercent = new JLabel("\u03A0\u039B\u0397\u03A1\u039F\u03A4\u0397\u03A4\u0391 \u0391\u03A0\u039F\u0398\u0397\u039A\u0397\u03A3");
		lblWarehousePercent.setBounds(24, 346, 153, 14);
		contentPane.add(lblWarehousePercent);
		
		JLabel lblWarehouseProd = new JLabel("\u039A\u0391\u03A4\u0391\u03A7\u03A9\u03A1\u0397\u039C\u0395\u039D\u0391 \u0395\u0399\u0394\u0397");
		lblWarehouseProd.setBounds(178, 346, 129, 14);
		contentPane.add(lblWarehouseProd);
	}
}
