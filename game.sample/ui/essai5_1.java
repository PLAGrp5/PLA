package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class essai5_1 extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image background;
    private essai5_2 mp;
    private ActionListener al;
    private Box b;
    private final String MENU[] = {"New Game", "Continue", "High Scores", "Exit"};
    public essai5_1(String name, String imagename)
    {
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex)
        {
        }
        mp = new essai5_2(imagename);
        mp.setBounds(0, 0, 700, 600);
        getContentPane().add(mp);
        setSize(700, 600);
    }
    public essai5_1(String name, ActionListener al)
    {
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex)
        {
        }
        mp = new essai5_2();
        mp.setBounds(0, 0, 700, 600);
        getContentPane().add(mp);
        for(int i = 0; i < MENU.length; i++)
        {
            JButton jb = new JButton(MENU[i]);
            this.add(jb);
            jb.addActionListener(al);
            jb.setBounds(250, 400 + 32 * i, 200, 30);
            mp.add(jb);
        }
        setSize(700, 600);
        setVisible(true);
    }
    public essai5_1(String name)
    {
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        mp = new essai5_2();
        mp.setBounds(0, 0, 700, 600);
        getContentPane().add(mp);
        setSize(700, 600);
    }
    public void setActionListener(ActionListener al)
    {
        for(int i = 0; i < MENU.length; i++)
        {
            JButton jb = new JButton(MENU[i]);
            this.add(jb);
            jb.addActionListener(al);
            jb.setBounds(250, 400 + 32 * i, 200, 30);
            mp.add(jb);
        }
        setVisible(true);
    }
    public essai5_1(String name, String imagename, ActionListener al)
    {
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        mp = new essai5_2(imagename);
        mp.setBounds(0, 0, 700, 600);
        getContentPane().add(mp);
        for(int i = 0; i < MENU.length; i++)
        {
            JButton jb = new JButton(MENU[i]);
            this.add(jb);
            jb.addActionListener(al);
            jb.setBounds(250, 400 + 32 * i, 200, 30);
            mp.add(jb);
        }
        setSize(700, 600);
        setVisible(true);
    }
}