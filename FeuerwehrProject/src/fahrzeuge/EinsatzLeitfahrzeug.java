package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class EinsatzLeitfahrzeug extends Fahrzeug {
	
	private String dienstgrad;
	
	public EinsatzLeitfahrzeug(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, String dienstgrad) {
		super(id,sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen);
		this.dienstgrad = dienstgrad;
	}
	public EinsatzLeitfahrzeug(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, String dienstgrad, int einsatzID) {
		super(id,sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen,einsatzID);
		this.dienstgrad = dienstgrad;
	}
	public String getDienstgradString () {
		
		return this.dienstgrad;
	}
	
	@Override
	public String getFahrzeugName() {
		return "Einsatz-Leitfahrzeug";
	}
	
	
}
