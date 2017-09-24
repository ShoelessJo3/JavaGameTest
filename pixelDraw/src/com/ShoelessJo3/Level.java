package com.ShoelessJo3;

public class Level{

    public int[] pixelsLoad;
    public int width;
    public int height;

    public Level(int w, int h)
    {
        width = w;
        height = h;
        pixelsLoad = new int[width*height];
    }

    public void addScreen(int sx, int sy, GameObject obj)
    {
        for(int x = 0; x < obj.width; x++)
        {
            for(int y = 0; y < obj.height; y++)
            {
                int c = obj.sprite.getPixel(x,y);
                if(c != obj.sprite.removePixel || !obj.sprite.removeCol)
                    pixelsLoad[x + sx + (y + sy)*width] = c;
            }
        }
    }

    public void render(Screen s, int offsetX, int offsetY)
    {   int[] holderArray = new int[s.width*s.height];

        for(int x = 0; x < s.width; x++)
        {
            for(int y = 0; y < s.height; y++)
            {
                holderArray[x + y*s.width] = pixelsLoad[(x+offsetX) + (y+offsetY)*width];
            }
        }
        s.setScreen(holderArray);
        s.render();
    }

}
