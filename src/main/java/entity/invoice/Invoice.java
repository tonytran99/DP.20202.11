package entity.invoice;

import entity.order.Order;

public class Invoice {

    private Order order;
    private int amount;
    private InvoiceState state;
    
    public Invoice(Order order, InvoiceState state){
        this.order = order;
        this.amount = order.getTotal();
        this.state = state;
    }

    public Order getOrder() {
        return order;
    }
    // data coupling do truyá»�n vÃ  sá»­ dá»¥ng háº¿t dá»¯ liá»‡u
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void saveInvoice(){
        
    }
    
    public void cancelInvoice() {
    	this.state.cancelInvoice();
    }
    
    public void changeState(InvoiceState state) {
    	this.state = state;
    }
}
