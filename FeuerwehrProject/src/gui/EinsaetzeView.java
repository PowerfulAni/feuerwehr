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
import fahrzeuge.Fahrzeug;
import main.Feuerwache;
import util.FahrzeugStatus;

public class EinsaetzeView extends JScrollPane {
	Feuerwache feuerwache;

	public EinsaetzeView(Gui gui, JTextPane panelMain, Feuerwache feuerwache) {
		this.feuerwache = feuerwache;
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setPreferredSize(new Dimension(700, 300));
		panelMain.setPreferredSize(new Dimension(700, 300));
		ArrayList<Einsatz> einsaetze = feuerwache.getEinsaetze();
		String content = "<html>";
		content += "<body>";
		content += "<h1 style='font-size: 25px; text-align: center;'>laufende Einsätze</h1>";

		content += "<center><table style='font-size: 12px; margin: 10px; width: 90%;'>";
		content += "<tr style='font-size: 15px; padding: 5px; margin: 5px;'><th>Einsatztyp</th><th>Fahrzeuge</th><th>Feuerwehrleute</th><th colspan='2'></th></tr>";
		for (int i = 0; i < einsaetze.size(); i++) {
			content += "<tr style='text-align: center; border-bottom: 1px dotted black;'><td>"
					+ einsaetze.get(i).getEinsatzTyp().getName() + "</td><td>" + einsaetze.get(i).getFahrzeuge().size()
					+ "</td><td>" + einsaetze.get(i).getMitarbeiter().size()
					+ "</td><td><form action='#'><input type='hidden' name='1' value='einsatz_" + i
					+ "'<input type='submit' value='Details'/></form></td><td><form action='#'><input type='hidden' name='2' value='einsatz_" + i
					+ "'<input type='submit' value='Beenden'/></form></td>";

		}
		content += "</table></center></body>";
		content += "</html>";
		panelMain.setText(content);
		this.getViewport().add(panelMain);
		HTMLEditorKit kit = (HTMLEditorKit) panelMain.getEditorKit();
		kit.setAutoFormSubmission(false);
		panelMain.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e instanceof FormSubmitEvent) {
					int event = Integer.parseInt(((FormSubmitEvent) e).getData().split("=")[0]);
					String[] splits1 = ((FormSubmitEvent) e).getData().split("=")[1].split("_");
					System.out.println(event);
					if(event == 1) {
						gui.showEinsatzdetails(Integer.parseInt(splits1[1]));
					}else {
						feuerwache.beendeEinsatz(Integer.parseInt(splits1[1]));
						gui.refreshView();
					}
					
				}
			}
		});
	}

}
