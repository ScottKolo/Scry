/**
 * 
 */
package Scry;

import java.awt.Color;

/**
 * @author Scott Kolodziej
 *
 */
public class Polygon implements Traceable
{
	Plane p;
	float kd, ks, kr, ka;
	Color color;

	/* (non-Javadoc)
	 * @see Scry.Traceable#getColor()
	 */
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	/* (non-Javadoc)
	 * @see Scry.Traceable#getDiffuse()
	 */
	public float getDiffuse() {
		// TODO Auto-generated method stub
		return kd;
	}

	/* (non-Javadoc)
	 * @see Scry.Traceable#getIntersections(Scry.Ray)
	 */
	public float[] getIntersections(Ray r) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Scry.Traceable#getNormal(Scry.Point)
	 */
	public Vector getNormal(Point p) {
		// TODO Auto-generated method stub
		return this.p.getNormal(p);
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
