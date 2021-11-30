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
	private final EinsatzTyp wohungsbrand = new EinsatzTyp(22, 1, 2, 1, 1);
	private final EinsatzTyp verkehrsunfall = new EinsatzTyp(16, 1, 1, 1, 0);
	private final EinsatzTyp naturkatastrophe = new EinsatzTyp(55, 3, 3, 3, 2);
	private final EinsatzTyp industrieunfall = new EinsatzTyp(40, 3, 2, 2, 2);
	
	/**
	 * Der kontruktor der Klasse um Fahrzeuge und Mitarbeiter zu initialisieren.
	 */
	public Feuerwache() {
		for (int i = 0; i < 18; i++) {
			switch (i) {
			case 0,1,2,3:
				fahrzeuge.add(new Einsatzfahrzeug(4, FahrzeugTyp.PKW, false, FahrzeugStatus.Bereit, "EI-" + i, "Einsatzleiter"));
				break;
			case 4,5,6,7,8:
				fahrzeuge.add(new Leiterwagen(2, FahrzeugTyp.LKW, false, FahrzeugStatus.Bereit, "LT-" + i, 8));
				break;
			case 9,10,11,12:
				fahrzeuge.add(new Mannschftstransporter(14, FahrzeugTyp.LKW, false, FahrzeugStatus.Bereit, "MT-" + i, 1990+i));
				break;
			case 13,14,15,16,17:
				fahrzeuge.add(new TankLoeschfahrzeug(4, FahrzeugTyp.LKW, false, FahrzeugStatus.Bereit, "TL-" + i, 50));
				break;
			}
		}
		for (int i = 0; i < 80; i++) {
			mitarbeiter.add(new Feuerwehrmensch(MitarbeiterStatus.Bereit, "Dummy " + i, i<10 ? FahrzeugTyp.LKW : FahrzeugTyp.PKW));
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
	
	public boolean startEinsatz(String einsatz, ArrayList<Fahrzeug> fahrzeuge, ArrayList<Feuerwehrmensch> mitarbeiter) {
		switch (einsatz) {
		case "Wohnungsbrand":
			if(checkEinsatzAnforderung(wohungsbrand, fahrzeuge, mitarbeiter))
				return false;
			einsaetze.add(new Einsatz(wohungsbrand, fahrzeuge, mitarbeiter));
			return true;
		case "Verkehrsunfall":
			if(checkEinsatzAnforderung(verkehrsunfall, fahrzeuge, mitarbeiter))
				return false;
			einsaetze.add(new Einsatz(verkehrsunfall, fahrzeuge, mitarbeiter));
			return true;
		case "Naturkatastrophe":
			if(checkEinsatzAnforderung(naturkatastrophe, fahrzeuge, mitarbeiter))
				return false;
			einsaetze.add(new Einsatz(naturkatastrophe, fahrzeuge, mitarbeiter));
			return true;
		case "Industrieunfall":
			if(checkEinsatzAnforderung(industrieunfall, fahrzeuge, mitarbeiter))
				return false;
			einsaetze.add(new Einsatz(industrieunfall, fahrzeuge, mitarbeiter));
			return true;

		default:
			return false;
		}
	}

	private boolean checkEinsatzAnforderung(EinsatzTyp typ, ArrayList<Fahrzeug> fahrzeuge, ArrayList<Feuerwehrmensch> mitarbeiter) {
		return false;
	}
	// TODO: methode in bool und Einsatz aufteilen um null zu verhindern
	public Einsatz getVorschlag(String einsatz){
		EinsatzTyp typ = switch (einsatz){
		case "Wohnungsbrand" -> wohungsbrand;
		case "Verkehrsunfall" -> verkehrsunfall;
		case "Naturkatastrophe" -> naturkatastrophe;
		case "Industrieunfall" -> industrieunfall;
		default -> wohungsbrand;
		};
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
				case "Einsatzfahrzeug":
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
			return null;
			
		ArrayList<Feuerwehrmensch> mit = new ArrayList<>();
		int minLKW = typ.minTankLoeschfahrzeug + typ.minManschaftstransporter + typ.minLeiterwagen;
		/*
		 * 0 -> LKW
		 * 1 -> PWK
		 */
		int[] curWagen = new int[2];
		for (Feuerwehrmensch mensch : mitarbeiter) {
			if(mensch.getMitarbeiterStatus() == MitarbeiterStatus.Bereit) {
				switch (mensch.getFahrerlaubnis()) {
				case LKW:
					if(curWagen[0] < minLKW) {
						curWagen[0]++;
						mit.add(mensch);
						break;
					}
				case PKW:
					if(curWagen[1] < typ.minEinsatzfahrzeug) {
						curWagen[1]++;
						mit.add(mensch);
					}
					break;

				default:
					break;
				}
			}
		}
		if(curWagen[0] < minLKW
			|| curWagen[1] < typ.minEinsatzfahrzeug)
			return null;
		
		return new Einsatz(typ, fahr, mit);		
	}
	
	public void beendeEinsatz(int index) {
		einsaetze.get(index).beendeEinsatz();
		einsaetze.remove(index);
	}
}
