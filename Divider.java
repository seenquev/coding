public class Divider {
    public int divide(int dividend, int divisor) {
        long lDividend = (long) dividend;
        long lDivisor = (long) divisor;
        long sign = 1;

        if (lDividend < 0) {
            sign *= (-1);
            lDividend = -lDividend;
        }
        if (lDivisor < 0) {
            sign *= (-1);
            lDivisor = -lDivisor;
        }

        if (lDivisor == 1) {
            return (int) Math.min(lDividend * sign, (long) Integer.MAX_VALUE);
        }

        int count = 1;
        while ((lDivisor << 1) < lDividend) {
            count++;
            lDivisor <<= 1;
        }

        long result = 0;
        for (int i = 0; i < count; i++) {
            result <<= 1;
            if (lDividend >= lDivisor) {
                lDividend -= lDivisor;
                result |= 1;
            }
            lDivisor >>= 1;
        }

        return (int) Math.min(result * sign, (long) Integer.MAX_VALUE);
    }

    public void run() {
        int dividend = Integer.MIN_VALUE;
        int divisor = 2;

        int res = divide(dividend, divisor);
        System.out.println(res);
    }
}
