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

    public void drawCircle(int xh, int yh, int r)
    {
        double PI = 3.1415926535;
        double i, angle, x1, y1;

        for(i = 0; i < 360; i += 0.1)
        {
            angle = i;
            x1 = r * Math.cos(angle * PI / 180);
            y1 = r * Math.sin(angle * PI / 180);
            setPixel(xh + (int)x1, yh + (int)y1, 0xffff00ff);
        }

    }

    public void drawCircle(int xh, int yh, int r, int diffusion)
    {
        double PI = 3.1415926535;
        double i, angle, x1, y1;

        for(i = 0; i < 360; i += 0.1)
        {
            angle = i;
            x1 = r * Math.cos(angle * PI / 180);
            y1 = r * Math.sin(angle * PI / 180);
            if(((int)(x1))%diffusion == 0 && ((int)y1)%diffusion == 0 )
                setPixel(xh + (int)x1, yh + (int)y1, 0xff00ff00);
        }

    }
}
