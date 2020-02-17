import java.io.*;
import java.util.*;

public class ReadFromFile{

    private Scanner savedFile;

    public void openFile(){
        try{
            savedFile = new Scanner(new File("myInventory.txt"));
        }catch(Exception e){
            System.out.println("Error loading file");
        }
    }
    public void readFile(){
        while(savedFile.hasNext()){
            
        }
    }
}