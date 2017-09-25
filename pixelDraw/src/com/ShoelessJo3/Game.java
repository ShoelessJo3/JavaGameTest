package com.ShoelessJo3;

import com.ShoelessJo3.Entities.Enemy;
import com.ShoelessJo3.Entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable{
    public static int width = 64;
    public static int height = 64;
    public static int scale = 10;
    public static int frameCount = 0;
    public Player objTest;
    public InputHandler input;
    public Background back;
    public static Point screenLoc;
    public static int mapWidth;
    public static int mapHeight;
    public Level level;
    public static int offsetX;
    public static int offsetY;
    public GameObject trashman;
    public ArrayList<Enemy> trashmen = new ArrayList<Enemy>();


    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    Screen screen = new Screen(width, height, pixels);

    private JFrame frame;

    public Game(){

        //trashman = new GameObject(0,0,true, new Sprite("sprites/playerImg.png", true));
        back = new Background(-width/2,-height/2, new Sprite("sprites/background2.png", false));
        Sprite playerS = new Sprite("sprites/playerImg.png", true);
        objTest = new Player(width/2 - playerS.width/2 + 5,height/2 - playerS.height/2 + 5, playerS);
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
                if(x%10 == 0 && y%10 == 0){ //(Math.random() < 0.01) {
                    Enemy t = new Enemy(x,y,true, new Sprite("sprites/playerImg.png", true));
                    trashmen.add(t);
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


        back.followCam(objTest,input);
        //objTest.move(input, );
        back.render(screen);
        level.addScreen(0,0,objTest);
        objTest.render(screen);//can I create an array list of gameObj and call those all at once
        //.offsetRender(screen);
        //System.out.println("")
        //System.out.println(trashmen.get(0).getAngle(objTest));

        for	(int i = 0; i < trashmen.size(); i++) {

            trashmen.get(i).offsetRender(screen);
            trashmen.get(i).trackPlayer(objTest);
        }
        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
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
