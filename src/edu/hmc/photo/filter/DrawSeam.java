package edu.hmc.photo.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

/*
 * @author Aria Coalson
 */
public class DrawSeam implements ImageFilter
{

    /*
     * This filter displays all the pixels in the seam so that they’re red (255,
     * 0, 0)
     */
    public BufferedImage filter(BufferedImage image)
    {
        BufferedImage imageCopy = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int x = 0; x < imageCopy.getWidth(); x++)
        {
            for (int y = 0; y < imageCopy.getHeight(); y++)
            {
                imageCopy.setRGB(x, y, image.getRGB(x, y));
            }
        }

        FindSeam f = new FindSeam();
        BufferedImage imag = f.filter(image);

        int[] seam = f.getSeam();

        for (int i = 0; i < seam.length; i++)
        {
            Color red = new Color(255, 0, 0);
            int redRGB = red.getRGB();
            imageCopy.setRGB(seam[i], i, redRGB);
        }

        return imageCopy;

    }

}
