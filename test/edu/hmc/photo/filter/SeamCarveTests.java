package edu.hmc.photo.filter;

import org.junit.Test;

import edu.hmc.photo.testUtil.ImageTest;

public class SeamCarveTests extends ImageTest {
  @Test
  public void testMicroCarveSeam() {
    testImageFilter("Micro.png", "MicroCarveSeam.png", new CarveSeam());
  }

  @Test
  public void testTinyCarveSeam() {
    testImageFilter("Tiny.png", "TinyCarveSeam.png", new CarveSeam());
  }

  @Test
  public void testBirdCarveSeam() {
    testImageFilter("Bird.png", "BirdCarveSeam.png", new CarveSeam());
  }

  @Test
  public void testWaterCarveSeam() {
    testImageFilter("Water.png", "WaterCarveSeam.png", new CarveSeam());
  }
}
