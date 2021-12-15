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
		switch (buttonText) {
		case "Übersicht":
			gui.showMain();
			break;
		case "Fahrzeuge":
			gui.showFahrzeuge();
			break;
		case "Feuerwehrleute":
			gui.showFeuerwehrleute();
			break;
		case "Einsätze":
			gui.showEinsaetze();
			break;
		case "Einsatz Wohnungsbrand starten":
		case "Einsatz Verkehrsunfall starten":
		case "Einsatz Naturkatastrophe starten":
		case "Einsatz Industrieunfall starten":
			if (gui.feuerwache.kannErzeugeVorschlag(buttonText.split(" ")[1])) {
				Einsatz vorschlag = gui.feuerwache.getVorschlag();
				gui.showVorschlag(vorschlag);
				// gui.feuerwache.startEinsatz(einsatz.getEinsatzTyp(), einsatz.getFahrzeuge(),
				// einsatz.getMitarbeiter());
				// gui.refreshView();
			}
			break;

		default:
			break;
		}
	}
}
