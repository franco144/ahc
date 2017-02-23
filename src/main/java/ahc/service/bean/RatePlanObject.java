package ahc.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RatePlanObject {

	public String getProductRatePlanId() {
		return productRatePlanId;
	}

	public void setProductRatePlanId(String productRatePlanId) {
		this.productRatePlanId = productRatePlanId;
	}

	public String getAmendmentId() {
		return amendmentId;
	}
	
	public void setAmendmentId(String amendmentId) {
		this.amendmentId = amendmentId;
	}
	
	@Override
	public String toString() {
		return "RatePlanObject [productRatePlanId=" + productRatePlanId + ", amendmentId=" + amendmentId + "]";
	}
	
	@JsonProperty("ProductRatePlanId")
	private String productRatePlanId;
	@JsonProperty("AmendmentId")
	private String amendmentId;
}
