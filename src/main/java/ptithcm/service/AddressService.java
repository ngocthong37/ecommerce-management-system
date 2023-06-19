package ptithcm.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.address.AddressDao;
import ptithcm.dao.order.OrderStatusDao;
import ptithcm.model.address.Address;
import ptithcm.model.address.District;
import ptithcm.model.address.Province;
import ptithcm.model.address.Ward;
import ptithcm.model.customer.CustomerAddress;
import ptithcm.model.order.OrderStatus;



@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private OrderStatusDao orderStatusDao;
	
	public  List<CustomerAddress> getAddressListByID(int id){
		List<CustomerAddress> addressList = addressDao.getAddressListByID(id);
		if (addressList == null) {
			return null;
		}
		return addressList;
	}
	
	public int deleteAddress(int addressID) {
		int delete = addressDao.deleteAddress(addressID);
		return delete;
	}
	
	public int deleteCustomerAddress(int addressID) {
		int delete = addressDao.deleteCustomerAddress(addressID);
		return delete;
	}
	
	public CustomerAddress getAddressById(int addressId) {
		CustomerAddress address = addressDao.getAddressById(addressId);
		return address;
	}
	public OrderStatus getOrderStatusById(int Id) {
		OrderStatus status = orderStatusDao.getOrderStatusById(Id);
		return status;
	}
	
	public List<Province> listProvinces(){
		List<Province> listProvinces = addressDao.getProvincesList();
		if (listProvinces == null) {
			return null;
		}
		return listProvinces;
	}
	
	public List<District> listDistricts(int id){
		List<District> listDistricts = addressDao.getDistricesList(id);
		if (listDistricts == null) {
			return null;
		}
		return listDistricts;
	}
	
	public List<Ward> listWards(int id){
		List<Ward> listWards = addressDao.getWardsList(id);
		if (listWards == null) {
			return null;
		}
		return listWards;
	}
	
	public Province getProvince(int id) {
		return addressDao.getProvince(id);
	}
	
	public District getDistrict(int id) {
		return addressDao.getDistrict(id);
	}
	
	public Ward getWard(int id) {
		return addressDao.getWard(id);
	}
}
