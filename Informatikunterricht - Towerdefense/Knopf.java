import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Knopf extends Actor
{
    public int knopfArt = 0;

    private GreenfootImage[] images = {new GreenfootImage("soundon.png"), new GreenfootImage("soundoff.png")};
    public Knopf(int knopfArt){
        this.knopfArt = knopfArt;
        if (knopfArt == 1 ) setImage(images[0]);

    }

    public void act() 
    {
        checkKnopf();
    }    

    public void checkKnopf() {
        //Wenn gedrückt alle Sounds muten
        if (knopfArt == 1 ){

            if (Greenfoot.mousePressed(this))
            {
                MyWorld myWorld = (MyWorld) getWorld();
                if (getImage() == images[0])
                {
                    //sound off
                    setImage(images[1]);
                    myWorld.sound = false;
                }
                else if (getImage() == images[1] )
                {
                    //sound on
                    setImage(images[0]);
                    myWorld.sound = true;
                    //myWorld.sound = true;//getWorld().unmute();
                }
            }

        }

        //INS HAUPTMENU KNOPF
        else if (knopfArt == 2 ){
            setImage("home.png");
            if (Greenfoot.mousePressed(this))
            {
                Greenfoot.setWorld(new Hauptmenu());
            }

        }

        //SPIELEN KNOPF
        else if (knopfArt == 3 ){
            setImage("spielen.PNG");
            if (Greenfoot.mousePressed(this))
            {
                Greenfoot.delay(20);
                Greenfoot.setWorld(new MyWorld());
            }

        }
        //BEENDEN
        else if (knopfArt == 4 ){
            setImage("beenden.PNG");
            if (Greenfoot.mousePressed(this))
            {
                System.out.println("Beendet!");
                
                Greenfoot.stop();
            }

        }
        //STEUERUNG ERKÄREN
        else if (knopfArt == 5 ){
            setImage("steuerung.PNG");
            if (Greenfoot.mousePressed(this))
            {
                System.out.println("Macht noch nichts!");
            }

        }
        else if (knopfArt == 6 ){
            setImage("boss.png");
            if (Greenfoot.mousePressed(this))
            {
                
                getWorld().addObject( BossGegner.getInstanz(), 20, 10);
            }
            MyWorld  world = (MyWorld) getWorld();

            if (world.lebenCounter.getValue() == 0) {
                Greenfoot.playSound("verloren.wav");
                Greenfoot.delay(100);
                
                Greenfoot.stop();
            }

        }
    }
}
