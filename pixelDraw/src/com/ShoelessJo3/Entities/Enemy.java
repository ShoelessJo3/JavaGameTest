package com.ShoelessJo3.Entities;

import com.ShoelessJo3.Game;
import com.ShoelessJo3.Sprite;

import java.awt.*;

import static com.ShoelessJo3.Game.*;

public class Enemy extends Entity {

    public int range = 50;
    public int lastDeltaDist = 0;
    public boolean seen = false;
    public int sightTime = 240;//this is how many ticks the enemy waits after it sees the player if the enemy is out of range
    public int timer = sightTime;
    public int enemyMinDist = 1;
    public int speed_div = 2;

    public Enemy(int xh, int yh, boolean a, Sprite s)
    {
        super(xh,yh,a,s, 1, 4);

    }

    public void trackPlayer(Player p){
        int playDist = getDistance(p);
        double playAngle = getAngle(p);

        seen = playDist < range;


        if(timer > sightTime && !seen)
        {
            //check timer
            seen = false;

        }
        else if (seen)
        {
            timer = 0;
        }
        else if (timer < sightTime) {
            seen = true;
        }

       // System.out.println("following stopped: " + timer);


        timer++;
        boolean moveVert = false;
        boolean moreHoriz = true;

        if(seen && playDist > enemyMinDist) //&& lastDeltaDist > playDist)
        {

            //move the enemy to the player with some maths
            if(centerY > p.mapPosy && frameCount%2 == 0)//enemy above
            {
                move(UP);
            }

            if(centerY < p.mapPosy && frameCount%2 == 0)//enemy below
            {
                move(DOWN);
            }

            if(centerX < p.mapPosx)// || frameCount%2 == 0))//enemy left
            {
                move(RIGHT);

            }

            if(centerX > p.mapPosx)//|| frameCount%2 == 0))//enemy right
            {
                move(LEFT);
            }

        }

        lastDeltaDist = playDist;
        //System.out.println("last delta dist: " + (centerY) + " new dist: " + p.mapPosy);

        if(directionX != lastDirectionX) {
            sprite.flipHorizontal();
        }

        lastDirectionX = directionX;




    }


    public Rectangle getBounds(){
        return new Rectangle(scale*(x + offsetX), scale*(y + offsetY),scale*(width+1), scale*(height+1));
    }





}
