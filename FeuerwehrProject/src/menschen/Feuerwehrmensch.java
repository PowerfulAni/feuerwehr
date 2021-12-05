package menschen;

import util.FahrzeugTyp;
import util.MitarbeiterStatus;

public class Feuerwehrmensch {
	
	private MitarbeiterStatus mitarbeiterStatus;
	private String name;
	private FahrzeugTyp fahrerlaubnis;
	private int id;
	private int einsatzID=0;
	
	public Feuerwehrmensch(int id,MitarbeiterStatus mitarbeiterStatus, String name, FahrzeugTyp fahrerlaubnis) {
		
		this.mitarbeiterStatus = mitarbeiterStatus;
		this.name = name;
		this.fahrerlaubnis = fahrerlaubnis;
		this.id = id;
	}
public Feuerwehrmensch(int id,MitarbeiterStatus mitarbeiterStatus, String name, FahrzeugTyp fahrerlaubnis, int einsatzID) {
		
		this.mitarbeiterStatus = mitarbeiterStatus;
		this.name = name;
		this.fahrerlaubnis = fahrerlaubnis;
		this.id = id;
		this.einsatzID = einsatzID;
	}
	
	public int getID() {
		return id;
	}
	public int getEinsatzID() {
		return einsatzID;
	}
	public void setEinsatzID(int einsatzID) {
		this.einsatzID=einsatzID;
	}
	
	public MitarbeiterStatus getMitarbeiterStatus() {
		return mitarbeiterStatus;
	}
	
	public String getName() {
		return name;
	}
	
	public FahrzeugTyp getFahrerlaubnis() {
		return fahrerlaubnis;
	}
	
	public void setMitarbeiterStatus(MitarbeiterStatus mitarbeiterStatus) {
		this.mitarbeiterStatus = mitarbeiterStatus;
	}
}
