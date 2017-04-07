package ahc.service.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class CamelCaseFieldsClass {
	
	@Override
	public String toString() {
		return "CamelCaseFieldsClass [_Id=" + _Id + ", _InvoiceId=" + _InvoiceId + "]";
	}
	public String getId() {
		return _Id;
	}
	public void setId(String id) {
		this._Id = id;
	}
	public String getInvoiceId() {
		return _InvoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this._InvoiceId = invoiceId;
	}
	
	private String _Id;
	private String _InvoiceId;
}
