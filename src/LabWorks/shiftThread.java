package LabWorks;

import LabWorks.test;

import java.util.ArrayList;
import java.util.Set;

public class shiftThread implements Runnable{

    static LabWorks.test test = new test();

    public shiftThread(int shift)
    {
        test.setShift(shift);
    }

    //private Object lock3 = new Object();



   public void shiftThreadMethod()
   {
       synchronized (this){
           System.out.println("Shifting Thread is running");
           Set<Integer> keys = test.hashMap.keySet();
           for (Integer key : keys)
           {
               ArrayList arrayList = test.hashMap.get(key);
               int c = arrayList.get(0).toString().charAt(0);
               arrayList.add(2,(char)(c+test.getShift())) ;
           }
       }
   }


    @Override
    public void run() {

        shiftThreadMethod();
    }
}