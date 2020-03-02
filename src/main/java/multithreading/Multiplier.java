package multithreading;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Multiplier {
    private static String readFromPath = "src/main/resources/in.txt";
    private static String writeToPath = "src/main/resources/out.txt";
    private static Integer processorsNumber;
    private static List<BigInteger> integers;

    static {
        processorsNumber = Runtime.getRuntime().availableProcessors();
        integers = getListFromFile();
    }

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

    private static List<BigInteger> getListFromFile() {
        List<BigInteger> integers = new LinkedList<>();
        try {
            integers = Files.lines(Paths.get(readFromPath))
                    .flatMap(line -> Stream.of(line.split("\t")))
                    .map(BigInteger::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return integers;
    }

    private static BigInteger oneThreadMultiply() {
        return integers.stream()
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }

    private static BigInteger manyThreadMultiply() {
        ExecutorService service = Executors.newFixedThreadPool(processorsNumber);
        int partitionSize = integers.size() / processorsNumber;

        List<List<BigInteger>> partitions = IntStream.range(0, processorsNumber)
                .mapToObj(i -> integers.subList(partitionSize * i, Math.min(partitionSize * i + partitionSize, integers.size())))
                .collect(Collectors.toList());

        BigInteger result = BigInteger.ONE;
        for (List<BigInteger> bigIntegers : partitions) {
            Future<BigInteger> future = service.submit(new MultiplyThread(bigIntegers));
            try {
                result = result.multiply(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

}