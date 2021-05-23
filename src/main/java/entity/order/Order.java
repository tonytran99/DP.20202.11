package entity.order;

import controller.SessionInformation;
import entity.cart.Cart;
import entity.cart.CartItem;
import entity.shipping.DeliveryInfo;
import views.screen.ViewsConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    private int shippingFees;
    private int subtotal; // tong gia tien cua san pham
    private int tax;// 
    private List<OrderItem> orderMediaList;
    // Nen de private
    protected DeliveryInfo deliveryInfo;
    public Order() {
        this.shippingFees = 0;
        this.subtotal = 0;
        this.tax = 0;
    }
    
    // stamp coupling do truyen cart o dang phuc hop khong can thiet
    
    public Order(Cart cart) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (Object object : SessionInformation.cart.getListMedia()) {
            CartItem cartItem = (CartItem) object;
            OrderItem orderItem = new OrderItem(cartItem.getMedia(),
                    cartItem.getQuantity(),
                    cartItem.getPrice());
            orderItems.add(orderItem);
        }
        this.orderMediaList = Collections.unmodifiableList(orderItems);
        this.subtotal = cart.calSubtotal();
        this.tax = (int) (ViewsConfig.PERCENT_VAT/100) * subtotal;
    }

    public List<OrderItem> getListOrderMedia() {
        return this.orderMediaList;
    }

    public int getShippingFees() {
        if (deliveryInfo == null) return 0;
        return this.shippingFees;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }
    
    // Data coupling do truyen va su dung het du lieu
    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
        this.shippingFees = deliveryInfo.calculateShippingFee(this);
    }

    public List<OrderItem> getOrderMediaList() {
        return orderMediaList;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public int getTax() {
        return tax;
    }

    public int getTotal() {
        return this.subtotal + this.tax + this.shippingFees;
    }
}
