import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * Created by zhantong on 2016/10/20.
 */
public class ColorBarcode extends GenericBarcode {
    public static void main(String[] args){
        int contentLength=10;
        GenericBarcode barcode=new ColorBarcode(2,1,contentLength,contentLength,DrawBarcode.DRAW_EPS);
        int[][] content=new int[contentLength][contentLength];
        Random random=new Random();
        for(int i=0;i<contentLength;i++){
            for(int j=0;j<contentLength;j++){
                content[i][j]=random.nextInt(4);
            }
        }
        barcode.drawContent(content);
        try {
            barcode.save("test.eps");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public ColorBarcode(int marginLength, int borderWidth, int contentLength, int pixelsPerBlockLength, int drawType) {
        super(marginLength, borderWidth, contentLength, pixelsPerBlockLength, drawType);
    }
    public Color data2Color(int data){
        switch (data){
            case 0:
                return Color.RED;
            case 1:
                return Color.YELLOW;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.GREEN;
        }
        return null;
    }
}
