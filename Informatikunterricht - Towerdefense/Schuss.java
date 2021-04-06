import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Schuss extends Projektil
{
    int maxReichweite = 0;
    int schaden;


    public void act() 
    {
        move(4);
        MyWorld myWorld = (MyWorld)getWorld();
        if (maxReichweite == 1 && (myWorld.sound == true)){
            Greenfoot.playSound("schuss.wav");
        }
        maxReichweite++;
        if (kannSehen(Gegner.class)== true){
            //entferne(Gegner.class);
            //getWorld().removeObject(this);
            Gegner e = (Gegner) getOneIntersectingObject(Gegner.class);
            if (e != null)
            {
                e.setSchaden(schaden);
                getWorld().removeObject(this);
            }
        }
        else if ( atWorldEdge() == true ) 
        { 
            getWorld().removeObject(this); 
        } 

    }    
}
