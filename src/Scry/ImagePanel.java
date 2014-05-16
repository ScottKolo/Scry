/**
 * 
 */
package Scry;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.*;

/**
 * @author Scott Kolodziej
 *
 */
public class ImagePanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 506100311225598413L;
	Image image;
	
	public ImagePanel()
	{
		
	}
	
	public ImagePanel(Image i)
	{
		setImage(i);
	}
	
	public void setImage(Image i)
	{
		image = i;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(image, 0, 0, this);
	}
}
