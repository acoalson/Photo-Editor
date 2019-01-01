package edu.hmc.photo.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

/*
 * @author Aria Coalson
 */
public class Grayscale implements ImageFilter
{

    /*
     * Uses the average method to average the three color values
     * (R + G + B) / 3
     */
	@Override
    public BufferedImage filter(BufferedImage image)
    {
        for (int x = 0; x < image.getWidth(); x++)
        {
            for (int y = 0; y < image.getHeight(); y++)
            {
                Color color = new Color(image.getRGB(x, y));
                int grayed = (color.getRed() + color.getBlue() + color.getGreen()) / 3;
                Color negColor = new Color(grayed, grayed, grayed);
                image.setRGB(x, y, negColor.getRGB());
            }
        }
        BufferedImage newImage = image;
        return newImage;
    }

}
