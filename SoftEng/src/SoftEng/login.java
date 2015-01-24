package SoftEng;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
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


public class login extends JFrame {


	private JPanel contentPane;

	private JTextField username;

	private JPasswordField password;
	boolean login = false;
	public String aclogin;
	public String aclevel;
	boolean checked=false;

	/** Launch the application.*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*** Create the frame.*/
	public login() {
		setFont(new Font("Magneto", Font.PLAIN, 18));
		setForeground(new Color(128, 128, 128));
		setBackground(new Color(220, 220, 220));
		setAlwaysOnTop(true);
		setType(Type.POPUP);
		setTitle("Electric Warehouse Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		setLocationRelativeTo(null); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
		lblNewLabel.setBounds(86, 81, 94, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(94, 145, 84, 14);
		contentPane.add(lblNewLabel_1);
		
		username = new JTextField();
		username.setBounds(212, 79, 86, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		final JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener()	{
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0){
				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				try{
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					String connectionUrl="jdbc:mysql://127.0.0.1:3306/softeng";
					String connectionUser="root";
					String connectionPassword="19896";
					conn = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
					stmt = conn.createStatement();
					rs = stmt.executeQuery("SELECT * FROM users");
					
					while (rs.next()){
						String dbUsername = rs.getString("username");
						String dbPassword = rs.getString("password");
						String dbID = rs.getString("id");
						String dbLevel = rs.getString("level");
				
				
						if (dbUsername.equals(username.getText()) && dbPassword.equals(password.getText()) ){
							login = true;
							aclogin  = dbID;
							aclevel= dbLevel;
							if(Integer.parseInt(dbLevel)==2){
							JOptionPane.showMessageDialog(LoginButton, "Successfull Login!!","Welcome Administrator",JOptionPane.INFORMATION_MESSAGE);
							new Store(aclogin);
							new Store().setVisible(true);
							}
							else{
								JOptionPane.showMessageDialog(LoginButton, "Succefull Login!!", "Welcome User", JOptionPane.INFORMATION_MESSAGE);
								new EmployeeStore().setVisible(true);
							}
							setVisible(false);
							
							}
					}
						if (login == false){
							
							JOptionPane.showMessageDialog(LoginButton, "Λαθος καταχωρηση!","UnSuccessful Login", JOptionPane.WARNING_MESSAGE);
							username.setText("");
							password.setText("");
							}	
						
				
					}
				
				catch (Exception e){
					e.printStackTrace();
				}
				finally{
					try{ if (rs != null)rs.close();}catch (SQLException e){e.printStackTrace();}
					
					try{ if (stmt != null)stmt.close();}catch(SQLException e){e.printStackTrace();}
					
					try{ if (conn != null)conn.close();}catch(SQLException e){e.printStackTrace();}
				}
			}

			

			
		}
		
				);
		
		LoginButton.setBounds(209, 227, 89, 23);
		contentPane.add(LoginButton);
		
		JButton ExitButton = new JButton("Exit");
		ExitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				System.exit(0);
			}
		}
		);
		ExitButton.setBounds(335, 227, 89, 23);
		contentPane.add(ExitButton);
		
		password = new JPasswordField();
		password.setBounds(212, 145, 86, 20);
		contentPane.add(password);
		
		JLabel lblV = new JLabel("V1.0");
		lblV.setBounds(0, 247, 46, 14);
		contentPane.add(lblV);
		
	}
	
}
