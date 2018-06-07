package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import framework.GameModel;
import framework.WindowListener;

public class Fenetre extends JFrame implements ActionListener {

	  private JButton bouton = new JButton("Mon bouton");
	  boolean appui = false;
	  private JPanel jpanel = new JPanel(); // Panel du jeu
	  private JPanel jpanelbouton = new JPanel(); // Panel du bouton
	  private JFrame jframe = new JFrame(); // Frame du jeu
	  
	  
	  
	  public Fenetre(JFrame jf, JPanel panel){
		  
		  this.jpanel = panel;
		  this.jframe = jf;
	  }
	  
	  
	  public void TestButton() {
		  
		  
		  jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
	   //Ajout du bouton à notre content pane
		    
		bouton.addActionListener(this); // Quand clic bouton
		  
	    this.jpanelbouton.add(this.bouton);
	    
	    this.jframe.add(this.jpanelbouton);
	    
	   // panel.setVisible(true);																																
	    
	   this.jframe.setVisible(true);
	  //  System.out.println(appui);
	    
	   while (appui != true) { };  
	    //this.jpanel.doLayout(); // Panel du jeu
	  //  this.jframe.setVisible(true);
	   jframe.setContentPane(jpanel);
       jframe.revalidate();
       jpanel.setVisible(true);
		 jframe.setVisible(true);
}
	     
	  
		public void actionPerformed (ActionEvent e){
			 System.out.println("lol");
			 appui = true;
			 jpanel.doLayout();
		       
			 Object o = e.getSource();
			 if (o == bouton) {
					// new WindowFrame();
			 }
			 dispose();
		}
		/*
	    public void changerMenu(){
	    	System.out.println("testMenu");
	    	this.jpanel.doLayout();
	        this.setContentPane(this.jpanel);
	        this.revalidate();
	    }
	    //Ecouteur de ton bouton
	    public class EcouteurBoutonChanger implements ActionListener{
	        public void actionPerformed(ActionEvent clic) {
	            //Appelle la méthode de changement de panel
	        	System.out.println("Test Clic");
	        	Fenetre.this.changerMenu();
	        }
	    }
	 */   
}
