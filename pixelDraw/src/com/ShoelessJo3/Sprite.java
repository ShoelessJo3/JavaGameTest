package com.ShoelessJo3;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite extends Component { //should player extend sprite?

    BufferedImage img;
    public int[] pixels;
    public int height;
    public int width;
    public String spritePath;
    public int removePixel;
    public boolean removeCol;

    public static Sprite test = new Sprite("sprites/testImage.png", true);

    public int getPixel(int x, int y){

        return pixels[x + y*width];
    }


    public Sprite(String p, boolean r) {
        spritePath = p;
        removeCol = r;
        BufferedImage image = null;


        try {
            image = ImageIO.read(new File(spritePath));
        } catch (IOException e) {
            System.out.print(e);
        }

        if(image == null)
        {
            return;
        }


        this.height = image.getHeight();
        this.width = image.getWidth();

        pixels = image.getRGB(0,0, width, height, null, 0, width);

        removePixel = pixels[0];


    }

    public void flipHorizontal()
    {
        int[] holderArray = new int[height*width];
        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                holderArray[width-x-1 + (y)*width] = pixels[x + y*width];
            }
        }

        pixels = holderArray;
    }

    public void flipVertical(){
        int[] holderArray = new int[height*width];
        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                holderArray[x + (height -y - 1)*width] = pixels[x + y*width];
            }
        }

        pixels = holderArray;

    }

    public Dimension getPreferredSize() {
        if (img == null) {
            return new Dimension(100, 100);
        } else {
            return new Dimension(img.getWidth(null), img.getHeight(null));
        }
    }
}
