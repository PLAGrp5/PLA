package framework;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import framework.GameUI.STATE;

public class Pause {

	JFrame pauseFrame;
	JLabel headerLabel;
	JPanel controlPanel;
	GameUI g_ui;

	public Pause(GameUI g) {
		g_ui = g;
		prepareGUI();
	}

	private void prepareGUI() {
		pauseFrame = new JFrame("Gitank pause");
		pauseFrame.setSize(256, 128);
		pauseFrame.setLayout(new GridLayout(2, 1));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		pauseFrame.setLocation(dim.width / 2 - pauseFrame.getSize().width / 2,
				dim.height / 2 - pauseFrame.getSize().height / 2);

		headerLabel = new JLabel("", JLabel.CENTER);

		pauseFrame.addWindowListener(new WindowAdapter() {
			// Lorsque le joueur appuie sur la croix, cela relance le jeu
			public void windowClosing(WindowEvent windowEvent) { 
				g_ui.setState(STATE.Game);
				pauseFrame.dispose();
				g_ui.resumeTimer();
				g_ui.temps_de_pause += System.currentTimeMillis() - g_ui.m_start;
			}
		});

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		pauseFrame.add(headerLabel);
		pauseFrame.add(controlPanel);
		pauseFrame.setVisible(true);
	}

	public void showEvent() {
		headerLabel.setText("Pause");
		MyButton ExitButton = new MyButton("EXIT", "game.sample/sprites/bleu.jpg", "game.sample/sprites/rouge.png");
		MyButton ResumeButton = new MyButton("RESUME", "game.sample/sprites/bleu.jpg", "game.sample/sprites/rouge.png");
		ExitButton.setActionCommand("EXIT");
		ResumeButton.setActionCommand("RESUME");

		ExitButton.setPreferredSize(new Dimension(100, 30));
		ResumeButton.setPreferredSize(new Dimension(100, 30));

		ExitButton.addActionListener(new ButtonClickListener());
		ResumeButton.addActionListener(new ButtonClickListener());

		controlPanel.add(ExitButton);
		controlPanel.add(ResumeButton);

		pauseFrame.setVisible(true);
	}

	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			if (command.equals("EXIT")) {
				// Gestion de la confirmation d'erreur
				int option = JOptionPane.showConfirmDialog(pauseFrame.getContentPane(), "Êtes-vous sûr ?", "Quitter ?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("game.sample/sprites/image.png"));
				if (option == JOptionPane.YES_OPTION) { // Si on a cliqué sur oui, alors on fait le if, sinon on le fait pas
					g_ui.setState(STATE.Over);
					Dimension d = new Dimension(1024, 1024);
					pauseFrame.dispose();
					g_ui.m_frame.dispose();
					g_ui.createWindow(d);
				}
			} else if (command.equals("RESUME")) {
				g_ui.setState(STATE.Game);
				pauseFrame.dispose();
				g_ui.resumeTimer();
				g_ui.temps_de_pause += System.currentTimeMillis() - g_ui.m_start;
			}
		}
	}
}