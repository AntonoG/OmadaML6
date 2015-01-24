package SoftEng;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;



public class EmployeeStore extends JFrame {

	private JPanel contentPane;
	private JTable table;
	boolean checked=false;
	
	/**	 * Launch the application.	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeStore frame = new EmployeeStore();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**	 * Create the frame.	 */
	public EmployeeStore() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton ExitButton = new JButton("EXIT");
		ExitButton.setBackground(Color.LIGHT_GRAY);
		ExitButton.setForeground(new Color(255, 0, 0));
		ExitButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		ExitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				System.exit(0);
			}
		}
		);
		contentPane.setLayout(null);
		ExitButton.setBounds(912, 704, 96, 27);
		contentPane.add(ExitButton);
		
		JButton LogoutButton = new JButton("LogOUT");
		LogoutButton.setBorder(new MatteBorder(2, 2, 3, 4, (Color) new Color(0, 0, 0)));
		LogoutButton.setBackground(new Color(240,240,240));
		LogoutButton.setForeground(new Color(0, 0, 139));
		LogoutButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		LogoutButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
			new login().setVisible(true);
			setVisible(false);
			}
		}
		);
		LogoutButton.setBounds(801, 702, 111, 27);
		contentPane.add(LogoutButton);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1010, 21);
		contentPane.add(menuBar);
		
		JButton btnShowStock = new JButton("\u0395\u03BC\u03C6\u03B1\u03BD\u03B9\u03C3\u03B7 \u03B1\u03C0\u03BF\u03B8\u03B5\u03BC\u03B1\u03C4\u03C9\u03BD");
		btnShowStock.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnShowStock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				new Stock().setVisible(true);
			}
		});
		menuBar.add(btnShowStock);
		
		JButton btnCustomers = new JButton("\u0394\u03B9\u03B1\u03C7\u03B5\u03B9\u03C1\u03B9\u03C3\u03B7 \u03A0\u03B5\u03BB\u03B1\u03C4\u03C9\u03BD");
		btnCustomers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				new Customers().setVisible(true);
			}
		});
		menuBar.add(btnCustomers);
		
		JLabel lblEditor = new JLabel("Electric WareHouse Employee ");		
		lblEditor.setBounds(0,715 , 182, 14);
		contentPane.add(lblEditor);
		
	
	
	}


	
}
