package fahrzeuge;

import java.io.ObjectInputStream.GetField;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class Fahrzeug {
	
	private int sitzplätze;
	private boolean inWartung;
	private FahrzeugTyp fahrzeugTyp;
	private FahrzeugStatus fahrzeugStatus;
	
	public Fahrzeug(int sitzplätze, FahrzeugTyp fahrzeugTyp, Boolean inWartung, FahrzeugStatus fahrzeugstatus ) {
		
		this.sitzplätze = sitzplätze;
		this.fahrzeugTyp = fahrzeugTyp;
		this.inWartung = inWartung;
		this.fahrzeugStatus = fahrzeugstatus;
	}
	public int getSitzplätze () {
		
		return this.sitzplätze;
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
	
		
	
	
	
	
	
	
}
