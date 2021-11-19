package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class TankLoeschfahrzeug extends Fahrzeug{

	private int maxTank;
	
	public TankLoeschfahrzeug(int sitzplaetze, FahrzeugTyp fahrzeugTyp, Boolean inWartung ,FahrzeugStatus fahrzeugstatus,int maxTank, String kennzeichen) {
		super(sitzplaetze, fahrzeugTyp, inWartung, fahrzeugstatus, kennzeichen);
		this.maxTank = maxTank;
	}
	public int getMaxTank () {
		
		return this.maxTank;
	}
}
