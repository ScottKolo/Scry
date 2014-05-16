/**
 * 
 */
package Scry;

import java.awt.Color;

/**
 * @author Scott Kolodziej
 *
 */
public class Light
{
	Point location;
	Color color;
	
	public Light()
	{
		location = new Point(0, 0, 0);
		color = new Color(255, 255, 255);
	}
	
	public Light(float x, float y, float z)
	{
		location = new Point(x, y, z);
		color = new Color(255, 255, 255);
	}
	
	public Light(float x, float y, float z, Color c)
	{
		location = new Point(x, y, z);
		color = c;
	}
	
	public Light(Point p, Color c)
	{
		location = p;
		color = c;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public float getX()
	{
		return location.getX();
	}
	
	public float getY()
	{
		return location.getY();
	}
	
	public float getZ()
	{
		return location.getZ();
	}
}
