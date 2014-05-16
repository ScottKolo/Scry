package Scry;

import java.awt.image.*;
import java.awt.Color;
import java.util.ArrayList;

public class RayTracer
{
	int fovx;
	int fovy;
	int height;
	int width;
	float eyeX, eyeY, eyeZ;
	Scene scene;
	int depth;
	int antialiasOrder;
	
	public RayTracer()
	{
		height = 600;
		width = 800;
		fovx = 55;
		fovy = 45;
		eyeX = 0;
		eyeY = 0;
		eyeZ = -5;
		depth = 1;
		antialiasOrder = 0;
	}
	
	public RayTracer(int w, int h)
	{
		height = h;
		width = w;
		fovx = (int) Math.round((180f/Math.PI*2f*Math.atan((float)width/(float)height*Math.tan(45f/2f/180f*Math.PI))));
		fovy = 45;
		eyeX = 0;
		eyeY = 0;
		eyeZ = -5;
		depth = 2;
		antialiasOrder = 4;
	}
	
	boolean inShadow(Point p, Light l)
	{
		float xv = l.getX()-p.getX();
		float yv = l.getY()-p.getY();
		float zv = l.getZ()-p.getZ();
		Ray r = new Ray(p, new Vector(xv, yv, zv));
		
		ArrayList<Traceable> objects = scene.getObjects();
		float t = Float.MAX_VALUE;
		
		for(int k = 0; k < objects.size(); k++)
		{
			float[] intersections = objects.get(k).getIntersections(r);
			if(intersections != null)
			for(int j = 0; j < intersections.length; j++)
			{
				if(intersections[j] < t  && intersections[j] > 0.0001)
				{
					t = intersections[j];
				}
			}
		}
		
		if(t > 1)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	Color primaryRayTrace(Ray r, int level)
	{
		if(level > depth)
		{
			return new Color(0,0,0);
		}
		
		Color c = scene.getBackgroundColor();
		
		ArrayList<Traceable> objects = scene.getObjects();
		float t = Float.MAX_VALUE;
		int bestK = 0;
		
		for(int k = 0; k < objects.size(); k++)
		{
			float[] intersections = objects.get(k).getIntersections(r);
			if(intersections != null)
			for(int j = 0; j < intersections.length; j++)
			{
				if(intersections[j] < t  && intersections[j] > 1.0001)
				{
					t = intersections[j];
					bestK = k;
				}
			}
		}
		
		if(t < Float.MAX_VALUE  && t > 1)
		{
			// Ambient Lighting
			Color ambient = scene.getAmbientLight();
			Color objColor = ColorUtility.multiply(objects.get(bestK).getColor(), objects.get(bestK).getAmbient());
			c = ColorUtility.multiplyColors(ambient, objColor);
			
			ArrayList<Light> lights = scene.getLights();
			for(int j = 0; j < lights.size(); j++)
			{
				Light light = lights.get(j);
				Point p = r.evaluate(t);
				
				// Check if this light reaches the surface of the object.
				boolean inShadow = inShadow(p, light);
					// Lighting calculations for that light.
					// Diffuse Lighting
					float kd = objects.get(bestK).getDiffuse();
					Vector lightVect = new Vector(light.getX()-p.getX(), light.getY()-p.getY(), light.getZ()-p.getZ());
					float d = kd*Math.abs(Vector.normalDotProduct(lightVect, objects.get(bestK).getNormal(p)));
				if(!inShadow)
				{
					c = ColorUtility.addColors(c, ColorUtility.multiply(light.getColor(), d));
				}
					// Specular Lighting
					float ks = objects.get(bestK).getSpecular();
					Vector normal = objects.get(bestK).getNormal(p);
					float xv = 2*(Vector.normalDotProduct(normal,lightVect)*(normal.normalize().getX()))-(lightVect.normalize().getX());
					float yv = 2*(Vector.normalDotProduct(normal,lightVect)*(normal.normalize().getY()))-(lightVect.normalize().getY());
					float zv = 2*(Vector.normalDotProduct(normal,lightVect)*(normal.normalize().getZ()))-(lightVect.normalize().getZ());
					Vector specular = new Vector(xv, yv, zv);
					float dp = Vector.normalDotProduct(lightVect, specular);
					if(dp < 0)
						dp = 0;
					float spec = (float) (ks*Math.pow(dp, 40));
					if(spec < 0)
					{
						spec = 0;
					}
				if(!inShadow)
				{
					c = ColorUtility.addColors(c, ColorUtility.multiply(light.getColor(), spec));
				}
					// Reflections
					float kr = objects.get(bestK).getReflectivity();
					xv = -r.getxv();
					yv = -r.getyv();
					zv = -r.getzv();
					Vector view = new Vector(xv, yv, zv);
					xv = (float)2.0*(Vector.normalDotProduct(normal, view)*(normal.normalize().getX()))-(float)(view.normalize().getX());
					yv = (float)2.0*(Vector.normalDotProduct(normal, view)*(normal.normalize().getY()))-(float)(view.normalize().getY());
					zv = (float)2.0*(Vector.normalDotProduct(normal, view)*(normal.normalize().getZ()))-(float)(view.normalize().getZ());
					Ray reflect = new Ray(p, xv, yv, zv);
					Color rColor = primaryRayTrace(reflect, level+1);
					c = ColorUtility.addColors(c, ColorUtility.multiply(rColor, kr));
			}
		}
		
		return c;
	}
	
	public BufferedImage generateImage()
	{
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		// Set up projection
		float scaleX = (float) (-eyeZ*Math.tan(fovx/360f*Math.PI));
		float scaleY = (float) (-eyeZ*Math.tan(fovy/360f*Math.PI));
		
		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				// Determine the position of the pixel in world space
				float x0 = (float)c/(float)width*scaleX-(float)scaleX/2.0f;
				float y0 = (float)r/(float)height*scaleY-(float)scaleY/2.0f;
				float z0 = 0;
				int n = antialiasOrder;
				Color color = new Color(0, 0, 0);
				Color[] colorArray = new Color[Math.max((int)4*(n-1), 1)];
				int num = 0;
				
				if(n == 1)
				{
					float h, w;
					w = 1.0f/(float)width/2.0f;
					h = 1.0f/(float)height/2.0f;
					
					float xv = x0 - eyeX + w;
					float yv = y0 - eyeY + h;
					float zv = z0 - eyeZ;
					Ray ray = new Ray(eyeX, eyeY, eyeZ, xv, yv, zv);
					Color temp = primaryRayTrace(ray, 0);
					colorArray[0] = temp;
				}
				else
				{
					for(int cp = 0; cp < n; cp++)
					{
						float h, w;
						w = 1.0f/(float)width/(float)(antialiasOrder-1)*-eyeZ;
						h = 1.0f/(float)height/(float)(antialiasOrder-1);
						
						// Determine vector from eye to pixel
						float xv  = x0 - eyeX + w*(cp);
						float yv1 = y0 - eyeY;
						float yv2 = y0 - eyeY + h;
						float zv  = z0 - eyeZ;
						Ray ray1  = new Ray(eyeX, eyeY, eyeZ, xv, yv1, zv);
						Ray ray2  = new Ray(eyeX, eyeY, eyeZ, xv, yv2, zv);
						Color temp1 = primaryRayTrace(ray1, 0);
						Color temp2 = primaryRayTrace(ray2, 0);
						colorArray[num] = temp1;
						colorArray[num+1] = temp2;
						num+=2;
					}
					for(int rp = 1; rp < n-1; rp++)
					{
						float h, w;
						w = 1.0f/(float)width;
						h = 1.0f/(float)height/(float)(antialiasOrder-1)*-eyeZ;
						
						// Determine vector from eye to pixel
						float xv1 = x0 - eyeX;
						float xv2 = x0 - eyeX + w;
						float yv = y0 - eyeY + h*(rp);
						float zv = z0 - eyeZ;
						Ray ray1 = new Ray(eyeX, eyeY, eyeZ, xv1, yv, zv);
						Ray ray2 = new Ray(eyeX, eyeY, eyeZ, xv2, yv, zv);
						Color temp1 = primaryRayTrace(ray1, 0);
						Color temp2 = primaryRayTrace(ray2, 0);
						colorArray[num] = temp1;
						colorArray[num+1] = temp2;
						num+=2;
					}
				}
				
				color = ColorUtility.average(colorArray);
				image.setRGB(c, height-1-r, color.getRGB());
			}
		}
		
		return image;
	}
	
	public void setScene(Scene s)
	{
		scene = s;
	}
}

class Ray
{
	float x, y, z;
	float xv, yv, zv;
	
	public Ray()
	{
	}
	
	public Ray(float x, float y, float z, float xv, float yv, float zv)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.xv = xv;
		this.yv = yv;
		this.zv = zv;
	}
	
	public Ray(Point p, float xv, float yv, float zv)
	{
		this(p.getX(), p.getY(), p.getZ(), xv, yv, zv);
	}
	
	public Ray(Point p, Vector v)
	{
		x = p.getX();
		y = p.getY();
		z = p.getZ();
		xv = v.getX();
		yv = v.getY();
		zv = v.getZ();
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
	
	public float getxv()
	{
		return xv;
	}
	
	public float getyv()
	{
		return yv;
	}
	
	public float getzv()
	{
		return zv;
	}
	
	public Point evaluate(float t)
	{
		float xe = x + xv*t;
		float ye = y + yv*t;
		float ze = z + zv*t;
		
		return new Point(xe, ye, ze);
	}
	
	public String toString()
	{
		return ("(" + x + ", " + y + ", " + z + ") to (" + xv + ", " + yv + ", " + zv + ")");
	}
}