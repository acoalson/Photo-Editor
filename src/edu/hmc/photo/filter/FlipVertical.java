package edu.hmc.photo.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class FlipVertical implements ImageFilter
{

    public BufferedImage filter(BufferedImage image)
    {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        for (int x = 0; x < image.getWidth(); x++)
        {
            for (int y = 0; y < image.getHeight(); y++)
            {
                Color color1 = new Color(image.getRGB(x, y));

                newImage.setRGB(image.getWidth() - x - 1 , y , color1.getRGB());

            }
        }
        return newImage;
    }
}

