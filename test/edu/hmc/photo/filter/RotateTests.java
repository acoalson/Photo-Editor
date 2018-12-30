package edu.hmc.photo.filter;

import org.junit.Test;

import edu.hmc.photo.testUtil.ImageTest;

public class RotateTests extends ImageTest {	

@Test
	public void testBirdLeft1() {
		testImageFilter("Bird.png", "BirdLeft.png", new RotateLeft());
	}
	
	@Test
	public void testBirdLeft2() {
	  testImageFilter("Bird.png", "BirdLeft2.png", new RotateLeft(), 2);
	}
	
	@Test
	public void testBirdLeft3() {
	 	testImageFilter("Bird.png", "BirdLeft3.png", new RotateLeft(), 3);
	}
	
	@Test
	public void testBirdLeft4() {
	  testImageFilter("Bird.png", "Bird.png", new RotateLeft(), 4);
	}
	
  @Test
	public void testBirdRight1() {
		testImageFilter("Bird.png", "BirdLeft3.png", new RotateRight());
	}
	
	@Test
	public void testBirdRight2() {
	  testImageFilter("Bird.png", "BirdLeft2.png", new RotateRight(), 2);
	}
	
	@Test
	public void testBirdRight3() {
	  testImageFilter("Bird.png", "BirdLeft.png", new RotateRight(), 3);
	}
	
	@Test
	public void testBirdRight4() {
	  testImageFilter("Bird.png", "Bird.png", new RotateRight(), 4);
	}
}
