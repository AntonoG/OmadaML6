package SoftEng;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

import java.awt.Window.Type;
import java.awt.Toolkit;

import javax.swing.UIManager;

import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTable;

import java.awt.Cursor;

import javax.swing.border.MatteBorder;


public class Store extends JFrame {

	private JPanel contentPane;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs  = null;
	String fname;
	private String loginName;
	boolean checked=false;
	/**	 * Launch the application.	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			
				try {
					Store frame = new Store();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void setLoginName(String login){
		loginName=login;
	}
	/**	 * Create the frame.	 */
	public Store() {
		
		
		setTitle("Electric Warehouse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JButton btnManageProd = new JButton("\u0394\u03B9\u03B1\u03C7\u03B5\u03B9\u03C1\u03B9\u03C3\u03B7 \u0391\u03C0\u03BF\u03B8\u03B7\u03BA\u03B7\u03C2");
		btnManageProd.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			new Warehouse().setVisible(true);
			
				}
			}
		);
		
		menuBar.add(btnManageProd);
		
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
		
		JButton btnUsers = new JButton("\u0394\u03B9\u03B1\u03C7\u03B5\u03B9\u03C1\u03B9\u03C3\u03B7 \u03A7\u03C1\u03B7\u03C3\u03C4\u03C9\u03BD");
		btnUsers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				new Users().setVisible(true);
			}
		}
		);
		menuBar.add(btnUsers);
		
		JButton btnWarehouses = new JButton("\u0391\u03C0\u03BF\u03B8\u03B7\u03BA\u03B5\u03C2");
		btnWarehouses.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				new Buildings().setVisible(true);
			}
		}
		);
		menuBar.add(btnWarehouses);
		
		JButton btnStatistics = new JButton("\u03A3\u03C4\u03B1\u03C4\u03B9\u03C3\u03C4\u03B9\u03BA\u03B1 \u0391\u03C0\u03BF\u03B8\u03B7\u03BA\u03C9\u03BD");
		btnStatistics.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bS){
				new Sales().setVisible(true);
			}
		});
		menuBar.add(btnStatistics);
		
		JLabel lblEditor = new JLabel("Electric WareHouse Administration");
		
		lblEditor.setBounds(0,715 , 220, 14);
		contentPane.add(lblEditor);
	}

	public Store(String loginName) {
		this();
		setLoginName(loginName);
		System.out.printf("Connect user:", loginName);
	}
}
