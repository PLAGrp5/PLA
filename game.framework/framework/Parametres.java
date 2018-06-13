package framework;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Parser.Ast;
import Parser.AutomataParser;
import Parser.ParseException;
import automate.Automate;
import framework.GameUI.STATE;
import ui.Model;

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
	
	final JFileChooser fcc = new JFileChooser();
	String sbire1_1 = "data/automates/sb1_1.txt";
	String sbire1_2 = "data/automates/sb1_2.txt";
	String sbire2_1 = "data/automates/sb2_1.txt";
	String sbire2_2 = "data/automates/sb2_2.txt";
	File fsb1_1 = new File("data/automates/sb1_1.txt");
	File fsb1_2 = new File("data/automates/sb1_2.txt");
	File fsb2_1 = new File("data/automates/sb2_1.txt");
	File fsb2_2 = new File("data/automates/sb2_2.txt");
	File carte = new File("data/cartes/map_test.txt");

	public Parametres(GameUI g) {
		g_ui = g;
	}
	
	public static String fromFile(String fp) {
		File f = null;
		Scanner scan = null;
		String res = "";
		try {
			f = new File(fp);
			scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
			System.exit(0);
		}
		while (scan.hasNextLine()) {
			res = scan.nextLine();
		}
		return res;		
	}

	private void prepareGUI() {
		paramFrame = new JFrame("Gitank paramètres");
		paramFrame.setSize(1447, 900);
		paramFrame.setIconImage(new ImageIcon("game.sample/sprites/image.png").getImage());
		paramFrame.setLayout(new GridLayout(4, 3));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		paramFrame.setLocation(dim.width / 2 - paramFrame.getSize().width / 2,
				dim.height / 2 - paramFrame.getSize().height / 2);

		headerLabel = new JLabel("", JLabel.CENTER);

		paramFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		fcc.setCurrentDirectory(new File("data/cartes/"));

		controlPanel = new JPanel();
		controlPanel.setOpaque(false);
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		Sbire_1 = new JPanel();
		Sbire_1.setOpaque(false);
		Sbire_1.setLayout(new GridLayout(1, 2));

		Sbire_2 = new JPanel();
		Sbire_2.setOpaque(false);
		Sbire_2.setLayout(new GridLayout(1, 2));

		Carte = new JPanel();
		Carte.setOpaque(false);
		Carte.setLayout(new GridLayout(1, 2));

		Font font = new Font("Arial", Font.BOLD, 25); // Choix police plus taille

		sb1_1 = new JLabel("Sbire 1_1 : " + fromFile(sbire1_1), JLabel.CENTER);
		sb1_1.setFont(font);
		sb1_2 = new JLabel("Sbire 1_2 : " + fromFile(sbire1_2), JLabel.CENTER);
		sb1_2.setFont(font);
		sb2_1 = new JLabel("Sbire 2_1 : " + fromFile(sbire2_1), JLabel.CENTER);
		sb2_1.setFont(font);
		sb2_2 = new JLabel("Sbire 2_2 : " + fromFile(sbire2_2), JLabel.CENTER);
		sb2_2.setFont(font);
		map = new JLabel("Carte : " + carte.getName(), JLabel.CENTER);
		map.setFont(font);
		JLabel f = new JLabel("", JLabel.CENTER);
		JLabel g = new JLabel("", JLabel.CENTER);

		// Gestion image de fond
		ImageIcon imageicon = new ImageIcon("game.sample/sprites/landscape.jpg");
		// L'image de fond est issue du site https://pixabay.com qui est une banque
		// d'images libre de droits
		Image image = imageicon.getImage();

		JPanel paramPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};

		paramPanel.setLayout(new GridLayout(4, 3));
		paramPanel.add(f);
		paramPanel.add(headerLabel);
		paramPanel.add(g);
		paramPanel.add(sb1_1);
		paramPanel.add(Sbire_1);
		paramPanel.add(sb2_1);
		paramPanel.add(sb1_2);
		paramPanel.add(Sbire_2);
		paramPanel.add(sb2_2);
		paramPanel.add(map);
		paramPanel.add(Carte);
		paramPanel.add(controlPanel);

		paramFrame.setContentPane(paramPanel);
		paramFrame.setVisible(true);
	}

	public void showEvent() {
		prepareGUI();
		headerLabel.setText("Choix des Automates");
		headerLabel.setFont(new Font("Arial", Font.BOLD, 30));

		MyButton CarteButton = new MyButton("Carte", "game.sample/sprites/bleu.jpg", "game.sample/sprites/rouge.png");
		MyButton ExitButton = new MyButton("EXIT", "game.sample/sprites/bleu.jpg", "game.sample/sprites/rouge.png");

		CarteButton.setActionCommand("CARTE");
		ExitButton.setActionCommand("EXIT");

		CarteButton.addActionListener(new ButtonClickListener());
		ExitButton.addActionListener(new ButtonClickListener());

		CarteButton.setPreferredSize(new Dimension(120, 30));
		ExitButton.setPreferredSize(new Dimension(250, 100));

		Ast a;
		try {
			a = new AutomataParser(new BufferedReader(new FileReader("game.parser/example/automata.txt"))).Run();
		} catch (FileNotFoundException | ParseException e) {
			e.printStackTrace();
			return;
		}
		Automate[] automates = (Automate[]) a.make();
		
		String[] ListAut = Model.getList(automates);
		JComboBox<String> ScrollAut1_1 = new JComboBox<String>(ListAut);
		ScrollAut1_1.setSelectedItem(fromFile(sbire1_1));
		JComboBox<String> ScrollAut1_2 = new JComboBox<String>(ListAut);
		ScrollAut1_2.setSelectedItem(fromFile(sbire1_2));
		JComboBox<String> ScrollAut2_1 = new JComboBox<String>(ListAut);
		ScrollAut2_1.setSelectedItem(fromFile(sbire2_1));
		JComboBox<String> ScrollAut2_2 = new JComboBox<String>(ListAut);
		ScrollAut2_2.setSelectedItem(fromFile(sbire2_2));

		ScrollAut1_1.setActionCommand("SB1_1");
		ScrollAut1_2.setActionCommand("SB1_2");
		ScrollAut2_1.setActionCommand("SB2_1");
		ScrollAut2_2.setActionCommand("SB2_2");

		ScrollAut1_1.addActionListener(new ButtonClickListener());
		ScrollAut1_2.addActionListener(new ButtonClickListener());
		ScrollAut2_1.addActionListener(new ButtonClickListener());
		ScrollAut2_2.addActionListener(new ButtonClickListener());

		JPanel bouton1 = new JPanel(new GridBagLayout());
		bouton1.setOpaque(false);
		bouton1.add(ScrollAut1_1);

		JPanel bouton2 = new JPanel(new GridBagLayout());
		bouton2.setOpaque(false);
		bouton2.add(ScrollAut1_2);

		JPanel bouton3 = new JPanel(new GridBagLayout());
		bouton3.setOpaque(false);
		bouton3.add(ScrollAut2_1);

		JPanel bouton4 = new JPanel(new GridBagLayout());
		bouton4.setOpaque(false);
		bouton4.add(ScrollAut2_2);

		JPanel bouton5 = new JPanel(new GridBagLayout());
		bouton5.setOpaque(false);
		bouton5.add(CarteButton);

		JPanel bouton6 = new JPanel(new GridBagLayout());
		bouton6.setOpaque(false);
		bouton6.add(ExitButton);

		// Panel vide pour combler trou
		JPanel test = new JPanel(new GridLayout());
		test.setOpaque(false);

		Sbire_1.add(bouton1);
		Sbire_2.add(bouton2);
		Sbire_1.add(bouton3);
		Sbire_2.add(bouton4);
		Carte.add(bouton5);
		Carte.add(test);
		controlPanel.add(bouton6);

		paramFrame.setVisible(true);
	}
	
	public static void toFile(String s, File f) throws IOException {
	    FileWriter fileWriter = new FileWriter(f);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.print(s);
	    printWriter.close();
	}

	private class ButtonClickListener implements ActionListener {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			if (command.equals("SB1_1")) {
				JComboBox<String> cb1_1 = (JComboBox<String>) e.getSource();
				sbire1_1 = (String) cb1_1.getSelectedItem();
				try {
					toFile(sbire1_1, fsb1_1);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				sb1_1.setText("Sbire 1_1 : " + sbire1_1);
			} else if (command.equals("SB1_2")) {
				JComboBox<String> cb1_2 = (JComboBox<String>) e.getSource();
				sbire1_2 = (String) cb1_2.getSelectedItem();
				try {
					toFile(sbire1_2, fsb1_2);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				sb1_2.setText("Sbire 1_2 : " + sbire1_2);
			} else if (command.equals("SB2_1")) {
				JComboBox<String> cb2_1 = (JComboBox<String>) e.getSource();
				sbire2_1 = (String) cb2_1.getSelectedItem();
				try {
					toFile(sbire2_1, fsb2_1);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				sb2_1.setText("Sbire 2_1 : " + sbire2_1);
			} else if (command.equals("SB2_2")) {
				JComboBox<String> cb2_2 = (JComboBox<String>) e.getSource();
				sbire2_2 = (String) cb2_2.getSelectedItem();
				try {
					toFile(sbire2_2, fsb2_2);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				sb2_2.setText("Sbire 2_2 : " + sbire2_2);

			} else if (command.equals("CARTE")) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers .txt", "txt", "text"); // Filtre TXT
				fcc.setFileFilter(filter); // Ajout d'un choix : .txt dans le file chooser
				fcc.setAcceptAllFileFilterUsed(false); // ne pas autoriser tout type de fichiers dans le file chooser
				int returnVal = fcc.showOpenDialog(paramFrame); 
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					carte = fcc.getSelectedFile();
					g_ui.map = carte;
				}
				map.setText("Carte : " + carte.getName());
			} else if (command.equals("EXIT")) {
				g_ui.setState(STATE.Menu);
				Dimension d = new Dimension(1024, 1024);
				g_ui.createWindow(d);
				paramFrame.dispose();
			}
		}

	}

}
