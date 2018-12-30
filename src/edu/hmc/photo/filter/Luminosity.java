package edu.hmc.photo.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Luminosity implements ImageFilter
{

    @Override
    public BufferedImage filter(BufferedImage image)
    {
        for (int x=0 ; x<image.getWidth() ; x++)
        {
            for (int y=0 ; y<image.getHeight() ; y++)
            {
                Color color = new Color(image.getRGB(x, y));
                int lum = (int)(0.21 * color.getRed() + 0.72 * color.getGreen() + 0.07 * color.getBlue());
                Color lumColor = new Color(lum, lum, lum);
                image.setRGB(x, y, lumColor.getRGB());
            }
        }
        BufferedImage newImage = image;
        return newImage;
    }
}
