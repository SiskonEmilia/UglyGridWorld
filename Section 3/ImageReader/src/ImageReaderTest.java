import static org.junit.Assert.*;
import org.junit.*;
import imagereader.IImageIO;
import imagereader.IImageProcessor;

import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImageReaderTest {

  private IImageIO imageioer;
  private IImageProcessor processor;
  private static final String TESTBMP = "../test/1.bmp";

  static boolean pixelEqual(BufferedImage ori, BufferedImage tar) {
    boolean result = true;
    for (int i = 0; i < ori.getHeight() && result; ++i) {
      for (int t = 0; t < ori.getWidth() && result; ++t) {
        result = ori.getRGB(t, i) == tar.getRGB(t, i);
      }
    }
    return result;
  }

  @Before
  public void init() {
    imageioer = new ImplementImageIO();
    processor = new ImplementImageProcessor();
  }

  @Test
  public void readTest() throws IOException, FileNotFoundException {
    Image image1 = null, image2 = null;

    image1 = imageioer.myRead(TESTBMP);
    image2 = imageioer.myRead("../test/2.bmp");

    FileInputStream file1 = null, file2 = null;

    file1 = new FileInputStream(TESTBMP);
    file2 = new FileInputStream("../test/2.bmp");

    BufferedImage oriImage1 = null, oriImage2 = null;

    oriImage1 = ImageIO.read(file1);
    oriImage2 = ImageIO.read(file2);

    BufferedImage tarImage1 = new BufferedImage(image1.getWidth(null), image1.getHeight(null),
        BufferedImage.TYPE_INT_BGR),
        tarImage2 = new BufferedImage(image2.getWidth(null), image2.getHeight(null), BufferedImage.TYPE_INT_BGR);

    tarImage1.getGraphics().drawImage(image1, 0, 0, image1.getWidth(null), image1.getHeight(null), null);

    tarImage2.getGraphics().drawImage(image2, 0, 0, image2.getWidth(null), image2.getHeight(null), null);

    assertEquals(oriImage1.getHeight(), tarImage1.getHeight());
    assertEquals(oriImage2.getHeight(), tarImage2.getHeight());
    // Is heights equals?
    assertEquals(oriImage1.getWidth(), tarImage1.getWidth());
    assertEquals(oriImage2.getWidth(), tarImage2.getWidth());
    // Is widths equals?
    assertEquals(pixelEqual(oriImage1, tarImage1), true);
    assertEquals(pixelEqual(oriImage2, tarImage2), true);
    // Is every pixel the same?
  }

  @Test
  public void channelRTest() throws IOException, FileNotFoundException {
    Image image = null;

    image = imageioer.myRead(TESTBMP);

    image = processor.showChanelR(image);

    FileInputStream file = null;

    file = new FileInputStream("../test/goal/1_red_goal.bmp");

    BufferedImage oriImage = null;

    oriImage = ImageIO.read(file);

    BufferedImage tarImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_BGR);

    tarImage.getGraphics().drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);

    assertEquals(oriImage.getHeight(), tarImage.getHeight());
    assertEquals(oriImage.getWidth(), tarImage.getWidth());

    assertEquals(pixelEqual(oriImage, tarImage), true);
  }

  @Test
  public void channelGTest() throws IOException, FileNotFoundException {
    Image image = null;

    image = imageioer.myRead(TESTBMP);

    image = processor.showChanelG(image);

    FileInputStream file = null;

    file = new FileInputStream("../test/goal/1_green_goal.bmp");

    BufferedImage oriImage = null;

    oriImage = ImageIO.read(file);

    BufferedImage tarImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_BGR);

    tarImage.getGraphics().drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);

    assertEquals(oriImage.getHeight(), tarImage.getHeight());
    assertEquals(oriImage.getWidth(), tarImage.getWidth());

    assertEquals(pixelEqual(oriImage, tarImage), true);
  }

  @Test
  public void channelBTest() throws IOException, FileNotFoundException {
    Image image = null;

    image = imageioer.myRead(TESTBMP);

    image = processor.showChanelB(image);

    FileInputStream file = null;
    file = new FileInputStream("../test/goal/1_blue_goal.bmp");

    BufferedImage oriImage = null;

    oriImage = ImageIO.read(file);

    BufferedImage tarImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_BGR);

    tarImage.getGraphics().drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);

    assertEquals(oriImage.getHeight(), tarImage.getHeight());
    assertEquals(oriImage.getWidth(), tarImage.getWidth());

    assertEquals(pixelEqual(oriImage, tarImage), true);
  }

  @Test
  public void grayTest() throws IOException, FileNotFoundException {
    Image image = null;

    image = imageioer.myRead(TESTBMP);

    image = processor.showGray(image);

    FileInputStream file = null;

    file = new FileInputStream("../test/goal/1_gray_goal.bmp");

    BufferedImage oriImage = null;

    oriImage = ImageIO.read(file);

    BufferedImage tarImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_BGR);

    tarImage.getGraphics().drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);

    assertEquals(oriImage.getHeight(), tarImage.getHeight());
    assertEquals(oriImage.getWidth(), tarImage.getWidth());

    assertEquals(pixelEqual(oriImage, tarImage), true);
  }
}