package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EssaiConcluant {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	final JFileChooser fc = new JFileChooser();

	public EssaiConcluant() {
		prepareGUI();
	}

	public static void main(String[] args) {
		EssaiConcluant swingControlDemo = new EssaiConcluant();
		swingControlDemo.showEventDemo();
	}

	private void prepareGUI() {
		mainFrame = new JFrame("Java SWING Examples");
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(4, 1));

		headerLabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);
		statusLabel.setSize(350, 100);

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}

	private void showEventDemo() {
		headerLabel.setText("Gitank");

		JButton StartButton = new JButton("START");
		JButton HelpButton = new JButton("HELP");
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

		controlPanel.add(StartButton);
		controlPanel.add(HelpButton);
		controlPanel.add(ExitButton);
		controlPanel.add(OpenButton);

		mainFrame.setVisible(true);
	}

	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			if (command.equals("START")) {
				statusLabel.setText("Game started !");
			} else if (command.equals("HELP")) {
				statusLabel.setText("Kill your ennemy");
			} else if (command.equals("EXIT")) {
				System.exit(0);
			} else if (command.equals("OPEN")) {
				int returnVal = fc.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					java.io.File file = fc.getSelectedFile();
					statusLabel.setText("File Selected :" + file.getName());
				} else {
					statusLabel.setText("Open command cancelled by user.");
				}

			}
		}

	}
}