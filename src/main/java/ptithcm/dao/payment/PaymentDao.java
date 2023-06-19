package ptithcm.dao.payment;


import java.util.List;


import org.springframework.stereotype.Service;
import ptithcm.model.pay.CustomerPaymentMethod;
import ptithcm.model.pay.PaymentType;
import ptithcm.model.ship.ShippingMethod;



@Service
public interface PaymentDao {
	public List<CustomerPaymentMethod> getPaymentListById(int ctmID);
	public List<ShippingMethod> getListShippingMethods();
	public ShippingMethod getShippingById(int ID );
	public CustomerPaymentMethod getPaymentById(int ID );
	public PaymentType gePaymentTypeById(int id);
		
}