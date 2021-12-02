package gui;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import main.Feuerwache;

public class Gui extends JFrame {

	JFrame frame;
	JLabel status;
	JPanel panelButtons;
	JTextPane panelMain;
	JPanel panelStatus;

	Feuerwache feuerwache;

	public Gui(Feuerwache feuerwache) {
		this.feuerwache = feuerwache;

		this.setTitle("Feuerwehr Management Tool by <#ToDo Cooler Name hier einfügen>");
		this.setSize(800, 600);
		this.setLayout(new FlowLayout());

		panelMain = new JTextPane();
		panelStatus = new JPanel();

		panelMain.setEditorKit(new javax.swing.text.html.HTMLEditorKit());
		panelMain.setPreferredSize(new Dimension(600, 420));
		panelMain.setAutoscrolls(true);

		panelStatus.setPreferredSize(new Dimension(700, 40));
		panelStatus.setBorder(new CompoundBorder(panelStatus.getBorder(), new LineBorder(Color.gray, 1)));
		// Leeres JLabel-Objekt wird erzeugt
		status = new JLabel("", SwingConstants.CENTER);
		status.setFont(new Font("Arial", Font.BOLD, 20));
		showMain();
	}

	public void showMain() {
		this.getContentPane().removeAll();
		this.add(new ButtonView(this));
		this.add(new MainView(panelMain, feuerwache));
		JButton alarm = new JButton("Alarm");
		alarm.setPreferredSize(new Dimension(700, 60));
		this.add(alarm);
		this.revalidate();
	}

	public void showFahrzeuge() {
		this.getContentPane().removeAll();
		this.add(new ButtonView(this));
		this.add(new FahrzeugView(panelMain, feuerwache));
		this.add(new FahrzeugZusatz(panelMain, feuerwache));
		this.revalidate();
	}

	public void showFeuerwehrleute() {
		this.getContentPane().removeAll();
		this.add(new ButtonView(this));
		this.add(new FeuerwehrleuteView(panelMain, feuerwache));
		this.add(new FeuerwehrleuteZusatz(panelMain, feuerwache));
		this.revalidate();
	}

	public void showEinsaetze() {
		this.getContentPane().removeAll();
		this.add(new ButtonView(this));
		this.add(new EinsaetzeView(panelMain, feuerwache));
		this.add(new EinsaetzeZusatz(panelMain, feuerwache));
		this.revalidate();
	}

}
