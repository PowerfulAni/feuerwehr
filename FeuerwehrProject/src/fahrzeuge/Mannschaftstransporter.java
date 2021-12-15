package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

/**
 * Konzept vom Mannschaftstransporter
 */
public class Mannschaftstransporter extends Fahrzeug {

	private int baujahr;
	
	/**
	 * Ertselle ein neues Fahrzeug
	 * @param id des Fahrzeuges
	 * @param sitzplaetze Die zur Verfügung stehen
	 * @param fahrzeugTyp ob LKW oder PKW
	 * @param fahrzeugstatus ob zurzeit verfügbar
	 * @param kennzeichen als Erkennungsmerkmal
	 * @param baujahr des Fahrzeugs
	 */
	public Mannschaftstransporter(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus,String kennzeichen, int baujahr) {
		super(id,sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen);
		this.baujahr = baujahr;
	}
	/**
	 * Ertselle ein neues Fahrzeug
	 * @param id des Fahrzeuges
	 * @param sitzplaetze Die zur Verfügung stehen
	 * @param fahrzeugTyp ob LKW oder PKW
	 * @param fahrzeugstatus ob zurzeit verfügbar
	 * @param kennzeichen als Erkennungsmerkmal
	 * @param baujahr des Fahrzeugs
	 * @param einsatzID um bei der Init dies zuzuweisen
	 */
	public Mannschaftstransporter(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus,String kennzeichen, int baujahr, int einsatzID) {
		super(id,sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen, einsatzID);
		this.baujahr = baujahr;
	}
	/**
	 * Bekomme das Baujahr
	 * @return Baujahr
	 */
	public int getBaujahr () {
		return this.baujahr;
	}
	@Override
	public String getSpezial() {
		return "Baujahr: "+this.baujahr;
	}
	@Override
	public String getFahrzeugName() {
		return "Mannschaftstransporter";
	}
}
