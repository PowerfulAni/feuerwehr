package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class Fahrzeug {
	
	private int sitzplaetze;
	private boolean inWartung;
	private FahrzeugTyp fahrzeugTyp;
	private FahrzeugStatus fahrzeugStatus;
	private String kennzeichen;
	
	public Fahrzeug(int sitzplaetze, FahrzeugTyp fahrzeugTyp, Boolean inWartung, FahrzeugStatus fahrzeugstatus, String kennzeichen ) {
		
		this.sitzplaetze = sitzplaetze;
		this.fahrzeugTyp = fahrzeugTyp;
		this.inWartung = inWartung;
		this.fahrzeugStatus = fahrzeugstatus;
		this.kennzeichen = kennzeichen;
	}
	public int getSitzplaetze () {
		
		return this.sitzplaetze;
	}
	public FahrzeugTyp getFahrzeugTyp () {
		
		return this.fahrzeugTyp;
	}
	public boolean getInWartung () {
		
		return this.inWartung; 
	}
	public FahrzeugStatus getFahrzeugStatus () {
		
		return this.fahrzeugStatus;
	}
	
	public void setInWartung (boolean inWartung) {
		this.inWartung = inWartung;
	}
	public void setFahrzeugStatus (FahrzeugStatus fahrzeugStatus) {
		this.fahrzeugStatus = fahrzeugStatus;
	}
	public String getKennzeichenString () {
		
		return this.kennzeichen;
	}
	
}
