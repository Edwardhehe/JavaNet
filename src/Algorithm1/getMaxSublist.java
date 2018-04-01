package Algorithm1;

import java.util.Arrays;

public class getMaxSublist {
    /*
    复杂法最高，不好
     */
    public static int[] directMethod(int[] lst) {
        return null;
    }

    /*
    左边最大，最大要么是在左面，有么在右面，要么在中间；
    中间是左边遍历一半，右面遍历一半，三个最大的好
     */
    public static double divideMethod(double[] lst, int from, int to) {
        if (from == to) {
            return lst[from];
        }
        int middle = (from + to) / 2;
        double m1 = divideMethod(lst, from, middle);
        double m2 = divideMethod(lst, middle + 1, to);
        double m3 = getMaxCross(lst, middle, from, to);
        return getMax(m1, m2, m3);
    }

    private static double getMaxCross(double[] lst, int middle, int from, int to) {
        double m3;
        double maxL = -1000;
        double maxR = -1000;
        int sumL = 0;
        int sumR = 0;
        for (int i = middle; i >= from; i--) {
            sumL += lst[i];
            if (sumL > maxL) {
                maxL = sumL;
            }
        }
        for (int i = middle + 1; i <= to; i++) {
            sumR += lst[i];
            if (sumR > maxR) {
                maxR = sumR;
            }
        }
        m3 = maxL + maxR;
        return m3;
    }

    private static double getMax(double... args) {
        double max = -1000;
        for (Double i : args) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    /*
    分析法：
    P[i]为0-i的和，S[i,j] is p[j]-p[i-1]
    遍历一遍求P[i]
     */
    public static double analysisMethod(double[] lst) {
        return 0;
    }

    /*
    动态规划方法
    以A[i]结尾的最大子数组为S[i],那么以A[i+1]结尾的最大子数组为
    max(S[i]+A[i+1],A[i+1])相当于判断S[i]是不是负数
     */
    public static double dynmaicpanMethod(double[] lst) {
        double[] record = new double[lst.length];
        for (int i = 0; i < lst.length; i++) {
            if (i == 0) {
                record[i] = lst[i];
            } else if (record[i - 1] < 0) {
                record[i] = lst[i];
            } else {
                record[i] = record[i - 1] + lst[i];
            }
        }
        return getMax(record);
    }

    public static double getClose20(double[] list) {
        double sum = 0;
        double[] p = new double[list.length];
        for (int i = 0; i < list.length; i++) {
            sum += list[i];
            p[i] = sum;
        }

        Arrays.sort(p);//排序
        double min = 1000000;
        for (int i = 0; i < list.length - 1; i++) {
            if (i == 0) {
                min = p[i];
            } else {
                if ((p[i] - p[i - 1]) < min)
                    min = (p[i] - p[i - 1]);
            }
        }
        return min;
    }

    public static void main(String[] args) {
        double[] a = {-1, 3, 2, -2, -4, 1, 10, -2, 2};
        System.out.println(divideMethod(a, 0, a.length - 1));
        System.out.println(dynmaicpanMethod(a));
    }

}
