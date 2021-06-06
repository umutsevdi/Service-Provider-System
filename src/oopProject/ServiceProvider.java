package oopProject;

public interface ServiceProvider {
	public void addSubscriptionPlan(SubscriptionPlan item);
	
	public boolean removeSubscriptionPlan(SubscriptionPlan item);

	public SubscriptionPlan findSubscriptionPlan(String name);

	public String getName();

	public void setName(String name);
}
