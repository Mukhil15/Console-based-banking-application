package customer;

import java.util.*;

import bank.Bank;

public class CustomerHandler {
	Scanner in=new Scanner(System.in);
	public void addCustomer() {
		System.out.println("Enter the Name :");
		String name=in.nextLine();
		System.out.println("Enter the Password :");
		String password=in.next();
		if(!isValidPassword(password)) {
			System.out.println("Add customer failed: Invalid password, Note : Password must contain atleast and only uppercase,lowercase and numbers");
			return;
		}
		System.out.println("Enter the Confirm Password :");
		String Confirm_password=in.next();
		if(!password.equals(Confirm_password)) {
			System.out.println("Add customer failed: Mismatch in password");
			return;
		}
		Customer customer=new Customer(
				++Bank.refCustomerId,
				++Bank.refAccountNumber,
				name,
				Bank.INITIAL_BALANCE,
				encryption(password)
				);
		Bank.customers.add(customer);
		Bank.customersMap.put(customer.getCustomerId(), customer);
		CustomerFileHandler customerFileHandler = new CustomerFileHandler();
		customerFileHandler.AddCustomertoFile();
		System.out.println("Created Successfully");
	}
	private boolean isValidPassword(String password) {
		//it should only contain Uppercase,LowerCase,Numbers
		//it should contain atleast one Uppercase,lowercase and digit
		boolean uppercase=false,lowercase=false,digit=false;
		char []charpassword=password.toCharArray();
		for(char character:charpassword) {
			if(character>='a' && character<='z') {
				lowercase=true;
			}else if(character>='A' && character<='Z') {
				uppercase=true;
			}
			else if(character>='0'&&character<='9') {
				digit=true;
			}else {
				return false;
			}
		}
		//below if statement is placed outside the for loop because it should also check whether it contains only numbers, uppercase and lowercases
		//so it traverse complete String
		if(uppercase && lowercase && digit) {
			return true;
		}
		return false;
	}
	//SWAP the character like if it is A then Z, B THEN Y, C THEN X, a then z, 1 THEN 8. 0 then 9
	private String encryption(String password) {
		String encrypted_password="";
		for(int i=0;i<password.length();i++) {
			char character=password.charAt(i);
			if(character>='a' && character<='z') {
				encrypted_password+=(char)('z'-(character-'a'));
			}
			else if(character>='A' && character<='Z') {
				encrypted_password+=(char)('Z'-(character-'A'));
			}else {
				encrypted_password+=(char)('9'-(character-'0'));
			}
		}
		return encrypted_password;
	}
	public boolean authentication(int customerId, String password) {
		String encrypted_password=encryption(password);
		Customer c=Bank.customersMap.get(customerId);
		if(c==null) {
			return false;
		}
		else {
			if(c.getPassword().equals(encrypted_password)) {
				return true;
			}
		}
		return false;
	}

	
}
