package Medecin;

import Classes_principales.Consultations;
import Classes_principales.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class detailsPatients extends JFrame {
    public detailsPatients(int ID) throws FileNotFoundException {
        this.setSize(800, 680);
        this.getContentPane().setBackground(new Color(52, 225, 245));

        JPanel infoPanel = new JPanel(new GridLayout(2, 4));
        infoPanel.setBounds(0, 0, 800, 100);
        infoPanel.setBackground(new Color(46, 188, 207));
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        String patient = Patient.details(ID);
        String Nom = patient.substring(patient.indexOf("Nom:"), patient.indexOf("$N"));
        Nom = Nom.substring(Nom.indexOf(" "));
        String Prenom = patient.substring(patient.indexOf("Prenom:"), patient.indexOf("$Pn"));
        Prenom = Prenom.substring(Prenom.indexOf(" "));
        String Adresse = patient.substring(patient.indexOf("Adresse:"), patient.indexOf("$A"));
        Adresse = Adresse.substring(Adresse.indexOf(" "));
        String Date = patient.substring(patient.indexOf("Date de naissance"), patient.indexOf("$Date"));
        Date = Date.substring(Date.lastIndexOf(" "));

        JLabel nomLabel = new JLabel("  Nom:");
        nomLabel.setBounds(50, 20, 100, 30);

        JTextField nomField = new JTextField();
        nomField.setBounds(120, 20, 200, 30);
        nomField.setText(Nom);
        nomField.setEditable(false);

        JLabel prenomLabel = new JLabel("  Prenom:");
        prenomLabel.setBounds(350, 20, 100, 30);

        JTextField prenomField = new JTextField();
        prenomField.setBounds(500, 20, 200, 30);
        prenomField.setText(Prenom);
        prenomField.setEditable(false);

        JLabel adresseLabel = new JLabel("  Adresse:");
        adresseLabel.setBounds(50, 60, 100, 30);
        adresseLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));

        JTextField adresseField = new JTextField();
        adresseField.setBounds(120, 60, 200, 30);
        adresseField.setText(Adresse);
        adresseField.setEditable(false);

        JLabel dateLabel = new JLabel("  Date de naissance:");
        dateLabel.setBounds(350, 60, 150, 30);
        dateLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));

        JTextField dateField = new JTextField();
        dateField.setBounds(500, 60, 200, 30);
        dateField.setText(Date);
        dateField.setEditable(false);

        String[] column = {"ID", "Consultations", "Appareil 1", "Appareil 2", "Appareil 3"};
        String[][] lignes = new String[0][column.length];

        DefaultTableModel tableModel = new DefaultTableModel(lignes, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            File file = new File("data/Consultations.txt");
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner_Consultations = new Scanner(reader);

            while (scanner_Consultations.hasNextLine()) {
                String ligneConsultation = scanner_Consultations.nextLine();
                int IDPatient = Integer.parseInt(ligneConsultation.substring(ligneConsultation.indexOf("ID: "), ligneConsultation.indexOf("$ID")).substring(4));

                if (IDPatient == ID) {
                    int IDConsultation = Integer.parseInt(ligneConsultation.substring(0, ligneConsultation.indexOf(" ")));
                    String Consultation;
                    String Appareil;
                    String Appareil_1 = "";
                    String Appareil_2 = "";
                    String Appareil_3 = "";
                    if (ligneConsultation.contains("$APPAREIL$")) {
                        Consultation = ligneConsultation.substring(ligneConsultation.indexOf("$ID"), ligneConsultation.indexOf("$APPAREIL$")).substring(4);
                        Appareil = Consultations.getAppareilUI(IDConsultation);
                        if (Appareil.contains("Appareil_1"))
                            Appareil_1 = Appareil.substring(Appareil.indexOf("Appareil_1"), Appareil.indexOf("$A1")).substring(12);
                        if (Appareil.contains("Appareil_2"))
                            Appareil_2 = Appareil.substring(Appareil.indexOf("Appareil_2"), Appareil.indexOf("$A2")).substring(12);
                        if (Appareil.contains("Appareil_3"))
                            Appareil_3 = Appareil.substring(Appareil.indexOf("Appareil_3"), Appareil.indexOf("$A3")).substring(12);
                    } else {
                        Consultation = ligneConsultation.substring(ligneConsultation.indexOf("$ID")).substring(4);
                    }
                    tableModel.addRow(new Object[]{IDConsultation, Consultation, Appareil_1, Appareil_2, Appareil_3});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JTable table = new JTable();
        table.setBackground(new Color(46, 188, 207));
        table.setForeground(Color.darkGray);
        table.setModel(tableModel);
        table.setBounds(50, 150, 700, 400);

        JTableHeader header = table.getTableHeader();
        header.setBounds(50, 130, 700, 20);
        header.getColumnModel().getColumn(1).setPreferredWidth(400);
        header.getColumnModel().getColumn(0).setPreferredWidth(40);

        JTextField searchbar = new JTextField("Chercher une consultation");
        searchbar.setBounds(50, 560, 700, 30);
        searchbar.setHorizontalAlignment(JTextField.CENTER);
        searchbar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (searchbar.getText().equals("Chercher une consultation"))
                    searchbar.setText("");
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (searchbar.getText().isEmpty())
                    searchbar.setText("Chercher une consultation");
            }
        });
        searchbar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tableModel.setRowCount(0);
                table.setModel(tableModel);
                try {
                    File file = new File("data/Consultations.txt");
                    FileInputStream inputStream = new FileInputStream(file);
                    InputStreamReader reader = new InputStreamReader(inputStream);
                    Scanner scanner_Consultations = new Scanner(reader);

                    while (scanner_Consultations.hasNextLine()) {
                        String ligneConsultation = scanner_Consultations.nextLine();
                        int IDPatient = Integer.parseInt(ligneConsultation.substring(ligneConsultation.indexOf("ID: "), ligneConsultation.indexOf("$ID")).substring(4));

                        if (IDPatient == ID) {
                            int IDConsultation = Integer.parseInt(ligneConsultation.substring(0, ligneConsultation.indexOf(" ")));
                            String Consultation;
                            String Appareil;
                            String Appareil_1 = "";
                            String Appareil_2 = "";
                            String Appareil_3 = "";
                            if (ligneConsultation.contains("$APPAREIL$")) {
                                Consultation = ligneConsultation.substring(ligneConsultation.indexOf("$ID"), ligneConsultation.indexOf("$APPAREIL$")).substring(4);
                                Appareil = Consultations.getAppareilUI(IDConsultation);
                                if (Appareil.contains("Appareil_1"))
                                    Appareil_1 = Appareil.substring(Appareil.indexOf("Appareil_1"), Appareil.indexOf("$A1")).substring(12);
                                if (Appareil.contains("Appareil_2"))
                                    Appareil_2 = Appareil.substring(Appareil.indexOf("Appareil_2"), Appareil.indexOf("$A2")).substring(12);
                                if (Appareil.contains("Appareil_3"))
                                    Appareil_3 = Appareil.substring(Appareil.indexOf("Appareil_3"), Appareil.indexOf("$A3")).substring(12);
                            } else {
                                Consultation = ligneConsultation.substring(ligneConsultation.indexOf("$ID")).substring(4);
                            }
                            String searchid = Integer.toString(IDConsultation);
                            if (Consultation.contains(searchbar.getText()) || searchid.contains(searchbar.getText()))
                                tableModel.addRow(new Object[]{IDConsultation, Consultation, Appareil_1, Appareil_2, Appareil_3});
                        }
                    }
                } catch (Exception i) {
                    i.printStackTrace();
                }
            }
        });

        infoPanel.add(nomLabel);
        infoPanel.add(nomField);
        infoPanel.add(prenomLabel);
        infoPanel.add(prenomField);
        infoPanel.add(adresseLabel);
        infoPanel.add(adresseField);
        infoPanel.add(dateLabel);
        infoPanel.add(dateField);

        this.add(searchbar);
        this.setLocationRelativeTo(null);
        this.add(infoPanel);
        this.add(table);
        this.add(header);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
