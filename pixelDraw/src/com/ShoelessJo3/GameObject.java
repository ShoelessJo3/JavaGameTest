package com.ShoelessJo3;
//game object should contain x and y, visible or not and other properties id?
//if game object is visible, draw to screen every tick, if not don't

import com.ShoelessJo3.Entities.Enemy;
import com.ShoelessJo3.Entities.Player;
import javafx.animation.Animation;

import java.awt.*;

import static com.ShoelessJo3.Game.*;


public class GameObject {
    public boolean active;
    public int x;
    public int y;
    public Sprite sprite;
    public int centerX;
    public int centerY;
    public int mapPosy;
    public int mapPosx;
    public int width;
    public int height;
    public boolean vertical;
    public boolean horizontal;
    public boolean isTop;
    public boolean isLeft;
    public boolean breakLoop;
    public GameAnimation animate;
    public boolean isAnimated = false;





    public GameObject(int xh, int yh, boolean a, Sprite s)
    {
        this.x = xh;
        this.y = yh;
        mapPosx = yh;
        mapPosy = xh;
        this.active = a;
        this.sprite = s;
        this.width = s.width;
        this.height = s.height;
        this.centerX = x - width/2;
        this.centerY = y - height/2;
    }

    public GameObject(int xh, int yh, boolean a, GameAnimation ani) {
        this.x = xh;
        this.y = yh;
        mapPosx = yh;
        mapPosy = xh;
        this.active = a;
        //this.sprite = s;
        this.width = ani.widthSprite;
        this.height = ani.heightSprite;
        this.centerX = x - width/2;
        this.centerY = y - height/2;
        animate = ani;
        isAnimated = true;
    }

    public void render(Screen screen) {



            if (!active) {
                return;
            }
            // render coordinates

           ;
        if(isAnimated)
        {
            animate.play();
            sprite = animate.frames.get(animate.currentFrame);
        }

            int rx = this.x;
            int ry = this.y;
            this.centerX = x + sprite.width / 2;
            this.centerY = y + sprite.height / 2;
            screen.draw(rx, ry, sprite);


    }

    public void offsetRender(Screen s){

        if (!active)
        {
            return;
        }
        this.centerX = this.x + sprite.width/2;
        this.centerY = this.y + sprite.height/2;
        if(!isAnimated) {
            s.draw(this.x + offsetX, this.y + offsetY, sprite);
        }
        else
        {
            animate.play();
            s.draw(this.x + offsetX, this.y + offsetY, animate.frames.get(animate.currentFrame));
        }

        //System.out.println("offsetX: " + offsetX + " offsetY: " + offsetY);
        this.mapPosx = this.x;
        this.mapPosy = this.y;
    }

    public int getDistance(GameObject g){
        return (int)Math.sqrt(Math.pow((double)(g.centerX - (centerX + offsetX)), 2) + Math.pow((double)(g.centerY-(centerY + offsetY)),2));
    }

    public double getAngle(GameObject g){
        double upperDist = ((double)(g.centerY-(centerY + offsetY)));
        double lowerDist = (double)(g.centerX - (centerX + offsetX));
        //System.out.println("upper dist " + upperDist + " lower dist: " + lowerDist);

        double addAngle = 0;

        if( upperDist < 0 && lowerDist > 0)
        {
            addAngle += 0;
            return Math.atan(Math.abs(upperDist)/Math.abs(lowerDist))*180/3.14 + addAngle;
        }

        if( upperDist < 0 && lowerDist < 0)
        {
            addAngle += 90;
            return Math.atan(Math.abs(lowerDist)/Math.abs(upperDist))*180/3.14 + addAngle;
        }

        if( upperDist > 0 && lowerDist < 0)
        {
            addAngle += 180;
            return Math.atan(Math.abs(upperDist)/Math.abs(lowerDist))*180/3.14 + addAngle;
        }



        if(upperDist > 0 && lowerDist > 0) {
            addAngle += 270;
            return Math.atan(Math.abs(lowerDist)/Math.abs(upperDist))*180/3.14 + addAngle;
        }

        return 0;

    }

    public void tick() {
        if (!active) return;

    }

    public void resetCollisions()
    {
        vertical = false;
        horizontal = false;
        isLeft = false;
        isTop = false;
        breakLoop = false;
    }

    public void checkCollisions(GameObject g2) {

        Rectangle r3 = getBounds();
        Rectangle r2 = g2.getBounds();



        if (r3.intersects(r2)) {
            // Determine the intersect of the collision...
            Rectangle insect = r3.intersection(r2);

            // Flags...
            boolean curHorizontal = horizontal, curVertical = vertical, curIsLeft = isLeft, curIsTop = isTop;
            int rightIntersect = r2.x - r3.x;
            int topIntersect = r2.y - r3.y;
            int leftIntersect = r3.x + r3.width - (r2.x + r2.width);
            int bottomIntersect = r3.y + r3.height - (r2.y + r2.height);//now find out which has the greatest value..



            int[] intersections = {rightIntersect, topIntersect, leftIntersect, bottomIntersect};


            int maxVal = rightIntersect, maxIndex = 0;

            for(int i = 0; i < 4; i++)
            {
                if(maxVal < intersections[i])
                {
                    maxVal = intersections[i];
                    maxIndex = i;
                }
                else if(maxVal == intersections[i] && i!= 0 && leftIntersect != rightIntersect)
                {
                    breakLoop = true;
                }//if two of the values are the same, then prevent anything from happening
            }

           // System.out.println();

            /*
            // Left side...
            if (insect.x == r3.x) {
                curHorizontal = true;
                curIsLeft = true;
                //System.out.println("left");
                // Right side
            } else if (insect.x + insect.width == r3.x + r3.width) {
                curHorizontal = true;

            }
            // Top
            if (insect.y == r3.y) {
                curVertical = true;
                curIsTop = true;
                //System.out.println("top");
                // Bottom
            } else if (insect.y + insect.height == r3.y + r3.height) {
                curVertical = true;
            }
            */
            //System.out.println("Maxdex " + maxIndex + " rightInt " + rightIntersect + " leftInt " + leftIntersect + " topInt " + topIntersect + " bottomInt " + bottomIntersect);

            if(maxIndex == 0)//that means the player is to the right
            {
                curHorizontal = true;
                curIsLeft = false;
            }

            if(maxIndex == 1)//player on the top
            {
                curVertical = true;
                curIsTop = false;
            }

            if(maxIndex == 2)//player to the left
            {
                curHorizontal = true;
                curIsLeft = true;
            }

            if(maxIndex == 3)//player to the bottom
            {
                curVertical = true;
                curIsTop = true;
            }


            if(!breakLoop) {
                horizontal = curHorizontal;
                vertical = curVertical;
                isTop = curIsTop;

                isLeft = curIsLeft;
            }

            // return the closest intersection
            //System.out.println(intersect_diffs.indexOf(Collections.Min(intersect_diffs)));

            //find min intersect difference




        }

    }


    public Rectangle getBounds(){
        return new Rectangle(scale*(x + offsetX+1), scale*(y + offsetY+1),scale*(width), scale*(height));
    }





}
