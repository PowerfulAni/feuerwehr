package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class Mannschftstransporter extends Fahrzeug {

	private int baujahr;
	
public Mannschftstransporter(int sitzplštze, FahrzeugTyp fahrzeugTyp, Boolean inWartung,FahrzeugStatus fahrzeugstatus,String kennzeichen, int baujahr) {
		super(sitzplštze, fahrzeugTyp, inWartung, fahrzeugstatus, kennzeichen);
		this.baujahr = baujahr;
		
	}
	public int getBaujahr () {
		
		return this.baujahr;
	}
}
