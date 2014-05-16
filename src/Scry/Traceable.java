/**
 * 
 */
package Scry;

import java.awt.Color;

/**
 * @author Scott Kolodziej
 *
 */
public interface Traceable
{
	float[] getIntersections(Ray r);
	Vector getNormal(Point p);
	Color getColor();
	float getAmbient();
	float getDiffuse();
	float getSpecular();
	float getReflectivity();
}
