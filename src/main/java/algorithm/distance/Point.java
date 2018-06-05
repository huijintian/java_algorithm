package algorithm.distance;

import java.util.Arrays;

/**
 * Created by mengtian on 2018/6/1
 */
public class Point {

    protected int[] dimensions;

    public Point(int[] dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public String toString() {
        return "Point{" +
                "dimensions=" + Arrays.toString(dimensions) +
                '}';
    }
}
