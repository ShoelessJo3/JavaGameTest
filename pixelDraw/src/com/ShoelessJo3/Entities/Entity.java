package com.ShoelessJo3.Entities;
import com.ShoelessJo3.Game;
import com.ShoelessJo3.GameAnimation;
import com.ShoelessJo3.GameObject;
import com.ShoelessJo3.Sprite;
import javafx.animation.Animation;

import static com.ShoelessJo3.Game.frameCount;

public class Entity extends GameObject {

    public int RIGHT = 0;
    public int LEFT = 1;
    public int UP = 2;
    public int DOWN = 3;
    public int directionX = RIGHT;
    public int lastDirectionX = RIGHT;
    public int directionY = UP;
    public int lastDirectionY = DOWN;
    public int speed = 1;
    public int speedDiv = 2;

    //add a bounding box to this
    //add bounding box to the method, if hit top if hit bottom etc

    public Entity(int xh, int yh, boolean a, Sprite s, int sp, int sD)
    {
        super(xh,yh,a, s);
        speed = sp;
        speedDiv = sD;

    }

    public Entity(int xh, int yh, boolean a, GameAnimation an, int sp)
    {
        super(xh,yh,a, an);
        speed = sp;
    }

    public Entity(int xh, int yh, boolean a, Sprite s, int sp)
    {
        super(xh,yh,true, s);
        speed = sp;
        speedDiv = 1;

    }



    public Entity(int xh, int yh, boolean a, Sprite s)
    {
        super(xh, yh, true, s);
        speed = 0;
        speedDiv = 1;
    }
    public void move(int d){

        int sX = 0;
        int sY = 0;

        if(d == RIGHT && frameCount%speedDiv == 0 && !(horizontal && !isLeft))
        {
            sX += speed;
            directionX = d;

        }


        if(d == LEFT && frameCount%speedDiv == 0 && (!horizontal || !isLeft))
        {
            sX -= speed;
            directionX = d;
        }
        if(d == UP && frameCount%speedDiv == 0 && !(vertical&&isTop))
        {
            sY -= speed;
            directionY = d;
        }
        if(d == DOWN && frameCount%speedDiv == 0 && !(vertical&&!isTop))
        {
            sY += speed;
            directionY = d;
        }

        if(!(breakLoop && Math.abs(sY) == Math.abs(sX)))
        {
            x += sX;
            y += sY;
        }

        if(frameCount%2 != 0)
        {
            x += sX;
        }
        else
        {
            y += sY;
        }
    }

}
