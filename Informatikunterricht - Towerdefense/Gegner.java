import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public abstract class Gegner extends Actor
{
    private int leben = 1;
    Knoten nextNode;
    Knoten currentNode;
    Knoten zielKnoten;
    boolean firstAct = true;
    boolean gespiegelt = false;
    boolean tot = false;
    boolean frozen = false;
    int frozentimer = 0;

    GegnerLautVerhalten gegnerlautverhalten;

    public void act() 
    {

    }    

    public int getLeben() {
        return leben;
    }

    public void setSchaden(int menge) {
        leben = leben - menge;
        if (leben < 0)
        {
            MyWorld  world = (MyWorld) getWorld();
            world.punkteCounter.add(5);
            world.goldCounter.add(5);
            tot = true;
            getWorld().removeObject(this);
        }
    }

    public Knoten getNextNode()
    {
        Knoten target  = null;
        if(!getObjectsInRange(350, Knoten.class).isEmpty()){
            int dist=350;

            while(!getObjectsInRange(dist, Knoten.class).isEmpty()){
                dist--;
            }
            dist++;

            target = (Knoten) getObjectsInRange(dist,Knoten.class).get(0);
        }
        return target;
    }

    public void neuFinden() {
        MyWorld world = (MyWorld)this.getWorld();

        if(firstAct){
            currentNode = getNextNode();
            firstAct = false;
            nextNode = currentNode;
        }
        if (!getObjectsInRange(5, Knoten.class).isEmpty()) {
            currentNode = (Knoten) getObjectsInRange(5,Knoten.class).get(0);
        }
        for(int j = 0; j< currentNode.kantenListe.size(); j++){

            Knoten current_neighbour = currentNode.kantenListe.get(j).pls_send_help(currentNode);
            if( current_neighbour.pfad == currentNode.pfad-1){

                nextNode =  current_neighbour;

            }
            else if (currentNode.pfad == 0){
                world.lebenCounter.add(-1);
                world.punkteCounter.add(-1);
                firstAct = true;
                getWorld().removeObject(this); 
                tot = true;
                return;

            }
        }

    }

    public void fliegen() {
        MyWorld world = (MyWorld)this.getWorld();
        if (tot == false && frozen == false){
            if(firstAct){
                firstAct = false;
                Knoten currentNode = getNextNode();
                zielKnoten = world.getZielKnoten();
                nextNode = world.getStartKnoten();
                nextNode = zielKnoten;
            }
            if (!getObjectsInRange(5, Knoten.class).isEmpty()) {
                currentNode = (Knoten) getObjectsInRange(5,Knoten.class).get(0);
                if ((Knoten) getObjectsInRange(5,Knoten.class).get(0)== zielKnoten){
                    world.lebenCounter.add(-1);
                    world.punkteCounter.add(-1);
                    firstAct = true;
                    getWorld().removeObject(this); 
                    tot = true;
                    return;
                }
            }

            if (nextNode.getX() < getX()){ 
                gespiegelt= true;
                setLocation(getX()-1, getY());

            }
            else if (nextNode.getX() > getX()){ 
                gespiegelt = false;
                setLocation(getX()+1, getY());

            }
            if (nextNode.getY() < getY()){ 

                setLocation(getX(), getY()-1);

            }
            else if (nextNode.getY() > getY()) {

                setLocation(getX(), getY()+1);

            }

        }
        act3Freeze();
    }

    public void knotenFinden() {
        MyWorld world = (MyWorld)this.getWorld();
        Knoten currentNode = getNextNode();  

        if(firstAct){
            firstAct = false;
            zielKnoten = world.getZielKnoten();
            nextNode = world.getStartKnoten();
            //System.out.println("\n new spuda " +currentNode.name + " to "+nextNode.name + " to "+zielKnoten.name );

        }

        if(currentNode == nextNode){
            nextNode = world.Ihatehtis(currentNode); 
            //System.out.println("  spuda from " +currentNode.name + " to "+nextNode.name );

        }
        //this.turnTowards(nextNode.getX(), nextNode.getY());
        zielBeruehrt();
    }

    public void zielBeruehrt(){
        Knoten currentNode = getNextNode();  
        if(currentNode == zielKnoten){
            MyWorld  world = (MyWorld) getWorld();
            world.lebenCounter.add(-1);
            world.punkteCounter.add(-1);
            //setSchaden(10000);
            tot = true;
            getWorld().removeObject(this); 
            return;
        }
    }

    public void bewegen(){
        if (tot == false && frozen == false){
            if (nextNode.getX() < getX()){ 
                gespiegelt= true;
                setLocation(getX()-1, getY());

            }
            else if (nextNode.getX() > getX()){ 
                gespiegelt = false;
                setLocation(getX()+1, getY());

            }
            if (nextNode.getY() < getY()){ 

                setLocation(getX(), getY()-1);

            }
            else if (nextNode.getY() > getY()) {

                setLocation(getX(), getY()+1);

            }

            //zielBeruehrt();
        }
        act3Freeze();
    }

    public void setGegnerLautVerhalten(GegnerLautVerhalten gb) {
        gegnerlautverhalten = gb;
    }

    public void tuLautMachen() {
        gegnerlautverhalten.lautMachen();
    }
    //Auftauen
    public void act3Freeze() {
        if (frozentimer < 60 && frozen && !tot){
            frozentimer++;
        }
        else if (frozentimer >= 60 && frozen&& !tot){
            frozentimer = 0;
            frozen = false;
        }

    }
}
