package ptithcm.model.address;

import java.util.Collection;
import java.util.List;

import javax.management.ConstructorParameters;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="District")
public class District{
	@Id
	@Column(name ="id")
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="prefix")
	private String prefix;
	@ManyToOne()
	@JoinColumn(name="province_id")
	private Province province;
	@OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
	private List<Ward> wards;
	
	
	public District() {
		super();
	}
	
	public District(int id, String name, String prefix, Province province) {
		super();
		this.id = id;
		this.name = name;
		this.prefix=prefix;
		this.province=province;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}
	
	
	
	
	
	
}