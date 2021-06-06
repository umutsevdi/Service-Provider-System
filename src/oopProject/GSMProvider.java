package oopProject;

import java.util.ArrayList;
import java.util.Iterator;

public class GSMProvider implements ServiceProvider {
	private String name;
	private ArrayList<SubscriptionPlan> subscriptionPlans;

	public GSMProvider(String name) {
		this.name = name;
		subscriptionPlans = new ArrayList<SubscriptionPlan>();
	}

	@Override
	public void addSubscriptionPlan(SubscriptionPlan item) {
		subscriptionPlans.add(item);
	}

	@Override
	public SubscriptionPlan findSubscriptionPlan(String planName) {
		Iterator<SubscriptionPlan> subsIterator = subscriptionPlans.iterator();
		while (subsIterator.hasNext()) {
			SubscriptionPlan iter = subsIterator.next();
			if (iter != null && iter.getName().equals(planName)) {
				return iter;
			}
		}
		return null;
	}

	@Override
	public boolean removeSubscriptionPlan(SubscriptionPlan item) {
		if (subscriptionPlans.contains(item)) {
			subscriptionPlans.remove(item);
			return true;
		} else
			return false;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;

	}

	@Override
	public String toString() {
		String response = "GSM Provider "+name;
		for(SubscriptionPlan iter : subscriptionPlans) {
			response+="\n"+iter.toString();
		}
		return response;
	}

}
