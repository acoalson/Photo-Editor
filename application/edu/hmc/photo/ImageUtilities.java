package edu.hmc.photo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * 
 * A class to load / save {@link java.awt.image.BufferedImage}s and
 * {@link javafx.scene.image.Image}s from / to files, and to convert between
 * the two formats  
 *
 */
public class ImageUtilities {
  /**
   * Load the contents of an image file 
   * @param filename The full path to the image file
   * @return an Image instance with the contents of the image file
   * @throws IOException If the file doesn't exist
   */
  public static Image loadImage(String filename) throws IOException {
    return loadImage(new File(filename));
  }
  
  /**
   * Load the contents of an image file 
   * @param file A File for the image
   * @return an Image instance with the contents of the image file
   * @throws IOException If the file doesn't exist
   */
  public static Image loadImage(File file) throws IOException {
    try {
     return new Image(file.toURI().toURL().toString());
    } catch (MalformedURLException error){
      throw new IOException(error.getMessage());
    }   
  }

  /**
   * Save the contents of an image to a file, as a PNG
   * @param image The instance of the image
   * @param file A file to save the image to
   * @throws IOException If saving fails
   */
  public static void saveImage(Image image, File file) throws IOException {
    BufferedImageUtilities.saveBufferedImage(asBufferedImage(image), file);
  }
  
  /**
   * Convert a {@link javafx.scene.image.Image} to a 
   * {@link java.awt.image.BufferedImage}
   */
  public static BufferedImage asBufferedImage(Image image) {
    return SwingFXUtils.fromFXImage(image, null);
  }
  
  /**
   * Convert a {@link java.awt.image.BufferedImage} to a 
   * {@link javafx.scene.image.Image}
   */
  public static Image asImage(BufferedImage image) {
    return SwingFXUtils.toFXImage(image, null);
  }
}
