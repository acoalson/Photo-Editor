package edu.hmc.photo.filter;

import org.junit.Test;

import edu.hmc.photo.testUtil.ImageTest;

public class SeamDrawTests extends ImageTest {
  @Test
  public void testMicroDrawSeam() {
    testImageFilter("Micro.png", "MicroDrawSeam.png", new DrawSeam());
  }

  @Test
  public void testTinyDrawSeam() {
    testImageFilter("Tiny.png", "TinyDrawSeam.png", new DrawSeam());
  }

  @Test
  public void testBirdDrawSeam() {
    testImageFilter("Bird.png", "BirdDrawSeam.png", new DrawSeam());
  }

  @Test
  public void testWaterDrawSeam() {
    testImageFilter("Water.png", "WaterDrawSeam.png", new DrawSeam());
  }
}
