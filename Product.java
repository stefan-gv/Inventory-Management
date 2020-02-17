//Stefan Georgiev
import java.io.Serializable;
public class Product implements Serializable{
    private String productCode;
    private String description;
    private int stock;
    private int sales;

    public Product(){
        productCode = "lego";
        description = "Building Blocks";
        stock = 0;
        sales = 0;
    }
    
    public Product(String a, String b, int c, int d){
        productCode = a;
        description = b;
        stock = c;
        sales = d;
    }
   
    public String getProductCode(){
        return productCode;
    }
    public String getDescription(){
        return description;
    }
    public int getStock(){
        return stock;
    }
    public int getSales(){
        return sales;
    }

    public void setProductCode(String code){
        productCode = code;
    }
    public void setDescription(String desc){
        description = desc;
    }
    public void setStock(int stockValue){
        stock = stockValue;
    }
    public void setSales(int salesValue){
        sales = salesValue;
    }

    public void getInventoryData(){
        System.out.println("Product Code:"+" "+productCode+" "+"Description:"+" "+ description+" "+" "+ "Stock:"+""+ stock + " "+ "Sales:" +""+sales);
    }

}