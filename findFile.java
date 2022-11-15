import java.io.File;
import java.io.FileNotFoundException;

/**
 * Finding files (Recursion)
 *
 * @author (Matthew Negasi)
 * @version (2/13/2022)
 */
public class findFile
{
    private int maxCount; // A Max count variable to set the peak amount of files that will be collected and found
    private String[] fileNames; // Array to collect all the file names
    private int totalCount = 0; // The total count to keep track of how close to the max amount of files the program will get
    private String target; // Target value for the file name 
    private String dirName; // Directory name
    
    /**
     * findFile() 
     * ----------
     * Constructor
     * 
     * Pre: maxFiles > 0
     * Post: sets the max amount of files to the parameter in take.
     * 
     */public findFile(int maxFiles)
    {
        if(maxFiles < 0) { // Invariant in case of the below 0 possibility
        this.maxCount = 4; 
        }
        this.maxCount = maxFiles;
        fileNames = new String[maxFiles];
    }

    /**
     * directorySearch() 
     * ---------------- 
     * Pre: None
     * Post: Takes in the values of the target and directory name and passes them through a recursive method.
     */public void directorySearch(String target, String dirName){
        this.target = target;
        this.dirName = dirName;
        try{ 
            File inputFile = new File(dirName);
            if(inputFile.isDirectory()){
                listDir(inputFile);
                if(this.totalCount == 0) {
                System.out.println("Could not find the target you were looking for.");
                }
            }
            else{
            throw new IllegalArgumentException("Invalid Entry, not a directory.");
            }  
        }
        catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * listDir() 
     * ---------
     * Pre: None
     * Post: Takes in the file input, and searches through the directories and increments the amount of target occurences there are.
     * 
     */public void listDir(File inputFile) { 
    try{
     if(inputFile.isFile()){
         if(inputFile.getName().equals(target)){ 
        
        if(this.totalCount == this.maxCount){
        System.out.println("Max amount of files have been found, thank you for your patience."); 
        // Notifying the user the max has been reached
        return;
        }
        this.fileNames[totalCount] = inputFile.getName(); // adding to the array of the fileNames each instance of the file being found continually
        this.totalCount ++;
        System.out.println("Found a total of : " + this.totalCount + " copies.");
        return;
        }
    }    
    else {         
    File[] elements = inputFile.listFiles(); 
    for(File element: elements){
    if(this.totalCount == this.maxCount){
    System.out.println("Max amount of files have been found, thank you for your patience."); 
    break;  // If we get to the total count of files, we break from the loop and exit this method
    }    
    listDir(element);
    }    
    }
    }
    catch(NullPointerException e){
    
    }
    }
    
    /**
     * getCount() 
     * ----------
     * This method returns the total amount of files that have been found that are identical to the target (int).
     * 
     */public int getCount(){
        return this.totalCount;
    }

    /**
     * getFiles() 
     * ----------
     * This method returns the compilation of all the target occurences (in String form) compiled within an Array. 
     * 
     */public String[] getFiles(){
        String [] fileName = new String[fileNames.length];
        for(int i = 0; i < fileNames.length; i ++){
        fileName[i] = fileNames[i];
        //System.out.println(fileNames[i]);
        }
        return fileName;
    }

}
