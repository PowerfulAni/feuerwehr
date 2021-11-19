package menschen;

import util.FahrzeugTyp;
import util.MitarbeiterStatus;

public class Feuerwehrmensch {
	
	private MitarbeiterStatus mitarbeiterStatus;
	private String name;
	private FahrzeugTyp fahrerlaubnis;
	
	public Feuerwehrmensch(MitarbeiterStatus mitarbeiterStatus, String name, FahrzeugTyp fahrerlaubnis) {
		
		this.mitarbeiterStatus = mitarbeiterStatus;
		this.name = name;
		this.fahrerlaubnis = fahrerlaubnis;
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
