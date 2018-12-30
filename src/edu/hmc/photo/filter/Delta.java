package edu.hmc.photo.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * A filter that visualizes the differences between two images 
 */
public class Delta implements ImageFilter {
  /** 
   * The color to use if two pixels at the same location in different 
   * images are the same
   */
  private static final int MATCH_COLOR = Color.BLACK.getRGB();

  /** 
   * The color to use if two pixels at the same location in different 
   * images are not the same
   */
  private static final int NO_MATCH_COLOR = Color.WHITE.getRGB();

  /** 
   * The color to use if the images are of different dimensions and the pixel
   * is out of bounds of the original image.
   */
  private static final int OUT_OF_BOUNDS_COLOR = Color.GRAY.getRGB();

  /** The image to compare against */
  private BufferedImage other;

  public Delta(BufferedImage other) {
    this.other = other;
  }
  
  @Override
  public BufferedImage filter(BufferedImage image) {
    // Create a new image, which we'll return. This new image visualizes the
    // differences between `image` and `this.otherImage`.
    int width = image.getWidth();
    int height = image.getHeight();
    int imageType = image.getType();
    BufferedImage newImage = new BufferedImage(width, height, imageType);
    
    // Process each pixel in column-major order
    for (int column = 0; column < newImage.getWidth(); column++) {
      for (int row = 0; row < newImage.getHeight(); row++) {
        
        try {
          // Compare the pixel at (column, row) in this image to the pixel
          // at (column, row) in the other image, and visualize the results
          // accordingly.
          int thisRGB = image.getRGB(column, row);
          int otherRGB = this.other.getRGB(column, row);
          int difference = otherRGB - thisRGB;
          if (difference == 0) {
            newImage.setRGB(column, row, MATCH_COLOR);
          } else {
            newImage.setRGB(column, row, NO_MATCH_COLOR);
          }
        } 
        
        // If the other image is narrower or shorter than this one, and this 
        // pixel lies out of the range of the smaller image, then visualize
        // this pixel as "out of bounds"
        catch (IndexOutOfBoundsException e) {
          newImage.setRGB(column, row, OUT_OF_BOUNDS_COLOR);
        }        
      }
    }
    
    return newImage;
  }
}
