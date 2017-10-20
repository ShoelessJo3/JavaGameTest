package com.ShoelessJo3.Entities;
//import com.ShoelessJo3.Sprite;


import com.ShoelessJo3.*;


import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static com.ShoelessJo3.Game.*;

public class Player extends Entity {


    public int mX = 0;
    public int mY = 0;
    public int direction =0;
    public GameAnimation jumpAnim;
    public GameAnimation idleAnim;
    public GameAnimation walkAnimR;
    public GameAnimation walkAnimL;



    public Player(int xh, int yh, Sprite s)
    {
        super(xh,yh,true, s, 1, 4);
        mapPosx = xh + s.width/2 + Game.width/2;
        mapPosy = yh + s.height/2 + Game.height/2;
    }

    public Player(int xh, int yh, GameAnimation iani, GameAnimation wani)
    {
        super(xh, yh, true, iani, 1);
        mapPosx = xh + iani.heightSprite/2 + Game.width/2;
        mapPosy = yh + iani.widthSprite/2 + Game.height/2;


        idleAnim = iani;
        walkAnimR = wani;

        walkAnimL = wani.flipAllHorizontal();


    }





    //public Player(int xh,)
    //add a flip method to sprite

    public void move(InputHandler handle, boolean following)
    {
        mX = 0;
        mY = 0;
        handle.mouseLoc();
        double locX = handle.mouseX - x;
        double locY = handle.mouseY - y;

        if(frameCount%15 == 0 && handle.attack.down)
        {
            //hypotenuse = speed? use cos

            int distToMouse = (int)Math.sqrt(Math.pow((double)(centerX - (centerX + locX)), 2) + Math.pow((centerY-(centerY + locY)),2));

            /*locX /= distToMouse;s
            locY /= distToMouse;*/
            double distX =  Math.abs(centerX - (centerX + locX));
            double distY = Math.abs((centerY-(centerY + locY)));


           locX = 2*(locX / distToMouse);
            locY = 2*(locY / distToMouse);
            //ratio is locX/locY, multiply ratio by speed to get final?

            double rat1 = locX/locY;
            double rat2 = locX/locY;


            if(!((int)locX == 0 && (int)locY == 0) && distX != 0 && distY != 0) {
                System.out.println(" x " + locX + " y " + locY + " dist m " + distToMouse + " dist x " + distX + " dist y " + distY);
                Projectile p = new Projectile(mapPosx,mapPosy, new Sprite("sprites/projectile.png", true), (int)locX,(int)locY);
                Projectiles.add(p);
            }


        }


        animate = idleAnim;
        if(handle.up.down){
            moveDirection(UP);
            //direction = UP;
 animate = walkAnimR;
        }
        if(handle.down.down){
            moveDirection(DOWN);
            //direction = DOWN;
animate = walkAnimR;
        }
        if(handle.right.down){
            moveDirection(RIGHT);
            direction = RIGHT;
            animate = walkAnimR;
        }
        if(handle.left.down){
            moveDirection(LEFT);
            direction = LEFT;
            animate = walkAnimL;
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




        /*if(direction != lastDirectionX) {
            sprite.flipHorizontal();
        }*/

        //lastDirectionX = direction;
        //lastWalkdirection = walkDirection;

        //System.out.println("direction = " + direction + " last dir= " + lastDirectionX + " walkdir " + walkDirection);
        //lastAnimate = animate;

    }


    public void moveDirection(int d)
    {



        if(d == RIGHT  && !(horizontal && !isLeft))
        {
            mX += speed;
            directionX = d;
        }
        if(d == LEFT&& (!horizontal || !isLeft))
        {
            mX -= speed;
            directionX = d;
        }
        if(d == UP && !(vertical&&isTop))
        {
            mY -= speed;
            directionY = d;
        }
        if(d == DOWN && !(vertical&&!isTop))
        {
            mY += speed;
            directionY = d;
        }

        if( breakLoop && Math.abs(mX) == Math.abs(mY))
        {
            if(frameCount %2 == 0)
            {
                mX = 0;
            }
            else
            {
                mY = 0;
            }


        }



    }

    public Rectangle getBounds(){
        return new Rectangle(scale*(x+1), scale*(y+1), scale*(height), scale*(width));
    }







}
