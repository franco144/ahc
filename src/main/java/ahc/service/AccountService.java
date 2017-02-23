package ahc.service;

import ahc.httpclient.ZuoraHttpClient;

public class AccountService {
	
	private ZuoraHttpClient _httpClient;

	public AccountService( ZuoraHttpClient zuoraHttpClient )
	{
		_httpClient = zuoraHttpClient;
	}

}
