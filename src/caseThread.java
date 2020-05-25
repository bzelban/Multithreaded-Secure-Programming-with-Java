import java.util.Set;
import java.util.Scanner;

public class caseThread implements Runnable {
    static test test = new test();

    public caseThread(String choice){
        test.setCaseChoice(choice);
    }

    @Override
    public void run() {
        System.out.println("Case Thread is running");
        Set<Integer> keys = test.hashMap.keySet();
        if (test.getCaseChoice().equals("U"))
        {
            for (Integer key : keys)
            {
                test.hashMap.get(key).add(1, test.hashMap.get(key).get(0).toString().toUpperCase());
            }
        }
        else if (test.getCaseChoice().equals("L"))
        {
            for (Integer key : keys)
            {
                test.hashMap.get(key).add(1, test.hashMap.get(key).get(0).toString().toLowerCase());
            }
        }
    }
}
