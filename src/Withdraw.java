import java.util.Random;


public class Withdraw extends Thread {
    private Bank accountName;
    private int depositAmount;
    private static Random RANDOMVALUE = new Random();
      
    public Withdraw(Bank name){
        accountName = name;
    }
    
    public void run() {
        while(true){
        	//withdrawals are made in amounts ranging from $1 to $50 
            depositAmount = RANDOMVALUE.nextInt(50);

            if (depositAmount == 0){
                depositAmount += 2;
            }
            if (depositAmount % 2 != 0){
                depositAmount ++;
            }

            accountName.Withdraw(depositAmount, Thread.currentThread().getName());
            Thread.yield();
        }
    }
}