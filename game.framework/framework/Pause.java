package framework;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import framework.GameUI.STATE;

import framework.Compteur;

public class Pause {

	JFrame pauseFrame;
	JLabel headerLabel;
	JPanel controlPanel;
	GameUI g_ui;
	GameController m_controller;
	public Pause(GameUI g) {
		g_ui = g;
		prepareGUI();
	}

	private void prepareGUI() {
		pauseFrame = new JFrame("Gitank pause");
		pauseFrame.setSize(256, 450);
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
		
		JPanel pantext = new JPanel(new BorderLayout());
		JLabel lab = new JLabel("Temps en ms entre");
		JLabel lab1 = new JLabel("  deux transitions");
		JLabel lab2 = new JLabel("   d'un automate :");
		pantext.add(lab, BorderLayout.NORTH);
		pantext.add(lab1, BorderLayout.CENTER);
		pantext.add(lab2, BorderLayout.SOUTH);
		
		JPanel pan = new JPanel();
		JSlider slide = new JSlider();
	    slide.setMaximum(500);
	    slide.setMinimum(100);
	    slide.setValue((int)g_ui.set_refresh);
	    slide.setPaintTicks(true);
	    slide.setPaintLabels(true);
	    slide.setMinorTickSpacing(100);
	    slide.setMajorTickSpacing(200);
	    slide.addChangeListener(new ChangeListener(){
	        public void stateChanged(ChangeEvent event){
	        	g_ui.set_refresh = ((JSlider)event.getSource()).getValue();
	        }

	      });      
	    pan.add(slide);
	    
	    controlPanel.add(pantext);
	    controlPanel.add(pan);

		pauseFrame.add(headerLabel);
		pauseFrame.add(controlPanel);
		pauseFrame.setVisible(true);
	}

	public void showEvent() {
		headerLabel.setFont(new Font("TimesRoman ",Font.BOLD,30));
		headerLabel.setText("Pause");
		MyButton ExitButton = new MyButton("EXIT", "game.sample/sprites/bleu.jpg", "game.sample/sprites/rouge.png");
		MyButton ResumeButton = new MyButton("RESUME", "game.sample/sprites/bleu.jpg", "game.sample/sprites/rouge.png");
		JButton SoundButton = new JButton(new ImageIcon("game.sample/sprites/iconsound.png"));

		ExitButton.setActionCommand("EXIT");
		ResumeButton.setActionCommand("RESUME");
		SoundButton.setActionCommand("SOUND");

		ExitButton.setPreferredSize(new Dimension(100, 30));
		ResumeButton.setPreferredSize(new Dimension(100, 30));
		SoundButton.setPreferredSize(new Dimension(80,50)); 

		ExitButton.addActionListener(new ButtonClickListener());
		ResumeButton.addActionListener(new ButtonClickListener());
		SoundButton.addActionListener(new ButtonClickListener());

		controlPanel.add(ExitButton);
		controlPanel.add(ResumeButton);
		controlPanel.add(SoundButton);

		pauseFrame.setVisible(true);
	}

	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			if (command.equals("EXIT")) {
				// Gestion de la confirmation d'erreur
				int option = JOptionPane.showConfirmDialog(pauseFrame.getContentPane(), "Êtes-vous sûr ?", "Quitter ?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
						new ImageIcon("game.sample/sprites/image.png"));
				if (option == JOptionPane.YES_OPTION) {
					g_ui.setState(STATE.Over);
					Dimension d = new Dimension(1024, 1024);
					g_ui.m_frame.dispose();
					pauseFrame.dispose();
					g_ui.createWindow(d);
				}
			} else if (command.equals("RESUME")) {
				g_ui.setState(STATE.Game);
				pauseFrame.dispose();
				g_ui.resumeTimer();
				g_ui.temps_de_pause += System.currentTimeMillis() - g_ui.m_start;
			} else if (command.equals("SOUND")) {
				Compteur.compteur++; // Le compteur permet de savoir si il faut relancer la musique ou l'arreter.
				if (Compteur.compteur%2==0)
					g_ui.musicStart();
				else if (Compteur.compteur%2==1)
					g_ui.musicStop();
			}
		}
	}
}