package framework;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
<<<<<<< HEAD
import java.awt.Image;
=======
>>>>>>> master
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import framework.GameUI.STATE;
import ui.*;

public class GameOver {

	JFrame overFrame;
	JPanel controlPanel;
	GameUI g_ui;

	public GameOver (GameUI g) {
		g_ui = g;
		prepareGUI();
	}
	
	public void prepareGUI() {
		overFrame = new JFrame("Game Over");
<<<<<<< HEAD
		overFrame.setSize(1024, 1024);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		overFrame.setLocation(dim.width / 2 - overFrame.getSize().width / 2,
				dim.height / 2 - overFrame.getSize().height / 2);
		overFrame.setIconImage(new ImageIcon("game.sample/sprites/image.png").getImage());
=======
		overFrame.setSize(1447, 1024);
		overFrame.setLayout(new GridLayout(3, 1));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		overFrame.setLocation(dim.width/2-overFrame.getSize().width/2, dim.height/2-overFrame.getSize().height/2);

		headerLabel = new JLabel("", JLabel.CENTER);
>>>>>>> master

		overFrame.addWindowListener(new WindowAdapter() {
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
					@Override
					protected void paintComponent(Graphics g) {
						super.paintComponent(g);
						g.drawImage(image, 0, 0, null);
					}
				};
		controlPanel.setLayout(new GridLayout(4,1));

		overFrame.setContentPane(controlPanel);
		overFrame.setVisible(true);
	}
	
	public void showEvent() {
<<<<<<< HEAD
		MyButton MenuButton = new MyButton("MENU", "game.sample/sprites/bleu.jpg", "game.sample/sprites/rouge.png");
		MenuButton.setActionCommand("MENU");
		
		JLabel label = new JLabel(); // Création d'un JLabel contenant le logo du jeu
		label.setIcon(new ImageIcon("game.sample/sprites/GameOver.png")); // Image du logo
		// Logo créé sur le site web https://cooltext.com
		// Il est écrit que l'on peut utiliser les images créées sur leur site.
		label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); // Centrer le logo
		
		MenuButton.addActionListener(new ButtonClickListener());
		MenuButton.setPreferredSize(new Dimension(250, 100));
		
=======
		headerLabel.setText("Game Over");

		JButton ExitButton = new JButton("MENU");

		Model mod = (Model)g_ui.m_model;
		int scorebleu = mod.m.scorebleu();
		int scorerouge = mod.m.scorerouge();
		if(scorebleu>scorerouge) {
			headerLabel.setText("Bleu gagne la partie      Bleu : "+scorebleu+" / Rouge : "+scorerouge);

		}
		else if (scorerouge>scorebleu){
			headerLabel.setText("Rouge gagne la partie      Bleu : "+scorebleu+" / Rouge : "+scorerouge);

		}
		else {
			headerLabel.setText("Egalité      Bleu : "+scorebleu+" / Rouge : "+scorerouge);

		}
		ExitButton.setActionCommand("EXIT");
>>>>>>> master
		
		JPanel panelBouton = new JPanel();
		panelBouton.setOpaque(false);
		panelBouton.add(MenuButton);
		
		controlPanel.add(label);
		controlPanel.add(new JLabel());
		controlPanel.add(new JLabel());
		controlPanel.add(panelBouton);
		
		overFrame.setVisible(true);
	}
	
	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			if (command.equals("MENU")) {
				g_ui.setState(STATE.Menu);
				Dimension d = new Dimension(1024, 1024);
				overFrame.dispose();
				g_ui.createWindow(d);
			}
		}
	}	
}