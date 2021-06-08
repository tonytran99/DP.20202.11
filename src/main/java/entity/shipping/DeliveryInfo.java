package entity.shipping;

import entity.order.Order;
import org.example.DistanceCalculator;
public class DeliveryInfo {
    // Content and common coupling do co the truy cap boi cac lop con ben ngoai va thay doi gia tri qua ham get set
    protected String name;
    protected String phone;
    protected String province;
    protected String address;
    protected String shippingInstructions;
    protected DistanceCalculator distanceCalculator;
    // Data coupling do truyen va su dung het du lieu
    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions, DistanceCalculator distanceCalculator) {
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.address = address;
        this.shippingInstructions = shippingInstructions;
    }
    // stamp coupling do truyen ca bien Order
    public int calculateShippingFee(Order order) {
        return shippingFeeCalculator.calculate(this, order);
    }

    public void setShippingFeeCalculator(ShippingFeeCalculator shippingFeeCalculator) {
        this.shippingFeeCalculator = shippingFeeCalculator;
    }
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getProvince() {
        return province;
    }

    public String getAddress() {
        return address;
    }

    public String getShippingInstructions() {
        return shippingInstructions;
    }
}