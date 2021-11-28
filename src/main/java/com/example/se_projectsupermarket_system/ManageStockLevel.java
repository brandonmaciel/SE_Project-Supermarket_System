package com.example.se_projectsupermarket_system;

public class ManageStockLevel {


    public static void removeQuantityItem(int quantity_inOrder, int item_id){
        //Basically subtract the customer quantity order from the items stock in Data.items
        for(int i = 0; i < Data.items.size(); i++){
            if(item_id == Data.items.get(i).getId()){

                //BUlk items quantity are subtracted by one else subtract the qauntity order
                if(Data.items.get(i).getBulk() == true){
                    int newQuantity = (Data.items.get(i).getQuantity() - 1);
                    Data.items.get(i).setQuantity(newQuantity);
                    System.out.println(newQuantity);
                }else{
                    int newQuantity = (Data.items.get(i).getQuantity() - quantity_inOrder);
                    Data.items.get(i).setQuantity(newQuantity);
                    System.out.println(newQuantity);
                }
                break;
            }
        }

    }

    public static void addToItemStock(int quantityOrder, int index){
        //Basically Restock the item to 50 above threshold
        Data.items.get(index).setQuantity(quantityOrder);
    }
}
