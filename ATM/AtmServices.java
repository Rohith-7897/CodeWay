package Week3;

import java.util.HashSet;
import java.util.List;

public class AtmServices implements ATM {

	HashSet<UserBankAccount> user = new HashSet<>();

	UserBankAccount user1 = new UserBankAccount(1050, 7897, 1234);
	UserBankAccount user2 = new UserBankAccount(2060, 1234, 7897);
	UserBankAccount user3 = new UserBankAccount(3050, 4567, 7654);
	UserBankAccount user4 = new UserBankAccount(4060, 6789, 9875);

	public AtmServices() {
		user.add(user1);
		user.add(user2);
		user.add(user3);
		user.add(user4);
	}

	private int cardNum;
	private int pinNum;

	public int getCardNum() {
		return cardNum;
	}

	public int getPinNum() {
		return pinNum;
	}

	public int setPinNum(int pinNum) {
		return this.pinNum = pinNum;
	}

	public int setCardNum(int cardNum) {
		return this.cardNum = cardNum;
	}

	@Override
	public int withdraw(int amount) {
		if (isPresent(getCardNum())) {
			for (UserBankAccount customer : user) {
				if (customer.getAtmCardNum() == getCardNum() && customer.getBalance() >= amount) {
					if (customer.getAtmPinNum() == getPinNum()) {
						customer.setBalance(customer.getBalance() - amount);
						customer.addTransaction(amount, "Rupees withdraw");
						System.out.println("WithDraw Successful !!");
						System.out.println("Available balance: " + customer.getBalance());
						break;
					} else if (customer.getAtmPinNum() == getPinNum()) {
						System.out.println("Wrong Credentials !");
					}
				} else if (customer.getAtmPinNum() == getPinNum()) {
					System.out.println("Insufficient Balance.");
				}
			}
		} else {
			System.out.println("User Not found !");
		}
		return amount;
	}

	@Override
	public void deposit(int amount) {
		if (isPresent(getCardNum())) {
			for (UserBankAccount customer : user) {
				if (customer.getAtmCardNum() == getCardNum() && customer.getAtmPinNum() == getPinNum()) {
					customer.setBalance(customer.getBalance() + amount);
					customer.addTransaction(amount, "Rupees Credited");
					System.out.println("Deposited Successful !!");
					System.out.println("Available balance: " + customer.getBalance());
					break;
				} else if (customer.getAtmCardNum() == getCardNum()) {
					System.out.println("Wrong Credentials !");
				}
			}
		} else {
			System.out.println("User Not found !");
		}
	}

	@Override
	public double balance() {
		if (isPresent(getCardNum())) {
			for (UserBankAccount customer : user) {
				if (customer.getAtmCardNum() == getCardNum() && customer.getAtmPinNum() == getPinNum()) {
					return customer.getBalance();
				} else if (customer.getAtmCardNum() == getCardNum()) {
					System.out.println("Wrong credentials !");
					return 0;
				}
			}
		}
		System.out.println("User Not found !");
		return 0;
	}

	public void passbookStatement(int cardNum) {
		for (UserBankAccount ctm : user) {
			if (ctm.getAtmCardNum() == cardNum) {
				List<UserBankAccount.Transaction> transactions = ctm.getStatement();
				int size = transactions.size();

				System.out.println("Last 10 Transactions for Card Number: " + cardNum);
				if (size == 0) {
					System.out.println("No Transaction found !");
					return;
				}
				int startIndex = Math.max(0, size - 10); // Start index to ensure at most 10 transactions are printed
				for (int i = startIndex; i < size; i++) {
					UserBankAccount.Transaction transaction = transactions.get(i);
					System.out.println("--> " + transaction.getAmount() + " " + transaction.getDescription()
							+ " ,Balance: " + transaction.getBalance());
				}
				return;
			}
		}
		System.out.println("Account Not found !");
	}

	public boolean isPresent(int cardNum) {
		for (UserBankAccount customer : user) {
			if (customer.getAtmCardNum() == cardNum)
				return true;
		}
		return false;
	}

}
