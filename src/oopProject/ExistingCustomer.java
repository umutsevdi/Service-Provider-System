package oopProject;

public class ExistingCustomer extends Customer {
	private Subscription subscription;

	public ExistingCustomer(String citizenshipN) {
		super(citizenshipN);

	}

	public ExistingCustomer(String citizenshipN, Subscription subscription) {
		super(citizenshipN);
		this.subscription = subscription;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	@Override
	public String toString() {
		return super.toString()+ "\n\t" + subscription;
	}

}
