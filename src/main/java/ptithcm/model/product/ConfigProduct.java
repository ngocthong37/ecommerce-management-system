package ptithcm.model.product;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "config_product")
public class ConfigProduct {
	@EmbeddedId
	private ConfigProductKey id;

	private String value;

	public ConfigProductKey getId() {
		return id;
	}

	public void setId(ConfigProductKey id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
