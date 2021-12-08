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
	ButtonAktion btnAktion1;
	ButtonAktion btnAktion2;
	ButtonAktion btnAktion3;
	ButtonAktion btnAktion4;

	public EinsaetzeZusatz(Gui gui, JTextPane panelMain, Feuerwache feuerwache) {
		this.feuerwache = feuerwache;
		this.setPreferredSize(new Dimension(700, 120));
		buttonStart1 = new JButton("Einsatz Wohnungsbrand starten");
		buttonStart2 = new JButton("Einsatz Verkehrsunfall starten");
		buttonStart3 = new JButton("Einsatz Naturkatastrophe starten");
		buttonStart4 = new JButton("Einsatz Industrieunfall starten");
		if(gui.feuerwache.kannErzeugeVorschlag("Wohnungsbrand")) {
			buttonStart1.setEnabled(true);
		}else {
			buttonStart1.setEnabled(false);
		}
		if(gui.feuerwache.kannErzeugeVorschlag("Verkehrsunfall")) {
			buttonStart2.setEnabled(true);
		}else {
			buttonStart2.setEnabled(false);
		}
		if(gui.feuerwache.kannErzeugeVorschlag("Naturkatastrophe")) {
			buttonStart3.setEnabled(true);
		}else {
			buttonStart3.setEnabled(false);
		}
		if(gui.feuerwache.kannErzeugeVorschlag("Industrieunfall")) {
			buttonStart4.setEnabled(true);
		}else {
			buttonStart4.setEnabled(false);
		}
		
		btnAktion1 = new ButtonAktion(buttonStart1, gui);
		btnAktion2 = new ButtonAktion(buttonStart2, gui);
		btnAktion3 = new ButtonAktion(buttonStart3, gui);
		btnAktion4 = new ButtonAktion(buttonStart4, gui);
		buttonStart1.setPreferredSize(new Dimension(300, 30));
		buttonStart2.setPreferredSize(new Dimension(300, 30));
		buttonStart3.setPreferredSize(new Dimension(300, 30));
		buttonStart4.setPreferredSize(new Dimension(300, 30));
		this.add(buttonStart1);
		this.add(buttonStart2);
		this.add(buttonStart3);
		this.add(buttonStart4);

	}

}
