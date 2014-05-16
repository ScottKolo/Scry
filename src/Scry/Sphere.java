/**
 * 
 */
package Scry;

import java.awt.Color;

/**
 * @author Scott Kolodziej
 *
 */
public class Sphere implements Traceable
{
	float x, y, z, radius;
	float kd, ks, kr, ka;
	Color color;
	
	public Sphere()
	{
		
	}
	
	public Sphere(float x, float y, float z, float r)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.radius = r;
		color = new Color(255, 255, 255);
		ks = 1.0f;
		kd = 1.0f;
		kr = 0.0f;
		ka = 1.0f;
	}
	
	public Sphere(float x, float y, float z, float r, Color c)
	{
		this(x, y, z, r);
		color = c;
		ks = 1.0f;
		kd = 1.0f;
		kr = 0.0f;
		ka = 1.0f;
	}
	
	public Sphere(Point p, Color c, float r, float ka, float kd, float ks, float kr)
	{
		this(p.getX(), p.getY(), p.getZ(), r, c);
		this.ka = ka;
		this.ks = ks;
		this.kd = kd;
		this.kr = kr;
	}
	
	/* (non-Javadoc)
	 * @see Scry.Traceable#getIntersections(Scry.Ray)
	 */
	public float[] getIntersections(Ray r) {
		float a = r.getxv()*r.getxv()+r.getyv()*r.getyv()+r.getzv()*r.getzv();
		float b = 2*(r.getxv()*(r.getX()-x)+r.getyv()*(r.getY()-y)+r.getzv()*(r.getZ()-z));
		float c = (float) (Math.pow(r.getX()-x,2) + Math.pow(r.getY()-y,2) + Math.pow(r.getZ()-z,2) - Math.pow(radius,2));
		
		float[] intersections = null;
		
		if(b*b-4*a*c < 0.0001 && b*b-4*a*c >= 0)
		{
			intersections = new float[1];
			intersections[0] = (float) ((-b+Math.sqrt(b*b-4*a*c))/(2*a));
		}
		else if(b*b-4*a*c > 0)
		{
			intersections = new float[2];
			intersections[0] = (float) ((-b+Math.sqrt(b*b-4*a*c))/(2*a));
			intersections[1] = (float) ((-b-Math.sqrt(b*b-4*a*c))/(2*a));
		}
		
		return intersections;
	}

	/* (non-Javadoc)
	 * @see Scry.Traceable#getNormal(Scry.Point)
	 */
	public Vector getNormal(Point p)
	{
		float xn = 2*(p.getX()-x);
		float yn = 2*(p.getY()-y);
		float zn = 2*(p.getZ()-z);
		
		Vector normal = new Vector(xn, yn, zn);
		
		return normal;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public float getAmbient()
	{
		return ka;
	}
	
	public float getDiffuse()
	{
		return kd;
	}
	
	public float getSpecular()
	{
		return ks;
	}
	
	public float getReflectivity()
	{
		return kr;
	}
}
