package login;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;
 

public class LoginForm implements ActionListener {
	
	   //Creating object of JFrame class
    JFrame frame;
    
    //Creating objects
    String[] gender={"Female","Male","Other","Prefer Not To Say"};
    JLabel nameLabel=new JLabel("NAME");
    JLabel genderLabel=new JLabel("GENDER");
    //JLabel ageLabel=new JLabel("GENDER");
    JLabel sirNameLabel=new JLabel("SIR NAME");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JLabel confirmPasswordLabel=new JLabel("CONFIRM PASSWORD");
    JLabel addressLabel=new JLabel("ADDRESS");
    JLabel emailLabel=new JLabel("EMAIL");
    JTextField nameTextField=new JTextField();
    JComboBox genderComboBox=new JComboBox(gender);
    //JTextField ageTextField=new JTextField();
    JTextField sirNameTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JPasswordField confirmPasswordField=new JPasswordField();
    JTextField addressTextField=new JTextField();
    JTextField emailTextField=new JTextField();
    JButton loginButton=new JButton("LOGIN");
    JButton registerButton=new JButton("REGISTER");
    JButton resetButton=new JButton("RESET");
    
    //Creating constructor
    LoginForm()
    {
        
        createWindow();//calling method from constructor
        setPlacementAndSize();
        addComponentsToFrame();
        actionEvent();
    }
 
    //Creating user-defined method
    public void createWindow()
    {
       //Setting properties of JFrame
        /**frame=new JFrame();
        frame.setTitle("Login Page");
        frame.setBounds(40,40,380,500);
        frame.setBackground(Color.BLUE);
        frame.getContentPane().setBackground(Color.white);
        frame.getContentPane().setLayout(null);
        frame.setForeground(Color.BLACK);
        frame.getContentPane().setForeground(Color.DARK_GRAY);
        frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);**/
        
        frame = new JFrame();
		frame.setTitle("REGISTER");
		frame.setBackground(Color.white);
		//frame.getContentPane().setBackground(new Color(106, 90, 205));
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.setBounds(200, 200, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
    }
    
    public void setPlacementAndSize()
    {
        //Setting Location and Size of Each Component
        nameLabel.setBounds(20,20,40,70);
        //ageLabel.setBounds(20,70,80,70);
        genderLabel.setBounds(20,70,80,70);
        sirNameLabel.setBounds(20,120,100,70);
        passwordLabel.setBounds(20,170,100,70);
        confirmPasswordLabel.setBounds(20,220,140,70);
        addressLabel.setBounds(20,270,100,70);
        emailLabel.setBounds(20,320,100,70);
        nameTextField.setBounds(180,43,165,23);
        genderComboBox.setBounds(180,93,165,23);
        //ageTextField.setBounds(180,143,165,23);
        sirNameTextField.setBounds(180,143,165,23);
        passwordField.setBounds(180,193,165,23);
        confirmPasswordField.setBounds(180,243,165,23);
        addressTextField.setBounds(180,293,165,23);
        emailTextField.setBounds(180,343,165,23);
        loginButton.setBounds(110,500,100,35);
        registerButton.setBounds(10,500,100,35);
        resetButton.setBounds(200,500,100,35);
    }
    public void addComponentsToFrame()
    {
        //Adding components to Frame
        frame.add(nameLabel);
        frame.add(genderLabel);
        //frame.add(ageLabel);
        frame.add(sirNameLabel);
        frame.add(passwordLabel);
        frame.add(confirmPasswordLabel);
        frame.add(addressLabel);
        frame.add(emailLabel);
        frame.add(nameTextField);
        frame.add(genderComboBox);
        //frame.add(ageTextField);
        frame.add(sirNameTextField);
        frame.add(passwordField);
        frame.add(confirmPasswordField);
        frame.add(addressTextField);
        frame.add(emailTextField);
        frame.add(loginButton);
        frame.add(registerButton);
        frame.add(resetButton);
    }
    
    public void actionEvent()
    {
       //Adding Action Listener to the buttons here
        registerButton.addActionListener(this);
        //loginButton.addActionListener(this);
        resetButton.addActionListener(this);
    }
    
    
    public class Main {
        public static void main(String[] args)
        {
            //creating object of LoginForm class
            new LoginForm();
        }
    } //trying to add the login here, havent figured it out yet

	
	@Override
    public void actionPerformed(ActionEvent e) {
		   if(e.getSource()==registerButton)
	        {
	            try {
	                //Creating Connection Object
	                Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/firstDatabase","root","root1997");
	                PreparedStatement Pstatement=connection.prepareStatement("insert into login values(?,?,?,?,?,?,?,?,?)");
	                //Specifying the values of it's parameter
	                Pstatement.setString(1,nameTextField.getText());
	                Pstatement.setString(2,genderComboBox.getSelectedItem().toString());
	                //Pstatement.setString(3,ageTextField.getText());
	                Pstatement.setString(3,sirNameTextField.getText());
	                Pstatement.setString(4,passwordField.getText());
	                Pstatement.setString(5,confirmPasswordField.getText());
	                Pstatement.setString(6,addressTextField.getText());
	                Pstatement.setString(7,emailTextField.getText());
	                //Checking if the Passwords match
	                if(passwordField.getText().equalsIgnoreCase(confirmPasswordField.getText()))
	                {
	                    //Executing query
	                    Pstatement.executeUpdate();
	                    JOptionPane.showMessageDialog(null,"Registered Successfully");
	                }
	                else
	                {
	                    JOptionPane.showMessageDialog(null,"password doesn't match");
	                }
	 
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }
	 
	 
	        }
	        if(e.getSource()==resetButton)
	        {
	            //Commas indicate the boxes are empty and to clear the form
	            nameTextField.setText("");
	            genderComboBox.setSelectedItem("Female");
	            //ageTextField.setText("");
	            sirNameTextField.setText("");
	            passwordField.setText("");
	            confirmPasswordField.setText("");
	            addressTextField.setText("");
	            emailTextField.setText("");
	        }
	 
	    }

}

