package customer;
import java.io.*;
import java.util.*;
import customer.Customer;
import bank.Bank;
public class CustomerFileHandler {
private static final String filename="src/bank_db.txt";
public void initialize() throws IOException {
	ArrayList<Customer> customers=new ArrayList<Customer>();
	HashMap<Integer,Customer> customersMap=new HashMap<Integer,Customer>();
	File file=new File(filename);
	BufferedReader reader=new BufferedReader(
			new FileReader(file)
			);
	String customerInfo=reader.readLine();
	while(customerInfo!=null){
		Customer customer=castStringToCustomer(customerInfo);
		customersMap.put(customer.getCustomerId(),customer);
		customers.add(customer);
		customerInfo=reader.readLine();
		}
	reader.close();
	Bank.customers=customers;
	Bank.customersMap=customersMap;
	Bank.refCustomerId=customers.get(customers.size()-1).getCustomerId();
	Bank.refAccountNumber=customers.get(customers.size()-1).getAccountNo();
}
private Customer castStringToCustomer(String customerInfo) {
	String []splitedInfo=customerInfo.split(" ");
	Customer customer=new Customer(
			Integer.parseInt(splitedInfo[0]),
			Long.parseLong(splitedInfo[1]),
			splitedInfo[2],
			Double.parseDouble(splitedInfo[3]),
			splitedInfo[4]
			);
	return customer;
			
}
public void AddCustomertoFile() {
	File file=new File(filename);
	FileWriter writer =null;
	try {
		writer=new FileWriter(file,true);
		writer.write("\n"+Bank.customers.get(Bank.customers.size()-1).toString());
	}catch(IOException e) {
		e.printStackTrace();
	}finally {
		if(writer!=null)
		try{
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
public void finilizeFile() {
	File file=new File(filename);
	FileWriter writer=null;
	try {
		writer=new FileWriter(file);
		Set keySet=Bank.customersMap.keySet();
		Iterator iterator=keySet.iterator();
		while(iterator.hasNext()) {
			int customerId=(int)iterator.next();
			Customer customer=Bank.customersMap.get(customerId);
			writer.write(customer.toString()+"\n");
		}
		
	}catch(IOException e) {
		e.printStackTrace();
	}finally {
        // Ensure the FileWriter is properly closed
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
}


