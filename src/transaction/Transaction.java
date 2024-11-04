package transaction;

public class Transaction {
	private int transactionId;
	private String type;
	private double amount;
	private double balance;
	public int getTransactionId() {
		return transactionId;
	}
	public String getType() {
		return type;
	}
	public double amount() {
		return amount;
	}
	public double balance() {
		return balance;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId=transactionId;
	}
	public void setType() {
		this.type=type;
	}
	public void setAmount(double amount) {
	    this.amount = amount;
	}

	public void setBalance(double balance) {
	    this.balance = balance;
	}
	
}
