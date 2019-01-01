package edu.hmc.photo.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

/*
 * @author Aria Coalson
 */
public class CarveSeam implements ImageFilter
{

    /*
     * Write a filter that removes the seam from an image,
     * creating an image that is one pixel narrower
     */
    public BufferedImage filter(BufferedImage image)
    {

        FindSeam f = new FindSeam();
        BufferedImage imag = f.filter(image);
        int[] seam = f.getSeam();

        
        BufferedImage shortImage = new BufferedImage(image.getWidth()-1, image.getHeight(), image.getType());
        
        for (int row = 0; row < imag.getHeight(); row++)
        {
            int remCol = seam[row]; //index of row to delete
            int cutCol = 0;
            for (int col = 0; col < imag.getWidth(); col++)
            {
                if (remCol == col)
                {
                    //Don't add this pixel[r,c] to short image
                }
                else
                {
                    shortImage.setRGB(cutCol, row, imag.getRGB(col, row));
                    cutCol++;
                }
            }
            
        }
        return shortImage;

    }
  
}
