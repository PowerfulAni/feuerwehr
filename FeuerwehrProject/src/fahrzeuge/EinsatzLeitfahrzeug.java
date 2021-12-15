package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

/**
 * Konzept vom Einsatz-Leitfahrzeug
 */
public class EinsatzLeitfahrzeug extends Fahrzeug {
	
	private String dienstgrad;
	
	/**
	 * Ertselle ein neues Fahrzeug
	 * @param id des Fahrzeuges
	 * @param sitzplaetze, die zu Verfügung stehen
	 * @param fahrzeugTyp ob LKW oder PKW
	 * @param fahrzeugstatus ob zuzeit verfügbar
	 * @param kennzeichen als erkennungs merkmal
	 * @param dienstgrad des Leiters
	 */
	public EinsatzLeitfahrzeug(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, String dienstgrad) {
		super(id,sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen);
		this.dienstgrad = dienstgrad;
	}
	/**
	 * Ertselle ein neues Fahrzeug
	 * @param id des fahrzeuges
	 * @param sitzplaetze Die zuverfügung stehen
	 * @param fahrzeugTyp ob LKW oder PKW
	 * @param fahrzeugstatus ob zuzeit verfügbar
	 * @param kennzeichen als erkennungs merkmal
	 * @param dienstgrad des Leiters
	 * @param einsatzID um bei der Init dies zuzuweisen
	 */
	public EinsatzLeitfahrzeug(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, String dienstgrad, int einsatzID) {
		super(id,sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen,einsatzID);
		this.dienstgrad = dienstgrad;
	}
	/**
	 * Bekomme den Dienstagrad
	 * @return Dienstgrad
	 */
	public String getDienstgradString () {
		return this.dienstgrad;
	}
	@Override
	public String getSpezial() {
		return "Dienstgrad: "+this.dienstgrad;
	}
	
	@Override
	public String getFahrzeugName() {
		return "Einsatz-Leitfahrzeug";
	}
	
	
}
