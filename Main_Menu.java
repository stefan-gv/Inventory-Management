//Stefan Georgiev
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main_Menu{
    public static void main (String[] args){
        //Set the ArrayList of products
        ArrayList<Product> products = new ArrayList<>();
        
        boolean run = true;
        Scanner sc = new Scanner(System.in);
        
        // Load the Temp File and set values
        try
        {
            FileInputStream fis = new FileInputStream("productData");
            ObjectInputStream ois = new ObjectInputStream(fis);
 
            products = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
            return;
        } 
        catch (ClassNotFoundException c) 
        {
            System.out.println("Error loading file");
            c.printStackTrace();
            return;
        }
        
        //Main Run Loop. Value set to false only for Case where user wants to quit
        while (run == true){
            //Setting up Main Menu display
            System.out.println("\n");
            System.out.println("Inventory Management Main Menu");
            System.out.println("List Current Inventory - Press 1");
            System.out.println("List Product Details - Press 2");
            System.out.println("Add new Product - Press 3");
            System.out.println("Remove Product From Inventory - Press 4");
            System.out.println("Edit Product - Press 5");
            System.out.println("List of all Products with Inventory Less Than 10 - Press 6");
            System.out.println("Search for Product - Press 7");
            System.out.println("Save Changes - Press 8");
            System.out.println("Save and Quit - Press 9");
            //System.out.print("Enter action here: ");

            boolean tryLoop = true;
            int mainMenuChoice = 14;
            
            //Error Checking for non integer values for main menu input
            while (tryLoop == true){
                try {
                    System.out.print("Enter Action here: ");
                    mainMenuChoice = sc.nextInt();
                    tryLoop = false;
                            
                }catch(Exception e){
                    System.out.println("ERROR - Please enter an Integer");
                    sc.nextLine();
                }
            }

            //Swtiching all cases that the user may have entered
            switch (mainMenuChoice){
                
                //Display information for all products
                case 1:
                    for (int productIndex = 0; productIndex < products.size(); productIndex++){
                        products.get(productIndex).getInventoryData();
                    }
                    run = true;
                    break;
                
                //Display information for 1 specific Product
                case 2:
                    //Verifier variable is used to check if the product code exists. If false
                    //...an errror message will display and return to main menu. If true
                    //...no error message will be displayed
                    boolean verifier = false;
                    Scanner codeFinder = new Scanner(System.in);
                    System.out.print("Enter Product Code You Are Searching For: ");
                    String chosenProductCode = codeFinder.next();
                    
                    for (int productIndex = 0; productIndex < products.size(); productIndex++){
                        if (chosenProductCode.equals(products.get(productIndex).getProductCode())){
                            products.get(productIndex).getInventoryData();
                            verifier = true;
                            break;
                        }
                    }
                    if (verifier == false){
                        System.out.println("Product code does not exist");
                    }
                    run = true;
                    break;
                
                //Add New product
                case 3:
                    //Try Loop are varibles involved with handling the while loops for my error catching
                    tryLoop = true;
                    boolean productRun = true;
                    
                    System.out.print("Add Product Description: ");
                    sc.nextLine();
                    String productDesc = sc.nextLine();
                    
                    //This loop invloves the user entering a desired product code and if that product code is in use it will continue
                    //...looping until user enter unique product code
                    
                    //Variables outside of the while loop that are worked on in the while loop must be assigned a data type and some value
                    //...in order to work
                    String productCode = "";
                    
                    while (productRun == true){
                        verifier = false;
                        System.out.print("Add Product Code: ");
                        productCode = sc.nextLine();
                        
                        for (int productIndex = 0; productIndex < products.size(); productIndex++){
                            if (productCode.equals(products.get(productIndex).getProductCode())){
                                //user has entered product code that exists therefore shoot and error message and re run loop
                                System.out.println("Product Code already in Use");
                                verifier = true;
                            } 
                        }
                        if (verifier == false){
                            //User has entered unique product code therefore exit loop
                            productRun = false;
                        }

                    }
                    
                    int productStock = 0;

                    //Error Handling
                    while (tryLoop == true){
                        try {
                            System.out.print("Add Total Stock of Product: ");
                            //sc.nextLine();
                            productStock = sc.nextInt();
                            tryLoop = false;
                            
                        }catch(Exception e){
                            System.out.println("ERROR - Please enter an Integer");
                            sc.nextLine();
                        }
                    }
                    
                    //Error Handling
                    tryLoop = true;
                    int productSales = 0;
                    
                    while (tryLoop == true){
                        try {
                            System.out.print("Add Total Product Sales: ");
                            //sc.nextLine();
                            productSales = sc.nextInt();
                            tryLoop = false;
                            
                        }catch(Exception e){
                            System.out.println("ERROR - Please enter an Integer");
                            sc.nextLine();
                        }
                    }
                    //initialising new product object in the array of products with user entered properties
                    products.add(new Product(productCode, productDesc, productStock,productSales));
                    break;
                
                //Remove Product
                case 4:
                    
                    verifier = false;
                    System.out.print("Enter Product Code Of Item You Want To Remove: ");
                    sc.nextLine();
                    chosenProductCode = sc.nextLine();
                    
                    //Checking all product objects in array for a matching code
                    for (int productIndex = 0; productIndex < products.size(); productIndex++){
                        if (chosenProductCode.equals(products.get(productIndex).getProductCode())){
                            //User has entered existing product code therefore execute the remove
                            products.remove(productIndex);
                            verifier = true;
                            break;
                        }
                    }
                    if (verifier == false){
                        //User has not entered valide code back to main menu
                        System.out.println("Product code does not exist");
                    }
                    run = true;
                    break;

                // Edit Product Details
                case 5:
                    verifier = false;
                    
                    System.out.print("Enter Product Code Of Item You Want To Edit: ");
                    sc.nextLine();
                    chosenProductCode = sc.nextLine();
                    
                    //Checking if Product code exists
                    for (int productIndex = 0; productIndex < products.size(); productIndex++){
                        if (chosenProductCode.equals(products.get(productIndex).getProductCode())){
                            
                            //Product code does exist so now we have a new run loop for the secondary menu where we can edit that 
                            //...product 
                            boolean detailsRun = true;
                            while (detailsRun == true){
                                System.out.println("\n");
                                System.out.println("Edit Product Code - Press 1");
                                System.out.println("Edit Product Description - Press 2");
                                System.out.println("Edit Product Stock - Press 3");
                                System.out.println("Edit Product Sales - Press 4");
                                System.out.println("Return To Main Menu - Press 5");
                                System.out.print("Enter Action here: ");

                                boolean internalTryLoop = true;
                                int chosenDetailEdit = 8;
                                 
                                //Error Handling
                                while (internalTryLoop == true){
                                    try {
                                        System.out.print("Enter Action here: ");
                                        chosenDetailEdit = sc.nextInt();
                                        internalTryLoop = false;           
                                    }catch(Exception e){
                                        System.out.println("ERROR - Please enter an Integer");
                                        sc.nextLine();
                                    }
                                }
                                //Switching Case for user inputs for secondary Menu
                                
                                switch (chosenDetailEdit){
                                    
                                    //New code
                                    case 1:
                                        System.out.print("Enter New Product Code: ");
                                        sc.nextLine();
                                        String newCode = sc.nextLine();
                                        products.get(productIndex).setProductCode(newCode);
                                        break;
                                    
                                    //New Description
                                    case 2:
                                        System.out.print("Enter New Product Description: ");
                                        sc.nextLine();
                                        String newDesc = sc.nextLine();
                                        products.get(productIndex).setDescription(newDesc);
                                        break;
                                    
                                    //New Stock
                                    case 3:
                                        int newStock = 0;
                                        tryLoop = true;
                                        //Error Handling
                                        while (tryLoop == true){
                                            try {
                                                System.out.println("Enter Updated Product Stock: ");
                                                newStock = sc.nextInt();
                                                tryLoop = false;
                                                
                                            }catch(Exception e){
                                                System.out.println("ERROR - Please enter an Integer");
                                                sc.nextLine();
                                            }
                                        }

                                        products.get(productIndex).setStock(newStock);
                                        break;
                                    
                                    //New Sales
                                    case 4:
                                        int newSales = 0;
                                        tryLoop = true;
                                        //Error Handling
                                        while (tryLoop == true){
                                            try {
                                                System.out.println("Enter Updated Product Sales: ");
                                                newSales = sc.nextInt();
                                                tryLoop = false;
                                                
                                            }catch(Exception e){
                                                System.out.println("ERROR - Please enter an Integer");
                                                sc.nextLine();
                                            }
                                        }
                                        products.get(productIndex).setSales(newSales);
                                        break;
                                    
                                    //Quit back to main menu
                                    case 5:
                                        detailsRun = false;
                                        break;
                                    
                                    //If user inputs an integer 0<x<6 display error message
                                    default:
                                        System.out.println("Menu Option not found");
                                        break;     
                                }
                                
                            }
                            verifier = true;
                            break;
                        }
                    }
                    //If the user inputs a product code that doesnt exist error message is displayed and user is returned to main menu
                    if (verifier == false){
                        System.out.println("Product code does not exist");
                    }
                    break;
                
                //Find all Products with stock <=10
                case 6:
                //Self explanitory basically the same as everything else   
                verifier = false;
                    for (int productIndex = 0; productIndex < products.size(); productIndex++){
                        if (products.get(productIndex).getStock() <= 10){
                            products.get(productIndex).getInventoryData();
                            verifier = true;
                        }
                    }
                    if (verifier == false){
                        System.out.println("No product in Inventory with stock of 10 or less");
                    }
                    break;
                
                //Search function
                case 7:
                verifier = false;
                    System.out.print("Enter Product Description You Are Searching For: ");
                    String keyWord = sc.next();
                    
                    //Loop through all product descriptions
                    for (int productIndex = 0; productIndex < products.size(); productIndex++){
                        String descriptionMatch = products.get(productIndex).getDescription();
                        //Does the description contain one of the keywords?
                        if (descriptionMatch.contains(keyWord)){
                            //Yes therefore print it's data
                            products.get(productIndex).getInventoryData();
                            verifier = true;
                        }
                    }
                    if (verifier == false){
                        //No therefore display error and return to main menu
                        System.out.println("No Matching Description");
                    }
                    run = true;
                    break;
                
                    //I Kept this other search code below because i liked it and i thought it was clever (Ignore it if you want)

                    /*
                    verifier = false;
                    System.out.print("Enter Product Description You Are Searching For: ");
                    String keyWord = sc.next();
                    int keyWordLength = keyWord.length();
                    
                    for (int productIndex = 0; productIndex < products.size(); productIndex++){
                        String descriptionMatch = products.get(productIndex).getDescription();
                        if (keyWordLength <= descriptionMatch.length()){
                            String descriptionMatchSubString = descriptionMatch.substring(0, keyWordLength);
                            if (descriptionMatchSubString.equals(keyWord)){
                                products.get(productIndex).getInventoryData();
                                verifier = true;
                            }
                        } 
                    }
                    if (verifier == false){
                        System.out.println("No Matching Description");
                    }
                    run = true;
                    break;
                    */

                //Save
                
                case 8:
                    //A bunch of serialisation 
                    try {
                        FileOutputStream fos = new FileOutputStream("productData");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(products);
                        oos.close();
                        fos.close();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    //Continue running the main loop
                    run = true;
                    break;
                
                //Save and quit
                case 9:
                    try {
                        FileOutputStream fos = new FileOutputStream("productData");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(products);
                        oos.close();
                        fos.close();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    //Leave the loop and exit program
                    run = false;
                    break;
                
                //If user inputs menu option not 0<x<10 then display error message and continue running loop
                default:
                System.out.println("Menu Option not found");
                run = true;
                break;     
            }
        }
        sc.close();
    }
}