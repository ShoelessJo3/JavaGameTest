package com.ShoelessJo3.Entities;
import com.ShoelessJo3.Game;
import com.ShoelessJo3.GameObject;
import com.ShoelessJo3.Sprite;

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
    public int speedDiv = 1;

    public Entity(int xh, int yh, boolean a, Sprite s, int sp, int sD)
    {
        super(xh,yh,true, s);
        speed = sp;
        speedDiv = sD;

    }

    public Entity(int xh, int yh, boolean a, Sprite s, int sp)
    {
        super(xh,yh,true, s);
        speed = sp;
        speedDiv = 0;

    }
    public void move(int d){
        if(d == RIGHT && Game.frameCount%speedDiv == 0)
        {
            x += speed;
            directionX = d;
        }
        if(d == LEFT && Game.frameCount%speedDiv == 0)
        {
            x -= speed;
            directionX = d;
        }
        if(d == UP && Game.frameCount%speedDiv == 0)
        {
            y -= speed;
            directionY = d;
        }
        if(d == DOWN && Game.frameCount%speedDiv == 0)
        {
            y += speed;
            directionY = d;
        }
    }

}
