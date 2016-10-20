import java.awt.*;
import java.io.IOException;

/**
 * Created by zhantong on 2016/10/20.
 */
public class GenericBarcode {
    private DrawBarcode barcode;
    private int marginLength;
    private int borderWidth;
    private int contentLength;
    public GenericBarcode(int marginLength,int borderWidth,int contentLength,int pixelsPerBlockLength,int drawType){
        this.marginLength=marginLength;
        this.borderWidth=borderWidth;
        this.contentLength=contentLength;
        int totalLength=marginLength*2+borderWidth*2+contentLength;
        barcode=new DrawBarcode(totalLength,totalLength,pixelsPerBlockLength,drawType);
        drawBorders();
    }
    private void drawBorders(){
        barcode.setColor(Color.BLACK);
        barcode.fillRect(marginLength,marginLength,contentLength+2*borderWidth,borderWidth);
        barcode.fillRect(marginLength,marginLength+borderWidth+contentLength,contentLength+2*borderWidth,borderWidth);
        barcode.fillRect(marginLength,marginLength,borderWidth,contentLength+2*borderWidth);
        barcode.fillRect(marginLength+borderWidth+contentLength,marginLength,borderWidth,contentLength+2*borderWidth);
    }
    public void setColor(Color color){
        barcode.setColor(color);
    }
    public void drawContentBlock(int x,int y){
        barcode.fillRect(marginLength+borderWidth+x,marginLength+borderWidth+y);
    }
    public void drawContentBlock(int x,int y, Color color){
        barcode.setColor(color);
        barcode.fillRect(marginLength+borderWidth+x,marginLength+borderWidth+y);
    }
    public void drawContent(int content[][]){
        for(int y=0;y<contentLength;y++){
            for(int x=0;x<contentLength;x++){
                Color color=data2Color(content[y][x]);
                drawContentBlock(x,y,color);
            }
        }
    }
    public Color data2Color(int data){
        return null;
    }
    public void save(String filePath) throws IOException{
        barcode.save(filePath);
    }
}
