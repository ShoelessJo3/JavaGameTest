package com.ShoelessJo3;

import com.ShoelessJo3.Entities.Enemy;
import com.ShoelessJo3.Entities.Player;
import com.ShoelessJo3.Entities.Projectile;
import com.ShoelessJo3.Entities.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable{
    public static int width = 50;
    public static int height = 50;
    public static int scale = 10;
    public static int frameCount = 0;
    public static int backgroundHeight = 300;
    public static int backgroundWidth = 300;
    public Player player;
    public InputHandler input;
    public Background back;
    public static Point screenLoc;
    public static int mapWidth;
    public static int mapHeight;
    public Level level;
    public static int offsetX;
    public static int offsetY;
    public GameObject trashman;
    public ArrayList<Wall> Walls = new ArrayList<Wall>();
    public ArrayList<Enemy> Enemies = new ArrayList<Enemy>();
    public static ArrayList<Projectile> Projectiles = new ArrayList<Projectile>();
  //  public Animations animate = new Animations(8,8,3,"sprites/brickAnim.png", true, .5);



    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    Screen screen = new Screen(width, height, pixels);

    private JFrame frame;

    public MapGenerator myMapGen = new MapGenerator();
    public Game(){

        //trashman = new GameObject(0,0,true, new Sprite("sprites/playerImg.png", true));
        back = new Background(-width/2,-height/2, myMapGen.mapFill(new Sprite(backgroundHeight,backgroundWidth,true), new Sprite("sprites/mars.png", false)));
        Sprite playerS = new Sprite("sprites/playerImg.png", true);
        player = new Player(width/2 - playerS.width/2 + 2 ,height/2 - playerS.height/2 + 2, new GameAnimation(8,8,3,"sprites/playerIdle.png", true, .5),  new GameAnimation(8,8,1,"sprites/alien.png", true, .5));
        mapWidth = back.sprite.width;
        mapHeight = back.sprite.height;
        level = new Level(mapWidth, mapHeight);



        Dimension dim = new Dimension(width*scale, height*scale); //size window
        setMaximumSize(dim);
        setMinimumSize(dim); //can't resize
        setPreferredSize(dim);
        frame = new JFrame("Joe frame");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        screenLoc = frame.getLocationOnScreen();

        for (int x = 0; x < mapWidth; x++) {
            for(int y = 0; y < mapHeight; y++)
            {
                 //(Math.random() < 0.01) {
                if(x%16 == 0 && y%16 == 0 && (Math.random() < 0.3)) {
                    Wall t = new Wall(x, y, true, new Sprite("sprites/Wall.png", true));
                    if(Math.random() < .5)
                        t.sprite.flipHorizontal();
                    Walls.add(t);

                }
                else if(x%16 == 0 && y%16 == 0 && Math.random() > .9)
                {
                    Enemy e = new Enemy(x,y,true, new Sprite("sprites/alien.png", true));
                    e.range = 50;
                    e.enemyMinDist = (int)(Math.random()*10) + 5;
                    Enemies.add(e);
                }



            }

		}

    }
    public void start(){
        init(); // game
        new Thread(this).start();

    }
    public void init(){
        input = new InputHandler(this);
    }


    public void run(){
        long lastTime = System.nanoTime();
        double TICKRATE = 60.0;
        double nsPerTick = 1000000000D / TICKRATE;

        int frames = 0;
        int ticks = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        boolean active = true;


        while(true) {

            while (active) {
                long now = System.nanoTime();
                delta += (now - lastTime) / nsPerTick;
                lastTime = now;
                while (delta >= 1) {
                    ticks++;
                    tick();
                    delta -= 1;
                    frames++;
                    render();
                }
                screenLoc = frame.getLocationOnScreen();
                //System.out.println(screenLoc.getX());

                double deltaT = System.currentTimeMillis() - lastTimer;

                if (deltaT >= 1000) {
                    lastTimer += 1000;
                    System.out.println(frames + " frames " + ticks + " ticks ");
                    frames = 0;
                    ticks = 0;
                }

            }
        }

    }
    public void render(){


        BufferStrategy bs = getBufferStrategy();
        frameCount++;
        screen.clear(0);
        if(bs == null)
        {
            createBufferStrategy(3);
            requestFocus();
            return;
        }

        //screen.render();
        int x = (int) (Math.cos(frameCount / 10f) * 40) + width/2;
        int y = (int) (Math.tan(frameCount / 10f) * 40) + height/2;


        back.followCam(player,input);
        //objTest.move(input, );
        back.render(screen);
        //level.addScreen(0,0,player);
        player.render(screen);//can I create an array list of gameObj and call those all at once
        //.offsetRender(screen);
        //System.out.println("")
        //System.out.println(trashmen.get(0).getAngle(objTest));

        player.resetCollisions();



        for	(int i = 0; i < Walls.size(); i++) {


            Walls.get(i).offsetRender(screen);
            player.checkCollisions(Walls.get(i));

        }

        for(int i = 0; i < Enemies.size(); i++)
        {
            Enemies.get(i).resetCollisions();
            for(int f = 0; f < Walls.size(); f++)
            {
                Enemies.get(i).checkCollisions(Walls.get(f));
            }

            Enemies.get(i).trackPlayer(player);
            Enemies.get(i).offsetRender(screen);
        }

        //screen.drawCircle(width/2 - player.width/2 + 5,height/2 - player.height/2 + 5,);

        if(frameCount%60 == 0)
        {Projectile p = new Projectile(50,50, new Sprite("sprites/projectile.png", false), 1,0);
        Projectiles.add(p);}


        for(int i = 0; i < Projectiles.size(); i++)
        {
            Projectiles.get(i).move();
            Projectiles.get(i).offsetRender(screen);
            for(int f = 0; f < Walls.size(); f++)
            {
                Projectiles.get(i).checkCollisions(Walls.get(f));
            }

            for(int f = 0; f < Walls.size(); f++)
            {

            }

            Projectiles.get(i).getDestroyed();

        }
        /*if(objTest.horizontal)
        {
            System.out.println("horizonal");
        }*/

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);



       // g.drawRect(scale*(trashmen.get(0).x + offsetX), scale*(trashmen.get(0).y + offsetY),scale*(trashmen.get(0).width+1), scale*(trashmen.get(0).height+1));

       // g.drawRect(scale*(objTest.x+1), scale*(objTest.y+1), scale*(objTest.height), scale*(objTest.width));
        g.dispose();
        bs.show();
    }

    public void tick(){

    }


    public static void main (String[] args)
    {
        Game game = new Game();
        game.start();
    }
}
