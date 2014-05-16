/**
 * 
 */
package Scry;

/**
 * @author Scott Kolodziej
 *
 */
public class Vector
{
	float x, y, z;
	
	public Vector()
	{
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Vector(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getZ()
	{
		return z;
	}
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	public void setY(float y)
	{
		this.y = y;
	}
	
	public void setZ(float z)
	{
		this.z = z;
	}
	
	public static Vector normalCrossProduct(Vector a, Vector b)
	{
		Vector cp = new Vector();
		
		return cp;
	}
	
	public static float normalDotProduct(Vector a, Vector b)
	{
		Vector c = normalize(a);
		Vector d = normalize(b);
		float dp = c.getX()*d.getX()+c.getY()*d.getY()+c.getZ()*d.getZ();
		return dp;
	}
	
	public float magnitude()
	{
		return (float) Math.sqrt(x*x+y*y+z*z);
	}
	
	public static float magnitude(Vector v)
	{
		return v.magnitude();
	}
	
	public static Vector normalize(Vector v)
	{
		float mag = v.magnitude();
		Vector r = new Vector(v.getX()/mag, v.getY()/mag, v.getZ()/mag);
		return r;
	}
	
	public Vector normalize()
	{
		return normalize(this);
	}
}
