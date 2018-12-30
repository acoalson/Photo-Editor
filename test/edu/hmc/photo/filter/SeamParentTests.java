package edu.hmc.photo.filter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;

import org.junit.Test;

import edu.hmc.photo.testUtil.ImageTest;

public class SeamParentTests extends ImageTest {
  private void testParent(String inputFilename, int[][] correctParent) {
    BufferedImage input = loadImage(inputFilename);
    FindSeam filter = new FindSeam();
    BufferedImage actual = filter.filter(input);
    assertTrue(isEqual(actual, input));
    assertTrue(isEqual(input, input));
    int[][] actualTable = filter.getParent();
    assertArrayEquals(actualTable, correctParent);
  }
  
  @Test
  public void testTiesParent() {
    int[][] correctParent = { { 0, 1 }, { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 3 }, { 0, 5 } };
    testParent("Ties.png", correctParent);
  }
  
  @Test
  public void testTies2Parent() {
    int[][] correctParent = { { 0, 0 }, { 0, 0 }, { 0, 1 }, { 0, 3 }, { 0, 3 }, { 0, 5 } };
    testParent("Ties2.png", correctParent);
  }
  
  @Test
  public void testMicroParent() {
    int[][] correctParent = { { 0, 1, 1, 0, 0 }, { 0, 1, 1, 0, 1 }, { 0, 1, 1, 1, 1 } };
    testParent("Micro.png", correctParent);
  }
  
  @Test
  public void testTinyParent() {
    int[][] correctParent = { { 0, 0, 0, 1, 0, 0 }, { 0, 1, 1, 1, 1, 1 }, { 0, 2, 2, 2, 2, 3 }, { 0, 3, 3, 3, 4, 3 },
        { 0, 4, 4, 5, 4, 4 }, { 0, 5, 6, 5, 4, 5 }, { 0, 5, 6, 5, 6, 5 }, { 0, 7, 6, 7, 6, 6 },
        { 0, 8, 8, 7, 7, 8 }, { 0, 9, 9, 9, 8, 8 } };
    testParent("Tiny.png", correctParent);
  }
  
  @Test
  public void testBirdSmallParent() {
    int[][] correctParent = {{0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 
        {0, 1, 0, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 0, 2, 0, 1, 2, 2, 1}, 
        {0, 1, 1, 1, 2, 3, 3, 2, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 2, 1}, 
        {0, 3, 4, 2, 2, 3, 4, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 3}, 
        {0, 5, 5, 5, 3, 3, 4, 4, 3, 3, 3, 4, 3, 3, 3, 3, 3, 3, 3, 3}, 
        {0, 5, 6, 6, 6, 6, 4, 6, 6, 5, 4, 4, 5, 4, 4, 4, 6, 6, 6, 6}, 
        {0, 5, 6, 7, 7, 7, 7, 6, 6, 7, 7, 5, 5, 6, 7, 7, 7, 7, 7, 7}, 
        {0, 8, 8, 8, 7, 7, 7, 6, 6, 7, 7, 7, 6, 6, 8, 8, 8, 8, 8, 8}, 
        {0, 8, 9, 8, 9, 9, 7, 7, 8, 7, 7, 9, 9, 9, 8, 9, 9, 9, 9, 9}, 
        {0, 8, 9, 8, 9, 9, 8, 9, 8, 10, 10, 10, 10, 10, 10, 10, 9, 10, 10, 10}, 
        {0, 11, 9, 10, 11, 9, 11, 11, 11, 11, 11, 11, 10, 11, 11, 10, 11, 11, 10, 10}, 
        {0, 11, 10, 10, 11, 12, 12, 12, 12, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12}, 
        {0, 12, 13, 13, 13, 12, 12, 12, 12, 13, 13, 13, 12, 12, 13, 13, 12, 12, 13, 13}, 
        {0, 14, 13, 13, 13, 12, 12, 12, 12, 13, 13, 13, 12, 14, 13, 13, 12, 12, 13, 13}, 
        {0, 14, 15, 13, 13, 13, 13, 13, 14, 13, 13, 13, 15, 15, 13, 15, 15, 15, 13, 14}, 
        {0, 14, 15, 15, 14, 14, 14, 14, 14, 16, 16, 16, 16, 16, 16, 15, 16, 16, 16, 16}, 
        {0, 17, 15, 17, 17, 17, 17, 16, 17, 17, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17}, 
        {0, 17, 17, 17, 18, 18, 18, 16, 17, 18, 18, 17, 17, 17, 17, 18, 18, 17, 17, 17}, 
        {0, 18, 17, 17, 18, 18, 18, 19, 19, 18, 19, 17, 17, 18, 17, 18, 18, 17, 19, 19}, 
        {0, 18, 19, 18, 18, 18, 18, 19, 19, 20, 20, 20, 18, 18, 20, 18, 18, 18, 19, 20}, 
        {0, 19, 19, 19, 19, 19, 21, 19, 19, 20, 20, 20, 20, 20, 21, 21, 19, 19, 19, 20}, 
        {0, 21, 21, 22, 21, 20, 21, 21, 20, 20, 20, 20, 20, 20, 21, 21, 21, 20, 20, 20}, 
        {0, 21, 22, 22, 21, 23, 21, 21, 22, 21, 21, 22, 21, 22, 21, 21, 21, 21, 21, 22}, 
        {0, 23, 22, 23, 24, 24, 24, 22, 22, 22, 22, 22, 22, 23, 23, 22, 24, 22, 22, 22}, 
        {0, 23, 25, 25, 24, 24, 24, 23, 23, 23, 23, 23, 23, 23, 23, 24, 24, 25, 25, 25}, 
        {0, 26, 25, 26, 24, 24, 26, 26, 26, 24, 26, 26, 24, 25, 24, 24, 24, 25, 25, 26}, 
        {0, 26, 25, 26, 27, 27, 27, 27, 27, 27, 27, 26, 27, 27, 25, 25, 26, 25, 25, 27}, 
        {0, 26, 27, 27, 27, 27, 28, 28, 28, 27, 27, 27, 27, 27, 27, 28, 26, 28, 26, 27}, 
        {0, 27, 27, 27, 27, 27, 28, 28, 28, 28, 27, 29, 27, 27, 27, 28, 29, 28, 29, 27}, 
        {0, 29, 28, 28, 28, 28, 29, 29, 28, 28, 28, 29, 28, 29, 28, 28, 29, 28, 29, 28}};
    testParent("BirdSmall.png", correctParent);
  }
 
}
