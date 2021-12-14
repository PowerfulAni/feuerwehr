package main;

import gui.Gui;
/**
 * Start Punkt des Programms
 */
public class Main {
	/**
	 * Wird nur zum Starten verwendet
	 * @param args
	 */
	public static void main(String[] args) {
		Feuerwache fw = new Feuerwache();
		Gui feuerwehrApp = new Gui(fw);
		feuerwehrApp.setResizable(false);
		feuerwehrApp.setVisible(true);
		//Debug
	}
}
