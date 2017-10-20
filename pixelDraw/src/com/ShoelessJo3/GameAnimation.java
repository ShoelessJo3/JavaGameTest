package com.ShoelessJo3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.ShoelessJo3.Game.frameCount;

public class GameAnimation extends Sprite{

    public int heightSprite;
    public int widthSprite;
    public ArrayList<Sprite> frames = new ArrayList<Sprite>();
    public int currentFrame = 0;
    public int numFrames;
    public int delay;
    public String pathS;
    public double originFPS;



    public GameAnimation(int h, int w, int nf, String path, boolean r, double fps)
    {

        super(h,w,r);
        originFPS = (double)fps;
        pathS = path;
        numFrames = nf;
        heightSprite = h;
        widthSprite = w;

        delay =(int) (fps*60);
        BufferedImage image = null;


        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.print(e);
        }

        if(image == null)
        {
            return;
        }


        this.height = image.getHeight();
        this.width = image.getWidth();

        //pixels = image.getRGB(0,0, width, height, null, 0, width);

        //removePixel = pixels[0];

        int curFrames = 0;
        System.out.println(this.height/h);
        for(int y = 0; y < this.height; y += h)
        {
            for(int x = 0; x < this.width; x += w)
            {
                if(curFrames < numFrames) {
                    Sprite sHolder = new Sprite(w, h, true);
                    sHolder.pixels = image.getRGB(x, y, w, h, null, 0, w);
                    curFrames += 1;
                    frames.add(sHolder);
                    System.out.println("added 1 to frame");
                }
            }
        }

    }
  /*  public  GameAnimation(GameAnimation anim)
    {
        this = new GameAnimation(anim);


    }*/

    public GameAnimation flipAllHorizontal()
    {
        GameAnimation holder = new GameAnimation(this.heightSprite, this.widthSprite, this.numFrames, this.pathS, this.removeCol, this.originFPS);
        for(int i = 0; i < numFrames; i++)
        {
            holder.frames.get(i).flipHorizontal();
        }
        return holder;
    }

    public void play(){
        //pixels = frames.get(currentFrame).pixels;
        if(frameCount %delay == 0) {
            currentFrame++;

            if (currentFrame >= numFrames) {
                currentFrame = 0;
            }
        }




    }//when this is triggered, shift sprite to the next frame


}
