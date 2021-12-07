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
import menschen.Feuerwehrmensch;
import util.MitarbeiterStatus;

public class FeuerwehrleuteView extends JScrollPane {
	Feuerwache feuerwache;

	public FeuerwehrleuteView(JTextPane panelMain, Feuerwache feuerwache) {
		this.feuerwache = feuerwache;
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setPreferredSize(new Dimension(700, 420));
		ArrayList<Feuerwehrmensch> feuerwehrleute = feuerwache.getMitarbeiter();
		String content = "<html><body><center><table style='font-size: 12px; margin: 10px; width: 90%;'><tr style='font-size: 15px; padding: 5px; margin: 5px;'><tr><th>#ID</th><th style='text-align: left;'>Name</th><th>Fahrerlaubnis</th><th>Status</th></tr>";
		for (int i = 0; i < feuerwehrleute.size(); i++) {
			content += "<tr style='text-align: center; border-bottom: 1px dotted black;'><td>" + ((int) i + 1)
					+ "</td><td style='text-align: left;'>" + ((Feuerwehrmensch) feuerwehrleute.get(i)).getName()
					+ "</td><td>" + ((Feuerwehrmensch) feuerwehrleute.get(i)).getFahrerlaubnis()
					+ "</td><td><form action='#'><select name='status'>";
			content += "<option value='bereit_" + i + "' "
					+ (feuerwehrleute.get(i).getMitarbeiterStatus().equals(MitarbeiterStatus.Bereit) ? "selected" : "")
					+ ">Bereit</option>";
			content += "<option value='einsatz_" + i + "' "
					+ (feuerwehrleute.get(i).getMitarbeiterStatus().equals(MitarbeiterStatus.Einsatz) ? "selected" : "")
					+ ">Einsatz</option>";
			content += "<option value='urlaub_" + i + "' "
					+ (feuerwehrleute.get(i).getMitarbeiterStatus().equals(MitarbeiterStatus.Urlaub) ? "selected" : "")
					+ ">Urlaub</option>";
			content += "<option value='krank_" + i + "' "
					+ (feuerwehrleute.get(i).getMitarbeiterStatus().equals(MitarbeiterStatus.Krank) ? "selected" : "")
					+ ">Krank</option></select><input type='submit' value='Speichern'/></form></td></tr>";
			// panelMain.add(new JLabel("Test"));
		}
		content += "</table></center></body></html>";
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
					feuerwehrleute.get(Integer.parseInt(splits[1])).setMitarbeiterStatus(switch (splits[0]) {
					case "bereit" -> MitarbeiterStatus.Bereit;
					case "einsatz" -> MitarbeiterStatus.Einsatz;
					case "urlaub" -> MitarbeiterStatus.Urlaub;
					case "krank" -> MitarbeiterStatus.Krank;
					default -> MitarbeiterStatus.Bereit;
					});
				}
			}
		});
	}

}
