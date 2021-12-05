package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class Mannschftstransporter extends Fahrzeug {

	private int baujahr;
	
public Mannschftstransporter(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, Boolean inWartung,FahrzeugStatus fahrzeugstatus,String kennzeichen, int baujahr) {
		super(id,sitzplaetze, fahrzeugTyp, inWartung, fahrzeugstatus, kennzeichen);
		this.baujahr = baujahr;
	}
	public int getBaujahr () {
		
		return this.baujahr;
	}
}
