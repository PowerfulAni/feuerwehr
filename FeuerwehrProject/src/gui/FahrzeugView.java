package gui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import main.Feuerwache;
import util.FahrzeugStatus;
import util.MitarbeiterStatus;
import fahrzeuge.Fahrzeug;
import main.Feuerwache;

public class FahrzeugView extends JScrollPane {
	Feuerwache feuerwache;

	public FahrzeugView(JTextPane panelMain, Feuerwache feuerwache) {
		this.feuerwache = feuerwache;
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setPreferredSize(new Dimension(700, 420));
		ArrayList fahrzeuge = feuerwache.getFahrzeuge();
		String content = "<html><center><table style='font-size: 12px; margin: 10px; width: 90%;'><tr style='font-size: 15px; padding: 5px; margin: 5px;'><th>Kennzeichen</th><th>Fahrzeugtyp</th><th>Sitzplätze</th><th>Status</th></tr>";
		for (int i = 0; i < fahrzeuge.size(); i++) {
			content += "<tr style='border-bottom: 1px dotted black; text-align: center;'><td style='border: 1px solid black'>" + ((Fahrzeug) fahrzeuge.get(i)).getKennzeichenString() + "</td><td>"
					+ ((Fahrzeug) fahrzeuge.get(i)).getFahrzeugTyp() + "</td><td>"
					+ ((Fahrzeug) fahrzeuge.get(i)).getSitzplaetze() + "</td><td>"
					+ ((Fahrzeug) fahrzeuge.get(i)).getFahrzeugStatus() + "</td><td>"
							+ "</td></tr>";

		}
		content += "</table></center></html>";

		panelMain.setText(content);
		panelMain.setCaretPosition(0);
		this.getViewport().add(panelMain);

	}

}
