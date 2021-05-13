package entity.cart;

import entity.media.Media;

public class CartItem {
    
    private Media media;
    private int quantity;
    private int price;

    public CartItem(){
    //Logical cohesion do cac thành phan item, price, quatity khac nhau ve ban chat
    }
    // Data coupling do truyen va su dung het du lieu
    public CartItem(Media media, Cart cart, int quantity, int price) {
        this.media = media;
        this.quantity = quantity;
        this.price = price;
    }
    
    public Media getMedia() {
        return this.media;
    }
    
    // Data coupling do truyen va su dung het du lieu
    public void setMedia(Media media) {
        this.media = media;
    }

    public int getQuantity() {
        return this.quantity;
    }
    // Data coupling do truyen va su dung het du lieu
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return this.price;
    }
    // Data coupling do truyen va su dung het du lieu
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" 
            + " media='" + media + "'" 
            + ", quantity='" + quantity + "'" 
            + "}";
    }

}

    
