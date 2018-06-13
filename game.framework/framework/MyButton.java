package framework;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;


// Classe trouvée sur Internet permettant de faire un bouton personnalisé
public class MyButton extends JButton {
	private static final long serialVersionUID = 1L;

	public MyButton(String txt, String icon, String iconHover) {
		super(txt);
		this.setForeground(Color.WHITE);
		this.setOpaque(false);
		this.setContentAreaFilled(false); // On met à false pour empêcher le composant de peindre l'intérieur du JButton.
		this.setBorderPainted(false); // De même, on ne veut pas afficher les bordures.
		this.setFocusPainted(false); // On n'affiche pas l'effet de focus.
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setIcon(new ImageIcon(icon));
		this.setRolloverIcon(new ImageIcon(iconHover));
	}
}