package edu.hmc.photo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 
 * A class to load / save {@link java.awt.image.BufferedImage}s and
 * {@link javafx.scene.image.Image}s from / to files, and to convert between
 * the two formats  
 *
 */
public class BufferedImageUtilities {
  /**
   * Load the contents of an image file 
   * @param filename The full path to the image file
   * @return an Image instance with the contents of the image file
   * @throws IOException If the file doesn't exist
   */
  public static BufferedImage loadBufferedImage(String filename) throws IOException {
    return ImageIO.read(new File(filename));
  }
  
  /**
   * Load the contents of an image file 
   * @param url A URL for the image
   * @return an Image instance with the contents of the image file
   * @throws IOException If the URL isn't reachable or can't be read
   */
  public static BufferedImage loadBufferedImage(URL url) throws IOException {
    return ImageIO.read(url);
  }

  /**
   * Load the contents of an image file 
   * @param file A File for the image
   * @return an Image instance with the contents of the image file
   * @throws IOException If the file doesn't exist
   */
  public static BufferedImage loadBufferedImage(File file) throws IOException {
    return ImageIO.read(file);
  }
  
  /**
   * Save the contents of an image to a file, as a PNG
   * @param image The instance of the image
   * @param file A file to save the image to
   * @throws IOException If saving fails
   */
  public static void saveBufferedImage(BufferedImage image, File file) throws IOException {
    ImageIO.write(image, "png", file);
	}
}
