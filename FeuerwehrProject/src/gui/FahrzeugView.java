package gui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.FormSubmitEvent;
import javax.swing.text.html.HTMLEditorKit;

import main.Feuerwache;
import util.FahrzeugStatus;
import util.MitarbeiterStatus;
import fahrzeuge.Fahrzeug;
import main.Feuerwache;

public class FahrzeugView extends JScrollPane {
	Feuerwache feuerwache;

	public FahrzeugView(JTextPane panelMain, Feuerwache feuerwache) {
		this.feuerwache = feuerwache;
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setPreferredSize(new Dimension(700, 420));
		ArrayList<Fahrzeug> fahrzeuge = feuerwache.getFahrzeuge();
		String content = "<html><center><table style='font-size: 12px; margin: 10px; width: 90%;'><tr style='font-size: 15px; padding: 5px; margin: 5px;'><th>Kennzeichen</th><th>Fahrzeugdaten</th><th>Status</th></tr>";
		for (int i = 0; i < fahrzeuge.size(); i++) {
			content += "<tr style='border-bottom: 1px dotted black; text-align: center;'><td style='border: 1px solid black'>" + fahrzeuge.get(i).getKennzeichen() + "</td><td>"
					+ fahrzeuge.get(i).getFahrzeugName() + "<hr>" + fahrzeuge.get(i).getFahrzeugTyp() + " | Sitzplätze: "
					+ fahrzeuge.get(i).getSitzplaetze() + "</td>";
			if(fahrzeuge.get(i).getFahrzeugStatus().equals(FahrzeugStatus.Einsatz)) {
				content += "<td><b>Im Einsatz</b></td>";
			}else {
				content += "<td><form action='#'><select name='status'>";
				content += "<option value='bereit_" + i + "' "
						+ (fahrzeuge.get(i).getFahrzeugStatus().equals(FahrzeugStatus.Bereit) ? "selected" : "")
						+ ">Bereit</option>";
				
				content += "<option value='wartung_" + i + "' "
						+ (fahrzeuge.get(i).getFahrzeugStatus().equals(FahrzeugStatus.Wartung) ? "selected" : "")
						+ ">In Wartung</option></select><input type='submit' value='Speichern'/></form></td>";
			}

		}
		content += "</tr></table></center></html>";

		panelMain.setText(content);
		panelMain.setCaretPosition(0);
		this.getViewport().add(panelMain);
		HTMLEditorKit kit = (HTMLEditorKit) panelMain.getEditorKit();
		kit.setAutoFormSubmission(false);
		panelMain.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e instanceof FormSubmitEvent) {
					String[] splits = ((FormSubmitEvent) e).getData().split("=")[1].split("_");
					System.out.println(splits[1]);
					System.out.println(splits[0]);
					fahrzeuge.get(Integer.parseInt(splits[1])).setFahrzeugStatus(switch (splits[0]) {
					case "bereit" -> FahrzeugStatus.Bereit;
					case "einsatz" -> FahrzeugStatus.Einsatz;
					case "wartung" -> FahrzeugStatus.Wartung;
					default -> FahrzeugStatus.Bereit;
					});
				}
			}
		});

	}

}
