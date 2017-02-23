package ahc.service.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RatePlan {
	
	public String getRatePlanName() {
		return ratePlanName;
	}

	public void setRatePlanName(String ratePlanName) {
		this.ratePlanName = ratePlanName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductRatePlanId() {
		return productRatePlanId;
	}

	public void setProductRatePlanId(String productRatePlanId) {
		this.productRatePlanId = productRatePlanId;
	}

	public List<RatePlanCharge> getRatePlanCharges() {
		return ratePlanCharges;
	}

	public void setRatePlanCharges(List<RatePlanCharge> ratePlanCharges) {
		this.ratePlanCharges = ratePlanCharges;
	}

	@Override
	public String toString() {
		return "RatePlan [id=" + id 
				+ ", ratePlanName=" + ratePlanName 
				+ ", productRatePlanId=" + productRatePlanId 
				+ ", ratePlanCharges=" + ratePlanCharges + "]";
	}

	private String id;
	private String ratePlanName;
	private String productRatePlanId;
	private List<RatePlanCharge> ratePlanCharges;
	
}
