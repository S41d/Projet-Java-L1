package Dialogue;

import RoundedBorders.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class dialogue extends JFrame {
    JLabel label;
    JButton button;

    public dialogue(String text) {
        this.setSize(350, 150);
        this.getContentPane().setBackground(Color.darkGray);

        label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.LIGHT_GRAY);
        label.setBounds(0, 15, 350, 30);

        button = new RoundedButton("OK");
        button.setBounds(145, 60, 60, 30);
        button.setBackground(new Color(79, 79, 79));
        button.setForeground(Color.LIGHT_GRAY);
        button.addActionListener(actionEvent -> this.dispose());

        this.add(label);
        this.add(button);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }

    public dialogue(String text, Color labelForeground, Color labelBackground, Color btnForeground, Color btnBackground) {
        this.setSize(350, 150);
        this.getContentPane().setBackground(labelBackground);

        label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(labelForeground);
        label.setBounds(0, 15, 350, 30);

        button = new RoundedButton("OK");
        button.setBounds(145, 60, 60, 30);
        button.setBackground(btnBackground);
        button.setForeground(btnForeground);
        button.addActionListener(actionEvent -> this.dispose());

        this.add(label);
        this.add(button);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }
}
