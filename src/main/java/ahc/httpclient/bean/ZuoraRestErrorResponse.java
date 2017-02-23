package ahc.httpclient.bean;

import java.util.List;
/**
 * Describe an error with the following JSON format:
 * {
	    "success": false,
	    "processId": "F91198EB81E4BBDA",
	    "reasons": [
	        {
	            "code": 50000040,
	            "message": "This is the original subscription and has no amendment."
	        }
	    ]
	}
 * @author Francesco
 *
 */
public class ZuoraRestErrorResponse {
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public List<Reason> getReasons() {
		return reasons;
	}
	public void setReasons(List<Reason> reasons) {
		this.reasons = reasons;
	}

	@Override
	public String toString() {
		return "ZuoraRestErrorResponse [success=" + success 
				+ ", processId=" + processId 
				+ ", reasons=" + reasons + "]";
	}

	private Boolean success;
	private String processId;
	private List<Reason> reasons;
	
}
