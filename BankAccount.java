 package checkbookBalancing;

public class BankAccount {
	// private members (instance variables)
	private double runningBalance;
	private double totalCharges = 0;
	
	// Initialize constants
	private final double SERVICE_FEE = 0.25;
	private final double OVERDRAFT_FEE = 25;
	
	private boolean overdraft_flag;
	
	// Constructor
	public BankAccount(double runningBalance) {
		
		// Validate runningBalance
		if (runningBalance < 0) {
			throw new IllegalArgumentException("Balance cannot be negative!");
		} // End of if block
		
		this.runningBalance = runningBalance;
	} // End of constructor
	
	
	// Setter methods
	public void setRunningBalance(double runningBalance) {
		// Validate runningBalance
		if (runningBalance < 0) {
			throw new IllegalArgumentException("Balance cannot be negative!");
		} // End of if block
		
		this.runningBalance = runningBalance;
	} // End of setRunningBalance
	
	
	public void setOverdraftFlag() {
		overdraft_flag = false;
	} // End of resetOverdraftFlag
	
	
	// Getter methods
	public double getRunningBalance() {
		return runningBalance;
	} // End of getRunningBalance
	
	public double getTotalCharges() {
		return totalCharges;
	} // End of getTotalCharges
	
	
	public double getServiceFee() {
		return SERVICE_FEE;
	} // End of getServiceFee
	
	
	public double getOverdraftFee() {
		return OVERDRAFT_FEE;
	} // End of getOverdraftFee
	
	
	public boolean getOverdraftFlag() {
		return overdraft_flag;
	} // End of getOverdraftFlag
	
	
	// Other methods
	
	public double processWithdraw(double userAmount) {
		return runningBalance -= userAmount;
	} // End of processWithdraw
	
	
	public double processDeposit(double userAmount) {
		return runningBalance += userAmount;
	} // End of processDeposit
	
	
	public double calculateServiceFee() {
		return totalCharges += SERVICE_FEE;
	} // End of calculateServiceCharge
	
	
	public double calculateOverdraftFee() {
		return totalCharges += OVERDRAFT_FEE;
	} // End of calculateOverdraftCharge
	
	
	public void checkOverdraftFee() {
		// Check if running balance is negative
		if (runningBalance < 0) {
			// Call method to calculate overdraft fee
			calculateOverdraftFee();
			
			// Set flag to true if calculateOverdraftFee method is called
			overdraft_flag = true;
		} // End of if block
	} // End of checkOverdraftFee
	
	
	public void displayTransactionConfirmation() {
		// Display confirmation
		System.out.printf("\n\nProcessed...\nRemaining Balance: $%,.2f\nService Charge: $%,.2f", 
				runningBalance, SERVICE_FEE);
		
		// Check if overdraft fee is applied
		if (overdraft_flag) {
			System.out.printf("\nOverdraft Charge: $%,.2f", OVERDRAFT_FEE );
		} // End of if block
		
		System.out.printf("\nTotal Charges: $%,.2f", totalCharges);
	} // End of displayTransactionConfirmation
	
	
	
	public double calculateFinalBalance() {
		return runningBalance -= totalCharges;
	} // End of calculateFinalBalance
	
	
} // End of BankAccount
