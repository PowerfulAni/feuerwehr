package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class TankLoeschfahrzeug extends Fahrzeug{

	private int maxTank;
	
	public TankLoeschfahrzeug(int sitzplštze, FahrzeugTyp fahrzeugTyp, Boolean inWartung,FahrzeugStatus fahrzeugstatus,String kennzeichen, maxTank) {
		super(sitzplštze, fahrzeugTyp, inWartung, fahrzeugstatus, kennzeichen);
		this.maxTank = maxTank;
	}
	public int getMaxTank () {
		
		return this.maxTank;
	}
}
