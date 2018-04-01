package Algorithm;

import java.util.Arrays;

public class searchUtils {
    public static int linearSearch(int target, int[] list) {
        int n = list.length;
        for (int i = 0; i < n; i++) {
            if (list[i] == target) {
                return i;
            }
        }
        return -1;
    }

    static boolean binarySearch(int v, int[] lst, int low, int high) {
        if (low > high) {
            System.out.println("not found");
            return false;
        }

        int middle = (low + high) / 2;

        if (v == lst[middle]) {
            System.out.println("found! It is at " + middle);
            return true;
        } else if (v > lst[middle]) {
            return binarySearch(v, lst, middle + 1, high);
        } else {
            return binarySearch(v, lst, low, middle - 1);
        }
    }

    public static void main(String[] args) {
        int[] list = {1, 2, 4, 0, 12, 5, 6, 11, 24, 17, 14};
        System.out.println(linearSearch(4, list));
        int[] sortedList = SortUtils.mergeSort(list);
        System.out.println(Arrays.toString(sortedList));
        System.out.println(binarySearch(11, sortedList, 0, list.length));
    }
}
