package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class Einsatzfahrzeug extends Fahrzeug {
	
	private String dienstgrad;
	
	public Einsatzfahrzeug(int sitzpl�tze, FahrzeugTyp fahrzeugTyp, Boolean inWartung, FahrzeugStatus fahrzeugstatus, String dienstgrad) {
		super(sitzpl�tze, fahrzeugTyp, inWartung, fahrzeugstatus);
		this.dienstgrad = dienstgrad;
	}
	public String getDienstgradString () {
		
		return this.dienstgrad;
	}
	
	
	
	
}
