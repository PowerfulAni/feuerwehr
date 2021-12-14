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
import menschen.Feuerwehrmensch;
import util.FahrzeugStatus;
import util.MitarbeiterStatus;

public class EinsatzVorschlag extends JScrollPane {

	Feuerwache feuerwache;
	int einsatzleiterfahrzeug;
	int leiterwagen;
	int transporter;
	int tankloesch;
	int leutepkw;
	int leutelkw;
	int addeinsatzleiterfahrzeug;
	int addleiterwagen;
	int addtransporter;
	int addtankloesch;
	int addleutepkw;
	int addleutelkw;
	int sitzplatze;
	ArrayList<Fahrzeug> einsatzleiterfahrzeugFrei = new ArrayList<>();
	ArrayList<Fahrzeug> leiterwagenFrei = new ArrayList<>();
	ArrayList<Fahrzeug> transporterFrei = new ArrayList<>();
	ArrayList<Fahrzeug> tankloeschFrei = new ArrayList<>();
	ArrayList<Feuerwehrmensch> leutepkwFrei = new ArrayList<>();
	ArrayList<Feuerwehrmensch> leutelkwFrei = new ArrayList<>();
	int anzahllkw;

	public EinsatzVorschlag(Gui gui, JTextPane panelMain, Feuerwache feuerwache, Einsatz vorschlag) {
		this.feuerwache = feuerwache;
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setPreferredSize(new Dimension(700, 420));
		ArrayList<Fahrzeug> fahrzeuge = vorschlag.getFahrzeuge();
		ArrayList<Feuerwehrmensch> leute = vorschlag.getMitarbeiter();
		ArrayList<Fahrzeug> fahrzeugeGes = feuerwache.getFahrzeuge();
		ArrayList<Feuerwehrmensch> leuteGes = feuerwache.getMitarbeiter();
		this.einsatzleiterfahrzeug = 0;
		this.leiterwagen = 0;
		this.transporter = 0;
		this.tankloesch = 0;
		this.leutepkw = 0;
		this.leutelkw = 0;
		this.addeinsatzleiterfahrzeug = 0;
		this.addleiterwagen = 0;
		this.addtransporter = 0;
		this.addtankloesch = 0;
		this.addleutepkw = 0;
		this.addleutelkw = 0;
		this.sitzplatze = 0;
		for (int i = 0; i < fahrzeuge.size(); i++) {

			switch (fahrzeuge.get(i).getFahrzeugName()) {
			case "Tank-Löschfahrzeug":
				tankloesch++;
				anzahllkw++;
				break;
			case "Mannschaftstransporter":
				transporter++;
				anzahllkw++;
				break;
			case "Leiterwagen":
				leiterwagen++;
				anzahllkw++;
				break;
			case "Einsatz-Leitfahrzeug":
				einsatzleiterfahrzeug++;
				break;
			default:
				break;
			}
		}
		
		for (int i = 0; i < fahrzeugeGes.size(); i++) {
			if ((fahrzeugeGes.get(i).getFahrzeugStatus().equals(FahrzeugStatus.Bereit)) && !fahrzeuge.contains(fahrzeugeGes.get(i))) {
				switch (fahrzeugeGes.get(i).getFahrzeugName()) {
				case "Tank-Löschfahrzeug":
					tankloeschFrei.add(fahrzeugeGes.get(i));
					break;
				case "Mannschaftstransporter":
					transporterFrei.add(fahrzeugeGes.get(i));
					break;
				case "Leiterwagen":
					leiterwagenFrei.add(fahrzeugeGes.get(i));
					break;
				case "Einsatz-Leitfahrzeug":
					einsatzleiterfahrzeugFrei.add(fahrzeugeGes.get(i));
					break;
				default:
					break;
				}
			}

		}

		for (int i = 0; i < leute.size(); i++) {

			switch (leute.get(i).getFahrerlaubnis()) {
			case PKW:
				leutepkw++;
				break;
			case LKW:
				leutelkw++;
				break;
			default:
				break;
			}
		}
		for (int i = 0; i < leuteGes.size(); i++) {
			if ((leuteGes.get(i).getMitarbeiterStatus().equals(MitarbeiterStatus.Bereit)) && !leute.contains(leuteGes.get(i))) {
				switch (leuteGes.get(i).getFahrerlaubnis()) {
				case PKW:
					leutepkwFrei.add(leuteGes.get(i));
					break;
				case LKW:
					leutelkwFrei.add(leuteGes.get(i));
					break;
				default:
					break;
				}
			}
			
		}
		
		berecheSitzplatze();
		panelMain.setPreferredSize(new Dimension(700, 300));
		String content = generateContent(vorschlag);
		panelMain.setText(content);
		this.getViewport().add(panelMain);
		HTMLEditorKit kit = (HTMLEditorKit) panelMain.getEditorKit();
		kit.setAutoFormSubmission(false);
		panelMain.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e instanceof FormSubmitEvent) {
					String event = (((FormSubmitEvent) e).getData().split("=")[0]);
					switch (event) {
					case "add_einsatzleiterfahrzeug":
						if (addeinsatzleiterfahrzeug < einsatzleiterfahrzeugFrei.size()) {
							einsatzleiterfahrzeug++;
							addeinsatzleiterfahrzeug++;
							berecheSitzplatze();
						}
						panelMain.setText(generateContent(vorschlag));
						break;
					case "sub_einsatzleiterfahrzeug":
						if (einsatzleiterfahrzeug > vorschlag.getEinsatzTyp().minEinsatzfahrzeug) {
							einsatzleiterfahrzeug--;
							addeinsatzleiterfahrzeug--;
							berecheSitzplatze();
						}
						panelMain.setText(generateContent(vorschlag));
						break;
					case "add_tankloesch":
						if (addtankloesch < tankloeschFrei.size() && leutelkw > anzahllkw) {
							tankloesch++;
							addtankloesch++;
							anzahllkw++;
							berecheSitzplatze();
						}
						panelMain.setText(generateContent(vorschlag));
						break;
					case "sub_tankloesch":
						if (tankloesch > vorschlag.getEinsatzTyp().minTankLoeschfahrzeug) {
							tankloesch--;
							addtankloesch--;
							anzahllkw--;
							berecheSitzplatze();
						}
						panelMain.setText(generateContent(vorschlag));
						break;
					case "add_leiterwagen":
						if (addleiterwagen < leiterwagenFrei.size() && leutelkw > anzahllkw) {
							leiterwagen++;
							addleiterwagen++;
							anzahllkw++;
							berecheSitzplatze();
						}
						panelMain.setText(generateContent(vorschlag));
						break;
					case "sub_leiterwagen":
						if (leiterwagen > vorschlag.getEinsatzTyp().minLeiterwagen) {
							leiterwagen--;
							addleiterwagen--;
							anzahllkw--;
							berecheSitzplatze();
						}
						panelMain.setText(generateContent(vorschlag));
						break;
					case "add_transporter":
						if (addtransporter < transporterFrei.size() && leutelkw > anzahllkw) {
							transporter++;
							addtransporter++;
							anzahllkw++;
							berecheSitzplatze();
						}
						panelMain.setText(generateContent(vorschlag));
						break;
					case "sub_transporter":
						if (transporter > vorschlag.getEinsatzTyp().minManschaftstransporter) {
							transporter--;
							addtransporter--;
							anzahllkw--;
							berecheSitzplatze();
						}
						panelMain.setText(generateContent(vorschlag));
						break;
					case "add_leutelkw":
						if (addleutelkw < (leutelkwFrei.size()) && (leutepkw+leutelkw < sitzplatze)) {
							leutelkw++;
							addleutelkw++;
						}
						panelMain.setText(generateContent(vorschlag));
						break;
					case "sub_leutelkw":
						if (addleutelkw > 0 && (leutepkw + leutelkw > vorschlag.getEinsatzTyp().minMitarbeiter)
								&& leutelkw > anzahllkw) {
							leutelkw--;
							addleutelkw--;
						}
						panelMain.setText(generateContent(vorschlag));
						break;
					case "add_leutepkw":
						if (addleutepkw < (leutepkwFrei.size()) && (leutepkw+leutelkw < sitzplatze)) {
							leutepkw++;
							addleutepkw++;
						}
						panelMain.setText(generateContent(vorschlag));
						break;
					case "sub_leutepkw":
						if (leutepkw > 0 && (leutepkw + leutelkw > vorschlag.getEinsatzTyp().minMitarbeiter)) {
							leutepkw--;
							addleutepkw--;
						}
						panelMain.setText(generateContent(vorschlag));
						break;
					case "starte_einsatz":
						int i = 0;
						while (addeinsatzleiterfahrzeug != 0) {
							fahrzeuge.add(einsatzleiterfahrzeugFrei.get(i));
							addeinsatzleiterfahrzeug--;
							i++;
						}
						i = 0;
						while (addleiterwagen != 0) {
							fahrzeuge.add(leiterwagenFrei.get(i));
							addleiterwagen--;
							i++;
						}
						
						i = 0;
						while (addtransporter != 0) {
							fahrzeuge.add(transporterFrei.get(i));
							addtransporter--;
							i++;
						}
						i = 0;
						while (addtankloesch != 0) {
							fahrzeuge.add(tankloeschFrei.get(i));
							addtankloesch--;
							i++;
						}
						i = 0;
						ArrayList<Feuerwehrmensch> temppkw = new ArrayList<>();
						while (addleutepkw != 0) {
							
							if (addleutepkw > 0) {
								leute.add(leutepkwFrei.get(i));
								addleutepkw--;
							} else {
								
								for (int z = 0; z < leute.size(); z++) {
									if(addleutepkw != 0) {
										switch (leute.get(z).getFahrerlaubnis()) {
										case PKW:
											temppkw.add(leute.get(z));
											addleutepkw++;
											break;
										default:
											break;
										}
									}
									
								}
							}

							i++;
						}
						for(Feuerwehrmensch pkwmensch : temppkw) {
							leute.remove(temppkw);
						}
						i = 0;
						while (addleutelkw != 0) {
							if (addleutelkw > 0) {
								leute.add(leutelkwFrei.get(i));
								addleutelkw--;
							}

							i++;
						}
						if(addeinsatzleiterfahrzeug == 0 && addleiterwagen == 0 && addtransporter == 0 && addtankloesch == 0 && addleutepkw == 0 && addleutelkw == 0) {
//							System.out.println("Einsatzleitwagen: "+einsatzleiterfahrzeug);
//							System.out.println("Tankwagen: "+tankloesch);
//							System.out.println("Transporter: "+transporter);
//							System.out.println("Leiterwagen: "+leiterwagen);
//							System.out.println("PKW Leute: "+leutepkw);
//							System.out.println("LKW Leute: "+leutelkw);
							feuerwache.startEinsatz(vorschlag.getEinsatzTyp(), fahrzeuge, leute);
							gui.refreshView();
							break;
						}else {
							break;
						}
					default:
						break;
					}
					

				}
			}

		});
	}

	private void berecheSitzplatze() {
		sitzplatze = 0;
		sitzplatze += (einsatzleiterfahrzeug*2);
		sitzplatze += (leiterwagen*2);
		sitzplatze += (transporter*14);
		sitzplatze += (tankloesch*4);
	}
	private String generateContent(Einsatz vorschlag) {
		String content = "<html>";
		content += "<body>";
		content += "<h1 style='font-size: 25px; text-align: center;'>Einsatzplanung</h1>";

		content += "<center><table style='font-size: 12px; margin: 10px; width: 400px;'>";
		content += "<tr><td style='width: 200px; padding: 4px;'><b>Einsatz-Leitfahrzeug</b></td><form action='#'><input type='submit' value='-' name='sub_einsatzleiterfahrzeug' "+((einsatzleiterfahrzeug > vorschlag.getEinsatzTyp().minEinsatzfahrzeug?"":"hidden"))+"></form><td><td style='font-size: 12px;'>"
				+ einsatzleiterfahrzeug
				+ "</td></td><form action='#'><input type='submit' value='+' name='add_einsatzleiterfahrzeug' "+(addeinsatzleiterfahrzeug < einsatzleiterfahrzeugFrei.size()?"":"hidden")+"></form><td>";
		content += "<tr><td style='width: 200px; padding: 4px;'><b>Tank-Löschfahrzeug</b></td><form action='#'><input type='submit' value='-' name='sub_tankloesch' "+((tankloesch > vorschlag.getEinsatzTyp().minTankLoeschfahrzeug)?"":"hidden")+"></form><td><td style='font-size: 12px;'>"
				+ tankloesch
				+ "</td></td><form action='#'><input type='submit' value='+' name='add_tankloesch' "+((addtankloesch < tankloeschFrei.size() && leutelkw > anzahllkw)?"":"hidden")+"></form><td>";
		content += "<tr><td style='width: 200px; padding: 4px;'><b>Leiterwagen</b></td><form action='#'><input type='submit' value='-' name='sub_leiterwagen' "+((leiterwagen > vorschlag.getEinsatzTyp().minLeiterwagen)?"":"hidden")+"></form><td><td style='font-size: 12px;'>"
				+ leiterwagen
				+ "</td></td><form action='#'><input type='submit' value='+' name='add_leiterwagen' "+((addleiterwagen < leiterwagenFrei.size() && leutelkw > anzahllkw)?"":"hidden")+"></form><td>";
		content += "<tr><td style='width: 200px; padding: 4px;'><b>Mannschaftstransporter</b></td><form action='#'><input type='submit' value='-' name='sub_transporter' "+((transporter > vorschlag.getEinsatzTyp().minManschaftstransporter)?"":"hidden")+"></form><td><td style='font-size: 12px;'>"
				+ transporter
				+ "</td></td><form action='#'><input type='submit' value='+' name='add_transporter' "+((addtransporter < transporterFrei.size() && leutelkw > anzahllkw)?"":"hidden")+"></form><td>";
		content += "<tr><td style='width: 200px; padding: 4px;'><b>Feuerwehrleute (LKW)</b></td><form action='#'><input type='submit' value='-' name='sub_leutelkw' "+((addleutelkw > 0 && (leutepkw + leutelkw > vorschlag.getEinsatzTyp().minMitarbeiter)?"":"hidden")+"></form><td><td style='font-size: 12px;'>"
				+ leutelkw
				+ "</td></td><form action='#'><input type='submit' value='+' name='add_leutelkw' "+((addleutelkw < (leutelkwFrei.size()) && (leutepkw+leutelkw < sitzplatze))?"":"hidden"))+"></form><td>";
		content += "<tr><td style='width: 200px; padding: 4px;'><b>Feuerwehrleute (PKW)</b></td><form action='#'><input type='submit' value='-' name='sub_leutepkw' "+((leutepkw > 0 && (leutepkw + leutelkw > vorschlag.getEinsatzTyp().minMitarbeiter))?"":"hidden")+"'></form><td><td style='font-size: 12px;'>"
				+ leutepkw
				+ "</td></td><form action='#'><input type='submit' value='+' name='add_leutepkw' "+((addleutepkw < (leutepkwFrei.size()) && (leutepkw+leutelkw < sitzplatze))?"":"hidden")+"></form><td>";
		content += "<tr><td style='width: 200px; padding: 4ƒƒpx;'></td><td colspan='2'>Sitzplätze</td><td>"+sitzplatze+"</td></tr>";
		content += "<tr><td colspan='5'>* Achtung: Es lassen sich nur realisierbare Kombinationen einstellen.</td></tr>";
		content += "</table>";

		content += "<form action='#'><input type='submit' name='starte_einsatz' value='Einsatz starten'></form>";
		content += "</center></body>";
		content += "</html>";
		return content;
	}
}
