import java.io.File;
import java.util.Stack;

/**
 * Write a description of class fileSearch here.
 *
 * @author (Matthew Negasi)
 * @version (Feb 2022)
 */
public class fileSearch
{

    public static Stack <File> fileNames = new Stack<File>() ;

    public static void main(String [] args) {
        String workingDir = System.getProperty("user.dir");

        System.out.println(workingDir);

        File path = new File("/Users/mtnsworld/");
        System.out.println(searchFiles(new File("/Users/mtnsworld/"),"genie.txt")); // Linear
        System.out.println(searchFiles2(new File("/Users/mtnsworld/"), "Driver.java"));
    }

    // Linear
    public static String searchFiles(File path, String target) {
        if(path.isDirectory()){
            fileNames.push(path);
            while(fileNames.size() > 0) {
                File copy = fileNames.pop();
                File [] copyArray = copy.listFiles();
                if(copyArray != null){
                    for(int i = 0; i < copyArray.length; i ++) {
                        if(copyArray[i].isDirectory()){

                            if(copyArray[i].getName().equals("CS142WinterSkills")){
                                File temp = copyArray[i];
                                System.out.println("Here at CS 142 ");
                            }

                            fileNames.push(copyArray[i]);
                        }
                        else {
                            if(copyArray[i].getName().equals(target)) {
                                return copyArray[i].getAbsolutePath();
                            }
                        }
                    }
                }
            }
        }

        return "Not Found.";   
    }
    // Recursive Search
    public static String searchFiles2(File inputFile, String target) { 
        try{
            if(inputFile.isFile()){
                if(inputFile.getName().equals(target)){ 
                    return inputFile.getAbsolutePath();
                }
            }    
            else {         
                return searchFiles(inputFile, target);  
            }
        }
        catch(NullPointerException e){

        }
        
        return "Not found.";
    }
}