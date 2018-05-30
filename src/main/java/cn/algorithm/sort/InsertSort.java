package cn.algorithm.sort;

/**
 * Created by mengtian on 2018/5/6
 */
public class InsertSort {
    public void sort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            //i 之前的为已经排好序的数组。
            for (int j = i + 1; j > 0; j--) {
                if (list[j] < list[j - 1]) {
                    swap(list, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public void swap(int[] list, int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    public static void main(String[] args) {
        InsertSort sort = new InsertSort();
        int[] list = new int[]{3, 5, 6, 8, 7, 2, 4};
        sort.sort(list);
        for (int i : list) {
            System.out.print(i + "\t");
        }
        System.out.println("test add");

    }
}
