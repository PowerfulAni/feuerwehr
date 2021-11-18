package fahrzeuge;

import java.io.ObjectInputStream.GetField;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class Fahrzeug {
	
	private int sitzpl�tze;
	private boolean inWartung;
	private FahrzeugTyp fahrzeugTyp;
	private FahrzeugStatus fahrzeugStatus;
	
	public Fahrzeug(int sitzpl�tze, FahrzeugTyp fahrzeugTyp, Boolean inWartung, FahrzeugStatus fahrzeugstatus ) {
		
		this.sitzpl�tze = sitzpl�tze;
		this.fahrzeugTyp = fahrzeugTyp;
		this.inWartung = inWartung;
		this.fahrzeugStatus = fahrzeugstatus;
	}
	public int getSitzpl�tze () {
		
		return this.sitzpl�tze;
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
