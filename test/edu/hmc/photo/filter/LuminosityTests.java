package edu.hmc.photo.filter;

import org.junit.Test;

import edu.hmc.photo.testUtil.ImageTest;

public class LuminosityTests extends ImageTest {
  @Test
  public void testMicroLuminosity() {
    testImageFilter("Micro.png", "MicroLuminosity.png", new Luminosity());
  }

  @Test
  public void testTinyLuminosity() {
    testImageFilter("Tiny.png", "TinyLuminosity.png", new Luminosity());
  }

  @Test
  public void testBirdLuminosity() {
    testImageFilter("Bird.png", "BirdLuminosity.png", new Luminosity());
  }

  @Test
  public void testWaterLuminosity() {
    testImageFilter("Water.png", "WaterLuminosity.png", new Luminosity());
  }
}
