package oopProject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class InformationSystem {
	enum objectType {
		CUSTOMER, SERVICE, MENU
	};

	public static ArrayList<Customer> customers = new ArrayList<Customer>();
	public static ArrayList<ServiceProvider> serviceProviders = new ArrayList<ServiceProvider>();

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		// Sample Cases
		GSMProvider turkcell = new GSMProvider("Turkcell");
		SubscriptionPlan turkcell4GB = new SubscriptionPlan("Turkcell 4GB", turkcell);
		turkcell.addSubscriptionPlan(turkcell4GB);

		SubscriptionPlan turkcell1000SMS = new SubscriptionPlan("Turkcell 1000 SMS", turkcell);
		turkcell.addSubscriptionPlan(turkcell1000SMS);

		serviceProviders.add(turkcell);

		ExistingCustomer mehmet = new ExistingCustomer("32643509892");
		mehmet.setMail("mehmet@test.com");
		mehmet.setName("Mehmet Yilmaz");
		mehmet.setTel("12412221");
		mehmet.setSubscription(
				new Subscription(Date.from(Instant.now()), turkcell.findSubscriptionPlan("Turkcell 4GB")));

		customers.add(mehmet);

		ExistingCustomer ahmet = new ExistingCustomer("123123123");
		ahmet.setMail("ahmetahmet@yahoo.com");
		ahmet.setName("Ahmet Üçgen");
		ahmet.setTel("5357981234");
		ahmet.setSubscription(
				new Subscription(Date.from(Instant.now()), turkcell.findSubscriptionPlan("Turkcell 1000 SMS")));

		customers.add(ahmet);

		CableProvider digiturk = new CableProvider("Digiturk");
		SubscriptionPlan digiturkPaket = new SubscriptionPlan("Digiturk Paket", digiturk);
		digiturk.addSubscriptionPlan(digiturkPaket);

		SubscriptionPlan digiturkSporPaket = new SubscriptionPlan("Digiturk Spor Paket", digiturk);
		digiturk.addSubscriptionPlan(digiturkSporPaket);

		SubscriptionPlan digiturkBelgeselPaket = new SubscriptionPlan("Digiturk Belgesel Paket", digiturk);
		digiturk.addSubscriptionPlan(digiturkBelgeselPaket);

		serviceProviders.add(digiturk);

		for (ServiceProvider iter : serviceProviders)
			System.out.println(iter.toString());
		for (Customer iter : customers)
			System.out.println(iter.toString());

		// Information System Starts Here

		System.out.println("\n\nWelcome to Information System");
		String[] input = { "help", "" };
		objectType object = objectType.MENU;
		while (!input[0].equals("exit")) { // Takes input until exit command is received

			if (input[0].equals("switch")) // If switch command is received in any menu it will switch to the given menu
											// with switchMenu()
				object = switchMenu(object, input[1]);
			else if (object.name().equals("CUSTOMER")) // Customer Menu
				try {
					customerMenu(input, scan);
				} catch (Exception e) {
					System.out.println("Error :\t" + e.getLocalizedMessage());
				}

			else if (object.name().equals("SERVICE")) // Service Menu
				try {
					serviceProviderMenu(input, scan);
				} catch (Exception e) {
					System.out.println("Error :\t" + e.getLocalizedMessage());
				}

			else {
				switch (input[0]) {
				case "help": { // Main Menu Help commands
					System.out.println("Avaliable commands for Main Menu : "
							+ "\n\tswitch\tmenuName :\tSwitches to selected menu"
							+ "\n\thelp :\tDisplays avaliable commands" + "\n\texit :\tExits from the application");
					break;
				}
				default: { // Invalid Command Message
					System.out.println("Error :\tInvalid Command \n>Try using help command to see avaliable command.");
					break;
				}
				}

			}
			System.out.print(object + " > ");
			input = scan.nextLine().split(" "); // Splitting the next input
		}
		scan.close();
		System.out.println("...");
	}

	public static objectType switchMenu(objectType object, String input) { // Switches to the given menu if it has valid
																			// name
		try {
			return objectType.valueOf(input.toUpperCase());
		} catch (Exception e) {
			System.out.println(">Invalid Command Syntax");
			return object;
		}
	}

	@SuppressWarnings("deprecation")
	public static void customerMenu(String[] input, Scanner scan) throws Exception {
		switch (input[0]) {
		case "create": { // Create command
			if (input[1] != null) {
				for (Customer iter : customers) { // Searches whether given number is in the arraylist
					if (iter.getCitizenshipNr().equals(input[1])) {
						throw new Exception("Customer Exists"); // Throws an exception if found
					}
				}
				PossibleCustomer customer = new PossibleCustomer(input[1]); // Customers are created as possible by
																			// default, and stay that way until a
																			// subscription is assigned
				System.out.println(">Enter Customer Profile Informations of " + input[1] + " :");
				System.out.print(">\tName : ");
				customer.setName(scan.nextLine());
				System.out.print(">\tMail Address : ");
				customer.setMail(scan.nextLine());
				System.out.print(">\tPhone Number : ");
				customer.setTel(scan.nextLine());
				System.out.println(">Customer Created");
				customers.add(customer);
			} else {
				throw new Exception("Missing Parameter");
			}
			break;
		}
		case "delete": { // Delete Command
			boolean isFound = false;
			if (input[1] != null) {
				for (Customer iter : customers) {
					if (iter.getCitizenshipNr().equals(input[1])) { // Deletes customer if given number is found
						isFound = true;
						System.out.println(
								">Customer " + iter.getName() + "(" + iter.getCitizenshipNr() + ") has been removed");
						customers.remove(iter);
						break;

					}
				}
			} else {
				throw new Exception("Missing Parameter");
			}
			if (isFound)
				break;
			else
				throw new Exception("Customer does not exist");
		}
		case "edit": { // Edits all parameters of the customer
			boolean isFound = false;
			if (input[1] != null) {
				for (Customer iter : customers) {
					if (iter.getCitizenshipNr().equals(input[1])) {
						isFound = true;
						System.out.println(">Enter Customer Profile Informations of " + input[1] + " :");
						System.out.print(">\tName : ");
						iter.setName(scan.nextLine());
						System.out.print(">\tMail Address : ");
						iter.setMail(scan.nextLine());
						System.out.print(">\tPhone Number : ");
						iter.setTel(scan.nextLine());
						System.out.println(">Customer is Updated");
						break;
					}
				}
			} else {
				throw new Exception("Missing Parameter");
			}
			if (isFound)
				break;
			else
				throw new Exception("Customer does not exist");
		}
		case "start": { // Start a subscription
			if (input[1] != null) {
				for (Customer iter : customers) {
					if (iter.getCitizenshipNr().equals(input[1])) {
						if (iter.getClass().equals(PossibleCustomer.class)) {
							ExistingCustomer object = new ExistingCustomer(input[1]);
							object.setMail(iter.getMail());
							object.setName(iter.getName());
							object.setTel(iter.getTel());
							customers.remove(iter);
							customers.add(object); // Transforms a possible customer to existing customer and adds the
													// subscription
							System.out.println(iter.toString());

							System.out.println(">Enter the Subscription Details for " + input[1] + " :");
							System.out.print(">\tService Provier Name : ");
							String providerName = scan.nextLine();
							for (ServiceProvider provider : serviceProviders) {
								if (provider.getName().equals(providerName)) {
									System.out.print(">\tSubscription Plan : ");
									String subscriptionPlanName = scan.nextLine();
									SubscriptionPlan plan = provider.findSubscriptionPlan(subscriptionPlanName);
									if (plan != null) {
										System.out.print(">\tStart Date : ");
										String[] startDate = scan.nextLine().split("/"); // MM/DD/YY
										Date subscriptionDate = new Date();
										subscriptionDate.setMonth(Integer.parseInt(startDate[0]));
										subscriptionDate.setDate(Integer.parseInt(startDate[1]));
										subscriptionDate.setYear(Integer.parseInt(startDate[2]));
										object.setSubscription(new Subscription(subscriptionDate, plan));
										System.out.println(object.getSubscription().toString() + " was added to "+object.getName());
										break;
									} else {
										throw new Exception("Subscription Plan does not exist");
									}
								} else {
									throw new Exception("Service Provider does not exist");
								}
							}

						} else {
							throw new Exception("Customer has Subscription");
						}

						System.out.println(iter.getClass());
						break;

					}
				}
			} else {
				throw new Exception("Missing Parameter");
			}
			break;
		}
		case "end": { // Ends the subscription
			boolean isFound = false;
			if (input[1] != null) {
				for (Customer iter : customers) {
					if (iter.getCitizenshipNr().equals(input[1])) {
						isFound = true;
						if (iter.getClass().equals(ExistingCustomer.class)) {
							PossibleCustomer object = new PossibleCustomer(input[1]);
							object.setMail(iter.getMail());
							object.setName(iter.getName());
							object.setTel(iter.getTel());
							customers.remove(iter);
							customers.add(object); // Ends the subscription by turning customer back to possible
													// customer
							System.out.println("Subscription of "+object.getName()+ " has ended");
							break;
						} else {
							throw new Exception("Customer has no Subscription");
						}
					}
				}
			} else {
				throw new Exception("Missing Parameter");
			}
			if (isFound)
				break;
			else
				throw new Exception("Customer does not exist");
		}
		case "display": { // Displays given customer's details
			boolean isFound = false;
			if (input[1] != null) {
				for (Customer iter : customers) {
					if (iter.getCitizenshipNr().equals(input[1])) {
						System.out.println(">\n" + iter.toString());
						isFound = true;
						break;
					}
				}
			} else {
				throw new Exception("Missing Parameter");
			}
			if (isFound)
				break;
			else
				throw new Exception("Customer does not exist");
		}
		case "list": { // Lists all customers along with their citizenship number
			System.out.println("\tFull Name\tCitizenship Number");
			for (Customer iter : customers) {

				System.out.println(">\t- " + iter.getName() + "\t" + iter.getCitizenshipNr());
			}
			break;

		}
		case "help": {
			System.out.println(
					"Avaliable commands for Customer Menu : " + "\n\tcreate\tcitizenshipNumber :\tCreates a customer"
							+ "\n\tedit\tcitizenshipNumber :\tEdits properties of the customer"
							+ "\n\tstart\tcitizenshipNumber :\tStarts a subscription for the selected customer"
							+ "\n\tend\tcitizenshipNumber :\tEnds the subscription of selected customer"
							+ "\n\tdisplay\tcitizenshipNumber :\tDisplays detailed information of the customer"
							+ "\n\tlist :\tDisplays each customer in a list"
							+ "\n\n\tswitch\tmenuName :\tSwitches to selected menu"
							+ "\n\thelp :\tDisplays avaliable commands" + "\n\texit :\tExits from the application");
			break;
		}
		default: {
			System.out.println("Error :\tInvalid Command \n>Try using help command to see avaliable command.");
			break;
		}
		}

	}

	public static void serviceProviderMenu(String[] input, Scanner scan) throws Exception {
		switch (input[0]) {
		case "create": { // Creates a service provider
			if (input[1] != null) {
				for (ServiceProvider iter : serviceProviders) {
					if (iter.getName().equals(input[1])) { // Checks whether a service provider with the same name
															// exists
						throw new Exception("Service Provider Exists");
					}
				}
				System.out.println(">Enter Service Provider Informations of " + input[1] + " :");
				System.out.print(">\tService Provider Type (GSM/Cable) : ");
				String providerType = scan.nextLine();
				if (providerType.equals("GSM")) {
					GSMProvider object = new GSMProvider(input[1]);
					serviceProviders.add(object);
					System.out.println("GSM Provider is created");
				} else if (providerType.equals("Cable")) {
					CableProvider object = new CableProvider(input[1]);
					serviceProviders.add(object);
					System.out.println("Cable Provider is created");
				} else {
					throw new Exception("Invalid Service Provider Type");
				}
			} else {
				throw new Exception("Missing Parameter");
			}
			break;
		}
		case "delete": { // Deletes the service provider
			boolean isFound = false;
			if (input[1] != null) {
				for (ServiceProvider iter : serviceProviders) {
					if (iter.getName().equals(input[1])) {
						isFound = true;
						System.out.println(">Service Provider " + iter.getName() + " has been removed");
						serviceProviders.remove(iter);
						break;

					}
				}
			} else {
				throw new Exception("Missing Parameter");
			}
			if (isFound)
				break;
			else
				throw new Exception("Service Provider does not exist");
		}
		case "edit": { // Edits the name of service provider
			boolean isFound = false;
			if (input[1] != null) {
				for (ServiceProvider iter : serviceProviders) {
					if (iter.getName().equals(input[1])) {
						isFound = true;
						System.out.println(">Enter New Service Provider Name for " + input[1] + " :");
						System.out.print(">\tName : ");
						iter.setName(scan.nextLine());
						System.out.println(">Service Provider is Updated");
						break;
					}
				}
			} else {
				throw new Exception("Missing Parameter");
			}
			if (isFound)
				break;
			else
				throw new Exception("Service Provider does not exist");
		}
		case "addPlan": { // Adds a subscription plan to the service provider
			boolean isFound = false;
			if (input[1] != null) {
				for (ServiceProvider iter : serviceProviders) {
					if (iter.getName().equals(input[1])) {
						isFound = true;
						System.out.println(">Add New Subscription Plan to " + input[1] + " :");
						System.out.print(">\tSubscription Plan Name : ");
						iter.addSubscriptionPlan(new SubscriptionPlan(scan.nextLine(), iter));
						System.out.println(">Subscribe Plan is Added");
						break;
					}
				}
			} else {
				throw new Exception("Missing Parameter");
			}
			if (isFound)
				break;
			else
				throw new Exception("Service Provider does not exist");

		}
		case "deletePlan": { // Removes a subscription plan from the service provider.
			boolean isFound = false;
			if (input[1] != null) {
				for (ServiceProvider iter : serviceProviders) {
					if (iter.getName().equals(input[1])) {
						isFound = true;
						System.out.print(">\tSubscription Plan Name : ");
						if (iter.removeSubscriptionPlan(iter.findSubscriptionPlan(scan.nextLine()))) // Removes if
																										// exists,
																										// returns false
																										// otherwise
							System.out.println(">Subscribe Plan is removed");
						else
							System.out.println(">Subscribe Plan does not exist");
						break;
					}
				}
			} else {
				throw new Exception("Missing Parameter");
			}
			if (isFound)
				break;
			else
				throw new Exception("Service Provider does not exist");
		}
		case "display": { // Displays all details of selected service provider
			boolean isFound = false;
			if (input[1] != null) {
				for (ServiceProvider iter : serviceProviders) { // Finds the provider
					if (iter.getName().equals(input[1])) {
						System.out.println(">\n" + iter.toString());
						isFound = true;
						break;
					}
				}
			} else {
				throw new Exception("Missing Parameter");
			}
			if (isFound)
				break;
			else
				throw new Exception("Customer does not exist");
		}
		case "list": { // Displays name of all service providers along with their types
			System.out.println("\tProvider Name\tProvider Type");
			for (ServiceProvider iter : serviceProviders) {

				System.out.println(">\t- " + iter.getName() + "\t" + iter.getClass().getSimpleName());
			}
			break;

		}
		case "help": {
			System.out.println("Avaliable commands for Service Provider Menu : "
					+ "\n\tcreate\tserviceName :\tCreates a Service Provider"
					+ "\n\tedit\tserviceName :\tEdits name of the Service Provider"
					+ "\n\taddPlan\tserviceName :\tAdds a subscription plan to selected service provider"
					+ "\n\tdeletePlan\tserviceName :\tRemoves the subscription from selected service providerr"
					+ "\n\tdisplay\tserviceName :\tDisplays detailed information of the service provider"
					+ "\n\tlist :\tDisplays each service provider in a list"
					+ "\n\n\tswitch\tmenuName :\tSwitches to selected menu" + "\n\thelp :\tDisplays avaliable commands"
					+ "\n\texit :\tExits from the application");
			break;
		}
		default: {
			System.out.println("Error :\tInvalid Command \n>Try using help command to see avaliable command.");
			break;
		}
		}
	}
}