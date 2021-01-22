package com.company;

import java.util.*;

class fibbo_queue{
    private volatile ArrayList<Integer> fibbo_array = new ArrayList<>();
    private volatile ArrayList<Integer> result = new ArrayList<>();
    private volatile ArrayList<Long> computation_time = new ArrayList<>();
    private volatile Boolean end= false;
    private volatile Boolean list_empty=true;

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    public synchronized void exit(){
        notifyAll();
    }
    public int get_length(){
        return fibbo_array.size();
    }
    public synchronized void add_element(int n){
        fibbo_array.add(n);
        list_empty=false;
        notifyAll();
    }
    public synchronized Integer get_element() {
        while(list_empty && !end) {
            try{
                wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        int x;
        try {
            x = fibbo_array.remove(0);
        }
        catch (Exception e){
            return -1;
        }
        if (fibbo_array.size()==0){
            list_empty=true;
        }
        return x;

    }
    public void add_result(int res,long time){
        result.add(res);
        computation_time.add(time);
    }
    public void display(){
        ListIterator<Integer> it = result.listIterator();
        ListIterator<Long> it_time= computation_time.listIterator();
        while(it.hasNext()){
            System.out.println("fibbonacci of " + it.next() + "  time taken " +it_time.next() + " nanosec");
        }
        result = new ArrayList<>();
        computation_time = new ArrayList<>();
    }

}
class Producer{
    private static fibbo_queue qu;
    private ArrayList<Thread> consumer_thread;
    private ArrayList<Consumer> consumer;
    public Producer(int n) throws InterruptedException {
        consumer_thread = new ArrayList<>();
        consumer = new ArrayList<>();
        qu= new fibbo_queue();
        for (int i=0;i<n;i++){
            Consumer c = new Consumer(qu);
            consumer.add(c);
            Thread t = new Thread(c);
            consumer_thread.add(t);
            consumer_thread.get(i).start();
        }
    }
    public void take_input() throws InterruptedException {
        Scanner s= new Scanner(System.in);
        int n = s.nextInt();
        Boolean exit =false;
        while(!exit) {
            if (n > 0) {
                qu.add_element(n);
            } else if (n == 0) {
                qu.display();
            } else if (n == -1) {
                exit=true;
                while (qu.get_length() != 0) {

                }
                qu.setEnd(true);
                for (int i = 0; i < consumer_thread.size(); i++) {
                    consumer.get(i).setEnd(true);
                    qu.exit();
                    consumer_thread.get(i).join();
                }
                qu.display();

            }
            else {
                System.out.println("wrong input");
            }
            if (!exit) {
                n = s.nextInt();
            }
        }
    }
}
class fibbonacci{
    private static Map<Integer,Integer> instances= new HashMap<Integer, Integer>();
    public static Integer getInstance(Integer num){
        if (!instances.containsKey(num)){
            instances.put(num,calculate_fibbonacci(num));
        }
        return instances.get(num);
    }
    public static Integer calculate_fibbonacci(int num){
        if (num<2){
            return num;
        }
        else{
            return getInstance(num-1)+getInstance(num-2);
        }

    }
}
class Consumer implements Runnable{
    private Boolean end=false;
    private static fibbo_queue qu;
    public Consumer(fibbo_queue q){
        qu=q;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    @Override
    public void run() {
        while(!end) {
            int x = qu.get_element();
            if (x!=-1) {
                long start = System.nanoTime();
                int res = fibbonacci.getInstance(x);
                long end = System.nanoTime();
                long time = end - start;
                qu.add_result(res, time);
            }
        }
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException {
	// write your code here
        Scanner s = new Scanner(System.in);
        System.out.println("please give input less than 10 (max threads =10)");
        int n =s.nextInt();
        Boolean good_input=false;
        while(!good_input){
            if (n<10 && n>=1){
                good_input=true;
            }
            else {
                System.out.println("give correct input");
                s = new Scanner(System.in);
                n=s.nextInt();
            }
        }
        Producer p =new Producer(n);
        p.take_input();

    }
}
