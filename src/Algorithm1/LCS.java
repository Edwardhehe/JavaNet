package Algorithm1;

import java.util.Arrays;
import java.util.Stack;

public class LCS {
    /*
    最长公共子序列不要求连续，
    穷举法2^m*2^n
    动态规划就是，将大规模的问题转化为小规模问题；
    最后一个元素相同则：
    最后一个元素不同，则：
    由此建立最大子数组之间的递推关系
    定义C[i][j]
    和B{i][j]两种取值方法
    其实方向数组是没必要做的，c只要是左和上确定的，
    只计算最小子数组的长度，那么只需要循环数组就可以搞定。
    如果求所有的lcs，如果多个解的长度是一样的。

    if l1i!=l2i
        cij=max(ci-1j,cij-1)
    else
        cij=ci-1j-1+1;
    end

    回溯->
     */


    public static Stack<Integer> getMaxSublist(int[] ls1, int[] ls2) {
        int lc1 = ls1.length;
        int lc2 = ls2.length;

        int[][] C = new int[lc1 + 1][lc2 + 1];
        for (int i = 1; i < lc1 + 1; i++) {
            for (int j = 1; j < lc2 + 1; j++) {
                if (ls1[i - 1] == ls2[j - 1]) {
                    C[i][j] = C[i - 1][j - 1] + 1;
                } else {
                    C[i][j] = Math.max(C[i][j - 1], C[i - 1][j]);
                }
            }
        }
        EdittingDistance.screenMat(C);
        System.out.println(C[lc1][lc2]);
        Stack<Integer> stack = new Stack<>();
        int loc1 = lc1;
        int loc2 = lc2;
        for (int i = 0; i < C[lc1][lc2]; ) {
            if (C[loc1 - 1][loc2 - 1] < C[loc1][loc2]) {
                stack.push(ls2[loc2 - 1]);
                loc1--;
                loc2--;
                i++;
            } else {
                loc2--;
            }
        }
        return stack;
    }

    /*
    最长单调递增子序列，就是先排序，求公共子序列
     */
    public static Stack<Integer> getMaxDirSublist(int[] ls) {
        int lc1 = ls.length;
        int[] lcSorted = Arrays.copyOf(ls, ls.length);
        Arrays.sort(lcSorted);
        return getMaxSublist(ls, lcSorted);
    }

    public static void main(String[] args) {
        int[] lc1 = {1, 2, 4, 5, 6, 8, 5, 9};
        int[] lc2 = {1, 4, 5, 7, 8, 5, 12};
        System.out.println(getMaxSublist(lc1, lc2));
        System.out.println(getMaxDirSublist(lc1));
    }
}
