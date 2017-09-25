package com.ShoelessJo3;
//game object should contain x and y, visible or not and other properties id?
//if game object is visible, draw to screen every tick, if not don't

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

    public void render(Screen screen) {
        if (!active)
        {
            return;
        }
        // render coordinates

        int rx = this.x;
        int ry = this.y;
        this.centerX = x + sprite.width/2;
        this.centerY = y + sprite.height/2;
        screen.draw(rx, ry, sprite);
    }

    public void offsetRender(Screen s){

        if (!active)
        {
            return;
        }
        this.centerX = this.x + sprite.width/2;
        this.centerY = this.y + sprite.height/2;
        s.draw(this.x + offsetX, this.y + offsetY, sprite);
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

}
