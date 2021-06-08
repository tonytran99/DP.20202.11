package entity.invoice;

public class ConfirmedInvoice implements InvoiceState{
	private Invoice invoice;
	
	public void cancelInvoice() {
		
	}
	
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
}

