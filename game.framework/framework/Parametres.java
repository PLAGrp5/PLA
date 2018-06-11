package framework;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import framework.GameUI.STATE;

public class Parametres {

	JFrame paramFrame;
	JLabel headerLabel;
	JLabel sb1_1;
	JLabel sb1_2;
	JLabel sb2_1;
	JLabel sb2_2;
	JLabel map;
	JPanel Sbire_1;
	JPanel Sbire_2;
	JPanel Carte;
	JPanel controlPanel;
	GameUI g_ui;
	final JFileChooser fc1_1 = new JFileChooser();
	final JFileChooser fc1_2 = new JFileChooser();
	final JFileChooser fc2_1 = new JFileChooser();
	final JFileChooser fc2_2 = new JFileChooser();
	final JFileChooser fcc = new JFileChooser();
	File sbire1_1 = new File("DefaultAut.txt");
	File sbire1_2 = new File("DefaultAut.txt");
	File sbire2_1 = new File("DefaultAut.txt");
	File sbire2_2 = new File("DefaultAut.txt");
	File carte = new File("game.sample/onscreen/map_test.txt");
	

	public Parametres(GameUI g) {
		g_ui = g;
		prepareGUI();
	}

	private void prepareGUI() {
		paramFrame = new JFrame("Gitank param");
		paramFrame.setSize(1447, 1024);
		paramFrame.setIconImage(new ImageIcon("game.sample/sprites/image.png").getImage());
		paramFrame.setLayout(new GridLayout(4,3));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		paramFrame.setLocation(dim.width/2-paramFrame.getSize().width/2, dim.height/2-paramFrame.getSize().height/2);

		headerLabel = new JLabel("", JLabel.CENTER);

		paramFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		Sbire_1 = new JPanel();
		Sbire_1.setLayout(new BoxLayout(Sbire_1, BoxLayout.X_AXIS));
		Sbire_2 = new JPanel();
		Sbire_2.setLayout(new BoxLayout(Sbire_2, BoxLayout.X_AXIS));
		Carte = new JPanel();
		Carte.setLayout(new BoxLayout(Carte, BoxLayout.X_AXIS));
		
		sb1_1 = new JLabel("Sbire 1_1 : " + sbire1_1.getName(), JLabel.CENTER);
		sb1_2 = new JLabel("Sbire 1_2 : " + sbire1_2.getName(), JLabel.CENTER);
		sb2_1 = new JLabel("Sbire 2_1 : " + sbire2_1.getName(), JLabel.CENTER);
		sb2_2 = new JLabel("Sbire 2_2 : " + sbire2_2.getName(), JLabel.CENTER);
		map = new JLabel("Carte : " + carte.getName(), JLabel.CENTER);
		JLabel f = new JLabel("",JLabel.CENTER);
		JLabel g = new JLabel("",JLabel.CENTER);
		
		paramFrame.add(f);
		paramFrame.add(headerLabel);
		paramFrame.add(g);
		paramFrame.add(sb1_1);
		paramFrame.add(Sbire_1);
		paramFrame.add(sb2_1);
		paramFrame.add(sb1_2);
		paramFrame.add(Sbire_2);
		paramFrame.add(sb2_2);
		paramFrame.add(map);
		paramFrame.add(Carte);
		paramFrame.add(controlPanel);
		
		paramFrame.setVisible(true);
	}

	public void showEvent() {
		headerLabel.setText("Choix des Automates et de la Carte");

		JButton Sb1_1Button = new JButton("Choisir 1_1");
		JButton Sb1_2Button = new JButton("Choisir 1_2");
		JButton Sb2_1Button = new JButton("Choisir 2_1");
		JButton Sb2_2Button = new JButton("Choisir 2_2");
		JButton CarteButton = new JButton("Choisir carte");
		JButton ExitButton = new JButton("EXIT");
		JLabel a = new JLabel("                                                                 ");
		JLabel b = new JLabel("                                                                 ");
		JLabel c = new JLabel("                                                                 ");

		Sb1_1Button.setActionCommand("SB1_1");
		Sb1_2Button.setActionCommand("SB1_2");
		Sb2_1Button.setActionCommand("SB2_1");
		Sb2_2Button.setActionCommand("SB2_2");
		CarteButton.setActionCommand("CARTE");
		ExitButton.setActionCommand("EXIT");

		Sb1_1Button.addActionListener(new ButtonClickListener());
		Sb1_2Button.addActionListener(new ButtonClickListener());
		Sb2_1Button.addActionListener(new ButtonClickListener());
		Sb2_2Button.addActionListener(new ButtonClickListener());
		CarteButton.addActionListener(new ButtonClickListener());
		ExitButton.addActionListener(new ButtonClickListener());

		Sbire_1.add(Sb1_1Button);
		Sbire_1.add(a);
		Sbire_2.add(Sb1_2Button);
		Sbire_1.add(Sb2_1Button);
		Sbire_2.add(b);
		Sbire_2.add(Sb2_2Button);
		Carte.add(CarteButton);
		controlPanel.add(c);
		controlPanel.add(ExitButton);

		paramFrame.setVisible(true);
	}

	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			if (command.equals("SB1_1")) {
				int returnVal = fc1_1.showOpenDialog(paramFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					sbire1_1 = fc1_1.getSelectedFile();
					g_ui.sb1_1 = sbire1_1;
				}
					sb1_1.setText("Sbire 1_1 : " + sbire1_1.getName());
			} else if (command.equals("SB1_2")) {
				int returnVal = fc1_2.showOpenDialog(paramFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					sbire1_2 = fc1_2.getSelectedFile();
					g_ui.sb1_2 = sbire1_2;
				}
					sb1_2.setText("Sbire 1_2 : " + sbire1_2.getName());
			} else if (command.equals("SB2_1")) {
				int returnVal = fc2_1.showOpenDialog(paramFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					sbire2_1 = fc2_1.getSelectedFile();
					g_ui.sb2_1 = sbire2_1;
				}
					sb2_1.setText("Sbire 2_1 : " + sbire2_1.getName());
			} else if (command.equals("SB2_2")) {
				int returnVal = fc2_2.showOpenDialog(paramFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					sbire2_2 = fc2_2.getSelectedFile();
					g_ui.sb2_2 = sbire2_2;
				}
					sb2_2.setText("Sbire 2_2 : " + sbire2_2.getName());
			} else if (command.equals("CARTE")) {
				int returnVal = fcc.showOpenDialog(paramFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					carte = fcc.getSelectedFile();
					g_ui.map = carte;
				}
					map.setText("Carte : " + carte.getName());
			}else if (command.equals("EXIT")) {
				g_ui.setState(STATE.Menu);
				Dimension d = new Dimension(1024, 1024);
				g_ui.createWindow(d);
				paramFrame.dispose();
			}
		}

	}
	
}
