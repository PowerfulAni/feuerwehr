package gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import main.Feuerwache;

public class EinsaetzeZusatz extends JPanel {
	Feuerwache feuerwache;
	JButton buttonStart1;
	JButton buttonStart2;
	JButton buttonStart3;
	JButton buttonStart4;
	ButtonAktion buttonWartungAktion1;
	ButtonAktion buttonBackAktion2;

	public EinsaetzeZusatz(JTextPane panelMain, Feuerwache feuerwache) {
		this.feuerwache = feuerwache;
		this.setPreferredSize(new Dimension(700, 300));
		buttonStart1 = new JButton("Einsatz Wohnungsbrand starten");
		buttonStart2 = new JButton("Einsatz Verkehrsunfall starten");
		buttonStart3 = new JButton("Einsatz Naturkatastrophe starten");
		buttonStart4 = new JButton("Einsatz Industrieunfall starten");
		buttonStart1.setPreferredSize(new Dimension(700, 60));
		buttonStart2.setPreferredSize(new Dimension(700, 60));
		buttonStart3.setPreferredSize(new Dimension(700, 60));
		buttonStart4.setPreferredSize(new Dimension(700, 60));
		this.add(buttonStart1);
		this.add(buttonStart2);
		this.add(buttonStart3);
		this.add(buttonStart4);

	}

}
