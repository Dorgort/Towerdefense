import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Explosion extends Projektil{
    int schaden;
    private int frame = 0;
    private GifImage gifImage = new GifImage("explosion.gif");
    private int machmallangsamer = 0;
    //private boolean schadenGemacht = false;

    public void act() 
    {
        machmallangsamer++;

        if (machmallangsamer == 4 ){
            MyWorld myWorld = (MyWorld)getWorld();
            if (frame == 1 && (myWorld.sound == true)){

                Greenfoot.playSound("explosion.wav");
            }

            setImage(gifImage.getCurrentImage());
            frame++;
            if (kannSehen(Gegner.class)== true && (frame < 4)){
                //if (schadenGemacht == false ){
                    for (Object obj : getIntersectingObjects(Gegner.class))
                    {

                        Gegner gegner = (Gegner) obj;
                        gegner.setSchaden(schaden);
                    }
                    //schadenGemacht = true;
                
            }
            machmallangsamer = 0;

            if(frame > 10){            

                getWorld().removeObject(this);
            }
        }   
    }
}
