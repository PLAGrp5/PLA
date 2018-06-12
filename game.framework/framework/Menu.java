package framework;

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
	JPanel controlPanel;
	GameUI g_ui;
	final JFileChooser fc = new JFileChooser();

	public Menu(GameUI g) {
		g_ui = g;
		prepareGUI();
	}

	private void prepareGUI() {
		menuFrame = new JFrame();
		menuFrame.setTitle("Gitank Menu");
		menuFrame.setSize(1024, 1024);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		menuFrame.setLocation(dim.width / 2 - menuFrame.getSize().width / 2,
				dim.height / 2 - menuFrame.getSize().height / 2);
		menuFrame.setIconImage(new ImageIcon("game.sample/sprites/image.png").getImage());
		menuFrame.setVisible(true);

		menuFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		// Gestion image de fond
		ImageIcon imageicon = new ImageIcon("game.sample/sprites/landscape.jpg");
		// L'image de fond est issue du site https://pixabay.com qui est une banque
		// d'images libre de droits
		Image image = imageicon.getImage();
		controlPanel = new JPanel() {            
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		@Override
		protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};

		GridLayout grid = new GridLayout(5, 1);
		grid.setVgap(20); // Espacement entre deux élément du layout
		controlPanel.setLayout(grid);

		menuFrame.setContentPane(controlPanel);
	}

	public void showEvent() {

		MyButton StartButton = new MyButton("START", "game.sample/sprites/bleu.jpg", "game.sample/sprites/rouge.png");
		MyButton HelpButton = new MyButton("HELP", "game.sample/sprites/bleu.jpg", "game.sample/sprites/rouge.png");
		MyButton ExitButton = new MyButton("EXIT", "game.sample/sprites/bleu.jpg", "game.sample/sprites/rouge.png");
		MyButton OpenButton = new MyButton("OPEN", "game.sample/sprites/bleu.jpg", "game.sample/sprites/rouge.png");
		MyButton CreditButton = new MyButton("CREDIT", "game.sample/sprites/bleu.jpg", "game.sample/sprites/rouge.png");


		StartButton.setActionCommand("START");
		HelpButton.setActionCommand("HELP");
		ExitButton.setActionCommand("EXIT");
		OpenButton.setActionCommand("OPEN");
		CreditButton.setActionCommand("CREDIT");

		StartButton.addActionListener(new ButtonClickListener());
		HelpButton.addActionListener(new ButtonClickListener());
		ExitButton.addActionListener(new ButtonClickListener());
		OpenButton.addActionListener(new ButtonClickListener());
		CreditButton.addActionListener(new ButtonClickListener());

		StartButton.setPreferredSize(new Dimension(350, 100));
		HelpButton.setPreferredSize(new Dimension(350, 100));
		OpenButton.setPreferredSize(new Dimension(350, 100));
		ExitButton.setPreferredSize(new Dimension(350, 100));
		CreditButton.setPreferredSize(new Dimension(350, 100));

		JLabel label = new JLabel(); // Création d'un JLabel contenant le logo du jeu
		label.setIcon(new ImageIcon("game.sample/sprites/gitank.png")); // Image du logo
		// Logo créé sur le site web https://cooltext.com
		// Il est écrit que l'on peut utiliser les images créées sur leur site.
		label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); // Centrer le logo

		// On est obligé de créer un label pour chaque bouton afin de pouvoir les
		// redimensionner

		JPanel bouton1 = new JPanel();
		bouton1.setOpaque(false);
		bouton1.add(StartButton);

		JPanel bouton2 = new JPanel();
		bouton2.setOpaque(false);
		bouton2.add(OpenButton);

		JPanel bouton3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
		bouton3.setOpaque(false);
		bouton3.add(HelpButton);
		bouton3.add(CreditButton);

		JPanel bouton4 = new JPanel();
		bouton4.setOpaque(false);
		bouton4.add(ExitButton);

		controlPanel.add(label);
		controlPanel.add(bouton1);
		controlPanel.add(bouton2);
		controlPanel.add(bouton3);
		controlPanel.add(bouton4);
		/*
		 * controlPanel.add(label); controlPanel.add(StartButton);
		 * controlPanel.add(HelpButton); controlPanel.add(OpenButton);
		 * controlPanel.add(ExitButton);
		 */
		menuFrame.setVisible(true);
	}

	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			if (command.equals("START")) {
				g_ui.setState(STATE.Game);
				Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
			    Dimension d = new Dimension(tailleMoniteur.height * 1384/1033, 1033);
				g_ui.createWindow(d);
				g_ui.createTimer();
				menuFrame.dispose();
			} else if (command.equals("HELP")) {
				g_ui.setState(STATE.Help);
				Dimension d = new Dimension(1447, 1033);
				g_ui.createWindow(d);
				menuFrame.dispose();
			} else if (command.equals("CREDIT")) {
				g_ui.setState(STATE.Credit);
				Dimension d = new Dimension(1447, 1033);
				g_ui.createWindow(d);
				menuFrame.dispose();
			} else if (command.equals("EXIT")) {
				System.exit(0);
			} else if (command.equals("OPEN")) {
				g_ui.setState(STATE.Param);
				Dimension d = new Dimension(1447, 1033);
				menuFrame.dispose();
				g_ui.createWindow(d);
			}
		}

	}

}