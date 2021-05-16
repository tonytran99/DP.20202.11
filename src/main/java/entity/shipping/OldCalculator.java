package entity.shipping;

import entity.order.Order;

public class OldCalculator implements ShippingFeeCalculator {
	@Override
	public int calculate(DeliveryInfo deliveryInfo, Order order) {
		int distance = deliveryInfo.distanceCalculator.calculateDistance(deliveryInfo.address, deliveryInfo.province);
        return (int) (distance * 1.2);
	}
}
