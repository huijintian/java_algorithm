package cn.algorithm.sort;

/**
 * Created by mengtian on 2018/5/4
 */
public class SelectionSort {
    public int[] sort(int[] list) {
        for (int i = 0; i < list.length; i++) {
            int small = list[i];
            for (int j = i + 1; j < list.length; j++) {
                if (list[j] < small) {//比较两个数，使small一直为最小的。
                    swap(list, i, j);
                    small = list[i];
                }
            }
        }
        return list;
    }

    public void swap(int[] list, int i, int j) {
        list[i] = list[i] ^ list[j];
        list[j] = list[i] ^ list[j];
        list[i] = list[i] ^ list[j];
    }

    public static void main(String[] args) {
        int[] sortList = new SelectionSort().sort(new int[]{3, 1, 9, 4, 31, 35, 22, 13});
        for (int i : sortList) {
            System.out.print(i + "\t");
        }
    }
}
