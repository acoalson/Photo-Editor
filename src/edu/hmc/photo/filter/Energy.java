package edu.hmc.photo.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

/*
 * Energy represents the edges around a pixel's edge
 * High-energy pixels are at a strong edge in the image whereas low energy pixels are those
 * in the middle of uniform patches of color  
 * Used in seam carving to find the lowest energy path
 *
 * @author Aria Coalson
 */
public class Energy implements ImageFilter
{

    /*
     * Energy is the approximation to the image derivative, which is how fast
     * the luminosity changes between adjacent pixels
     * 
     * Uses the horizontal and vertical forward rates of change in luminosity to calculate energy
     * energy = abs( i(x+1, y) − i(x, y) ) + abs( i(x, y+1) − i(x, y) )
     * with i(x,y) being the luminosity at a given point
     */
	@Override
    public BufferedImage filter(BufferedImage image)
    {

        ImageFilter luminosity = new Luminosity();
        BufferedImage luminosityImage = luminosity.filter(image);

        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        for (int x = 0; x < luminosityImage.getWidth(); x++)
        {
            for (int y = 0; y < luminosityImage.getHeight(); y++)
            {
                int horizontalRGB;
                int verticalRGB;
                Color colorW1 = new Color(luminosityImage.getRGB(x, y));
                Color colorW2;
                Color colorH1 = new Color(luminosityImage.getRGB(x, y));
                Color colorH2;

                if (x == image.getWidth() - 1) // horizontal backwards
                                               // difference
                {
                    colorW1 = new Color(luminosityImage.getRGB(x, y)); // i(x,y)
                    colorW2 = new Color(luminosityImage.getRGB(x - 1, y)); // i(x−1,y)
                }
                else // horizontal forwards difference
                {
                    colorW2 = new Color(luminosityImage.getRGB(x + 1, y)); // i(x+1,y)
                    colorW1 = new Color(luminosityImage.getRGB(x, y)); // i(x,y)
                }
                horizontalRGB = colorW1.getRed() - colorW2.getRed();

                if (y == image.getHeight() - 1) // vertical backwards difference
                {
                    colorH1 = new Color(luminosityImage.getRGB(x, y)); // i(x,y)
                    colorH2 = new Color(luminosityImage.getRGB(x, y - 1)); // i(x,y-1)
                }
                else // vertical forwards difference
                {
                    colorH2 = new Color(luminosityImage.getRGB(x, y + 1)); // i(x, y+1)
                    colorH1 = new Color(luminosityImage.getRGB(x, y)); // i(x,y)
                }
                verticalRGB = colorH1.getRed() - colorH2.getRed();

                int energy = Math.abs(horizontalRGB) + Math.abs(verticalRGB);
                if (energy > 255)
                    energy = 255;
                Color energyCol = new Color(energy, energy, energy);
                newImage.setRGB(x, y, energyCol.getRGB());
            }
        }
        return newImage;
    }
}
