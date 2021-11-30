package einsaetze;

import java.util.ArrayList;

import fahrzeuge.Fahrzeug;
import menschen.Feuerwehrmensch;
import util.FahrzeugStatus;
import util.MitarbeiterStatus;

public class Einsatz {
	private EinsatzTyp einsatzTyp;
	private ArrayList<Fahrzeug> fahrzeuge;
	private ArrayList<Feuerwehrmensch> mitarbeiter;
	
	public Einsatz(EinsatzTyp typ, ArrayList<Fahrzeug> fahrzeuge, ArrayList<Feuerwehrmensch> mitarbeiter) {
		einsatzTyp = typ;
		this.fahrzeuge = fahrzeuge;
		this.mitarbeiter = mitarbeiter;
	}
	
	private void starteEinsatz() {
		for (Fahrzeug fahrzeug : fahrzeuge) {
			fahrzeug.setFahrzeugStatus(FahrzeugStatus.Einsatz);
		}
		for (Feuerwehrmensch mitarbeiter : mitarbeiter) {
			mitarbeiter.setMitarbeiterStatus(MitarbeiterStatus.Einsatz);
		}
	}
	
	public void beendeEinsatz() {
		for (Fahrzeug fahrzeug : fahrzeuge) {
			fahrzeug.setFahrzeugStatus(FahrzeugStatus.Bereit);
		}
		for (Feuerwehrmensch mitarbeiter : mitarbeiter) {
			mitarbeiter.setMitarbeiterStatus(MitarbeiterStatus.Bereit);
		}
		einsatzTyp = null;
		fahrzeuge = null;
		mitarbeiter = null;
	}
}
