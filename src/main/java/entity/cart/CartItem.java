package entity.cart;

import entity.media.Media;

public class CartItem {
    
    private Media media;
    private int quantity;
    private int price;

    public CartItem(){
    //Logical cohesion do cac th�nh phan item, price, quatity khac nhau ve ban chat
    }
    // data coupling do truy�?n và sử dụng hết dữ liệu
    public CartItem(Media media, Cart cart, int quantity, int price) {
        this.media = media;
        this.quantity = quantity;
        this.price = price;
    }
    
    public Media getMedia() {
        return this.media;
    }
    
    // data coupling do truy�?n và sử dụng hết dữ liệu
    public void setMedia(Media media) {
        this.media = media;
    }

    public int getQuantity() {
        return this.quantity;
    }
    // data coupling do truy�?n và sử dụng hết dữ liệu
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return this.price;
    }
    // data coupling do truy�?n và sử dụng hết dữ liệu
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

    
