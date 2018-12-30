package edu.hmc.photo.filter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;

import org.junit.Test;

import edu.hmc.photo.testUtil.ImageTest;

public class SeamComputeTests extends ImageTest {
  private void testSeam(String inputFilename, int[] correctSeam) {
    BufferedImage input = loadImage(inputFilename);
    FindSeam filter = new FindSeam();
    BufferedImage actual = filter.filter(input);
    assertTrue(isEqual(actual, input));
    assertTrue(isEqual(input, input));
    int[] actualSeam = filter.getSeam();
    assertArrayEquals(actualSeam, correctSeam);
  }
  
  @Test
  public void testComputeMicroSeam() {
    int[] correctSeam = { 1, 1, 0, 1, 2 };
    testSeam("Micro.png", correctSeam);
  }
  
  @Test
  public void testComputeTinySeam() {
    int[] correctSeam = { 5, 6, 5, 4, 3, 2 };
    testSeam("Tiny.png", correctSeam);
  }
  
  @Test
  public void testComputeBirdSmallSeam() {
    int[] correctSeam = { 17, 17, 17, 18, 18, 18, 19, 19, 18, 18, 17, 17, 17, 
        17, 18, 18, 17, 17, 17, 16 };
    testSeam("BirdSmall.png", correctSeam);
  }
  
  @Test
  public void testComputeBirdSeam() {
    int[] correctSeam = { 20, 20, 19, 18, 18, 19, 20, 20, 20, 21, 20, 19, 18, 
        17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 9, 10, 10, 10, 9, 10, 10, 10, 10, 
        10, 10, 10, 9, 9, 8, 8, 7, 8, 8, 7, 7, 7, 6, 6, 5, 4, 4, 4, 4, 5, 5, 5, 
        5, 5, 6, 6, 6, 7, 8, 9, 9, 10, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 
        13, 14, 15, 15, 15, 14, 15, 15, 16, 17, 18, 18, 18, 17, 16, 15, 14, 15, 
        16, 16, 16, 17, 18, 19, 18, 17, 16, 15, 14, 15, 16, 15, 16, 16, 15, 14, 
        13, 12, 13, 13, 14, 14, 14, 15, 15, 16, 16, 17, 17, 18, 19, 19, 18, 18, 
        18, 17, 16, 16, 16, 17, 17, 16, 17, 17, 16, 17, 17, 18, 19, 18, 18, 18, 
        18, 17, 18, 18, 18, 18, 19, 20, 20, 21, 22, 21, 22, 23, 24, 24, 23, 22, 
        22, 23, 24, 25, 25, 25, 26, 25, 26, 26, 26, 27, 27, 27, 28, 29, 29, 30, 
        30, 30, 30, 30, 29, 28, 29, 28, 27, 26, 25, 24, 24, 24, 24, 23, 23, 22, 
        21, 21, 20, 19, 18, 17, 16, 15, 15, 14, 13, 13, 12, 11, 10, 9, 8, 8, 8, 
        8, 8, 7, 7, 7, 6, 5, 4, 4, 4, 4, 4, 3, 4, 3, 2, 2, 2, 3, 3, 3, 4, 5, 6, 
        6, 6, 6, 6, 7, 8, 7, 7, 7, 6, 5, 4, 4, 5, 5, 4, 3, 2, 1, 2, 1, 0, 0, 0, 
        0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 4, 3, 2, 3, 2, 1, 1, 1, 2, 2, 3, 
        3, 3, 4, 4, 4, 5, 5, 5, 6, 5, 5, 6, 5, 4, 3, 3, 2, 2, 2, 2, 3, 4, 4, 5, 
        5, 5, 5, 6, 7, 6, 5, 4, 5, 6, 5, 5, 6, 6, 7, 7, 7, 7, 8, 9, 10, 9, 9, 9,
        10, 11, 11, 11, 12, 12, 12, 11, 10, 9, 10, 11, 11, 10, 9, 9, 9, 8, 7, 6,
        6, 6, 6, 6, 6, 5, 5, 4, 3, 2, 2, 1, 1, 2, 3, 4, 5, 4, 4, 3, 2, 2, 1, 1, 
        1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 2, 2, 2, 1, 0, 0, 0, 1, 1, 2, 1, 0, 0, 
        0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 2, 3, 2, 1, 1, 1, 1, 
        1, 2, 3, 4, 4, 5, 6, 6, 6, 6, 5, 4, 3, 2, 2, 2, 3, 3, 4, 5, 5, 6, 7, 7, 
        8, 8, 7, 6, 5, 5, 6, 5, 4, 3, 4, 5, 5, 6, 7, 7, 7, 7, 8, 8, 7, 6, 5, 5, 
        4, 5, 6, 7, 7, 7, 7, 6, 6, 7, 7, 8, 9, 9, 10, 11, 12, 12, 11, 11, 12, 
        12, 11, 11, 11, 11, 10, 10, 11, 12, 11, 10, 9, 10, 10, 10, 9, 8, 7, 6, 
        5, 5, 5, 4, 4, 4, 5, 4, 3, 3, 4, 5, 6, 5, 5, 6, 7, 7, 7 };
;
    testSeam("Bird.png", correctSeam);
  }
}
