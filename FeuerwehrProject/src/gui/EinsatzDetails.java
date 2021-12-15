package gui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.FormSubmitEvent;
import javax.swing.text.html.HTMLEditorKit;

import einsaetze.Einsatz;
import einsaetze.EinsatzTyp;
import fahrzeuge.Fahrzeug;
import main.Feuerwache;
import menschen.Feuerwehrmensch;
import util.FahrzeugStatus;

public class EinsatzDetails extends JScrollPane {
	Feuerwache feuerwache;

	public EinsatzDetails(Gui gui, JTextPane panelMain, Feuerwache feuerwache, int id) {

		this.feuerwache = feuerwache;
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setPreferredSize(new Dimension(700, 420));

		panelMain.setPreferredSize(new Dimension(700, 420));

		Einsatz einsatz = feuerwache.getEinsaetze().get(id);
		EinsatzTyp typ = einsatz.getEinsatzTyp();
		
		ArrayList<Fahrzeug> fahrzeuge = einsatz.getFahrzeuge();
		ArrayList<Feuerwehrmensch> mitarbeiter = einsatz.getMitarbeiter();

		String content = "<html>";
		content += "<body>";
		content += "<h1 style='font-size: 25px; text-align: center;'>Einsatzdetails - " + typ.getName() + "</h1><hr>";
		content += "<h3 style='font-size: 15px; text-align: center;'>Fahrzeuge</h3>";

		content += "<center><table style='font-size: 12px; margin: 10px; width: 90%;'>";
		
		for (int i = 0; i < fahrzeuge.size(); i++) {
			content += "<tr><td style='text-align: center;'><div style='border: 1px solid black; padding: 5px; margin: 5px;'>"
					+ fahrzeuge.get(i).getKennzeichen() + "</div></td><td><b>" + fahrzeuge.get(i).getFahrzeugName()
					+ "</b> (" + fahrzeuge.get(i).getSitzplaetze() + " Sitzplätze)<hr>"
					+ fahrzeuge.get(i).getFahrzeugTyp() + " | " + fahrzeuge.get(i).getSpezial() + "</td>";

		}
		
		content += "</table></center><hr>";
		content += "<h3 style='font-size: 15px; text-align: center;'>Feuerwehrleute</h3>";
		content += "<center><table style='font-size: 12px; margin: 10px; width: 60%;'>";
		content += "<tr><th style='text-align: left;'>#</th><th style='text-align: left;'>Name</th><th style='text-align: left;'>Fahrerlaubnis</th></tr>";
		
		for (int i = 0; i < mitarbeiter.size(); i++) {
			content += "<tr><td style='text-align: left;'>" + (i + 1) + "</td><td style='text-align: left;'>"
					+ mitarbeiter.get(i).getName() + "</td><td style='text-align: left;'>"
					+ mitarbeiter.get(i).getFahrerlaubnis() + "</td></tr>";

		}
		
		content += "</table></center>";
		content += "</body></html>";
		panelMain.setText(content);
		panelMain.setCaretPosition(0);
		this.getViewport().add(panelMain);

	}
}
