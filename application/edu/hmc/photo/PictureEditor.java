package edu.hmc.photo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import edu.hmc.photo.filter.CarveMany;
import edu.hmc.photo.filter.CarveSeam;
import edu.hmc.photo.filter.Delta;
import edu.hmc.photo.filter.DrawSeam;
import edu.hmc.photo.filter.Energy;
import edu.hmc.photo.filter.FlipHorizontal;
import edu.hmc.photo.filter.FlipVertical;
import edu.hmc.photo.filter.Grayscale;
import edu.hmc.photo.filter.ImageFilter;
import edu.hmc.photo.filter.Luminosity;
import edu.hmc.photo.filter.Negate;
import edu.hmc.photo.filter.RotateLeft;
import edu.hmc.photo.filter.RotateRight;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A class that implements a photo-editing application.
 * 
 * @author Ben Wiedermann (benw@cs.hmc.edu)
 * 
 * Inspired by an assignment by Barb Ericson (ericson@cc.gatech.edu), with
 * modifications by:
 *   Colleen Lewis (lewis@cs.hmc.edu)
 *   Jonathan Kotker (jo_ko_berkeley@berkeley.edu)
 *   Kaushik Iyer (kiyer@berkeley.edu), George Wang (georgewang@berkeley.edu),
 *      and David Zeng (davidzeng@berkeley.edu)
 *   Zach Dodds (dodds@cs.hmc.edu)
 * 
 * see: https://docs.google.com/document/d/1CKDZKCXwOAZJJHdjs2wE3O6UGp4nYhBHbgLCTwFOd5U/edit#
 *
 */
public class PictureEditor extends Application {
  
  /** The image currently being displayed */
  private Image image;
  
  /** The component that displays the image */
  private ImageView imageView;
  
  /** The main stage for this application */
  private Stage stage;

  /** Update the application to display the provided image */
  public void showImage(Image image) {
    this.image = image;
    this.imageView.setImage(this.image);
    this.stage.sizeToScene();
  }

  /** Apply a given filter to an image. If there is no current image, do nothing */
  public void applyFilter(ImageFilter filter) {
    // If there is no current image, do nothing
    if (image == null) {
      return;
    }
    
    // If there is an image, apply the filter to the image and update the display.
    BufferedImage input = ImageUtilities.asBufferedImage(this.image);
    BufferedImage output = filter.filter(input);
    this.showImage(ImageUtilities.asImage(output));
  }

  /**
   * Displays an image that visualizes the difference between the current
   * image and one that the user chooses.
   */
  public void showDifferences() {
    BufferedImage otherImage = null;

    // Ask the user to select another image, to compare against the current one
    File file = getImageFile();
    
    // If the user chose another file, try to display the difference between
    // that file and the current one.
    if (file != null) {
      
      // Try to open the other file
      try {
        otherImage = BufferedImageUtilities.loadBufferedImage(file);
      }
      
      // If we can't open the other file, print an error and return
      catch (IOException error) {
        System.err.println(error);
        return;
      }
      
      // Apply the Delta filter to the two images, to create an image that
      // visualizes the differences between the two.
      BufferedImage bufferedImage = ImageUtilities.asBufferedImage(this.image);
      ImageFilter deltaFilter = new Delta(bufferedImage);
      BufferedImage difference = deltaFilter.filter(otherImage);
      
      // Display the visualized differences in a new window.
      Stage dialog = new Stage();
      dialog.initModality(Modality.NONE);
      dialog.initOwner(this.stage);
      VBox vbox = new VBox();
      ImageView view = new ImageView(ImageUtilities.asImage(difference));
      vbox.getChildren().add(view);
      dialog.setScene(new Scene(vbox));
      dialog.show();
    }
  }

  /** Create a file chooser with the specified title */
  private static FileChooser createImageFileChooser(String title) {
    FileChooser chooser = new FileChooser();
    chooser.setTitle(title);
    chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.png", "*.jpg", "*.bmp"),
        new FileChooser.ExtensionFilter("All Files", "*.*"));
    return chooser;
  }

  /** Ask the user to select an image file to open */
  private File getImageFile() {
    FileChooser chooser = PictureEditor.createImageFileChooser("Open image file");
    return chooser.showOpenDialog(this.stage);
  }

  /** Open and display an image */
  private void openImageFile() {
    File file = this.getImageFile();
    if (file != null) {
      try {
        Image newImage = ImageUtilities.loadImage(file);
        this.showImage(newImage);
      } catch (IOException error) {
        System.err.println(error);
      }
    }
  }

  /** Save the current image to a file */
  private void saveImageFileAs() {
    FileChooser chooser = PictureEditor.createImageFileChooser("Save image file");
    File file = chooser.showSaveDialog(this.stage);
    if (file != null) {
      try {
        ImageUtilities.saveImage(this.image, file);
      } catch (IOException error) {
        System.err.println(error);
      }
    }
  }

  /** Build the application's menu bar */
  private MenuBar buildMenus() {
    MenuBar menuBar = new MenuBar();

    // -----------------
    // File menu
    // -----------------
    Menu fileMenu = new Menu("File");

    // File > Open...
    MenuItem openItem = new MenuItem("Open...");
    openItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.META_DOWN));
    openItem.setOnAction(e -> this.openImageFile());
    fileMenu.getItems().add(openItem);

    // File > Save as...
    MenuItem saveAsItem = new MenuItem("Save as...");
    saveAsItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.META_DOWN));
    saveAsItem.setOnAction(e -> this.saveImageFileAs());
    fileMenu.getItems().add(saveAsItem);

    // File > Show differences...
    MenuItem showDifferencesItem = new MenuItem("Show differences...");
    showDifferencesItem.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.META_DOWN));
    showDifferencesItem.setOnAction(e -> this.showDifferences());
    fileMenu.getItems().add(showDifferencesItem);

    // File > Quit
    MenuItem quitItem = new MenuItem("Quit");
    quitItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.META_DOWN));
    quitItem.setOnAction(e -> Platform.exit());
    fileMenu.getItems().add(quitItem);

    // -----------------
    // Filter menu
    // -----------------
    Menu filterMenu = new Menu("Filters");

    // Filter > Negate
    MenuItem negateItem = new MenuItem("Negate");
    negateItem.setOnAction(e -> this.applyFilter(new Negate()));
    filterMenu.getItems().add(negateItem);

    // Filter > Grayscale
    MenuItem grayscaleItem = new MenuItem("Grayscale");
    grayscaleItem.setOnAction(e -> this.applyFilter(new Grayscale()));
    filterMenu.getItems().add(grayscaleItem);

    // Filter > Luminosity
    MenuItem luminosityItem = new MenuItem("Luminosity");
    luminosityItem.setOnAction(e -> this.applyFilter(new Luminosity()));
    filterMenu.getItems().add(luminosityItem);

    // Filter > FlipHorizontal
    MenuItem flipHorizontalItem = new MenuItem("Flip horizontal");
    flipHorizontalItem.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.META_DOWN));
    flipHorizontalItem.setOnAction(e -> this.applyFilter(new FlipHorizontal()));
    filterMenu.getItems().add(flipHorizontalItem);

    // Filter > FlipVertical
    MenuItem flipVerticalItem = new MenuItem("Flip vertical");
    flipVerticalItem.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.META_DOWN));
    flipVerticalItem.setOnAction(e -> this.applyFilter(new FlipVertical()));
    filterMenu.getItems().add(flipVerticalItem);

    // Filter > RotateRight
    MenuItem rotateRightItem = new MenuItem("Rotate right");
    rotateRightItem.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.META_DOWN));
    rotateRightItem.setOnAction(e -> this.applyFilter(new RotateRight()));
    filterMenu.getItems().add(rotateRightItem);

    // Filter > RotateLeft
    MenuItem rotateLeftItem = new MenuItem("Rotate left");
    rotateLeftItem.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.META_DOWN));
    rotateLeftItem.setOnAction(e -> this.applyFilter(new RotateLeft()));
    filterMenu.getItems().add(rotateLeftItem);

    // -----------------
    // Seam carving menu
    // -----------------
    Menu seamMenu = new Menu("Seam Carving");

    // Seam Carving > Energy
    MenuItem energyItem = new MenuItem("Energy");
    energyItem.setOnAction(e -> this.applyFilter(new Energy()));
    seamMenu.getItems().add(energyItem);

    // Seam Carving > Show seam
    MenuItem showSeamItem = new MenuItem("Show Seam");
    showSeamItem.setOnAction(e -> this.applyFilter(new DrawSeam()));
    seamMenu.getItems().add(showSeamItem);

    // Seam Carving > Carve seam
    MenuItem carveSeamItem = new MenuItem("Carve Seam");
    carveSeamItem.setOnAction(e -> this.applyFilter(new CarveSeam()));
    seamMenu.getItems().add(carveSeamItem);

    // Seam Carving > Carve seam
    MenuItem carveMany = new MenuItem("Carve Many");
    carveMany.setOnAction(e -> {
      int maxCarves = this.image == null ? 0 : (int) this.image.getWidth();
      int repetitions = this.getNumber(0, maxCarves, "Enter a number", "How many seams do you want to carve?");
      this.applyFilter(new CarveMany(repetitions));
    });
    seamMenu.getItems().add(carveMany);

    menuBar.getMenus().addAll(fileMenu, filterMenu, seamMenu);
    return menuBar;
  }

  /**
   * Ask the user to provide a number, within a given range. 
   * 
   * If the user enters a number smaller than the minimum, the function returns 
   * the minimum. If a user enters a number larger than the maximum, the 
   * function returns the maximum. If the user enters something other than a
   * number, the function returns the minimum.
   * 
   * Inspired by: https://code.makery.ch/blog/javafx-dialogs-official/ 
   * 
   * @param min The smallest valid number
   * @param max The largest valid number
   * @param title The title for the dialog
   * @param header The header for the box
   * @return a number, if the user enters one
   */
  private int getNumber(int min, int max, String title, String header) {
    // Open a dialog and ask for a number
    TextInputDialog dialog = new TextInputDialog(title);
    dialog.setTitle(title);
    dialog.setHeaderText(header);
    String msg = String.format("Enter a number between %d and %d, inclusive", min, max);
    dialog.setContentText(msg);
    Optional<String> result = dialog.showAndWait();
    
    // Try to parse the user-provided value into a valid number
    if (result.isPresent()) {
      try {
        int number = Integer.parseInt(result.get());
        if (number < min) {
          return min;
        } else if (number > max) {
          return max;
        } else {
          return number;
        }
      } catch (NumberFormatException e) {
        return min;
      }
    } else {
      return min;
    }
  }

  @Override
  public void start(Stage stage) {
    this.stage = stage;
    this.stage.setTitle("Photo Editor");

    this.imageView = new ImageView();

    Pane vbox = new VBox();
    vbox.getChildren().add(this.buildMenus());
    vbox.getChildren().add(this.imageView);

    this.stage.setScene(new Scene(vbox));
    stage.setMaximized(true);
    this.stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
