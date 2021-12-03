package gui;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import main.Feuerwache;
import util.FahrzeugStatus;
import util.MitarbeiterStatus;

public class MainView extends JScrollPane {
	Feuerwache feuerwache;

	public MainView(JTextPane panelMain, Feuerwache feuerwache) {
		this.feuerwache = feuerwache;
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    this.setPreferredSize(new Dimension(700, 420));
	    panelMain.setPreferredSize(new Dimension(700, 420));
		int fahrzeugeAbsolut = feuerwache.getFahrzeuge().size();
		int fahrzeugeVerfuegbar = feuerwache.getAbsFahrzeugStatus((new FahrzeugStatus[] { FahrzeugStatus.Bereit }));
		int fahrzeugeWartung = feuerwache.getAbsFahrzeugStatus((new FahrzeugStatus[] { FahrzeugStatus.Wartung }));
		int fahrzeugeEinsatz = feuerwache.getAbsFahrzeugStatus((new FahrzeugStatus[] { FahrzeugStatus.Einsatz }));
		int feuerwehrLeuteAbsolut = feuerwache.getMitarbeiter().size();
		int feuerwehrLeuteVerfuegbar = feuerwache
				.getAbsMitarbeiterStatus((new MitarbeiterStatus[] { MitarbeiterStatus.Bereit }));
		int feuerwehrLeuteWartung = feuerwache
				.getAbsMitarbeiterStatus((new MitarbeiterStatus[] { MitarbeiterStatus.Krank }));
		int feuerwehrLeuteEinsatz = feuerwache
				.getAbsMitarbeiterStatus((new MitarbeiterStatus[] { MitarbeiterStatus.Einsatz }));
		
		String content = """
				<html>
				<body>
				<center><h1 style="font-size: 25px;">Feuerwache Rödermark</h1></center><br><br>
				""";
				
				
		content +=		"""
				<center><table style="font-size: 12px; width: 80%;">
				<tr style="font-size: 15px; border-bottom: 1px solid black;"><th colspan="2">Fahrzeuge</th><th></th><th colspan="2">Feuerwehrleute</th></tr>
				""";
		content += "<tr><td>Verfügbar</td><td>" + fahrzeugeVerfuegbar + "</td><td></td><td>Verfügbar</td><td>" + feuerwehrLeuteVerfuegbar + "</td></tr>";
		content += "<tr><td>Im Einsatz</td><td>" + fahrzeugeEinsatz + "</td><td></td><td>Im Einsatz</td><td>" + feuerwehrLeuteEinsatz + "</td></tr>";
		
		content += "<tr><td>In Wartung</td><td>" + fahrzeugeWartung + "</td><td></td><td>Im Urlaub</td><td>" + feuerwehrLeuteWartung + "</td></tr>";
		
		content += "<tr><td>Gesamt</td><td>" + fahrzeugeAbsolut + "</td><td></td><td>Gesamt</td><td>" + feuerwehrLeuteAbsolut + "</td></tr>";
		
		
		

		content += "</table></center>";
		content += """
				<br><br>
				<center><h1 style="font-size: 20px;">Bereitschaft</h1></center>
				<hr>
				<table style="width: 100%">
				<tr style="font-size: 18px;"><td>Wohnungsbrand</td><td>Verkehrsunfall</td><td>Naturkatastrophe</td><td>Industrieunfall</td></tr>
				<tr style="font-size: 20px; text-align: center; padding: 3px;"><td style="color: green;">Bereit</td><td style="color: green;">Bereit</td><td style="color: green;">Bereit</td><td style="color: red;">Nicht Bereit</td></tr>
				</table>
				</body>
				</html>
						""";
		panelMain.setText(content);
		this.getViewport().add(panelMain);

	}

}