import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class colorThread implements Runnable{
    static test test = new test();


    public  colorThread(String color)
    {
        test.setColorChoice(color);
    }

    private Object lock2 = new Object();

    public void colorThreadMethod(){
        synchronized (this){
            System.out.println("Color Thread is running");
            Set<Integer> keys = test.hashMap.keySet();
            for (Integer key : keys)
            {
                ArrayList arrayList = test.hashMap.get(key);
                String colorTemplate="";

                colorTemplate = new String(arrayList.get(0).toString());

                arrayList.add(3, colorTemplate);
            }
        }
    }

    @Override
    public void run() {
        colorThreadMethod();
    }
}
