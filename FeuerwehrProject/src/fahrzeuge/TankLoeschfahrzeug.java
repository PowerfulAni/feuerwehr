package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

/**
 * Konzept vom Tank-Löschfahrzeug
 */
public class TankLoeschfahrzeug extends Fahrzeug{

	private int maxTank;
	
	/**
	 * Ertselle ein neues Fahrzeug
	 * @param id des fahrzeuges
	 * @param sitzplaetze Die zuverfügung stehen
	 * @param fahrzeugTyp ob LKW oder PKW
	 * @param fahrzeugstatus ob zuzeit verfügbar
	 * @param kennzeichen als erkennungs merkmal
	 * @param maxTank maximale Tank größe
	 */
	public TankLoeschfahrzeug(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, int maxTank) {
		super(id,sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen);
		this.maxTank = maxTank;
	}
	/**
	 * Ertselle ein neues Fahrzeug
	 * @param id des fahrzeuges
	 * @param sitzplaetze Die zuverfügung stehen
	 * @param fahrzeugTyp ob LKW oder PKW
	 * @param fahrzeugstatus ob zuzeit verfügbar
	 * @param kennzeichen als erkennungs merkmal
	 * @param maxTank maximale Tank größe
	 * @param einsatzID um bei der Init dies zuzuweisen
	 */
	public TankLoeschfahrzeug(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, int maxTank, int einsatzID) {
		super(id,sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen,einsatzID);
		this.maxTank = maxTank;
	}
	/**
	 * Bekomme die Maximale Tank größe
	 * @return max Tank größe
	 */
	public int getMaxTank () {
		return this.maxTank;
	}
	@Override
	public String getSpezial() {
		return "Tankgröße: "+this.maxTank;
	}
	@Override
	public String getFahrzeugName() {
		return "Tank-Löschfahrzeug";
	}
}
