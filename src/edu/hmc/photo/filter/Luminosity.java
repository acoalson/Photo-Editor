package edu.hmc.photo.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

/*
 * @author Aria Coalson
 * 
 */
public class Luminosity implements ImageFilter
{

    /*
     * Takes the weighted average of red green and blue in weights take into
     * account that human vision has much better visual acuity within some color
     * frequencies
     * 
     * luminosity = (int)(0.21 * redness + 0.72 * greenness + 0.07 * blueness);
     */
	@Override
    public BufferedImage filter(BufferedImage image)
    {
        
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        
        for (int x = 0; x < image.getWidth(); x++)
        {
            for (int y = 0; y < image.getHeight(); y++)
            {
                Color color = new Color(image.getRGB(x, y));
                int lum = (int) (0.21 * color.getRed() + 0.72 * color.getGreen() + 0.07 * color.getBlue());
                Color lumColor = new Color(lum, lum, lum);
                newImage.setRGB(x, y, lumColor.getRGB());
            }
        }
        return newImage;
    }
}
