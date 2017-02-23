package ahc.service;

import static ahc.httpclient.constants.HttpClientConstants.ZUORA_SANDBOX_REST_ENDPOINT;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import ahc.httpclient.ZuoraHttpClient;
import ahc.service.bean.Subscription;
import okhttp3.Response;

public class SubscriptionService {
	
	private static final String REQUEST_URL = ZUORA_SANDBOX_REST_ENDPOINT + "/subscriptions/accounts/%s";
	private static final String SUBS_REQUEST_URL = ZUORA_SANDBOX_REST_ENDPOINT + "/subscriptions/%s";
	
	private ZuoraHttpClient _httpClient;
	private ObjectMapper _mapper;
	
	public SubscriptionService(ZuoraHttpClient httpClient)
	{
		_httpClient = httpClient;
		_mapper = new ObjectMapper();
		_mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
	}

	public List<Subscription> getSubscriptionsByAccountNumber(String accNumber) {
		String request = String.format(REQUEST_URL, accNumber);
		
		Response response = null;
		SubscriptionsWrapper subsc = null;
		try {
			response = _httpClient.makeGetRequest( request );
			
			subsc = _mapper.readValue( response.body().string(), SubscriptionsWrapper.class);
			
			System.out.println("Message: "+ response.message());
			if( ! subsc.success() ) {
				System.out.println("Success: "+ subsc.success() +"\n");
				System.out.println(response.body().string());
			} else {
				System.out.println(subsc);
				return subsc.getSubscriptions();
			}
		} catch (IOException e) {
			System.out.println("Failed requesting Subscriptions by AccountNumber:"+ accNumber +"\n");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * /v1/subscriptions/%s"
	 * @param id
	 * @return
	 */
	public Subscription getSubscriptionById(String id) {
		String request = String.format(SUBS_REQUEST_URL, id);
		
		Response response = null;
		Subscription subsc = null;
		try {
			response = _httpClient.makeGetRequest( request );
			
			subsc = _mapper.readValue( response.body().string(), Subscription.class);
			
			System.out.println("--> Message: "+ response.message());
			return subsc;
			
		} catch (IOException e) {
			System.out.println("Failed requesting Subscriptions by Id:"+ id +"\n");
			e.printStackTrace();
		}
		
		return null;
	}

}
