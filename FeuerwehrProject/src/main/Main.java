package main;

import gui.Gui;
import util.FahrzeugStatus;

public class Main {
	public static void main(String[] args) {
		Feuerwache fw = new Feuerwache();
		System.out.println(fw.getFahrzeuge());
		System.out.println(fw.getMitarbeiter());
		System.out.println(fw.getAbsFahrzeugStatus(new FahrzeugStatus[] {FahrzeugStatus.Bereit, FahrzeugStatus.Wartung}));
		Gui feuerwehrApp = new Gui(fw);
		feuerwehrApp.setResizable(false);
		feuerwehrApp.setVisible(true);
	}
}
