import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Turm extends Actor

{
    Platzhalter platzhalter = new Platzhalter();
    Platzhalter platzhalter2 = new Platzhalter();
    Platzhalter platzhalter3 = new Platzhalter();
    Platzhalter platzhalter4 = new Platzhalter();
    Kreis kreis = new Kreis();
    String name;
    private int drehtest = 0;
    int warteZeit = 0;
    int turmArt = 0;
    int level = 0;
    int schaden = 0;
    int reichweite = 0;
    int cooldown = 100;
    boolean saveLoaded = false;
    public void act() 
    {
        if (getWorld() instanceof MyWorld){
            List<Gegner> gegner = getObjectsInRange(reichweite, Gegner.class);

            bauen();
            checkPlatzhalter();
            if (gegner.size() != 0){
                pointAtObject(gegner.get(0));

                fire();
            }
            else {
                nichtsMachen();
            }
        }
        else if (getWorld() instanceof Hauptmenu){
            nichtsMachen();
        }
    }    

    //Gegner anvisieren
    public void pointAtObject(Actor o) {
        setRotation((int)(180*Math.atan2(o.getY()-getY(),o.getX()-getX())/Math.PI));
    }

    //Passendes Projektil abfeuern
    private void fire()
    {

        List<Gegner> gegner = getObjectsInRange(reichweite, Gegner.class);
        warteZeit = warteZeit+1;

        if (warteZeit == 1)  {

            if (turmArt == 1){
                Schuss schuss = new Schuss();
                schuss.schaden = schaden;
                getWorld().addObject(schuss,getX(), getY());
                schuss.setRotation(this.getRotation());
            } 
            else if (turmArt == 2){
                Laser laser = new Laser();
                laser.schaden = schaden;
                getWorld().addObject(laser,getX(), getY());
                laser.setRotation(this.getRotation());
            } 
            else if (turmArt == 3){
                Rakete rakete = new Rakete();
                rakete.schaden = schaden;
                getWorld().addObject(rakete,getX(), getY());
                rakete.setRotation(this.getRotation());
            } 
            else if (turmArt == 4){
                FrostGranate granate = new FrostGranate();
                granate.schaden = schaden;
                getWorld().addObject(granate,getX(), getY());
                granate.setRotation(this.getRotation());
            } 
        }

        if (warteZeit == cooldown || (gegner.size() == 0))  {
            warteZeit = 0;

        }
    }

    public void bauen(){
        if (Greenfoot.mouseClicked(this)){
            shop();
        }
    }

    public void shop(){
        //Shop damit er richtige Bilder anzeigt

        getWorld().addObject(kreis,this.getX(), this.getY() );
        Greenfoot.delay(20);
        getWorld().addObject(platzhalter,this.getX()+25, this.getY()+25 );
        platzhalter.setImage("turm1level1.png");
        if (turmArt == 1 && level == 1) platzhalter.setImage("turm1level2.png");
        else if (turmArt == 1 && level == 2) platzhalter.setImage("turm1level3.png");
        else if (turmArt == 1 && level >= 3) platzhalter.setImage("turm1level4.png");

        getWorld().addObject(platzhalter2,this.getX()+25, this.getY()-25 );
        platzhalter2.setImage("turm2level1.png");
        if (turmArt == 2 && level == 1) platzhalter2.setImage("turm2level2.png");
        else if (turmArt == 2 && level == 2) platzhalter2.setImage("turm2level3.png");
        else if (turmArt == 2 && level >= 3) platzhalter2.setImage("turm2level4.png");

        getWorld().addObject(platzhalter3,this.getX()-25, this.getY()+25 );
        platzhalter3.setImage("turm3level1.png");
        if (turmArt == 3 && level == 1) platzhalter3.setImage("turm3level2.png");
        else if (turmArt == 3 && level == 2) platzhalter3.setImage("turm3level3.png");
        else if (turmArt == 3 && level >= 3) platzhalter3.setImage("turm3level4.png");

        getWorld().addObject(platzhalter4,this.getX()-25, this.getY()-25 );
        platzhalter4.setImage("turm4level1.png");
        if (turmArt == 4 && level == 1) platzhalter4.setImage("turm4level2.png");
        else if (turmArt == 4 && level == 2) platzhalter4.setImage("turm4level3.png");
        else if (turmArt == 4 && level >= 3) platzhalter4.setImage("turm4level4.png");

    }

    //Turm upgrades
    public void checkPlatzhalter(){
        //upgrade Normaler Turm
        MyWorld  world = (MyWorld) getWorld();
        if (level >4)level = 4;
        if (turmArt >4)turmArt = 1;

        if ((Greenfoot.mouseClicked(platzhalter) && (world.goldCounter.getValue() > 5))|| saveLoaded){

            if (turmArt != 1 && !saveLoaded){
                turmArt = 1;
                level = 0;
            }
            if  (!saveLoaded) level = level+1;
            if (level == 1 && turmArt == 1){ 
                setImage("turm1level1.png");
                schaden = 5;
                reichweite = 100;
                cooldown = 100;
                warteZeit = 0;
                world.goldCounter.add(-5);
                saveLoaded = false;
            }
            else if (level == 2&& turmArt == 1){ 
                setImage("turm1level2.png");
                schaden = 10;
                reichweite = 120;
                cooldown = 90;
                warteZeit = 0;
                world.goldCounter.add(-5);
                saveLoaded = false;
            }
            else if (level == 3&& turmArt == 1){ 
                setImage("turm1level3.png");
                schaden = 15;
                reichweite = 130;
                cooldown = 80;
                warteZeit = 0;
                world.goldCounter.add(-5);
                saveLoaded = false;
            }
            else if (level == 4&& turmArt == 1){ 
                setImage("turm1level4.png");
                schaden = 20;
                reichweite = 170;
                cooldown = 50;
                warteZeit = 0;
                world.goldCounter.add(-5);
                saveLoaded = false;
            }
            getWorld().removeObject(platzhalter);
            getWorld().removeObject(platzhalter2);
            getWorld().removeObject(platzhalter3);
            getWorld().removeObject(platzhalter4);
            getWorld().removeObject(kreis);

        }
        else  if (Greenfoot.mouseClicked(platzhalter) && (world.goldCounter.getValue() < 5)){
            getWorld().removeObject(platzhalter);
            getWorld().removeObject(platzhalter2);
            getWorld().removeObject(platzhalter3);
            getWorld().removeObject(platzhalter4);
            getWorld().removeObject(kreis);
        }

        //upgrade Laser Turm
        if ((Greenfoot.mouseClicked(platzhalter2) && (world.goldCounter.getValue() > 5))|| saveLoaded){
            if (turmArt != 2 && !saveLoaded){
                turmArt = 2;
                level = 0;
            }
            if  (!saveLoaded) level = level+1;

            if (level == 1&& turmArt == 2){ 
                setImage("turm2level1.png");
                schaden = 4;
                reichweite = 100;
                cooldown = 8;
                world.goldCounter.add(-5);
                saveLoaded = false;
            }
            else if (level == 2&& turmArt == 2){ 
                setImage("turm2level2.png");
                schaden = 6;
                reichweite = 120;
                cooldown = 7;
                world.goldCounter.add(-5);
                saveLoaded = false;
            }
            else if (level == 3&& turmArt == 2){ 
                setImage("turm2level3.png");
                schaden = 10;
                reichweite = 130;
                cooldown = 6;
                world.goldCounter.add(-5);
                saveLoaded = false;
            }
            else if (level == 4&& turmArt == 2){ 
                setImage("turm2level4.png");
                schaden = 15;
                reichweite = 140;
                cooldown = 3;
                world.goldCounter.add(-5);
                saveLoaded = false;
            }
            getWorld().removeObject(platzhalter);
            getWorld().removeObject(platzhalter2);
            getWorld().removeObject(platzhalter3);
            getWorld().removeObject(platzhalter4);
            getWorld().removeObject(kreis);

        } 
        else  if (Greenfoot.mouseClicked(platzhalter2) && (world.goldCounter.getValue() < 5)){
            getWorld().removeObject(platzhalter);
            getWorld().removeObject(platzhalter2);
            getWorld().removeObject(platzhalter3);
            getWorld().removeObject(platzhalter4);
            getWorld().removeObject(kreis);
        }

        //upgrade Raketen Turm
        if ((Greenfoot.mouseClicked(platzhalter3) && (world.goldCounter.getValue() > 7))|| saveLoaded){
            if (turmArt != 3 && !saveLoaded){
                turmArt = 3;
                level = 0;
            }
            if  (!saveLoaded) level = level+1;

            if (level == 1&& turmArt == 3){ 
                setImage("turm3level1.png");
                schaden = 20;
                reichweite = 150;
                cooldown = 100;
                world.goldCounter.add(-7);
                saveLoaded = false;
            }
            else if (level == 2&& turmArt == 3){ 
                setImage("turm3level2.png");
                schaden = 30;
                reichweite = 170;
                cooldown = 100;
                world.goldCounter.add(-7);
                saveLoaded = false;
            }
            else if (level == 3&& turmArt == 3){ 
                setImage("turm3level3.png");
                schaden = 40;
                reichweite = 200;
                cooldown = 100;
                world.goldCounter.add(-7);
                saveLoaded = false;
            }
            else if (level == 4&& turmArt == 3){ 
                setImage("turm3level4.png");
                schaden = 50;
                reichweite = 230;
                cooldown = 100;
                world.goldCounter.add(-7);
                saveLoaded = false;
            }
            getWorld().removeObject(platzhalter);
            getWorld().removeObject(platzhalter2);
            getWorld().removeObject(platzhalter3);
            getWorld().removeObject(platzhalter4);
            getWorld().removeObject(kreis);

        }
        else  if (Greenfoot.mouseClicked(platzhalter3) && (world.goldCounter.getValue() < 7)){
            getWorld().removeObject(platzhalter);
            getWorld().removeObject(platzhalter2);
            getWorld().removeObject(platzhalter3);
            getWorld().removeObject(platzhalter4);
            getWorld().removeObject(kreis);
        }

        //upgrade FrostGranaten Turm
        if ((Greenfoot.mouseClicked(platzhalter4) && (world.goldCounter.getValue() > 2))|| saveLoaded){
            if (turmArt != 4 && !saveLoaded){
                turmArt = 4;
                level = 0;
            }
            if  (!saveLoaded) level = level+1;

            if (level == 1&& turmArt == 4){ 
                setImage("turm4level1.png");
                schaden = 1;
                reichweite = 100;
                cooldown = 100;
                world.goldCounter.add(-2);
                saveLoaded = false;
            }
            else if (level == 2&& turmArt == 4){ 
                setImage("turm4level2.png");
                schaden = 2;
                reichweite = 120;
                cooldown = 90;
                world.goldCounter.add(-2);
                saveLoaded = false;
            }
            else if (level == 3&& turmArt == 4){ 
                setImage("turm4level3.png");
                schaden = 4;
                reichweite = 130;
                cooldown = 80;
                world.goldCounter.add(-2);
                saveLoaded = false;
            }
            else if (level == 4&& turmArt == 4){ 
                setImage("turm4level4.png");
                schaden = 5;
                reichweite = 170;
                cooldown = 50;
                world.goldCounter.add(-2);
                saveLoaded = false;
            }
            getWorld().removeObject(platzhalter);
            getWorld().removeObject(platzhalter2);
            getWorld().removeObject(platzhalter3);
            getWorld().removeObject(platzhalter4);
            getWorld().removeObject(kreis);

        }
        else  if  (Greenfoot.mouseClicked(platzhalter4) && (world.goldCounter.getValue() < 2)){
            getWorld().removeObject(platzhalter);
            getWorld().removeObject(platzhalter2);
            getWorld().removeObject(platzhalter3);
            getWorld().removeObject(platzhalter4);
            getWorld().removeObject(kreis);
        }
        if (saveLoaded)saveLoaded = false;
    }
    //Idle animation
    public void nichtsMachen() {

        if (( Greenfoot.getRandomNumber(100) < 1 && level != 0) || ((getWorld() instanceof Hauptmenu)&& Greenfoot.getRandomNumber(100) < 1)) 
        {
            drehtest = getRandomNumber(-1,2);
            if (drehtest == 2)drehtest = 0;
        }
        setRotation(getRotation()+ drehtest);

    }

    public int getRandomNumber(int start,int end)
    {
        int normal = Greenfoot.getRandomNumber(end-start+1);
        return normal+start;
    }
}