package Scry;

import java.awt.Color;

/**
 * @author Scott Kolodziej
 *
 */
public class UncheckedColor
{
	/**
	 * 
	 */
	float r, g, b;
	
	public UncheckedColor()
	{
		r = 0;
		g = 0;
		b = 0;
	}
	
	public UncheckedColor(int r, int g, int b)
	{
		this.r = (float)r/(float)255;
		this.g = (float)g/(float)255;
		this.b = (float)b/(float)255;
	}
	
	public UncheckedColor(float r, float g, float b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public Color getColor()
	{
		return new Color(r, g, b);
	}
}
