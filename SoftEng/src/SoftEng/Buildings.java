package SoftEng;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Buildings extends JFrame {

	private JPanel contentPane;
	private JTable table;
	boolean checked=false;

	/**	 * Launch the application.	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buildings frame = new Buildings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**	 * Create the frame.	 */
	public Buildings() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("ΑΠΟΘΗΚΕΣ");
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 564, 140);
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
			rs = stmt.executeQuery("SELECT id,name,products,product_in,product_out FROM warehouse");
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
		table.setBounds(571, 11, 215, 217);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(160);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Ονομα Αποθηκης");
		table.getColumnModel().getColumn(2).setHeaderValue("Ειδη Ενεργα");
		table.getColumnModel().getColumn(3).setHeaderValue("Ειδη Εξοδου");
		table.getColumnModel().getColumn(4).setHeaderValue("Ειδη Εισοδου");
		scrollPane.setViewportView(table);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnExit.setBounds(485, 327, 89, 23);
		contentPane.add(btnExit);
	}
}
