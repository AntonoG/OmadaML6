package SoftEng;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;





import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import com.mysql.jdbc.ResultSetMetaData;

import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Stock extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtFldSerial;
	private JTextField txtFldCount;
	private JTextField txtFldID;
	private JTable table_1;
	boolean checked=false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stock frame = new Stock();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**	 * Create the frame.	 */
	public Stock() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("ΕΜΦΑΝΙΣΗ ΑΠΟΘΕΜΑΤΩΝ");
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel label = new JLabel("\u0391\u039D\u0391\u0396\u0397\u03A4\u0397\u03A3\u0397 \u0394\u0399\u0391\u0398\u0395\u03A3\u0399\u039C\u039F\u03A4\u0397\u03A4\u0391\u03A3");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(24, 390, 344, 32);
		contentPane.add(label);
		
		JLabel lblSerialNumber = new JLabel("\u03A3\u03B5\u03B9\u03C1\u03B9\u03B1\u03BA\u03BF\u03C2 \u0391\u03C1\u03B9\u03B8\u03BC\u03BF\u03C2");
		lblSerialNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSerialNumber.setBounds(34, 433, 122, 20);
		contentPane.add(lblSerialNumber);
		
		txtFldSerial = new JTextField();
		txtFldSerial.setBounds(166, 433, 86, 20);
		contentPane.add(txtFldSerial);
		txtFldSerial.setColumns(10);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnExit.setBounds(685, 527, 89, 23);
		contentPane.add(btnExit);
		
		final JButton btnSearch = new JButton("\u0391\u039D\u0391\u0396\u0397\u03A4\u0397\u03A3\u0397");
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
					rsSrch = stmtSrch.executeQuery("SELECT id,Name,Serial,Price,seller_name,barcode,count,vat FROM products where id='"+Srch_ID+"'");
					
					while (rsSrch.next() ){
						String dbprodID = rsSrch.getString("ID");
						String dbprodSerial = rsSrch.getString("Serial");
						
						if (dbprodID.equals(txtFldID.getText())){
								txtFldSerial.setText(rsSrch.getString("serial"));
								txtFldCount.setText(rsSrch.getString("count"));
								}
						else if(dbprodSerial.equals(txtFldSerial.getText())){
								txtFldID.setText(rsSrch.getString("ID"));
								txtFldCount.setText(rsSrch.getString("count"));
						}
						else{
							JOptionPane.showMessageDialog(btnSearch, "Λαθος Στοιχεια Αναζητησης","ERROR", JOptionPane.WARNING_MESSAGE);
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
		btnSearch.setBounds(303, 434, 122, 23);
		contentPane.add(btnSearch);
		
		txtFldCount = new JTextField();
		txtFldCount.setEnabled(false);
		txtFldCount.setEditable(false);
		txtFldCount.setBounds(648, 400, 86, 20);
		contentPane.add(txtFldCount);
		txtFldCount.setColumns(10);
		
		JLabel lblID = new JLabel("ID \u0395\u0399\u03B4\u03BF\u03C5\u03C2");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblID.setBounds(44, 464, 112, 17);
		contentPane.add(lblID);
		
		txtFldID = new JTextField();
		txtFldID.setBounds(166, 461, 86, 20);
		contentPane.add(txtFldID);
		txtFldID.setColumns(10);
		
		JLabel lblAvailable = new JLabel("\u0394\u0399\u0391\u0398\u0395\u03A3\u0399\u039C\u039F\u03A4\u0397\u03A4\u0391");
		lblAvailable.setFont(new Font("Sylfaen", Font.BOLD, 16));
		lblAvailable.setBounds(476, 394, 162, 32);
		contentPane.add(lblAvailable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 764, 382);
		contentPane.add(scrollPane);
		
		table= new JTable();
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
			rs = stmt.executeQuery("SELECT id,name,price,count,status,serial,warehouse FROM products");
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
		table.setDragEnabled(true);
		table.setFillsViewportHeight(true);
		table.setFocusable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(2);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.getColumnModel().getColumn(5).setPreferredWidth(20);
		table.getColumnModel().getColumn(6).setPreferredWidth(20);
		///SELECT id,name,price,count,status,serial,warehouse FROM products
		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Ονομασια");
		table.getColumnModel().getColumn(2).setHeaderValue("Τιμη");
		table.getColumnModel().getColumn(3).setHeaderValue("Πληθος");
		table.getColumnModel().getColumn(4).setHeaderValue("Κατασταση");
		table.getColumnModel().getColumn(5).setHeaderValue("Σειριακος ");
		table.getColumnModel().getColumn(6).setHeaderValue("Αποθηκη");
		table.setColumnSelectionAllowed(true);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		table.setBounds(0, 15, 790, 350);
		///contentPane.add(table);
		scrollPane.setViewportView(table);
	}
}
