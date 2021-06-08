package entity.invoice;

public class WaitingInvoice implements InvoiceState{
	private Invoice invoice;
	
	public void cancelInvoice() {
		
	}
	
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
}
