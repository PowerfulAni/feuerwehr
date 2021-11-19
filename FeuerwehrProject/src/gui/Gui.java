package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
 

public class Gui extends JFrame implements ActionListener {
	
	JButton buttonFunktion1;
    JButton buttonFunktion2;
    JButton buttonFunktion3;
    JButton buttonFunktion4;
    JButton buttonSubmit;
    JButton buttonCancel;
    JLabel status;
    JLabel mainContent;
    JPanel panelButtons;
    JPanel panelMain;
    JPanel panelStatus;

    public Gui() {
    	
    	this.setTitle("Feuerwehr Management Tool by <#ToDo Cooler Name hier einfügen>");
        this.setSize(800, 600);
        this.setLayout(new FlowLayout());
        panelButtons = new JPanel();
        panelMain = new JPanel();
        panelStatus = new JPanel();
        panelButtons.setPreferredSize(new Dimension(700, 40));
        panelMain.setPreferredSize(new Dimension(700, 420));
        panelStatus.setPreferredSize(new Dimension(700, 40));
        
        panelMain.setBorder(new CompoundBorder(panelStatus.getBorder(), new LineBorder(Color.gray, 1)));
        
        panelStatus.setBorder(new CompoundBorder(panelStatus.getBorder(), new LineBorder(Color.gray, 1)));
        // Leeres JLabel-Objekt wird erzeugt
        status = new JLabel("", SwingConstants.CENTER);
        status.setFont(new Font("Arial", Font.BOLD, 20));
        String content = """
        		<html>
        		<body>
        		<h1>Feuerwache Rödermark</h1>
        		<table>
        		<tr><th>Fahrzeug</th><th>Kennzeichen</th><th>Status</th></tr>
        		<tr><td>Löschfahrzeug</td><td>WI-FI 42</td><td>Bereit</td></tr>
        		<tr><td>Löschfahrzeug</td><td>WI-FI 43</td><td>Bereit</td></tr>
        		<tr><td>Löschfahrzeug</td><td>WI-FI 44</td><td>Bereit</td></tr>
        		<tr><td>Löschfahrzeug</td><td>WI-FI 45</td><td>In Reparatur</td></tr>
        		</table>
        		<hr>
        		<table>
        		<tr><th>Name</th><th>Rang</th><th>Status</th></tr>
        		<tr><td>Reiner Zufall</td><td>Leiter</td><td>Bereit</td></tr>
        		</table>
        		</body>
        		</html>"
        				""";
        mainContent = new JLabel(content, SwingConstants.LEFT);
        //Buttons werden erstellt
        buttonFunktion1 = new JButton("BTN - Funktion 1");
        buttonFunktion2 = new JButton ("BTN - Funktion 2");
        buttonFunktion3 = new JButton ("BTN - Funktion 3");
        buttonFunktion4 = new JButton ("BTN - Funktion 4");
        buttonSubmit = new JButton ("BTN - Bestätigen");
        buttonCancel = new JButton ("BTN - Abbrechen");
 
        //Buttons werden dem Listener zugeordnet
        buttonFunktion1.addActionListener(this);
        buttonFunktion2.addActionListener(this);
        buttonFunktion3.addActionListener(this);
        buttonFunktion4.addActionListener(this);
        buttonSubmit.addActionListener(this);
        buttonCancel.addActionListener(this);
 
        //Buttons werden dem JPanel hinzugefügt
        panelButtons.add(buttonFunktion1);
        panelButtons.add(buttonFunktion2);
        panelButtons.add(buttonFunktion3);
        panelButtons.add(buttonFunktion4);
        //panel.add(buttonSubmit);
        //panel.add(buttonCancel);
 
        //JLabel wird dem Panel hinzugefügt
        panelMain.add(mainContent);
        
        panelStatus.add(status);
        this.add(panelButtons);
        this.add(panelMain);
       this.add(panelStatus);
    }
	public static void main(String[] args) {
		// Ein neues Objekt der Klasse BeispielListener wird erzeugt
        // und sichtbar gemacht
		Gui feuerwehrApp = new Gui();
		feuerwehrApp.setResizable(false);
		feuerwehrApp.setVisible(true);

	}
	public void actionPerformed (ActionEvent ae){
        // Die Quelle wird mit getSource() abgefragt und mit den
        // Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
        // der Buttons ist, wird der Text des JLabels entsprechend geändert
        if(ae.getSource() == this.buttonFunktion1){
        	//Hier Funktionen für Button 1 #ToDo
        	status.setText(("Button 1 wurde betätigt"));
        }
        else if(ae.getSource() == this.buttonFunktion2){
        	//Hier Funktionen für Button 2 #ToDo
        	status.setText("Button 2 wurde betätigt");
        }
        else if (ae.getSource() == this.buttonFunktion3){
        	//Hier Funktionen für Button 3 #ToDo
        	status.setText(("Button 3 wurde betätigt"));
        }
        else if (ae.getSource() == this.buttonFunktion4){
        	//Hier Funktionen für Button 4 #ToDo
        	status.setText(("Button 4 wurde betätigt"));
        }
        else if (ae.getSource() == this.buttonSubmit){
        	//Hier Funktionen für Button Submit #ToDo
        	status.setText(("Button 3 wurde betätigt"));
        }
        else if (ae.getSource() == this.buttonCancel){
        	//Hier Funktionen für Button Cancel #ToDo
        	status.setText(("Button 3 wurde betätigt"));
        }
    }

}
