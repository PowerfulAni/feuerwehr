package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import fahrzeuge.*;
import menschen.*;
import util.*;

public class Feuerwache {
	private ArrayList<Fahrzeug> fahrzeuge = new ArrayList<>();
	private ArrayList<Feuerwehrmensch> mitarbeiter = new ArrayList<>();
	public Feuerwache() {
		Random rnd = new Random();
		for (int i = 0; i < 18; i++) {
			switch (rnd.nextInt(4)) {
			case 0:
				fahrzeuge.add(new Einsatzfahrzeug(4, FahrzeugTyp.PKW, false, FahrzeugStatus.Bereit, "EI-" + i, "Einsatzleiter"));
				break;
			case 1:
				fahrzeuge.add(new Leiterwagen(2, FahrzeugTyp.PKW, false, FahrzeugStatus.Wartung, "LT-" + i, 8));
				break;
			case 2:
				fahrzeuge.add(new Mannschftstransporter(8, FahrzeugTyp.PKW, false, FahrzeugStatus.Einsatz, "MT-" + i, 1990+i));
				break;
			case 3:
				fahrzeuge.add(new TankLoeschfahrzeug(2, FahrzeugTyp.PKW, false, FahrzeugStatus.Bereit, "TL-" + i, 50));
				break;
			}
		}
		for (int i = 0; i < 80; i++) {
			mitarbeiter.add(new Feuerwehrmensch(MitarbeiterStatus.Bereit, "Dummy " + i, rnd.nextBoolean() ? FahrzeugTyp.LKW : FahrzeugTyp.PKW));
		}
	}
	
	public ArrayList<Fahrzeug> getFahrzeuge(){
		return fahrzeuge;
	}
	public ArrayList<Feuerwehrmensch> getMitarbeiter(){
		return mitarbeiter;
	}
	
	public int getAbsFahrzeugStatus(FahrzeugStatus[] status) {
		int sum = 0;
		for (int i = 0; i < fahrzeuge.size(); i++) {
			for (int j = 0; j < status.length; j++) {
				if(fahrzeuge.get(i).getFahrzeugStatus() == status[j]) {
					sum++;
					continue;
				}
			}
		}
		return sum;
	}
	
	public int getAbsMitarbeiterStatus(MitarbeiterStatus[] status) {
		int sum = 0;
		for (int i = 0; i < mitarbeiter.size(); i++) {
			for (int j = 0; j < status.length; j++) {
				if(mitarbeiter.get(i).getMitarbeiterStatus() == status[j]) {
					sum++;
					continue;
				}
			}
		}
		return sum;
	}
	
}
