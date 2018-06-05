package algorithm.distance;

import java.util.*;

/**
 * Created by mengtian on 2018/6/1
 * <p>
 * code from https://github.com/Unknowncmbk/DiscreteFrechetDistance
 */
public class FrechetDistance {

    /**
     * 时间序列的大小
     */
    private static int DIM;
    /**
     * 缓存
     */
    private static double[][] mem;
    /**
     * 第一个时间序列
     */
    private static List<Point> timeSeriesP;
    /**
     * 第二个时间序列
     */
    private static List<Point> timeSeriesQ;

    public static double frechetDistance(String firstSeries, String secondSeries) {
        if (notNull(firstSeries) && notNull(secondSeries)) {
            timeSeriesP = parse(firstSeries);
            timeSeriesQ = parse(secondSeries);
            Collections.sort(timeSeriesP, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return o1.dimensions[0] - o2.dimensions[0];
                }
            });

            Collections.sort(timeSeriesQ, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return o1.dimensions[0] - o2.dimensions[0];
                }
            });

            System.out.println("P:" + timeSeriesP);
            System.out.println("Q:" + timeSeriesQ);

            for (Point point : timeSeriesP) {
                System.out.print(point.dimensions[0] + ",");
            }
            System.out.println();

            for (Point point : timeSeriesP) {
                System.out.print(point.dimensions[1] + ",");
            }
            System.out.println();

            for (Point point : timeSeriesQ) {
                System.out.print(point.dimensions[0] + ",");
            }

            System.out.println();

            for (Point point : timeSeriesQ) {
                System.out.print(point.dimensions[1] + ",");
            }

            System.out.println();


            return computeDiscreteFrechet(timeSeriesP, timeSeriesQ);
        }
        return Integer.MAX_VALUE;
    }

    /**
     * @param P 第一个时间序列
     * @param Q 第二个时间序列
     * @return 可以遍历两个时间序列的最短长度
     */
    private static double computeDiscreteFrechet(List<Point> P, List<Point> Q) {
        mem = new double[P.size()][Q.size()];

        for (int i = 0; i < mem.length; i++) {
            for (int j = 0; j < mem[i].length; j++) {
                mem[i][j] = -1.0;
            }
        }

        return computeDFD(P.size() - 1, Q.size() - 1);
    }

    /**
     * 计算两个序列之间的frechet distance
     * <p>
     * 算法：http://www.kr.tuwien.ac.at/staff/eiter/et-archive/cdtr9464.pdf
     *
     * @param i 行
     * @param j 列
     * @return
     */
    private static double computeDFD(int i, int j) {

        if (mem[i][j] > -1) {//已经计算过的
            return mem[i][j];
        } else if (i == 0 && j == 0) {//最左的点
            mem[i][j] = euclideanDistance(timeSeriesP.get(i), timeSeriesQ.get(j));
        } else if (i > 0 && j == 0) {//i > 0 j = 0
            mem[i][j] = max(computeDFD(i - 1, 0), euclideanDistance(timeSeriesP.get(i), timeSeriesQ.get(j)));
        } else if (i == 0 && j > 0) {
            mem[i][j] = max(computeDFD(0, j - 1), euclideanDistance(timeSeriesP.get(i), timeSeriesQ.get(j)));
        } else if (i > 0 && j > 0) {
            mem[i][j] = max(min(computeDFD(i - 1, j), computeDFD(i - 1, j - 1),
                    computeDFD(i, j - 1)), euclideanDistance(timeSeriesP.get(i), timeSeriesQ.get(j)));
        } else {
            mem[i][j] = Integer.MAX_VALUE;
        }
//        printMemory();
        return mem[i][j];
    }

    private static void printMemory() {
        System.out.println("\n");
        for (int row = 0; row < mem.length; row++) {
            for (int col = 0; col < mem[row].length; col++) {
                System.out.print(mem[row][col] + "\t");
            }
            System.out.println();
        }
    }

    public static double min(double... values) {
        double min = Integer.MAX_VALUE;
        for (double i : values) {
            if (i <= min) {
                min = i;
            }
        }
        return min;
    }


    public static double max(double... values) {
        double max = Integer.MIN_VALUE;
        for (double i : values) {
            if (i >= max) {
                max = i;
            }
        }
        return max;
    }

    /**
     * 给定两个点，计算两个点之间的欧几里得距离
     * 欧几里得距离计算方法：((|x - y|) ^ 2) ^ 1/2
     *
     * @param i 第一个点
     * @param j 第二个点
     * @return
     */
    private static double euclideanDistance(Point i, Point j) {
        double distance = 0;
        for (int n = 0; n < DIM; n++) {
            distance += Math.sqrt(Math.pow(Math.abs((i.dimensions[n] - j.dimensions[n])), 2));
        }
        return distance;
    }

    private static List<Point> parse(String input) {
        List<Point> points = new LinkedList<>();

        String[] tuples = input.split(";");
        if (tuples != null && tuples.length > 0) {
            for (String tuple : tuples) {
                String[] dims = tuple.split(",");
                if (dims != null && dims.length > 0) {
                    int[] dimensions = new int[dims.length];

                    if (DIM != dims.length) {
                        DIM = dims.length;
                    }

                    for (int i = 0; i < dims.length; i++) {
                        int val = Integer.parseInt(dims[i]);
                        dimensions[i] = val;
                    }

                    Point p = new Point(dimensions);
                    points.add(p);
                    Arrays.toString(p.dimensions);
                }
            }
        }
        return points;
    }

    private static boolean notNull(String series) {
        if (series == null || series.equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        /*String firstSeries = "64,25;42,55;37,21;34,76;77,2;98,0;9,20;20,10;12,27;32,61;88,49;60,90;99,37;85,53;7,87;67,33;20,62;4,88";
        String secondSeries = "76,92;71,59;65,73;29,52;19,13;81,6;89,36;76,10;38,93;60,44;5,26;58,84;46,16;0,55;56,71";
        */
        String firstSeries = "1,1;2,0";
        String secondSeries = "1,1;2,0";
        System.out.println(FrechetDistance.frechetDistance(firstSeries, secondSeries));
    }

}
