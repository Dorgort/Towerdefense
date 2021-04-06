import greenfoot.*;

public class SaveFile extends File 
{
    boolean save = true;
    MyWorld myWorld = (MyWorld)getWorld();
    /**
     *false FUER SPEICHERN
     *true FUER LADEN
     */
    public SaveFile(Boolean savepls)
    {
        save = savepls;
    }

    public void act(){
        if (Greenfoot.mousePressed(this)){
            if (save == true) save();
            else load();
        }

    }

    /**
     * Saves the Strings given as the second to the last parameter in the file named like given in filename.
     * 
     * @param filename
     *      The name of the file where the Strings should be saved.
     * 
     * @param addToExistingFile
     *      If you want to add the text to an existing file this variable has to be true;
     * 
     * @param fileText
     *      The strings that should be saved in the file.
     * 
     * @return
     *      Returns true if the file was successfully createt. False if not.
     */

    public void save(){
        MyWorld myWorld = (MyWorld)getWorld();
        System.out.println("SAVED");
        //0
        saveFile("saves/savefile.txt", false, "LOADED SAVEFILE");
        //1currentBackground
        saveFile("saves/savefile.txt", true, "" + myWorld.currentBackground);
        //2lebenCounter
        saveFile("saves/savefile.txt", true, "" + myWorld.lebenCounter.getValue());
        //3punkteCounter
        saveFile("saves/savefile.txt", true, "" + myWorld.punkteCounter.getValue());
        //4goldCounter
        saveFile("saves/savefile.txt", true, "" + myWorld.goldCounter.getValue());
        //5-105Knoten
        for (int i = 0; i < myWorld.knotenListe.size(); i++) {
            //saveFile("saves/savefile.txt", true, "" + myWorld.knotenListe.get(i).name);
            saveFile("saves/savefile.txt", true, "" + myWorld.knotenListe.get(i).getGeblockt());
        }
        //106
        saveFile("saves/savefile.txt", true, "TURME");
        //107-Rest turme
        for (int i = 0; i < myWorld.knotenListe.size(); i++) {
            if(myWorld.knotenListe.get(i).getGeblockt() == true){
                saveFile("saves/savefile.txt", true, "" + myWorld.knotenListe.get(i).getTurret().name);
                saveFile("saves/savefile.txt", true, "" + myWorld.knotenListe.get(i).getTurret().turmArt);
                saveFile("saves/savefile.txt", true, "" + myWorld.knotenListe.get(i).getTurret().level);
            }
            else saveFile("saves/savefile.txt", true, "" + "nope");

        }
    }
    //WIEDER LADEN
    public void load(){
        MyWorld myWorld = (MyWorld)getWorld();

        //DELETUS
        for (int i = 0; i < myWorld.knotenListe.size(); i++) myWorld.knotenListe.get(i).removeTurret();
        //MAKE SURE - DELETUS2
        for (int i = 0; i < myWorld.getObjects(Turm.class).size(); i++) {
            myWorld.removeObject(myWorld.getObjects(Turm.class).get(i));
        }

        System.out.println(loadFile("saves/savefile.txt"));
        //1currentBackground
        myWorld.currentBackground = Integer.parseInt(loadFile("saves/savefile.txt").get(1));
        myWorld.changeBackground();
        //2lebenCounter
        myWorld.lebenCounter.setValue(Integer.parseInt(loadFile("saves/savefile.txt").get(2)));
        //3punkteCounter
        myWorld.punkteCounter.setValue(Integer.parseInt(loadFile("saves/savefile.txt").get(3)));
        ///4goldCounter
        //myWorld.goldCounter.setValue(Integer.parseInt(loadFile("saves/savefile.txt").get(4)));
        //myWorld.goldCounter.setValue(50);
        //5-105Knoten
        for (int i = 0; i < loadFile("saves/savefile.txt").size(); i++) {
            if (i >= 5 && i<= 104){
                myWorld.knotenListe.get(i-5).geblockt = Boolean.parseBoolean(loadFile("saves/savefile.txt").get(i));
            }
        }
        for (int i = 0; i < myWorld.knotenListe.size(); i++) {
            myWorld.knotenListe.get(i).setTurret();
        }
        //107 bis rest?
        int j = 0;
        for (int i = 0; i < loadFile("saves/savefile.txt").size(); i++) {

            if (i >= 106 ){
                //System.out.println(i);
                //System.out.println(loadFile("saves/savefile.txt").get(i));
                if (loadFile("saves/savefile.txt").get(i).equals("test")) {
                    //System.out.println(myWorld.knotenListe.get(i-106-j).getTurret());
                    //System.out.println(loadFile("saves/savefile.txt").get(i+1)+ "turmart");
                    // System.out.println(loadFile("saves/savefile.txt").get(i+2)+ "level");
                    myWorld.knotenListe.get(i-106-j).getTurret().saveLoaded = true;
                    myWorld.knotenListe.get(i-106-j).getTurret().turmArt = Integer.parseInt(loadFile("saves/savefile.txt").get(i+1));
                    myWorld.knotenListe.get(i-106-j).getTurret().level = Integer.parseInt(loadFile("saves/savefile.txt").get(i+2));
                    myWorld.knotenListe.get(i-106-j).getTurret().checkPlatzhalter();
                    j= j+2;
                }
            }
        }
                ///4goldCounter
        myWorld.goldCounter.setValue(Integer.parseInt(loadFile("saves/savefile.txt").get(4)));
       
    }
}
