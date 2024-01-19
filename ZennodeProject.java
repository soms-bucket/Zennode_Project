import java.util.HashMap;
import java.util.Scanner;

public class ZennodeProject {
    // pr -> Price, pro -> Product, qn -> Quantity

    // These functions are just following the discount rule 
    static float flat_10_Discount(int totalPr){
        if (totalPr > 200) return totalPr-10;
        return totalPr;
    }
    static float bulk_10_Discount(int totalQn,int totalPr){
        if(totalQn > 20) return  (float) (totalPr - (float)(totalPr*0.1));
        return (float)totalPr;
    }
    static float discount(int totalPr,int qn,int cost,float percent){
        int itemPr = qn*cost;
        totalPr -= (itemPr*percent);
        return (float)totalPr;
    }
    public static void main(String args[]) {
        //Printing the item and their prices
        
        HashMap<String,Integer> productList = new HashMap<>();
        String proList[]= {"Coke","Chips","Candy"};
        int prList[] = {25,20,10};
        for(int i=0; i<proList.length; i++) productList.put(proList[i],prList[i]);
        System.out.println("Product-Name:\t Price");        
        for(String pro: productList.keySet()) System.out.println(" "+pro+"\t\t  "+productList.get(pro));
        System.out.println();
        
        Scanner sc = new Scanner(System.in);
        HashMap<String,Integer> shop = new HashMap<>();
        int gift =0, inp = 0;
        String inps;
        int totalPr = 0, totalQn = 0;
        System.out.println("Enter quantity:\n*NOTE: Gift wrap fee: 1 per unit");
        for (String pro: productList.keySet()){
            while(true){
                try{
                    System.out.print(" "+pro+":");
                    inp = Integer.parseInt(sc.nextLine()); // taking user-input until correct format appears
                    if(inp<0) { System.out.println(" Please Enter a Existing quantity!.");continue;} 
                    shop.put(pro,inp);
                }
                catch(NumberFormatException e) {System.out.println(" Please Enter a Valid Quantity!.");continue;}
                break;
            }
            while(true){
                System.out.print(" "+pro+" Include Gift Wrap? (Y/N):");
                inps = sc.nextLine();
                if(inps.equals("Y") || inps.equals("y")) { gift+=shop.get(pro);break; }
                else if(inps.equals("N") || inps.equals("n")) break;
                else { System.out.println(" Please Enter \"Y/y\" or \"N/n\".");continue; }
            }
            totalPr += shop.get(pro)*productList.get(pro);
            totalQn += shop.get(pro);
        }
        sc.close();
        //System.out.println("Done");

        float discountPrice = totalPr;
        String discountOffer = "";
        //calculating the discount amount for the most beneficial one for customer
        float price = flat_10_Discount(totalPr);
        if(discountPrice>price){
            discountPrice = price;
            discountOffer = "flat_10_discount";
        }
        price = bulk_10_Discount(totalQn,totalPr);
        if(discountPrice>price){
            discountPrice = price;
            discountOffer = "bulk_10_discount";
        }
        for (String pro: shop.keySet()){
            if(shop.get(pro)>10){
                price = discount(totalPr,shop.get(pro),productList.get(pro),(float)0.05);
                if(discountPrice>price){
                    discountPrice = price;
                    discountOffer = "bulk_5_discount";
                }
            }
            if(totalQn>30 && shop.get(pro)>15){
                price = discount(totalPr,shop.get(pro)-15,productList.get(pro),(float)0.5);
                if(discountPrice>price){
                    discountPrice = price;
                    discountOffer = "tiered_50_discount";
                }
            }
        }
        System.out.println("\n");
        int shipPr = (totalQn/10)*5;    //Calculate shipping fee
        if(totalQn%10 > 0) shipPr+=5;


        //printing the bill/Output
        System.out.println("Billing:-");
        System.out.println("Product-Name:\t  Quantity\t  Amount");
        for (String pro: shop.keySet()) 
            System.out.println(" "+pro+"\t\t   "+shop.get(pro)+"\t\t  "+shop.get(pro)*productList.get(pro));
        //System.out.println("\n")
        System.out.println("Subtotal: "+totalPr);
        if(!discountOffer.equals(""))
            System.out.println("Discount Applied \""+discountOffer+"\" owhoo!\nThe Discount amount: "+discountPrice);
        else System.out.println("Sorry! No Discount Applied");
        System.out.println("The shipping fee: "+shipPr+"\nThe gift wrap fee: "+gift);
        float allTotal = discountPrice+shipPr+gift;
        System.out.println("Total:"+allTotal);

    }
}
