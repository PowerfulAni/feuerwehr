package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class Mannschftstransporter extends Fahrzeug {

	private int baujahr;
	
public Mannschftstransporter(int sitzplätze, FahrzeugTyp fahrzeugTyp, Boolean inWartung,FahrzeugStatus fahrzeugstatus, int baujahr) {
		super(sitzplätze, fahrzeugTyp, inWartung, fahrzeugstatus);
		this.baujahr = baujahr;
		
	}
	public int getBaujahr () {
		
		return this.baujahr;
	}
}
