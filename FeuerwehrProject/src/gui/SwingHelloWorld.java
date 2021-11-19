package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class SwingHelloWorld {
//    public static void main(String[] args) {
//
//        JFrame frame = new JFrame("Feuerwehr Management Tool by 'Cooler Name #ToDo'");
//        JButton button1 = new JButton("Button 1");
//        JButton button2 = new JButton("Button 2");
//        // Frame definieren
//        frame.setMinimumSize(new Dimension(800, 600));
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        button1.setBounds(100, 100, 100, 80);
//        // Mittigen Text erstellen
//        JLabel lblText = new JLabel("Hello World!", SwingConstants.CENTER);
//
//        // Text zu Panel hinzufügen
//        frame.getContentPane().add(lblText);
//        
//        // Frame anzeigen
//        frame.pack();
//        frame.add(button1);
//        frame.setVisible(true);
//
//    }
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
             
            @Override
            public void run() {
                JFrame frame = new JFrame("Feuerwehr Management Tool by 'Cooler Name #ToDo'");
                frame.setSize(800,600);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 
                JButton button = new JButton("Testbutton");
                //Explicitly set the size to what you want
                button.setPreferredSize(new Dimension(150, 50));
                //Content pane default layout is BorderLayout which does NOT
                //Respect the preferred sizes. Set it to FlowLayout which does
                frame.getContentPane().setLayout(new FlowLayout());
                frame.getContentPane().add(button);
                 
                //Finally display the frame
                frame.setVisible(true);
            }
        });
    }
	
	
	
	public String test() {
		return "Test erfolgreich";
	}
}