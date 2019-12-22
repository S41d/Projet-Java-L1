package super_admin;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import javax.swing.*;
import java.util.Scanner;
import RoundedBorders.*;

public class Main {
    public static void main(String[] args) {
	   JFrame frame = new JFrame("Login");
	   frame.setSize(600, 320);

	   JTextField dummy = new JTextField();
	   dummy.requestFocusInWindow();  // pour que ca focus pas directement sur les autres textefields

	   JTextField textField = new RoundedTextField();
	   textField.setText("Username");
	   textField.setBackground(new Color(79, 79, 79));
	   textField.setForeground(Color.LIGHT_GRAY);
	   textField.setHorizontalAlignment(JTextField.CENTER);
	   textField.setBounds(50, 30, 500, 40);
	   textField.addFocusListener(new FocusListener() {
		  @Override
		  public void focusGained(FocusEvent focusEvent) {
			 if (textField.getText().toUpperCase().equals("USERNAME"))
			     textField.setText("");
		  }

		  @Override
		  public void focusLost(FocusEvent focusEvent) {
		      if (textField.getText().isEmpty())
		          textField.setText("Username");
		  }
	   });

	   JPasswordField passwordField = new RoundedPasswordField();
	   passwordField.setText("Password");
	   passwordField.setBackground(new Color(79, 79, 79));
	   passwordField.setForeground(Color.LIGHT_GRAY);
	   char Char = passwordField.getEchoChar();
	   passwordField.setEchoChar((char)0);
	   passwordField.setHorizontalAlignment(JPasswordField.CENTER);
	   passwordField.setBounds(50, 90, 500, 40);
	   passwordField.addFocusListener(new FocusListener() {
		  @Override
		  public void focusGained(FocusEvent focusEvent) {
			 String password = String.valueOf(passwordField.getPassword());
			 if (password.toUpperCase().equals("PASSWORD")){
			     passwordField.setText("");
			     passwordField.setEchoChar(Char);
			 }
		  }
		  @Override
		  public void focusLost(FocusEvent focusEvent) {
			 String password = String.valueOf(passwordField.getPassword());
		      if (password.isEmpty()){
		          passwordField.setEchoChar((char) 0);
		          passwordField.setText("Password");
			 }
		  }
	   });

	   JLabel label = new JLabel();
	   label.setForeground(Color.lightGray);
	   label.setBounds(0, 250, 600, 30);
	   Timer timer = new Timer(2000, arg0 -> label.setText(""));

	   JButton login_button = new RoundedButton("Login"); // Logging in button
	   login_button.setBounds(110, 150, 120, 40);
	   login_button.setBackground(new Color(51, 208, 240));
	   login_button.setForeground(Color.darkGray);
	   login_button.addActionListener(arg0 -> {
		  String username = textField.getText();
		  String password = new String(passwordField.getPassword());

		  File passwords_file = new File("data/Roles.txt");
		  FileReader reader;
		  try {
			 reader = new FileReader(passwords_file);
			 Scanner scanner = new Scanner(reader);

			 while (scanner.hasNextLine()) {
				String string = scanner.nextLine();
				String usernames = string.substring(string.indexOf("Username: "), string.indexOf("$USER"));
				String passwords = string.substring(string.indexOf("Password: "), string.indexOf("$P"));
				String roles = string.substring(string.indexOf("Role: "), string.indexOf("$R"));

				if (usernames.contains(username) && passwords.equals("Password: " + password) && roles.equals("Role: " + "Medecin")) {
				    label.setHorizontalAlignment(SwingConstants.CENTER);
				    label.setText("loggin in as medecin");
				    timer.start();
				    break;
				} else if (usernames.contains(username) && passwords.equals("Password: " + password) && roles.equals("Role: " + "Agent")) {
				    label.setHorizontalAlignment(SwingConstants.CENTER);
				    label.setText("loggin in as agent");
				    timer.start();
				    Thread.sleep(1000);
				    Agent.Main.ui();
				    break;
				} else if (usernames.contains(username) && passwords.equals("Password: " + password) && roles.equals("Role: " + "Technicien")) {
				    label.setHorizontalAlignment(SwingConstants.CENTER);
				    label.setText("loggin in as IT");
				    timer.start();
				    break;
				} else {
				    label.setHorizontalAlignment(SwingConstants.CENTER);
				    label.setText("exist pas");
				}
			 }
			 scanner.close();
		  } catch (FileNotFoundException e) {
			 JOptionPane.showMessageDialog(frame, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
		  } catch (InterruptedException e) {
			 e.printStackTrace();
		  }
	   }); // Logging in button

	   JButton create_an_account_Button = new RoundedButton("Creer un nouveau compte"); // Creating a new account
	   create_an_account_Button.setBackground(Color.darkGray);
	   create_an_account_Button.setForeground(Color.lightGray);
	   create_an_account_Button.setBounds(110, 200, 380, 40);
	   create_an_account_Button.addActionListener(arg0 -> Create_an_account.ui());


	   JButton modifier_Button = new RoundedButton("Modifier");
	   modifier_Button.setBounds(240, 150, 120, 40);
	   modifier_Button.setBackground(new Color(243, 144, 57));
	   modifier_Button.setForeground(Color.darkGray);
	   modifier_Button.addActionListener(arg0 -> Modifier.takeNum());

	   JButton supprimer_Button = new RoundedButton("Supprimer");
	   supprimer_Button.setBounds(370, 150, 120, 40);
	   supprimer_Button.setBackground(new Color(244, 72, 72));
	   supprimer_Button.setForeground(Color.darkGray);
	   supprimer_Button.addActionListener(e -> Supprimer.ui());

	   frame.add(textField); frame.add(passwordField); frame.add(label);	frame.add(dummy);
	   frame.add(create_an_account_Button); frame.add(modifier_Button); frame.add(supprimer_Button);
	   frame.add(login_button);
	   frame.getContentPane().setBackground(Color.DARK_GRAY); frame.setResizable(false);
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.setLocationRelativeTo(null); frame.setLayout(null);
	   frame.setVisible(true);
    }
}