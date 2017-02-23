package ahc.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Amendment {

	public String getServiceActivationDate() {
		return serviceActivationDate;
	}
	public void setServiceActivationDate(String serviceActivationDate) {
		this.serviceActivationDate = serviceActivationDate;
	}
	public String getContractEffectiveDate() {
		return contractEffectiveDate;
	}
	public void setContractEffectiveDate(String contractEffectiveDate) {
		this.contractEffectiveDate = contractEffectiveDate;
	}
	public String getBaseSubscriptionId() {
		return baseSubscriptionId;
	}
	public void setBaseSubscriptionId(String baseSubscriptionId) {
		this.baseSubscriptionId = baseSubscriptionId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNewRatePlanId() {
		return newRatePlanId;
	}
	public void setNewRatePlanId(String newRatePlanId) {
		this.newRatePlanId = newRatePlanId;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "Amendment [success=" + success 
				+ ", id=" + id 
				+ ", serviceActivationDate=" + serviceActivationDate
				+ ", contractEffectiveDate=" + contractEffectiveDate 
				+ ", type=" + type 
				+ ", baseSubscriptionId=" + baseSubscriptionId 
				+ ", newRatePlanId=" + newRatePlanId + "]";
	}

	@JsonProperty("success")
	private Boolean success;

	@JsonProperty("id")
	private String id;
	@JsonProperty("serviceActivationDate")
	private String serviceActivationDate;
	@JsonProperty("contractEffectiveDate")
	private String contractEffectiveDate;
	@JsonProperty("type")
	private String type;
	@JsonProperty("baseSubscriptionId")
	private String baseSubscriptionId;
	@JsonProperty("newRatePlanId")
	private String newRatePlanId;
}
