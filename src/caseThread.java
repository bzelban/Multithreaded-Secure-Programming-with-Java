import java.util.Set;
import java.util.Scanner;

public class caseThread implements Runnable {
    static test test = new test();

    public caseThread(String choice){
        test.setCaseChoice(choice);
    }

    //private Object lock1 = new Object();

    public void caseThreadMethod(){
        synchronized (this){
            System.out.println("Case Thread is running");
            Set<Integer> keys = test.hashMap.keySet();
            if (test.getCaseChoice().equals("U") || test.getCaseChoice().equals("u"))
            {
                for (Integer key : keys)
                {
                    test.hashMap.get(key).add(1, test.hashMap.get(key).get(0).toString().toUpperCase());
                }
            }
            else if (test.getCaseChoice().equals("L") || test.getCaseChoice().equals("l") )
            {
                for (Integer key : keys)
                {
                    test.hashMap.get(key).add(1, test.hashMap.get(key).get(0).toString().toLowerCase());
                }
            }
            else{
                System.out.println("Invalid entry...");
            }
        }
    }



    @Override
    public void run() {
        caseThreadMethod();
}
}