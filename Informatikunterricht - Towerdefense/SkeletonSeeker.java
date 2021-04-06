import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SkeletonSeeker extends Gegner{
    GifImage gifImage = new GifImage("skeleton_seeker_spawn.gif");
    GifImage gifImage2 = new GifImage("skeleton_seeker_walkR.gif");
    GifImage gifImage3 = new GifImage("skeleton_seeker_walkL.gif");
    int waitpls = 0;
    boolean test = false;

    public SkeletonSeeker(){
        gegnerlautverhalten = new MachtLaut();
    }

    public void act() 
    {
        MyWorld myWorld = (MyWorld)getWorld();
        if (firstAct == true){
            test = true;
            setSchaden(-1000);

        }
        if (waitpls < 120 && test == true){
            setImage(gifImage.getCurrentImage());
            waitpls++;
            neuFinden();
        }
        else {
            setImage(gifImage2.getCurrentImage());
            if (gespiegelt == true) setImage(gifImage3.getCurrentImage());

            neuFinden(); 
            bewegen();
        }

        if (myWorld.sound == true && tot ==false){
            tuLautMachen();
        }

    }  
}