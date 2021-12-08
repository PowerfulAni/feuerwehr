package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import einsaetze.Einsatz;
public class ButtonAktion extends JFrame implements ActionListener {

	JButton button;
	Gui gui;

	public ButtonAktion(JButton button, Gui gui) {
		this.gui = gui;
		this.button = button;
		button.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		String buttonText = this.button.getText();
		if (buttonText == "Übersicht") {
			gui.showMain();
		} else if (buttonText == "Fahrzeuge") {
			gui.showFahrzeuge();
		} else if (buttonText == "Feuerwehrleute") {
			gui.showFeuerwehrleute();
		} else if (buttonText == "Einsätze") {
			gui.showEinsaetze();
		} else if (buttonText == "Einsatz Wohnungsbrand starten") {
			if(gui.feuerwache.kannErzeugeVorschlag("Wohnungsbrand")) {
				Einsatz einsatz = gui.feuerwache.getVorschlag();
				gui.feuerwache.addEinsatz(einsatz);
				einsatz.starteEinsatz();
				gui.refreshView();
			}
		} else if (buttonText == "Einsatz Verkehrsunfall starten") {
			if(gui.feuerwache.kannErzeugeVorschlag("Verkehrsunfall")) {
				Einsatz einsatz = gui.feuerwache.getVorschlag();
				gui.feuerwache.addEinsatz(einsatz);
				einsatz.starteEinsatz();
				gui.refreshView();
			}
		} else if (buttonText == "Einsatz Naturkatastrophe starten") {
			if(gui.feuerwache.kannErzeugeVorschlag("Naturkatastrophe")) {
				Einsatz einsatz = gui.feuerwache.getVorschlag();
				gui.feuerwache.addEinsatz(einsatz);
				einsatz.starteEinsatz();
				gui.refreshView();
			}
		} else if (buttonText == "Einsatz Industrieunfall starten") {
			if(gui.feuerwache.kannErzeugeVorschlag("Industrieunfall")) {
				Einsatz einsatz = gui.feuerwache.getVorschlag();
				gui.feuerwache.addEinsatz(einsatz);
				einsatz.starteEinsatz();
				gui.refreshView();
			}
		}
		
	}
}
