package Agent;

import Classes_principales.Patient;
import RoundedBorders.RoundedButton;
import RoundedBorders.RoundedFormattedTextField;
import RoundedBorders.RoundedTextField;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;

public class Creer_Patient {
    public static void ui() throws ParseException {
	   JFrame frame = new JFrame("Créer un nouveau compte");
	   frame.setSize(650, 400);
	   frame.getContentPane().setBackground(Color.darkGray);

	   JTextField userField = new RoundedTextField();
	   userField.setBackground(new Color(79, 79, 79));
	   userField.setForeground(Color.LIGHT_GRAY);
	   userField.setBounds(150, 30, 400, 40);

	   JLabel login_Label = new JLabel("Nom :");
	   login_Label.setForeground(Color.LIGHT_GRAY);
	   login_Label.setBounds(50, 30, 100, 40);

	   JTextField prenomField = new RoundedTextField();
	   prenomField.setBackground(new Color(79, 79, 79));
	   prenomField.setForeground(Color.LIGHT_GRAY);
	   prenomField.setBounds(150, 90, 400, 40);

	   JLabel pass2_label = new JLabel("Prenom :");
	   pass2_label.setForeground(Color.LIGHT_GRAY);
	   pass2_label.setBounds(50, 90, 100, 40);

	   MaskFormatter adresseFormatter = new MaskFormatter("#####");
	   JFormattedTextField adresseField = new RoundedFormattedTextField();
	   adresseFormatter.install(adresseField);
	   adresseField.setBackground(new Color(79, 79, 79));
	   adresseField.setForeground(Color.LIGHT_GRAY);
	   adresseField.setBounds(150, 150, 400, 40);

	   JLabel adresse_label = new JLabel("<html>Code <br>postal :</html>");
	   adresse_label.setForeground(Color.LIGHT_GRAY);
	   adresse_label.setBounds(50, 150, 100, 40);

	   MaskFormatter dateFormatter = new MaskFormatter("##-##-####");
	   dateFormatter.setPlaceholderCharacter(' ');
	   JFormattedTextField dateFeild = new RoundedFormattedTextField();
	   dateFeild.setText("jj-mm-aaaa");
	   dateFeild.setBackground(new Color(79, 79, 79));
	   dateFeild.setForeground(Color.LIGHT_GRAY);
	   dateFeild.setBounds(150, 210, 400, 40);
	   dateFeild.addFocusListener(new FocusListener() {
		  @Override
		  public void focusGained(FocusEvent focusEvent) {
			 if (dateFeild.getText().equals("jj-mm-aaaa")) {
				dateFeild.setText("");
				dateFormatter.install(dateFeild);
				dateFormatter.setAllowsInvalid(false);
			 }
		  }
		  @Override
		  public void focusLost(FocusEvent focusEvent) {
			 if (dateFeild.getText().equals("  -  -    ")) {
				dateFormatter.setAllowsInvalid(true);
				dateFeild.setText("jj-mm-aaaa");
			 }
		  }
	   });

	   JLabel date_lablel = new JLabel("Date :");
	   date_lablel.setForeground(Color.LIGHT_GRAY);
	   date_lablel.setBounds(50, 210, 100, 40);

	   JButton btnAnnuler = new RoundedButton("Annuler");
	   btnAnnuler.setBackground(new Color(240, 74, 82));
	   btnAnnuler.setForeground(Color.DARK_GRAY);
	   btnAnnuler.setBounds(335, 300, 150, 40);
	   btnAnnuler.addActionListener(actionEvent -> frame.dispose());

	   JButton create_Button = new RoundedButton("Créer compte");
	   create_Button.setBounds(175, 300, 150, 40);
	   create_Button.setBackground(new Color(105, 205, 160));
	   create_Button.addActionListener(arg0 -> {
		  if (userField.getText().isEmpty() || prenomField.getText().isEmpty() || dateFeild.getText().equals("  -  -    ") || adresseField.getText().isEmpty()) {
			  empty_fields();
		} else {
			  Patient.nouveauPatient(userField.getText(), prenomField.getText(), Integer.parseInt(adresseField.getText()), dateFeild.getText());
		}
	   });

	   frame.add(date_lablel);
	   frame.add(dateFeild);
	   frame.add(adresseField);
	   frame.add(adresse_label);
	   frame.add(btnAnnuler);
	   frame.add(prenomField);
	   frame.add(pass2_label);
	   frame.add(userField);
	   frame.add(login_Label);
	   frame.add(create_Button);
	   frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   frame.setLocationRelativeTo(null);
	   frame.setLayout(null);
	   frame.setVisible(true);
    }
    // les interfaces pour les erreurs

    public static void password_dont_match() {
	   JFrame frame = new JFrame();
	   frame.setSize(350, 130);
	   frame.getContentPane().setBackground(Color.darkGray);

	   JLabel label = new JLabel("Les mots de passes ne sont pas le meme");
	   label.setHorizontalAlignment(JLabel.CENTER);
	   label.setForeground(Color.LIGHT_GRAY);
	   label.setBounds(0, 20, 350, 30);

	   JButton button = new RoundedButton("OK");
	   button.setBounds(145, 60, 60, 30);
	   button.setBackground(new Color(79, 79, 79));
	   button.setForeground(Color.LIGHT_GRAY);
	   button.addActionListener(actionEvent -> frame.dispose());

	   frame.add(label);
	   frame.add(button);
	   frame.setLocationRelativeTo(null);
	   frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   frame.setLayout(null);
	   frame.setVisible(true);
    }

    public static void role_incorrect() {
	   JFrame frame = new JFrame();
	   frame.setSize(350, 130);
	   frame.getContentPane().setBackground(Color.darkGray);

	   JLabel label = new JLabel("<html><div style='text-align: center;'>Role incorrect<br>" +
			 "Choisir entre Medecin,Agent et Technicien</html>");
	   label.setForeground(Color.LIGHT_GRAY);
	   label.setBounds(20, 20, 350, 30);

	   JButton button = new RoundedButton("OK");
	   button.setBounds(145, 60, 60, 30);
	   button.setBackground(new Color(79, 79, 79));
	   button.setForeground(Color.LIGHT_GRAY);
	   button.addActionListener(actionEvent -> frame.dispose());

	   frame.add(label);
	   frame.add(button);
	   frame.setLocationRelativeTo(null);
	   frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   frame.setLayout(null);
	   frame.setVisible(true);
    }

    public static void empty_fields() {
	   JFrame frame = new JFrame();
	   frame.setSize(350, 130);
	   frame.getContentPane().setBackground(Color.darkGray);

	   JLabel label = new JLabel("Un ou plusieurs champs est vide");
	   label.setForeground(Color.LIGHT_GRAY);
	   label.setBounds(20, 20, 350, 30);

	   JButton button = new RoundedButton("OK");
	   button.setBounds(145, 60, 60, 30);
	   button.setBackground(new Color(79, 79, 79));
	   button.setForeground(Color.LIGHT_GRAY);
	   button.addActionListener(actionEvent -> frame.dispose());

	   frame.add(label);
	   frame.add(button);
	   frame.setLocationRelativeTo(null);
	   frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   frame.setLayout(null);
	   frame.setVisible(true);
    }
}
