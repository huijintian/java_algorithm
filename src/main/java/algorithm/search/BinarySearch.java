package algorithm.search;

/**
 * Created by mengtian on 2018/5/4
 */
public class BinarySearch {
    public int search(int[] list, int target) {
        if (list[0] > target) {
            return -1;
        }
        if (list[list.length - 1] < target) {
            return -1;
        }
        int low = 0;
        int high = list.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >>> 1);//防止溢出
            if (list[mid] > target) {
                high = mid - 1;
            } else if (list[mid] < target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new BinarySearch().search(new int[]{1, 3, 4, 5, 8}, 7));
        System.out.println("test add");

    }
}
