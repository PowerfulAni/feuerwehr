package main;

import gui.Gui;

public class Main {
	public static void main(String[] args) {
		Feuerwache fw = new Feuerwache();
		Gui feuerwehrApp = new Gui(fw);
		feuerwehrApp.setResizable(false);
		feuerwehrApp.setVisible(true);
		
		//Debug
		
		
	}
}
