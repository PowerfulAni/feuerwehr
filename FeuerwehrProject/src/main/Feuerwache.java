package main;

import java.util.ArrayList;

import einsaetze.Einsatz;
import einsaetze.EinsatzTyp;
import fahrzeuge.*;
import menschen.*;
import util.*;

/**
 * Hier werden alle fahrzeuge und Mitarbeiter verwaltet.
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
	 * Der kontruktor der Klasse um Fahrzeuge und Mitarbeiter zu initialisieren.
	 */
	public Feuerwache() {
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
	 * Gibt die Absolute anzahl an fahrzeugen des angegeben status zurück.
	 * Um z.b. alle Fahrzeuge zurückzugen die zu Hause sind:
	 * new FahrzeugStatus[] { FahrzeugStatus.Bereit, FahrzeugStatus.Wartung }
	 * 
	 * @param status Ein Array an Status
	 * @return int
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
	 * Gibt die Absolute anzahl an Mitarbeitern des angegeben status zurück.
	 * Um z.b. alle mitarbeiter zurückzugen die zu absolut nicht zur verfügung stehen sind:
	 * new MitarbeiterStatus[] { MitarbeiterStatus.Urlaub, MitarbeiterStatus.Krank }
	 * 
	 * @param status Ein Array an Status
	 * @return int
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
	 * Startet nach möglichkeit den Übergebenen Einsatz.
	 * @param einsatz
	 * @param fahrzeuge
	 * @param mitarbeiter
	 * @return
	 */
	public boolean startEinsatz(String einsatz, ArrayList<Fahrzeug> fahrzeuge, ArrayList<Feuerwehrmensch> mitarbeiter) {
		EinsatzTyp typ = getEinsatzTyp(einsatz);
		System.out.println(einsaetze.size());
		if(!istEinsatzAnforderung(typ, fahrzeuge, mitarbeiter))
			return false;
		einsaetze.add(new Einsatz(typ, fahrzeuge, mitarbeiter, false));
		System.out.println(einsaetze.size());
		return true;
	}
	public boolean addEinsatz(Einsatz einsatz) {
		einsaetze.add(einsatz);
		return true;
	}

	private boolean istEinsatzAnforderung(EinsatzTyp typ, ArrayList<Fahrzeug> fahrzeuge, ArrayList<Feuerwehrmensch> mitarbeiter) {
		int[] curFahr = new int[4];
		for (Fahrzeug fahrzeug : fahrzeuge) {
			switch (fahrzeug.getClass().getSimpleName()) {
				case "Einsatzfahrzeug":
					if(curFahr[0] < typ.minEinsatzfahrzeug) {
						curFahr[0]++;
					}
					break;
				case "TankLoeschfahrzeug":
					if(curFahr[1] < typ.minTankLoeschfahrzeug) {
						curFahr[1]++;
					}
					break;
				case "Mannschaftstransporter":
					if(curFahr[2] < typ.minManschaftstransporter) {
						curFahr[2]++;
					}
					break;
				case "Leiterwagen":
					if(curFahr[3] < typ.minLeiterwagen) {
						curFahr[3]++;
					}
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
				|| minMit > (curFahr[0] * 4 + curFahr[1] * 4 + curFahr[2] * 14 + curFahr[3] * 2)
				? false : true;
	}

	/**
	 * Gibt den Zuvor Erstellten Vorschlag zurück.
	 * @return
	 */
	public Einsatz getVorschlag(){
		return vorschlag;		
	}
	
	/**
	 * Erzeugt nach möglichkeit einen Einsatz Vorschlag.
	 * @param einsatz
	 * @return
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
				switch (fahrzeug.getClass().getSimpleName()) {
				case "EinsatzLeitfahrzeug":
					if(curFahr[0] < typ.minEinsatzfahrzeug) {
						curFahr[0]++;
						fahr.add(fahrzeug);
					}
					break;
				case "TankLoeschfahrzeug":
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
//		System.out.println("minEinsatzfahrzeug - !"+typ.minEinsatzfahrzeug +" < "+curFahr[0]);
//		System.out.println("minTankLoeschfahrzeug - !"+typ.minTankLoeschfahrzeug +" < "+curFahr[1]);
//		System.out.println("minManschaftstransporter - !"+typ.minManschaftstransporter +" < "+curFahr[2]);
//		System.out.println("minLeiterwagen - !"+typ.minLeiterwagen +" < "+curFahr[3]);
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
//		System.out.println("Wenn True Abbruch");
//		System.out.println("1. "+curWagen[0] +" < "+minLKW +" => "+(curWagen[0] < minLKW));
//		System.out.println("2."+curWagen[1] +" < "+typ.minEinsatzfahrzeug+(curWagen[1] < typ.minEinsatzfahrzeug));
//		System.out.println("3. "+mit.size() +" < "+typ.minMitarbeiter+(mit.size() < typ.minMitarbeiter));
//		System.out.println("4 "+mit.size()+">"+(curFahr[0] * 2 + curFahr[1] * 4 + curFahr[2] * 14 + curFahr[3] * 2)+" => "+(mit.size() > (curFahr[0] * 2 + curFahr[1] * 4 + curFahr[2] * 14 + curFahr[3] * 2)));
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
	 * @param index Der index der position in der Liste
	 */
	public void beendeEinsatz(int index) {
		
		einsaetze.get(index).einsatzBeenden();
		einsaetze.remove(index);
		
	}
}
