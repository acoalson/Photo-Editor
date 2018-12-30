package edu.hmc.photo.filter;

import java.awt.image.BufferedImage;

/** A filter for the photo-editing program */
public interface ImageFilter {
  /**
   * Apply the filter to the image and return a new image. 
   * 
   * The filter should not modify the input image.
   * 
   * @param image The image to filter
   * @return 
   *   A new image, which results from applying the filter to the input image.
   */
  public BufferedImage filter(BufferedImage image);
}
