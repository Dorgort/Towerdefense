import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Skeleton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Skeleton extends Gegner
{
    GifImage gifImage = new GifImage("skeletonR.gif");
    GifImage gifImage2 = new GifImage("skeletonL.gif");

    public Skeleton(){
        gegnerlautverhalten = new MachtLaut();
    }

    public void act() 
    {
        if (firstAct == true){
            setSchaden(-100);
        }
        MyWorld myWorld = (MyWorld)getWorld();
        if (myWorld.sound == true){
            tuLautMachen();
        }
        
        neuFinden(); 
        bewegen();
        setImage(gifImage.getCurrentImage());
        if (gespiegelt == true) setImage(gifImage2.getCurrentImage());

    }   
}
