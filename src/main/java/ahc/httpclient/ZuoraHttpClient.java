package ahc.httpclient;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ZuoraHttpClient {
	
	private String _user;
	private String _password;
	private OkHttpClient _okHttpClient;

	public ZuoraHttpClient(OkHttpClient httpClient, String user, String password)
	{
		_okHttpClient = httpClient;
		_user = user;
		_password = password;
	}

	public Response makeGetRequest( String requestUrl ) throws IOException {
		System.out.println("GET Url: "+requestUrl );

//		MediaType mediaType = MediaType.parse("application/json");
		Request request = new Request.Builder()
		  .url( requestUrl )
		  .get()
		  .addHeader("apiaccesskeyid", _user)
		  .addHeader("apisecretaccesskey", _password)
		  .addHeader("content-type", "application/json")
		  .addHeader("cache-control", "no-cache")
		  .build();
		
		return _okHttpClient.newCall(request).execute();
	}

}
