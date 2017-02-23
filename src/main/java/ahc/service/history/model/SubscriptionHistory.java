package ahc.service.history.model;

public class SubscriptionHistory {
	
	public String getContractEffectiveDate() {
		return _contractEffectiveDate;
	}
	public void setContractEffectiveDate(String _contractEffectiveDate) {
		this._contractEffectiveDate = _contractEffectiveDate;
	}
	public String getServiceActivationDate() {
		return _serviceActivationDate;
	}
	public void setServiceActivationDate(String _serviceActivationDate) {
		this._serviceActivationDate = _serviceActivationDate;
	}
	public String getProductRatePlanChargeId() {
		return _productRatePlanChargeId;
	}
	public void setProductRatePlanChargeId(String _productRatePlanChargeId) {
		this._productRatePlanChargeId = _productRatePlanChargeId;
	}
	public String getProductRatePlanId() {
		return _productRatePlanId;
	}
	public void setProductRatePlanId(String _productRatePlanId) {
		this._productRatePlanId = _productRatePlanId;
	}
	
	@Override
	public String toString() {
		return "SubscriptionHistory [_contractEffectiveDate=" + _contractEffectiveDate + ", _serviceActivationDate="
				+ _serviceActivationDate + ", _productRatePlanChargeId=" + _productRatePlanChargeId
				+ ", _productRatePlanId=" + _productRatePlanId + "]";
	}

	private String _contractEffectiveDate;
	private String _serviceActivationDate;
	private String _productRatePlanChargeId;
	private String _productRatePlanId;
}
