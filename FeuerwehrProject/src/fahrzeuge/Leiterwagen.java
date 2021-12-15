package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

/**
 * Konzept vom Leiterwagen
 */
public class Leiterwagen extends Fahrzeug {
	
	private int maxLeiter;
	
	/**
	 * Ertselle ein neues Fahrzeug
	 * @param id des Fahrzeuges
	 * @param sitzplaetze Die zur Verfügung stehen
	 * @param fahrzeugTyp ob LKW oder PKW
	 * @param fahrzeugstatus ob zurzeit verfügbar
	 * @param kennzeichen als Erkennungsmerkmal
	 * @param maxLeiter maximale Leiterlänge
	 */
	public Leiterwagen(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, int maxLeiter) {
		super(id, sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen);
		this.maxLeiter = maxLeiter;
	}
	/**
	 * Erstelle ein neues Fahrzeug
	 * @param id des Fahrzeuges
	 * @param sitzplaetze Die zur Verfügung stehen
	 * @param fahrzeugTyp ob LKW oder PKW
	 * @param fahrzeugstatus ob zurzeit verfügbar
	 * @param kennzeichen als Erkennungsmerkmal
	 * @param maxLeiter maximale Leiterlänge
	 * @param einsatzID um bei der Init dies zuzuweisen
	 */
	public Leiterwagen(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, int maxLeiter, int einsatzID) {
		super(id, sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen, einsatzID);
		this.maxLeiter = maxLeiter;
	}
	/**
	 * Bekomme die maximale Leiterlänge
	 * @return max Leiter länge
	 */
	public int getMaxLeiter () {
		return this.maxLeiter;
	}
	@Override
	public String getSpezial() {
		return "Maximale Leiterhöhe: "+maxLeiter;
	}
	
	@Override
	public String getFahrzeugName() {
		return "Leiterwagen";
	}
}
