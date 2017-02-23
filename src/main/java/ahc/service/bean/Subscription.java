package ahc.service.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscription {

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getContractEffectiveDate() {
		return contractEffectiveDate;
	}

	public void setContractEffectiveDate(String contractEffectiveDate) {
		this.contractEffectiveDate = contractEffectiveDate;
	}

	public String getServiceActivationDate() {
		return serviceActivationDate;
	}

	public void setServiceActivationDate(String serviceActivationDate) {
		this.serviceActivationDate = serviceActivationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<RatePlan> getRatePlans() {
		return ratePlans;
	}

	public void setRatePlans(List<RatePlan> ratePlans) {
		this.ratePlans = ratePlans;
	}

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", accountId=" + accountId + ", contractEffectiveDate="
				+ contractEffectiveDate + ", serviceActivationDate=" + serviceActivationDate + ", status=" + status
				+ ", ratePlans=" + ratePlans + "]";
	}

	private String id;
	private String accountId;
	private String contractEffectiveDate;
	private String serviceActivationDate;
	private String status;
	
	private List<RatePlan> ratePlans;
}
