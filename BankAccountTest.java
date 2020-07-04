package checkbookBalancing;

import java.util.Scanner;

// Program Description
/*
 * Write a Java program to help you balance your checkbook at the end of the month. The program
 * should have the user enter the initial balance followed by a series of transactions. For each
 * transaction, first have the user enter a transaction type. The valid transaction types are:
 * C - process a withdraw.
 * D - process a deposit.
 * E - perform end of program processing and exit the program.
 * 
 * For withdraws and deposits, the user should be prompted to enter the transaction amount. 
 */
 

public class BankAccountTest {
	
	public static void main(String[] args) {	
		// Instantiate object 
		BankAccountTest object1 = new BankAccountTest();
		
		// Create Scanner object
		Scanner input = new Scanner(System.in);
		
		// Initialize variables
		String initialBalancePrompt = "Enter initial balance: $";
		String transactionAmountPrompt = "Enter transaction amount: $";
		
		// Call getInitialBalance; Pass Scanner object and String variable as arguments; Store returning value.
		double initialBalance = object1.getUserAmount(input, initialBalancePrompt);
		
		// Instantiate object from BankAccount class
		BankAccount customer1 = new BankAccount(initialBalance);
		
		// Call processTransaction; Pass objects and String variable
		object1.processTransaction(customer1, object1, input, transactionAmountPrompt);
		
		// Call functions when user proceeds to quit program
		customer1.calculateFinalBalance();
		object1.quitProgram(customer1.getTotalCharges(), customer1.getRunningBalance());
				
		// Close Scanner object
		input.close();
	} // End of main

	
	// This method gets user input for initial balance
	public double getUserAmount(Scanner input, String userPrompt) {
		// Initialize variable
		double initialBalance = 0;

		
		// Loop until valid input is accepted
		do {
			// prompt user
			System.out.printf("\n%s", userPrompt);
			
			// Check for invalid input
			while(!input.hasNextDouble()) {
				System.out.printf("\n%s\n%s", "Invalid input!", userPrompt);

				// Advance scanner past current input(s)
				input.nextLine();
			} // End of while loop
			
			// Get input
			initialBalance = input.nextDouble();	

			// Check for negative input
			if (initialBalance < 0) {
				System.out.printf("\n%s", "Amount cannot be less than 0. Please try again.");
			} // End of if block
		
		} while (initialBalance < 0);
				
		return initialBalance;
	} // End of getInitialBalance

	public void displayMenu() {
		// display transaction menu
		System.out.printf("\n\n%s","----------------------------------------------------------" );
		System.out.printf("\n%s\n%s\n%s\n%s", "Select Transaction Type:", "C - Process a Withdraw", 
				"D - Process a Deposit", "E - Exit");
	} // End of displayMenu
	
	public char getTransactionInput(Scanner input) {
		char transactionInput;
		// Loop until valid input accepted
		do {
			// Prompt user with menu
			displayMenu();
			System.out.printf("\n\n%s", "Enter a transaction option: ");

			// Get one character input
			transactionInput = input.next().charAt(0);
			
			// Check for invalid inputs
			if(transactionInput != 'C') {
				if(transactionInput != 'D') {
					if(transactionInput != 'E') {
						System.out.printf("\n%s", "Invalid input! Try again and please enter C, D, or E.");
						
						transactionInput = 'X';
						
						// Advance scanner past current input(s)
						input.nextLine();
					} // End second nested if block
				} // End of nested if block
			} // End of if block
		} while (transactionInput == 'X');
	
		return transactionInput;
	} // end of getTransactionInput

	public void displayInputConfirmation(char transactionInput, double transactionAmount) {
			// Declare Variable
			String transactionType;
			
			// Check what type of transaction
			if (transactionInput == 'C') {
				transactionType = "withdraw";
			} // End of if block
			else {
				transactionType = "deposit";
			} // End of else block
			
			// Display output confirmation
			System.out.printf("\nProcessing %s for $%,.2f", transactionType, transactionAmount);
	} // End of displayInputConfirmation
	
	public void quitProgram(double totalCharges, double finalBalance) {
		System.out.printf("\n\nProcessing end of program...\nDeducting Service Charges: $%,.2f"
				+ "\nFinal Balance: $%,.2f", totalCharges, finalBalance);
	} // End of quitProgram
	
	public void processTransaction(BankAccount customer1, BankAccountTest object1, Scanner input, 
			String transactionAmountPrompt) {
		// Declare variable
		char transactionInput;
		double transactionAmount = 0;

		// Loop until user inputs 'E' to quit
		do {
			// Call function; Pass Scanner object as argument; Store returning value in transactionInput
			transactionInput = object1.getTransactionInput(input);
					
			if (transactionInput == 'C' || transactionInput == 'D') {
				// Call method; Pass Scanner object as argument; Store returning value in transactionInput
				transactionAmount = object1.getUserAmount(input, transactionAmountPrompt);
										
				// Call method; Pass transactionInput and transactionAmount as arguments
				object1.displayInputConfirmation(transactionInput, transactionAmount);			
			
				// Call method; Pass BankAccount object, transactionInput and transactionAmount as arguments
				object1.determineTransactionType(customer1, transactionInput, transactionAmount);

				// Call functions from BankAccount class
				customer1.calculateServiceFee();
				customer1.displayTransactionConfirmation();
				customer1.setOverdraftFlag(); // Reset overdraft flag			

			} // End of if block	
		} 	while (transactionInput != 'E'); // End of do-while
	} // End of processTransaction
	
	// This method determines which type of transaction to process (Withdraw or Deposit)
	public void determineTransactionType(BankAccount customer1, char transactionInput, double transactionAmount) {
		// Determine which type of transaction to process
		switch(transactionInput) {
			case 'C':
				customer1.processWithdraw(transactionAmount);
				customer1.checkOverdraftFee();
				break;
				
			case 'D':
				customer1.processDeposit(transactionAmount);
				break;
		} // End of switch
	} // End of determineTransactionType
	
	
} // End of BankAccountTest class