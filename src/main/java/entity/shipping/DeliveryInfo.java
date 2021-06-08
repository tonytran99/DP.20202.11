package entity.shipping;

import entity.order.Order;
import org.example.DistanceCalculator;
public class DeliveryInfo {
	// Content and common coupling do có thể bị truy cập bởi các lớp con bên ngoài và thay đổi giá trị trực tiếp không thông qua hàm get set
    protected String name;
    protected String phone;
    protected String province;
    protected String address;
    protected String shippingInstructions;
    protected DistanceCalculator distanceCalculator;
    final double CACULATE_SHIPPING_FEE = 1.2;
    // data coupling do truy�?n và sử dụng hết dữ liệu
    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions, DistanceCalculator distanceCalculator) {
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.address = address;
        this.shippingInstructions = shippingInstructions;
        this.distanceCalculator = distanceCalculator;
    }
    // stamp coupling do truy�?n biến order và không dùng đến
    public int calculateShippingFee(Order order) {
        int distance = distanceCalculator.calculateDistance(address, province);
        return (int) (distance * CACULATE_SHIPPING_FEE);
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
