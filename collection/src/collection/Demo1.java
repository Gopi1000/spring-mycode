package collection;

public class Demo1 {

	    private double balance; // The internal state is private

	    public double getBalance() {
	        return balance; // Getter method provides read-only access
	    }

	    public void deposit(double amount) {
	        if (amount > 0) {
	            balance += amount; // Setter method enforces constraints
	        }
	    }

	    public void withdraw(double amount) {
	        if (amount > 0 && amount <= balance) {
	            balance -= amount;
	        }
	    }
	}
	

