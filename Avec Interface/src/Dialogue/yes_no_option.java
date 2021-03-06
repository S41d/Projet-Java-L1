package Dialogue;

import RoundedBorders.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class yes_no_option extends JFrame {
    public static JLabel label;
    public static JButton YES;
    public static JButton NO;

    public yes_no_option(String text) {
        this.setSize(350, 150);
        this.getContentPane().setBackground(new Color(72, 201, 176));

        label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.darkGray);
        label.setBounds(0, 15, 350, 30);

        YES = new RoundedButton("Oui");
        YES.setBounds(115, 60, 60, 30);
        YES.setBackground(new Color(243, 144, 57));
        YES.setForeground(Color.DARK_GRAY);
        YES.addActionListener(actionEvent -> this.dispose());

        NO = new RoundedButton("Non");
        NO.setBounds(185, 60, 60, 30);
        NO.setBackground(new Color(41, 41, 41));
        NO.setForeground(Color.lightGray);
        NO.addActionListener(actionEvent -> this.dispose());

        this.add(label);
        this.add(YES);
        this.add(NO);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }
}
