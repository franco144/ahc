package ahc.service;

import static ahc.httpclient.constants.HttpClientConstants.ZUORA_SANDBOX_REST_ENDPOINT;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import ahc.httpclient.ZuoraHttpClient;
import ahc.service.bean.RatePlanObject;
import okhttp3.Response;

public class RatePlanService {
	private static final String REQUEST_URL_TEMPLATE = ZUORA_SANDBOX_REST_ENDPOINT + "/object/rate-plan/%s";
	
	private ZuoraHttpClient _httpClient;
	private ObjectMapper _mapper;
	
	public RatePlanService(ZuoraHttpClient httpClient)
	{
		_httpClient = httpClient;
		_mapper = new ObjectMapper();
		_mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
	}

	public RatePlanObject getRatePlanById(String id) {
		String requestUrl = String.format(REQUEST_URL_TEMPLATE, id);
		try {
			Response response = _httpClient.makeGetRequest(requestUrl);
			return _mapper.readValue(response.body().string(), RatePlanObject.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
