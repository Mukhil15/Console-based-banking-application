package bank;

import customer.Customer;

public class AccountActionHandler {
	public boolean deposit(int customerId,double amount) {
		Customer customer=Bank.customersMap.get(customerId);
		if(amount<0) {
			System.out.println("Invalid Amount");
			return false;
		}
		customer.setBalance(customer.getBalance()+amount);
		Bank.customersMap.put(customerId, customer);
		return true;
	}
	public boolean withDraw(int customerId,double amount) {
		Customer customer=Bank.customersMap.get(customerId);
		double balance=customer.getBalance()-amount;
		if(balance>=10000.0) {
			customer.setBalance(balance);
			Bank.customersMap.put(customerId, customer);
			return true;
		}
		System.out.println("InSufficient Balance. Crosses the Minimum Balance");
		return false;
	}
	public boolean transfer(int fromCustomerId, int toCustomerId,double amount) {
		Customer toCustomer=Bank.customersMap.get(toCustomerId);
		if(toCustomer==null) {
			System.out.println("Invalid Receiver CustomerId");
			return false;
		}
		//Customer fromCustomer=Bank.customersMap.get(fromCustomerId);
		if(withDraw(fromCustomerId,amount)) {
			
			return deposit(toCustomerId,amount);
		}else {
			return false;
		}
		
	}
}
