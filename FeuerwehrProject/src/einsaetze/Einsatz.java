package einsaetze;

import java.util.ArrayList;

import fahrzeuge.Fahrzeug;
import menschen.Feuerwehrmensch;
import util.FahrzeugStatus;
import util.MitarbeiterStatus;

/**
 * Um Einsätze zu speichern
 */
public class Einsatz {
	private EinsatzTyp einsatzTyp;
	private ArrayList<Fahrzeug> fahrzeuge;
	private ArrayList<Feuerwehrmensch> mitarbeiter;
	private int einsatzID;
	
	/**
	 * Erstellt einen neuen Einsatz.
	 * @param typ Der Einsatz, der ausgeführt werden soll.
	 * @param fahrzeuge Die Fahrzeuge, die verwendet werden sollen.
	 * @param mitarbeiter Die Mitarbeiter, die zugeteilt wurden.
	 * @param istVorschlag auf true setzen, um den Einsatz nicht zu Starten.
	 */
	public Einsatz(EinsatzTyp typ, ArrayList<Fahrzeug> fahrzeuge, ArrayList<Feuerwehrmensch> mitarbeiter, boolean istVorschlag) {
		einsatzTyp = typ;
		this.fahrzeuge = fahrzeuge;
		this.mitarbeiter = mitarbeiter;
		if(!istVorschlag)
			starteEinsatz();
	}
	/**
	 * Erstellt einen neuen Einsatz.
	 * @param typ Der Einsatz, der ausgeführt werden soll.
	 * @param fahrzeuge Die Fahrzeuge, die verwendet werden sollen.
	 * @param mitarbeiter Die Mitarbeiter, die zugeteilt wurden.
	 * @param istVorschlag auf true setzen, um den Einsatz nicht zu Starten.
	 * @param einsatzID um den Einsatz bei der Init zuzuweisen.
	 */
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
	 * Beendet den Laufenden Einsatz und macht Mitarbeiter und Fahrzeuge wieder verfügbar.
	 */
	public void einsatzBeenden() {
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
	 * @return Der EinsatzTyp dieses Einsatzes
	 */
	public EinsatzTyp getEinsatzTyp() {
		return einsatzTyp;
	}
	
	/**
	 * Zum Anzeigen im Gui.
	 * @return ArrayList alle Fahrzeug
	 */
	public ArrayList<Fahrzeug> getFahrzeuge(){
		return fahrzeuge;
	}
	/**
	 * Zum Anzeigen im Gui.
	 * @return ArrayList alle Mitarbeiter
	 */
	public ArrayList<Feuerwehrmensch> getMitarbeiter(){
		return mitarbeiter;
	}
	/**
	 * Bekomme Die Einsatz Id
	 * @return Einsatz Id
	 */
	public int getID() {
		return einsatzID;
	}
}
