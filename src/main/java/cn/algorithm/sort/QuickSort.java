package cn.algorithm.sort;

/**
 * Created by mengtian on 2018/5/4
 * <p>
 * 参考链接
 * https://www.sczyh30.com/posts/Algorithm/algorithm-quicksort/
 * <p>
 * java.util.DualPivotQuicksort 私有，非公共类里面提供了快速排序在各种情况下的实现。
 */
public class QuickSort {

    public void qsort(int[] list) {
        if (list.length < 2) return;
        qsort(list, 0, list.length - 1);

    }

    private void qsort(int[] list, int left, int right) {
        if (left > right) return;
        int idx = partition(list, left, right);
        qsort(list, left, idx - 1);
        qsort(list, idx + 1, right);
    }


    private int partition(int[] list, int left, int right) {
        int i = left, j = right;
        int pivot = list[left];
        while (i < j) {
            //由右至左找到在i之后（第一次i为0）比pivot小的数（如果有的话）
            while (i < j && list[j] > pivot) j--;
            //当上面的情况存在时，交换比pivot小的那个数到左边
            if (i < j) {
                list[i] = list[j];
                i++;
            }
            //由左至右找到在j之前比pivot大的数
            while (i < j && list[i] < pivot) i++;
            //当上面的情况存在时，交换比pivot大的那个数到刚刚找到的j的位置上
            if (i < j) {
                list[j] = list[i];
                j--;
            }
        }
        //在上面的操作中，最原始的list[i]的值即pivot被替换了，我们需要在结束前把数值放回去
        list[i] = pivot;
        return i;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] list = new int[]{3, 1, 4, 2, 9, 232, 13, 0, -1};
        quickSort.qsort(list);
        for (int i : list) {
            System.out.println(i);
        }
    }
}
