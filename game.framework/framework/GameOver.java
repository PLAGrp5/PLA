package framework;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import framework.GameUI.STATE;
import ui.*;

public class GameOver {

	JFrame overFrame;
	JLabel headerLabel;
	JPanel controlPanel;
	GameUI g_ui;

	public GameOver (GameUI g) {
		g_ui = g;
		prepareGUI();
	}
	
	public void prepareGUI() {
		overFrame = new JFrame("Game Over");
		overFrame.setSize(1447, 1024);
		overFrame.setLayout(new GridLayout(3, 1));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		overFrame.setLocation(dim.width/2-overFrame.getSize().width/2, dim.height/2-overFrame.getSize().height/2);

		headerLabel = new JLabel("", JLabel.CENTER);

		overFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		overFrame.add(headerLabel);
		overFrame.add(controlPanel);
		overFrame.setVisible(true);
	}
	
	public void showEvent() {
		headerLabel.setText("Game Over");

		JButton ExitButton = new JButton("MENU");

		Model mod = (Model)g_ui.m_model;
		int scorebleu = mod.m_Map.scorebleu();
		int scorerouge = mod.m_Map.scorerouge();
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
		
		ExitButton.addActionListener(new ButtonClickListener());
		
		controlPanel.add(ExitButton);
		
		overFrame.setVisible(true);
	}
	
	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			if (command.equals("EXIT")) {
				g_ui.setState(STATE.Menu);
				Dimension d = new Dimension(1024, 1024);
				overFrame.dispose();
				g_ui.createWindow(d);
			}
		}
	}	
}