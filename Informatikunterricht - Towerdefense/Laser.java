import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Laser extends Projektil
{
    int maxReichweite = 0;
    int schaden;
    public void act() 
    {
        move(4);
        maxReichweite++;
        if (kannSehen(Gegner.class)== true){
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
        else if ( maxReichweite > 50 ) 
        { 
            getWorld().removeObject(this); 
        } 
    }    
}
