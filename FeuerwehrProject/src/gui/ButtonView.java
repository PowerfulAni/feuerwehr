package gui;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Diese Klasse erstellt den Button View
 *
 *
 */
public class ButtonView extends JPanel {

	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	ButtonAktion buttonAktion1;
	ButtonAktion buttonAktion2;
	ButtonAktion buttonAktion3;
	ButtonAktion buttonAktion4;

	public ButtonView(Gui gui) {

		// Panel erstellen und einstellen
		this.setPreferredSize(new Dimension(700, 40));

		// Buttons erstellen
		button1 = new JButton("Übersicht");
		button2 = new JButton("Fahrzeuge");
		button3 = new JButton("Feuerwehrleute");
		button4 = new JButton("Einsätze");

		// Button funktionen hinzufügen

		buttonAktion1 = new ButtonAktion(button1, gui);
		buttonAktion2 = new ButtonAktion(button2, gui);
		buttonAktion3 = new ButtonAktion(button3, gui);
		buttonAktion4 = new ButtonAktion(button4, gui);

		// Buttons werden dem JPanel hinzugefügt
		this.add(button1);
		this.add(button2);
		this.add(button3);
		this.add(button4);
	}
}
