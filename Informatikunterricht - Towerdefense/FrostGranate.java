import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FrostGranate extends Projektil
{
    int schaden;
    public void act() 
    {
        move(2);
        
        if (kannSehen(Gegner.class)== true){
            Gegner e = (Gegner) getOneIntersectingObject(Gegner.class);
            if (e != null)
            {
                e.setSchaden(schaden);
                e.frozen = true;
                if (e.frozen == true)e.frozentimer = e.frozentimer - 5;
                getWorld().removeObject(this);
            }
        }
        else if ( atWorldEdge() == true ) 
        { 
            getWorld().removeObject(this); 
        } 
    }   

}
