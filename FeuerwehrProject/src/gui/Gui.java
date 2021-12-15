package gui;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.HyperlinkListener;

import einsaetze.Einsatz;
import fahrzeuge.EinsatzLeitfahrzeug;
import fahrzeuge.Leiterwagen;
import fahrzeuge.Mannschaftstransporter;
import fahrzeuge.TankLoeschfahrzeug;
import main.Feuerwache;
import util.FahrzeugStatus;
import util.FahrzeugTyp;

public class Gui extends JFrame {

	JFrame frame;
	JLabel status;
	JPanel panelButtons;
	JTextPane panelMain;
	JPanel panelStatus;
	int aktuellerView;

	Feuerwache feuerwache;

	public Gui(Feuerwache feuerwache) {
		this.feuerwache = feuerwache;

		this.setTitle("Feuerwehr Management Tool by FireByter");
		this.setSize(800, 600);
		this.setLayout(new FlowLayout());
		
		   ImageIcon icon = new ImageIcon("/gui/logo.png");
		    this.setIconImage(icon.getImage());

		panelMain = new JTextPane();
		panelStatus = new JPanel();

		panelMain.setEditorKit(new javax.swing.text.html.HTMLEditorKit());
		panelMain.setPreferredSize(new Dimension(600, 420));
		panelMain.setEditable(false);
		panelMain.setAutoscrolls(true);

		panelStatus.setPreferredSize(new Dimension(700, 40));
		panelStatus.setBorder(new CompoundBorder(panelStatus.getBorder(), new LineBorder(Color.gray, 1)));
		// Leeres JLabel-Objekt wird erzeugt
		status = new JLabel("", SwingConstants.CENTER);
		status.setFont(new Font("Arial", Font.BOLD, 20));
		showMain();
	}

	public void showMain() {
		aktuellerView = 0;
		this.getContentPane().removeAll();
		removeListener(panelMain);
		this.add(new ButtonView(this));
		this.add(new MainView(panelMain, feuerwache));
		JButton alarm = new JButton("Alarm");
		alarm.setPreferredSize(new Dimension(700, 60));
		//this.add(alarm);
		this.revalidate();
		this.getContentPane().repaint();
	}

	public void showFahrzeuge() {
		aktuellerView = 1;
		this.getContentPane().removeAll();
		removeListener(panelMain);
		this.add(new ButtonView(this));
		this.add(new FahrzeugView(panelMain, feuerwache));
		//this.add(new FahrzeugZusatz(panelMain, feuerwache));
		this.revalidate();
		this.getContentPane().repaint();
	}

	public void showFeuerwehrleute() {
		aktuellerView = 2;
		this.getContentPane().removeAll();
		removeListener(panelMain);
		this.add(new ButtonView(this));
		this.add(new FeuerwehrleuteView(panelMain, feuerwache));
		//this.add(new FeuerwehrleuteZusatz(panelMain, feuerwache));
		this.revalidate();
		this.getContentPane().repaint();
	}

	public void showEinsaetze() {
		aktuellerView = 3;
		this.getContentPane().removeAll();
		removeListener(panelMain);
		this.add(new ButtonView(this));
		this.add(new EinsaetzeView(this, panelMain, feuerwache));
		this.add(new EinsaetzeZusatz(this, panelMain, feuerwache));
		this.revalidate();
		this.getContentPane().repaint();
	}
	
	public void showEinsatzdetails(int id) {
		aktuellerView = 3;
		this.getContentPane().removeAll();
		removeListener(panelMain);
		this.add(new ButtonView(this));
		this.add(new EinsatzDetails(this, panelMain, feuerwache, id));
		this.revalidate();
		this.getContentPane().repaint();
	}
	
	public void showVorschlag(Einsatz vorschlag) {
		aktuellerView = 3;
		this.getContentPane().removeAll();
		removeListener(panelMain);
		this.add(new ButtonView(this));
		this.add(new EinsatzVorschlag(this, panelMain, feuerwache, vorschlag));
		this.revalidate();
		this.getContentPane().repaint();
	}
	
	private void removeListener(JTextPane panelMain) {
		
		for (HyperlinkListener listener : panelMain.getHyperlinkListeners()) {
			panelMain.removeHyperlinkListener(listener);
		}
		
	}
	
	public void refreshView() {
		switch (aktuellerView) {
		case 0:
			showMain();
			break;
		case 1:
			showFahrzeuge();
			break;
		case 2:
			showFeuerwehrleute();
			break;
		case 3:
			showEinsaetze();
			break;
		}
	}

}
