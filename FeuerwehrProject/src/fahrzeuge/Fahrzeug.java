package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

/**
 * Grundkonzept eines Fahrzeuges
 */
public abstract class Fahrzeug {
	
	private int sitzplaetze;
	private FahrzeugTyp fahrzeugTyp;
	private FahrzeugStatus fahrzeugStatus;
	private String kennzeichen;
	private int id;
	private int einsatzID=0;
	
	/**
	 * Ertselle ein neues Fahrzeug
	 * @param id des fahrzeuges
	 * @param sitzplaetze Die zuverfügung stehen
	 * @param fahrzeugTyp ob LKW oder PKW
	 * @param fahrzeugstatus ob zuzeit verfügbar
	 * @param kennzeichen als erkennungs merkmal
	 */
	public Fahrzeug(int id, int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen ) {
		
		this.id = id;
		this.sitzplaetze = sitzplaetze;
		this.fahrzeugTyp = fahrzeugTyp;
		this.fahrzeugStatus = fahrzeugstatus;
		this.kennzeichen = kennzeichen;
	}
	/**
	 * Ertselle ein neues Fahrzeug
	 * @param id des fahrzeuges
	 * @param sitzplaetze Die zuverfügung stehen
	 * @param fahrzeugTyp ob LKW oder PKW
	 * @param fahrzeugstatus ob zuzeit verfügbar
	 * @param kennzeichen als erkennungs merkmal
	 * @param einsatzID um bei der Init dies zuzuweisen
	 */
	public Fahrzeug(int id, int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, int einsatzID ) {
		
		this.id = id;
		this.sitzplaetze = sitzplaetze;
		this.fahrzeugTyp = fahrzeugTyp;
		this.fahrzeugStatus = fahrzeugstatus;
		this.kennzeichen = kennzeichen;
		this.einsatzID = einsatzID;
	}
	/**
	 * Bekomme die Id
	 * @return Id
	 */
	public int getID() {
		return id;
	}
	/**
	 * Bekomme die Einsatz Id
	 * @return Id
	 */
	public int getEinsatzID() {
		return einsatzID;
	}
	/**
	 * Weise einen neuen Einsatz zu
	 * @param einsatzID
	 */
	public void setEinsatzID(int einsatzID) {
		this.einsatzID=einsatzID;
	}
	/**
	 * Bekomme die anzahl an Sitzplätzen
	 * @return anzahl an Sitzplätzen
	 */
	public int getSitzplaetze () {
		return this.sitzplaetze;
	}
	/**
	 * Bekomme den FahrzeugTypen
	 * @return LKW oder PKW
	 */
	public FahrzeugTyp getFahrzeugTyp () {
		return this.fahrzeugTyp;
	}
	/**
	 * Bekomme den fahrzeugstatus
	 * @return Status
	 */
	public FahrzeugStatus getFahrzeugStatus () {
		return this.fahrzeugStatus;
	}
	/**
	 * Setze den Fahrzeugstatus
	 * @param fahrzeugStatus
	 */
	public void setFahrzeugStatus (FahrzeugStatus fahrzeugStatus) {
		this.fahrzeugStatus = fahrzeugStatus;
	}
	/**
	 * Bekomme das Kennzeichen
	 * @return Kennzeichen
	 */
	public String getKennzeichen () {
		return this.kennzeichen;
	}
	
	/**
	 * Eine methode um den Fahrzeug namen zu erhalten.
	 * @return Fahrzeugname
	 */
	public abstract String getFahrzeugName();
	/**
	 * Eine methode um den Fahrzeug spezial zuerhalten
	 * @return Spezial
	 */
	public abstract String getSpezial();
}
