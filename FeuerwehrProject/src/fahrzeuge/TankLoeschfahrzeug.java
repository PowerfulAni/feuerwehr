package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class TankLoeschfahrzeug extends Fahrzeug{

	private int maxTank;
	
	public TankLoeschfahrzeug(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, int maxTank) {
		super(id,sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen);
		this.maxTank = maxTank;
	}
	public TankLoeschfahrzeug(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, int maxTank, int einsatzID) {
		super(id,sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen,einsatzID);
		this.maxTank = maxTank;
	}
	public int getMaxTank () {
		
		return this.maxTank;
	}
}
