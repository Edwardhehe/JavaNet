package Algorithm;

import java.util.Arrays;

public class SortUtils {
    public static void selectionSort(int[] lst) {
        int n = lst.length;
        for (int i = 0; i < n; i++) {
            int index = 0;
            int smallest = lst[i];
            for (int j = i; j < n; j++) {
                if (lst[j] < smallest) {
                    smallest = lst[j];
                    index = j;
                }
            }

            if (lst[i] != smallest) {
                int tmp = lst[i];
                lst[i] = smallest;
                lst[index] = tmp;
            }
        }
    }

    public static void bubleSort(int[] list) {
        boolean swap;//要确定没有swap的时候就不算了，因为这时候前一个都比后一个小了
        int n = list.length;
        for (int i = 1; i < n; i++) {
            swap = false;
            for (int j = 0; j < n - i; j++) {
                if (list[j + 1] < list[j]) {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }
    }

    public static int[] mergeSort(int[] lst) {
        if (lst.length == 1) {
            return lst;
        } else {
            //将数据放在两个盒子里
            int n = lst.length;
            int part = n / 2;
            int ileft = 0;
            int iright = 0;
            int[] lsl = new int[part];
            int[] lsr = new int[n - part];
            for (int i = 0; i < n; i++) {
                if (i < part) {
                    lsl[ileft] = lst[i];
                    ileft++;
                } else {
                    lsr[iright] = lst[i];
                    iright++;
                }
            }
            int[] sortedl = mergeSort(lsl);
            int[] sortedr = mergeSort(lsr);
            return merge(sortedl, sortedr);
        }
    }

    private static int[] merge(int[] lst1, int[] lst2) {
        int n1 = lst1.length;
        int n2 = lst2.length;
        int[] result = new int[n1 + n2];
        int index = 0;
        int ilst1 = 0;
        int ilst2 = 0;
        while (ilst1 < n1 & ilst2 < n2) {
            if (lst1[ilst1] <= lst2[ilst2]) {
                result[index] = lst1[ilst1];
                ilst1++;
            } else {
                result[index] = lst2[ilst2];
                ilst2++;
            }
            index++;
        }
        if (ilst1 < n1) {
            for (int i = index; i < (n1 + n2); i++) {
                result[i] = lst1[ilst1];
                ilst1++;
            }
        } else if (ilst2 < n2) {
            for (int i = index; i < (n1 + n2); i++) {
                result[i] = lst2[ilst2];
                ilst2++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 5, 4, 9, 11, 8, 7};
        selectionSort(a);
        System.out.println(Arrays.toString(a));
        int[] b = {1, 2, 3, 5, 4, 9, 11, 8, 7};
        bubleSort(b);
        System.out.println(Arrays.toString(b));
        int[] c = {1, 2, 3, 5, 4, 9, 11, 8, 7};
        System.out.println(Arrays.toString(mergeSort(c)));
    }
}
