package com.ShoelessJo3.Entities;

import com.ShoelessJo3.Game;
import com.ShoelessJo3.Sprite;

public class Projectile extends Entity {
    public int direction;
    public int speedX;
    public int speedY;

    public Projectile(int xh, int yh, Sprite s, int spdX, int spdY) {
        super(xh, yh, true, s, 0);
        speedX = spdX;
        speedY = spdY;


        mapPosx = xh + s.width / 2 + Game.width / 2;
        mapPosy = yh + s.height / 2 + Game.height / 2;
    }

    public void getDestroyed()
    {
        if(horizontal || vertical)
        {
            active = false;
        }
    }

    public void move()
    {

            x += speedX;

        y+= speedY;
    }
}
