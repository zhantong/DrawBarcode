import net.sf.epsgraphics.ColorMode;
import net.sf.epsgraphics.EpsGraphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by zhantong on 2016/10/20.
 */
public class DrawBarcode {
    private int pixelsPerBlockLine;
    private int widthInBlocks;
    private int heightInBlocks;
    private int widthInPixels;
    private int heightInPixels;
    private Graphics2D g;
    private BufferedImage img;
    private ByteArrayOutputStream outputStream;

    public static final int DRAW_NORMAL = 1;
    public static final int DRAW_EPS = 2;

    private int drawType;

    public static void main(String[] args) {
        DrawBarcode barcode = new DrawBarcode(4, 4, 10, DrawBarcode.DRAW_EPS);
        try {
            barcode.save("Test.eps");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DrawBarcode(int numBlocksInWidth, int numBlocksInHeight, int pixelsPerBlockLine, int drawType) {
        this.pixelsPerBlockLine = pixelsPerBlockLine;
        this.drawType = drawType;
        widthInBlocks = numBlocksInWidth;
        heightInBlocks = numBlocksInHeight;
        widthInPixels = widthInBlocks * pixelsPerBlockLine;
        heightInPixels = heightInBlocks * pixelsPerBlockLine;
        if (drawType == DRAW_NORMAL) {
            img = new BufferedImage(widthInPixels, heightInPixels, BufferedImage.TYPE_INT_RGB);
            g = img.createGraphics();
        } else {
            outputStream = new ByteArrayOutputStream();
            try {
                g = new EpsGraphics("Title", outputStream, 0, 0, widthInPixels, heightInPixels, ColorMode.COLOR_RGB);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        clearBarcode();
    }

    private void clearBarcode() {
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, widthInPixels, heightInPixels);
    }

    public void save(String filePath) throws IOException {
        File file = new File(filePath);
        if (drawType == DRAW_NORMAL) {
            g.dispose();
            img.flush();
            String imgFormat = getExtension(filePath);
            ImageIO.write(img, imgFormat, file);
        } else {
            EpsGraphics epsG = (EpsGraphics) g;
            epsG.flush();
            epsG.close();
            outputStream.flush();
            outputStream.writeTo(new FileOutputStream(file));
        }
    }

    private static String getExtension(String str) {
        return str.substring(str.lastIndexOf('.') + 1);
    }
}
