package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class Leiterwagen extends Fahrzeug {
	
	private int maxLeiter;
	
	public Leiterwagen(int sitzpl�tze, FahrzeugTyp fahrzeugTyp, Boolean inWartung, FahrzeugStatus fahrzeugstatus, int maxLeiter) {
		super(sitzpl�tze, fahrzeugTyp, inWartung, fahrzeugstatus);
		this.maxLeiter = maxLeiter;
	}
	public int getMaxLeiter () {
		
		return this.maxLeiter;
	}
}