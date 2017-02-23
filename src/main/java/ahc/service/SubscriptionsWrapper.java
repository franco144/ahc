package ahc.service;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ahc.service.bean.Subscription;

public class SubscriptionsWrapper {

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Boolean success()
	{
		return success;
	}

	@Override
	public String toString() {
		return "SubscriptionsWrapper [subscriptions=" + subscriptions + "]";
	}

	private List<Subscription> subscriptions;
	@JsonProperty("success")
	private Boolean success;
}
