/**
 * 
 */
package Scry;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

/**
 * @author Scott Kolodziej
 *
 */
public class ScryCore
{
	BufferedImage image;
	Container container;
	
	public ScryCore(Container c)
	{
		container = c;
		
		if(c instanceof JFrame)
			((JFrame)container).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GraphicsConfiguration gc = (new JFrame()).getGraphicsConfiguration();
		Rectangle bounds = gc.getBounds();
		
		container.setSize(800,638);
		container.setLocation(bounds.width/2-300,bounds.height/2-319);
		//for(int j = 0; j < 15; j++){
		// Start up the Ray Tracer
		RayTracer tracer = new RayTracer(600, (int)(6f/8f*600));
		
		Scene s = new Scene();
		s.addObject(new Sphere(new Point( 0.0f,  0.0f, 30f), new Color(200, 200, 200), 2, 0.2f, 0.5f, 0.7f, 0.3f));
		s.addObject(new Sphere(new Point( 0.0f,  5.0f, 30f), new Color(255,   0,   0), 2, 0.2f, 0.5f, 0.7f, 0.3f));
		s.addObject(new Sphere(new Point( 4.3f, -2.5f, 30f), new Color(0,     0, 255), 2, 0.2f, 0.5f, 0.7f, 0.3f));
		s.addObject(new Sphere(new Point(-4.3f, -2.5f, 30f), new Color(0,   255,   0), 2, 0.2f, 0.5f, 0.7f, 0.3f));
		s.addObject(new Sphere(new Point( 0.0f, -5.0f, 30f), new Color(0,   255, 255), 2, 0.2f, 0.5f, 0.7f, 0.3f));
		s.addObject(new Sphere(new Point( 4.3f,  2.5f, 30f), new Color(255,   0, 255), 2, 0.2f, 1.0f, 0.7f, 0.3f));
		s.addObject(new Sphere(new Point(-4.3f,  2.5f, 30f), new Color(255, 255,   0), 2, 0.2f, 1.0f, 0.7f, 0.3f));
		
		//s.addObject(new Sphere(-1, -1, 5, 1, new Color(0, 255, 255)));
		//s.addObject(new Sphere(-2, 2, 8, 1, new Color(255, 0, 0)));
		//s.addObject(new Sphere(new Point(-2, -0.5f, 20), new Color(0, 0, 0), 1.5f, 0.3f, 0.2f, 0.0f, 0.3f));
		//s.addObject(new Sphere(new Point(2, -0.5f, 20), new Color(0, 0, 0), 1.5f, 0.3f, 0.2f, 0.0f, 0.3f));
		//s.addObject(new Plane(0f, 1f, 0f, -6f, 0.3f, 0.2f, 0.4f, 0.3f, new Color(0.0f, 0.0f, 0.0f)));
		s.setAmbientLight(new Color(1.0f, 1.0f, 1.0f));
		s.setBackgroundColor(new Color(0.6f, 0.6f, 0.6f));
		s.addLight(new Light(-5, 10, 24));
		s.addLight(new Light(5, 10, 24));
		tracer.setScene(s);
		
		ImagePanel ip = new ImagePanel();
		BufferedImage image = tracer.generateImage();
		ip.setImage(image);
		
		container.add(ip);
		
		container.setVisible(true);
		
		try {
			ImageIO.write(image, "png", new File("test.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//}
	}
}
