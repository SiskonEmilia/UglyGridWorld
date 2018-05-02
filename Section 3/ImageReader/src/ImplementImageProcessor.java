import imagereader.IImageProcessor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class ImplementImageProcessor implements IImageProcessor {
  private static final int FILTER_RED = 0xffff0000;
  private static final int FILTER_GREEN = 0xff00ff00;
  private static final int FILTER_BLUE = 0xff0000ff;
  private static final int FILTER_ALPHA = 0xff000000;

  public class RedFilter extends RGBImageFilter {
    public int filterRGB(int x, int y, int rgb) {
      return rgb & FILTER_RED;
    }
  }

  public class GreenFilter extends RGBImageFilter {
    public int filterRGB(int x, int y, int rgb) {
      return rgb & FILTER_GREEN;
    }
  }

  public class BlueFilter extends RGBImageFilter {
    public int filterRGB(int x, int y, int rgb) {
      return rgb & FILTER_BLUE;
    }
  }

  public class GrayFilter extends RGBImageFilter {
    public int filterRGB(int x, int y, int rgb) {
      int red = (rgb & FILTER_RED & ~FILTER_ALPHA) >> 16,
        green = (rgb & FILTER_GREEN & ~FILTER_ALPHA) >> 8,
        blue = rgb & FILTER_BLUE & ~FILTER_ALPHA;
      int gray = (int)(0.299 * red + 0.587 * green + 0.114 * blue);

      return (rgb & FILTER_ALPHA) | (gray << 16) | (gray << 8) | gray;
    }
  }

  public Image showChanelR(Image image) {
    return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new RedFilter()));
  }

  public Image showChanelG(Image image) {
    return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new GreenFilter()));
  }

  public Image showChanelB(Image image) {
    return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new BlueFilter()));
  }

  public Image showGray(Image image) {
    return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new GrayFilter()));
  }
}