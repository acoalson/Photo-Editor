package edu.hmc.photo.filter;

import org.junit.Test;

import edu.hmc.photo.testUtil.ImageTest;

public class NegateTests extends ImageTest {
  @Test
  public void testMicroNegate() {
    testImageFilter("Micro.png", "MicroNegate.png", new Negate());
  }

  @Test
  public void testTinyNegate() {
    testImageFilter("Tiny.png", "TinyNegate.png", new Negate());
  }

  @Test
  public void testBirdNegate() {
    testImageFilter("Bird.png", "BirdNegate.png", new Negate());
  }

  @Test
  public void testWaterNegate() {
    testImageFilter("Water.png", "WaterNegate.png", new Negate());
  }
}
