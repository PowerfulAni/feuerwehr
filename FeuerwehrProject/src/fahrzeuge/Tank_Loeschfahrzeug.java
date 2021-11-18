package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class Tank_Loeschfahrzeug extends Fahrzeug{

	private int maxTank;
	
	public Tank_Loeschfahrzeug(int sitzplätze, FahrzeugTyp fahrzeugTyp, Boolean inWartung,FahrzeugStatus fahrzeugstatus,int maxTank) {
		super(sitzplätze, fahrzeugTyp, inWartung, fahrzeugstatus);
		this.maxTank = maxTank;
	}
	public int getMaxTank () {
		
		return this.maxTank;
	}
}
