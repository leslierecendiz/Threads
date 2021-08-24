
import java.util.Random;

public class Deposit extends Thread {
    private Bank accountName;
    private int depositAmount;
    private static Random RANDOMVALUE = new Random();
    
    public Deposit(Bank name){
        accountName = name;
    }

   
    public void run() {
    	
        while(true){
        	//deposits  are made  in  amounts  ranging  from  $1  to  $250 
            depositAmount = RANDOMVALUE.nextInt(250);

            if (depositAmount == 0){
                depositAmount += 2;
            }
            if (depositAmount % 2 != 0){
                depositAmount ++;
            }

            accountName.Deposit(depositAmount, Thread.currentThread().getName());

            try{
                Thread.sleep(RANDOMVALUE.nextInt(300));
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        
    }
    
}