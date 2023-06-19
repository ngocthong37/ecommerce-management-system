package ptithcm.dao.address;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ptithcm.model.address.Address;
import ptithcm.model.address.District;
import ptithcm.model.address.Province;
import ptithcm.model.address.Ward;
import ptithcm.model.customer.Customer;
import ptithcm.model.customer.CustomerAddress;
import ptithcm.model.product.ProductItem;


public interface AddressDao {
	//public List<Address> getAddressByID(int addressId);
	public List<CustomerAddress> getAddressListByID(int addressId);
	public int deleteAddress(int addressId);
	public int deleteCustomerAddress(int addressId);
	public CustomerAddress getAddressById(int addressId);
	public List<Province> getProvincesList();
	public List<District> getDistricesList(int id);
	public List<Ward> getWardsList (int id);
	public Province getProvince(int id);
	public District getDistrict(int id);
	public Ward getWard(int id);
		
}
