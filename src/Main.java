import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        MyThread myThread = new MyThread();
        myThread.start();

        Thread thread = new Thread(new Runner());
        thread.start();

        for(int i = 0; i<10; i++) {
            System.out.println("Tread #1 - " + i);
        }

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        myThread.shutdown();
    }
}

// 2 Way with using interface Runnable
class Runner implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i<10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Tread #3 - " + i);
        }
    }
}

// 1 Way with class which extends Thread
class MyThread extends Thread{
    private volatile boolean running = true;
    int i = 0;

    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Tread #2 - " + i);
            i++;
        }
    }

    public void shutdown(){
        this.running = false;
    }
}