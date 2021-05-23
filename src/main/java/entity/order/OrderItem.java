package entity.order;

import entity.media.Media;

public class OrderItem {
    
    private Media media;
    private int price;
    private int quantity;
    // Data coupling do truyen va su dung het du lieu
    public OrderItem(Media media, int quantity, int price) {
        this.media = media;
        this.quantity = quantity;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "{" +
            "  media='" + media + "'" +
            ", quantity='" + quantity + "'" +
            ", price='" + price + "'" +
            "}";
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

}
