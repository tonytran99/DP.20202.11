package entity.shipping;

import org.example.DistanceCalculator;

import entity.order.Order;

public class OldCalculator implements ShippingFeeCalculator {
	@Override
	public int calculate(DeliveryInfo deliveryInfo, Order order) {
		DistanceCalculator distanceCalculator = new DistanceCalculator();
		int distance = distanceCalculator.calculateDistance(deliveryInfo.address, deliveryInfo.province);
        return (int) (distance * 1.2);
	}
}
