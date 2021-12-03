package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class EinsatzLeitfahrzeug extends Fahrzeug {
	
	private String dienstgrad;
	
	public EinsatzLeitfahrzeug(int sitzplaetze, FahrzeugTyp fahrzeugTyp, Boolean inWartung, FahrzeugStatus fahrzeugstatus, String kennzeichen, String dienstgrad) {
		super(sitzplaetze, fahrzeugTyp, inWartung, fahrzeugstatus, kennzeichen);
		this.dienstgrad = dienstgrad;
	}
	public String getDienstgradString () {
		
		return this.dienstgrad;
	}
	
	
	
	
}