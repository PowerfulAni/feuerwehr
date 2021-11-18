package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class Leiterwagen extends Fahrzeug {
	
	private int maxLeiter;
	
	public Leiterwagen(int sitzplätze, FahrzeugTyp fahrzeugTyp, Boolean inWartung, FahrzeugStatus fahrzeugstatus, int maxLeiter) {
		super(sitzplätze, fahrzeugTyp, inWartung, fahrzeugstatus);
		this.maxLeiter = maxLeiter;
	}
	public int getMaxLeiter () {
		
		return this.maxLeiter;
	}
}
