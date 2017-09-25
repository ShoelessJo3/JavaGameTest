package com.ShoelessJo3.Entities;
//import com.ShoelessJo3.Sprite;


import com.ShoelessJo3.Game;
import com.ShoelessJo3.GameObject;
import com.ShoelessJo3.InputHandler;
import com.ShoelessJo3.Sprite;

import static com.ShoelessJo3.Game.offsetX;

public class Player extends Entity {


    public int mX = 0;
    public int mY = 0;

    public Player(int xh, int yh, Sprite s)
    {
        super(xh,yh,true, s, 1, 4);
        mapPosx = xh + s.width/2 + Game.width/2;
        mapPosy = yh + s.height/2 + Game.height/2;
    }
    //add a flip method to sprite

    public void move(InputHandler handle, boolean following)
    {
        mX = 0;
        mY = 0;
        if(handle.up.down){
            moveDirection(UP);
        }
        if(handle.down.down){
            moveDirection(DOWN);
        }
        if(handle.right.down){
            moveDirection(RIGHT);
        }
        if(handle.left.down){
            moveDirection(LEFT);
        }


        if(!following)
        {
            x += mX;
            y += mY;
        }

       // mapPosx += mX;
        //mapPosy += mY;

       // System.out.println("player x: " + mapPosx + " player y: " + mapPosy + " player direction = " + directionX + directionY);

        //handle.mouseLoc();
        //System.out.println(handle.mouseX);
        /*if(handle.mouseX < centerX)
        {
            direction = LEFT;
        }
        else
        {
            direction = RIGHT;
        }
        */
        if(directionX != lastDirectionX) {
            sprite.flipHorizontal();
        }

        lastDirectionX = directionX;
    }


    public void moveDirection(int d)
    {
        mX = 0;
        mY = 0;
        if(d == RIGHT)
        {
            mX += speed;
            directionX = d;
        }
        if(d == LEFT)
        {
            mX -= speed;
            directionX = d;
        }
        if(d == UP)
        {
            mY -= speed;
            directionY = d;
        }
        if(d == DOWN)
        {
            mY += speed;
            directionY = d;
        }
    }



}
