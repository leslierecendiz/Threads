//Name: Leslie Recendiz
//Course: CNT 4714Spring 2021
//Assignment title: Project2–Synchronized, Cooperating ThreadsUnder Locking
//Due Date:February 14, 2021

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
	 private Lock threadLock = new ReentrantLock();
     private Condition depositIsPossible = threadLock.newCondition();
	 private Condition withdrawalIsPossible = threadLock.newCondition();
	 
	 int balance = 0;
	 
	  public void Withdraw(int withdrawalAmount, String thread){
	        threadLock.lock();

	        if (balance >= withdrawalAmount){
	            balance -= withdrawalAmount;
	            System.out.printf("\t\t\t\t\t%s withdraws $%s\t\t(-)BALANCE is $%d\n", thread, 
	                withdrawalAmount, balance);
	            
	        }
	        else{
	            System.out.println("\t\t\t\t\t" + thread + " withdraws $" 
	                + withdrawalAmount + "\t\t(******) WITHDRAWAL BLOCKED - Insufficient Funds!");

	            try{
	                depositIsPossible.signalAll();
	                withdrawalIsPossible.await();
	            }//try            
	            catch (InterruptedException e){
	                e.printStackTrace();
	            }//catch
	        }
	        threadLock.unlock();
	    }
	  
	  
	  public void Deposit(int depositAmount, String thread){
	        threadLock.lock();

	        balance +=depositAmount;
	        System.out.printf("%s deposits $%s\t\t\t\t\t\t\t(+)BALANCE is $%d\n", thread, 
	            depositAmount, balance);

	        try {
	            withdrawalIsPossible.signalAll();
	            depositIsPossible.await();
	        }//try       
	        catch (InterruptedException e) {
	            e.printStackTrace();
	        }//catch
	        threadLock.unlock();
	  }
	  
	  
	  public static void main (String[] args){
		  	Bank BankMove = new Bank();
	        System.out.println("Deposit Threads \t\t\t Withdrawal Threads \t\t Balance");
	        System.out.println("--------------- \t\t\t ------------------ \t\t -----------");
	        
	        
	        Deposit depositThread1 = new Deposit(BankMove);
	        depositThread1.setName("Thread D1");
	        Deposit depositThread2 = new Deposit(BankMove);
	        depositThread2.setName("Thread D2");
	        Deposit depositThread3 = new Deposit(BankMove);
	        depositThread3.setName("Thread D3");
	        Deposit depositThread4 = new Deposit(BankMove);
	        depositThread4.setName("Thread D4");
	        Deposit depositThread5 = new Deposit(BankMove);
	        depositThread5.setName("Thread D5");
	        
	        Withdraw withdrawThread1 = new Withdraw(BankMove);
	        withdrawThread1.setName("Thread W1");
	        Withdraw withdrawThread2 = new Withdraw(BankMove);
	        withdrawThread2.setName("Thread W2");
	        Withdraw withdrawThread3 = new Withdraw(BankMove);
	        withdrawThread3.setName("Thread W3");
	        Withdraw withdrawThread4 = new Withdraw(BankMove);
	        withdrawThread4.setName("Thread W4");
	        Withdraw withdrawThread5 = new Withdraw(BankMove);
	        withdrawThread5.setName("Thread W5");
	        Withdraw withdrawThread6 = new Withdraw(BankMove);
	        withdrawThread6.setName("Thread W6");
	        Withdraw withdrawThread7 = new Withdraw(BankMove);
	        withdrawThread7.setName("Thread W7");
	        Withdraw withdrawThread8 = new Withdraw(BankMove);
	        withdrawThread8.setName("Thread W8");
	        Withdraw withdrawThread9 = new Withdraw(BankMove);
	        withdrawThread9.setName("Thread W9");
	        
	        depositThread1.start();
	        depositThread2.start();
	        depositThread3.start();
	        depositThread4.start();
	        depositThread5.start();
	        
	        withdrawThread1.start();
	        withdrawThread2.start();
	        withdrawThread3.start();
	        withdrawThread4.start();
	        withdrawThread5.start();
	        withdrawThread6.start();
	        withdrawThread7.start();
	        withdrawThread8.start();
	        withdrawThread9.start();
	    }
	
	
}
