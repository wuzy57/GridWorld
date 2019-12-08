import imagereader.IImageIO;
import imagereader.IImageProcessor;
import imagereader.Runner;
import java.awt.Image;
import java.io.*;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.Color.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImplementImageProcessor implements IImageProcessor {
	
//red
  public Image showChanelR(Image sourceImage) {
    BufferedImage bfi = new BufferedImage(sourceImage.getWidth(null), sourceImage.getHeight(null), BufferedImage.TYPE_INT_RGB);  
    bfi.getGraphics().drawImage(sourceImage, 0, 0, null);  
    for (int y = 0; y < bfi.getHeight(); y++) {
      for (int x = 0; x < bfi.getWidth(); x++) {  
            Color pixel = new Color(bfi.getRGB(x, y));
            bfi.setRGB(x, y, new Color(getRed(pixel), 0, 0).getRGB());  
      }
    }
        
    return bfi;
  }
//green
  public Image showChanelG(Image sourceImage) {
    BufferedImage bfi = new BufferedImage(sourceImage.getWidth(null), sourceImage.getHeight(null), BufferedImage.TYPE_INT_RGB);  
    bfi.getGraphics().drawImage(sourceImage, 0, 0, null);  
    for (int y = 0; y < bfi.getHeight(); y++) {
      for (int x = 0; x < bfi.getWidth(); x++) {  
            Color pixel = new Color(bfi.getRGB(x, y));  
            bfi.setRGB(x, y, new Color(0, getGreen(pixel), 0).getRGB());  
      }
    }
        
    return bfi;
  }
//blue
  public Image showChanelB(Image sourceImage) {
    BufferedImage bfi = new BufferedImage(sourceImage.getWidth(null), sourceImage.getHeight(null), BufferedImage.TYPE_INT_RGB);  
    bfi.getGraphics().drawImage(sourceImage, 0, 0, null);  
    for (int y = 0; y < bfi.getHeight(); y++) {
      for (int x = 0; x < bfi.getWidth(); x++) {  
            Color pixel = new Color(bfi.getRGB(x, y));  
            bfi.setRGB(x, y, new Color(0, 0, getBlue(pixel)).getRGB());  
      }
    }
        
    return bfi;
  }
//转化为灰度图
  public Image showGray(Image sourceImage) {
    BufferedImage bfi = new BufferedImage(sourceImage.getWidth(null), sourceImage.getHeight(null), BufferedImage.TYPE_INT_RGB);  
    bfi.getGraphics().drawImage(sourceImage, 0, 0, null);  
    for (int y = 0; y < bfi.getHeight(); y++) {
      for (int x = 0; x < bfi.getWidth(); x++) {  
            Color pixel = new Color(bfi.getRGB(x, y));  
            bfi.setRGB(x, y, new Color(getGray(pixel), getGray(pixel), getGray(pixel)).getRGB());  
      }
    }
        
    return bfi;
  }

  public static int getGray(Color pixel) {
	  //灰度图计算公式
    return (int)(pixel.getRed() * 0.299 + pixel.getGreen() * 0.587 + pixel.getBlue() * 0.114);
  }
  public static int getRed(Color pixel) {
    return pixel.getRed();
  }
  public static int getGreen(Color pixel) {
    return pixel.getGreen();
  }
  public static int getBlue(Color pixel) {
    return pixel.getBlue();
  }
}
