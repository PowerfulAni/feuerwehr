package gui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import main.Feuerwache;
import menschen.Feuerwehrmensch;

public class FeuerwehrleuteView extends JScrollPane {
	Feuerwache feuerwache;

	public FeuerwehrleuteView(JTextPane panelMain, Feuerwache feuerwache) {
		this.feuerwache = feuerwache;
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setPreferredSize(new Dimension(700, 420));
		ArrayList feuerwehrleute = feuerwache.getMitarbeiter();
		String content = "<html><body><center><table style='font-size: 12px; margin: 10px; width: 90%;'><tr style='font-size: 15px; padding: 5px; margin: 5px;'><tr><th>#ID</th><th style='text-align: left;'>Name</th><th>Fahrerlaubnis</th><th>Status</th></tr>";
		for (int i = 0; i < feuerwehrleute.size(); i++) {
			content += "<tr style='text-align: center; border-bottom: 1px dotted black;'><td>"+((int)i+1)+"</td><td style='text-align: left;'>"+((Feuerwehrmensch) feuerwehrleute.get(i)).getName()+"</td><td>"+((Feuerwehrmensch) feuerwehrleute.get(i)).getFahrerlaubnis()+"</td><td>"+((Feuerwehrmensch) feuerwehrleute.get(i)).getMitarbeiterStatus()+"</td></tr>";
			//panelMain.add(new JLabel("Test"));
		}
		content += "</table></center></body></html>";
		panelMain.setText(content);
		panelMain.setCaretPosition(0);
		this.getViewport().add(panelMain);

	}

}
