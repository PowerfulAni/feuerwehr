package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class TankLoeschfahrzeug extends Fahrzeug{

	private int maxTank;
	
	public TankLoeschfahrzeug(int sitzpl�tze, FahrzeugTyp fahrzeugTyp, Boolean inWartung,FahrzeugStatus fahrzeugstatus,int maxTank) {
		super(sitzpl�tze, fahrzeugTyp, inWartung, fahrzeugstatus);
		this.maxTank = maxTank;
	}
	public int getMaxTank () {
		
		return this.maxTank;
	}
}
