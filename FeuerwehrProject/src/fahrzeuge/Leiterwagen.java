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
	 * @param id des fahrzeuges
	 * @param sitzplaetze Die zuverfügung stehen
	 * @param fahrzeugTyp ob LKW oder PKW
	 * @param fahrzeugstatus ob zuzeit verfügbar
	 * @param kennzeichen als erkennungs merkmal
	 * @param maxLeiter maximale Leiterlänge
	 */
	public Leiterwagen(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, int maxLeiter) {
		super(id, sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen);
		this.maxLeiter = maxLeiter;
	}
	/**
	 * Ertselle ein neues Fahrzeug
	 * @param id des fahrzeuges
	 * @param sitzplaetze Die zuverfügung stehen
	 * @param fahrzeugTyp ob LKW oder PKW
	 * @param fahrzeugstatus ob zuzeit verfügbar
	 * @param kennzeichen als erkennungs merkmal
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
