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
	 * @param id des fahrzeuges
	 * @param sitzplaetze Die zuverfügung stehen
	 * @param fahrzeugTyp ob LKW oder PKW
	 * @param fahrzeugstatus ob zuzeit verfügbar
	 * @param kennzeichen als erkennungs merkmal
	 * @param baujahr des fahrzeugs
	 */
	public Mannschaftstransporter(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus,String kennzeichen, int baujahr) {
		super(id,sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen);
		this.baujahr = baujahr;
	}
	/**
	 * Ertselle ein neues Fahrzeug
	 * @param id des fahrzeuges
	 * @param sitzplaetze Die zuverfügung stehen
	 * @param fahrzeugTyp ob LKW oder PKW
	 * @param fahrzeugstatus ob zuzeit verfügbar
	 * @param kennzeichen als erkennungs merkmal
	 * @param baujahr des fahrzeugs
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
