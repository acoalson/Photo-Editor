package edu.hmc.photo.filter;

import org.junit.Test;

import edu.hmc.photo.testUtil.ImageTest;

public class SeamCarveManyTests extends ImageTest {    
  @Test
  public void testCarveManyMicroSeam1() {
    testImageFilter("Micro.png", "MicroCarveSeam.png", new CarveMany(1));
  }
  
  
  @Test
  public void testCarveManyMicroSeam2() {
    testImageFilter("Micro.png", "MicroCarveSeam2.png", new CarveMany(2));
  }
  
  @Test
  public void testCarveManyTinySeam() {
    testImageFilter("Tiny.png", "TinyCarveSeam5.png", new CarveMany(5));
  }
  
  @Test
  public void testCarveManyBirdSmallSeam() {
    testImageFilter("BirdSmall.png", "BirdSmallCarveSeam5.png", new CarveMany(5));
  }

  @Test
  public void testCarveManyBirdSeam() {
    testImageFilter("Bird.png", "BirdCarveSeam5.png", new CarveMany(5));
  }
  
  @Test
  public void testCarveManyWaterSeam() {
    testImageFilter("Water.png", "WaterCarveSeam10.png", new CarveMany(10));
  }
}
