/**
 * 
 */
package Scry;

import java.awt.Color;

/**
 * @author Scott Kolodziej
 *
 */
public class Plane implements Traceable
{
	float A, B, C, D;
	float kd, ks, kr, ka;
	Color color;
	
	public Plane()
	{
		A = 0;
		B = 1;
		C = 0;
		D = 0;
		kd = 1.0f;
		ks = 0.5f;
		kr = 0.0f;
		ka = 1.0f;
		color = new Color(1.0f, 1.0f, 1.0f);
	}
	
	public Plane(float a, float b, float c, float d, float ka, float kd, float ks, float kr, float red, float green, float blue)
	{
		A = a;
		B = b;
		C = c;
		D = d;
		this.ka = ka;
		this.kd = kd;
		this.ks = ks;
		this.kr = kr;
		color = new Color(red, green, blue);
	}
	
	public Plane(float a, float b, float c, float d, float ka, float kd, float ks, float kr, Color color)
	{
		A = a;
		B = b;
		C = c;
		D = d;
		this.ka = ka;
		this.kd = kd;
		this.ks = ks;
		this.kr = kr;
		this.color = color;
	}

	/* (non-Javadoc)
	 * @see Scry.Traceable#getColor()
	 */
	public Color getColor()
	{
		return color;
	}

	/* (non-Javadoc)
	 * @see Scry.Traceable#getDiffuse()
	 */
	public float getDiffuse()
	{
		// TODO Auto-generated method stub
		return kd;
	}

	/* (non-Javadoc)
	 * @see Scry.Traceable#getIntersections(Scry.Ray)
	 */
	public float[] getIntersections(Ray r)
	{
		float[] intersections = new float[1];
		if(A*r.getxv()+B*r.getyv()+C*r.getzv() != 0)
		{
			intersections[0] = (D-A*r.getX()-B*r.getY()-C*r.getZ())/(A*r.getxv()+B*r.getyv()+C*r.getzv());
		}
		else
		{
			intersections = null;
		}
		
		return intersections;
	}

	/* (non-Javadoc)
	 * @see Scry.Traceable#getNormal(Scry.Point)
	 */
	public Vector getNormal(Point p) {
		// TODO Auto-generated method stub
		return new Vector(A, B, C);
	}

	/* (non-Javadoc)
	 * @see Scry.Traceable#getReflectivity()
	 */
	public float getReflectivity() {
		// TODO Auto-generated method stub
		return kr;
	}

	/* (non-Javadoc)
	 * @see Scry.Traceable#getSpecular()
	 */
	public float getSpecular() {
		// TODO Auto-generated method stub
		return ks;
	}
	
	public float getAmbient()
	{
		return ka;
	}
}
