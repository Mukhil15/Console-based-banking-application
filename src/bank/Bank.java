package bank;
import java.util.*;

import customer.Customer;
public class Bank {
	public static ArrayList<Customer> customers=new ArrayList<Customer>();
	public static HashMap<Integer,Customer> customersMap=new HashMap<Integer,Customer>();
	public static int refCustomerId;
	public static long refAccountNumber;
	public static final double INITIAL_BALANCE=10000.0;
}
