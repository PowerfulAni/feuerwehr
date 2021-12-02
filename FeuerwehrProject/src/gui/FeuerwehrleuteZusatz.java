package gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import main.Feuerwache;

public class FeuerwehrleuteZusatz extends JPanel{
	Feuerwache feuerwache;
	JButton buttonWartung;
    JButton buttonBack;
    JTextField kennzeichen;
    JLabel kennzeichenText;
    ButtonAktion buttonWartungAktion1;
    ButtonAktion buttonBackAktion2;
	
	public FeuerwehrleuteZusatz(JTextPane panelMain, Feuerwache feuerwache) {
		this.feuerwache = feuerwache;
		this.setPreferredSize(new Dimension(700, 100));
		buttonWartung = new JButton("In Urlaub schicken");
		buttonBack = new JButton("Zurück aus urlaub");
		kennzeichen = new JTextField(12);
		kennzeichenText = new JLabel("#ID:", SwingConstants.CENTER);
		this.add(kennzeichenText);
		this.add(kennzeichen);
		this.add(buttonWartung);
		this.add(buttonBack);
		
	}

}
