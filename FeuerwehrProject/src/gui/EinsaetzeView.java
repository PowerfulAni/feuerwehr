package gui;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import main.Feuerwache;

public class EinsaetzeView extends JScrollPane {
	Feuerwache feuerwache;

	public EinsaetzeView(JTextPane panelMain, Feuerwache feuerwache) {
		this.feuerwache = feuerwache;
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setPreferredSize(new Dimension(700, 300));
		
		panelMain.setPreferredSize(new Dimension(700, 300));

		String content = "<html>";
		content += "<body>";
		content += "<h1 style='font-size: 25px; text-align: center;'>Einsätze</h1>";

		content += "<center><table>";
		content += "<tr style='font-size: 15px;'><th colspan='2'>laufende Einsätze</th></tr>";
		content += "</table></center></body>";
		content += "</html>";
		panelMain.setText(content);
		this.getViewport().add(panelMain);

	}

}
