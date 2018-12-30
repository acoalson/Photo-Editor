package edu.hmc.photo.filter;

import org.junit.Test;

import edu.hmc.photo.testUtil.ImageTest;

public class FlipTests extends ImageTest {	

	@Test
	public void testHorizontal1() {
		testImageFilter("Bird.png", "BirdHorizontal.png", new FlipHorizontal());
	}
	
	@Test
	public void testHorizontal2() {
	  testImageFilter("Bird.png", "Bird.png", new FlipHorizontal(), 2);
	}
	
	@Test
	public void testVertical1() {
		testImageFilter("Bird.png", "BirdVertical.png", new FlipVertical());
	}
	
	@Test
	public void testVertical2() {
		testImageFilter("Bird.png", "Bird.png", new FlipVertical(), 2);
	}
}
