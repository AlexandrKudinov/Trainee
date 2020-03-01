package multithreading;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Multiplier {
    private static String readFromPath = "src/main/resources/in.txt";
    private static String writeToPath = "src/main/resources/out.txt";

    public static void main(String[] args) {
        long startOne = System.currentTimeMillis();
        BigInteger firstResult = oneThreadMultiply();
        long stopOne = System.currentTimeMillis();
        String oneThreadTime = "One Thread: " + (stopOne - startOne) + " ms";
        long startMany = System.currentTimeMillis();
        BigInteger secondResult = manyThreadMultiply();
        long stopMany = System.currentTimeMillis();
        String manyThreadTime = "Many Thread: " + (stopMany - startMany) + " ms";
        putResultInFile(firstResult + "\n" + oneThreadTime + "\n" + secondResult + "\n" + manyThreadTime);
    }

    private static void putResultInFile(String result) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(writeToPath))) {
            fileOutputStream.write(result.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static BlockingQueue<BigInteger> getQueueFromFile() {
        BlockingQueue<BigInteger> integers = new LinkedBlockingQueue<>();
        try {
            integers = Files.lines(Paths.get(readFromPath))
                    .flatMap(line -> Stream.of(line.split("\t")))
                    .map(BigInteger::new)
                    .collect(Collectors.toCollection(LinkedBlockingQueue::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return integers;
    }

    private static BigInteger oneThreadMultiply() {
        Queue<BigInteger> integers = getQueueFromFile();
        return integers.stream()
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }

    private static BigInteger manyThreadMultiply() {
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            service.submit(new MultiplyThread());
        }
        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return MultiplyThread.integers.peek();
    }

}

