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

import main.Feuerwache;
 

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
    
    Feuerwache feuerwache;

    public Gui(Feuerwache fw) {
    	feuerwache = fw;
    	
    	this.setTitle("Feuerwehr Management Tool by <#ToDo Cooler Name hier einsetzen>");
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
        
        mainContent = new JLabel("", SwingConstants.LEFT);
        setContent(generateMainHtml());
        //Buttons werden erstellt
        buttonFunktion1 = new JButton("Fahrzeuge");
        buttonFunktion2 = new JButton ("Feuerwehrleute");
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
	/*public static void main(String[] args) {
		// Ein neues Objekt der Klasse BeispielListener wird erzeugt
        // und sichtbar gemacht
		Gui feuerwehrApp = new Gui();
		feuerwehrApp.setResizable(false);
		feuerwehrApp.setVisible(true);

	}*/
	
	public void setContent(String content) {
		mainContent.setText(content);
	}
	
	public String generateMainHtml() {
		String content = """
        		<html>
        		<body>
        		<h1>Feuerwache R&ouml;dermark</h1>
        		<hr>
        		<table>
        		<tr><th>#</th><th>Verfügbar</th><th>Im Einsatz</th><th>Nicht Verfügbar</th><th>Gesamt</th></tr>
        		<tr><td>Fahrzeuge</td><td>15</td><td>0</td><td>3</td><td>18</td></tr>
        		<tr><td>Feuerwehrleute</td><td>70</td><td>0</td><td>10</td><td>80</td></tr>
        		</table>
        		</body>
        		</html>"
        				""";
		return content;
	}
	public String generateFahrzeugeListeHtml() {
		return "";
	}
	public String generateFeuerwehrleuteListeHtml() {
		return "";
	}
	public String generateEinsaetzeListeHtml() {
		return "";
	}
	public void actionPerformed (ActionEvent ae){
        // Die Quelle wird mit getSource() abgefragt und mit den
        // Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
        // der Buttons ist, wird der Text des JLabels entsprechend geändert
        if(ae.getSource() == this.buttonFunktion1){
        	//Hier Funktionen für Button 1 #ToDo
        	status.setText(("<html>Button 1 wurde bet&auml;tigt</html>"));
        	setContent("""
            		<html>
            		<body>
            		<h1>Feuerwache R&ouml;dermark</h1>
            		<table>
            		<tr><th>Fahrzeug</th><th>Kennzeichen</th><th>Status</th></tr>
            		<tr><td>L&ouml;schfahrzeug</td><td>WI-FI 42</td><td>Bereit</td></tr>
            		</table>
            		<hr>
            		<table>
            		<tr><th>Name</th><th>Rang</th><th>Status</th></tr>
            		<tr><td>Reiner Zufall</td><td>Leiter</td><td>Bereit</td></tr>
            		</table>
            		</body>
            		</html>"
            				""");
        }
        else if(ae.getSource() == this.buttonFunktion2){
        	//Hier Funktionen für Button 2 #ToDo
        	status.setText("<html>Button 2 wurde bet&auml;tigt</html>");
        }
        else if (ae.getSource() == this.buttonFunktion3){
        	//Hier Funktionen für Button 3 #ToDo
        	status.setText(("<html>Button 3 wurde bet&auml;tigt</html>"));
        }
        else if (ae.getSource() == this.buttonFunktion4){
        	//Hier Funktionen für Button 4 #ToDo
        	status.setText(("<html>Button 4 wurde bet&auml;tigt</html>"));
        }
        else if (ae.getSource() == this.buttonSubmit){
        	//Hier Funktionen für Button Submit #ToDo
        	status.setText(("<html>Button 3 wurde bet&auml;tigt</html>"));
        }
        else if (ae.getSource() == this.buttonCancel){
        	//Hier Funktionen für Button Cancel #ToDo
        	status.setText(("<p>Button 3 wurde bet&auml;tigt</p>"));
        }
    }

}
