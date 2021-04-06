import greenfoot.*;
import java.util.*;

public class Knoten extends Actor
{
    String name;

    boolean geblockt = false;
    boolean besucht = false;
    boolean gefunden = false;
    LinkedList<Kante> kantenListe;

    //pfadint
    int pfad = -1;

    //Vorgänger
    Knoten vorgaenger;

    //Legt fest, ob der Knoten der Startpunkt ist.
    boolean startpunkt;

    //Legt fest, ob der Knoten der Zielpunkt ist.
    //macht noch nichts
    boolean zielpunkt;

    public void act() 
    {
        if(geblockt == false && (Greenfoot.mouseClicked(this)) && !startpunkt && !zielpunkt )
        {

            setImage("mauer.png");
            Turm turm = new Turm();
            getWorld().addObject(turm, getX(), getY() );
            turm.name = "test";
            geblockt = true;
            MyWorld myWorld = (MyWorld)getWorld();
            myWorld.pfadUpdate();

        }  
        if (name.equals("Knoten99")){
            setZiel(true);
            setImage("blocked.png");
        }
    } 

    public Knoten(String name){
        this.name = name;
        kantenListe = new LinkedList<Kante>();

    }

    public void kanteHinzufuegen(Kante k){
        kantenListe.add(k);
    }

    public boolean getBesucht()
    {
        return besucht;
    }

    public boolean getGeblockt()
    {
        return geblockt;
    }

    public void setBesucht(boolean b)
    {
        besucht=b;
        /*if(b && !geblockt){
        setImage("besucht.png");
        }
        else if (!b && !geblockt) {
        setImage("ball.png");
        }
         */
    }

    public void setZiel(boolean b) {
        zielpunkt = b;
    }

    public void setTurret() {
        if (geblockt == true){
            setImage("mauer.png");
            Turm turm = new Turm();
            getWorld().addObject(turm, getX(), getY() );
            turm.name = "test";

            geblockt = true;
            MyWorld myWorld = (MyWorld)getWorld();
            myWorld.pfadUpdate();
        }
    }

    public void removeTurret() {
        MyWorld myWorld = (MyWorld)getWorld();
        setImage("ball.png");
        geblockt = false;
        if (!getObjectsInRange(10, Turm.class).isEmpty()){
            
            for (int i = 0; i < getObjectsInRange(10, Turm.class).size(); i++) {
                getWorld().removeObject(getObjectsInRange(10, Turm.class).get(i));
            }
            myWorld.pfadUpdate();
        }
    }

    public Turm getTurret() {
        if (!getObjectsInRange(5, Turm.class).isEmpty()) {
            Turm turm =(Turm) getObjectsInRange(5,Turm.class).get(0);
            return turm;
        }
        else return null;
    }

}
