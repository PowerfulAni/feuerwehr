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
	 * @param id des Fahrzeuges
	 * @param sitzplaetze Die zur Verfügung stehen
	 * @param fahrzeugTyp ob LKW oder PKW
	 * @param fahrzeugstatus ob zurzeit verfügbar
	 * @param kennzeichen als Erkennungsmerkmal
	 * @param maxTank maximale Tankgröße
	 */
	public TankLoeschfahrzeug(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, int maxTank) {
		super(id,sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen);
		this.maxTank = maxTank;
	}
	/**
	 * Ertselle ein neues Fahrzeug
	 * @param id des Fahrzeuges
	 * @param sitzplaetze Die zur Verfügung stehen
	 * @param fahrzeugTyp ob LKW oder PKW
	 * @param fahrzeugstatus ob zurzeit verfügbar
	 * @param kennzeichen als Erkennungsmerkmal
	 * @param maxTank maximale Tankgröße
	 * @param einsatzID um bei der Init dies zuzuweisen
	 */
	public TankLoeschfahrzeug(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, int maxTank, int einsatzID) {
		super(id,sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen,einsatzID);
		this.maxTank = maxTank;
	}
	/**
	 * Bekomme die Maximale Tankgröße
	 * @return max Tankgröße
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
