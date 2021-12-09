package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class Leiterwagen extends Fahrzeug {
	
	private int maxLeiter;
	
	public Leiterwagen(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, int maxLeiter) {
		super(id, sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen);
		this.maxLeiter = maxLeiter;
	}
	public Leiterwagen(int id,int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, int maxLeiter, int einsatzID) {
		super(id, sitzplaetze, fahrzeugTyp, fahrzeugstatus, kennzeichen, einsatzID);
		this.maxLeiter = maxLeiter;
	}
	public int getMaxLeiter () {
		
		return this.maxLeiter;
	}
	
	public String getSpezial() {
		return "Maximale Leiterhöhe: "+maxLeiter;
	}
	
	@Override
	public String getFahrzeugName() {
		return "Leiterwagen";
	}
}
