package edu.hmc.photo.filter;

import java.awt.image.BufferedImage;

public class CarveMany implements ImageFilter {

  /** The number of times to carve */
  private int repetitions;
  
  public CarveMany(int repetitions) {
    this.repetitions = repetitions;
  }
  
  @Override
  public BufferedImage filter(BufferedImage image) {
    ImageFilter carver = new CarveSeam();
    for (int i = 0; i < this.repetitions; i++) {
      image = carver.filter(image);
    }
    return image;
  }

}
