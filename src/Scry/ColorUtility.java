package Scry;

import java.awt.Color;

public class ColorUtility
{
	static Color addColors(Color p, Color q)
	{
		int r = p.getRed()+q.getRed();
		int g = p.getGreen()+q.getGreen();
		int b = p.getBlue()+q.getBlue();
		
		if(r > 255 || g > 255 || b > 255)
		{
			int scale = Math.max(Math.max(r, g), b);
			r = (int) Math.round((float)r/(float)scale*255);
			g = (int) Math.round((float)g/(float)scale*255);
			b = (int) Math.round((float)b/(float)scale*255);
		}
		
		return new Color(r, g, b);
	}
	
	static Color multiplyColors(Color p, Color q)
	{
		int r = (p.getRed()*q.getRed())/255;
		int g = (p.getGreen()*q.getGreen())/255;
		int b = (p.getBlue()*q.getBlue())/255;
		
		if(r > 255 || g > 255 || b > 255)
		{
			int scale = Math.max(Math.max(r, g), b);
			r = (int) Math.round((float)r/(float)scale*255);
			g = (int) Math.round((float)g/(float)scale*255);
			b = (int) Math.round((float)b/(float)scale*255);
		}
		
		return new Color(r, g, b);
	}
	
	static Color multiply(Color c, float f)
	{
		//System.out.println(f);
		Color r = new Color((int)(c.getRed()*f), (int)(c.getGreen()*f), (int)(c.getBlue()*f));
		return r;
	}
	
	static Color average(Color[] array)
	{
		int r = 0;
		int g = 0;
		int b = 0;
		
		for(int k = 0; k < array.length; k++)
		{
			if(array[k] == null)
			{
				System.out.println(k + " " + array.length);
				break;
			}
			r += array[k].getRed();
			g += array[k].getGreen();
			b += array[k].getBlue();
		}
		
		r = (int)((float)r / (float)array.length);
		g = (int)((float)g / (float)array.length);
		b = (int)((float)b / (float)array.length);
		
		return new Color(r, g, b);
	}
}
