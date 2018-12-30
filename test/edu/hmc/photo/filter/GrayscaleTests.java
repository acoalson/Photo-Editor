package edu.hmc.photo.filter;

import org.junit.Test;

import edu.hmc.photo.testUtil.ImageTest;

public class GrayscaleTests extends ImageTest {
  @Test
  public void testMicroGray() {
    testImageFilter("Micro.png", "MicroGray.png", new Grayscale());
  }

  @Test
  public void testTinyGray() {
    testImageFilter("Tiny.png", "TinyGray.png", new Grayscale());
  }

  @Test
  public void testBirdGray() {
    testImageFilter("Bird.png", "BirdGray.png", new Grayscale());
  }

  @Test
  public void testWaterGray() {
    testImageFilter("Water.png", "WaterGray.png", new Grayscale());
  }
}
