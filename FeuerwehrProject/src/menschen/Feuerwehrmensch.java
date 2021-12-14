package menschen;

import util.FahrzeugTyp;
import util.MitarbeiterStatus;

/**
 * Grundkonzept der Mitarbeiter
 */
public class Feuerwehrmensch {
	
	private MitarbeiterStatus mitarbeiterStatus;
	private String name;
	private FahrzeugTyp fahrerlaubnis;
	private int id;
	private int einsatzID=0;
	
	/**
	 * Erstellt einen Mitarbeiter
	 * @param id des Mitarbeiters
	 * @param mitarbeiterStatus ob grade verfügbar
	 * @param name des Mitarbeiters
	 * @param fahrerlaubnis für PKW oder LKW
	 */
	public Feuerwehrmensch(int id,MitarbeiterStatus mitarbeiterStatus, String name, FahrzeugTyp fahrerlaubnis) {
		
		this.mitarbeiterStatus = mitarbeiterStatus;
		this.name = name;
		this.fahrerlaubnis = fahrerlaubnis;
		this.id = id;
	}
	/**
	 * Erstellt einen Mitarbeiter
	 * @param id des Mitarbeiters
	 * @param mitarbeiterStatus ob grade verfügbar
	 * @param name des Mitarbeiters
	 * @param fahrerlaubnis für PKW oder LKW
	 * @param einsatzID um in der Init dies zuzuweisen
	 */
	public Feuerwehrmensch(int id,MitarbeiterStatus mitarbeiterStatus, String name, FahrzeugTyp fahrerlaubnis, int einsatzID) {
		
		this.mitarbeiterStatus = mitarbeiterStatus;
		this.name = name;
		this.fahrerlaubnis = fahrerlaubnis;
		this.id = id;
		this.einsatzID = einsatzID;
	}
	
	/**
	 * bekomme die Id
	 * @return Id
	 */
	public int getID() {
		return id;
	}
	/**
	 * Bekomme die Einsatz Id
	 * @return Einsatz Id
	 */
	public int getEinsatzID() {
		return einsatzID;
	}
	/**
	 * Setze die Einsatz Id
	 * @param einsatzID des neuen Einsatzes
	 */
	public void setEinsatzID(int einsatzID) {
		this.einsatzID=einsatzID;
	}
	/**
	 * Bekomme den Aktuellen Mitarbeiter Status
	 * @return Mitarbeiter Status
	 */
	public MitarbeiterStatus getMitarbeiterStatus() {
		return mitarbeiterStatus;
	}
	/**
	 * Bekomme den namen des Mitarbeiters
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Bekomme die Fahrerlaubnis
	 * @return Faherlaubnis (PKW / LKW)
	 */
	public FahrzeugTyp getFahrerlaubnis() {
		return fahrerlaubnis;
	}
	/**
	 * Setze den Mitarbeietr Status
	 * @param mitarbeiterStatus neu zuweisen
	 */
	public void setMitarbeiterStatus(MitarbeiterStatus mitarbeiterStatus) {
		this.mitarbeiterStatus = mitarbeiterStatus;
	}
}
