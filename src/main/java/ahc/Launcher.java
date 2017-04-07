package ahc;

import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

import com.fasterxml.jackson.databind.ObjectMapper;

import ahc.httpclient.bean.ZuoraRestErrorResponse;
import ahc.properties.ZuoraProperties;
import ahc.service.SubscriptionsWrapper;
import ahc.service.bean.Amendment;
import ahc.service.bean.CamelCaseFieldsClass;
import ahc.service.bean.RatePlanObject;
import ahc.service.bean.Subscription;

public class Launcher {

	public static void main(String[] args) {
		
		ZuoraProperties properties = new ZuoraProperties();
		Main main = new Main(properties);
		
		main.startRetrieveHistory();
		
//		testParseCamelCase();
//		testCompare();
//		testJsonMapperRatePlan();
//		testJsonMapperAmendment();
//		testStreamFilter();
//		testErrorResponse();
	}
	
	private static void testParseCamelCase() {
		String postBody = "{\"Id\":123,\"InvoiceId\":\"F91198EB81E4BBDA\"}";
		ObjectMapper mapper = new ObjectMapper();
		try {
			CamelCaseFieldsClass carl = mapper.readValue(postBody, CamelCaseFieldsClass.class);
			System.out.println( carl );
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.exit(0);
	}
	
	private static void testErrorResponse() {
		String errorResp = "{\"success\":false,\"processId\":\"F91198EB81E4BBDA\",\"reasons\":[{\"code\":50000040,\"message\":\"This is the original subscription and has no amendment.\"}]}";
		ObjectMapper mapper = new ObjectMapper();
		try {
			ZuoraRestErrorResponse error = mapper.readValue(errorResp, ZuoraRestErrorResponse.class);
			System.out.println( error );
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.exit(0);
	}

	private static void testStreamFilter() {
		String toParse = "{\"subscriptions\":[{\"id\":\"2c92c0f95a4b4895015a62986a714b6a\",\"accountId\":\"2c92c0f95a4b48b8015a62953c4c1a98\",\"accountNumber\":\"A00001492\",\"accountName\":\"Fra11 Franz\",\"invoiceOwnerAccountId\":\"2c92c0f95a4b48b8015a62953c4c1a98\",\"invoiceOwnerAccountNumber\":\"A00001492\",\"invoiceOwnerAccountName\":\"Fra11 Franz\",\"subscriptionNumber\":\"A-S00005992\",\"termType\":\"EVERGREEN\",\"invoiceSeparately\":false,\"contractEffectiveDate\":\"2016-11-12\",\"serviceActivationDate\":\"2016-11-15\",\"customerAcceptanceDate\":\"2016-11-15\",\"subscriptionStartDate\":\"2016-11-12\",\"termStartDate\":\"2016-11-12\",\"termEndDate\":null,\"initialTerm\":null,\"initialTermPeriodType\":\"Month\",\"currentTerm\":null,\"currentTermPeriodType\":\"Month\",\"autoRenew\":false,\"renewalSetting\":\"RENEW_TO_EVERGREEN\",\"renewalTerm\":null,\"renewalTermPeriodType\":\"Month\",\"contractedMrr\":29.99,\"totalContractedValue\":null,\"notes\":\"\",\"status\":\"Active\",\"ship_to_postalcode__c\":null,\"QuoteNumber__QT\":null,\"QuoteBusinessType__QT\":null,\"ship_to_state__c\":null,\"ship_to_city__c\":null,\"OpportunityName__QT\":null,\"ship_to_country__c\":null,\"nubo_subscription_id__c\":\"6347\",\"ship_to_lastname__c\":null,\"SalesChannel__c\":\"Other\",\"ship_to_address1__c\":null,\"ship_to_address2__c\":null,\"ship_to_firstname__c\":null,\"OpportunityCloseDate__QT\":null,\"CpqBundleJsonId__QT\":null,\"QuoteType__QT\":null,\"ratePlans\":[{\"id\":\"2c92c0f85a4b3a23015a6298082210f9\",\"lastChangeType\":\"Add\",\"productId\":\"2c92c0f94f054902014f0799732a668b\",\"productName\":\"Nubo Pro\",\"productSku\":\"SKU-00000014\",\"productRatePlanId\":\"2c92c0f94f054903014f079b7f2a6c6a\",\"ratePlanName\":\"Pro Month B2C\",\"ratePlanCharges\":[{\"id\":\"2c92c0f85a4b3a23015a6298082210fa\",\"originalChargeId\":\"2c92c0f85a4b3a23015a6298082210fa\",\"productRatePlanChargeId\":\"2c92c0f84f05413d014f07af247e6e14\",\"number\":\"C-00007172\",\"name\":\"Pro Month\",\"type\":\"Recurring\",\"model\":\"Volume\",\"uom\":\"License\",\"version\":1,\"pricingSummary\":\"EUR29.99 flat fee for 1 License or more\",\"priceChangeOption\":null,\"priceIncreasePercentage\":null,\"currency\":\"EUR\",\"price\":null,\"tiers\":[{\"tier\":1,\"startingUnit\":1,\"endingUnit\":null,\"price\":29.99,\"priceFormat\":\"FlatFee\"}],\"includedUnits\":null,\"overagePrice\":null,\"discountPercentage\":null,\"discountAmount\":null,\"applyDiscountTo\":null,\"discountLevel\":null,\"billingDay\":\"DefaultFromCustomer\",\"listPriceBase\":\"Per_Billing_Period\",\"billingPeriod\":\"Month\",\"specificBillingPeriod\":null,\"billingTiming\":\"IN_ADVANCE\",\"billingPeriodAlignment\":\"AlignToCharge\",\"quantity\":1,\"smoothingModel\":null,\"numberOfPeriods\":null,\"overageCalculationOption\":null,\"overageUnusedUnitsCreditOption\":null,\"unusedUnitsCreditRates\":null,\"usageRecordRatingOption\":null,\"segment\":1,\"effectiveStartDate\":\"2017-01-10\",\"effectiveEndDate\":null,\"processedThroughDate\":null,\"chargedThroughDate\":null,\"done\":false,\"triggerDate\":null,\"triggerEvent\":\"ServiceActivation\",\"endDateCondition\":\"Subscription_End\",\"upToPeriodsType\":null,\"upToPeriods\":null,\"specificEndDate\":null,\"mrr\":29.99,\"dmrc\":29.99,\"tcv\":null,\"dtcv\":null,\"description\":\"Pro Month\",\"ShippingDate__c\":null,\"ShippingStatus__c\":\"New Order\",\"CameraType__c\":null}],\"subscriptionProductFeatures\":[]},{\"id\":\"2c92c0f95a4b4895015a62986a874b6e\",\"lastChangeType\":\"Remove\",\"productId\":\"2c92c0f84f0540f3014f078f71a065d6\",\"productName\":\"Nubo Basic\",\"productSku\":\"SKU-00000013\",\"productRatePlanId\":\"2c92c0f94f054902014f07907fdb44e4\",\"ratePlanName\":\"Basic Month B2C\",\"ratePlanCharges\":[{\"id\":\"2c92c0f95a4b4895015a62986a884b6f\",\"originalChargeId\":\"2c92c0f85a4b3a20015a6296576d6cf3\",\"productRatePlanChargeId\":\"2c92c0f94f0548fe014f0795b5b350ed\",\"number\":\"C-00007171\",\"name\":\"Basic Month\",\"type\":\"Recurring\",\"model\":\"Volume\",\"uom\":\"License\",\"version\":2,\"pricingSummary\":\"EUR9.99/License for 1 License or more\",\"priceChangeOption\":null,\"priceIncreasePercentage\":null,\"currency\":\"EUR\",\"price\":null,\"tiers\":[{\"tier\":1,\"startingUnit\":1,\"endingUnit\":null,\"price\":9.99,\"priceFormat\":\"PerUnit\"}],\"includedUnits\":null,\"overagePrice\":null,\"discountPercentage\":null,\"discountAmount\":null,\"applyDiscountTo\":null,\"discountLevel\":null,\"billingDay\":\"DefaultFromCustomer\",\"listPriceBase\":\"Per_Billing_Period\",\"billingPeriod\":\"Month\",\"specificBillingPeriod\":null,\"billingTiming\":\"IN_ADVANCE\",\"billingPeriodAlignment\":\"AlignToCharge\",\"quantity\":1,\"smoothingModel\":null,\"numberOfPeriods\":null,\"overageCalculationOption\":null,\"overageUnusedUnitsCreditOption\":null,\"unusedUnitsCreditRates\":null,\"usageRecordRatingOption\":null,\"segment\":1,\"effectiveStartDate\":\"2016-11-15\",\"effectiveEndDate\":\"2017-01-10\",\"processedThroughDate\":null,\"chargedThroughDate\":null,\"done\":false,\"triggerDate\":null,\"triggerEvent\":\"ServiceActivation\",\"endDateCondition\":\"Subscription_End\",\"upToPeriodsType\":null,\"upToPeriods\":null,\"specificEndDate\":null,\"mrr\":9.99,\"dmrc\":0,\"tcv\":null,\"dtcv\":null,\"description\":\"Basic Month\",\"ShippingDate__c\":null,\"ShippingStatus__c\":\"New Order\",\"CameraType__c\":null}],\"subscriptionProductFeatures\":[]}]}],\"success\":true}";
		ObjectMapper mapper = new ObjectMapper();
		SubscriptionsWrapper subW = null;
		try {
			subW = mapper.readValue(toParse, SubscriptionsWrapper.class);
			System.out.println( subW );
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		if( subW == null ) {
			System.out.println( "subW is NULL" );
			return;
		}
		
		Subscription sub = subW.getSubscriptions().stream().filter( s -> s.getId().equals("2c92c0f95a4b4895015a62986a714b6a")).findFirst().get();
		System.out.println("Sub found ? "+ (sub != null));
		
		String productRatePlanChargeId = 
				sub.getRatePlans().stream()
				.filter( rp -> 	rp.getId().equals("2c92c0f85a4b3a23015a6298082210f9")	)
				.findFirst()
				.get()
				.getRatePlanCharges()
				.stream()
				.findFirst()
				.get()
				.getProductRatePlanChargeId();
		
		System.out.println("PrpcID found ? "+ productRatePlanChargeId);
	}

	private static void testJsonMapperAmendment() {
		String toParse = "{\"success\":true,\"id\":\"2c92c0f95a4b48b8015a6297d4b02848\",\"code\":\"A-AM00001904\",\"name\":\"New Product\",\"type\":\"NewProduct\",\"description\":\"\",\"status\":\"Completed\",\"contractEffectiveDate\":\"2017-01-10\",\"serviceActivationDate\":\"2017-01-10\",\"customerAcceptanceDate\":\"2017-01-10\",\"effectiveDate\":\"2017-01-10\",\"newSubscriptionId\":\"2c92c0f95a4b4895015a62986a714b6a\",\"baseSubscriptionId\":\"2c92c0f95a4b489a015a62977bc87671\",\"termType\":null,\"currentTerm\":null,\"currentTermPeriodType\":null,\"termStartDate\":null,\"renewalSetting\":null,\"renewalTerm\":null,\"renewalTermPeriodType\":null,\"autoRenew\":null,\"specificUpdateDate\":null,\"newRatePlanId\":\"2c92c0f85a4b3a23015a6298082210f9\",\"baseRatePlanId\":null,\"destinationAccountId\":null,\"destinationInvoiceOwnerId\":null}";
		ObjectMapper mapper = new ObjectMapper();
		try {
			Amendment amndo = mapper.readValue(toParse, Amendment.class);
			System.out.println( amndo );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		System.exit(0);
	}

	private static void testJsonMapperRatePlan() {
		String toParse = "{\"Id\":\"2c92c0f85a4b3a23015a6298082210f9\",\"CreatedById\":\"2c92c0f852b47fa10152cb9f0fbe2748\",\"CreatedDate\":\"2017-02-21T22:33:35.000+01:00\",\"Name\":\"Pro Month B2C\",\"AmendmentId\":\"2c92c0f95a4b48b8015a6297d4b02848\",\"SubscriptionId\":\"2c92c0f95a4b4895015a62986a714b6a\",\"AmendmentType\":\"NewProduct\",\"ProductRatePlanId\":\"2c92c0f94f054903014f079b7f2a6c6a\",\"UpdatedById\":\"2c92c0f852b47fa10152cb9f0fbe2748\",\"UpdatedDate\":\"2017-02-21T22:34:00.000+01:00\"}";
		ObjectMapper mapper = new ObjectMapper();
		try {
			RatePlanObject rpo = mapper.readValue(toParse, RatePlanObject.class);
			System.out.println( rpo );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		System.exit(0);
	}

	private static void testCompare() {
		SortedSet<MyClass> intsort = new TreeSet<>();
		MyClass a = new MyClass(5);
		MyClass b = new MyClass(1);
		MyClass c = new MyClass(3);
		MyClass d = new MyClass(2);
		MyClass e = new MyClass(4);
		
		intsort.add(a);
		intsort.add(b);
		intsort.add(c);
		intsort.add(d);
		intsort.add(e);
		
//		for( MyClass item : intsort )
//			System.out.println(item);
		
	}
}

class MyClass implements Comparable<MyClass> {
	private int _i;
	public MyClass(int i) {
		_i = i;
	}
	public int getI() {
		return _i;
	}
	
	@Override
	public String toString() {
		return "MyClass [_i=" + _i + "]";
	}
	@Override
	public int compareTo(MyClass o) {
		if( this.getI() < o.getI() )
			return 1;
		else if( this.getI() > o.getI() )
			return -1;
		return 0;
	}
	
}
