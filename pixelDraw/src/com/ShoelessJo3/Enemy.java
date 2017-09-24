package com.ShoelessJo3;

import static com.ShoelessJo3.Game.*;

public class Enemy extends GameObject {

    public int speed = 1;
    public int range = 20;
    public int lastDeltaDist = 0;
    public boolean seen = false;
    public int sightTime = 64;//this is how many ticks the enemy waits after it sees the player if the enemy is out of range
    public int timer = sightTime;


    public Enemy(int xh, int yh, boolean a, Sprite s)
    {
        super(xh,yh,a,s);
    }

    public void trackPlayer(Player p){
        int playDist = getDistance(p);

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

        if(seen) //&& lastDeltaDist > playDist)
        {

            //move the enemy to the player with some maths
            if(centerY > p.mapPosy)//enemy above
            {
                move(0, -speed);
            }

            if(centerY < p.mapPosy)//enemy below
            {
                move(0, speed);
            }

            if(centerX < p.mapPosx)//enemy left
            {
                move(speed,0);
            }

            if(centerX > p.mapPosx)//enemy right
            {
                move(-speed, 0);
            }
        }

        lastDeltaDist = playDist;
        //System.out.println("last delta dist: " + (centerY) + " new dist: " + p.mapPosy);

    }

    public void move(int xd, int yd){
        x += xd;
        y += yd;

    }

}
