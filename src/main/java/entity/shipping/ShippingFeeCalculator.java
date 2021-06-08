package entity.shipping;

import entity.order.Order;

public interface ShippingFeeCalculator {
	public int calculate(DeliveryInfo deliveryInfo, Order order);
}
