package edu.hmc.photo.filter;

import java.awt.image.BufferedImage;
import java.awt.Color;


public class Negate implements ImageFilter {

  @Override
  /*
   * 
   * @see edu.hmc.photo.filter.ImageFilter#filter(java.awt.image.BufferedImage)
   */
  public BufferedImage filter(BufferedImage image)
  {
      for (int x=0 ; x<image.getWidth() ; x++)
      {
          for (int y=0 ; y<image.getHeight() ; y++)
          {
              Color color = new Color(image.getRGB(x, y));
              Color negColor = new Color(255-color.getRed(), 255-color.getGreen(),255-color.getBlue());
              image.setRGB(x, y, negColor.getRGB());
              
          }
      }
      BufferedImage newImage = image;
      return newImage;
  }
  
}
