package com.ShoelessJo3;

import com.ShoelessJo3.Entities.Player;

public class Background extends GameObject {


    public int mapWidth;
    public int mapHeight;
    public int startX;
    public int startY;

    public Background(int xh, int yh,Sprite s)
    {
        super(xh, yh, true, s);
        Game.offsetX = xh;
        Game.offsetY = yh;
        mapWidth = s.width;
        mapHeight = s.height;
        startX = xh;
        startY = yh;
    }

    public void followCam(Player p, InputHandler i)
    {
        p.move(i, true);


        if((x < 0 || p.mX > 0)&&(x > -mapWidth + Game.width|| p.mX < 0)) {
            x -= p.mX;
            p.mapPosx += p.mX;
            Game.offsetX -= p.mX;
        }
        if ((y < 0|| p.mY > 0)&&(y > -mapHeight + Game.height|| p.mY < 0)){
            y -= p.mY;
            p.mapPosy += p.mY;
            Game.offsetY -= p.mY;
        }

        //System.out.println("y: " + y + " x: " + x);






    }



}
