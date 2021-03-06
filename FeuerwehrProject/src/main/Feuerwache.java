package main;

import java.util.ArrayList;

import einsaetze.*;
import fahrzeuge.*;
import menschen.*;
import util.*;
import mysql.*;

/**
 * Hier werden alle Fahrzeuge und Mitarbeiter verwaltet.
 */
public class Feuerwache {
	private ArrayList<Fahrzeug> fahrzeuge = new ArrayList<>();
	private ArrayList<Feuerwehrmensch> mitarbeiter = new ArrayList<>();
	private ArrayList<Einsatz> einsaetze = new ArrayList<>();
	private Einsatz vorschlag;
	private final EinsatzTyp wohungsbrand = new EinsatzTyp("Wohnungsbrand", 22, 1, 2, 1, 1);
	private final EinsatzTyp verkehrsunfall = new EinsatzTyp("Verkehrsunfall", 16, 1, 1, 1, 0);
	private final EinsatzTyp naturkatastrophe = new EinsatzTyp("Naturkatastrophe", 55, 3, 3, 3, 2);
	private final EinsatzTyp industrieunfall = new EinsatzTyp("Industrieunfall", 40, 3, 2, 2, 2);

	/**
	 * Der Kontruktor der Klasse, um Fahrzeuge und Mitarbeiter zu initialisieren.
	 */
	public Feuerwache() {
		if(Datenbank.verbinden()) {
			fahrzeuge = Datenbank.initFahrzeug();
			mitarbeiter = Datenbank.initFeuerwehrmensch();
			// Erstelle die gestarteten Einsätze
			ArrayList<EinsatzDaten> mission = Datenbank.initEinsatz();
			for (EinsatzDaten entry : mission) {
				ArrayList<Fahrzeug> f = new ArrayList<>();
				for (int i = 0; i < fahrzeuge.size(); i++) {
					if(fahrzeuge.get(i).getEinsatzID() == entry.getID()) {
						f.add(fahrzeuge.get(i));
					}
				}
				ArrayList<Feuerwehrmensch> m = new ArrayList<>();
				for (int i = 0; i < mitarbeiter.size(); i++) {
					if(mitarbeiter.get(i).getEinsatzID() == entry.getID()) {
						m.add(mitarbeiter.get(i));
					}
				}
				einsaetze.add(new Einsatz(getEinsatzTyp(entry.getName()), f, m, false, entry.getID()));
				f = null;
				m = null;
			}
			mission = null;
		}
		else {
			// falls keine verbindung vorhanden ist
			for (int i = 0; i < 18; i++) {
				switch (i) {
				case 0,1,2,3:
					fahrzeuge.add(new EinsatzLeitfahrzeug(1,2, FahrzeugTyp.PKW, FahrzeugStatus.Bereit, "EI-" + i, "Einsatzleiter"));
					break;
				case 4,5,6,7,8:
					fahrzeuge.add(new Leiterwagen(2,4, FahrzeugTyp.LKW, FahrzeugStatus.Bereit, "LT-" + i, 8));
					break;
				case 9,10,11,12:
					fahrzeuge.add(new Mannschaftstransporter(3,14, FahrzeugTyp.LKW, FahrzeugStatus.Bereit, "MT-" + i, 1990+i));
					break;
				case 13,14,15,16,17:
					fahrzeuge.add(new TankLoeschfahrzeug(4,2, FahrzeugTyp.LKW, FahrzeugStatus.Bereit, "TL-" + i, 50));
					break;
				}
			}
			for (int i = 0; i < 80; i++) {
				mitarbeiter.add(new Feuerwehrmensch(i,MitarbeiterStatus.Bereit, "Dummy " + i, i<10 ? FahrzeugTyp.LKW : FahrzeugTyp.PKW));
			}
		}
	}
	
	/**
	 * Gibt alle Fahrzeuge zurück.
	 * @return ArrayList von allen Fahrzeugen
	 */
	public ArrayList<Fahrzeug> getFahrzeuge(){
		return fahrzeuge;
	}
	
	/**
	 * Gibt alle Mitarbeiter zurück.
	 * @return ArrayList von allen Mitarbeitern
	 */
	public ArrayList<Feuerwehrmensch> getMitarbeiter(){
		return mitarbeiter;
	}
	
	/**
	 * Gibt alle Einsätze zurück.
	 * @return ArrayList von allen Einsätzen
	 */
	public ArrayList<Einsatz> getEinsaetze(){
		return einsaetze;
	}
	
	/**
	 * Gibt die absolute Anzahl an Fahrzeugen des angegeben Status zurück.
	 * Um z.B. alle Fahrzeuge zurückzugeben, die zu Hause sind:
	 * new FahrzeugStatus[] { FahrzeugStatus.Bereit, FahrzeugStatus.Wartung }
	 * 
	 * @param status Ein Array an Status
	 * @return Maximale Anzahl an Fahrzeugen mit den Status
	 */
	public int getAbsFahrzeugStatus(FahrzeugStatus[] status) {
		int sum = 0;
		for (int i = 0; i < fahrzeuge.size(); i++) {
			for (int j = 0; j < status.length; j++) {
				if(fahrzeuge.get(i).getFahrzeugStatus() == status[j]) {
					sum++;
					break;
				}
			}
		}
		return sum;
	}
	
	/**
	 * Gibt die Absolute Anzahl an Mitarbeitern des angegeben Status zurück.
	 * Um z.b. alle Mitarbeiter zurückzugeben, die absolut nicht zur Verfügung stehen:
	 * new MitarbeiterStatus[] { MitarbeiterStatus.Urlaub, MitarbeiterStatus.Krank }
	 * 
	 * @param status Ein Array an Status
	 * @return Maximale anzahl an Mitarbeitern mit den Status
	 */
	public int getAbsMitarbeiterStatus(MitarbeiterStatus[] status) {
		int sum = 0;
		for (int i = 0; i < mitarbeiter.size(); i++) {
			for (int j = 0; j < status.length; j++) {
				if(mitarbeiter.get(i).getMitarbeiterStatus() == status[j]) {
					sum++;
					break;
				}
			}
		}
		return sum;
	}
	
	/**
	 * Startet nach Möglichkeit den übergebenen Einsatz.
	 * @param einsatz
	 * @param fahrzeuge
	 * @param mitarbeiter
	 * @return Ob der Einsatz möglich war
	 */
	public boolean startEinsatz(EinsatzTyp typ, ArrayList<Fahrzeug> fahrzeuge, ArrayList<Feuerwehrmensch> mitarbeiter) {
		if(!istEinsatzAnforderung(typ, fahrzeuge, mitarbeiter))
			return false;
		einsaetze.add(new Einsatz(typ, fahrzeuge, mitarbeiter, false));
		// Fühge Einsatz hinzu und setze ID
		int id = Datenbank.einfuegenEinsatz(mitarbeiter, fahrzeuge, typ);
		for (int i = 0; i < fahrzeuge.size(); i++) {
			fahrzeuge.get(i).setEinsatzID(id);
			Datenbank.updateEinsatz(fahrzeuge.get(i), id);
		}
		for (int i = 0; i < mitarbeiter.size(); i++) {
			mitarbeiter.get(i).setEinsatzID(id);
			Datenbank.updateEinsatz(mitarbeiter.get(i), id);
		}
		return true;
	}

	private boolean istEinsatzAnforderung(EinsatzTyp typ, ArrayList<Fahrzeug> fahrzeuge, ArrayList<Feuerwehrmensch> mitarbeiter) {
		int[] curFahr = new int[4];
		for (Fahrzeug fahrzeug : fahrzeuge) {
			switch (fahrzeug.getFahrzeugName()) {
				case "Einsatz-Leitfahrzeug":
						curFahr[0]++;
					break;
				case "Tank-Löschfahrzeug":
						curFahr[1]++;
					break;
				case "Mannschaftstransporter":
						curFahr[2]++;
					break;
				case "Leiterwagen":
						curFahr[3]++;
					break;

				default:
					break;
			}
		}
		if(curFahr[0] < typ.minEinsatzfahrzeug 
			|| curFahr[1] < typ.minTankLoeschfahrzeug
			|| curFahr[2] < typ.minManschaftstransporter
			|| curFahr[3] < typ.minLeiterwagen)
			return false;
			
		int minLKW = typ.minTankLoeschfahrzeug + typ.minManschaftstransporter + typ.minLeiterwagen;
		/*
		 * 0 -> LKW
		 * 1 -> PWK
		 */
		int[] curWagen = new int[2];
		int minMit = 0;
		for (Feuerwehrmensch mensch : mitarbeiter) {
			if(mensch.getMitarbeiterStatus() == MitarbeiterStatus.Bereit) {
				switch (mensch.getFahrerlaubnis()) {
				case LKW:
					if(curWagen[0] < minLKW) {
						curWagen[0]++;
						minMit++;
						break;
					}
				case PKW:
					if(curWagen[1] < typ.minEinsatzfahrzeug) {
						curWagen[1]++;
					}
					minMit++;
					break;

				default:
					break;
				}
			}
		}
		return curWagen[0] < minLKW
				|| curWagen[1] < typ.minEinsatzfahrzeug
				|| minMit < typ.minMitarbeiter
				|| minMit > (curFahr[0] * 2 + curFahr[1] * 4 + curFahr[2] * 14 + curFahr[3] * 2)
				? false : true;
	}

	/**
	 * Gibt den zuvor erstellten Vorschlag zurück.
	 * @return den zuvor erstellten Vorschlag
	 */
	public Einsatz getVorschlag(){
		return vorschlag;		
	}
	
	/**
	 * Erzeugt nach Möglichkeit einen Einsatz Vorschlag.
	 * @param einsatz
	 * @return Ob der Vorschlag erstellt werden konnte
	 */
	public boolean kannErzeugeVorschlag(String einsatz) {
		EinsatzTyp typ = getEinsatzTyp(einsatz);
		ArrayList<Fahrzeug> fahr = new ArrayList<>();
		/*
		 * 0 -> Einsatzfahrzeug
		 * 1 -> TankLöschfahrzeug
		 * 2 -> Manschaftstransporter
		 * 3 -> Leiterwagen
		 */
		int[] curFahr = new int[4];
		for (Fahrzeug fahrzeug : fahrzeuge) {
			if(fahrzeug.getFahrzeugStatus() == FahrzeugStatus.Bereit) {
				switch (fahrzeug.getFahrzeugName()) {
				case "Einsatz-Leitfahrzeug":
					if(curFahr[0] < typ.minEinsatzfahrzeug) {
						curFahr[0]++;
						fahr.add(fahrzeug);
					}
					break;
				case "Tank-Löschfahrzeug":
					if(curFahr[1] < typ.minTankLoeschfahrzeug) {
						curFahr[1]++;
						fahr.add(fahrzeug);
					}
					break;
				case "Mannschaftstransporter":
					if(curFahr[2] < typ.minManschaftstransporter) {
						curFahr[2]++;
						fahr.add(fahrzeug);
					}
					break;
				case "Leiterwagen":
					if(curFahr[3] < typ.minLeiterwagen) {
						curFahr[3]++;
						fahr.add(fahrzeug);
					}
					break;

				default:
					break;
				}
			}
		}
		if(curFahr[0] < typ.minEinsatzfahrzeug 
			|| curFahr[1] < typ.minTankLoeschfahrzeug
			|| curFahr[2] < typ.minManschaftstransporter
			|| curFahr[3] < typ.minLeiterwagen)
			return false;
		
		ArrayList<Feuerwehrmensch> mit = new ArrayList<>();
		int minLKW = typ.minTankLoeschfahrzeug + typ.minManschaftstransporter + typ.minLeiterwagen;
		/*
		 * 0 -> LKW
		 * 1 -> PKW
		 */
		int[] curWagen = new int[2];
		for (Feuerwehrmensch mensch : mitarbeiter) {
			if(mensch.getMitarbeiterStatus() == MitarbeiterStatus.Bereit) {
				switch (mensch.getFahrerlaubnis()) {
				case LKW:
					if(curWagen[0] < minLKW) {
						curWagen[0]++;
						mit.add(mensch);
					}
					
					break;
				case PKW:
					if(curWagen[1] < typ.minEinsatzfahrzeug) {
						curWagen[1]++;
						
					}
					if(mit.size() < typ.minMitarbeiter) {
						mit.add(mensch);
						
					}
					break;

				default:
					break;
				}
			}
		}

		if(curWagen[0] < minLKW
			|| curWagen[1] < typ.minEinsatzfahrzeug
			|| mit.size() < typ.minMitarbeiter
			|| mit.size() > (curFahr[0] * 2 + curFahr[1] * 4 + curFahr[2] * 14 + curFahr[3] * 2))
			return false;
		vorschlag = new Einsatz(typ, fahr, mit, true);
		return true;
	}
	
	private EinsatzTyp getEinsatzTyp(String einsatz) {
		switch (einsatz){
			case "Wohnungsbrand":
				return wohungsbrand;
			case "Verkehrsunfall":
				return verkehrsunfall;
			case "Naturkatastrophe":
				return naturkatastrophe;
			case "Industrieunfall":
				return industrieunfall;
			default:
				return wohungsbrand;
		}
	}
	
	/**
	 * Beendet den angegeben Einsatz
	 * @param index Der Index der Position in der Liste
	 */
	public void beendeEinsatz(int index) {
		// Resette die Einsatz Id
		for (Fahrzeug fahr : einsaetze.get(index).getFahrzeuge()) {
			fahr.setEinsatzID(0);
			Datenbank.updateEinsatz(fahr, 0);
		}
		for (Feuerwehrmensch mit : einsaetze.get(index).getMitarbeiter()) {
			mit.setEinsatzID(0);
			Datenbank.updateEinsatz(mit, 0);
		}
		
		einsaetze.get(index).einsatzBeenden();
		
		Datenbank.entfernEinsatz(einsaetze.get(index));
		einsaetze.remove(index);
		
	}
	
	/**
	 * Updatet den Status vom Fahrzeug
	 * @param fahr das Fahrzeug
	 * @param stat der Ziel Status
	 */
	public void updateStatus(Fahrzeug fahr, FahrzeugStatus stat) {
		fahr.setFahrzeugStatus(stat);
		Datenbank.updateStatus(fahr, stat);
	}
	/**
	 * Updatet den Status vom Mitarbeiter
	 * @param mit der Mitarbiter
	 * @param stat der Ziel Status
	 */
	public void updateStatus(Feuerwehrmensch mit, MitarbeiterStatus stat) {
		mit.setMitarbeiterStatus(stat);
		Datenbank.updateStatus(mit, stat);
	}
}
