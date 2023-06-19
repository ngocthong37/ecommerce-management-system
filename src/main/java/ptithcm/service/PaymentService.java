package ptithcm.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.payment.PaymentDao;

import ptithcm.model.pay.CustomerPaymentMethod;
import ptithcm.model.pay.PaymentType;
import ptithcm.model.ship.ShippingMethod;


@Service
public class PaymentService {
	@Autowired
	private PaymentDao paymentDao;
	public List<CustomerPaymentMethod> getPaymentListById(int ctmID){
		List<CustomerPaymentMethod> listPayment = paymentDao.getPaymentListById(ctmID);
		if (listPayment== null) {
			return null;
		}
		return listPayment;
	};
	
	public List<ShippingMethod> getListShippingMethods() {
		List<ShippingMethod> listShippingMethods = paymentDao.getListShippingMethods();
		if (listShippingMethods == null) {
			return null;
		}
		return listShippingMethods;
	}
	
	public ShippingMethod getShippingById(int Id) {
		ShippingMethod shippingMethod = paymentDao.getShippingById(Id);
		return shippingMethod;
	}

	public CustomerPaymentMethod getPaymentById(int Id) {
		CustomerPaymentMethod paymentMethod = paymentDao.getPaymentById(Id);
		return paymentMethod;
	}
	
	public PaymentType gePaymentTypeById(int id) {
		return paymentDao.gePaymentTypeById(id);
	}
	
}
