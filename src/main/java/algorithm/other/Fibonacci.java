package algorithm.other;

/**
 * Created by mengtian on 2018/5/4
 */
public class Fibonacci {
    /**
     * 普通递归
     *
     * @param n
     * @return
     */
    public long fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * 尾递归方式
     *
     * @param n
     * @param res1
     * @param res2
     * @return
     */
    public long fibonacci(int n, int res1, int res2) {
        if (n == 0) return 0;
        if (n == 1) return res1;
        return fibonacci(n - 1, res2, res1 + res2);
    }

    public static void main(String[] args) {
        Fibonacci fibo = new Fibonacci();
        System.out.println(fibo.fibonacci(6));
        System.out.println(fibo.fibonacci(6, 1, 1));
        System.out.println("test add");
    }
}
