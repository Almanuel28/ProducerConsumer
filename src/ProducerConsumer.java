
public class ProducerConsumer {

    public static void main(String[] args) {

        Storage storage = new Storage();

        System.out.println("Start of Main Method");

        Runnable task = new Producer("Producer 1", storage);
        Runnable task2 = new Producer("Producer 2", storage);
        Runnable task3 = new Consumer("Consumer 1", storage);
        Runnable task4 = new Consumer("Consumer 2", storage);
        Runnable task5 = new Consumer("Consumer 3", storage);

        Thread producer = new Thread(task);
        Thread producer2 = new Thread(task2);
        Thread consumer = new Thread(task3);
        Thread consumer2 = new Thread(task4);
        Thread consumer3 = new Thread(task5);

        System.out.println("Starting the thread");

        producer.start();
        producer2.start();
        try {
            Thread.sleep((long) (2000 * Math.random()));
        } catch (Exception e) {

        }
        consumer.start();
        try {
            Thread.sleep(20000);
        } catch (Exception e) {

        }
        consumer2.start();
        consumer3.start();

        System.out.println("Thread started");

        System.out.println("End of Main Method");
    }
}

class Storage {
    private int data = 0;
    private int MAX_CAPACITY = 50;

    synchronized boolean produce(int p) {
        if (data + p > MAX_CAPACITY) {
            return false;
        } else {
            data += p;
            return true;
        }
    }

    synchronized int getData() {
        return data;
    }

    synchronized int printData() {
        for (int i = 0; i < data; i++) {
            System.out.print("*");
        }
        return data;
    }

    synchronized boolean consume(int c) {
        if (data - c < 0) {
            return false;
        } else {
            data -= c;
            return true;
        }
    }
}

class Consumer implements Runnable {

    private String name;
    private Storage storage;

    Consumer(String name, Storage storage) {
        this.name = name;
        this.storage = storage;
    }

    public void run() {
        while (true) {

            int c = (int) (Math.random() * 10) + 1;

            System.out.println(name + " : Consuming : " + c);

            boolean success = storage.consume(c);

            if (success) {
                System.out.println(name + " : Success : " + c);
            } else {
                System.out.println(name + " : Failed : " + c);
            }

            System.out.println("Storage: [" + storage.getData() + "]");
            storage.printData();
            System.out.println("    ");
            System.out.println("    ");
            System.out.println("    ");
            try {
                Thread.sleep((long) (1000 * Math.random()));
            } catch (Exception e) {

            }
        }
    }
}

class Producer implements Runnable {

    private String name;
    private Storage storage;

    Producer(String name, Storage storage) {
        this.name = name;
        this.storage = storage;
    }

    public void run() {
        while (true) {

            int p = (int) (Math.random() * 10) + 1;

            System.out.println(name + " : Inserting : " + p);

            boolean success = storage.produce(p);

            if (success) {
                System.out.println(name + " : Success : " + p);
            } else {
                System.out.println(name + " : Failed : " + p);
            }
            System.out.println("Storage: [" + storage.getData() + "]");
            storage.printData();
            System.out.println("    ");
            System.out.println("    ");
            System.out.println("    ");
            try {
                Thread.sleep((long) (1000 * Math.random()));
            } catch (Exception e) {

            }
        }
    }

}
