package ahc.service.history.model;

import java.time.LocalDate;

public class AmendmentHistory implements Comparable<AmendmentHistory> {

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getProductRatePlanId() {
		return productRatePlanId;
	}

	public void setProductRatePlanId(String productRatePlanId) {
		this.productRatePlanId = productRatePlanId;
	}

	public String getProductRatePlanChargeId() {
		return productRatePlanChargeId;
	}

	public void setProductRatePlanChargeId(String productRatePlanChargeId) {
		this.productRatePlanChargeId = productRatePlanChargeId;
	}

	/**
	 * Since ServiceActivationDate (SA) is expected to be either same as
	 * ContractEffectiveDate (CE) - i.e. for an upgrade - or greater than CE -
	 * i.e. for a downgrade - the comparison is only made based on CE
	 * <ul>
	 * <li>-1 if this obj is has older CE than the other</li>
	 * <li>0 if CE are on same date</li>
	 * <li>1 if this obj has newer CE than the other</li>
	 * </ul>
	 */
	@Override
	public int compareTo(AmendmentHistory o) {
		LocalDate otherCEDate = LocalDate.parse(o.getContractEffectiveDate());
		LocalDate thisCEDate = LocalDate.parse(this.getContractEffectiveDate());
		if (otherCEDate == null || thisCEDate == null)
			throw new RuntimeException("Cannot have ContractEffectiveDate null. otherCE: \"" + otherCEDate
					+ "\" thisCEDate: \"" + thisCEDate + "\"");

		if (thisCEDate.isBefore(otherCEDate))
			return -1;
		else if (thisCEDate.isAfter(otherCEDate))
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		return "AmendmentHistory [type=" + type 
				+ ", name=" + name 
				+ ", contractEffectiveDate=" + contractEffectiveDate
				+ ", serviceActivationDate=" + serviceActivationDate 
				+ ", productRatePlanId=" + productRatePlanId
				+ ", productRatePlanChargeId=" + productRatePlanChargeId + "]";
	}

	private String type;
	private String name;
	private String contractEffectiveDate;
	private String serviceActivationDate;
	private String productRatePlanId;
	private String productRatePlanChargeId;
}
