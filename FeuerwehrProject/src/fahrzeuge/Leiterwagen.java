package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class Leiterwagen extends Fahrzeug {
	
	private int maxLeiter;
	
	public Leiterwagen(int sitzplaetze, FahrzeugTyp fahrzeugTyp, Boolean inWartung, FahrzeugStatus fahrzeugstatus, String kennzeichen, int maxLeiter) {
		super(sitzplaetze, fahrzeugTyp, inWartung, fahrzeugstatus, kennzeichen);
		this.maxLeiter = maxLeiter;
	}
	public int getMaxLeiter () {
		
		return this.maxLeiter;
	}
}
