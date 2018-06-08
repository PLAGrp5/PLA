package ui;

import java.awt.*;
import javax.swing.*;
public class essai5_2 extends JPanel
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imgName;
    public essai5_2()
    {
        super();
        setLayout(null);
        imgName = "";
        setSize(700, 600);
        setVisible(true);
    }
    public essai5_2(String image)
    {
        super();
        setLayout(null);
        setSize(700, 600);
        imgName = image;
        setVisible(true);
    }
    public void paintComponent(Graphics g)
    {
        if(!imgName.equals(""))
        {
            try 
            {
                MediaTracker mt = new MediaTracker (this);
                Image intro = Toolkit.getDefaultToolkit().getImage(imgName);
                mt.addImage(intro, 0);
                mt.waitForID(0);
                g.drawImage(intro, 0, 0, this);
            }
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
    }
}