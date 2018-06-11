package framework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
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

	public GameOver(GameUI g) {
		g_ui = g;
		prepareGUI();
	}

	public void prepareGUI() {
		overFrame = new JFrame("Game Over");
		overFrame.setSize(900, 900);
		overFrame.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		overFrame.setLocation(dim.width / 2 - overFrame.getSize().width / 2,
				dim.height / 2 - overFrame.getSize().height / 2);
		overFrame.setIconImage(new ImageIcon("game.sample/sprites/image.png").getImage());

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
		controlPanel.setLayout(new GridLayout(5, 1));

		overFrame.setContentPane(controlPanel);
		overFrame.setVisible(true);
	}

	public void showEvent() {
		MyButton MenuButton = new MyButton("MENU", "game.sample/sprites/bleu.jpg", "game.sample/sprites/rouge.png");
		MenuButton.setActionCommand("MENU");

		JLabel label = new JLabel(); // Création d'un JLabel contenant le logo du jeu
		label.setIcon(new ImageIcon("game.sample/sprites/GameOver.png")); // Image du logo
		// Logo créé sur le site web https://cooltext.com
		// Il est écrit que l'on peut utiliser les images créées sur leur site.
		label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); // Centrer le logo

		MenuButton.addActionListener(new ButtonClickListener());
		MenuButton.setPreferredSize(new Dimension(250, 100));

		Model mod = (Model) g_ui.m_model;

		int scorebleu = mod.m_Map.scorebleu(); // Score du joueur bleu
		int scorerouge = mod.m_Map.scorerouge(); // Score du joueur rouge
		
		JLabel gagnant = new JLabel();
		JLabel score = new JLabel();
		JLabel raison = new JLabel();
		gagnant.setHorizontalAlignment(JLabel.CENTER); // Centrer le JLabel sur la Frame
		score.setHorizontalAlignment(JLabel.CENTER); // Centrer le JLabel sur la Frame
		raison.setHorizontalAlignment(JLabel.CENTER); // Centrer le JLabel sur la Frame
		Font font = new Font("Arial", Font.BOLD, 30); // Choix police plus taille
		Font font_raison = new Font("Arial", Font.BOLD, 50); // Choix police plus taille
		
		score.setFont(font); // On attribue la police au JLabel
		raison.setFont(font_raison); // On attribue la police au JLabel
		//raison.setForeground(Color.GRAY); // Choix couleur JLabel
		gagnant.setFont(font);

// Il peut y avoir deux façons de gagner et il faut trouver laquelle  a terminé la partie
		int parcourstank = 0;

		boolean raisontrouve = false; // On cherche la raison de fin de partie
		while (parcourstank < mod.ntank) {
			if (mod.tanks[parcourstank].vie == 0) {
				raisontrouve = true; // Raison trouvee
				raison.setText("Tank KO");
				if (mod.tanks[parcourstank].m_tank == Color.cyan) {
					gagnant.setText("Rouge gagne la partie");

				} else {
					gagnant.setText("Bleu gagne la partie");

				}
			}
			parcourstank++;
		}
		score.setText("Bleu : " + scorebleu + " / Rouge : " + scorerouge);

		
		if (!raisontrouve) { // Si la raison n'est toujours pas trouvée :


			raison.setText("Fin du temps");
			if (scorebleu > scorerouge) {
				gagnant.setText("Bleu gagne la partie");
				score.setText(
						"Bleu : " + scorebleu + " / Rouge : " + scorerouge);

			} else if (scorerouge > scorebleu) {
				gagnant.setText("Rouge gagne la partie");
				score.setText("Bleu : " + scorebleu + " / Rouge : " + scorerouge);

			} else {
				gagnant.setText("Égalité");
				score.setText("Bleu : " + scorebleu + " / Rouge : " + scorerouge);

			}
		}

		JPanel panelBouton = new JPanel(); // Création d'un JPanel stockant le bouton
		panelBouton.setOpaque(false);
		panelBouton.add(MenuButton);

		// Ajout des éléments swing dans notre JPanel qui fait la taille de la JFrame
		controlPanel.add(label);
		controlPanel.add(raison);
		controlPanel.add(gagnant);
		controlPanel.add(score);
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