package edu.hmc.photo.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class CarveSeam implements ImageFilter
{

    /*
     * In edu.hmc.photo.filter.CarveSeam, write a filter that removes the seam from an image,
     * creating an image that is one pixel narrower
     */
    public BufferedImage filter(BufferedImage image)
    {
        /*
        BufferedImage imageCopy = new BufferedImage(image.getWidth(), image.getHeight(), image.getType()); 
        for (int x = 0; x < imageCopy.getWidth(); x++)
        {
            for (int y = 0; y < imageCopy.getHeight(); y++)
            {
                imageCopy.setRGB(x, y, image.getRGB(x, y));
            }
        }
        */
        
        FindSeam f = new FindSeam();
        BufferedImage imag = f.filter(image);
        int[] seam = f.getSeam();


        
        
        BufferedImage shortImage = new BufferedImage(image.getWidth()-1, image.getHeight(), image.getType());
        
        for (int row = 0; row < imag.getHeight(); row++)
        {
            int remCol = seam[row]; //index of row to delete
            int cutCol = 0;
            for (int col = 0; col < imag.getHeight(); col++)
            {
                if (remCol == col)
                {
                    //Don't add this pixel[r,c] to short image
                }
                else
                {
                    shortImage.setRGB(row, cutCol, imag.getRGB(row, col));
                    cutCol++;
                }
            }
            
        }
         return shortImage;

    }
  
}
