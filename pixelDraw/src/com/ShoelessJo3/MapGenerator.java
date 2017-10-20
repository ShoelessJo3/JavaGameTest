package com.ShoelessJo3;

import com.ShoelessJo3.Sprite;

import java.util.Random;

import static com.ShoelessJo3.Game.frameCount;
import static java.lang.Math.random;

public class MapGenerator {

    public Sprite mapGen(Sprite s)
    {

        int scale = 1;
        for(int x = 0; x < s.width; x+= scale) {
            for (int y = 0; y < s.height; y += scale)
            {

                int colo = (int)(10000*Math.cos(x*y));//new Random((int)(Math.sin(y)*15.0)).nextInt(0xffffff);//(int)(Math.random()*100) + 10000;
                    for(int xi = 0; xi < scale; xi++)
                        for(int yi = 0; yi < scale; yi++)
                        {
                            if(y + yi < s.height - 1)
                                s.setPixel(x + xi, y + yi, colo);
                        }


            }
        }

        return s;
    }

    public Sprite mapFill(Sprite s, Sprite background)
    {
        for(int x = 0; x < s.width; x+= background.width) {
            for (int y = 0; y < s.height ; y += background.height)
            {

                s.draw(x,y,background);

                if(Math.random() > .5)
                {
                    background.flipHorizontal();
                }
                else
                {
                    background.flipVertical();
                }


            }
        }
        return s;
    }




}
