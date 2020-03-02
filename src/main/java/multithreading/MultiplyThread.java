package multithreading;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.Callable;

class MultiplyThread implements Callable<BigInteger> {
    private List<BigInteger> integers;

    public MultiplyThread(List<BigInteger> integers) {
        this.integers = integers;
    }

    @Override
    public BigInteger call() {
        return integers.stream()
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}