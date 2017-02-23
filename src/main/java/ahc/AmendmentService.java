package ahc;

import static ahc.httpclient.constants.HttpClientConstants.ZUORA_SANDBOX_REST_ENDPOINT;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import ahc.httpclient.ZuoraHttpClient;
import ahc.httpclient.bean.ZuoraRestErrorResponse;
import ahc.httpclient.constants.ZuoraRestErrorCodes;
import ahc.service.bean.Amendment;
import okhttp3.Response;

public class AmendmentService {

	private static final String SUBS_AMND_REQUEST_URL = ZUORA_SANDBOX_REST_ENDPOINT + "/amendments/subscriptions/%s";

	private ZuoraHttpClient _httpClient;
	private ObjectMapper _mapper;

	public AmendmentService(ZuoraHttpClient httpClient) {
		_httpClient = httpClient;
		_mapper = new ObjectMapper();
		_mapper.setVisibilityChecker(
				VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
	}

	/**
	 * /v1/amendments/subscriptions/{subscriptionId}
	 * 
	 * @param id
	 * @return null if no Amendment has been found for given subscription ID
	 * @throws Exception
	 *             thrown if unhandled error response is returned from the API
	 *             call
	 */
	public Amendment getSubscriptionAmendments(String subscriptionId) throws Exception {
		String requestUrl = String.format(SUBS_AMND_REQUEST_URL, subscriptionId);

		Response response = null;
		Amendment amendment = null;
		try {
			response = _httpClient.makeGetRequest(requestUrl);
			
			String responseAsString = response.body().string();
			amendment = _mapper.readValue( responseAsString, Amendment.class);


			if (amendment.getSuccess()) {
				System.out.println("--> Message: " + response.message());
				return amendment;
			}

			// error occurred
			ZuoraRestErrorResponse error = _mapper.readValue(responseAsString, ZuoraRestErrorResponse.class);
			if (error.getReasons().size() != 1) {
				System.out.println("Multiple error response's resons received!");
			} else if (error.getReasons().get(0).getCode() == ZuoraRestErrorCodes.IS_ORIGINAL_SUBSCRIPTION.getCode()) {
				System.out.println("--> Error: " + error.getReasons().get(0).getMessage());
				return null;
			}

			throw new Exception("Unexpected error response");

		} catch (IOException e) {
			System.out.println("Failed requesting Amendment by Id:" + subscriptionId + "\n");
			e.printStackTrace();
		}
		return null;
	}

}
