package algorithm.sort;

/**
 * Created by mengtian on 2018/5/6
 */
public class BubbleSort {
    public void sort(int[] list) {
        if (list == null || list.length < 2) return;
        for (int i = 0; i < list.length; i++) {
            for (int j = list.length - 1; j > 0; j--) {
                //将小的数字往前移
                if (list[j] < list[j - 1]) {
                    swap(list, j, j - 1);
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
        BubbleSort sort = new BubbleSort();
        int[] list = new int[]{3, 5, 6, 8, 7, 2, 4};
        sort.sort(list);
        for (int i : list) {
            System.out.print(i + "\t");
        }
        System.out.println("test add");

    }
}
