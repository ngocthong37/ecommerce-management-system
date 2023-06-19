package ptithcm.model.pay;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "Payment_Type")
public class PaymentType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
// @Column(name = "value")	
	@Column(name = "value")
	private String value;
	
	
	@OneToMany(mappedBy = "paymentType", fetch = FetchType.LAZY)
	private Collection<CustomerPaymentMethod> customerPaymentMethods;
	
	public PaymentType() {
		super();
	}

	public PaymentType(Integer id, String status) {
		super();
		this.id = id;
		this.value = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return value;
	}

	public void setStatus(String status) {
		this.value = status;
	}
	
	
	
	
}
