package ahc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import ahc.httpclient.ZuoraHttpClient;
import ahc.properties.ZuoraProperties;
import ahc.service.AccountService;
import ahc.service.RatePlanService;
import ahc.service.SubscriptionService;
import ahc.service.bean.Amendment;
import ahc.service.bean.RatePlan;
import ahc.service.bean.RatePlanObject;
import ahc.service.bean.Subscription;
import ahc.service.history.model.AmendmentHistory;
import ahc.service.history.model.SubscriptionHistory;
import okhttp3.OkHttpClient;

public class Main {
	
	private final String accNumber = "A00001492";
	
	public Main(ZuoraProperties properties) {
		init( properties.getRestUser(), properties.getRestPassword() );
	}
	
	public void startRetrieveHistory()
	{
		try {
			Set<SubscriptionHistory> subHistory = new HashSet<>();
			Map<String, SortedSet<AmendmentHistory>> amndHistory = new HashMap<String, SortedSet<AmendmentHistory>>();
			// get the history for account's subscriptions and amendments of each subscription
			retrieveHistoryV2(subHistory, amndHistory);
			
			// print out all the history
			printHistory(subHistory, amndHistory);
			System.out.println("\nFinished.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void retrieveHistoryV2(Set<SubscriptionHistory> subHistory,
			Map<String, SortedSet<AmendmentHistory>> amndHistory) throws Exception {
		
		// sanity checks
		if( subHistory == null )
			throw new IllegalStateException();
		if( amndHistory == null )
			throw new IllegalStateException();
		// get subscriptions
		List<Subscription> accSubsc = _subscSer.getSubscriptionsByAccountNumber(accNumber);
		if( accSubsc == null ){
			System.out.println("Cannot find subscriptions for account "+ accNumber);
			return;
		}
		
		System.out.println("Nr of subscriptions "+ accSubsc.size());
		for( Subscription sub : accSubsc ) {
			String baseSubscription = sub.getId();
			int loopThreshold = 5;
			do {
				System.out.println("\n\n");
				// get amendment by subsc ID
				Amendment amnd = _amndSer.getSubscriptionAmendments( baseSubscription );
				System.out.println("Found amnd: "+ amnd);
				
				// check if it's original subscription: it has no amendments
				if( amnd == null ) {
					// store original subscription in history
					SubscriptionHistory subH = new SubscriptionHistory();
					
					// get subscription with id of last baseSubscription so that only one rate plan will exist for it
					Subscription subscription = _subscSer.getSubscriptionById( baseSubscription );
					
					subH.setContractEffectiveDate( subscription.getContractEffectiveDate() );
					subH.setServiceActivationDate( subscription.getServiceActivationDate() );
					
					//sanity check: only one rate plan is expected
					if( subscription.getRatePlans().size() != 1 ) {
						System.out.println("[FATAL] excepted only one rate plan but found. SubId: "+ subscription.getId() );
						break;
					}
					// get the rate plan
					RatePlan ratePlan = subscription.getRatePlans().get(0);
					subH.setProductRatePlanId( ratePlan.getProductRatePlanId() );
					// each rate plan has only one rate plan charge 
					subH.setProductRatePlanChargeId( ratePlan.getRatePlanCharges().get(0).getProductRatePlanChargeId() );
					
					System.out.println("Adding to subscriptions..");
					subHistory.add( subH );
					
					// BASE_SUB = null --> stop looping
					baseSubscription = null;
				} else {
					// get info from rate plan (amnd.newrateplanId)
					RatePlanObject ratePlanObj = _rpSer.getRatePlanById( amnd.getNewRatePlanId() );
					
					AmendmentHistory amndH = new AmendmentHistory();
					
					amndH.setContractEffectiveDate( amnd.getContractEffectiveDate() );
					amndH.setServiceActivationDate( amnd.getServiceActivationDate() );
					amndH.setType( amnd.getType() );
					
					// RemoveProduct type has no rate plan (hence no rate plan charge) 
					// because the amendment itself removed it.
					// We store this as amendment with CE and SA and no rateplan
					if( ! amnd.getType().equals( AmendmentType.RemoveProduct.name() ) ) {
						String productRatePlanChargeId = searchRatePlanChargeId( sub, amnd.getNewRatePlanId() );
						
						amndH.setProductRatePlanId( ratePlanObj.getProductRatePlanId() );
						amndH.setProductRatePlanChargeId( productRatePlanChargeId );
					}
					
					System.out.println("Adding to amendments..");
					addToAmndHistory( amndH, sub.getId(), amndHistory );
					
					baseSubscription = amnd.getBaseSubscriptionId();
				}
				System.out.println("Last baseSubscriptionId "+ baseSubscription);
				
				if( loopThreshold <= 0 ) {
					System.out.println("[FATAL] threshold reached! Exiting..");
				}
			} while( baseSubscription != null && loopThreshold-- > 0 );
		}
	}

	private String searchRatePlanChargeId(Subscription sub, String newRatePlanId) {
		return sub.getRatePlans().stream()
				.filter( rp -> 	rp.getId().equals(newRatePlanId) )
				.findFirst()
				.get()
				.getRatePlanCharges()
				.stream()
				.findFirst()
				.get()
				.getProductRatePlanChargeId();
	}
	
	private void addToAmndHistory( AmendmentHistory item, String key, Map<String, SortedSet<AmendmentHistory>> amndHistory ) {
		SortedSet<AmendmentHistory> amndHSet = amndHistory.get(key);
		if( amndHSet == null )
			amndHSet = new TreeSet<>();
		amndHSet.add( item );
		amndHistory.put(key, amndHSet );
	}
	private void init(String user, String password) {
	
		// share between calls to save resources
		ZuoraHttpClient zuoraHttpClient = new ZuoraHttpClient(new OkHttpClient(), user, password);
	
		_accountSer = new AccountService(zuoraHttpClient);
		_rpSer = new RatePlanService(zuoraHttpClient);
		_subscSer = new SubscriptionService(zuoraHttpClient);
		_amndSer = new AmendmentService(zuoraHttpClient);
	}

	private void printHistory(Set<SubscriptionHistory> subHistory,
			Map<String, SortedSet<AmendmentHistory>> amndHistory) {
		
		System.out.println("\n\n======================Subscription's Rate Plans\n");
		for(SubscriptionHistory sub : subHistory) {
			System.out.println(sub);
		}
		
		System.out.println("\n\n======================Amendments\n");
		for(Entry<String, SortedSet<AmendmentHistory>> entry : amndHistory.entrySet()) {
			System.out.println(entry.getKey() +" ==> "+ entry.getValue());
		}
	}
	private AccountService _accountSer;
	private SubscriptionService _subscSer;
	private RatePlanService _rpSer;
	private AmendmentService _amndSer;
}
