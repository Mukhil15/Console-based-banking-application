package customer;

public class Customer {
	private static final String SPACE=" ";
	private int customerId;
	private long accountNo;
	private String name;
	private double balance;
	private String password;
	public Customer(int customerId,long accountNo,String name,double balance,String password) {
		this.customerId=customerId;
		this.accountNo=accountNo;
		this.name=name;
		this.balance=balance;
		this.password=password;
	}
	//Getters
	public int getCustomerId() {
		return customerId;
	}
	public long getAccountNo() {
        return accountNo;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setCustomerId(int customerId) {
    	this.customerId=customerId;
    }
    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
    	return customerId+SPACE+accountNo+SPACE+name+SPACE+balance+SPACE+password;
    }
}
