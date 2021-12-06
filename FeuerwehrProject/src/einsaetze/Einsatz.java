package einsaetze;

import java.util.ArrayList;

import fahrzeuge.Fahrzeug;
import menschen.Feuerwehrmensch;
import util.FahrzeugStatus;
import util.MitarbeiterStatus;

public class Einsatz {
	private EinsatzTyp einsatzTyp;
	private ArrayList<Fahrzeug> fahrzeuge;
	private ArrayList<Feuerwehrmensch> mitarbeiter;
	private int einsatzID;
	
	/**
	 * Erstellt einen neuen Einsatz.
	 * @param typ Der Einsatz der ausgeführt werden soll.
	 * @param fahrzeuge Die Fahrzeuge die verwendet werden sollen.
	 * @param mitarbeiter Die Mitarbeiter die zugeteilt wurden.
	 * @param istVorschlag auf true setzen um den Einsatz nicht zu Starten.
	 */
	public Einsatz(EinsatzTyp typ, ArrayList<Fahrzeug> fahrzeuge, ArrayList<Feuerwehrmensch> mitarbeiter, boolean istVorschlag) {
		einsatzTyp = typ;
		this.fahrzeuge = fahrzeuge;
		this.mitarbeiter = mitarbeiter;
		if(!istVorschlag)
			starteEinsatz();
	}
	public Einsatz(EinsatzTyp typ, ArrayList<Fahrzeug> fahrzeuge, ArrayList<Feuerwehrmensch> mitarbeiter, boolean istVorschlag, int einsatzID) {
		einsatzTyp = typ;
		this.fahrzeuge = fahrzeuge;
		this.mitarbeiter = mitarbeiter;
		this.einsatzID=einsatzID;
		if(!istVorschlag)
			starteEinsatz();
	}
	
	private void starteEinsatz() {
		for (Fahrzeug fahrzeug : fahrzeuge) {
			fahrzeug.setFahrzeugStatus(FahrzeugStatus.Einsatz);
		}
		for (Feuerwehrmensch mitarbeiter : mitarbeiter) {
			mitarbeiter.setMitarbeiterStatus(MitarbeiterStatus.Einsatz);
		}
	}
	
	/**
	 * Beendet den Laufenden Einsatz und macht Mitarbeiter / Fahrzeuge wieder verfügbar.
	 */
	public void beendeEinsatz() {
		for (Fahrzeug fahrzeug : fahrzeuge) {
			fahrzeug.setFahrzeugStatus(FahrzeugStatus.Bereit);
		}
		for (Feuerwehrmensch mitarbeiter : mitarbeiter) {
			mitarbeiter.setMitarbeiterStatus(MitarbeiterStatus.Bereit);
		}
		einsatzTyp = null;
		fahrzeuge = null;
		mitarbeiter = null;
	}
	
	/**
	 * Zum Anzeigen im Gui.
	 * @return EinsatzTyp
	 */
	public EinsatzTyp getEinsatzTyp() {
		return einsatzTyp;
	}
	
	/**
	 * Zum Anzeigen im Gui.
	 * @return ArrayList Fahrzeug
	 */
	public ArrayList<Fahrzeug> getFahrzeuge(){
		return fahrzeuge;
	}
	
	/**
	 * Zum Anzeigen im Gui.
	 * @return ArrayList Feuerwehrmensch
	 */
	public ArrayList<Feuerwehrmensch> getMitarbeiter(){
		return mitarbeiter;
	}
	public int getID() {
		return einsatzID;
	}
}
