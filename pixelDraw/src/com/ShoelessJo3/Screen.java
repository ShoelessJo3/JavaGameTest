package com.ShoelessJo3;

public class Screen {

    public int width;
    public int height;
    public int[] pixels;
    public int framecount;
     //what part of the screen are we looking at


    public int screenOffsetx;
    public int screenOffsety;

    public Screen(int w, int h, int[] p){
        pixels = p;
        width = w;
        height = h;
    }

    public void setScreen(int[] p){
        pixels = p;
    }
    public void setPixel(int x, int y, int c)
    {
        if(x < width && y < height && x >= 0 && y >= 0)
        {
            pixels[x + y*width] = c;
        }
    }

    public void clear(int c)
    {
        for(int i = 0; i < pixels.length; i++)
        {
            pixels[i] = c;
        }
    }

    public void draw(int x0, int y0, Sprite s){
        for(int x = 0; x < s.width; x++)
        {
            for(int y = 0; y < s.height; y++)
            {
                int c = s.getPixel(x,y);
                if(c != s.removePixel || !s.removeCol)
                    setPixel(x + x0, y +y0, c);
            }
        }


    }

    public void render(){
        framecount++;
        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                int i = x + y * width;
                int total = width * height;
                setPixel(x,y,(int) ((i / (float)total) * 0xffffff));
            }
        }
    }
}
