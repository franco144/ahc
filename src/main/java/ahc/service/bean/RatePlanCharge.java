package ahc.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RatePlanCharge {
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductRatePlanChargeId() {
		return this.productRatePlanChargeId;
	}
	public void setProductRatePlanChargeId( String productRatePlanChargeId ) {
		this.productRatePlanChargeId = productRatePlanChargeId;
	}
	
	@Override
	public String toString() {
		return "RatePlanCharge [id=" + id + ", name=" + name + ", productRatePlanChargeId=" + productRatePlanChargeId
				+ "]";
	}

	private String id;
	private String name;
	private String productRatePlanChargeId;
}
