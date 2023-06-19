package ptithcm.model.address;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Province")
public class Province{
	@Id
	@Column(name ="id")
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="code")
	private String code;
	@OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
    private List<Ward> wards;
	@OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
    private List<District> districts;
	

	
	public Province() {
		super();
	}
	
	public Province(int id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code =code;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
}