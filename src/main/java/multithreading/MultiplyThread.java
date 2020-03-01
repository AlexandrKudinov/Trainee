package multithreading;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

class MultiplyThread implements Runnable {
    static final BlockingQueue<BigInteger> integers = Multiplier.getQueueFromFile();

    @Override
    public void run() {
        while (integers.size() > 1) {
            try {
                BigInteger first = null;
                BigInteger second = null;
                synchronized (integers) {
                    if (integers.size() > 1) {
                        second = integers.take();
                        first = integers.take();
                    }
                }
                integers.put(first.multiply(second));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}