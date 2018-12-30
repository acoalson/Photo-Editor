package edu.hmc.photo.filter;

import org.junit.Test;

import edu.hmc.photo.testUtil.ImageTest;

public class EnergyTests extends ImageTest {
  @Test
  public void testMicroEnergy() {
    testImageFilter("Micro.png", "MicroEnergy.png", new Energy());
  }

  @Test
  public void testTinyEnergy() {
    testImageFilter("Tiny.png", "TinyEnergy.png", new Energy());
  }

  @Test
  public void testBirdEnergy() {
    testImageFilter("Bird.png", "BirdEnergy.png", new Energy());
  }

  @Test
  public void testWaterEnergy() {
    testImageFilter("Water.png", "WaterEnergy.png", new Energy());
  }
}
