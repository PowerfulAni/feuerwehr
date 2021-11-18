package menschen;

import util.FahrzeugTyp;
import util.MitarbeiterStatus;

public class Feuerwehrmensch {
	
	private MitarbeiterStatus status;
	private String name;
	private FahrzeugTyp fahrerlaubnis;
	
	public Feuerwehrmensch(MitarbeiterStatus status, String name, FahrzeugTyp fahrerlaubnis) {
		
		this.status = status;
		this.name = name;
		this.fahrerlaubnis = fahrerlaubnis;
	}
	
	
	public MitarbeiterStatus getStatus() {
		return status;
	}
	
	public String getName() {
		return name;
	}
	
	public FahrzeugTyp getFahrerlaubnis() {
		return fahrerlaubnis;
	}
	
	public void setMitarbeiterStatus(MitarbeiterStatus status) {
		this.status = status;
	}
}
