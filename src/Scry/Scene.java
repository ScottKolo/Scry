/**
 * 
 */
package Scry;

import java.util.ArrayList;
import java.awt.Color;

/**
 * @author Scott Kolodziej
 *
 */
public class Scene
{
	ArrayList<Traceable> objects;
	ArrayList<Light> lights;
	Color ambientLight;
	Color backgroundColor;
	
	public Scene()
	{
		objects = new ArrayList<Traceable>();
		lights = new ArrayList<Light>();
		ambientLight = new Color(255, 255, 255);
		backgroundColor = new Color(0, 0, 0);
	}
	
	public ArrayList<Traceable> getObjects()
	{
		return objects;
	}
	
	public void addObject(Traceable object)
	{
		objects.add(object);
	}
	
	public ArrayList<Light> getLights()
	{
		return lights;
	}
	
	public void addLight(Light light)
	{
		lights.add(light);
	}
	
	public void setAmbientLight(Color light)
	{
		ambientLight = light;
	}
	
	public Color getAmbientLight()
	{
		return ambientLight;
	}
	
	public void setBackgroundColor(Color bg)
	{
		backgroundColor = bg;
	}
	
	public Color getBackgroundColor()
	{
		return backgroundColor;
	}
}
