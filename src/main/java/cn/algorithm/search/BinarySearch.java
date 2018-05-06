package cn.algorithm.search;

import java.util.Arrays;

/**
 * Created by mengtian on 2018/5/4
 */
public class BinarySearch {
    public int search(int[] list, int target) {
        /*if (list[0] > target) {
            return -1;
        }
        if (list[list.length - 1] < target) {
            return -1;
        }*/
        int low = 0;
        int high = list.length - 1;

        while (low < high) {
            /*if (list[low] == target) {
                return low;
            }
            if (list[high] == target) {
                return high;
            }
            if (list[(low + high + 1) / 2] >= target) {
                high = (low + high + 1) / 2;
            } else {
                low = (low + high + 1) / 2;
            }*/

        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new BinarySearch().search(new int[]{1, 3, 4, 5, 8}, 4));
    }
}
