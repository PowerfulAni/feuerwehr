package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
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
		}
	}
}
