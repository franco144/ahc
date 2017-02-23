package ahc.httpclient.constants;

public enum ZuoraRestErrorCodes {
	
	IS_ORIGINAL_SUBSCRIPTION(50000040); // This is the original subscription and has no amendment.
	
	private int code;
	
	ZuoraRestErrorCodes(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
