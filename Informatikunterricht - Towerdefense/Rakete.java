import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EisLaser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rakete extends Projektil
{
    Explosion explosion = new Explosion();
    int maxReichweite = 0;
    int schaden;
    public void act() 
    {
        if(!getObjectsInRange(350, Gegner.class).isEmpty()){
            int dist=350;

            while(!getObjectsInRange(dist, Gegner.class).isEmpty()){
                dist--;
            }
            dist++;
            Actor target = (Gegner) getObjectsInRange(dist,Gegner.class).get(0);

            this.turnTowards(target.getX(), target.getY());
            move(3);
            MyWorld myWorld = (MyWorld)getWorld();
            if (maxReichweite == 1 && (myWorld.sound == true)){
                Greenfoot.playSound("rakete.wav");
            }
            maxReichweite++;
            if (kannSehen(Gegner.class)== true){
                explosion.schaden = schaden;
                getWorld().addObject(explosion,getX(), getY());
                getWorld().removeObject(this); 
            }
            else if ( atWorldEdge() == true ) 
            { 
                getWorld().addObject(explosion,getX(), getY());
                explosion.schaden = schaden;
                getWorld().removeObject(this); 

            } 

        }  
        else {
            getWorld().addObject(explosion,getX(), getY());
            explosion.schaden = schaden;
            getWorld().removeObject(this);
        }
    }
}
