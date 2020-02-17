import java.util.*;
public class WriteToFile{
    private Formatter savedFile;

    public void openFile(){
        try{
            savedFile = new Formatter("myInventory.txt");
        }catch(Exception e){
            System.out.print("Error saving file");
        }
    }
    public void save(String codes, String descriptions, int stock, int sales){
        savedFile.format("%s%s%s%s", codes, descriptions, stock, sales);
    }

    public void closeFile(){
        savedFile.close();
    }
}