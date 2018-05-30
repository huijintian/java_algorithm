package cn.algorithm.sort;

/**
 * Created by mengtian on 2018/5/6
 */
public class ShellSort {
    public void sort(int[] list) {
        int gap = 1, i, j, len = list.length;
        int temp;
        //计算步长 https://zh.wikipedia.org/wiki/%E5%B8%8C%E5%B0%94%E6%8E%92%E5%BA%8F#%E6%AD%A5%E9%95%BF%E5%BA%8F%E5%88%97
        while (gap < len / 3)
            gap = gap * 3 + 1;//1, 4, 13, 40, 121, ...
        for (; gap > 0; gap /= 3) {
            for (i = gap; i < len; i++) {
                temp = list[i];
                for (j = i - gap; j >= 0 && list[j] > temp; j -= gap) {
                    list[j + gap] = list[j];
                }
                list[j + gap] = temp;
            }
        }
    }

    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        int[] list = new int[]{55, 94, 87, 1, 4, 32, 11, 77, 39, 42, 64, 53, 70, 12, 9};
        shellSort.sort(list);
        for (int i : list) {
            System.out.print(i + "\t");
        }
        System.out.println("test add");

    }
}
