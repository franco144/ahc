package ahc.httpclient.bean;

public class Reason {
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "Reason [code=" + code + ", message=" + message + "]";
	}

	private int code;
	private String message;
}