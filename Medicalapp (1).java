package com.medical.app;
import javax.swing.JFrame;
import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;


import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class Medicalapp {	
	
	private static Medicalapp t;
	private JTextField chGetName;
	
	JFrame admin = new JFrame();
	static JFrame login = new JFrame();
	static JFrame userRegistrationFrame = new JFrame();
	static JFrame cancelAppointmentFrame = new JFrame();
	
	static final String MED_DB_NAME = "medical_info";
	static final String MED_DB_USER = "root";
	static final String MED_DB_PASSWORD = "admin1234";
	
	static final String FIRST_DB_NAME = "firstDatabase";
	static final String FIRST_DB_USER = "root";
	static final String FIRST_DB_PASSWORD = "admin1234";
	
	
	DateFormat DB_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public Medicalapp()
	{
		
	}
	
	public static synchronized Medicalapp getTest()
	{
		if(t == null)
			t = new Medicalapp();
		return t;
	}
	
	
			
	public void run() {
		
		JFrame frmMedicalAppointmentBooking = new JFrame();	
		
		JFrame records = new JFrame();
		
		userRegistrationFrame = getUserRegistrationFrame();
		cancelAppointmentFrame = getCancelAppointmentFrame();
		
		//login.setSize(708,470);
		login.setSize(800,600);
		login.getContentPane().setLayout(null);
		login.setLocationRelativeTo(null);
		
		
		JLabel hseLabel = new JLabel("New label");
		hseLabel.setIcon(getImageLabel());
		hseLabel.setBounds(250, 30, 264, 140);
		login.getContentPane().add(hseLabel);
		
		
		JLabel username = new JLabel("Username");
		username.setFont(new Font("Tahoma", Font.BOLD, 20));
		username.setBounds(250, 190, 117, 31);
		login.getContentPane().add(username);
		
		JTextField getUsername = new JTextField();
		getUsername.setBounds(400, 190, 158, 40);
		login.getContentPane().add(getUsername);
		getUsername.setColumns(10);
		getUsername.setSize(200, 35);
		
		
		JLabel password = new JLabel("Password");
		password.setFont(new Font("Tahoma", Font.BOLD, 20));
		password.setBounds(250, 260, 135, 31);
		login.getContentPane().add(password);
		
		JPasswordField  passwordfield = new JPasswordField (35);
		passwordfield.setBounds(400, 260, 158, 40);
		login.getContentPane().add(passwordfield);
		passwordfield.setColumns(10);
		passwordfield.setSize(200, 35);
		
		
		JButton loginButton = new JButton("LOGIN");
		loginButton.setPreferredSize(new Dimension(40, 40));
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		loginButton.setBounds(400, 317, 174, 40);
		loginButton.setSize(200, 35);
		
		loginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					String userName = getUsername.getText();
					String password = passwordfield.getText();
					
					if(checkUserLogin(userName, password))
					{
						login.setVisible(false);
						frmMedicalAppointmentBooking.setVisible(true);
					}else if(userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin"))
					{
						login.setVisible(false);
						frmMedicalAppointmentBooking.setVisible(true);
					}else
					{
						JOptionPane.showMessageDialog(null,"Login Failed.");
					}

		}});
		
		login.getContentPane().add(loginButton);
		
		JButton userRegButton = new JButton("Registration");
		userRegButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		userRegButton.setBounds(400, 390, 174, 40);
		userRegButton.setSize(200, 35);
		userRegButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				userRegistrationFrame.setVisible(true);
				login.setVisible(false);
			}
		});
		login.getContentPane().add(userRegButton);
		
		JButton adminButton = new JButton("Admin Page");
		adminButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		adminButton.setBounds(190, 390, 174, 40);
		adminButton.setSize(200, 35);
		adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin.setVisible(true);
				login.setVisible(false);
			}
		});
		login.getContentPane().add(adminButton);
		
		
		
		
		frmMedicalAppointmentBooking.setSize(1175,720);
		//frmMedicalAppointmentBooking.setSize(1150,674);
		frmMedicalAppointmentBooking.setLocationRelativeTo(null);
		
		
		frmMedicalAppointmentBooking.setTitle("Medical Appointment Booking System");
		frmMedicalAppointmentBooking.getContentPane().setLayout(null);
	
		
		JLabel registerLabel = new JLabel("Register a Patient");
		registerLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		registerLabel.setBounds(121, 6, 156, 25);
		frmMedicalAppointmentBooking.getContentPane().add(registerLabel);
		
		JLabel bookingLabel = new JLabel("Book Appointment");
		bookingLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		bookingLabel.setBounds(121, 239, 156, 18);
		frmMedicalAppointmentBooking.getContentPane().add(bookingLabel);
		
		JLabel calendarLabel = new JLabel("Calendar");
		calendarLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		calendarLabel.setBounds(500, 11, 90, 14);
		frmMedicalAppointmentBooking.getContentPane().add(calendarLabel);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(434, 30, 227, 358);
		frmMedicalAppointmentBooking.getContentPane().add(calendar);
		
		JTextArea dr1TA = new JTextArea();
		dr1TA.setBounds(700, 57, 129, 453);
		frmMedicalAppointmentBooking.getContentPane().add(dr1TA);
		
		JTextArea dr2TA = new JTextArea();
		dr2TA.setBounds(839, 57, 129, 453);
		frmMedicalAppointmentBooking.getContentPane().add(dr2TA);
		
		JTextArea dr3TA = new JTextArea();
		dr3TA.setBounds(978, 57, 129, 453);
		frmMedicalAppointmentBooking.getContentPane().add(dr3TA);
		
		JLabel dateLabel = new JLabel("");
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateLabel.setBounds(880, 8, 146, 20);
		frmMedicalAppointmentBooking.getContentPane().add(dateLabel);
		
		
		JButton calendarButton = new JButton("Check Appointments");
		calendarButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		calendarButton.setBackground(Color.GRAY);
		calendarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				java.sql.Date newDate = new java.sql.Date(new java.util.Date().getTime()); 
				if(calendar.getDate() !=null);
				{
					newDate = new java.sql.Date(calendar.getDate().getTime());
				}
								
				try 
				{
					Connection con = getMedicalInfoDBConnection();
					Statement stmt = con.createStatement(); // establish connection same as above
					
					String emptyOrNot = "select count(*) from bookings where appDate = '" + newDate + "'";
					ResultSet empty = stmt.executeQuery(emptyOrNot);
					empty.next();
					int count = empty.getInt(1);
					
					if(count == 0)
					{
						JOptionPane.showMessageDialog(null, "No appointments on this date");
					}
					else {
						
						dr1TA.setText("");
						dr2TA.setText("");
						dr3TA.setText("");
					
					String query = "select * from bookings where appDate = '" + newDate + "' and doctor = 'Dr John' ORDER BY appTime"; // query that will select all information when the patientName is input into text field
					ResultSet resultSet = stmt.executeQuery(query); // execute query
					
					dateLabel.setText(newDate.toString());
					
					ArrayList<String> patient = new ArrayList<String>();
					
					
						
					while(resultSet.next()) {
						String name = resultSet.getString("patientName");
						String time = resultSet.getString("appTime");
						String reason = resultSet.getString("reason");
						
						patient.add(name);
						patient.add(time);
						patient.add(reason);
						patient.add("");
						
						}
					
					for(String p : patient)
					{
						dr1TA.append(p + "\n");
					}
					
					query = "select * from bookings where appDate = '" + newDate + "' and doctor = 'Dr James' ORDER BY appTime";
					resultSet = stmt.executeQuery(query);
					ArrayList<String> patient2 = new ArrayList<String>();
					while(resultSet.next()) {
						String name = resultSet.getString("patientName");
						String time = resultSet.getString("appTime");
						String reason = resultSet.getString("reason");
						
						patient2.add(name);
						patient2.add(time);
						patient2.add(reason);
						patient2.add("");
						
					}
					
					for(String p : patient2)
					{
						dr2TA.append(p + "\n");
						
					}
					
					query = "select * from bookings where appDate = '" + newDate + "' and doctor = 'Dr Joe' ORDER BY appTime";
					resultSet = stmt.executeQuery(query);
					ArrayList<String> patient3 = new ArrayList<String>();
					while(resultSet.next()) {
						String name = resultSet.getString("patientName");
						String time = resultSet.getString("appTime");
						String reason = resultSet.getString("reason");
						
						patient3.add(name);
						patient3.add(time);
						patient3.add(reason);
						patient3.add("");
						
					}
					
					for(String p : patient3)
					{
						dr3TA.append(p + "\n");
						
					}
					
					}
					stmt.close();  
			        con.close(); 
				}
						
						
					catch (SQLException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			
			}
				
			//	calendarTA.setText(newDate);
				
			});
		calendarButton.setForeground(Color.BLACK);
		calendarButton.setBounds(444, 392, 205, 32);
		frmMedicalAppointmentBooking.getContentPane().add(calendarButton);
		
		JLabel patName = new JLabel("Patient Name");
		patName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		patName.setBounds(6, 30, 90, 14);
		frmMedicalAppointmentBooking.getContentPane().add(patName);
		
		JLabel patDOB = new JLabel("Date of Birth");
		patDOB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		patDOB.setBounds(6, 60, 81, 14);
		frmMedicalAppointmentBooking.getContentPane().add(patDOB);
		
		JLabel patPhone = new JLabel("Phone Number");
		patPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		patPhone.setBounds(6, 90, 101, 14);
		frmMedicalAppointmentBooking.getContentPane().add(patPhone);
		
		JLabel patEmail = new JLabel("Email Address");
		patEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		patEmail.setBounds(6, 120, 101, 14);
		frmMedicalAppointmentBooking.getContentPane().add(patEmail);
		
		JLabel patUnderlying = new JLabel("Underlying Conditions");
		patUnderlying.setFont(new Font("Tahoma", Font.PLAIN, 14));
		patUnderlying.setBounds(6, 146, 137, 24);
		frmMedicalAppointmentBooking.getContentPane().add(patUnderlying);
		
		JComboBox patGetUC = new JComboBox();
		patGetUC.setModel(new DefaultComboBoxModel(new String[] {"None", "Asthma ", "Cancer", "Cystic Fibrosis", "Diabetes", "Heart Disease", "High/Low Blood Pressure", "Immuno-compromised", "Kidney Disease", "Liver Disease", "Lung Disease", "Multiple Sclerosis", "Obesity", "One or more of the above", "Other"}));
		patGetUC.setBounds(194, 149, 146, 22);
		frmMedicalAppointmentBooking.getContentPane().add(patGetUC);
		
		
		JTextField patF1 = new JTextField();
		patF1.setBounds(194, 29, 146, 20);
		frmMedicalAppointmentBooking.getContentPane().add(patF1);
		patF1.setColumns(10);
		
		JTextField patF2 = new JTextField();
		patF2.setBounds(194, 89, 146, 20);
		frmMedicalAppointmentBooking.getContentPane().add(patF2);
		patF2.setColumns(10);
		
		JTextField patF3 = new JTextField();
		patF3.setBounds(194, 119, 146, 20);
		frmMedicalAppointmentBooking.getContentPane().add(patF3);
		patF3.setColumns(10);
		
		JLabel bookingName = new JLabel("Patient Name");
		bookingName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bookingName.setBounds(6, 260, 90, 14);
		frmMedicalAppointmentBooking.getContentPane().add(bookingName);
		
		JLabel bookingReason = new JLabel("Appointment Reason");
		bookingReason.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bookingReason.setBounds(6, 312, 129, 18);
		frmMedicalAppointmentBooking.getContentPane().add(bookingReason);
		
		JLabel patGender = new JLabel("Gender");
		patGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		patGender.setBounds(6, 180, 45, 14);
		frmMedicalAppointmentBooking.getContentPane().add(patGender);
		
		JComboBox patGetGender = new JComboBox();
		patGetGender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		patGetGender.setBounds(194, 178, 146, 22);
		frmMedicalAppointmentBooking.getContentPane().add(patGetGender);
		
		JLabel bookingTime = new JLabel("Appointment Time");
		bookingTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bookingTime.setBounds(6, 341, 115, 18);
		frmMedicalAppointmentBooking.getContentPane().add(bookingTime);
		
		JLabel bookingDate = new JLabel("Appointment Date");
		bookingDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bookingDate.setBounds(6, 370, 115, 18);
		frmMedicalAppointmentBooking.getContentPane().add(bookingDate);
		
		JTextField bookingF1 = new JTextField();
		bookingF1.setBounds(194, 259, 146, 20);
		frmMedicalAppointmentBooking.getContentPane().add(bookingF1);
		bookingF1.setColumns(10);
		
		JComboBox bookingGetReason = new JComboBox();
		bookingGetReason.setModel(new DefaultComboBoxModel(new String[] {"Annual Wellness Check", "Cervical Screening", "Consultation", "Mental Health", "Minor Illness ", "Minor Surgery", "Phlebotomy (Blood Test)", "Postnatal Care", "Sprain/Break/Fracture", "Urgent Appointment", "Vaccinations", "Other"}));
		bookingGetReason.setBounds(194, 313, 146, 20);
		frmMedicalAppointmentBooking.getContentPane().add(bookingGetReason);
		
		JComboBox bookingGetTime = new JComboBox();
		bookingGetTime.setModel(new DefaultComboBoxModel(new String[] {"09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45"}));
		bookingGetTime.setBounds(194, 342, 146, 20);
		frmMedicalAppointmentBooking.getContentPane().add(bookingGetTime);
		
		JDateChooser patGetDOB = new JDateChooser();
		patGetDOB.setBounds(194, 59, 146, 20);
		frmMedicalAppointmentBooking.getContentPane().add(patGetDOB);
		
		JButton registerButton = new JButton("Register");
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		registerButton.setBounds(129, 210, 137, 23);
		
		registerButton.addActionListener(new ActionListener() { // add listener to book appointment and add to database
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
					
				
				if(patF1.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter a name");
				}
				
				else if(patGetDOB.getDate() == null)
				{
					JOptionPane.showMessageDialog(null, "Please enter a date of birth");
				}
				
				else if(patF2.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter a phone number");
				}
				
				else if(patF3.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter an email address");
				}
				
				
				
				else {
					
				
					try {
						Connection con = getMedicalInfoDBConnection(); // connection address, user, password
						PreparedStatement stmt = con.prepareStatement("INSERT INTO MEDICAL_INFO (PATIENTNAME, DOB,PHONENUMBER,EMAIL,CONDITIONS,GENDER) VALUES(?,?,?,?,?,?)"); // statement to add values into table
						stmt.setString(1,patF1.getText());
						
						java.sql.Date dateofBirth = new java.sql.Date(new java.util.Date().getTime()); 
								
						if(patGetDOB.getDate() !=null);
						{
							dateofBirth = new java.sql.Date(patGetDOB.getDate().getTime());
						}
						
						stmt.setDate(2, dateofBirth);
						stmt.setString(3,patF2.getText());
						stmt.setString(4,patF3.getText());
						String underlying = String.valueOf(patGetUC.getSelectedItem());
						stmt.setString(5,underlying); 
						String gender = String.valueOf(patGetGender.getSelectedItem());
						stmt.setString(6,gender);
						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Patient Registered"); 
						stmt.close();  
						con.close();  
					} catch (SQLException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Patient Registration failed!!!");
					}
				}
			}
				
		
	});
		frmMedicalAppointmentBooking.getContentPane().add(registerButton);
		
		JDateChooser bookingGetDate = new JDateChooser();
		bookingGetDate.setBounds(194, 368, 146, 20);
		frmMedicalAppointmentBooking.getContentPane().add(bookingGetDate);
		
		JComboBox bookingGetDoctor = new JComboBox();
		bookingGetDoctor.setModel(new DefaultComboBoxModel(new String[] {"Dr John", "Dr James", "Dr Joe"}));
		bookingGetDoctor.setBounds(194, 402, 146, 22);
		frmMedicalAppointmentBooking.getContentPane().add(bookingGetDoctor);
		
		JDateChooser bookingGetDOB = new JDateChooser();
		bookingGetDOB.setBounds(194, 285, 146, 20);
		frmMedicalAppointmentBooking.getContentPane().add(bookingGetDOB);
		
		JButton bookingButton = new JButton("Book");
		bookingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(bookingF1.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter a name");
				}
				
				else if(bookingGetDate.getDate() == null)
				{
					JOptionPane.showMessageDialog(null, "Please select a date");
				}
				
				else if(bookingGetDOB.getDate() == null)
				{
					JOptionPane.showMessageDialog(null, "Please select a date");
				}
				else {
					try {
						Connection con =getMedicalInfoDBConnection(); // connection address, user, password
						
						java.sql.Date bookDate = new java.sql.Date(new java.util.Date().getTime()); 
						
						if(bookingGetDate.getDate() !=null);
						{
							bookDate = new java.sql.Date(bookingGetDate.getDate().getTime());
						}
						
						String newDate = bookingGetDate.getDate().toString();
							
						
						Statement stmt1 = con.createStatement(); // establish connection same as above
						String checkName = bookingF1.getText();
						String isRegistered = "select count(*) from medical_info where patientName = '" + checkName + "'";
						ResultSet empty = stmt1.executeQuery(isRegistered);
						empty.next();
						int check = empty.getInt(1);
						
						String checkTime = "select count(*) from bookings where appDate = '" + bookDate + "' and appTime = '" + String.valueOf(bookingGetTime.getSelectedItem()) + "'";
						ResultSet timeCheck = stmt1.executeQuery(checkTime);
						timeCheck.next();
						int check1 = timeCheck.getInt(1);
						
					
						
						if(check == 0)
						{
							JOptionPane.showMessageDialog(null, "This patient is not registered \n Please register before booking");
						}
						else if(check1 == 1)
						{
							JOptionPane.showMessageDialog(null, "No bookings available at this time \n Please select another time.");
						}
						else {
						
						
							PreparedStatement stmt = con.prepareStatement("INSERT INTO BOOKINGS (PATIENTNAME, DOB,REASON,APPTIME,APPDATE,TIMEOFYEAR,DOCTOR) VALUES (?,?,?,?,?,?,?)"); // statement to add values into table
							stmt.setString(1,bookingF1.getText());
							java.sql.Date dateofBirth = new java.sql.Date(new java.util.Date().getTime()); 
							if(bookingGetDOB.getDate() !=null);
							{
								dateofBirth = new java.sql.Date(bookingGetDOB.getDate().getTime());
							}
							stmt.setDate(2, dateofBirth);
							
							String reason = String.valueOf(bookingGetReason.getSelectedItem());
							stmt.setString(3,reason);
							String time = String.valueOf(bookingGetTime.getSelectedItem());
							stmt.setString(4,time);
							
							
							java.sql.Date appointmentDate = new java.sql.Date(new java.util.Date().getTime()); 
							if(bookingGetDOB.getDate() !=null);
							{
								appointmentDate = new java.sql.Date(bookingGetDate.getDate().getTime());
							}
							stmt.setDate(5, appointmentDate);
							
							if(newDate.contains("Jan") | newDate.contains("Dec") | newDate.contains("Nov")) 
							{
								stmt.setString(6, "Winter");
							}
							
							else if(newDate.contains("Feb") | newDate.contains("Mar") | newDate.contains("Apr")) 
							{
								stmt.setString(6, "Spring");
							}
							
							else if(newDate.contains("May") | newDate.contains("Jun") | newDate.contains("Jul")) 
							{
								stmt.setString(6, "Summer");
							}
							
							else if(newDate.contains("Aug") | newDate.contains("Sep") | newDate.contains("Oct")) 
							{
								stmt.setString(6, "Autumn");
							}
							
							
							String doctor = String.valueOf(bookingGetDoctor.getSelectedItem()); // 7
							stmt.setString(7, doctor);
							
							stmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "Booking Confirmed"); // booking confirmation dialog
							
							String query = "select conditions from medical_info where patientName = '" + bookingF1.getText() + "'";
							ResultSet result = stmt1.executeQuery(query);
							result.next();
							String conditions = result.getString("conditions");
							query = "select DOB from medical_info where patientName = '" + bookingF1.getText() + "'";
							result = stmt1.executeQuery(query);
							result.next();
							String DOB = result.getString("DOB");
							query = "select timeOfYear from bookings where patientName = '" + bookingF1.getText() + "'";
							result = stmt1.executeQuery(query);
							result.next();
							String timeOfYear = result.getString("timeOfYear");
							
							query = "select timeTaken from records where timeOfYear = '" + timeOfYear + "' or underlyingCon = '" + conditions + "' or dateOfBirth = '" + DOB + "' or reason = '" + reason + "'";
							result = stmt1.executeQuery(query);
							
							ArrayList<String> list = new ArrayList<String>();
							
							while(result.next())
							{
								list.add(result.getString("timeTaken"));
							}
							
							if(list.size()>0)
							{
								String times [] = new String[list.size()];
								times = list.toArray(times);
								
								int [] timesInt = new int [times.length];
								
								for(int i = 0; i<times.length; i++)
								{
									timesInt[i] = Integer.parseInt(times[i]);
								}
								
								Arrays.sort(timesInt);
								
								for(int i = 0; i<timesInt.length; i++)
								{
									System.out.print(timesInt[i] + " ");
								}
								double median;
								if (timesInt.length % 2 == 0)
								    median = ((double)timesInt[timesInt.length/2] + (double)timesInt[timesInt.length/2 - 1])/2;
								else
								    median = (double) timesInt[timesInt.length/2];
								System.out.println("Median of array" + median);
								
								int medianInt = (int) median;
								
								JOptionPane.showMessageDialog(null, "Estimated Appointment Duration: " + medianInt + " minutes");
								
							}
							
							
							
							stmt1.close();
					        stmt.close();  
					        con.close(); 
						}
					}
					catch (SQLException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
				
			
		
		bookingButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		bookingButton.setBounds(6, 434, 137, 23);
		frmMedicalAppointmentBooking.getContentPane().add(bookingButton);
		
	
		
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		logoutButton.setBounds(909, 589, 227, 48);
		logoutButton.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent e)
				{
					frmMedicalAppointmentBooking.setVisible(false);
					login.setVisible(true);
				}
			
				});
		frmMedicalAppointmentBooking.getContentPane().add(logoutButton);
		
		JTextField enterName = new JTextField();
		enterName.setBounds(6, 496, 164, 20);
		frmMedicalAppointmentBooking.getContentPane().add(enterName);
		enterName.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(6, 527, 346, 99);
		textArea.setFont(textArea.getFont().deriveFont(Font.BOLD));
		frmMedicalAppointmentBooking.getContentPane().add(textArea);
		
		JButton getInfoButton = new JButton("Get App Info");
		getInfoButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		getInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(enterName.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter a name");
				}
				
				else {
					try {
						Connection con = getMedicalInfoDBConnection();
						Statement stmt = con.createStatement(); // establish connection same as above
						
						String incorrectName = "select count(*) from bookings where patientName = '"  + enterName.getText() + "'";
	
						ResultSet wrongName = stmt.executeQuery(incorrectName);
						wrongName.next();
						int check = wrongName.getInt(1);
						
						if(check == 0)
						{
							JOptionPane.showMessageDialog(null, "No booking with this name \n Please re-enter a name");
						}
						else {
							String query = "select * from bookings where patientName = '" + enterName.getText() +"'"; // query that will select all information when the patientName is input into text field
							ResultSet resultSet = stmt.executeQuery(query); // execute query
							
							while(resultSet.next()) {
								String name = resultSet.getString("patientName");
								String date = resultSet.getString("appDate"); // setting variables based on information from databse
								String time = resultSet.getString("appTime");
								String reason = resultSet.getString("reason");
								textArea.setText("Patient Name: " + name +  "\n Appointment Date: " + date + "\n Appointment Time: " + time + "\n Reason: " + reason);
								//JOptionPane.showMessageDialog(null, "Patient Name: " + name + " Patient Age: " + age + " Appointment Date: " + date + " Appointment Time: " + time + " Reason: " + reason);
								// show dialog, name, age, date, time, reason
							}
							
							
							}
						stmt.close();  
				        con.close(); 
					}
					
						catch (SQLException | ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					
					}
				}
		});
		getInfoButton.setBounds(194, 494, 163, 23);
		frmMedicalAppointmentBooking.getContentPane().add(getInfoButton);
		
		JLabel dataRetrieval = new JLabel("Check Appointment");
		dataRetrieval.setFont(new Font("Tahoma", Font.BOLD, 16));
		dataRetrieval.setBounds(121, 468, 242, 18);
		frmMedicalAppointmentBooking.getContentPane().add(dataRetrieval);
		
		
		records.getContentPane().setLayout(null);
		
		JLabel recordsLabel = new JLabel("Add a Booking Record");
		recordsLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		recordsLabel.setBounds(146, 24, 206, 36);
		records.getContentPane().add(recordsLabel);
		
		JLabel recordName = new JLabel("Patient Name");
		recordName.setFont(new Font("Tahoma", Font.BOLD, 14));
		recordName.setBounds(10, 71, 95, 22);
		records.getContentPane().add(recordName);
		
		JLabel recordsTime = new JLabel("Booking Time");
		recordsTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		recordsTime.setBounds(10, 108, 95, 22);
		records.getContentPane().add(recordsTime);
		
		JLabel recordsDate = new JLabel("Booking Date");
		recordsDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		recordsDate.setBounds(10, 141, 95, 22);
		records.getContentPane().add(recordsDate);
		
		JLabel recordTOY = new JLabel("Time of Year");
		recordTOY.setFont(new Font("Tahoma", Font.BOLD, 14));
		recordTOY.setBounds(10, 174, 95, 22);
		records.getContentPane().add(recordTOY);
		
		JLabel recordArrived = new JLabel("Did the patient arrive?");
		recordArrived.setFont(new Font("Tahoma", Font.BOLD, 14));
		recordArrived.setBounds(10, 207, 157, 22);
		records.getContentPane().add(recordArrived);
		
		JLabel recordsArrivalTime = new JLabel("Arrival Time");
		recordsArrivalTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		recordsArrivalTime.setBounds(10, 240, 108, 22);
		records.getContentPane().add(recordsArrivalTime);
		
		JLabel recordsTimeSeen = new JLabel("Time Seen");
		recordsTimeSeen.setFont(new Font("Tahoma", Font.BOLD, 14));
		recordsTimeSeen.setBounds(10, 267, 95, 29);
		records.getContentPane().add(recordsTimeSeen);
		
		JLabel recordsTimeFin = new JLabel("Time Finished");
		recordsTimeFin.setFont(new Font("Tahoma", Font.BOLD, 14));
		recordsTimeFin.setBounds(10, 296, 95, 22);
		records.getContentPane().add(recordsTimeFin);
		
		JTextField getRecordsName = new JTextField();
		getRecordsName.setBounds(180, 74, 96, 20);
		records.getContentPane().add(getRecordsName);
		getRecordsName.setColumns(10);
		
		JComboBox getRecordsTime = new JComboBox();
		getRecordsTime.setModel(new DefaultComboBoxModel(new String[] {"9:00", "9:15", "9:30", "9:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45"}));
		getRecordsTime.setBounds(180, 110, 96, 22);
		records.getContentPane().add(getRecordsTime);
		
		JDateChooser getRecordDate = new JDateChooser();
		getRecordDate.setBounds(180, 144, 96, 20);
		records.getContentPane().add(getRecordDate);
		
		JComboBox getRecordTOY = new JComboBox();
		getRecordTOY.setModel(new DefaultComboBoxModel(new String[] {"Spring", "Summer", "Autumn", "Winter"}));
		getRecordTOY.setBounds(180, 176, 96, 22);
		records.getContentPane().add(getRecordTOY);
		
		JComboBox getRecordArrived = new JComboBox();
		getRecordArrived.setModel(new DefaultComboBoxModel(new String[] {"Yes", "No"}));
		getRecordArrived.setBounds(180, 209, 96, 22);
		records.getContentPane().add(getRecordArrived);
		
		JTextField getRecordArrival = new JTextField();
		getRecordArrival.setBounds(180, 243, 96, 20);
		records.getContentPane().add(getRecordArrival);
		getRecordArrival.setColumns(10);
		
		JTextField getRecordSeen = new JTextField();
		getRecordSeen.setBounds(180, 273, 96, 20);
		records.getContentPane().add(getRecordSeen);
		getRecordSeen.setColumns(10);
		
		JTextField getRecordFin = new JTextField();
		getRecordFin.setBounds(180, 299, 96, 20);
		records.getContentPane().add(getRecordFin);
		getRecordFin.setColumns(10);
		
		JTextField getRecordDur = new JTextField();
		getRecordDur.setBounds(180, 331, 96, 20);
		records.getContentPane().add(getRecordDur);
		getRecordDur.setColumns(10);
		
		JLabel recordDuration = new JLabel("Time Taken");
		recordDuration.setFont(new Font("Tahoma", Font.BOLD, 14));
		recordDuration.setBounds(10, 329, 95, 17);
		records.getContentPane().add(recordDuration);
		
		
		
		JLabel recordDOB = new JLabel("Date of Birth");
		recordDOB.setFont(new Font("Tahoma", Font.BOLD, 14));
		recordDOB.setBounds(303, 77, 95, 14);
		records.getContentPane().add(recordDOB);
		
		JDateChooser getRecordDob = new JDateChooser();
		getRecordDob.setBounds(405, 74, 96, 20);
		records.getContentPane().add(getRecordDob);
		
		JLabel recordUC = new JLabel("Underlying Conditions");
		recordUC.setFont(new Font("Tahoma", Font.BOLD, 14));
		recordUC.setBounds(303, 114, 162, 22);
		records.getContentPane().add(recordUC);
		
		JComboBox getRecordUC = new JComboBox();
		getRecordUC.setModel(new DefaultComboBoxModel(new String[] {"None", "Asthma ", "Cancer", "Cystic Fibrosis", "Diabetes", "Heart Disease", "High/Low Blood Pressure", "Immuno-compromised", "Kidney Disease", "Liver Disease", "Lung Disease", "Multiple Sclerosis", "Obesity", "One or more of the above", "Other"}));
		getRecordUC.setBounds(303, 143, 157, 22);
		records.getContentPane().add(getRecordUC);
		
		JLabel recordNOC = new JLabel("Nature of Complaint");
		recordNOC.setFont(new Font("Tahoma", Font.BOLD, 14));
		recordNOC.setBounds(303, 176, 157, 29);
		records.getContentPane().add(recordNOC);
		
		JComboBox getRecordNOC = new JComboBox();
		getRecordNOC.setModel(new DefaultComboBoxModel(new String[] {"Annual Wellness Check", "Cervical Screening", "Consultation", "Mental Health", "Minor Illness ", "Minor Surgery", "Phlebotomy (Blood Test)", "Postnatal Care", "Sprain/Break/Fracture", "Urgent Appointment", "Vaccinations", "Other"}));
		getRecordNOC.setBounds(303, 209, 151, 22);
		records.getContentPane().add(getRecordNOC);
		
		JButton addRecord = new JButton("Add Record");
		addRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = getMedicalInfoDBConnection(); // connection address, user, password
					PreparedStatement stmt = con.prepareStatement("INSERT INTO RECORDS (PATIENTNAME, BOOKINGTIME,BOOKINGDATE,TIMEOFYEAR,PATIENTARRIVED,ARRIVALTIME,TIMESEEN,TIMEFINISHED,TIMETAKEN,DOB,UNDERLYINGCON,NCOMPLAINT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
					
					stmt.setString(1, getRecordsName.getText());
					stmt.setString(2, String.valueOf(getRecordsTime.getSelectedItem()));
					
					java.sql.Date bookingDate = new java.sql.Date(new java.util.Date().getTime()); 
					if(getRecordDate.getDate() !=null);
					{
						bookingDate = new java.sql.Date(getRecordDate.getDate().getTime());
					}
					
					stmt.setDate(3, bookingDate);
					stmt.setString(4, String.valueOf(getRecordTOY.getSelectedItem()));
					stmt.setString(5, String.valueOf(getRecordArrived.getSelectedItem()));
					stmt.setString(6, getRecordArrival.getText());
					stmt.setString(7, getRecordSeen.getText());
					stmt.setString(8, getRecordFin.getText());
					stmt.setString(9, getRecordDur.getText());
					
					java.sql.Date newDOB = new java.sql.Date(new java.util.Date().getTime()); 
					if(getRecordDob.getDate() !=null);
					{
						newDOB = new java.sql.Date(getRecordDob.getDate().getTime());
					}
					stmt.setDate(10, newDOB);
					
					stmt.setString(11, String.valueOf(getRecordUC.getSelectedItem()));
					stmt.setString(12, String.valueOf(getRecordNOC.getSelectedItem()));
					JOptionPane.showMessageDialog(null, "Record added to database.");
					
					
					stmt.executeUpdate();
					stmt.close();
					con.close();
					
				}
				
				catch(SQLException | ClassNotFoundException e1)
				{
					
				}
			}
		});
		addRecord.setFont(new Font("Tahoma", Font.BOLD, 14));
		addRecord.setBounds(331, 283, 134, 53);
		records.getContentPane().add(addRecord);
		
		JButton recordsButton = new JButton("Add a Record");
		recordsButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		recordsButton.setBounds(194, 434, 137, 23);
		recordsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				records.setSize(524,406);
				records.setVisible(true);
			}
			});
		
		frmMedicalAppointmentBooking.getContentPane().add(recordsButton);
		
		JLabel bookingDoctor = new JLabel("Chosen Doctor");
		bookingDoctor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bookingDoctor.setBounds(6, 406, 115, 18);
		frmMedicalAppointmentBooking.getContentPane().add(bookingDoctor);
		
		JLabel bookingDOB = new JLabel("Date of Birth");
		bookingDOB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bookingDOB.setBounds(6, 285, 90, 25);
		frmMedicalAppointmentBooking.getContentPane().add(bookingDOB);
		
		
		
		JLabel appLabel = new JLabel("Appointments:");
		appLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		appLabel.setBounds(745, 9, 125, 18);
		frmMedicalAppointmentBooking.getContentPane().add(appLabel);
		
	
		JLabel dr1Label = new JLabel("Dr John");
		dr1Label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dr1Label.setBounds(745, 38, 70, 14);
		frmMedicalAppointmentBooking.getContentPane().add(dr1Label);
		
		JLabel dr2Label = new JLabel("Dr James");
		dr2Label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dr2Label.setBounds(878, 38, 90, 14);
		frmMedicalAppointmentBooking.getContentPane().add(dr2Label);
		
		JLabel dr3Label = new JLabel("Dr Joe");
		dr3Label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dr3Label.setBounds(1004, 38, 90, 14);
		frmMedicalAppointmentBooking.getContentPane().add(dr3Label);
		
		JButton cancelButton = new JButton("Cancel Appointment");
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelAppointmentFrame.setVisible(true);	
			}
		});
		cancelButton.setBounds(700, 515, 129, 36);
		cancelButton.setSize(250, 35);
		frmMedicalAppointmentBooking.getContentPane().add(cancelButton);
		
		
		JButton clearButton = new JButton("Clear");
		clearButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dr1TA.setText("");
				dr2TA.setText("");
				dr3TA.setText("");
				
			}
		});
		clearButton.setBounds(960, 515, 129, 36);
		clearButton.setSize(150, 35);
		frmMedicalAppointmentBooking.getContentPane().add(clearButton);
		
		
		
		
		
		
		JLabel changeLabel = new JLabel("Change Appointment");
		changeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		changeLabel.setBounds(444, 465, 190, 25);
		frmMedicalAppointmentBooking.getContentPane().add(changeLabel);
		
		JLabel chNameLabel = new JLabel("Patient Name");
		chNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chNameLabel.setBounds(444, 494, 90, 20);
		frmMedicalAppointmentBooking.getContentPane().add(chNameLabel);
		
		chGetName = new JTextField();
		chGetName.setBounds(538, 496, 96, 20);
		frmMedicalAppointmentBooking.getContentPane().add(chGetName);
		chGetName.setColumns(10);
		
		JLabel chTimeLabel = new JLabel("New Time");
		chTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chTimeLabel.setBounds(444, 530, 81, 14);
		frmMedicalAppointmentBooking.getContentPane().add(chTimeLabel);
		
		JComboBox chGetTime = new JComboBox();
		chGetTime.setModel(new DefaultComboBoxModel(new String[] {"09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45"}));
		chGetTime.setBounds(538, 528, 96, 22);
		frmMedicalAppointmentBooking.getContentPane().add(chGetTime);
		
		JLabel chDateLabel = new JLabel("New Date");
		chDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chDateLabel.setBounds(444, 568, 70, 14);
		frmMedicalAppointmentBooking.getContentPane().add(chDateLabel);
		
		JDateChooser chGetDate = new JDateChooser();
		chGetDate.setBounds(538, 562, 96, 20);
		frmMedicalAppointmentBooking.getContentPane().add(chGetDate);
		
		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
				Connection con = getMedicalInfoDBConnection();
				
				
				Statement stmt = con.createStatement(); // establish connection same as above
				String incorrectName = "select count(*) from bookings where patientName = '"  + chGetName.getText() + "'";
				//PreparedStatement Pstatement=connection.prepareStatement("DELETE FROM BOOKINGS WHERE PATIENTNAME = ? AND APPDATE = ? AND DOCTOR = ? AND APPTIME = ?");
	             
				ResultSet wrongName = stmt.executeQuery(incorrectName);
				wrongName.next();
				int check = wrongName.getInt(1);
				if(check == 0)
				{
					JOptionPane.showMessageDialog(null, "No booking with this name \n Please re-enter a name");
				}
				else {
					PreparedStatement st = con.prepareStatement("UPDATE bookings SET appDate = ?, appTime = ? WHERE patientName = '" + chGetName.getText() + "'");
					
					
					String getDate = chGetDate.getDate().toString();
					StringBuilder build = new StringBuilder(getDate);
					
					for(int i = 0; i<13; i ++)
					{
						int count = 10;
						build.deleteCharAt(count);
						count++;
					}
					String getNewDate = build.toString();
					st.setString(1, getNewDate);
					st.setString(2, String.valueOf(chGetTime.getSelectedItem()));
					int count = st.executeUpdate();
					st.close();
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Appointment Updated for " + chGetName.getText() + "\nDate: " + getNewDate + "\nTime: " + String.valueOf(chGetTime.getSelectedItem()));
					
				}
				
				}
				catch(SQLException | ClassNotFoundException e1) 
				{
					
				}
				
			}
		});
		updateButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		updateButton.setBounds(444, 590, 190, 23);
		frmMedicalAppointmentBooking.getContentPane().add(updateButton);
		
		//JFrame admin = new JFrame();
		admin.setTitle("Admin Page");
		
		//admin.setSize(500,306);
		admin.setSize(530,375);
		admin.setLocationRelativeTo(null);
		
		admin.getContentPane().setLayout(null);
		JRadioButton radioButton1 = new JRadioButton("Default");
		radioButton1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radioButton1.setBounds(328, 60, 111, 23);
		
		
		JRadioButton radioButton2 = new JRadioButton("Light Gray");
		radioButton2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radioButton2.setBounds(328, 90, 111, 23);
		
		
		JRadioButton radioButton3 = new JRadioButton("Gray");
		radioButton3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radioButton3.setBounds(328, 120, 111, 23);
		
		
		JRadioButton radioButton4 = new JRadioButton("White");
		radioButton4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radioButton4.setBounds(328, 150, 111, 23);
		
		
		JLabel colourLabel = new JLabel("Change Colour");
		colourLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		colourLabel.setBounds(328, 39, 111, 14);
		admin.getContentPane().add(colourLabel);
		
		JButton adminLogout = new JButton("Logout");
		adminLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		adminLogout.setBounds(347, 218, 139, 51);
		adminLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin.setVisible(false);
				login.setVisible(true);
			}
		});
		admin.getContentPane().add(adminLogout);
		
		JRadioButton radioButton5 = new JRadioButton("Cyan");
		radioButton5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radioButton5.setBounds(328, 180, 139, 23);
		
		radioButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				admin.getContentPane().setBackground(UIManager.getColor(" Panel.Background "));
				radioButton2.setSelected(false);
				radioButton3.setSelected(false);
				radioButton4.setSelected(false);
				radioButton5.setSelected(false);
			}
		});
		admin.getContentPane().add(radioButton1);
		radioButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				admin.getContentPane().setBackground(Color.LIGHT_GRAY);
				radioButton1.setSelected(false);
				radioButton3.setSelected(false);
				radioButton4.setSelected(false);
				radioButton5.setSelected(false);
				
			}
		});
		admin.getContentPane().add(radioButton2);
		radioButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				admin.getContentPane().setBackground(Color.GRAY);
				radioButton1.setSelected(false);
				radioButton2.setSelected(false);
				radioButton4.setSelected(false);
				radioButton5.setSelected(false);
			}
		});
		
		admin.getContentPane().add(radioButton3);
		
		radioButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				admin.getContentPane().setBackground(Color.WHITE);
				radioButton1.setSelected(false);
				radioButton2.setSelected(false);
				radioButton3.setSelected(false);
				radioButton5.setSelected(false);
			}
		});
		
		admin.getContentPane().add(radioButton4);
		radioButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				admin.getContentPane().setBackground(Color.CYAN);
				radioButton1.setSelected(false);
				radioButton2.setSelected(false);
				radioButton3.setSelected(false);
				radioButton4.setSelected(false);
			}
		});
		
		admin.getContentPane().add(radioButton5);
		
		JLabel addAccountLabel = new JLabel("Create Account");
		addAccountLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		addAccountLabel.setBounds(12, 39, 111, 16);
		admin.getContentPane().add(addAccountLabel);
		
		JLabel createUsername = new JLabel("Username");
		createUsername.setBounds(6, 60, 67, 14);
		admin.getContentPane().add(createUsername);
		
		JTextField getNewUsername = new JTextField();
		getNewUsername.setBounds(72, 62, 80, 20);
		admin.getContentPane().add(getNewUsername);
		getNewUsername.setColumns(10);
		
		JLabel createPassword = new JLabel("Password");
		createPassword.setBounds(7, 95, 67, 14);
		admin.getContentPane().add(createPassword);
		
		JLabel createType = new JLabel("Account Type");
		createType.setBounds(6, 130, 85, 23);
		admin.getContentPane().add(createType);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(72, 92, 80, 20);
		admin.getContentPane().add(passwordField);
		
		JComboBox getAccType = new JComboBox();
		getAccType.setFont(new Font("Tahoma", Font.PLAIN, 9));
		getAccType.setModel(new DefaultComboBoxModel(new String[] {"Doctor", "Nurse", "Receptionist"}));
		getAccType.setBounds(93, 130, 59, 22);
		admin.getContentPane().add(getAccType);
		
		JButton createButton = new JButton("Create Account");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = getMedicalInfoDBConnection();
					PreparedStatement stmt = con.prepareStatement("INSERT into users values(?,?,?)");
					stmt.setString(1, getNewUsername.getText());
					stmt.setString(2, String.valueOf(passwordField.getPassword()));
					stmt.setString(3, String.valueOf(getAccType.getSelectedItem()));
					stmt.executeUpdate();
					stmt.close();
					con.close();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				} 
				
				
			}
		});
		createButton.setBounds(6, 165, 130, 23);
		//JButton adminButton = new JButton("Admin Page");
		
		JFrame registerFrame = new JFrame();
		registerFrame.setVisible(false);
		
		JButton adminRegisterButton = new JButton("Register");
		adminRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerFrame.setVisible(true);
			}
		});
		adminRegisterButton.setBounds(12, 234, 89, 23);
		
		
		
		String[] rgender={"Female","Male","Other","Prefer Not To Say"};
		JLabel rnameLabel=new JLabel("NAME");
		JLabel rgenderLabel=new JLabel("GENDER");
		JLabel rsurnameLabel=new JLabel("SURNAME");
		JLabel rpasswordLabel=new JLabel("PASSWORD");
		JLabel rconfirmPasswordLabel=new JLabel("CONFIRM PASSWORD");
		JLabel raddressLabel=new JLabel("ADDRESS");
		JLabel remailLabel=new JLabel("EMAIL");
		JTextField rnameTextField=new JTextField();
		JComboBox rgenderComboBox=new JComboBox(rgender);
		JTextField rsurnameTextField=new JTextField();
		JPasswordField rpasswordField=new JPasswordField();
		JPasswordField rconfirmPasswordField=new JPasswordField();
		JTextField raddressTextField=new JTextField();
		JTextField remailTextField=new JTextField();
		JButton rloginButton=new JButton("LOGIN");
		JButton rregisterButton=new JButton("REGISTER");
		JButton rresetButton=new JButton("RESET");
		
		rnameLabel.setBounds(20,20,40,70);
	    rgenderLabel.setBounds(20,70,80,70);
	    rsurnameLabel.setBounds(20,120,100,70);
	    rpasswordLabel.setBounds(20,170,100,70);
	    rconfirmPasswordLabel.setBounds(20,220,140,70);
	    raddressLabel.setBounds(20,270,100,70);
	    remailLabel.setBounds(20,320,100,70);
	    rnameTextField.setBounds(180,43,165,23);
	    rgenderComboBox.setBounds(180,93,165,23);
	    rsurnameTextField.setBounds(180,143,165,23);
	    rpasswordField.setBounds(180,193,165,23);
	    rconfirmPasswordField.setBounds(180,243,165,23);
	    raddressTextField.setBounds(180,293,165,23);
	    remailTextField.setBounds(180,343,165,23);
	    rloginButton.setBounds(110,500,100,35);
	    rregisterButton.setBounds(10,500,100,35);
	    rresetButton.setBounds(200,500,100,35);
	    
	    registerFrame.add(rnameLabel);
	    registerFrame.add(rgenderLabel);
	    registerFrame.add(rsurnameLabel);
	    registerFrame.add(rpasswordLabel);
	    registerFrame.add(rconfirmPasswordLabel);
	    registerFrame.add(raddressLabel);
	    registerFrame.add(remailLabel);
	    registerFrame.add(rnameTextField);
	    registerFrame.add(rgenderComboBox);
	    registerFrame.add(rsurnameTextField);
	    registerFrame.add(rpasswordField);
	    registerFrame.add(rconfirmPasswordField);
	    registerFrame.add(raddressTextField);
	    registerFrame.add(remailTextField);
	    registerFrame.add(rloginButton);
	    registerFrame.add(rregisterButton);
	    registerFrame.add(rresetButton);
	    
	    rregisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	                //Creating Connection Object
	                Connection connection=getMedicalInfoDBConnection();
	                PreparedStatement Pstatement=connection.prepareStatement("insert into login values(?,?,?,?,?,?,?,?,?)");
	                //Specifying the values of it's parameter
	                Pstatement.setString(1,rnameTextField.getText());
	                Pstatement.setString(2,rgenderComboBox.getSelectedItem().toString());
	                Pstatement.setString(3,rsurnameTextField.getText());
	                Pstatement.setString(4,rpasswordField.getText());
	                Pstatement.setString(5,rconfirmPasswordField.getText());
	                Pstatement.setString(6,raddressTextField.getText());
	                Pstatement.setString(7,remailTextField.getText());
	                //Checking if the Passwords match
	                if(passwordField.getText().equalsIgnoreCase(rconfirmPasswordField.getText()))
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
	            } catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	 
	 
				}
			}
			);
	    rresetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	    
	    registerFrame.setTitle("REGISTER");
	    registerFrame.setBackground(Color.white);
	    registerFrame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
	    registerFrame.setBounds(200, 200, 500, 600);
	    registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    registerFrame.getContentPane().setLayout(null);
	    registerFrame.setResizable(false);
		
		
		
		admin.getContentPane().add(adminRegisterButton);
		admin.getContentPane().add(createButton);
		
		login.setVisible(true);
	    
	    //admin.setVisible(true);
		
		
		
		
		//frmMedicalAppointmentBooking.setVisible(true);
}
	
	private static JFrame getUserRegistrationFrame()
	{
		JFrame frame = new JFrame();	
		
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
	    
	    frame = new JFrame();
		frame.setTitle("REGISTER");
		frame.setBackground(Color.white);
		//frame.getContentPane().setBackground(new Color(106, 90, 205));
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.setBounds(200, 200, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		frame.setSize(380,550);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		
		
		
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
        
        registerButton.setBounds(180,400,100,35);
        registerButton.setSize(160, 30);
        
        loginButton.setBounds(10,450,100,35);
        loginButton.setSize(160, 30);
        
        resetButton.setBounds(180,450,100,35);
        resetButton.setSize(160, 30);
		
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
        
        registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 registerUser(nameTextField.getText(), 
						 genderComboBox.getSelectedItem().toString(), 
						 sirNameTextField.getText(), 
						 passwordField.getText(),
						 confirmPasswordField.getText(), 
						 addressTextField.getText(), emailTextField.getText());
				
			}
		});
        
        
        resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 nameTextField.setText("");
		         genderComboBox.setSelectedItem("Female");
		         //ageTextField.setText("");
		         sirNameTextField.setText("");
		         passwordField.setText("");
		         confirmPasswordField.setText("");
		         addressTextField.setText("");
		         emailTextField.setText("");
			}
		});
        
        loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				userRegistrationFrame.setVisible(false);
				login.setVisible(true);
			}
		});

        frame.setVisible(false);
	    return frame;
	}
	
	private static JFrame getCancelAppointmentFrame()
	{
		JFrame frame = new JFrame();	

		String[] times = new String[] {"09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45"};
	    JLabel nameLabel=new JLabel("Patient Name");
	    JLabel appDateLabel=new JLabel("Appointment Date");
	    JLabel timeLabel=new JLabel("Appointment Time");
	    JLabel doctorLabel=new JLabel("Doctor ");
	    
	    JTextField nameTextField=new JTextField();
	    JComboBox<?> timesComboBox=new JComboBox(times);
	    JDateChooser appDate = new JDateChooser();
	    JComboBox bookingGetDoctor = new JComboBox();
		bookingGetDoctor.setModel(new DefaultComboBoxModel(new String[] {"Dr John", "Dr James", "Dr Joe"}));
	    
	    JButton cancelButton=new JButton("Cancel Appointment");
	   	    
	    frame = new JFrame();
		frame.setTitle("CANCEL APPOINTMENT");
		frame.setBackground(Color.white);
		//frame.getContentPane().setBackground(new Color(106, 90, 205));
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.setBounds(200, 200, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		frame.setSize(420,300);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		 //Setting Location and Size of Each Component
        nameLabel.setBounds(20,20,140,70);
        appDateLabel.setBounds(20,60,180,70);
        timeLabel.setBounds(20,100,220,70);
        doctorLabel.setBounds(20,140,220,70);
        
        
        nameTextField.setBounds(180,43,165,23);
        appDate.setBounds(180,83,165,23);
        timesComboBox.setBounds(180,123,165,23);
        bookingGetDoctor.setBounds(180,163,165,23);
        
      
        cancelButton.setBounds(180,210,100,35);
        cancelButton.setSize(150, 30);
        
        		
        //Adding components to Frame
        frame.add(nameLabel);
        frame.add(timeLabel);
        frame.add(appDateLabel);
        frame.add(doctorLabel);
       
        
        frame.add(nameTextField);
        frame.add(timesComboBox);
        frame.add(appDate);
        frame.add(bookingGetDoctor);
        
        
        frame.add(cancelButton);
      
        cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String patientName= nameTextField.getText();
				String doctor= bookingGetDoctor.getSelectedItem().toString();
				
				java.sql.Date appointmentDate = new java.sql.Date(new java.util.Date().getTime()); 
				if(appDate.getDate() !=null);
				{
					appointmentDate = new java.sql.Date(appDate.getDate().getTime());
				}
				String time = timesComboBox.getSelectedItem().toString();
				
				int recCount = cancelAppointment(patientName,appointmentDate, time,doctor);
				if(recCount == 1)
				{
					JOptionPane.showMessageDialog(null,"Appointment Successfully cancelled");
					cancelAppointmentFrame.setVisible(false);
				}else
				{
					JOptionPane.showMessageDialog(null,"Appointment cancellation Failed or Appointment NOT found.!!");
				}

			}
		});
        
	
		return frame;
	}
	
	
	public static int cancelAppointment(String patientName,java.sql.Date bookingDate, String time , String doctor)
	{
		 try {
             //Creating Connection Object
        	 Connection connection=getMedicalInfoDBConnection(); 
        	 PreparedStatement Pstatement=connection.prepareStatement("DELETE FROM BOOKINGS WHERE PATIENTNAME = ? AND APPDATE = ? AND DOCTOR = ? AND APPTIME = ?");
             //Specifying the values of it's parameter
             Pstatement.setString(1,patientName);
             Pstatement.setDate(2,bookingDate);
             //Pstatement.setString(3,ageTextField.getText());
             Pstatement.setString(3,doctor);
             Pstatement.setString(4,time);
             int count = Pstatement.executeUpdate();
             return count;
         } catch (SQLException | ClassNotFoundException e1) {
             e1.printStackTrace();
         }
         return 0;
	}
	public static void registerUser(String name, String gender, String sirName, String password, String cnfPassword, String address, String mail)
	{
		 System.out.println("Registration triggred");
         try {
             //Creating Connection Object
        	 Connection connection=getFirstDBConnection(); 
        	 PreparedStatement Pstatement=connection.prepareStatement("insert into login values(?,?,?,?,?,?,?)");
             //Specifying the values of it's parameter
             Pstatement.setString(1,name);
             Pstatement.setString(2,gender);
             //Pstatement.setString(3,ageTextField.getText());
             Pstatement.setString(3,sirName);
             Pstatement.setString(4,password);
             Pstatement.setString(5,cnfPassword);
             Pstatement.setString(6,address);
             Pstatement.setString(7,mail);
             //Checking if the Passwords match
             if(password.equalsIgnoreCase(cnfPassword))
             {
                 //Executing query
                 Pstatement.executeUpdate();
                 JOptionPane.showMessageDialog(null,"Registered Successfully");
             }
             else
             {
                 JOptionPane.showMessageDialog(null,"password doesn't match");
             }

         } catch (SQLException | ClassNotFoundException e1) {
             e1.printStackTrace();
         }
	}
	
	public static boolean checkUserLogin(String name,  String password)
	{
		 System.out.println("Registration triggred");
         try {
             //Creating Connection Object
        	 Connection connection=getFirstDBConnection(); 
        	 PreparedStatement Pstatement=connection.prepareStatement("SELECT * FROM LOGIN WHERE USERNAME = ? AND PASSWRD = ?");
             //Specifying the values of it's parameter
             Pstatement.setString(1,name);
             Pstatement.setString(2,password);
             ResultSet res = Pstatement.executeQuery();
			if(res.next())
				return true;

         } catch (SQLException | ClassNotFoundException e1) {
             e1.printStackTrace();
         }
         return false;
		
	}
	public static Connection getMedicalInfoDBConnection() throws ClassNotFoundException
	{
		 try 
		 {
			 Class.forName("com.mysql.cj.jdbc.Driver"); // driver for java database connection (added by external jar)
			 String url = "jdbc:mysql://localhost:3306/"+MED_DB_NAME+"?allowPublicKeyRetrieval=true&useSSL=FALSE"; // string for where to make connection
			 return DriverManager.getConnection(url, MED_DB_USER, MED_DB_PASSWORD);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}
	
	public static Connection getFirstDBConnection() throws ClassNotFoundException
	{
		 try 
		 {
			 Class.forName("com.mysql.cj.jdbc.Driver"); 
			 String url = "jdbc:mysql://localhost:3306/"+FIRST_DB_NAME+"?allowPublicKeyRetrieval=true&useSSL=FALSE";
			 return DriverManager.getConnection(url, FIRST_DB_USER, FIRST_DB_PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}
	
	 private static ImageIcon getImageLabel()
	   {
		   BufferedImage myPicture;
	       
	       try 
			{
	    	   File imageFile = new File("icons/HSE.png");
	    	   
	    	   //Pufferung des Bildes
	    	   myPicture = ImageIO.read(imageFile);
	    	   
	    	   //Rückgabe als Bildsymbol.
			   return new ImageIcon(myPicture);
			}catch (IOException e) {
				e.printStackTrace();
			}
	       return null;
	   }
		public static void main (String [] args)
		{
			Medicalapp t = Medicalapp.getTest();
			t.run();
			
		}
}