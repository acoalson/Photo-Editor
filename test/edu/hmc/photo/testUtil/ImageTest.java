package edu.hmc.photo.testUtil;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import edu.hmc.photo.BufferedImageUtilities;
import edu.hmc.photo.filter.ImageFilter;

public class ImageTest {
  public static final URL TEST_IMAGE_PATH = 
      BufferedImageUtilities.class.getResource("testPhotos/");

  /** 
   * Tests two BufferedImages to make sure their contents are equal. 
   * Fails if they are not equal.
   * @param actual the image under test
   * @param expected the correct image
   * @return true if the the two images are the same
   */
	public static boolean isEqual(BufferedImage actual, BufferedImage expected) {
	  if (actual == null && expected != null) {
	    fail("The actual image is null, but the expected image is not.");
	  }
	  
	  // Check that the widths are the same
	  int expectedWidth = expected.getWidth();
	  int actualWidth = actual.getWidth();
		if (expectedWidth != actualWidth) {
			fail(String.format("Actual width %d differs from expected width %d", actualWidth, expectedWidth));
			return false;
		}

		// Check that the heights are the same
		int expectedHeight = expected.getHeight();
    int actualHeight = actual.getHeight();
    if (expectedHeight != actualHeight) {
      fail(String.format("Actual height %d differs from expected Height %d", actualHeight, expectedHeight));
      return false;
    }

    // Check that the contents are the same
		for (int column = 0; column < expectedWidth; column++) {
			for (int row = 0; row < expectedHeight; row++) {
			  int expectedRGB = expected.getRGB(column, row);
			  int actualRGB = actual.getRGB(column, row);
			  
			  // If the contents are different, generate a descriptive error message
				if (expectedRGB != actualRGB) {
				  Color expectedColor = new Color(expectedRGB);
				  Color actualColor = new Color(actualRGB);
				  String message = String.format("Actual image differs from expected image at column %d, row %d (%s vs %s)", column, row, actualColor, expectedColor);
				  fail(message);
				  return false;
				}
			}
		}

		return true;
	}
	
	/**
	 * Tries to load a test image. If the image can't be loaded, fails and returns
	 * null.
	 * @param imageName name of the image to be loaded, e.g., "Bird.png"
	 * @return the BufferedImage for the corresponding file
	 */
  public static BufferedImage loadImage(String imageName) {
    try {
      URL url = new URL(TEST_IMAGE_PATH, imageName);      
      return BufferedImageUtilities.loadBufferedImage(url);      
    }
    catch (IOException error) {
      String message = "Unable to load picture " + imageName + "\n" + error.toString();
      fail(message);
      return null;
    }
  }
	
  /**
   * Apply a filter to an input image (specified by filename) and test that
   * the result equals the expected output image (specified by filename)
   * @param inputFilename The name of the image to filter
   * @param expectedOutputFilename The name of the expected-result image 
   * @param filter An instance of a filter to apply
   */
	public void testImageFilter(String inputFilename, String expectedOutputFilename, ImageFilter filter) {
		testImageFilter(loadImage(inputFilename), loadImage(expectedOutputFilename), filter);
	}
	
	/**
   * Apply a filter to an input image and test that
   * the result equals the expected output image
   * @param input The image to filter
   * @param expectedOutput The expected-result image 
   * @param filter An instance of a filter to apply
   */
  public void testImageFilter(BufferedImage input, BufferedImage expectedOutput, ImageFilter filter) {
		BufferedImage actual = filter.filter(input);		
		assertTrue(isEqual(actual, expectedOutput));
		assertTrue(isEqual(input, input));
	}
	
  /**
   * Apply a filter n times to an input image (specified by filename) and test 
   * that the result equals the expected output image (specified by filename)
   * @param inputFilename The name of the image to filter
   * @param expectedOutputFilename The name of the expected-result image 
   * @param filter An instance of a filter to apply
   * @param repetitions The number of times to apply the filter
   */
	public void testImageFilter(String inputFilename, String expectedOutputFilename, ImageFilter filter, int repetitions) {
	  testImageFilter(loadImage(inputFilename), loadImage(expectedOutputFilename), filter, repetitions);
	}
	
  /**
   * Apply a filter n times to an input image and test 
   * that the result equals the expected output image
   * @param input The image to filter
   * @param expectedOutput The expected-result image 
   * @param filter An instance of a filter to apply
   * @param repetitions The number of times to apply the filter
   */
	public void testImageFilter(BufferedImage input, BufferedImage expectedOutput, ImageFilter filter, int repetitions) {
    for (int i=0; i < repetitions; i++) {
      input = filter.filter(input);
    }
    assertTrue(isEqual(input, expectedOutput));      
  }
}
