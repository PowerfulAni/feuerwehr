package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class Einsatzfahrzeug extends Fahrzeug {
	
	private String dienstgrad;
	
	public Einsatzfahrzeug(int sitzplštze, FahrzeugTyp fahrzeugTyp, Boolean inWartung, FahrzeugStatus fahrzeugstatus, String kennzeichen, String dienstgrad) {
		super(sitzplštze, fahrzeugTyp, inWartung, fahrzeugstatus, kennzeichen);
		this.dienstgrad = dienstgrad;
	}
	public String getDienstgradString () {
		
		return this.dienstgrad;
	}
	
	
	
	
}
