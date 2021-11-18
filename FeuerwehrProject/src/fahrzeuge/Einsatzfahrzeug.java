package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class Einsatzfahrzeug extends Fahrzeug {
	
	private String dienstgraString;
	
	public Einsatzfahrzeug(int sitzplštze, FahrzeugTyp fahrzeugTyp, Boolean inWartung, FahrzeugStatus fahrzeugstatus, String dienstgrad) {
		super(sitzplštze, fahrzeugTyp, inWartung, fahrzeugstatus);
		this.dienstgraString = dienstgrad;
	}
	public String getDienstgradString () {
		
		return this.dienstgraString;
	}
	
	
	
	
}
