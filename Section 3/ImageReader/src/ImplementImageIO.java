import imagereader.IImageIO;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.Console;
// Import Components

/*
* Created by Emilia on 5/1, 2018
* Copyright (c) Emilia 2018
* All rights reserved
*/

public class ImplementImageIO implements IImageIO {

  private static final int BM_HEAD_LENGTH = 14;
  private static final int BM_INFO_LENGTH = 40;
  private static final int BM_LENGTH_TWO = 2;
  private static final int BM_LENGTH_FOUR = 4;

  private static final int BM_TWOFIVESIC_COLOR = 8;
  private static final int BM_TRUE_COLOR = 24;

  public Image myRead(String filePath) {
    FileInputStream file;
    try {
      file = new FileInputStream(filePath);
    } catch (FileNotFoundException e) {
      System.out.println(e);
      return (Image) null;
    }
    // Get file by the filePath
    byte bmHead[] = new byte[BM_HEAD_LENGTH];
    byte bmInfo[] = new byte[BM_INFO_LENGTH];
    // Initialize the byte array to store Head and Info of Bitmap
    byte rgbData[];
    int rgbaData[];
    // Create byte array to store rgba and rgb data
    int height, width, imageSize, pixelSize, offset, bitCount, span;
    // Variables to store characteristics of bitmap

    Image image = null;
    // Variable to store the result

    try {
      file.read(bmHead, 0, BM_HEAD_LENGTH);
      file.read(bmInfo, 0, BM_INFO_LENGTH);
      // Read bytes from the file stream

      offset = (int) (bmHead[10] & 0xff) | ((int) (bmHead[11] & 0xff) << 8) | ((int) (bmHead[12] & 0xff) << 16)
          | ((int) (bmHead[13] & 0xff) << 24);
      // Get the offset

      offset -= BM_HEAD_LENGTH + BM_INFO_LENGTH;
      // Minus the data read, and I hope the result is 0

      width = (int) (bmInfo[4] & 0xff) | ((int) (bmInfo[5] & 0xff) << 8) | ((int) (bmInfo[6] & 0xff) << 16)
          | ((int) (bmInfo[7] & 0xff) << 24);
      // Get the image width

      height = (int) (bmInfo[8] & 0xff) | ((int) (bmInfo[9] & 0xff) << 8) | ((int) (bmInfo[10] & 0xff) << 16)
          | ((int) (bmInfo[11] & 0xff) << 24);
      // Get the image height

      bitCount = (int) (bmInfo[14] & 0xff) | ((int) (bmInfo[15] & 0xff) << 8);
      // Get bitCount

      imageSize = (int) (bmInfo[20] & 0xff) | ((int) (bmInfo[21] & 0xff) << 8) | ((int) (bmInfo[22] & 0xff) << 16)
          | ((int) (bmInfo[23] & 0xff) << 24);
      // Get imageSize

      int oriIndex;

      rgbData = new byte[imageSize + offset];
      file.read(rgbData, 0, imageSize + offset);
      oriIndex = offset;

      if (bitCount == BM_TRUE_COLOR) {
        pixelSize = 3;
        rgbaData = new int[width * height];
        // Under true color mode, each pixel is three-bytes long
        span = (imageSize / height) - width * pixelSize;

        for (int row = 0; row < height; ++row) {
          for (int col = 0; col < width; ++col) {
            rgbaData[width * (height - row - 1) + col] = ((int) 255 << 24) | ((int) (rgbData[oriIndex + 2] & 0xff) << 16)
                | ((int) (rgbData[oriIndex + 1] & 0xff) << 8) | (int) (rgbData[oriIndex] & 0xff);
            oriIndex += pixelSize;
          }
          oriIndex += span;
        }

        image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, rgbaData, 0, width));
      } else if (bitCount == BM_TWOFIVESIC_COLOR) {
        pixelSize = 1;
        rgbaData = new int[width * height];
        // Under 256 colors mode, each pixel is one-byte long
        span = (imageSize / height) - width * pixelSize;

        for (int row = 0; row < height; ++row) {
          for (int col = 0; col < width; ++col) {
            rgbaData[width * (height - row - 1) + col] = ((int) 255 << 24) | // alpha
                ((int) (rgbData[oriIndex] & 0xff) << 16) | // red
                ((int) (rgbData[oriIndex] & 0xff) << 8) | // green
                (int) (rgbData[oriIndex] & 0xff); // blue
            ++oriIndex;
          }
          oriIndex += span;
        }

        image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, rgbaData, 0, width));
      }
    } catch (IOException e) {
      System.out.println(e);
    }
    try {
      file.close();
    } catch (IOException e) {
      System.out.println(e);
    }
    return image;
  }

  public Image myWrite(Image image, String filePath) {
    int height = image.getHeight(null), width = image.getWidth(null);
    int fileType = BufferedImage.TYPE_3BYTE_BGR;

    BufferedImage bi = new BufferedImage(width, height, fileType);
    bi.getGraphics().drawImage(image, 0, 0, null);

    try {
      File file = new File(filePath + ".bmp");
      ImageIO.write(bi, "bmp", file);
    } catch (IOException e) {
      System.out.println(e);
    }

    return image;
  }
}
