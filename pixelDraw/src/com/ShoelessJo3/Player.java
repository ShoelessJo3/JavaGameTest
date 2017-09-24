package com.ShoelessJo3;
//import com.ShoelessJo3.Sprite;


import static com.ShoelessJo3.Game.offsetX;

public class Player extends GameObject{

    public int speed = 3;
    public int speedDivisor = 1;//if divisor 1s then no divisor
    public int curSpeed;
    private int RIGHT = 0;
    private int LEFT = 1;
    public int direction = RIGHT;
    public int lastDirection = RIGHT;
    public int mX = 0;
    public int mY = 0;

    public Player(int xh, int yh, Sprite s)
    {
        super(xh,yh,true, s);
        mapPosx = xh + s.width/2 + Game.width/2;
        mapPosy = yh + s.height/2 + Game.height/2;
    }
    //add a flip method to sprite

    public void move(InputHandler handle, boolean following)
    {
        mX = 0;
        mY = 0;
        curSpeed++;
        if(handle.up.down && curSpeed >= speedDivisor){
            mY = -speed;
            curSpeed = 0;
        }
        if(handle.down.down && curSpeed >= speedDivisor){
            mY = speed;
            curSpeed = 0;
        }
        if(handle.right.down && curSpeed >= speedDivisor){
            mX = speed;
            curSpeed = 0;
            direction = RIGHT;
        }
        if(handle.left.down && curSpeed >= speedDivisor){
            mX = -speed;
            curSpeed = 0;
            direction = LEFT;
        }


        if(!following)
        {
            x += mX;
            y += mY;
        }

       // mapPosx += mX;
        //mapPosy += mY;

        //System.out.println("player x: " + mapPosx + " player y: " + mapPosy);

        handle.mouseLoc();
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
        if(direction != lastDirection) {
            sprite.flipHorizontal();
        }

        lastDirection = direction;
    }


}
