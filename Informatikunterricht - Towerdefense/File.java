import greenfoot.*;

//The following imports are necessary for the reading and writing of files:
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class contains some methods to load save and delete .txt files.
 * The methods can be used in any class which imports the necessary classes.
 */
public class File extends Actor
{
    private String fileText = "";

    
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
    public boolean saveFile(String filename, boolean addToExistingFile, String ... fileText) {
        List<String> existingText = loadFile(filename);
        BufferedWriter file = null;
        try {
            file = new BufferedWriter(new FileWriter(filename));
            if (addToExistingFile) {
                for (String output : existingText) {
                    file.write(output);
                    file.write('\n');
                }
            }
            for (String output : fileText) {
                file.write(output);
                file.write('\n');
            }
            file.close();
        }
        catch (IOException ioe) {
            //ioe.printStackTrace();
            return false;
        }
        finally {
            try {
                file.close();
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
            catch (NullPointerException npe) {
                //npe.printStackTrace();
            }
        }
        return true;
    }
    

    
    /**
     * Loads the text of the file whith the given filename.
     * 
     * @param filename
     *      The name of the file that should be loaded.
     * 
     * @return
     *      Returns a list of Strings consisting of the text of the file.
     *      Each line of the file is a new element of the list.
     */
    public java.util.List<String> loadFile(String filename) {
        ArrayList<String> fileText = new ArrayList<String>();
        BufferedReader file = null;
        try {
            file = new BufferedReader(new FileReader(filename));
            String input;
            while ((input = file.readLine()) != null) {
                fileText.add(input);
            }
        }
        catch (FileNotFoundException fnfe) {
            //fnfe.printStackTrace();
            return null;
        }
        catch (IOException ioe) {
            //ioe.printStackTrace();
            return null;
        }
        finally {
            try {
                file.close();
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
            catch (NullPointerException npe) {
                //npe.printStackTrace();
            }
        }
        return fileText;
    }
    
    /**
     * Deletes the content of a file.
     * 
     * @param filename
     *      The name of the file that should be deleted.
     * 
     * @return
     *      Returns true if the file has ben deleted or if the file didn't exist.
     *      Returns false if there is a IOException.
     */
    public boolean deleteFile(String filename) {
        BufferedWriter file = null;
        try {
            file = new BufferedWriter(new FileWriter(filename));
            file.write("");
            file.close();
        }
        catch (FileNotFoundException fnfe) {
            //fnfe.printStackTrace();
            return true;
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
        finally {
            try {
                file.close();
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
            catch (NullPointerException npe) {
                //npe.printStackTrace();
            }
        }
        return true;
    }
    
    /**
     * Check whether a file with the given name is currently existing.
     * 
     * @param filename
     *      The name of the file that should be checked.
     * 
     * @return
     *      Returns true if the file is existing.
     *      Returns false if the file is not found or if there was a IOException.
     */
    public boolean fileExisting(String filename) {
        BufferedReader file = null;
        try {
            file = new BufferedReader(new FileReader(filename));
        }
        catch (FileNotFoundException fnfe) {
            //fnfe.printStackTrace();
            return false;
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
        finally {
            try {
                file.close();
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
            catch (NullPointerException npe) {
                //npe.printStackTrace();
            }
        }
        return true;
    }
    
    /**
     * Returns a list of Strings concerning the names of all existing files in the choosen directiory.
     */
    public java.util.List<String> getExistingFileNames(String path) {
        java.io.File file;
        List<java.io.File> files;
        ArrayList<String> fileNames = new ArrayList<String>();
        file = new java.io.File(path);
        files = Arrays.asList(file.listFiles());
        for (java.io.File temp : files) {
            fileNames.add(temp.getName());
        }
        return fileNames;
    }
    
    /**
     * Returns a list of all files in the choosen directory.
     */
    public java.util.List<java.io.File> getExistingFiles(String path) {
        java.io.File file = new java.io.File(path);
        return Arrays.asList(file.listFiles());
    }
}