package framework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import framework.GameUI.*;

public class Menu {

	JFrame menuFrame;
	JLabel headerLabel;
	JPanel controlPanel;
	GameUI g_ui;
	final JFileChooser fc = new JFileChooser();
	ImageIcon imageicon = new ImageIcon("game.sample/sprites/fond.jpg");
	Image image = imageicon.getImage();
	public Menu(GameUI g) {
		g_ui = g;
		prepareGUI();
	}

	private void prepareGUI()  {
		menuFrame = new JFrame();
		menuFrame.setTitle("Gitank Menu");
		GridLayout grid = new GridLayout(5, 1);
		grid.setVgap(20); // Espacement entre deux élément du layout
		menuFrame.setSize(1024, 1024);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		menuFrame.setLocation(dim.width/2-menuFrame.getSize().width/2, dim.height/2-menuFrame.getSize().height/2);
		menuFrame.setIconImage(new ImageIcon("game.sample/sprites/image.png").getImage());
		menuFrame.setVisible(true);
		

		menuFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});


		controlPanel = new JPanel() {            
			@Override
            protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, null);
        }
    };
    
		controlPanel.setLayout(grid);

		menuFrame.setContentPane(controlPanel);
	}

	public void showEvent() {

		JButton StartButton = new JButton("START");
		JButton HelpButton = new JButton("Règles et commandes");
		JButton ExitButton = new JButton("EXIT");
		JButton OpenButton = new JButton("OPEN");

		StartButton.setActionCommand("START");
		HelpButton.setActionCommand("HELP");
		ExitButton.setActionCommand("EXIT");
		OpenButton.setActionCommand("OPEN");

		StartButton.addActionListener(new ButtonClickListener());
		HelpButton.addActionListener(new ButtonClickListener());
		ExitButton.addActionListener(new ButtonClickListener());
		OpenButton.addActionListener(new ButtonClickListener());
		
		StartButton.setPreferredSize(new Dimension(350, 100));
		HelpButton.setPreferredSize(new Dimension(350, 100));
		OpenButton.setPreferredSize(new Dimension(350, 100));
		ExitButton.setPreferredSize(new Dimension(350, 100));
		
		JLabel label = new JLabel(); // Création d'un JLabel contenant le logo du jeu
		label.setIcon(new ImageIcon("game.sample/sprites/logo.png")); // Image du logo	
		label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); // Centrer le logo
		
		// On est obligé de créer un label pour chaque bouton afin de pouvoir les redimensionner
		
		JPanel bouton1 = new JPanel();
		bouton1.setOpaque(false);
		bouton1.add(StartButton);
		JPanel bouton2 = new JPanel();
		bouton2.setOpaque(false);
		bouton2.add(HelpButton);
		JPanel bouton3 = new JPanel();
		bouton3.setOpaque(false);
		bouton3.add(OpenButton);
		JPanel bouton4 = new JPanel();
		bouton4.setOpaque(false);
		bouton4.add(ExitButton);
		
		controlPanel.add(label);
		controlPanel.add(bouton1);
		controlPanel.add(bouton2);
		controlPanel.add(bouton3);
		controlPanel.add(bouton4);
	/*	
		controlPanel.add(label);
		controlPanel.add(StartButton);
		controlPanel.add(HelpButton);
		controlPanel.add(OpenButton);
		controlPanel.add(ExitButton);
*/
		menuFrame.setVisible(true);
	}

	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			if (command.equals("START")) {
				g_ui.setState(STATE.Game);
				Dimension d = new Dimension(1447, 1024);
				g_ui.createWindow(d);
				g_ui.createTimer();
				menuFrame.dispose();
			} else if (command.equals("HELP")) {
				g_ui.setState(STATE.Help);
				Dimension d = new Dimension(1447, 1024);
				g_ui.createWindow(d);
				g_ui.createTimer();
				menuFrame.dispose();
			} else if (command.equals("EXIT")) {
				System.exit(0);
			} else if (command.equals("OPEN")) {
				int returnVal = fc.showOpenDialog(menuFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					java.io.File file = fc.getSelectedFile();
					
				} else {

				}
			}
		}

	}

}