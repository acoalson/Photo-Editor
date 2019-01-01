package edu.hmc.photo.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

/*
 * @author Aria Coalson
 */
public class RotateRight implements ImageFilter
{

    /*
     * Rotates the image 90 degrees clockwise
     */
    public BufferedImage filter(BufferedImage image)
    {

        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage newImage = new BufferedImage(h, w, image.getType());

        for (int x = 0; x < w; x++)
        {
            for (int y = 0; y < h; y++)
            {
                Color newCol = new Color(image.getRGB(x, y));
                newImage.setRGB(h - y - 1, x, newCol.getRGB());

            }
        }
        return newImage;
    }

}
